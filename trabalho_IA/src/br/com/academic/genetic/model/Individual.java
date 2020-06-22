package br.com.academic.genetic.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import br.com.academic.genetic.algorithm.crossover.Cruiser;
import br.com.academic.genetic.algorithm.fitness.EvaluateFitness;

public final class Individual implements EvaluateFitness, Cruiser<Individual> {

	private final List<IndividualProduct> products;
	private Double fitnessValue;

	public Individual(List<IndividualProduct> products) {
		this.products = products;
	}

	public List<IndividualProduct> getProducts() {
		return products;
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
	
	public Collection<Byte> asBinary(){
		return products.stream()
				.map(IndividualProduct::getState)
				.map(ProductStatus::getState)
				.collect(Collectors.toList());
	}

	@Override
	public void evaluate() {
		fitnessValue = getMaxPrice();
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
}
