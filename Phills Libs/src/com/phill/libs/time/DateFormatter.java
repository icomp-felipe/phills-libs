package com.phill.libs.time;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public enum TimeFormatter {

	SQL_DATE_TIME(DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss")),
	SQL_DATE     (DateTimeFormat.forPattern("YYYY-MM-dd")),
	AWT_DATE_TIME(DateTimeFormat.forPattern("dd/MM/YYYY HH:mm:ss")),
	AWT_DATE     (DateTimeFormat.forPattern("dd/MM/YYYY")),
	AWT_DATE_US  (DateTimeFormat.forPattern("MM/dd/YYYY")),
	RAW_DATE     (DateTimeFormat.forPattern("ddMMyyyy")),
	XLS_DATE     (DateTimeFormat.forPattern("yyyyMMdd//"));
	
	private DateTimeFormatter formatter;
	
	TimeFormatter(DateTimeFormatter formatter) {
		this.formatter = formatter;
	}
	
	public DateTimeFormatter getFormat() {
		return formatter;
	}
	
}
