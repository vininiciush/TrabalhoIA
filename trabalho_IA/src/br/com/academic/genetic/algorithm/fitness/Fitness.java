package br.com.academic.genetic.algorithm.fitness;

import java.util.Collection;

public final class Fitness {

	public static Collection<EvaluateFitness> evaluate(Collection<EvaluateFitness> collection) {
		collection.forEach(EvaluateFitness::evaluate);
		return collection;
	}
	
	public static void evaluate(EvaluateFitness individual) {
		individual.evaluate();
	}
}
