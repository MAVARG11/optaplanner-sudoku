package com.mycompany.sudoku.solver.score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

















import com.mycompany.sudoku.common.Sudoku;
import com.mycompany.sudoku.domain.BoardCell;
import com.mycompany.sudoku.domain.Number;
import com.mycompany.sudoku.domain.Region;
import com.mycompany.sudoku.domain.SudokuBoard;


public class SudokuEasyScoreCalculator implements EasyScoreCalculator<SudokuBoard>{

	List<int[]> numValues = Arrays.asList(Sudoku.numberValues);

	public SimpleScore calculateScore(SudokuBoard sudokuBoard){
		List<Integer> scoreList = new ArrayList<Integer>();
		List<Integer> rowNumValues =null;
		List<Integer> colNumValues = null;
		boolean allNumberNotAssigned = false;
		for (int i = 1; i <= Sudoku.maxNumberOfRows; i++) {
			rowNumValues = new ArrayList<Integer>();
			colNumValues = new ArrayList<Integer>();
			for (Iterator<BoardCell> cellIterator = sudokuBoard.getCells().iterator(); cellIterator.hasNext();) {
				BoardCell cell = cellIterator.next();
				//add all cells with the same row index
				if (cell.getRowIndex()==i) {
					if (cell.getNumber()!=null) {
						rowNumValues.add(cell.getCellNumberValue());	
					}
				}
				//add all cells with the same row index
				if (cell.getColIndex()==i) {
					if (cell.getNumber()!=null) {
						colNumValues.add(cell.getCellNumberValue());
					}
				}
			}
			/*System.out.println("RowNumValues "+rowNumValues);
			System.out.println("ColNumValues "+colNumValues);*/
			boolean rowFlag = rowNumValues.containsAll(numValues);
			boolean colFlag = colNumValues.containsAll(numValues);
			//if((rowNumValues.size() >= Sudoku.maxNumberOfRows)||(colNumValues.size()>= Sudoku.maxNumberOfColums)){
				if (!colFlag || !rowFlag) {
					scoreList.add(i);
				}
			//}
		}
		int score =0;
		//System.out.println("Score List > "+scoreList);
		if (!scoreList.isEmpty()) {
			score-=scoreList.size();
		}
		return SimpleScore.valueOf(score);
	}
	/**
	 * check all number values[1-9] assigned to the rows
	 * @param rows
	 * @return
	 */
	private int checkRows(List<BoardCell> rows, int index){
		int score =0;
		List<Integer> rowNumValues = new ArrayList<Integer>(); 
		for (Iterator iterator = rows.iterator(); iterator.hasNext();) {
			BoardCell boardCell = (BoardCell) iterator.next();
			rowNumValues.add(boardCell.getCellNumberValue());
		}

		boolean containFlag = rowNumValues.containsAll(numValues);
		//System.out.println("Rows "+rowNumValues);
		//System.out.println("Contain Flag "+containFlag);
		if (!containFlag) {
			score--;
		}
		return score;
	}
	/**
	 * check all number values[1-9] is assigned to the columns
	 * @param cols
	 * @return
	 */
	private int checkColumns(List<BoardCell> cols, int index){
		List<Integer> colNumValues = new ArrayList<Integer>();
		int score = 0;
		for (Iterator iterator = cols.iterator(); iterator.hasNext();) {
			BoardCell boardCell = (BoardCell) iterator.next();
			colNumValues.add(boardCell.getCellNumberValue());
		}
		boolean containFlag = colNumValues.containsAll(numValues);
		//System.out.println("Cols "+colNumValues);
		//System.out.println("Contain Flag "+containFlag);
		if (!containFlag) {
			score--;;
		}
		return score;
	}
	/**
	 * check if cells in the region has all numbers 
	 * 
	 * @param regions
	 * @return
	 */
	private int checkRegions(List<Region> regions){
		boolean allNumberNotAssigned = false;
		for (Iterator iterator = regions.iterator(); iterator.hasNext();) {
			List<Integer> regionNumValues = new ArrayList<Integer>();
			Region region = (Region) iterator.next();
			//System.out.println("Cells in the region "+region.getCellsInRegion());
			/*for (Iterator iterator2 = region.getCellsInRegion().iterator(); iterator2
					.hasNext();) {
				BoardCell cell = (BoardCell) iterator2.next();
				if (cell.getNumber()==null) {
					allNumberNotAssigned = true;
					break;
				}
				regionNumValues.add(cell.getNumber().getNumberValue());
			}*/
			//System.out.println("Region num value "+regionNumValues);
			//System.out.println("Contain Flag "+allNumberNotAssigned);
			if (!regionNumValues.containsAll(numValues)) {
				allNumberNotAssigned = true;
				break;
			}
		}
		if (allNumberNotAssigned) {
			return -1;
		}else{
			return 1;
		}
	}

}
