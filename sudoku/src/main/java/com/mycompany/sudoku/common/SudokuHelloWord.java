package com.mycompany.sudoku.common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mycompany.sudoku.domain.BoardCell;
import com.mycompany.sudoku.domain.Region;
import com.mycompany.sudoku.domain.SudokuBoard;

public class SudokuHelloWord {
	SudokuBoard unSolvedBoard = null;
	SudokuBoard solvedBoard = null;
	protected final transient Logger logger = LoggerFactory.getLogger(getClass());
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SudokuHelloWord sh = new SudokuHelloWord();
		sh.init();
	}

	public void init(){
		
		long startTime = System.currentTimeMillis();
		SudokuBoardGenerator generator = new SudokuBoardGenerator();
		unSolvedBoard = generator.generateSudokuBoard();
		//printSolvedSolution(unSolvedBoard);
		//logger.info("Numbers =>"+unSolvedBoard.getNumbers());
		// Build the Solver
		SolverFactory solverFactory = 
				SolverFactory.createFromXmlResource("com/mycompany/sudoku/sudokuSolverConfig.xml");
		Solver solver = solverFactory.buildSolver();
		
		// Solve the problem
		solver.solve(unSolvedBoard);
		solvedBoard = (SudokuBoard) solver.getBestSolution();
		printSolvedSolution(solvedBoard);
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		logger.info("Solved in "+(duration/1000)+" seconds");
		//printRegions(solvedBoard);
		
	}
	public void printRegions(SudokuBoard board){
		for (Iterator cellIterator = board.getCells().iterator(); cellIterator.hasNext();) {
			BoardCell cell = (BoardCell) cellIterator.next();
			logger.info("Cell "+cell);
			//System.out.println(" Cell Region=> "+cell.getRegion());
		}
		for (Iterator iterator = board.getRegions().iterator(); iterator.hasNext();) {
			Region region = (Region) iterator.next();
			logger.info("Region => "+region);
		}
	}
	/**
	 * 
	 * @param board
	 */
	public void printSolvedSolution(SudokuBoard board){
		//		System.out.println(cells);
		for (int i = 0; i < Sudoku.maxNumberOfColums; i++) {
			if((i%Sudoku.numberOfColsInRegion)==0){
				System.out.print("|");
			}
			System.out.print("-");
		}
		System.out.print("|");

		for (int i=1;i<=Sudoku.maxNumberOfRows;i++) {
			System.out.println();
			System.out.print("|");
			for (int j = 1; j <= Sudoku.maxNumberOfColums; j++) {
				//System.out.println("i "+i+" J "+j);
				BoardCell c = board.getCell(i,j);
				System.out.print(c.getCellNumberValue());
				if((j%Sudoku.numberOfColsInRegion)==0){
					System.out.print("|");
				}
			}
			if((i%Sudoku.numberOfRowsInRegion)==0){
				System.out.println();
				for (int k = 0; k < Sudoku.maxNumberOfColums; k++) {
					if((k%Sudoku.numberOfColsInRegion)==0){
						System.out.print("|");
					}
					System.out.print("-");
				}
				System.out.print("|");
			}
		}

	}
}
