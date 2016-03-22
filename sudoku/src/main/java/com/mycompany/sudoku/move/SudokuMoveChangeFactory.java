package com.mycompany.sudoku.move;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.optaplanner.core.impl.heuristic.move.Move;
import org.optaplanner.core.impl.heuristic.selector.move.factory.MoveListFactory;

import com.mycompany.sudoku.domain.BoardCell;
import com.mycompany.sudoku.domain.SudokuBoard;
import com.mycompany.sudoku.domain.Number;

public class SudokuMoveChangeFactory implements MoveListFactory<SudokuBoard> {

	/* (non-Javadoc)
	 * @see org.optaplanner.core.impl.heuristic.selector.move.factory.MoveListFactory#createMoveList(org.optaplanner.core.api.domain.solution.Solution)
	 */
	@Override
	public List<? extends Move> createMoveList(SudokuBoard solution) {
		// TODO Auto-generated method stub
		 List<Move> moveList = new ArrayList<Move>();
		 for (BoardCell cell:solution.getCells()) {
			for (Number toNumber : solution.getNumbers()) {
				moveList.add(new SudokuNumberChangeMove(cell,toNumber));
			}
			
		}
		return moveList;
	}

}
