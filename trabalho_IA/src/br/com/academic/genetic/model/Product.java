package br.com.academic.genetic.model;

import java.math.BigDecimal;

public class Product {

	private final String name;
	private final Double volume;
	private final BigDecimal price;
	private final String imageName;

	public Product(String name, Double volume, BigDecimal price, String imageName) {
		this.name = name;
		this.volume = volume;
		this.price = price;
		this.imageName = imageName;
	}

	public String getName() {
		return name;
	}

	public Double getVolume() {
		return volume;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getImageName() {
		return imageName;
	}
}
