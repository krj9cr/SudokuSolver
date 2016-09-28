import java.awt.Point;

public class Validator {
	private String errors = "";
	int numErrors = 0;
	
	public Validator() {
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	public boolean Validate(int[][] sol) {
		boolean result = true;
		errors = ""; // reset String of errors
		numErrors = 0;
		
		// Check rows
		for (int i = 0; i < 9; i++) {
			int sum = 0;
			int[] row = sol[i];
			for (int j = 0; j< 9; j++) {
				sum += row[j];
			}
			if (sum != 45) {
				result = false;
				errors += "Invalid row: " + i + "\n";
				numErrors++;
			}
		}
		// Check cols
		for (int i = 0; i < 9; i++) {
			int sum = 0;
			for (int j = 0; j< 9; j++) {
				sum += sol[j][i];
			}
			if (sum != 45) {
				result = false;
				errors += "Invalid col: " + i + "\n";
				numErrors++;
			}
		}
		// Check 3x3 quadrants
		boolean quads = true;
		quads = quads && CheckQuadrant(new Point(0,0), sol);
		quads = quads && CheckQuadrant(new Point(3,0), sol);
		quads = quads && CheckQuadrant(new Point(6,0), sol);
		quads = quads && CheckQuadrant(new Point(0,3), sol);
		quads = quads && CheckQuadrant(new Point(3,3), sol);
		quads = quads && CheckQuadrant(new Point(6,3), sol);
		quads = quads && CheckQuadrant(new Point(0,6), sol);
		quads = quads && CheckQuadrant(new Point(3,6), sol);
		quads = quads && CheckQuadrant(new Point(6,6), sol);
		if (!quads) {
			result = false;
		}
		
		return result;
	}
	
	
	
	public boolean ValidNumber(Point cell, int[][] sol) {
		boolean result = true;
		
		int value = sol[cell.x][cell.y];
		
		// Check rows
		for (int i = 0; i < 9; i++) {
			if (sol[i][cell.y] == value && i != cell.x) {
				result = false;
				break;
			}
		}
		// Check cols
		for (int i = 0; i < 9; i++) {
			if (sol[cell.x][i] == value && i != cell.y) {
				result = false;
				break;
			}
		}
		// Check 3x3 quadrant
		Point first = FirstInQuadrant(cell);
		for (int i = 0; i < 3; i++) {
			int x = i + first.x;
			for (int j = 0; j < 3; j++) {
				int y = j + first.y;
				if (sol[x][y] == value && x != cell.x && y != cell.y) {
					result = false;
					break;
				}
			}
			if (result == false) break;
		}
		
		return result;
	}
	
	public boolean CheckQuadrant(Point first, int[][] sol) {
		boolean result = true;
		int sum = 0;
		
		for (int i = 0; i < 3; i++) {
			int x = i + first.x;
			for (int j = 0; j < 3; j++) {
				int y = j + first.y;
				sum += sol[x][y];
			}
		}
		if (sum != 45) {
			result = false;
			errors += "Invalid quadrant: (" + first.x + ", " + first.y + "\n";
			numErrors++;
		}
		return result;
	}
	
	public Point FirstInQuadrant(Point cell) {
		Point result = new Point(0,0);
		
		// First col
		if (cell.x < 3) {
			// First row
			if (cell.y < 3) {
				result = new Point(0,0);
			}
			// Second row
			else if (cell.y < 6) {
				result = new Point(0,3);
			}
			// Third row
			else {
				result = new Point(0,6);
			}
		}
		// Second col
		else if (cell.x < 6) {
			// First row
			if (cell.y < 3) {
				result = new Point(3,0);
			}
			// Second row
			else if (cell.y < 6) {
				result = new Point(3,3);
			}
			// Third row
			else {
				result = new Point(3,6);
			}
		}
		// Third col
		else {
			// First row
			if (cell.y < 3) {
				result = new Point(6,0);
			}
			// Second row
			else if (cell.y < 6) {
				result = new Point(6,3);
			}
			// Third row
			else {
				result = new Point(6,6);
			}
		}
		
		return result;
	}
	
	public boolean Compare(int[][] solution, int[][] correct) {
		boolean result = true;
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (solution[i][j] != correct[i][j]) {
					result = false;
					break;
				}
			}
		}
		
		return result;
	}
}
