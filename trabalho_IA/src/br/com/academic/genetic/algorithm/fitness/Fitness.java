package br.com.academic.genetic.algorithm.fitness;

import java.util.Collection;

public final class Fitness {

	public static void evaluate(Collection<EvaluateFitness> collection) {
		collection.forEach(EvaluateFitness::evaluate);
	}
}
