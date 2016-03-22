package com.mycompany.sudoku.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.mycompany.sudoku.domain.BoardCell;
import com.mycompany.sudoku.domain.Number;
import com.mycompany.sudoku.domain.Region;
import com.mycompany.sudoku.domain.SudokuBoard;

public class SudokuBoardGenerator {
	SudokuBoard board = null;
	List<BoardCell> cells;
	List<Region> regions;
	List<Number> numbers;
	
	public SudokuBoard generateSudokuBoard(){
		board = new SudokuBoard();
		
		cells = createSudokuBoardCells();
		regions = createRegions();
		assignCellsToRegionsLite(cells,regions);
		numbers = createNumbersLite();
		
		board.setCells(cells);
		board.setRegions(regions);
		board.setNumbers(numbers);
		//assign problem assigned cells
		//board.setCells(assignProblemToBoard(20,board));
		return board;
	}
	private List<BoardCell> createSudokuBoardCells(){
		List<BoardCell> cells = new ArrayList<BoardCell>(Sudoku.numberOfCells);
		for(int i=0;i<Sudoku.maxNumberOfRows;i++){
			for(int j=0;j<Sudoku.maxNumberOfColums;j++){
				BoardCell cell = new BoardCell();
				cell.setRowIndex(i+1);
				cell.setColumnIndex(j+1);
				cells.add(cell);
			}
		}
		return cells;
	}
	private List<Region> createRegions(){
		List<Region> regions = new ArrayList<Region>(Sudoku.numOfRegions);
		for (int i = 0,counter=0; i < Sudoku.numberOfRowsInRegion; i++) {
			for (int j = 0; j < Sudoku.numberOfColsInRegion; j++) {
				Region r = new Region();
				r.setRowIndex(i+1);
				r.setColumnIndex(j+1);
				++counter;
				r.setIndex(counter);
				regions.add(r);
			}
		}
		return regions;
	}
	/*private List<Number> generateSudokuProblem(int cellNumberCount){
		List<Number> numbers = new ArrayList<Number>(cellNumberCount);
		for (int i = 0; i < cellNumberCount; i++) {
			Number num = new Number();
			num.setNumberValue(ThreadLocalRandom.current().nextInt(Sudoku.lowestNumValue, Sudoku.heighestNumValue));
			numbers.add(num);
		}
		return numbers;
	}
	private List<BoardCell> assignProblemToBoard(int cellNumberCount, SudokuBoard brd){
		for (int i = 0; i < cellNumberCount; i++) {
			int rowIndex = ThreadLocalRandom.current().nextInt(1, Sudoku.maxNumberOfRows);
			int colIndex = ThreadLocalRandom.current().nextInt(1, Sudoku.maxNumberOfColums);
			int numValue = ThreadLocalRandom.current().nextInt(Sudoku.lowestNumValue, Sudoku.heighestNumValue);
			BoardCell cell = brd.getCell(rowIndex, colIndex);
			cell.setFixed(true);
			cell.getNumber().setNumberValue(numValue);
			//System.out.println("Random Cell{" + rowIndex +","+colIndex+"} "+cell);
		}
		return brd.getCells();
	}*/
	/**
	private void assignCellsToRegions(List<BoardCell> cells, List<Region> regions){
		
		for (Iterator iterator = regions.iterator(); iterator.hasNext();) {
			Region region = (Region) iterator.next();
			for (Iterator iterator2 = cells.iterator(); iterator2.hasNext();) {
				BoardCell cell = (BoardCell) iterator2.next();
				if (cell.getRowIndex()<=Sudoku.numberOfRowsInRegion && 
						cell.getColIndex()<=Sudoku.numberOfColsInRegion) {
					Region r0 = regions.get(0);
					
					if (r0.getCellsInRegion()!=null && 
							!r0.getCellsInRegion().contains(cell)) {
						r0.addCellsInRegion(cell);
						cell.setRegion(r0);
					}else if (r0.getCellsInRegion()==null){
						r0.addCellsInRegion(cell);
						cell.setRegion(r0);
					}
					
				}
				if (cell.getRowIndex()<=Sudoku.numberOfRowsInRegion && 
						(cell.getColIndex()>Sudoku.numberOfColsInRegion && 
								cell.getColIndex()<=(Sudoku.numberOfColsInRegion*2))) {
					Region r1 = regions.get(1);
					if (r1.getCellsInRegion()!=null && 
							!r1.getCellsInRegion().contains(cell)) {
						r1.addCellsInRegion(cell);
						cell.setRegion(r1);
					}else if(r1.getCellsInRegion()==null){
						r1.addCellsInRegion(cell);
						cell.setRegion(r1);
					}
				}
				if (cell.getRowIndex()<=Sudoku.numberOfRowsInRegion && 
						(cell.getColIndex()>(Sudoku.numberOfColsInRegion*2) && 
								cell.getColIndex()<=(Sudoku.numberOfColsInRegion*3))) {
					Region r2 = regions.get(2);
					if (r2.getCellsInRegion()!=null && 
							!r2.getCellsInRegion().contains(cell)) {
						r2.addCellsInRegion(cell);
						cell.setRegion(r2);
					}else if(r2.getCellsInRegion()==null){
						r2.addCellsInRegion(cell);
						cell.setRegion(r2);
					}
				}
				if (cell.getColIndex()<=Sudoku.numberOfColsInRegion && 
						(cell.getRowIndex()>(Sudoku.numberOfRowsInRegion) && 
								cell.getRowIndex()<=(Sudoku.numberOfRowsInRegion*2))) {
					Region r3 = regions.get(3);
					if (r3.getCellsInRegion()!=null && 
							!r3.getCellsInRegion().contains(cell)) {
						r3.addCellsInRegion(cell);
						cell.setRegion(r3);
					}else if(r3.getCellsInRegion()==null){
						r3.addCellsInRegion(cell);
						cell.setRegion(r3);
					}
				}
				if ((cell.getColIndex()>Sudoku.numberOfColsInRegion &&
						cell.getColIndex()<=(Sudoku.numberOfColsInRegion*2)) && 
						(cell.getRowIndex()>(Sudoku.numberOfRowsInRegion) && 
								cell.getRowIndex()<=(Sudoku.numberOfRowsInRegion*2))) {
					Region r4 = regions.get(4);
					if (r4.getCellsInRegion()!=null && 
							!r4.getCellsInRegion().contains(cell)) {
						r4.addCellsInRegion(cell);
						cell.setRegion(r4);
					}else if(r4.getCellsInRegion()==null){
						r4.addCellsInRegion(cell);
						cell.setRegion(r4);
					}
				}
				if ((cell.getColIndex()>(Sudoku.numberOfColsInRegion*2) &&
						cell.getColIndex()<=(Sudoku.numberOfColsInRegion*3)) && 
						(cell.getRowIndex()>(Sudoku.numberOfRowsInRegion) && 
								cell.getRowIndex()<=(Sudoku.numberOfRowsInRegion*2))) {
					Region r5 = regions.get(5);
					if (r5.getCellsInRegion()!=null && 
							!r5.getCellsInRegion().contains(cell)) {
						r5.addCellsInRegion(cell);
						cell.setRegion(r5);
					}else if(r5.getCellsInRegion()==null){
						r5.addCellsInRegion(cell);
						cell.setRegion(r5);
					}
				}
				if (cell.getColIndex()<=Sudoku.numberOfColsInRegion && 
						(cell.getRowIndex()>(Sudoku.numberOfRowsInRegion*2) && 
								cell.getRowIndex()<=(Sudoku.numberOfRowsInRegion*3))) {
					Region r6 = regions.get(6);
					if (r6.getCellsInRegion()!=null && 
							!r6.getCellsInRegion().contains(cell)) {
						r6.addCellsInRegion(cell);
						cell.setRegion(r6);
					}else if(r6.getCellsInRegion()==null){
						r6.addCellsInRegion(cell);
						cell.setRegion(r6);
					}
				}
				if ((cell.getColIndex()>Sudoku.numberOfColsInRegion &&
						cell.getColIndex()<=(Sudoku.numberOfColsInRegion*2)) && 
						(cell.getRowIndex()>(Sudoku.numberOfRowsInRegion*2) && 
								cell.getRowIndex()<=(Sudoku.numberOfRowsInRegion*3))) {
					Region r7 = regions.get(7);
					if (r7.getCellsInRegion()!=null && 
							!r7.getCellsInRegion().contains(cell)) {
						r7.addCellsInRegion(cell);
						cell.setRegion(r7);
					}else if(r7.getCellsInRegion()==null){
						r7.addCellsInRegion(cell);
						cell.setRegion(r7);
					}
				}
				if ((cell.getColIndex()>(Sudoku.numberOfColsInRegion*2) &&
						cell.getColIndex()<=(Sudoku.numberOfColsInRegion*3)) && 
						(cell.getRowIndex()>(Sudoku.numberOfRowsInRegion*2) && 
								cell.getRowIndex()<=(Sudoku.numberOfRowsInRegion*3))) {
					Region r8 = regions.get(8);
					if (r8.getCellsInRegion()!=null && 
							!r8.getCellsInRegion().contains(cell)) {
						r8.addCellsInRegion(cell);
						cell.setRegion(r8);
					}else if(r8.getCellsInRegion()==null){
						r8.addCellsInRegion(cell);
						cell.setRegion(r8);
					}
				}
			}
		}
		
	}
	*/
	private void assignCellsToRegionsLite(List<BoardCell> cells, List<Region> regions){
		for (BoardCell cell : cells) {
			if (cell.getRowIndex()<=Sudoku.numberOfRowsInRegion && 
					cell.getColIndex()<=Sudoku.numberOfColsInRegion) {
				Region r0 = regions.get(0);
				cell.setRegion(r0);
			}
			if (cell.getRowIndex()<=Sudoku.numberOfRowsInRegion && 
					(cell.getColIndex()>Sudoku.numberOfColsInRegion && 
							cell.getColIndex()<=(Sudoku.numberOfColsInRegion*2))) {
				Region r1 = regions.get(1);
				cell.setRegion(r1);
			}
			if (cell.getRowIndex()<=Sudoku.numberOfRowsInRegion && 
					(cell.getColIndex()>(Sudoku.numberOfColsInRegion*2) && 
							cell.getColIndex()<=(Sudoku.numberOfColsInRegion*3))) {
				Region r2 = regions.get(2);
				cell.setRegion(r2);
			}
			if (cell.getColIndex()<=Sudoku.numberOfColsInRegion && 
					(cell.getRowIndex()>(Sudoku.numberOfRowsInRegion) && 
							cell.getRowIndex()<=(Sudoku.numberOfRowsInRegion*2))) {
				Region r3 = regions.get(3);

				cell.setRegion(r3);

			}
			if ((cell.getColIndex()>Sudoku.numberOfColsInRegion &&
					cell.getColIndex()<=(Sudoku.numberOfColsInRegion*2)) && 
					(cell.getRowIndex()>(Sudoku.numberOfRowsInRegion) && 
							cell.getRowIndex()<=(Sudoku.numberOfRowsInRegion*2))) {
				Region r4 = regions.get(4);

				cell.setRegion(r4);
			}
			if ((cell.getColIndex()>(Sudoku.numberOfColsInRegion*2) &&
					cell.getColIndex()<=(Sudoku.numberOfColsInRegion*3)) && 
					(cell.getRowIndex()>(Sudoku.numberOfRowsInRegion) && 
							cell.getRowIndex()<=(Sudoku.numberOfRowsInRegion*2))) {
				Region r5 = regions.get(5);
				cell.setRegion(r5);
			}
			if (cell.getColIndex()<=Sudoku.numberOfColsInRegion && 
					(cell.getRowIndex()>(Sudoku.numberOfRowsInRegion*2) && 
							cell.getRowIndex()<=(Sudoku.numberOfRowsInRegion*3))) {
				Region r6 = regions.get(6);
				cell.setRegion(r6);
			}
			if ((cell.getColIndex()>Sudoku.numberOfColsInRegion &&
					cell.getColIndex()<=(Sudoku.numberOfColsInRegion*2)) && 
					(cell.getRowIndex()>(Sudoku.numberOfRowsInRegion*2) && 
							cell.getRowIndex()<=(Sudoku.numberOfRowsInRegion*3))) {
				Region r7 = regions.get(7);
				cell.setRegion(r7);
			}
			if ((cell.getColIndex()>(Sudoku.numberOfColsInRegion*2) &&
					cell.getColIndex()<=(Sudoku.numberOfColsInRegion*3)) && 
					(cell.getRowIndex()>(Sudoku.numberOfRowsInRegion*2) && 
							cell.getRowIndex()<=(Sudoku.numberOfRowsInRegion*3))) {
				Region r8 = regions.get(8);
				cell.setRegion(r8);
			}
		}
	}
	/*
	 * number should be 1-9 repeating 9 times
	 */
	private List<Number> createNumbers(){
		List<Number> numbers = new ArrayList<Number>(Sudoku.countOfNumbersInBoard);
		for(int i=0;i<Sudoku.maxNumberOfRows;i++){
			for(int j=0;j<Sudoku.maxNumberOfColums;j++){
				Number n = new Number();
				n.setNumberValue(j+1);
				numbers.add(n);
			}
		}
		return numbers;
	}
	private List<Number> createNumbersLite(){
		List<Number> numbers = new ArrayList<Number>(Sudoku.heighestNumValue);
		for(int i=1;i<=Sudoku.heighestNumValue;i++){
			Number n = new Number();
			n.setNumberValue(i);
			numbers.add(n);
		}
		Collections.shuffle(numbers);
		return numbers;
	}

}
