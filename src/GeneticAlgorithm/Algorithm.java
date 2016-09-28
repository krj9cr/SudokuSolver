package GeneticAlgorithm;

import java.util.Random;

/* Code based on: 
 * http://www.theprojectspot.com/tutorial-post/creating-a-genetic-algorithm-for-beginners/3 
 */

public class Algorithm {
	
	/* GA parameters */
    private static final double uniformRate = 0.55;
    private static final double mutationRate = 0.03;
    private static final int tournamentSize = 8;
    private static final boolean elitism = true;
    private static final int e = 1; // number of best individuals to keep

    /* Public methods */
    
    // Evolve a population
    public static Population evolvePopulation(Population pop, int[][] puzzle) {
        Population newPopulation = new Population(pop.size(), false, null);

        // Keep our best e individuals
//        if (elitism) {
//            newPopulation.saveIndividual(0, pop.getFittest());
//        }
        if (elitism) {
            for (int i = 0; i < e; i++) {
            	Individual indiv = tournamentSelection(pop);
            	newPopulation.saveIndividual(i, indiv);
            }
        }

        // Crossover population
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        // Loop over the population size and create new individuals with
        // crossover
        for (int i = elitismOffset; i < pop.size(); i++) {
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            Individual newIndiv = crossover(indiv1, indiv2, puzzle);
            newPopulation.saveIndividual(i, newIndiv);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i), puzzle);
        }

        return newPopulation;
    }

    // Crossover individuals
    private static Individual crossover(Individual indiv1, Individual indiv2, int[][] puzzle) {
        Individual newSol = new Individual(puzzle);
        // Loop through genes
        for (int i = 0; i < indiv1.size(); i++) {
        	for (int j = 0; j < indiv1.size(); j++) {
        		if (puzzle[i][j] == 0) {
	                // Crossover
	                if (Math.random() <= uniformRate) {
	                    newSol.setGene(i,j, indiv1.getGene(i,j));
	                } else {
	                    newSol.setGene(i,j, indiv2.getGene(i,j));
	                }
        		}
        	}
        }
        return newSol;
    }

    // Mutate an individual
    private static void mutate(Individual indiv, int[][] puzzle) {
    	Random random = new Random();
        // Loop through genes
        for (int i = 0; i < indiv.size(); i++) {
        	for (int j = 0; j < indiv.size(); j++) {
        		if (puzzle[i][j] == 0) {
        			if (Math.random() <= mutationRate) {
                        // Create random gene
                    	int gene = random.nextInt(10);
                        indiv.setGene(i,j, gene);
                	}
                }
        	}
        }
    }

    // Select individuals for crossover
    private static Individual tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false, null);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        // Get the fittest
        Individual fittest = tournament.getFittest();
        return fittest;
    }
}
