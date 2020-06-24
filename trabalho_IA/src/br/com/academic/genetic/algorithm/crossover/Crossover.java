package br.com.academic.genetic.algorithm.crossover;

public final class Crossover {

	public static <T> T cross(Breeder<T> dad, T mom) {
		return dad.cross(mom);
	}
}
