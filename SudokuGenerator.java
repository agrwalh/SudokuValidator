// package com.assignment5.sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class SudokuGenerator {

	public static boolean fillGrid(int[][] grid) {

		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {

				if (grid[row][col] == 0) {

					List<Integer> numbers = new ArrayList<>();
					for (int i = 1; i <= 9; i++) {
						numbers.add(i);
					}

					Collections.shuffle(numbers);

					for (int num : numbers) {

						if (isSafe(grid, row, col, num)) {

							grid[row][col] = num;

							if (fillGrid(grid)) {
								return true;
							}

							grid[row][col] = 0;
						}
					}

					return false;
				}
			}
		}

		return true;
	}

	private static boolean isSafe(int[][] grid, int row, int col, int num) {

		for (int i = 0; i < 9; i++) {
			if (grid[row][i] == num)
				return false;
			if (grid[i][col] == num)
				return false;
		}

		int startRow = row - (row % 3);
		int startCol = col - (col % 3);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (grid[startRow + i][startCol + j] == num) {
					return false;
				}
			}
		}

		return true;
	}

	public static void applyDifficulty(int[][] grid, String level) {

		int remove;

		if (level.equalsIgnoreCase("medium")) {
			remove = 30;
		} else if (level.equalsIgnoreCase("hard")) {
			remove = 50;
		} else {
			remove = 20;
		}

		Random rand = new Random();

		while (remove > 0) {

			int row = rand.nextInt(9);
			int col = rand.nextInt(9);

			if (grid[row][col] != 0) {
				grid[row][col] = 0;
				remove--;
			}
		}
	}
}
