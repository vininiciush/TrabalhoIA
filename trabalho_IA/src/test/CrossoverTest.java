package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.academic.genetic.algorithm.crossover.Crossover;
import br.com.academic.genetic.algorithm.generator.IndividualGenerator;
import br.com.academic.genetic.algorithm.generator.PopulationGenerator;
import br.com.academic.genetic.model.Individual;

class CrossoverTest {

	@BeforeAll
	static void header() {
		System.out.println("#####################");
		System.out.println("### CrossoverTest ###");
		System.out.println("#####################");
	}
	
	@Test
	void test() {
		
		List<Individual> parents = PopulationGenerator.generate(2, new IndividualGenerator());
		Individual son = Crossover.cross(parents.get(0), parents.get(1));
		int size = Math.min(parents.get(0).getProducts().size(), parents.get(1).getProducts().size());
		int half = size / 2;
		
		System.out.println("Pais");
		parents.stream()
			.map(Individual::asBinary)
			.forEach(System.out::println);
		
		System.out.println("filho");
		System.out.println(son.asBinary());
		
		assertEquals(son.getProducts().subList(0, half), 
				parents.get(0).getProducts().subList(0, half));		
		assertEquals(son.getProducts().subList(half, size), 
				parents.get(1).getProducts().subList(half, size));
	}

}
