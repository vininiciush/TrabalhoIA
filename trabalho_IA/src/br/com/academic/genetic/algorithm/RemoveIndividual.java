package br.com.academic.genetic.algorithm;

import java.util.Collection;

import br.com.academic.genetic.algorithm.fitness.EvaluateFitness;

public final class RemoveIndividual extends Common {

	public static <T extends EvaluateFitness> Collection<T> removeHalf(Collection<T> collection, boolean doEvaluate) {
		int half = collection.size() / 2;
		return remove(collection, half, doEvaluate);
	}

	public static <T extends EvaluateFitness> Collection<T> remove(Collection<T> collection, int qtd, boolean doEvaluate) {
		qtd = Math.min(collection.size(), qtd);
		return get(collection, qtd, doEvaluate);
	}
}
