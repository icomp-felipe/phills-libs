package com.phill.libs.files;

import java.awt.Component;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.*;
import javax.swing.filechooser.*;

/** Implements some useful methods to manipulate files in Java.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 2.3, 27/MAR/2023 */
public class PhillFileUtils {

	// Available comparators (used in 'listFilesOrdered' method)
	public static final Comparator<File> ASCENDING  = (a1,a2) -> a1.getName().compareToIgnoreCase(a2.getName());
	public static final Comparator<File> DESCENDING = (a1,a2) -> a1.getName().compareToIgnoreCase(a2.getName());
	
	// FileChooser dialog types
	public static final boolean OPEN_DIALOG = true;
	public static final boolean SAVE_DIALOG = false;
	
	// User home directory
	public static final File HOME_DIRECTORY = new File(System.getProperty("user.home"));
	
    /** Builds a list of files from <code>sourceDir</code> that matches the desired <code>extension</code>.
     *  @param sourceDir - source directory
     *  @param extension - file extension to be filtered
     *  @return A list with files containing only the file <code>extension</code> specified. */
    public static List<File> filterByExtension(final File sourceDir, final String extension) throws IOException {
    	
    	if (!sourceDir.isDirectory())
    		throw new IllegalArgumentException("Source must be a directory!");
    	
    	final List<File> filtered;

    	try (Stream<Path> walker = Files.walk(sourceDir.toPath(), 1)) {
    		
    		filtered = walker.filter (path -> !Files.isDirectory(path))
    				         .map    (path -> path.toFile())
    				         .filter (file -> file.getName().toLowerCase().endsWith(extension.toLowerCase()))
    				         .collect(Collectors.toList());
    		
    	}
    	
    	return filtered;
    }
	
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
	
	/** Shows a directory selectiom dialog.
	 *  @param parentWindow - determines the <code>Frame</code> in which the dialog is displayed; if <code>null</code>, or if the <code>parentComponent</code> has no <code>Frame</code>, a default <code>Frame</code> is used
	 *  @param title - dialog title
	 *  @param dialogType - can be {@link PhillFileUtils#OPEN_DIALOG} or {@link PhillFileUtils#SAVE_DIALOG}
	 *  @param suggestion - directory suggestion, it is used to set initial directory value in dialog
	 *  @return The selected directory or 'null' if the dialog is cancelled. */
	public static File loadDir(final Component parentWindow, final String title, final boolean dialogType, final File suggestion) {
		
		// Creating a new chooser
		JFileChooser chooser = new JFileChooser();
		
		chooser.setDialogTitle(title);
		chooser.setCurrentDirectory(suggestion);
		chooser.setMultiSelectionEnabled(false);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		// Configures the dialog type
		int result = (dialogType == OPEN_DIALOG) ? chooser.showOpenDialog(parentWindow) : chooser.showSaveDialog(parentWindow);
		
	    return (result == JFileChooser.APPROVE_OPTION) ? chooser.getSelectedFile() : null;
	}
	
	/** Shows a file selectiom dialog.
	 *  @param parentWindow - determines the <code>Frame</code> in which the dialog is displayed; if <code>null</code>, or if the <code>parentComponent</code> has no <code>Frame</code>, a default <code>Frame</code> is used
	 *  @param title - dialog title
	 *  @param filter - file filter
	 *  @param dialogType - can be {@link PhillFileUtils#OPEN_DIALOG} or {@link PhillFileUtils#SAVE_DIALOG}
	 *  @param parent - set the current directory of the filechooser. Normally you may pass a directory as parameter, but you can also pass a file, in this case
	 *  its parent directory is used. If 'null' or if the 'parent' does not exist, then the user home directory is set by default
	 *  @param suggestion - sets the selected file. If the file's parent directory is not the current directory, changes the current directory to be the file's parent directory
	 *  @return The selected file or 'null' if the dialog is cancelled.
	 *  @see FileNameExtensionFilter */
	public static File loadFile(final Component parentWindow, final String title, final FileNameExtensionFilter filter, final boolean dialogType, final File parent, final File suggestion) {
		return loadFile(parentWindow, title, new FileNameExtensionFilter[] {filter}, dialogType, parent, suggestion);
	}
	
	/** Shows a file selectiom dialog.
	 *  @param parentWindow - determines the <code>Frame</code> in which the dialog is displayed; if <code>null</code>, or if the <code>parentComponent</code> has no <code>Frame</code>, a default <code>Frame</code> is used
	 *  @param title - dialog title
	 *  @param filters - filters array
	 *  @param dialogType - can be {@link PhillFileUtils#OPEN_DIALOG} or {@link PhillFileUtils#SAVE_DIALOG}
	 *  @param parent - set the current directory of the filechooser. Normally you may pass a directory as parameter, but you can also pass a file, in this case
	 *  its parent directory is used. If 'null' or if the 'parent' does not exist, then the user home directory is set by default
	 *  @param suggestion - sets the selected file. If the file's parent directory is not the current directory, changes the current directory to be the file's parent directory
	 *  @return The selected file or 'null' if the dialog is cancelled.
	 *  @see FileNameExtensionFilter */
	public static File loadFile(final Component parentWindow, final String title, final FileNameExtensionFilter[] filters, final boolean dialogType, final File parent, final File suggestion) {
		
		File file = null;
		
		// Creating a new chooser
		JFileChooser chooser = new JFileChooser();
		
		chooser.setDialogTitle     (title);
		chooser.setCurrentDirectory(parent);
		chooser.setSelectedFile    (suggestion);
		chooser.setMultiSelectionEnabled  (false);
		chooser.setAcceptAllFileFilterUsed(false);
		
		// Assigning filters
		for (FileNameExtensionFilter filter: filters)
			chooser.addChoosableFileFilter(filter);
		
		// Configures the dialog type
		int result = (dialogType == OPEN_DIALOG) ? chooser.showOpenDialog(parentWindow) : chooser.showSaveDialog(parentWindow);
		
		// If a file was selected...
		if (result == JFileChooser.APPROVE_OPTION) {
			
			// ...then some extension treatments are done here to avoid file name mismatches
			String filename  = chooser.getSelectedFile().getAbsolutePath();
		    String extension = "";
		    
		    for (FileNameExtensionFilter filter: filters) {
		    	if ((filter != null) && (chooser.getFileFilter().equals(filter))) {
		    		extension = filter.getExtensions()[0];
		    		break;
		    	}
		    }
		    
		    if (!filename.endsWith(extension))
		    	filename += ("." + extension);
		    
		    // Finally, the actual file object is created
		    file = new File(filename);
			
		}
		
		return file;
	}
	
	/** Reads a file to a string. This method uses UTF-8 encoding do read bytes from file.
	 *  @param file - text file to be read
	 *  @return A String with all text coming from the given file.
	 *  @throws IOException if the file file could not be read. */
	public static String readFileToString(File file) {
		try { return org.apache.commons.io.FileUtils.readFileToString(file, StandardCharsets.UTF_8); }
		catch (IOException exception) { exception.printStackTrace(); return null; }
	}
	
}
