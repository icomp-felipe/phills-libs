package com.phill.libs.time;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/** Contains some known date formats to be used in {@link DateParser} methods.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 2.0, 24/SEP/2020 */
public enum DateFormatter {

	SQL_DATE_TIME(DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss")),
	SQL_DATE     (DateTimeFormat.forPattern("YYYY-MM-dd")),
	AWT_DATE_TIME(DateTimeFormat.forPattern("dd/MM/YYYY HH:mm:ss")),
	AWT_DATE     (DateTimeFormat.forPattern("dd/MM/YYYY")),
	AWT_DATE_US  (DateTimeFormat.forPattern("MM/dd/YYYY")),
	RAW_DATE     (DateTimeFormat.forPattern("ddMMyyyy")),
	XLS_DATE     (DateTimeFormat.forPattern("yyyyMMdd//"));
	
	private DateTimeFormatter formatter;
	
	DateFormatter(DateTimeFormatter formatter) {
		this.formatter = formatter;
	}

	/** Retrieves a Joda Time formatter corresponding to the selected enum.
	 *  @return A Joda Time formatter corresponding to the selected enum.
	 *  @see DateTimeFormatter */
	public DateTimeFormatter getFormat() {
		return this.formatter;
	}
	
}
