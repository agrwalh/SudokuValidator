package com.assignment5.sudoku;

class SudokuValidatorService {

	public static void validateAll(int[][] grid) throws InvalidSudokuException {
		new RowChecker(grid).validate();
		new ColumnChecker(grid).validate();
		new BoxChecker(grid).validate();
	}
}