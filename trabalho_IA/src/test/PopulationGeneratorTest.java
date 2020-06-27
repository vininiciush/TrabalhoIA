package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.academic.genetic.algorithm.generator.IndividualGenerator;
import br.com.academic.genetic.algorithm.generator.PopulationGenerator;
import br.com.academic.genetic.model.Individual;
import br.com.academic.genetic.model.Truck;

class PopulationGeneratorTest {

	@BeforeAll
	static void header() {
		System.out.println("###############################");
		System.out.println("### PopulationGeneratorTest ###");
		System.out.println("###############################");
	}
	
	@Test
	void test() {
		List<Individual> list = PopulationGenerator.generate(3, new IndividualGenerator());

		list.stream().forEach(i -> {
			System.out.println("Preço máximo: " + i.getMaxPrice());
			System.out.println("Volume máximo: " + i.getMaxVolume());
		});

		assertNotNull(list);
		assertEquals(list.size(), 3);
		assertTrue(list.stream().allMatch(x -> Truck.checkIfValid(x.getMaxVolume())));
	}
}
