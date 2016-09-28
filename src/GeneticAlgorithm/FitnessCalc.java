package GeneticAlgorithm;

/* Code based on: 
 * http://www.theprojectspot.com/tutorial-post/creating-a-genetic-algorithm-for-beginners/3 
 */

public class FitnessCalc {
	static int[][] solution = new int[9][9];
	static int[][] correct = new int[9][9];

    /* Public methods */
    public static void setSolution(int[][] newSolution) {
        solution = newSolution;
    }
    
    public static void setCorrect(int[][] newSolution) {
        correct = newSolution;
    }

    // Calculate inidividual's fitness by counting the number of errors
    static int getFitness(Individual individual) {
        int fitness = 0;
        
        Validator validator = new Validator();
        validator.Compare(individual.getGenes(), correct);
        fitness = validator.numErrors;

        return fitness;
    }
    
    // Get optimum fitness
    static int getMinFitness() {
        return 0;
    }
}
