package br.com.academic.genetic.algorithm;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import br.com.academic.genetic.algorithm.fitness.EvaluateFitness;

public final class BestResult {

	public static <T extends EvaluateFitness> T best(Collection<T> collection, boolean doEvaluate) {
		doEvaluate(collection, doEvaluate);

		return collection.stream()
				.sorted(Comparator.comparingDouble(EvaluateFitness::getFitnessValue).reversed())
				.findFirst()
				.orElseThrow(NoSuchElementException::new);
	}

	public static <T extends EvaluateFitness> Collection<T> best(Collection<T> collection, long qtd, boolean doEvaluate) {
		doEvaluate(collection, doEvaluate);
		
		return collection.stream()
				.sorted(Comparator.comparingDouble(EvaluateFitness::getFitnessValue).reversed())
				.limit(qtd)
				.collect(Collectors.toList());
	}
	
	private static <T extends EvaluateFitness> void doEvaluate(Collection<T> collection, boolean doEvaluate) {
		if (doEvaluate) {
			collection.forEach(EvaluateFitness::evaluate);
		}
	}

}
