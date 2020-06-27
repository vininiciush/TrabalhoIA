package br.com.academic.genetic.algorithm.selection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import br.com.academic.genetic.algorithm.fitness.EvaluateFitness;

public class Roulette<T extends EvaluateFitness> implements SelectionAlgorithm<T> {

	private Map<T, List<Double>> relativeAptitude = new HashMap<>();
	private double remaining = 0.0;

	@Override
	public Collection<T> select(Collection<T> collection) {
		int qtd = collection.size() / 2;
		return select(collection, qtd);
	}
	
	public Collection<T> select(Collection<T> collection, int qtd) {
		dataPrepare(collection);
		List<T> selected = new ArrayList<>();
		Random random = new Random();
		
		for (int i = 0; i < qtd; i++) {
			Double randomNumber = random.nextDouble();

			relativeAptitude.forEach((obj, values) -> {
				if(values.get(0) <= randomNumber && values.get(1) > randomNumber) {
					selected.add(obj);
				}
			});
		}
		
		return selected;
	}

	private void dataPrepare(Collection<T> collection) {
		Double sum = collection.stream()
				.mapToDouble(EvaluateFitness::getFitnessValue)
				.sum();

		collection.forEach(i -> {
			List<Double> properties = new ArrayList<>();
			properties.add(remaining);
			properties.add(remaining += i.getFitnessValue() / sum);
			properties.add(i.getFitnessValue() / sum);
			
			relativeAptitude.put(i, properties);
		});

//		printRelativeAptitude();
	}

	private void printRelativeAptitude() {
		System.out.println("--- Relative Aptitudes ---");
		relativeAptitude.forEach((i, r) -> {
			System.out.println("Individual Fitness Value: " + i.getFitnessValue());
			System.out.println("Relative Aptitude: " + r);
		});
	}
}
