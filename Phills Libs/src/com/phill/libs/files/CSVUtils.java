package com.phill.libs.files;

import java.io.*;

/** This class contains some useful methods to manipulate
 *  Comma Separated Value Files (.csv) in Java.
 *  @author Felipe André - felipeandre.eng@gmail.com
 *  @version 1.5, 17/SEP/2020 */
public class CSVUtils {

	// Possible CSV delimiters
	private static final String[] DELIMITERS = new String[]{",",";","\t"};
	
	/** Infers a CSV delimiter based on the one that most
	 *  appeared in the first line read from the file.
	 *  @param csvFile - CSV file
	 *  @return Infered CSV delimiter.
	 *  @throws IOException if the file could not be read. */
	public static String getCSVDelimiter(final File csvFile) throws IOException {
		
		BufferedReader stream = new BufferedReader(new FileReader(csvFile));
		String delimiter = getCSVDelimiter(stream);
		stream.close();
		
		return delimiter;
	}
	
	/** Infers a CSV delimiter based on the one that most
	 *  appeared in the first line read from the stream.
	 *  @param stream - buffered text input stream
	 *  @return Infered CSV delimiter.
	 *  @throws IOException if the stream could not be read. */
	public static String getCSVDelimiter(BufferedReader stream) throws IOException {
		
		String firstLine = stream.readLine();
		String delimiter = getCSVDelimiter(firstLine);
		
		return delimiter;
	}

	/** Infers a CSV delimiter based on the one that most
	 *  appeared in the first line read from the String.
	 *  @param firstLine - first line of a CSV string
	 *  @return Infered CSV delimiter. */
	public static String getCSVDelimiter(final String firstLine) {
		
		int mostAppearedDelimiterIndex = 0;
		String delimiter = null;
		
		for (short i=0;i<3;i++) {
			
			int fieldCount = firstLine.split(DELIMITERS[i]).length;
			
			if (fieldCount > mostAppearedDelimiterIndex) {
				mostAppearedDelimiterIndex = fieldCount;
				delimiter   = DELIMITERS[i];
			}
			
		}
		
		return delimiter;
	}
	
}
