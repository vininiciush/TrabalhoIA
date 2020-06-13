package br.com.academic.genetic.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import br.com.academic.genetic.model.Product;

public final class ProductsFlyWeight {

	private static Map<Integer, Product> products;

	static {
		products = new HashMap<Integer, Product>();
		products.put(1, new Product("Geladeira Dako", 0.751, new BigDecimal(999.90), ""));
		products.put(2, new Product("Notebook Dell", 0.00350, new BigDecimal(2499.90), ""));
		products.put(3, new Product("Microondas Panasonic", 0.0319, new BigDecimal(299.29), ""));
		products.put(4, new Product("Notebook Asus", 0.527, new BigDecimal(3999.00), ""));
		products.put(5, new Product("Iphone 6", 0.0000899, new BigDecimal(2199.12), ""));
		products.put(6, new Product("Ventilador Panasonic", 0.496, new BigDecimal(199.90), ""));
		products.put(7, new Product("Geladeira Brastemp", 0.635, new BigDecimal(849.00), ""));
		products.put(8, new Product("TV 55'", 0.400, new BigDecimal(4346.99), ""));
		products.put(9, new Product("Microondas Electrolux", 0.0424, new BigDecimal(308.66), ""));
		products.put(10, new Product("Geladeira Consul", 0.870, new BigDecimal(1199.89), ""));
		products.put(11, new Product("TV 50'", 0.290, new BigDecimal(3999.90), ""));
		products.put(12, new Product("TV 42'", 0.200, new BigDecimal(2999.90), ""));
		products.put(13, new Product("Microondas LG", 0.0544, new BigDecimal(429.90), ""));
		products.put(14, new Product("Notebook Lenovo", 0.498, new BigDecimal(1999.90), ""));
	}

	public static Product getProduct(Integer index) {
		return products.get(index);
	}

	public static int numberOfElements() {
		return products.size();
	}
}
