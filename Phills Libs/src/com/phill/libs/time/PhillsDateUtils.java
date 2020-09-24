package com.phill.libs.time;

import org.joda.time.*;

public class PhillsDateUtils {

	/** Compares two dates.
	 *  @return 0 if the dates are the same;<br>
	 * 			1 if date1 > date2;<br>
	 * 		   -1 if date1 < date2. */
	public static int compare(final DateTime date1, final DateTime date2) {
		return DateTimeComparator.getDateOnlyInstance().compare(date1, date2);
	}
	
	/** Compares two dates using the given <code>format</code>.
	 *  @return 0 if the dates are the same;<br>
	 * 			1 if date1 > date2;<br>
	 * 		   -1 if date1 < date2. */
	public static int compare(final String date1, final String date2, final String format) {
		
		DateTime dt1 = PhillsDateParser.createDate(date1, format);
		DateTime dt2 = PhillsDateParser.createDate(date2, format);
		
		return DateTimeComparator.getDateOnlyInstance().compare(dt1, dt2);
		
	}
	
	/** Retrieves the current semester as an integer.
	 *  @return Current semester. */
	public static int getCurrentSemester() {
		return new DateTime().getMonthOfYear() / 7 + 1;
	}
	
	/** Retrieves the current year as an integer.
	 *  @return Current year. */
	public static int getCurrentYear() {
		return new DateTime().getYear();
	}
	
	/** Returns the days passed between the <code>start</code> and <code>end</code> dates.
	 *  @param start - start date
	 *  @param end - end date
	 *  @return the days passed between the <code>start</code> and <code>end</code> dates. */
	public static long getDurationDays(final DateTime start, final DateTime end) {
		return new Duration(start, end).getStandardDays();
	}
	
	/** Gets the milliseconds of the <code>date</code> instant from the Java epoch of 1970-01-01T00:00:00Z.
	 *  @param date - String date
	 *  @param format - A date format
	 *  @return Milliseconds since the Java epoch of 1970-01-01T00:00:00Z, or '-1' if the given <code>date</code> could not be formatted. */
	public static long getMillis(final String date, final String format) {
		try { return PhillsDateParser.createDate(date, format).getMillis(); }
		catch (NullPointerException exception) { return -1L; }
	}
	
	/** Retrieves the current date respecting the given <code>format</code>.
	 *  @param format - a date format
	 *  @return The current formatted system date. */
	public static String now(final String format) {
		return PhillsDateParser.retrieveDate(new DateTime(), format);
	}
	
	/** Tells if the given <code>date</code> is in the past. Always remember, today is not in the past!
	 *  @param date - a date
	 *  @return 'true' if the date is in the past, or 'false' otherwise. */
	public static boolean past(final DateTime date) {
		return compare(date, new DateTime()) < 0;
	}
	
	/** Retrieves the years passed since the given <code>date</code> until today.
	 *  @param date - String date
	 *  @param format - A date format
	 *  @return Years since the given <code>date</code> until today. */
	public static int yearsSince(final String date, final String format) {
		
		DateTime current = new DateTime();
		DateTime givendt = PhillsDateParser.createDate(date, format);
		
		return Years.yearsBetween(givendt, current).getYears();
	}
	
}
