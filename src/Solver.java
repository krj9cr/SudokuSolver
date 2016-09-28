import java.awt.Point;

public class Solver {
	
	public Solver() {
	}
	
	public int[][] SolveBacktracking(int[][] puzzle) {
		Validator validator = new Validator();
		
		int[][] solution = puzzle.clone();
		
		// Get list of empty cells
		Point[] empties = GetInitialEmptyCells(puzzle);
		int empty_idx = 0;
		
		// Traverse the empty cells
		while (empties[empty_idx] != null) {
			Point currCell = empties[empty_idx];
			
			// Increment current cell by 1
			puzzle[currCell.x][currCell.y] += 1;
			
			// Check if we passed 9
			if (puzzle[currCell.x][currCell.y] > 9) {
				puzzle[currCell.x][currCell.y] = 0; // reset
				empty_idx--; // backtrack
				continue;
			}
			
			// Check that this value is allowed here
			boolean valid = validator.ValidNumber(currCell, puzzle);
			
			if (valid) {
				empty_idx++;
			}
//			else {
//				puzzle[currCell.x][currCell.y] = 0; // reset
//				empty_idx--; // backtrack
//			}
		}
		
		return solution;
	}
	
	public Point[] GetInitialEmptyCells(int[][] puzzle) {
		Point[] cells = new Point[81]; // at most 81 cells
		// Initialize Points to null
		for (int i = 0; i < 81; i++) {
			cells[i] = null;
		}
		
		// Find empty cells
		int count = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (puzzle[i][j] == 0) {
					cells[count] = new Point(i,j);
					count++;
				}
			}
		}
		
		return cells;
	}
	
	public void PrintPuzzle(int[][] puzzle) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(puzzle[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
}
