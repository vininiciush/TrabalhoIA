package br.com.academic.genetic.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.academic.genetic.algorithm.crossover.Crossover;
import br.com.academic.genetic.algorithm.mutation.Mutation;
import br.com.academic.genetic.algorithm.selection.Roulette;
import br.com.academic.genetic.algorithm.selection.Selection;
import br.com.academic.genetic.model.Individual;
import br.com.academic.genetic.view.Final_View;
import main.Session;

public class GeneticAlgorithm {

	public static void Execute(int generation, List<Individual> individuals, int LimitGeneration) {
		for(int i = generation; i < LimitGeneration; i++) {
			//Seleciona individuos para o crossover
			List<Individual> select = (List<Individual>) Selection.selectHalf(individuals, new Roulette<Individual>());
			//gera individuos a partir dos selecionados
			List<Individual> individuals_cross = Crossover(select);
			//Seleciona os melhores
			List<Individual> selecionados_prox_passo = new ArrayList<Individual>();
			selecionados_prox_passo.addAll(individuals);
			selecionados_prox_passo.addAll(individuals_cross);
			individuals = (List<Individual>) BestResult.best(selecionados_prox_passo, individuals.size(), true);
			//Executa Mutações
			if(generation %Session.getInstance().getMutationFreq() == 0)
				Mutate(individuals,3);
		}
		Final_View final_View = new Final_View(individuals,LimitGeneration);
		final_View.setVisible(true);
	}
	
	private static List<Individual> Crossover(List<Individual> individuals) {
		List<Individual> individualsReturn = new ArrayList<Individual>();
		for(int x = 0; x < individuals.size()-1; x++) {
			for(int i = x+1; i < individuals.size(); i++) {
				Individual son = Crossover.cross(individuals.get(x).getClone(), individuals.get(i).getClone());
				individualsReturn.add(son);
			}
		}
		return individualsReturn;
	}
	
	private static void Mutate(List<Individual> individuals, int numMutate) {
		Random random = new Random();
		for(int x = 0; x < numMutate; x++) {
			Mutation.mutate(individuals.get(random.nextInt(individuals.size())), random.nextInt(3));
		}
	}
	
}
