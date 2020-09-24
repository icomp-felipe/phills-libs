package com.phill.libs.time;

import java.text.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Years;

public class DateUtils {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private static long getMillisFromDate(String date) throws ParseException {
		return sdf.parse(date).getTime();
	}
	
	public static boolean parseDates(String data1, String data2) {
		try {
			long date1 = getMillisFromDate(data1);
			long date2 = getMillisFromDate(data2);
			return (date1 > date2);
		}
		catch (ParseException exception) {
			return false;
		}
		
	}
	
	/** Converte uma data no formato DD/MM/YYYY para o formato SQL */
	public static String parseMySQLDate(String data) {
		String formattedDate = null;
		try {
			Date raw = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(raw);
		}
		catch (ParseException e) {
			return null;
		}
		return formattedDate;
	}
	
	/** Recupera uma data do formato SQL para o formato DD/MM/YYYY */
	public static String recoverMySQLDate(String data) {
		String formattedDate = null;
		try {
			if (data.equals("0000-00-00")) {
				return "00/00/0000";
			}
			Date raw = new SimpleDateFormat("yyyy-MM-dd").parse(data);
			formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(raw);
		}
		catch (ParseException e) {
			formattedDate = "00/00/0000";
		}
		return formattedDate;
	}
	
	public static String recoverXLSDate(String data) {
		try {
			return String.format("%s/%s/%s",data.substring(0,2),data.substring(2,4),data.substring(4,8));
		}
		catch (Exception exception) {
			return data;
		}
	}
	
	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public static int getCurrentSemester() {
		return new DateTime().getMonthOfYear() / 6 + 1;
	}
	
	public static boolean dataPassada(DateTime date) {

		DateTime today = new DateTime();
		long duration = getDurationDays(today, date);

		return (duration < 0);
	}

	public static long getDurationDays(DateTime start, DateTime end) {
		
		Duration duration = new Duration(start, end);
		return duration.getStandardDays();
		
	}
	
	/** Retorna a data atual do sistema */
	public static String getSystemDate(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static int yearsSince(String date) {
		
		date = parseMySQLDate(date);

		DateTime current = new DateTime(System.currentTimeMillis());
		DateTime givendt = new DateTime(date);
		
		return Years.yearsBetween(givendt, current).getYears();
	}
	
	public static List<LocalDate> getDatesBetween(String startDate, String endDate) {
		
		List<LocalDate> dateList = new ArrayList<LocalDate>();
		
		LocalDate start = LocalDate.parse(startDate);
		LocalDate end   = LocalDate.parse(endDate);
		
		while (!start.isAfter(end)) {
			
			dateList.add(start);
			start = start.plusDays(1L);
			
		}
		
		return dateList;
		
	}
	
}
