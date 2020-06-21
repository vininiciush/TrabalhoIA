package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.academic.genetic.algorithm.FitnessFunction;
import br.com.academic.genetic.algorithm.generator.IndividualGenerator;
import br.com.academic.genetic.algorithm.generator.PopulationGenerator;
import br.com.academic.genetic.model.Individual;

class FitnessFunctionTest {

	@Test
	void test() {
		List<Individual> list = PopulationGenerator.generate(5, new IndividualGenerator());
		FitnessFunction.calculate((List) list);

		list.forEach(i -> {
			System.out.println("Fitness value: " + i.getFitnessValue());
			System.out.println("Max price: " + i.getMaxPrice());
			System.out.println("Max volume: " + i.getMaxVolume());
		});

		assertTrue(list.get(0).getFitnessValue() > 0);
	}

}
