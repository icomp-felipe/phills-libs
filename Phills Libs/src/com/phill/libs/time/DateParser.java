package com.phill.libs.time;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/** A helper class to parse dates between Java and JodaTime date.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 2.0, 24/SEP/2020 */
public class DateParser {

	/** Converts a Java Date to a Joda Time Date.
	 *  @param date - Java date
	 *  @return A Joda Time date with info coming from the Java date given as parameter. */
	public static DateTime createDate(final Date date) {
		return new DateTime(date);
	}
	
	/** Tries to create a Joda Time date parsing the given <code>date</code> string using some known formats.
	 *  @param date - String date
	 *  @return A Joda Time date or 'null' if the given <code>date</code> does not match none of the known formats.
	 *  @see TimeFormatter */
	public static DateTime createDate(final String date) {
		
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
	
	/** Returns the given <code>date</code> formatted using the given <code>format</code>.
	 *  @param format - Joda Time date format
	 *  @param date - String date
	 *  @return A formatted string date.
	 *  @see DateTimeFormat */
	public static String retrieveDate(final TimeFormatter format, final DateTime date) {
		return (date == null) ? null : date.toString(format.getFormat());
	}
	
	/** Returns a string formatted like '12 h 57 min 34 s'.
	 *  using a timestamp in 'sec' format.
	 *  @param seconds - timestamp in seconds
	 *  @return Time in format '12 h 57 min 34 s'. */
	public static String getHumanReadableTime(long seconds) {
		
        if(seconds < 0)
            return "0s";
        
        long hours = TimeUnit.SECONDS.toHours(seconds);
        seconds   -= TimeUnit.HOURS.toSeconds(hours);
        
        long minutes = TimeUnit.SECONDS.toMinutes(seconds);
        seconds -= TimeUnit.MINUTES.toSeconds(minutes);

        StringBuilder sb = new StringBuilder(64);
        
        if (hours > 0) {
        	sb.append(hours);
        	sb.append(" h ");
        }
        if (minutes > 0) {
        	sb.append(minutes);
        	sb.append(" min ");
        }
        sb.append(seconds);
        sb.append(" s");

        return(sb.toString());
    }
	
	/**************************** Internal Methods Section ***************************************/
	
	/** Creates a Joda Time <code>date</code> parsing the given string date using <code>format</code>.
	 *  @param format - Joda Time date format
	 *  @param date - String date
	 *  @return A Joda Time date with data coming from <code>date</code>.
	 *  @see DateTimeFormat */
	private static DateTime formatDate(final TimeFormatter format, final String date) {
		try { return format.getFormat().parseDateTime(date); }
		catch (IllegalArgumentException exception) { return null; }
	}
	
}
