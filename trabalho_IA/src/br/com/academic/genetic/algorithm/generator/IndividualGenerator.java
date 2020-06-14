package br.com.academic.genetic.algorithm.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.academic.genetic.model.Individual;
import br.com.academic.genetic.model.IndividualProduct;
import br.com.academic.genetic.model.Product;
import br.com.academic.genetic.model.ProductStatus;
import br.com.academic.genetic.model.Truck;
import br.com.academic.genetic.service.ProductsFlyWeight;

public final class IndividualGenerator implements Generator<Individual> {

	private final Random random = new Random();

	@Override
	public Individual generate() {
		List<IndividualProduct> individualProducts = new ArrayList<>();

		for (int i = 0; i < ProductsFlyWeight.numberOfElements(); i++) {
			int number = random.nextInt(2);
			Product product = ProductsFlyWeight.getProduct(i + 1);
			ProductStatus stateProduct = ProductStatus.fromInt(number);
			individualProducts.add(new IndividualProduct(product, stateProduct));
		}

		return evaluateIfValidIndividual(new Individual(individualProducts));
	}

	private Individual evaluateIfValidIndividual(Individual individual) {
		while (!checkIfValidIndividual(individual)) {
			List<IndividualProduct> products = individual.getOccupiedProducts();
			int index = random.nextInt(products.size());
			products.get(index).setState(ProductStatus.NON_OCCUPIED);
		}

		return individual;
	}

	private boolean checkIfValidIndividual(Individual individual) {
		return individual.getMaxVolume() <= Truck.MAXCAPACITY;
	}
}
