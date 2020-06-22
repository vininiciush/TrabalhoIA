package br.com.academic.genetic.model;

public class IndividualProduct {

	private final Product product;
	private ProductStatus productStatus;

	public IndividualProduct(Product product, ProductStatus productStatus) {
		this.product = product;
		this.productStatus = productStatus;
	}

	public ProductStatus getState() {
		return productStatus;
	}

	public void setState(ProductStatus productStatus) {
		if (this.productStatus.equals(productStatus)) {
			return;
		}

		this.productStatus = productStatus;
	}
	
	public Double getPrice() {
		return productStatus.equals(ProductStatus.OCCUPIED) ? product.getPrice() : 0.0;
	}
	
	public Double getVolume() {
		return productStatus.equals(ProductStatus.OCCUPIED) ? product.getVolume() : 0.0;
	}
}
