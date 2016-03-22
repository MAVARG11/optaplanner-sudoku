package com.mycompany.sudoku.domain.solver;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.optaplanner.core.impl.heuristic.selector.common.decorator.SelectionSorterWeightFactory;

import com.mycompany.sudoku.domain.Number;
import com.mycompany.sudoku.domain.SudokuBoard;

public class SudokuNumberStrengthWeightFactory implements
		SelectionSorterWeightFactory<SudokuBoard, Number> {

	/* (non-Javadoc)
	 * @see org.optaplanner.core.impl.heuristic.selector.common.decorator.SelectionSorterWeightFactory#createSorterWeight(org.optaplanner.core.api.domain.solution.Solution, java.lang.Object)
	 */
	@Override
	public Comparable createSorterWeight(SudokuBoard solution, Number selection) {
		return new NumberStrengthWeight(selection);
	}
	
	public static class NumberStrengthWeight implements Comparable<NumberStrengthWeight>{
		Number number;

		public NumberStrengthWeight(Number number) {
			super();
			this.number = number;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		/**
		 * @return the number
		 */
		public Number getNumber() {
			return number;
		}

		/**
		 * @param number the number to set
		 */
		public void setNumber(Number number) {
			this.number = number;
		}

		@Override
		public int compareTo(NumberStrengthWeight num) {
			return new CompareToBuilder()
			.append(this.number.getNumberValue(), num.getNumber().getNumberValue())
			.append(this.number.getId(), num.getNumber().getId())
			.toComparison();
		}
		
	}
	
}
