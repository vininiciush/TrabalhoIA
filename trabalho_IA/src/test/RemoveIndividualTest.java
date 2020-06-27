package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.academic.genetic.algorithm.RemoveIndividual;
import br.com.academic.genetic.algorithm.generator.IndividualGenerator;
import br.com.academic.genetic.algorithm.generator.PopulationGenerator;
import br.com.academic.genetic.model.Individual;

class RemoveIndividualTest {
	
	@BeforeAll
	static void header() {
		System.out.println("############################");
		System.out.println("### RemoveIndividualTest ###");
		System.out.println("############################");
	}
	
	@Test
	void noQtdtest() {
		System.out.println("===============");
		System.out.println("=== Teste 1 ===");
		System.out.println("===============");
		
		List<Individual> list = PopulationGenerator.generate(5, new IndividualGenerator());

		System.out.println("# Antes da remoção");
		list.stream().forEach(i -> {
			System.out.println("Preço máximo: " + i.getMaxPrice());
			System.out.println("Volume máximo: " + i.getMaxVolume());
		});

		Collection<Individual> removeHalf = RemoveIndividual.removeHalf(list, false);
		
		System.out.println("# Depois da remoção");
		removeHalf.stream().forEach(i -> {
			System.out.println("Preço máximo: " + i.getMaxPrice());
			System.out.println("Volume máximo: " + i.getMaxVolume());
		});
		
		assertNotNull(list);
		assertNotNull(removeHalf);
		
		removeHalf.forEach(r -> {
			assertTrue(list.contains(r));
		});
	}

	@Test
	void withQtdtest() {
		System.out.println("===============");
		System.out.println("=== Teste 2 ===");
		System.out.println("===============");
		
		List<Individual> list = PopulationGenerator.generate(5, new IndividualGenerator());

		System.out.println("# Antes da remoção");
		list.stream().forEach(i -> {
			System.out.println("Preço máximo: " + i.getMaxPrice());
			System.out.println("Volume máximo: " + i.getMaxVolume());
		});

		Collection<Individual> removeHalf = RemoveIndividual.remove(list, 8, false);
		
		System.out.println("# Depois da remoção");
		removeHalf.stream().forEach(i -> {
			System.out.println("Preço máximo: " + i.getMaxPrice());
			System.out.println("Volume máximo: " + i.getMaxVolume());
		});
		
		assertNotNull(list);
		assertNotNull(removeHalf);
		assertEquals(removeHalf.size(), 5);
		
		removeHalf.forEach(r -> {
			assertTrue(list.contains(r));
		});
	}
}
