package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import br.com.academic.genetic.algorithm.generator.IndividualGenerator;
import br.com.academic.genetic.algorithm.generator.PopulationGenerator;
import br.com.academic.genetic.algorithm.mutation.Mutation;
import br.com.academic.genetic.model.Individual;
import br.com.academic.genetic.uitl.CloneUtil;

class MutationTest {

	@Test
	void test() {
		List<Individual> originalList = PopulationGenerator.generate(1, new IndividualGenerator());
		List<Individual> adulteredList = new ArrayList<>(originalList
				.stream()
				.map(Individual::getClone)
				.collect(Collectors.toList()));
		
		System.out.println("ANTES MUTAÇÃO ORIGINAL");
		originalList.stream().forEach(i -> {
			System.out.println("Valor Fitness: " + i.getFitnessValue());
			System.out.println("Preço máximo: " + i.getMaxPrice());
			System.out.println("Volume máximo: " + i.getMaxVolume());
			System.out.println("Binário: " + i.asBinary());
		});
		
		Mutation.mutate(adulteredList.get(0), 10);
		
		System.out.println("DEPOIS MUTAÇÃO ORIGINAL");
		originalList.stream().forEach(i -> {
			System.out.println("Valor Fitness: " + i.getFitnessValue());
			System.out.println("Preço máximo: " + i.getMaxPrice());
			System.out.println("Volume máximo: " + i.getMaxVolume());
			System.out.println("Binário: " + i.asBinary());
		});
		
		System.out.println("DEPOIS MUTAÇÃO ADULTERED");
		adulteredList.stream().forEach(i -> {
			System.out.println("Valor Fitness: " + i.getFitnessValue());
			System.out.println("Preço máximo: " + i.getMaxPrice());
			System.out.println("Volume máximo: " + i.getMaxVolume());
			System.out.println("Binário: " + i.asBinary());
		});

		assertEquals(originalList.get(0).getProducts().get(0).getProduct(), adulteredList.get(0).getProducts().get(0).getProduct());
		System.out.println(originalList.get(0).getMaxVolume());
		System.out.println(adulteredList.get(0).getMaxVolume());
		assertNotEquals(originalList.get(0).getMaxVolume(), adulteredList.get(0).getMaxVolume());
	}

}
