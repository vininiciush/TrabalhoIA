package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.academic.genetic.algorithm.generator.IndividualGenerator;
import br.com.academic.genetic.algorithm.generator.PopulationGenerator;
import br.com.academic.genetic.algorithm.selection.Roulette;
import br.com.academic.genetic.algorithm.selection.Selection;
import br.com.academic.genetic.model.Individual;

class SelectionTest {

	@Test
	void noQtdtest() {
		System.out.println("===============");
		System.out.println("=== Teste 1 ===");
		System.out.println("===============");
		List<Individual> list = PopulationGenerator.generate(10, new IndividualGenerator());

		list.stream().forEach(i -> {
			System.out.println("Preço máximo: " + i.getMaxPrice());
			System.out.println("Volume máximo: " + i.getMaxVolume());
		});
		
		Collection<Individual> select = Selection.select(list, new Roulette<Individual>());
		
		System.out.println("--- Selecionados ---");
		select.stream().forEach(i -> {
			System.out.println("Preço máximo: " + i.getMaxPrice());
			System.out.println("Volume máximo: " + i.getMaxVolume());
		});
		
		assertNotNull(select);
	}
	
	@Test
	void withQtdTest() {
		System.out.println("===============");
		System.out.println("=== Teste 2 ===");
		System.out.println("===============");
		List<Individual> list = PopulationGenerator.generate(5, new IndividualGenerator());

		list.stream().forEach(i -> {
			System.out.println("Preço máximo: " + i.getMaxPrice());
			System.out.println("Volume máximo: " + i.getMaxVolume());
		});
		
		Collection<Individual> select = Selection.select(list, 5, new Roulette<Individual>());
		
		System.out.println("--- Selecionados ---");
		select.stream().forEach(i -> {
			System.out.println("Preço máximo: " + i.getMaxPrice());
			System.out.println("Volume máximo: " + i.getMaxVolume());
		});
		
		assertNotNull(select);
		assertEquals(select.size(), 5);
	}

}
