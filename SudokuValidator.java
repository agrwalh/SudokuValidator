// package com.assignment5.sudoku;

import java.util.Scanner;

public class SudokuValidator {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		while (true) {

			System.out.println("\n1. Validate Sudoku");
			System.out.println("2. Play Game");
			System.out.println("3. Exit");

			int choice;

			if (scanner.hasNextInt()) {
				choice = scanner.nextInt();
			} else {
				System.out.println("Enter a valid number.");
				scanner.next();
				continue;
			}

			if (choice == 1) {

				int[][] grid = new int[9][9];

				System.out.println("Enter Sudoku (9x9 grid, values 1-9):");

				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {

						if (scanner.hasNextInt()) {
							int val = scanner.nextInt();

							if (val >= 1 && val <= 9) {
								grid[i][j] = val;
							} else {
								System.out.println("Invalid input! Enter numbers between 1-9.");
								j--; // same position dobara fill karwao
							}

						} else {
							System.out.println("Invalid input! Enter only numbers.");
							scanner.next();
							j--; // retry same cell
						}
					}
				}

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
					level = scanner.next();

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

					if (scanner.hasNextInt()) {
						r = scanner.nextInt();
					} else {
						System.out.println("Invalid input. Enter numbers only.");
						scanner.next();
						continue;
					}

					if (scanner.hasNextInt()) {
						c = scanner.nextInt();
					} else {
						System.out.println("Invalid input. Enter numbers only.");
						scanner.next();
						continue;
					}

					if (scanner.hasNextInt()) {
						num = scanner.nextInt();
					} else {
						System.out.println("Invalid input. Enter numbers only.");
						scanner.next();
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
				System.out.println("Exited");
				break;
			}

			else {
				System.out.println("Invalid choice.");
			}
		}

		scanner.close();
	}
}
