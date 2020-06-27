package br.com.academic.genetic.algorithm;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

import br.com.academic.genetic.algorithm.fitness.EvaluateFitness;

public final class BestResult extends Common {

	public static <T extends EvaluateFitness> T best(Collection<T> collection, boolean doEvaluate) {
		doEvaluate(collection, doEvaluate);

		return collection.stream()
				.sorted(Comparator.comparingDouble(EvaluateFitness::getFitnessValue).reversed())
				.findFirst()
				.orElseThrow(NoSuchElementException::new);
	}

	public static <T extends EvaluateFitness> Collection<T> best(Collection<T> collection, int qtd, boolean doEvaluate) {
		qtd = Math.min(collection.size(), qtd);		
		return get(collection, qtd, doEvaluate);
	}
}
