package com.mycompany.sudoku.move;

import java.util.Collection;
import java.util.Collections;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.optaplanner.core.impl.heuristic.move.AbstractMove;
import org.optaplanner.core.impl.heuristic.move.Move;
import org.optaplanner.core.impl.score.director.ScoreDirector;

import com.mycompany.sudoku.domain.BoardCell;
import com.mycompany.sudoku.domain.Number;

public class SudokuNumberChangeMove extends AbstractMove {
	
	private BoardCell cell;
	private Number toNumber;
	
	/**
	 * 
	 * @param cell
	 * @param number
	 */
	public SudokuNumberChangeMove(BoardCell cell, Number toNumber) {
		super();
		this.cell = cell;
		this.toNumber = toNumber;
	}

	@Override
	public boolean isMoveDoable(ScoreDirector scoreDirector) {
		return !ObjectUtils.equals(cell.getNumber(), toNumber);
	}

	@Override
	public Move createUndoMove(ScoreDirector scoreDirector) {
		return new SudokuNumberChangeMove(cell, cell.getNumber());
	}

	@Override
	public Collection<? extends Object> getPlanningEntities() {
		return Collections.singletonList(cell);
	}

	@Override
	public Collection<? extends Object> getPlanningValues() {
		return Collections.singletonList(toNumber);
	}

	@Override
	protected void doMoveOnGenuineVariables(ScoreDirector scoreDirector) {
		scoreDirector.beforeVariableChanged(cell, "number");
		cell.setNumber(toNumber);
		scoreDirector.afterVariableChanged(cell, "number");
	}
	@Override
	public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof SudokuNumberChangeMove) {
        	SudokuNumberChangeMove other = (SudokuNumberChangeMove) o;
            return new EqualsBuilder()
                    .append(cell, other.cell)
                    .append(toNumber, other.toNumber)
                    .isEquals();
        } else {
            return false;
        }
    }
	@Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(cell)
                .append(toNumber)
                .toHashCode();
    }
	@Override
    public String toString() {
        return cell + " {" + cell.getNumber() + " -> " + toNumber + "}";
    }

}
