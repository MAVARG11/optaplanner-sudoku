package com.mycompany.sudoku.domain.solver;

import java.io.Serializable;
import java.util.Comparator;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.mycompany.sudoku.domain.BoardCell;

public class SudokuDifficultyComparator implements Comparator<BoardCell>,
		Serializable {

	@Override
	public int compare(BoardCell a, BoardCell b) {
		// TODO Auto-generated method stub
		return new CompareToBuilder()
				.append(a.getRowColNumberMultiplicand(), b.getRowColNumberMultiplicand())
				.append(a.getId(), b.getId()).toComparison();
	}

}
