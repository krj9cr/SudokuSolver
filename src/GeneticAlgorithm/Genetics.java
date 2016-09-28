package GeneticAlgorithm;

/* Code based on: 
 * http://www.theprojectspot.com/tutorial-post/creating-a-genetic-algorithm-for-beginners/3 
 */

public class Genetics {

	public int[][] RunGeneticAlgorithm(int[][] puzzle, int[][] correct) {

        // Create an initial population
        Population myPop = new Population(50, true, puzzle);
        FitnessCalc.setCorrect(correct);
        
        // Evolve our population until we reach an optimum solution
        int generationCount = 0;
        while (myPop.getFittest().getFitness() > FitnessCalc.getMinFitness()) {
            generationCount++;
            //System.out.println("Generation: " + generationCount + " Fittest: " + myPop.getFittest().getFitness());
            myPop = Algorithm.evolvePopulation(myPop, puzzle);
            //System.out.println(myPop.getFittest().toString());
        }
        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes:");
        System.out.println(myPop.getFittest());
        
        return myPop.getFittest().getGenes();
    }
}
