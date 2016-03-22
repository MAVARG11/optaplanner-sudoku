package com.mycompany.sudoku.domain;

import java.io.Serializable;
import java.util.List;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import com.mycompany.sudoku.domain.solver.SudokuDifficultyComparator;
import com.mycompany.sudoku.domain.solver.SudokuNumberStrengthWeightFactory;

/**
 * @author 66755
 *
 */
//Planning Entity
@PlanningEntity(difficultyComparatorClass = SudokuDifficultyComparator.class)
public class BoardCell implements Serializable{
	private  int id;
	private static int count;
	private Row row;
	private Column col;
	private Region region;
	//private boolean fixed;
	//planning variable number
	private Number number;
	
	public BoardCell() {
		this(++count);
	}
	private BoardCell(int id){
		this.id = id;
		this.row = new Row();
		this.col = new Column();
		//this.number = new Number();
	}
	public int getId(){
		return id;
	}
	/**
	 * @return the row
	 */
	private Row getRow() {
		return row;
	}
	/**
	 * @return the col
	 */
	private Column getCol() {
		return col;
	}
	
	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}
	public int getRegionIndex(){
		return getRegion().getIndex();
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(Region region) {
		this.region = region;
	}
	/**
	 * @param index
	 */
	public void setRowIndex(int index){
		getRow().setIndex(index);
	}
	/**
	 * 
	 * @return
	 */
	@PlanningVariable(valueRangeProviderRefs = {"numberRange"},strengthWeightFactoryClass = SudokuNumberStrengthWeightFactory.class)
	public Number getNumber(){
		return this.number;
	}
	public void setNumber(Number number){
		this.number = number;
		setCellNumberInRegion(number);
	}
	private void setCellNumberInRegion(Number number){
		
	}
	/**
	 * 
	 * @return
	 */
	public int getRowIndex(){
		return getRow().getIndex();
	}
	public void setColumnIndex(int index){
		getCol().setIndex(index);
	}
	public int getColIndex(){
		return getCol().getIndex();
	}
	
	/**
	 * @return the fixed
	 */
	/*public boolean isFixed() {
		return fixed;
	}*/
	/**
	 * @param fixed the fixed to set
	 */
	/*public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}*/
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("Cell [row,col,region{" + row.getIndex() + "," + col.getIndex() 
				+","+getRegionIndex()+"} NumberValue=> ");
		if (getNumber()==null) {
			sb.append("null");
		}else{
			sb.append(getNumber().getNumberValue());
		}
		
		return sb.toString();
	}
	public int getCellNumberValue(){
		if(getNumber()==null){
			return 0;
		}else{
		return getNumber().getNumberValue();
		}
	}
	/**
	 * for difficulty comparator 
	 * @return
	 */
	public int getRowColNumberMultiplicand(){
		
		return getRowIndex()*getColIndex();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}*/
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	/*@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardCell other = (BoardCell) obj;
		if (id != other.id)
			return false;
		return true;
	}*/
	
	
}
