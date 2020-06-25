package br.com.academic.genetic.algorithm.crossover;

public interface Breeder<T> {

	T cross(T partner);
}
