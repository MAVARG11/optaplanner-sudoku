package com.mycompany.sudoku.domain;

import java.io.Serializable;
import java.util.List;

import com.mycompany.sudoku.common.Sudoku;

public class Number implements Serializable {
	private int id;
	private static int count;
	private int numberValue;
	//planning variable 
	private BoardCell cell;
	
	public Number(){
		this(++count);
	}
	private Number(int id) {
		this.id = id;
		numberValue=0;
	}
	public int getId(){
		return this.id;
	}
	/**
	 * @return the numberValue
	 */
	public int getNumberValue() {
		return numberValue;
	}
	/**
	 * number value should between {@link Sudoku}Sudoku.minCellNumValue and {@link Sudoku}Sudoku.maxCellNumValue
	 * @param numberValue the numberValue to set
	 */
	public void setNumberValue(int numberValue) {
		if (numberValue>Sudoku.maxCellNumValue || numberValue<Sudoku.minCellNumValue) {
			throw new IllegalArgumentException("Number Values should be between 1-9!");
		}
		this.numberValue = numberValue;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Number [id=" + id + ", numberValue=" + numberValue + "]";
	}
	/**
	 * 
	 * @return
	 */
	public BoardCell getBoardCell(){
		return this.cell;
	}
	/**
	 * 
	 * @param cell
	 */
	public void setBoardCell(BoardCell cell){
		this.cell = cell;
	}
	public int[] getCellIndex(){
		if(cell !=null){
			return new int[]{cell.getRowIndex(),cell.getColIndex()};
		}
		return new int[]{0,0};
	}
	/**
	 * Return a list of cells in the same row in the assigned boardcell 
	 * @param boardCells
	 * @return
	 */
	public List<BoardCell> getBoardCellRow(List<BoardCell> boardCells){
		return null;
	}
	/**
	 * return list of cell in the same columns in the assigned boardcell
	 * @param boardCells
	 * @return
	 */
	public List<BoardCell> getBoardCellColumn(List<BoardCell> boardCells){
		return null;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Number other = (Number) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
