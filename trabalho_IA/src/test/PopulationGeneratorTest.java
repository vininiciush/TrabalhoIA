package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.academic.genetic.algorithm.generator.IndividualGenerator;
import br.com.academic.genetic.algorithm.generator.PopulationGenerator;
import br.com.academic.genetic.model.Individual;
import br.com.academic.genetic.model.Truck;

class PopulationGeneratorTest {

	@Test
	void test() {
		List<Individual> list = PopulationGenerator.generate(3, new IndividualGenerator());

		list.stream().forEach(i -> {
			System.out.println("Pre�o m�ximo: " + i.getMaxPrice());
			System.out.println("Volume m�ximo: " + i.getMaxVolume());
		});

		assertEquals(list.size(), 3);
		assertTrue(list.stream().allMatch(x -> x.getMaxVolume() < Truck.MAXCAPACITY));
	}
}
