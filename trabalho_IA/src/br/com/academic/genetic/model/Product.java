package br.com.academic.genetic.model;

public class Product {

	private final String name;
	private final Double volume;
	private final Double price;
	private final String imageName;

	public Product(String name, Double volume, Double price, String imageName) {
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

	public Double getPrice() {
		return price;
	}

	public String getImageName() {
		return imageName;
	}
}
