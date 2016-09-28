import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import ConstraintAlgorithm.Constraints;
import GeneticAlgorithm.Genetics;

public class Main {

	public static void main(String[] args) {
		double startTime = System.currentTimeMillis();
		String file = "input.txt";
		int numPuzzles = 0;
		
		// Read from file
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    // Read first line for # of puzzles
			String line = br.readLine();
	    	if (line == null) {
	    		System.out.println("Could not read first line");
	    	}
	    	else {
	    		numPuzzles = Integer.parseInt(line);
	    		
	    		// For each puzzle
	    		for (int p = 0; p < numPuzzles; p++) {
	    			// Read 9 lines for puzzle
	    			int[][] puzzle = ReadPuzzle(br);
	    			
	    			// Read 9 lines for solution
	    			int[][] correct = ReadPuzzle(br);
	    			br.readLine(); // read blank line
	    			
	    			// Output so far
	    			System.out.println("Given puzzle:");
	    			PrintPuzzle(puzzle);
	    			System.out.println("Given solution:");
	    			PrintPuzzle(correct);
	    			
	    			// Validate 'correct' solution
	    			Validator validator = new Validator();
	    			boolean valid = validator.Validate(correct);
	    			if (!valid) {
	    				System.out.println("Given solution for puzzle " + p + " is not valid:");
	    				System.out.println(validator.getErrors());
	    				continue;
	    			}
	    			
	    			// Solve the puzzle
	    			Solver solver = new Solver();
	    			int[][] solution = solver.SolveBacktracking(puzzle);
//	    			Genetics genetics = new Genetics();
//	    			int[][] solution = genetics.RunGeneticAlgorithm(puzzle, correct);
	    			
	    			// Output
	    			System.out.println("Found Solution:");
	    			PrintPuzzle(solution);
	    			
	    			// Validate solution
	    			valid = validator.Validate(solution);
	    			if (!valid) {
	    				System.out.println("Solution for puzzle " + p + " is not valid:");
	    				System.out.println(validator.getErrors());
	    			}
	    			
	    			// Compare solution to correct solution
	    			valid = validator.Compare(solution, correct);
	    			if (valid) {
	    				System.out.println("CORRECT: puzzle " + p);
	    			}
	    			else {
	    				System.out.println("INCORRECT: puzzle " + p);
	    			}
	    			
	    			System.out.println();
	    		}
	    	}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		double endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + ((endTime - startTime)/1000) + "s");
	}
	
	public static int[][] ReadPuzzle(BufferedReader br) throws IOException {
		int[][] puzzle = new int[9][9];
		
		String  line = br.readLine(); 
		String str = line.trim();
		int count = 0; // keep count of 1D array
		
		// Iterate through 2D array
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				puzzle[i][j] = Integer.parseInt(Character.toString(str.charAt(count)));
				count++;
			}
		}
		return puzzle;
	}
	
	public static void PrintPuzzle(int[][] puzzle) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(puzzle[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
}
