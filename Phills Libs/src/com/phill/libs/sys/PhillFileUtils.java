package com.phill.libs.sys;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

/** Implements some useful methods to manipulate files in Java.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 1.5, 17/SEP/2020 */
public class PhillFileUtils {

	// Available comparators (used in 'listFilesOrdered' method)
	public static final Comparator<File> ASCENDING  = (a1,a2) -> a1.getName().compareToIgnoreCase(a2.getName());
	public static final Comparator<File> DESCENDING = (a1,a2) -> a1.getName().compareToIgnoreCase(a2.getName());
	
	/** Converts a byte count to a human readable format. Useful when displaying file sizes in UI.
	 *  @param bytes - byte count
	 *  @return A string containing a byte count in human readable format (KB, MB, GB, ..., EB). */
	public static String humanReadableByteCount(final long bytes) {
		
	    long absB = (bytes == Long.MIN_VALUE) ? Long.MAX_VALUE : Math.abs(bytes);
	    
	    if (absB < 1024) {
	        return bytes + " B";
	    }
	    
	    long value = absB;
	    CharacterIterator ci = new StringCharacterIterator("KMGTPE");
	    
	    for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
	        value >>= 10;
	        ci.next();
	    }
	    
	    value *= Long.signum(bytes);
	    
	    return String.format("%.2f %cB", value / 1024f, ci.current());
	}
	
	/** Gets all files in the given 'directory' that matches with the given 'extension'.
	 *  @param directory - the directory to have its files listed
	 *  @param extension - the file extension to be applied on the select criteria */
	public static File[] listFilesFiltered(final File directory, final String extension){
		return directory.listFiles((dir, name) -> name.toLowerCase().endsWith(extension));
	}

	/** Gets all files in the given 'directory' using the given 'order' of its filenames.
	 *  @param directory - the directory to have its files listed
	 *  @param order - order of listing files
	 *  @return An ordered list of files extracted from the given directory,
	 *  or null if 'directory' or 'order' objects are null. */
	public static File[] listFilesOrdered(final File directory, final Comparator<File> order) {
		
		if ((directory != null) && (order != null)) {
			
			File[] fileList = directory.listFiles();
			Arrays.sort(fileList,order);
			
			return fileList;
		}
		
		return null;
	}
	
	/** Reads a file to a string. This method uses UTF-8 encoding do read bytes from file.
	 *  @param file - text file to be read
	 *  @return A String with all text coming from the given file.
	 *  @throws IOException if the file file could not be read. */
	public static String readFileToString(File file) {
		try { return org.apache.commons.io.FileUtils.readFileToString(file,"UTF-8"); }
		catch (IOException exception) { exception.printStackTrace(); return null; }
	}
	
}
