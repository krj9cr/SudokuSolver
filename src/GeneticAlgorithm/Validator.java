package GeneticAlgorithm;

/* Code based on: 
 * http://www.theprojectspot.com/tutorial-post/creating-a-genetic-algorithm-for-beginners/3 
 */

public class Validator {
	int numErrors = 0;
	
	public boolean Compare(int[][] solution, int[][] correct) {
		boolean result = true;
		numErrors = 0;
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (solution[i][j] != correct[i][j]) {
					result = false;
					numErrors++;
				}
			}
		}
		
		return result;
	}
}
