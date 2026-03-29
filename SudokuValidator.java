// package com.assignment5.sudoku;

import java.util.Scanner;

public class SudokuValidator {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.println("\n1. Validate Sudoku");
			System.out.println("2. Play Game");
			System.out.println("3. Exit");

			int choice;

			if (sc.hasNextInt()) {
				choice = sc.nextInt();
			} else {
				System.out.println("Enter a valid number.");
				sc.next();
				continue;
			}

			if (choice == 1) {

				int[][] grid = { { 5, 3, 4, 6, 7, 8, 9, 1, 2 }, { 6, 7, 2, 1, 9, 5, 3, 4, 8 },
						{ 1, 9, 8, 3, 4, 2, 5, 6, 7 }, { 8, 5, 9, 7, 6, 1, 4, 2, 3 }, { 4, 2, 6, 8, 5, 3, 7, 9, 1 },
						{ 7, 1, 3, 9, 2, 4, 8, 5, 6 }, { 9, 6, 1, 5, 3, 7, 2, 8, 4 }, { 2, 8, 7, 4, 1, 9, 6, 3, 5 },
						{ 3, 4, 5, 2, 8, 6, 1, 7, 9 } };

				try {
					SudokuUtil.validateStructure(grid);

					new RowChecker(grid).validate();
					new ColumnChecker(grid).validate();
					new BoxChecker(grid).validate();

					System.out.println("Sudoku is VALID");

				} catch (InvalidSudokuException e) {
					System.out.println("Sudoku is INVALID");
					System.out.println("Reason: " + e.getMessage());
				}
			}

			else if (choice == 2) {

				int[][] grid = new int[9][9];

				SudokuGenerator.fillGrid(grid);

				String level;
				while (true) {
					System.out.println("Enter difficulty (easy/medium/hard): ");
					level = sc.next();

					if (level.equalsIgnoreCase("easy") || level.equalsIgnoreCase("medium")
							|| level.equalsIgnoreCase("hard")) {
						break;
					} else {
						System.out.println("Invalid difficulty. Try again.");
					}
				}

				SudokuGenerator.applyDifficulty(grid, level);

				while (true) {

					for (int i = 0; i < 9; i++) {
						for (int j = 0; j < 9; j++) {
							System.out.print((grid[i][j] == 0 ? ". " : grid[i][j] + " "));
						}
						System.out.println();
					}

					System.out.println("Enter row col num (1-9) or 0 0 0 to exit:");

					int r, c, num;

					if (sc.hasNextInt()) {
						r = sc.nextInt();
					} else {
						System.out.println("Invalid input. Enter numbers only.");
						sc.next();
						continue;
					}

					if (sc.hasNextInt()) {
						c = sc.nextInt();
					} else {
						System.out.println("Invalid input. Enter numbers only.");
						sc.next();
						continue;
					}

					if (sc.hasNextInt()) {
						num = sc.nextInt();
					} else {
						System.out.println("Invalid input. Enter numbers only.");
						sc.next();
						continue;
					}

					if (r == 0 && c == 0 && num == 0)
						break;

					if (r < 1 || r > 9 || c < 1 || c > 9 || num < 1 || num > 9) {
						System.out.println("Invalid range. Use values between 1 and 9.");
						continue;
					}

					r--;
					c--;

					int old = grid[r][c];
					grid[r][c] = num;

					try {
						new RowChecker(grid).validate();
						new ColumnChecker(grid).validate();
						new BoxChecker(grid).validate();

						System.out.println("Valid move");

					} catch (InvalidSudokuException e) {
						grid[r][c] = old;
						System.out.println("Invalid move: " + e.getMessage());
					}

					boolean complete = true;
					for (int i = 0; i < 9; i++) {
						for (int j = 0; j < 9; j++) {
							if (grid[i][j] == 0) {
								complete = false;
							}
						}
					}

					if (complete) {
						System.out.println("Sudoku Completed!");
						break;
					}
				}
			}

			else if (choice == 3) {
				System.out.println("Exit");
				break;
			}

			else {
				System.out.println("Invalid choice.");
			}
		}

		sc.close();
	}
}
