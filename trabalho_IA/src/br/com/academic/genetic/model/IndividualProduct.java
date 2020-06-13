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

	public Product getProduct() {
		return product;
	}
}
