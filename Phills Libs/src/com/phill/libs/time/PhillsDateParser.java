package com.phill.libs.time;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/** A helper class to parse dates between Java and JodaTime date.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 2.1, 22/JAN/2022 */
public class PhillsDateParser {

	/** Converts a Java Date to a Joda Time Date.
	 *  @param date - Java date
	 *  @return A Joda Time date with info coming from the Java date given as parameter. */
	public static DateTime createDate(final Date date) {
		return new DateTime(date);
	}
	
	/** Tries to create a Joda Time date parsing the given <code>date</code> string using the given <code>format</code>.
	 *  @param date - String date
	 *  @param format - A date format
	 *  @return A Joda Time date or 'null' if the given <code>date</code> could not be formatted.
	 *  @see DateTimeFormatter */
	public static DateTime createDate(final String date, final String format) {
		
		try {
			
			DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
			return formatter.parseDateTime(date);
			
		}
		catch (Exception exception) {
			return null;
		}
		
	}
	
	/** Tries to create a Joda Time date parsing the given <code>date</code> string using some known formats.
	 *  @param date - String date
	 *  @return A Joda Time date or 'null' if the given <code>date</code> does not match none of the known formats.
	 *  @see PhillsDateFormatter */
	public static DateTime createDate(final String date) {
		
		if (date == null)
			return null;
		
		// SQL DateTime + milliseconds
		if (date.matches(".*-.*-.*:.*:.*\\..*"))
			return formatDate(PhillsDateFormatter.SQL_DATE_TIME_MS, date);
		
		else if (date.matches(".*-.*-.*:.*:.*"))
			return formatDate(PhillsDateFormatter.SQL_DATE_TIME, date);
		
		else if (date.matches(".*-.*-.*"))
			return formatDate(PhillsDateFormatter.SQL_DATE, date);
		
		else if (date.matches(".*//"))
			return formatDate(PhillsDateFormatter.XLS_DATE, date);
		
		else if (date.matches(".*/.*/.*:.*:.*"))
			return formatDate(PhillsDateFormatter.AWT_DATE_TIME, date);
		
		else if (date.matches(".*/.*/.*")) {
			DateTime formattedDate = formatDate(PhillsDateFormatter.AWT_DATE, date);
			
			if (formattedDate == null)
				formattedDate = formatDate(PhillsDateFormatter.AWT_DATE_US, date);
			
			return formattedDate;
		}
		
		else if (date.length() == 8)
			return formatDate(PhillsDateFormatter.RAW_DATE, date);
		
		return null;
		
	}
	
	/** Converts a <code>date</code> from a <code>sourceFormat</code> to a <code>targetFormat</code>.
	 *  @param date - a String date
	 *  @param sourceFormat - a source date format
	 *  @param targetFormat - a target date format
	 *  @return A string date after conversion. */
	public static String convert(final String date, final String sourceFormat, final String targetFormat) {
		
		DateTime newDate = createDate(date, sourceFormat);
		
		return retrieveDate(newDate, targetFormat);
	}
	
	/** Returns the given <code>date</code> formatted using the given <code>format</code>.
	 *  @param date - Joda Time date
	 *  @param format - Joda Time date format
	 *  @return A formatted string date.
	 *  @see DateTimeFormat */
	public static String retrieveDate(final DateTime date, final PhillsDateFormatter format) {
		return (date == null) ? null : date.toString(format.getFormat());
	}
	
	/** Returns the given <code>date</code> formatted using the given <code>format</code>.
	 *  @param date - Joda Time date
	 *  @param format - a date format
	 *  @return A formatted string date.
	 *  @see DateTimeFormat */
	public static String retrieveDate(final DateTime date, final String format) {
		return date.toString(format);
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
	private static DateTime formatDate(final PhillsDateFormatter format, final String date) {
		try { return format.getFormat().parseDateTime(date); }
		catch (IllegalArgumentException exception) { exception.printStackTrace(); return null; }
	}
	
}
