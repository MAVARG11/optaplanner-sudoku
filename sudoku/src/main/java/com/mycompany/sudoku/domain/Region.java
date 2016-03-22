package com.mycompany.sudoku.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.mycompany.sudoku.common.Sudoku;

public class Region {
	private int index;
	private Row row;
	private Column col;
	
	public Region() {
		super();
		this.row = new Row();
		this.col = new Column();
		// TODO Auto-generated constructor stub
	}
	public Region(int index){
		this();
		this.index=index;
	}
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	private Row getRow(){
		return this.row;
	}
	private Column getColumn(){
		return this.col;
	}
	public int getRowIndex(){
		return getRow().getIndex();
	}
	public int getColumnIndex(){
		return getColumn().getIndex();
	}
	public void setRowIndex(int index){
		if (index>Sudoku.numberOfRowsInRegion) {
			throw new IllegalArgumentException("Row index should be less than "+Sudoku.numberOfRowsInRegion);
		}
		getRow().setIndex(index);
	}
	public void setColumnIndex(int index){
		if (index>Sudoku.numberOfColsInRegion) {
			throw new IllegalArgumentException("Column index should be less than "+Sudoku.numberOfColsInRegion);
		}
		getColumn().setIndex(index);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Region [index=" + index + ", Row and Column Index ={"+getRowIndex()+","+getColumnIndex()
				+"}";
	}
	

}
