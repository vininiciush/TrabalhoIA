package br.com.academic.genetic.algorithm;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import br.com.academic.genetic.algorithm.fitness.EvaluateFitness;

public abstract class Common {
	
	protected static <T extends EvaluateFitness> Collection<T> get(Collection<T> collection, int qtd, boolean doEvaluate) {
		doEvaluate(collection, doEvaluate);
		qtd = Math.min(collection.size(), qtd);
		
		return collection.stream()
				.sorted(Comparator.comparingDouble(EvaluateFitness::getFitnessValue).reversed())
				.limit(qtd)
				.collect(Collectors.toList());
	}
	
	protected static <T extends EvaluateFitness> void doEvaluate(Collection<T> collection, boolean doEvaluate) {
		if (doEvaluate) {
			collection.forEach(EvaluateFitness::evaluate);
		}
	}

}
