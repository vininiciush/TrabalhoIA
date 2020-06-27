package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.academic.genetic.algorithm.BestResult;
import br.com.academic.genetic.algorithm.fitness.EvaluateFitness;
import br.com.academic.genetic.algorithm.generator.IndividualGenerator;
import br.com.academic.genetic.algorithm.generator.PopulationGenerator;
import br.com.academic.genetic.model.Individual;

class BestResultTest {

	@BeforeAll
	static void header() {
		System.out.println("######################");
		System.out.println("### BestResultTest ###");
		System.out.println("######################");
	}
	
	@Test
	void oneBestResultTest() {
		System.out.println("===============");
		System.out.println("=== Teste 1 ===");
		System.out.println("===============");
		
		List<Individual> list = PopulationGenerator.generate(3, new IndividualGenerator());

		list.stream().forEach(i -> System.out.println("Fitness Value: " + i.getFitnessValue()));
		
		EvaluateFitness best = BestResult.best((Collection) list, false);
		System.out.println("Max Fitness Value: " + best.getFitnessValue());
		
		assertNotNull(best);
	}
	
	@Test
	void manyBestResultTest() {
		System.out.println("===============");
		System.out.println("=== Teste 2 ===");
		System.out.println("===============");
		
		List<Individual> list = PopulationGenerator.generate(5, new IndividualGenerator());

		list.stream().forEach(i -> {
			System.out.println("Fitness Value: " + i.getFitnessValue());
		});
		
		Collection<Individual> best = BestResult.best((Collection) list, 3, false);
		best.stream().forEach(i -> System.out.println("Max Fitness Value: " + i.getFitnessValue()));
		
		assertNotNull(best);
		assertEquals(best.size(), 3);
	}

}
