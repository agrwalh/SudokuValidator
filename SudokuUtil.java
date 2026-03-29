// package com.assignment5.sudoku;

class SudokuUtil {

	public static void validateStructure(int[][] grid) throws InvalidSudokuException {

		if (grid == null)
			throw new InvalidSudokuException("Grid is null");

		if (grid.length != 9)
			throw new InvalidSudokuException("Sudoku must have 9 rows");

		for (int i = 0; i < 9; i++) {

			if (grid[i] == null)
				throw new InvalidSudokuException("Row " + (i + 1) + " is null");

			if (grid[i].length != 9)
				throw new InvalidSudokuException("Row " + (i + 1) + " must have 9 columns");

			for (int j = 0; j < 9; j++) {
				if (grid[i][j] < 0 || grid[i][j] > 9) {
					throw new InvalidSudokuException(
							"Invalid number " + grid[i][j] + " at (" + (i + 1) + "," + (j + 1) + ")");
				}
			}
		}
	}

	public static boolean isComplete(int[][] grid) {
		for (int[] row : grid) {
			for (int val : row) {
				if (val == 0)
					return false;
			}
		}
		return true;
	}
}
