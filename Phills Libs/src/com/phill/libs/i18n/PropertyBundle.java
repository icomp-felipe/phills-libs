package com.phill.libs.i18n;

import java.io.*;
import java.util.*;
import java.nio.charset.*;

import com.phill.libs.ResourceManager;

/** Contains the implementation of a custom property resource bundle for making internationalizing your Java Project easier.
 *  It behaves similarly to the default Java {@link ResourceBundle} implementation, but there are some things you may know:<br>
 *  1. The inheritance behaves the same as {@link ResourceBundle}s;<br>
 *  2. Text resource files (refered here as i18n files) are loaded using UTF-8 character encoding;<br>
 *  3. Here, the i18n files can be stored in any directory of your system, but don't forget to say this to the constructor;<br>
 *  4. You can choose the i18n files extensions;<br>
 *  5. You may also want to know the {@link PropertyBundleFiller} class if you want to integrate this class with Java Swing components.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 1.0, 23/SEP/2020 */
public class PropertyBundle {
	
	private final String baseFile;
	private String extension;
	private Locale currentLocale;
	private Properties bundle;
	private PropertyBundleFiller filler;
	
	/******************************** Constructors Section ***************************************/
	
	/** Constructor setting internal attributes and loading i18n files. Here the default extension is set to "lng", and the JVM default locale is loaded.
	 *  @param resource - base i18n resource path using {@link ResourceManager} format
	 *  @see ResourceManager#getResource(String) */
	public PropertyBundle(final String resource) {
		this(resource, "lng", null);
	} 
	
	/** Constructor setting internal attributes and loading i18n files. Here the default extension is set to "lng".
	 *  @param resource - base i18n resource path using {@link ResourceManager} format
	 *  @param locale - locale to be used as base to load i18n files. If 'null' is passed, the JVM default locale is loaded.
	 *  @see ResourceManager#getResource(String) */
	public PropertyBundle(final String resource, final Locale locale) {
		this(resource, "lng", locale);
	}
	
	/** Constructor setting internal attributes and loading i18n files.
	 *  @param resource - base i18n resource path using {@link ResourceManager} format
	 *  @param extension - i18n files extension (without .)
	 *  @param locale - locale to be used as base to load i18n files. If 'null' is passed, the JVM default locale is loaded.
	 *  @see ResourceManager#getResource(String) */
	public PropertyBundle(final String resource, final String extension, final Locale locale) {
		
		this.extension = extension;
		this.baseFile  = ResourceManager.getResource(resource);
		this.currentLocale = (locale != null) ? locale : Locale.getDefault();

		load();
		
	}
	
	/** Constructor setting internal attributes and loading i18n files. Here the default extension is set to "lng", and the JVM default locale is loaded.
	 *  @param baseFile - base i18n file path */
	public PropertyBundle(final File baseFile) {
		this(baseFile, "lng", null);
	}
	
	/** Constructor setting internal attributes and loading i18n files. Here the default extension is set to "lng".
	 *  @param baseFile - base i18n file path
	 *  @param locale - locale to be used as base to load i18n files. If 'null' is passed, the JVM default locale is loaded. */
	public PropertyBundle(final File baseFile, final Locale locale) {
		this(baseFile, "lng", locale);
	}
	
	/** Constructor setting internal attributes and loading i18n files.
	 *  @param baseFile - base i18n file path
	 *  @param extension - i18n files extension (without .)
	 *  @param locale - locale to be used as base to load i18n files. If 'null' is passed, the JVM default locale is loaded. */
	public PropertyBundle(final File baseFile, final String extension, final Locale locale) {
		
		this.extension = extension;
		this.baseFile  = baseFile.getAbsolutePath();
		this.currentLocale = (locale != null) ? locale : Locale.getDefault();

		load();
		
	}
	
	/******************************* User Interface Methods **************************************/
	
	/** Changes the current <code>locale</code> and updates the internal {@link PropertyBundleFiller} components. */
	public void changeLocale(final Locale locale) {
		this.currentLocale = locale;	reload();
	}
	
	/** Internal i18n-located bundle getter.
	 *  @return A {@link Properties} class containing the i18n-located strings. */
	public Properties getBundle() {
		return this.bundle;
	}
	
	/** Current set locale getter.
	 *  @return The current set locale. */
	public Locale getCurrentLocate() {
		return this.currentLocale;
	}
	
	/** Returns the i18n files extension.
	 *  @return Astring representing the i18n files extension. */
	public String getExtension() {
		return this.extension;
	}

	/** Retrieves a string format associated with the given <code>key</code> and fills it with data coming from <code>args</code>.
	 *  Also the current locale is considered when filling the string.
	 *  @param key - the key whose associated i18n-string format is to be returned
	 *  @param args - argumets to fill the formatted string
	 *  @return A formatted string. */
	public String getFormattedString(final String key, final Object... args) {
		return String.format(this.currentLocale, this.bundle.getProperty(key), args);
	}
	
	/** Returns the i18n string to which the specified key is mapped, or 'null' if the loaded files do contain no mapping for the key.
	 *  @param key - the key whose associated i18n-string is to be returned
	 *  @return The string to which the specified key is mapped, or 'null' if the loaded files do contain no mapping for the key. */
	public String getString(final String key) {
		return this.bundle.getProperty(key);
	}
	
	/** Returns the i18n string array to which the specified key is mapped, or 'null' if the loaded files do contain no mapping for the key.
	 *  By default, the ; character is used to split the keys in the text array.
	 *  @param key - the key whose associated i18n-string array is to be returned
	 *  @return The string array to which the specified key is mapped, or 'null' if the loaded files do contain no mapping for the key. */
	public String[] getStringArray(final String key) {
		return this.getStringArray(key, ";");
	}
	
	/** Returns the i18n string array to which the specified key is mapped, or 'null' if the loaded files do contain no mapping for the key.
	 *  @param key - the key whose associated i18n-string array is to be returned
	 *  @param regex - the delimiting regular expression used to split the keys in the text array
	 *  @return The string array to which the specified key is mapped, or 'null' if the loaded files do contain no mapping for the key. */
	public String[] getStringArray(final String key, final String regex) {
		return this.bundle.getProperty(key).split(regex);
	}
	
	/** Returns the i18n int to which the specified key is mapped, or '-1' if the loaded files do contain no mapping for the key.
	 *  @param key - the key whose associated i18n-string is to be returned
	 *  @return The int to which the specified key is mapped, or '-1' if the loaded files do contain no mapping for the key. */
	public int getInt(final String key) {
		String value = this.bundle.getProperty(key);
		try { return Integer.parseInt(value); }
		catch (NumberFormatException exception) { return -1; }
	}
	
	/** Returns the i18n int array to which the specified key is mapped, or 'null' if the loaded files do contain no mapping for the key.
	 *  By default, the ; character is used to split the keys in the text array.
	 *  @param key - the key whose associated i18n-string array is to be returned
	 *  @return The int array to which the specified key is mapped, or 'null' if the loaded files do contain no mapping for the key. */
	public int[] getIntArray(final String key) {
		return this.getIntArray(key, ";");
	}
	
	/** Returns the i18n int array to which the specified key is mapped, or 'null' if the loaded files do contain no mapping for the key.
	 *  @param key - the key whose associated i18n-string array is to be returned
	 *  @param regex - the delimiting regular expression used to split the keys in the text array
	 *  @return The int array to which the specified key is mapped, or 'null' if the loaded files do contain no mapping for the key. */
	public int[] getIntArray(final String key, final String regex) {
		String[] numbers = getStringArray(key, regex);
		try { return Arrays.stream(numbers).mapToInt(Integer::parseInt).toArray(); }
		catch (Exception exception) { return null; }
	}
	
	/** List all pairs (key, value) loaded from the current i18n-located files (for debug purposes). */
	public void listKeys() {
		this.bundle.forEach((key,value) -> System.out.printf("Key: '%s' - Value: '%s'\n", key, value));
	}
	
	/** Reloads the current-located i18n files and updates the internal {@link PropertyBundleFiller} components. */
	public void reload() {
		load(); updateUI();
	}
	
	/** Updates the {@link PropertyBundleFiller} UI components. */
	public void updateUI() {
		if (this.filler != null) this.filler.update();
	}
	
	/************************* PropertyBundleFiller Interface Methods ****************************/

	/** Registers the filler.
	 *  @param filler - filler class, it will be controlled by this instance */
	protected void registerFiller(final PropertyBundleFiller filler) {
		this.filler = filler;
	}
	
	/******************************* Class Control Methods ***************************************/
	
	/** Loads all located i18n files to a new internal resource bundle, starting
	 *  from the most general file until the more specific-located one. */
	private void load() {
		
		// Creating new properties object
		this.bundle = new Properties();
		
		// Fallback
		String fallback = String.format("%s.%s", this.baseFile,
												 this.extension);
		load(fallback);
		
		// Language specific
		if ( !this.currentLocale.getLanguage().isEmpty() ) {
			
			String language = String.format("%s_%s.%s", this.baseFile,
														this.currentLocale.getLanguage(),
														this.extension);
			load(language);
			
		}
		
		// Country specific
		if ( !this.currentLocale.getCountry().isEmpty() ) {
			
			String country = String.format("%s_%s_%s.%s", this.baseFile,
														  this.currentLocale.getLanguage(),
														  this.currentLocale.getCountry (),
														  this.extension);
			load(country);
		
		}
		
		// Variant specific
		if ( !this.currentLocale.getVariant().isEmpty() ) {
			
			String variant = String.format("%s_%s_%s_%s.%s", this.baseFile,
															 this.currentLocale.getLanguage(),
															 this.currentLocale.getCountry (),
															 this.currentLocale.getVariant (),
															 this.extension);
			load(variant);
					
		}
		
	}
	
	/** Loads a property file identified by <code>path</code> to the internal resource bundle.
	 *  It does not throw exceptions because the file path is tested to read before.<br>
	 *  Note: here the i18n text files are read using UTF-8 character encoding.
	 *  @param path - i18n file path */
	private void load(final String path) {

		File property = new File(path);
		
		if (property.canRead()) {
			
			try {
				
				FileInputStream   stream = new FileInputStream(path);
				InputStreamReader reader = new InputStreamReader(stream, Charset.forName("UTF-8"));
				
				this.bundle.load(reader);
				
				stream.close();
				
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}
