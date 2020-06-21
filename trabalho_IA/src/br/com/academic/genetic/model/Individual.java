package br.com.academic.genetic.model;

import java.util.List;
import java.util.stream.Collectors;

import br.com.academic.genetic.algorithm.EvaluateFitness;

public final class Individual implements EvaluateFitness {

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

	@Override
	public void evaluate() {
		fitnessValue = getMaxPrice();
	}
}
