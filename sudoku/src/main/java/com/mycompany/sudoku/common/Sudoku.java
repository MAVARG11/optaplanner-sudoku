package com.mycompany.sudoku.common;

public interface Sudoku {
	
	public static final int numberOfCells = 9*9;
	public static final int maxNumberOfRows =9;
	public static final int maxNumberOfColums=9;
	public static final int minCellNumValue=1;
	public static final int maxCellNumValue=9;
	public static final int numOfRegions = 9;
	public static final int numOfCellsInRegion = 3*3;
	public static final int numberOfRowsInRegion = 3;
	public static final int numberOfColsInRegion = 3;
	public enum GameMode{
		EASY, MEDIUM, HARD
	};
	public static final int countOfNumbersInBoard=9*9;
	public static final int heighestNumValue = 9;
	public static final int lowestNumValue = 1;
	public static final int numberValues[] = new int[]{1,2,3,4,5,6,7,8,9};
}
