package br.com.academic.genetic.algorithm.selection;

import java.util.Collection;

public interface SelectionAlgorithm<T> {

	Collection<T> select(Collection<T> collection);

	Collection<T> select(Collection<T> collection, int qtd);
}
