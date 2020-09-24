package com.phill.libs;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.nio.file.*;
import javax.imageio.*;
import java.awt.image.*;
import org.apache.commons.io.FileUtils;

/** Contains some methods to deal with external resources (strings, formats, files, etc)
 *  in Java applications.<br>
 *  * Note 1: all methods here use the 'res' directory as base, it must be placed in your Java application project home.<br>
 *  * Note 2: when it comes to resource parameters, all methods here consider the 'res' directory as base to locate a resource file.
 *  For example, if your file is located at 'res/config/program.properties', your 'resource' string path will be 'config/program.properties'. 
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 4.0, 23/SEP/2020 */
public class ResourceManager {	

	/** Retrieves the current running project absolute path.
	 *  @return A string containing a full path to the current running project. */
	public static String getCurrentPath() {
		Path currentRelativePath = Paths.get("");
		return currentRelativePath.toAbsolutePath().toString();
	}
	
	/** Loads a TTF font from a file to a {@link Font}.
	 *  @param resourcePath - font resource path. For more info, please refer to see also section. */
	public static Font getFont(final File resourcePath) throws Exception {
		return Font.createFont(Font.TRUETYPE_FONT, resourcePath);
	}
	
	/** Derives a new {@link Font} from an existing one.
	 *  @param baseFont - font to be derived
	 *  @param style - the style for the new font (Font.BOLD, Font.ITALIC, Font.PLAIN, etc...)
	 *  @param size - the size for the new font
	 *  @return A new font with the specified style and size. */
	public static Font deriveFont(final Font baseFont, final int style, final int size) {
		return baseFont.deriveFont(style, size);
	}
	
	/** Loads a String format from the given <code>resource</code>.
	 *  @param resource - resource string path
	 *  @return A string format. For more info, please refer to 'See Also' section.
	 *  @see String#format(String, Object...) */
	public static String getFormat(final String resource) {
		
		try {
			
			final File formatFile = getResourceAsFile(resource);
			final String   format = FileUtils.readFileToString(formatFile,"UTF-8");
			
			return format;
		}
		catch (IOException exception) {
			exception.printStackTrace();
			return null;
		}
		
	}
	
	/** Retrieves a <code>resource</code> string and fills it with <code>args</code> data
	 *  using {@link String#format(String, Object...)}.
	 *  @param resource - resource string path
	 *  @param args - same arguments used in {@link String#format(String, Object...)}
	 *  @return A string with resource coming from <code>resource</code> and data coming from
	 *  <code>args</code>, or 'null' if an Exception is internally thrown. */
	private static String getFormattedString(final String resource, final Object... args) {
		
		try {
			String format = getFormat(resource);
			return String.format(format, args);
		}
		catch (Exception exception) {
			return null;
		}
		
	}

	/** Loads a resized {@link Icon} from the given <code>resource</code>.
	 *  @param resource - resource string path
	 *  @param width - custom icon width
	 *  @param height - custom icon height
	 *  @return An {@link Icon} object loaded from <code>resource</code> path and resized
	 *  using <code>width</code> and <code>height</code>, or 'null' if an internal Exception is thrown,
	 *  most of the cases because the icon couldn't be read for some reason. */
	public static Icon getIcon(final String resource, final int width, final int height) {
		
		try {
			
			File iconFile = getResourceAsFile(resource);
			BufferedImage rawImage = ImageIO.read(iconFile);
			Image resized = rawImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			
			return new ImageIcon(resized);
		}
		catch (Exception exception) {
			return null;
		}
		
	}
	
	/** Retrieves a full file path (from the 'res' directory) with the <code>resource</code> included.
	 *  @param resource - resource string path
	 *  @return A full path to the <code>resource</code>. */
	public static String getResource(final String resource) {
		String baseDirectory = getCurrentPath();
		return (baseDirectory + "/res/" + resource);
	}

	/** Retrieves a file (from the 'res' directory) with the <code>resource</code> included.
	 *  @param resource - resource string path
	 *  @return A file with the <code>resource</code> included. */
	public static File getResourceAsFile(final String resource) {
		return new File(getResource(resource));
	}
	
	/** Retrieves a text resource from the 'text/&lt;object class name&gt;/&lt;resource&gt;' and fills it with
	 *  <code>args</code> data using {@link String#format(String, Object...)}.
	 *  @param object - object class (its simple name will be used as base directory to find the resource)
	 *  @param resource - resource name
	 *  @param args - same arguments used in {@link String#format(String, Object...)} */
	public static String getText(final Object object, final String resource, final Object... args) {

		String resourcePath = String.format("text/%s/%s", object.getClass().getSimpleName(), resource);
		
		return getFormattedString(resourcePath, args);
	}
	
	/***************** These will be deprecated in a short future **************************/
	
	/** Retrieves a format using the 'sql' directory as base.
	 *  @param resource - SQL format resource */
	private static String getSQLFormat(final String resource) throws IOException {
		return getFormat("sql/" + resource);
	}
	
	/** Loads a SQL format and fills it with <code>args</code>
	 *  data using {@link String#format(String, Object...)}.
	 *  @param resource - SQL resource string path
	 *  @param args - same arguments used in {@link String#format(String, Object...)}
	 *  @return A string with resource coming from <code>resource</code> and data coming from
	 *  <code>args</code>, or 'null' if an Exception is internally thrown. */
	public static String getSQLString(final String resource, final Object... args) throws IOException {
		String format = getSQLFormat(resource);
		return StringUtils.blankToNull(String.format(format, args));
	}
	
}
