package br.com.academic.genetic.service;

import br.com.academic.genetic.model.Product;

public class ProductFactory {

	public Product create(String name, Double volume, Double price, String imageName) {
		return new Product(name, volume, price, imageName);
	}
}
