package com.phill.libs;

import java.io.*;

public class CSVUtils {

	private static final String[] DELIMITERS = new String[]{",",";","\t"};
	
	public static String getCSVDelimiter(BufferedReader stream) throws IOException {
		
		String firstLine = stream.readLine();
		String delimiter = getCSVDelimiter(firstLine);
		
		return delimiter;
	}
	
	public static String getCSVDelimiter(File csvFile) throws IOException {
		
		BufferedReader stream = new BufferedReader(new FileReader(csvFile));
		String delimiter = getCSVDelimiter(stream);
		stream.close();
		
		return delimiter;
	}

	public static String getCSVDelimiter(String firstLine) throws IOException {
		
		int indiceMaior  = 0;
		String delimiter = null;
		
		for (short i=0;i<3;i++) {
			
			int fieldCount = firstLine.split(DELIMITERS[i]).length;
			
			if (fieldCount > indiceMaior) {
				indiceMaior = fieldCount;
				delimiter   = DELIMITERS[i];
			}
			
		}
		
		return delimiter;
	}
	
}
