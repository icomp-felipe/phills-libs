package com.phill.libs.time;

import java.util.Date;

import org.joda.time.DateTime;

public class TimeParser {

	private static DateTime formatDate(TimeFormatter format, String date) {
		try { return format.getFormat().parseDateTime(date); }
		catch (IllegalArgumentException exception) { return null; }
	}
	
	public static String retrieveDate(TimeFormatter format, DateTime date) {
		return (date == null) ? null : date.toString(format.getFormat());
	}
	
	public static DateTime createDate(Date date) {
		return new DateTime(date);
	}
	
	public static DateTime createDate(String date) {
		
		if (date == null)
			return null;
		
		if (date.matches(".*-.*-.*:.*:.*"))
			return formatDate(TimeFormatter.SQL_DATE_TIME, date);
		
		else if (date.matches(".*-.*-.*"))
			return formatDate(TimeFormatter.SQL_DATE, date);
		
		else if (date.matches(".*//"))
			return formatDate(TimeFormatter.XLS_DATE, date);
		
		else if (date.matches(".*/.*/.*:.*:.*"))
			return formatDate(TimeFormatter.AWT_DATE_TIME, date);
		
		else if (date.matches(".*/.*/.*")) {
			DateTime formattedDate = formatDate(TimeFormatter.AWT_DATE, date);
			
			if (formattedDate == null)
				formattedDate = formatDate(TimeFormatter.AWT_DATE_US, date);
			
			return formattedDate;
		}
		
		else if (date.length() == 8)
			return formatDate(TimeFormatter.RAW_DATE, date);
		
		return null;
		
	}
	
}
