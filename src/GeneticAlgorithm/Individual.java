package GeneticAlgorithm;

/* Code based on: 
 * http://www.theprojectspot.com/tutorial-post/creating-a-genetic-algorithm-for-beginners/3 
 */

import java.util.Random;

public class Individual {
	 private int[][] genes = new int[9][9];
	 private int fitness = 0;
	 
	 public Individual() {
		 
	 }
	 
	 public Individual(int[][] puzzle) {
		 this.setGenes(puzzle);
	 }

	 // Create a random individual
	 public void generateIndividual(int[][] puzzle) {
		 setGenes(puzzle);
		 Random random = new Random();
		 for (int i = 0; i < 9; i++) {
			 for (int j = 0; j < 9; j++) {
				 if (genes[i][j] == 0) {
					 int gene = random.nextInt(10);
					 genes[i][j] = gene;
				 }
			 }
		 }
	 }

	 public int[][] getGenes() {
		return genes;
	 }
	 
	 public void setGenes(int[][] puzzle) {
		 for (int i = 0; i < 9; i++) {
			 for (int j = 0; j < 9; j++) {
				 genes[i][j] = puzzle[i][j];
			 }
		 }
	 }
	 
	 public int getGene(int x, int y) {
		 return genes[x][y];
	 }

	 public void setGene(int x, int y, int value) {
		 genes[x][y] = value;
		 fitness = 0;
	 }

	 public int getFitness() {
		 if (fitness == 0) {
			 fitness = FitnessCalc.getFitness(this);
		 }
		 return fitness;
	 }
	 
	 public int size() {
		 return 9;
	 }

	 @Override
	 public String toString() {
		 String geneString = "";
		 for (int i = 0; i < 9; i++) {
			 for (int j = 0; j < 9; j++) {
				 geneString += getGene(i,j) + " ";
			 }
			 geneString += "\n";
		 }
		 return geneString;
	 }
}
