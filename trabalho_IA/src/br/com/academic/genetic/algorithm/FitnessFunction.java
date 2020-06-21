package br.com.academic.genetic.algorithm;

import java.util.Collection;

public class FitnessFunction {

	public static void calculate(Collection<EvaluateFitness> collection) {
		collection.forEach(EvaluateFitness::evaluate);
	}
}
