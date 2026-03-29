package com.assignment5.sudoku;

class ColumnChecker extends SudokuChecker {

	public ColumnChecker(int[][] grid) {
		super(grid);
	}

	public boolean validate() throws InvalidSudokuException {

		for (int i = 0; i < 9; i++) {

			boolean[] visited = new boolean[10];

			for (int j = 0; j < 9; j++) {

				int num = grid[j][i];

				if (num == 0)
					continue;

				if (visited[num]) {
					throw new InvalidSudokuException("Duplicate number " + num + " found in column " + (i + 1));
				}

				visited[num] = true;
			}
		}
		return true;
	}
}