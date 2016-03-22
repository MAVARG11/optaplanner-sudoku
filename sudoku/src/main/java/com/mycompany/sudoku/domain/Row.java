package com.mycompany.sudoku.domain;

import java.io.Serializable;

public class Row implements Serializable{
	private int index;
	

	public Row() {
		super();
		this.index = 0;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Row [index=" + index + "]";
	}
	

}
