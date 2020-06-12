package br.com.academic.genetic.model;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;

import br.com.academic.genetic.uitl.FileUtil;
import br.com.academic.genetic.uitl.StringUtil;

public class Product {

	private final String name;
	private final Double volume;
	private final BigDecimal price;
	private final String logoName;

	public Product(String name, Double volume, BigDecimal price, String logoName) {
		this.name = name;
		this.volume = volume;
		this.price = price;
		this.logoName = logoName;
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

	public String getLogoName() {
		return logoName;
	}

	public BufferedImage loadImage() {
		if (StringUtil.isNullOrEmpty(logoName)) {
			return null;
		}

		return FileUtil.loadImage(logoName);
	}
}
