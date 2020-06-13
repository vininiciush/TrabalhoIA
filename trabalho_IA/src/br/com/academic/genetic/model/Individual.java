package br.com.academic.genetic.model;

import java.util.List;
import java.util.stream.Collectors;

public final class Individual {

	private final List<IndividualProduct> products;

	public Individual(List<IndividualProduct> products) {
		this.products = products;
	}

	public List<IndividualProduct> getProducts() {
		return products;
	}
	
	public List<IndividualProduct> getOccupiedProducts(){
		return products.stream()
				.filter(p -> p.getState().equals(ProductStatus.OCCUPIED))
				.collect(Collectors.toList());
	}
	
	public double getMaxVolume() {
		return products.stream()
				.filter(p -> p.getState().equals(ProductStatus.OCCUPIED))
				.map(IndividualProduct::getProduct)
				.mapToDouble(Product::getVolume)
				.sum();
	}
}
