package br.com.academic.genetic.algorithm.selection;

import java.util.Collection;

public final class Selection {

	public static <T> Collection<T> select(Collection<T> collection, SelectionAlgorithm<T> algorithm) {
		return algorithm.select(collection);
	}
	
	public static <T> Collection<T> select(Collection<T> collection, int qtd, SelectionAlgorithm<T> algorithm) {
		return algorithm.select(collection, qtd);
	}
}
