// package com.assignment5.sudoku;

class BoxChecker extends SudokuChecker {

	public BoxChecker(int[][] grid) {
		super(grid);
	}

	public boolean validate() throws InvalidSudokuException {

		for (int row = 0; row < 9; row += 3) {
			for (int col = 0; col < 9; col += 3) {

				boolean[] visited = new boolean[10];

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {

						int num = grid[row + i][col + j];

						if (num == 0)
							continue;

						if (visited[num]) {
							throw new InvalidSudokuException("Duplicate number " + num + " in 3x3 box starting at ("
									+ (row + 1) + "," + (col + 1) + ")");
						}

						visited[num] = true;
					}
				}
			}
		}
		return true;
	}
}
