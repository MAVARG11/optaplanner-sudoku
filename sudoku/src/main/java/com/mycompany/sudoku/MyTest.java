package com.mycompany.sudoku;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//timeDelay();
		displayDateByWeekDay();
	}
	public static void displayDateByWeekDay(){
		GregorianCalendar gDate = new GregorianCalendar();
		//date.clear();
		gDate.get(0);
		System.out.println("Date "+gDate);
		System.out.println("First week of the day "+gDate.getFirstDayOfWeek());
	}

	/**
	 * 
	 */
	public static void timeDelay() {
		long startTime = System.currentTimeMillis();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		System.out.println("Solved in "+(duration/1000)+" seconds");
	}

}
