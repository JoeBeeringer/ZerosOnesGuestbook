package de.zeros.ones.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//helper class for time 
public class DateHelper {

	/**
	 * converts date to string 
	 * @param date date  
	 * @return string with date
	 */
	public String dateToString(Date date){
		
		//sets the european date format
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		
		//returns string with right date format
		return "" + dateFormat.format(date);
	}
	
}
