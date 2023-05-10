package com.phill.libs;

import java.io.*;
import java.util.*;
import java.nio.charset.*;

/** Contains a static implementation of a {@link Properties} manager for Java.
 *  Also, the UTF-8 character encoding is used by default in all methods.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 2.6, 20/APR/2023 */
public class PropertiesManager {

	private static final String DELIMITER  = ";";
	private static final String CUSTOM_RES = "config/program.properties";
	
	/****************************** Getter Methods Section ***************************************/
	
	/** Searches for the property with the specified key in the property file read. The method
	 *  returns null if the property is not found or if the property file could not be read.<br>
	 *  Note: here the method will search for 'res/config/program.properties' if 'null' is passed as resource parameter.
	 *  @param key - the property key
	 *  @param resource - the resource file using {@link ResourceManager} format
	 *  @return The value in the property read with the specified key value.
	 *  @throws IOException when the properties file could not be loaded for some reason. */
	public static String getString(final String key, final String resource) throws IOException {
		
		Properties properties = getProperties(resource);
		String property = properties.getProperty(key);
			
		return property;
		
	}
	
	/** Searches for the property array with the specified key in the property file read. The method
	 *  returns null if the property is not found or if the property file could not be read.<br>
	 *  Note 1: here the method will search for 'res/config/program.properties' if 'null' is passed as resource parameter;<br>
	 *  Note 2: by default, the ; character is used to split the keys in the text array.
	 *  @param key - the property key
	 *  @param resource - the resource file using {@link ResourceManager} format
	 *  @return The value in the property read with the specified key value as a String array.
	 *  @throws IOException when the properties file could not be loaded for some reason. */
	public static String[] getStringArray(final String key, final String resource) throws IOException {
		return getStringArray(key, DELIMITER, resource);
	}
	
	/** Searches for the property with the specified key in the property file read. The method
	 *  returns null if the property is not found or if the property file could not be read.<br>
	 *  Note: here the method will search for 'res/config/program.properties' if 'null' is passed as resource parameter.
	 *  @param key - the property key
	 *  @param delimiter - the delimiting regular expression used to split the keys in the text array
	 *  @param resource - the resource file using {@link ResourceManager} format
	 *  @return The value in the property read with the specified key value as a String array.
	 *  @throws IOException when the properties file could not be loaded for some reason. */
	public static String[] getStringArray(final String key, final String delimiter, final String resource) throws IOException {
		
		Properties properties = getProperties(resource);
		String property = properties.getProperty(key);
			
		return property.split(delimiter);
		
	}
	
	/** Searches for the property with the specified key in the property file read.<br>
	 *  Note: here the method will search for 'res/config/program.properties' if 'null' is passed as resource parameter.
	 *  @param key - the property key
	 *  @param resource - the resource file using {@link ResourceManager} format
	 *  @return The value in the property read with the specified key value as an integer.
	 *  @throws IOException when the properties file could not be loaded for some reason. */
	public static int getInt(final String key, final String resource) throws IOException {
		return Integer.parseInt(getString(key, resource));
	}
	
	/** Searches for the property int array with the specified key in the property file read. The method
	 *  returns null if the property is not found or if the property file could not be read.<br>
	 *  Note 1: here the method will search for 'res/config/program.properties' if 'null' is passed as resource parameter;<br>
	 *  Note 2: by default, the ; character is used to split the keys in the text array.
	 *  @param key - the property key
	 *  @param resource - the resource file using {@link ResourceManager} format
	 *  @return The value in the property read with the specified key value as a integer array.
	 *  @throws IOException when the properties file could not be loaded for some reason. */
	public static int[] getIntArray(final String key, final String resource) throws IOException {
		return getIntArray(key, DELIMITER, resource);
	}
	
	/** Searches for the property int array with the specified key in the property file read. The method
	 *  returns null if the property is not found or if the property file could not be read.<br>
	 *  Note: here the method will search for 'res/config/program.properties' if 'null' is passed as resource parameter.
	 *  @param key - the property key
	 *  @param delimiter - the delimiting regular expression used to split the keys in the text array
	 *  @param resource - the resource file using {@link ResourceManager} format
	 *  @return The value in the property read with the specified key value as a integer array.
	 *  @throws IOException when the properties file could not be loaded for some reason. */
	public static int[] getIntArray(final String key, final String delimiter, final String resource) throws IOException {
		String[] numbers = getStringArray(key,delimiter,resource);
		return Arrays.stream(numbers).mapToInt(Integer::parseInt).toArray();
	}
	
	/****************************** Setter Methods Section ***************************************/	
	
	/** Stores the given <code>key</code> and <code>value</code> pair in the given <code>resource</code> file.<br>
	 *  Note: if 'null' is passed as resource parameter, this method will search for 'res/config/program.properties' by default.
	 *  @param key - the key to be placed into this property list
	 *  @param value - the value corresponding to key
	 *  @param resource - the resource file using {@link ResourceManager} format
	 *  @throws IOException when the properties file could not be written for some reason. */
	public static void setString(final String key, final String value, final String resource) throws IOException {
		
		// Retrieving properties object and setting new property
		final Properties props = getProperties(resource); props.setProperty(key, value);
		
		// Getting properties file and creating its subdirectories
		final File propsFile = new File((resource == null) ? ResourceManager.getResource(CUSTOM_RES) : ResourceManager.getResource(resource)); propsFile.getParentFile().mkdirs();
		
		// Writing data
		final FileOutputStream   stream = new FileOutputStream(propsFile);
		final OutputStreamWriter writer = new OutputStreamWriter(stream, StandardCharsets.UTF_8);
			
		props.store(writer, null);
			
		stream.close();
			
	}
	
	/** Stores the given <code>key</code> and <code>array</code> pair in the given <code>resource</code> file.<br>
	 *  Note 1: the array is converted to a comma separated values format, using ; as default delimiter;<br>
	 *  Note 2: if 'null' is passed as resource parameter, this method will search for 'res/config/program.properties' by default.
	 *  @param key - the key to be placed into this property list
	 *  @param value - the value corresponding to key
	 *  @param resource - the resource file using {@link ResourceManager} format
	 *  @return 'true' if the key and value could be stored, 'false' otherwise.
	 *  @throws IOException when the properties file could not be written for some reason. */
	public static void setStringArray(final String key, final String[] array, final String propsPath) throws IOException {
		setStringArray(key, array, DELIMITER, propsPath);
	}
	
	/** Stores the given <code>key</code> and <code>array</code> pair in the given <code>resource</code> file.<br>
	 *  Note: if 'null' is passed as resource parameter, this method will search for 'res/config/program.properties' by default.
	 *  @param key - the key to be placed into this property list
	 *  @param value - the value corresponding to key
	 *  @param delimiter - a delimiter to separate array values in a string value 
	 *  @param resource - the resource file using {@link ResourceManager} format
	 *  @throws IOException when the properties file could not be written for some reason. */
	public static void setStringArray(final String key, final String[] array, final String delimiter, final String resource) throws IOException {
		
		final String value = serialize(array, delimiter);
		final Properties props = getProperties(resource);
		
		// Getting properties file and creating its subdirectories
		final File propsFile = new File((resource == null) ? ResourceManager.getResource(CUSTOM_RES) : ResourceManager.getResource(resource)); propsFile.getParentFile().mkdirs();
		
		props.setProperty(key, value);
			
		final FileOutputStream   stream = new FileOutputStream(propsFile);
		final OutputStreamWriter writer = new OutputStreamWriter(stream, StandardCharsets.UTF_8);
			
		props.store(writer, null);
			
		stream.close();
			
	}
	
	/** Stores the given <code>key</code> and <code>value</code> pair in the given <code>resource</code> file.<br>
	 *  Note: if 'null' is passed as resource parameter, this method will search for 'res/config/program.properties' by default.
	 *  @param key - the key to be placed into this property list
	 *  @param value - the value corresponding to key
	 *  @param resource - the resource file using {@link ResourceManager} format
	 *  @throws IOException when the properties file could not be written for some reason. */
	public static void setInt(final String key, final int value, final String propsPath) throws IOException {
		setString(key, Integer.toString(value), propsPath);
	}
	
	/** Stores the given <code>key</code> and <code>array</code> pair in the given <code>resource</code> file.<br>
	 *  Note 1: the array is converted to a comma separated values format, using ; as default delimiter;<br>
	 *  Note 2: if 'null' is passed as resource parameter, this method will search for 'res/config/program.properties' by default.
	 *  @param key - the key to be placed into this property list
	 *  @param value - the value corresponding to key
	 *  @param resource - the resource file using {@link ResourceManager} format
	 *  @throws IOException when the properties file could not be written for some reason. */
	public static void setIntArray(final String key, final int[] array, final String propsPath) throws IOException {
		setIntArray(key, array, DELIMITER, propsPath);
	}
	
	/** Stores the given <code>key</code> and <code>array</code> pair in the given <code>resource</code> file.<br>
	 *  Note: if 'null' is passed as resource parameter, this method will search for 'res/config/program.properties' by default.
	 *  @param key - the key to be placed into this property list
	 *  @param value - the value corresponding to key
	 *  @param delimiter - a delimiter to separate array values in a string value 
	 *  @param resource - the resource file using {@link ResourceManager} format
	 *  @throws IOException when the properties file could not be written for some reason. */
	public static void setIntArray(final String key, final int[] array, final String delimiter, final String propsPath) throws IOException {
		setStringArray(key, Arrays.stream(array).mapToObj(String::valueOf).toArray(String[]::new), delimiter, propsPath);
	}
	
	/**************************** Internal Methods Section ***************************************/
	
	/** Reads a property file from the given <code>resource</code> using UTF-8 character encoding.
	 *  @param resource - property resource path
	 *  @return A properties class with all data loaded or an empty object if the resource file does not exist. */
	private static Properties getProperties(final String resource) throws IOException {

		final String propsPath = (resource == null) ? ResourceManager.getResource(CUSTOM_RES) : ResourceManager.getResource(resource);
		
		final Properties props = new Properties();
		
		final File propsFile = new File(propsPath);
		
		if (!propsFile.isDirectory() && propsFile.canRead()) {
			
			final FileInputStream   stream = new FileInputStream  (propsPath);
			final InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
			
			props.load(reader);
			
			stream.close();
			
		}
		
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
