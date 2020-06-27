package br.com.academic.genetic.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import br.com.academic.genetic.algorithm.crossover.Breeder;
import br.com.academic.genetic.algorithm.fitness.EvaluateFitness;
import br.com.academic.genetic.algorithm.mutation.Mutant;

public class Individual implements EvaluateFitness, Breeder<Individual>, Mutant {

	private final List<IndividualProduct> products;
	private ValidIndividual status;
	private Double fitnessValue;

	public Individual(List<IndividualProduct> products) {
		this.products = products;
	}

	public List<IndividualProduct> getProducts() {
		return products;
	}
	
	public ValidIndividual getStatus() {
		return status;
	}

	public Double getFitnessValue() {
		return fitnessValue;
	}

	public List<IndividualProduct> getOccupiedProducts() {
		return products.stream()
				.filter(p -> p.getState().equals(ProductStatus.OCCUPIED))
				.collect(Collectors.toList());
	}

	public Double getMaxPrice() {
		return products.stream()
				.mapToDouble(IndividualProduct::getPrice)
				.sum();
	}

	public Double getMaxVolume() {
		return products.stream()
				.mapToDouble(IndividualProduct::getVolume)
				.sum();
	}

	public Collection<Byte> asBinary() {
		return products.stream()
				.map(IndividualProduct::getState)
				.map(ProductStatus::getState)
				.collect(Collectors.toList());
	}

	@Override
	public boolean evaluate() {
		boolean valid = Truck.checkIfValid(getMaxVolume());
		
		if (valid) {
			status = ValidIndividual.VALID;
			fitnessValue = getMaxPrice();
		}
		else {
			status = ValidIndividual.INVALID;
			fitnessValue = getMaxPrice() / 3;
		}
		
		return valid;
	}

	@Override
	public Individual cross(Individual partner) {
		List<IndividualProduct> newProducts = new ArrayList<>();
		int size = Math.min(products.size(), partner.getProducts().size());
		int half = size / 2;
		products.subList(0, half).forEach(newProducts::add);
		partner.getProducts().subList(half, size).forEach(newProducts::add);

		return new Individual(newProducts);
	}
	
	@Override
	public void mutate(int mutationsNumber) {
		int size = products.size();
		Random random = new Random();
		List<Integer> indexes = new ArrayList<>();

		for (int i = 0; i < mutationsNumber; i++) {
			indexes.add(random.nextInt(size));
		}

		indexes.forEach(i -> {
			products.get(i).invertState();
			evaluate();
		});
	}
	
	public Individual getClone() {
        List<IndividualProduct> newProducts = new ArrayList<>();
        
        for (IndividualProduct individualProduct : products) {
        	newProducts.add(new IndividualProduct(individualProduct.getProduct(), individualProduct.getState()));
		}
        
        Individual individual = new Individual(newProducts);
        
        return individual;
    }
}
