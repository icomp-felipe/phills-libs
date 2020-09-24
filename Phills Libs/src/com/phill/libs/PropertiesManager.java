package com.phill.libs;

import java.io.*;
import java.util.*;
import java.nio.charset.*;

/** Contains a static implementation of a {@link Properties} manager for Java.
 *  Also, the UTF-8 character encoding is used by default in all methods.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 2.5, 24/SEP/2020 */
public class PropertiesManager {

	private static final String DELIMITER  = ";";
	private static final String CUSTOM_RES = "config/program.properties";
	
	/****************************** Getter Methods Section ***************************************/
	
	/** Searches for the property with the specified key in the property file read. The method
	 *  returns null if the property is not found or if the property file could not be read.<br>
	 *  Note: here the method will search for 'res/config/program.properties' if 'null' is passed as resource parameter.
	 *  @param key - the property key
	 *  @param resource - the resource file using {@link ResourceManager} format
	 *  @return The value in the property read with the specified key value. */
	public static String getString(final String key, final String resource) {
		
		try {
			
			Properties properties = getProperties(resource);
			String property = properties.getProperty(key);
			
			return property;
		}
		catch (IOException exception) {
			return null;
		}
		
	}
	
	/** Searches for the property array with the specified key in the property file read. The method
	 *  returns null if the property is not found or if the property file could not be read.<br>
	 *  Note 1: here the method will search for 'res/config/program.properties' if 'null' is passed as resource parameter;<br>
	 *  Note 2: by default, the ; character is used to split the keys in the text array.
	 *  @param key - the property key
	 *  @param resource - the resource file using {@link ResourceManager} format
	 *  @return The value in the property read with the specified key value as a String array. */
	public static String[] getStringArray(final String key, final String resource) {
		return getStringArray(key, DELIMITER, resource);
	}
	
	/** Searches for the property with the specified key in the property file read. The method
	 *  returns null if the property is not found or if the property file could not be read.<br>
	 *  Note: here the method will search for 'res/config/program.properties' if 'null' is passed as resource parameter.
	 *  @param key - the property key
	 *  @param delimiter - the delimiting regular expression used to split the keys in the text array
	 *  @param resource - the resource file using {@link ResourceManager} format
	 *  @return The value in the property read with the specified key value as a String array. */
	public static String[] getStringArray(final String key, final String delimiter, final String resource) {
		
		try {
			
			Properties properties = getProperties(resource);
			String property = properties.getProperty(key);
			
			return property.split(delimiter);
			
		}
		catch (IOException exception) {
			return null;
		}
		
	}
	
	/** Searches for the property with the specified key in the property file read. The method
	 *  returns '-1' if the property is not found or if the property file could not be read.<br>
	 *  Note: here the method will search for 'res/config/program.properties' if 'null' is passed as resource parameter.
	 *  @param key - the property key
	 *  @param resource - the resource file using {@link ResourceManager} format
	 *  @return The value in the property read with the specified key value as an integer. */
	public static int getInt(final String key, final String resource) {
		try { return Integer.parseInt(getString(key, resource)); }
		catch (NumberFormatException exception) { return -1; }
	}
	
	/** Searches for the property int array with the specified key in the property file read. The method
	 *  returns null if the property is not found or if the property file could not be read.<br>
	 *  Note 1: here the method will search for 'res/config/program.properties' if 'null' is passed as resource parameter;<br>
	 *  Note 2: by default, the ; character is used to split the keys in the text array.
	 *  @param key - the property key
	 *  @param resource - the resource file using {@link ResourceManager} format
	 *  @return The value in the property read with the specified key value as a integer array. */
	public static int[] getIntArray(final String key, final String resource) {
		return getIntArray(key, DELIMITER, resource);
	}
	
	/** Searches for the property int array with the specified key in the property file read. The method
	 *  returns null if the property is not found or if the property file could not be read.<br>
	 *  Note: here the method will search for 'res/config/program.properties' if 'null' is passed as resource parameter.
	 *  @param key - the property key
	 *  @param delimiter - the delimiting regular expression used to split the keys in the text array
	 *  @param resource - the resource file using {@link ResourceManager} format
	 *  @return The value in the property read with the specified key value as a integer array. */
	public static int[] getIntArray(final String key, final String delimiter, final String resource) {
		String[] numbers = getStringArray(key,delimiter,resource);
		try { return Arrays.stream(numbers).mapToInt(Integer::parseInt).toArray(); }
		catch (Exception exception) { return null; }
	}
	
	/****************************** Setter Methods Section ***************************************/	
	
	/** Stores the given <code>key</code> and <code>value</code> pair in the given <code>resource</code> file.<br>
	 *  Note: if 'null' is passed as resource parameter, this method will search for 'res/config/program.properties' by default.
	 *  @param key - the key to be placed into this property list
	 *  @param value - the value corresponding to key
	 *  @param resource - the resource file using {@link ResourceManager} format
	 *  @return 'true' if the key and value could be stored, 'false' otherwise.  */
	public static boolean setString(final String key, final String value, final String resource) {
		
		try {
			
			final Properties props = getProperties(resource);
			
			props.setProperty(key, value);
			
			final FileOutputStream   stream = new FileOutputStream(resource);
			final OutputStreamWriter writer = new OutputStreamWriter(stream, Charset.forName("UTF-8"));
			
			props.store(writer, null);
			
			stream.close();
			
			return true;
		}
		catch (IOException exception) {
			return false;
		}
		
	}
	
	/** Stores the given <code>key</code> and <code>array</code> pair in the given <code>resource</code> file.<br>
	 *  Note 1: the array is converted to a comma separated values format, using ; as default delimiter;<br>
	 *  Note 2: if 'null' is passed as resource parameter, this method will search for 'res/config/program.properties' by default.
	 *  @param key - the key to be placed into this property list
	 *  @param value - the value corresponding to key
	 *  @param resource - the resource file using {@link ResourceManager} format
	 *  @return 'true' if the key and value could be stored, 'false' otherwise.  */
	public static boolean setStringArray(final String key, final String[] array, final String propsPath) {
		return setStringArray(key, array, DELIMITER, propsPath);
	}
	
	/** Stores the given <code>key</code> and <code>array</code> pair in the given <code>resource</code> file.<br>
	 *  Note: if 'null' is passed as resource parameter, this method will search for 'res/config/program.properties' by default.
	 *  @param key - the key to be placed into this property list
	 *  @param value - the value corresponding to key
	 *  @param delimiter - a delimiter to separate array values in a string value 
	 *  @param resource - the resource file using {@link ResourceManager} format
	 *  @return 'true' if the key and value could be stored, 'false' otherwise.  */
	public static boolean setStringArray(final String key, final String[] array, final String delimiter, final String propsPath) {
		
		try {
			
			final String value = serialize(array, delimiter);
			final Properties props = getProperties(propsPath);
			
			props.setProperty(key, value);
			
			final FileOutputStream   stream = new FileOutputStream(propsPath);
			final OutputStreamWriter writer = new OutputStreamWriter(stream, Charset.forName("UTF-8"));
			
			props.store(writer, null);
			
			stream.close();
			
			return true;
		}
		catch (IOException exception) {
			return false;
		}
		
	}
	
	/** Stores the given <code>key</code> and <code>value</code> pair in the given <code>resource</code> file.<br>
	 *  Note: if 'null' is passed as resource parameter, this method will search for 'res/config/program.properties' by default.
	 *  @param key - the key to be placed into this property list
	 *  @param value - the value corresponding to key
	 *  @param resource - the resource file using {@link ResourceManager} format
	 *  @return 'true' if the key and value could be stored, 'false' otherwise.  */
	public static boolean setInt(final String key, final int value, final String propsPath) {
		try { return setString(key, Integer.toString(value), propsPath); }
		catch (NumberFormatException exception) { return false; }
	}
	
	/** Stores the given <code>key</code> and <code>array</code> pair in the given <code>resource</code> file.<br>
	 *  Note 1: the array is converted to a comma separated values format, using ; as default delimiter;<br>
	 *  Note 2: if 'null' is passed as resource parameter, this method will search for 'res/config/program.properties' by default.
	 *  @param key - the key to be placed into this property list
	 *  @param value - the value corresponding to key
	 *  @param resource - the resource file using {@link ResourceManager} format
	 *  @return 'true' if the key and value could be stored, 'false' otherwise.  */
	public static boolean setIntArray(final String key, final int[] array, final String propsPath) {
		return setIntArray(key, array, DELIMITER, propsPath);
	}
	
	/** Stores the given <code>key</code> and <code>array</code> pair in the given <code>resource</code> file.<br>
	 *  Note: if 'null' is passed as resource parameter, this method will search for 'res/config/program.properties' by default.
	 *  @param key - the key to be placed into this property list
	 *  @param value - the value corresponding to key
	 *  @param delimiter - a delimiter to separate array values in a string value 
	 *  @param resource - the resource file using {@link ResourceManager} format
	 *  @return 'true' if the key and value could be stored, 'false' otherwise.  */
	public static boolean setIntArray(final String key, final int[] array, final String delimiter, final String propsPath) {
		try { return setStringArray(key, Arrays.stream(array).mapToObj(String::valueOf).toArray(String[]::new), delimiter, propsPath); }
		catch (Exception exception) { return false; }
	}
	
	/**************************** Internal Methods Section ***************************************/
	
	/** Reads a property file from the given <code>resource</code> using UTF-8 character encoding.
	 *  @param resource - property resource path
	 *  @return A properties class with all data loaded. */
	private static Properties getProperties(final String resource) throws IOException {

		final String propsPath = (resource == null) ? ResourceManager.getResource(CUSTOM_RES) : ResourceManager.getResource(resource);
		
		final Properties props = new Properties();
		
		final FileInputStream   stream = new FileInputStream  (propsPath);
		final InputStreamReader reader = new InputStreamReader(stream, Charset.forName("UTF-8"));
		
		props.load(reader);
		
		stream.close();
		
		return props;
		
	}
	
	/** Converts and String array to a 'delimiter'-delimited string.
	 *  @param array - the String array to be serialized
	 *  @param delimiter- the field delimiter
	 *  @return A 'delimiter'-delimited string. */
	private static String serialize(final String[] array, final String delimiter) {
		
		StringBuilder builder = new StringBuilder();
		
		for (String value: array)
			builder.append(value + delimiter);
		
		return builder.toString().trim();
	}
	
}
