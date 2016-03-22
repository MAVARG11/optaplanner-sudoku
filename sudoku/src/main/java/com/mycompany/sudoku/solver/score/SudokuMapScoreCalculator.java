package com.mycompany.sudoku.solver.score;

import java.util.Iterator;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

import com.mycompany.sudoku.domain.BoardCell;
import com.mycompany.sudoku.domain.SudokuBoard;


public class SudokuMapScoreCalculator implements EasyScoreCalculator<SudokuBoard> {

	/* (non-Javadoc)
	 * @see org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator#calculateScore(org.optaplanner.core.api.domain.solution.Solution)
	 */
	@Override
	public Score calculateScore(SudokuBoard solution) {
		// TODO Auto-generated method stub
		for (Iterator<BoardCell> cellItr = solution.getCells().iterator(); cellItr.hasNext();) {
			BoardCell cell = cellItr.next();
			
			
		}
		return null;
	}

}
