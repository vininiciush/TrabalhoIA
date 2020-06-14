package br.com.academic.genetic.algorithm.generator;

import java.util.ArrayList;
import java.util.List;

public final class PopulationGenerator {

	public static <T> List<T> generate(int individualsNumber, Generator<T> generator) {
		List<T> list = new ArrayList<>();

		for (int i = 0; i < individualsNumber; i++) {
			list.add(generator.generate());
		}

		return list;
	}
}
