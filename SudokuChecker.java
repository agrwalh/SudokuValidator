package com.assignment5.sudoku;

abstract class SudokuChecker {

	protected int[][] grid;

	public SudokuChecker(int[][] grid) {
		this.grid = grid;
	}

	abstract boolean validate() throws InvalidSudokuException;
}