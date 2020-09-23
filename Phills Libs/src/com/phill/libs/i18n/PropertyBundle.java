package com.phill.libs.i18n;

import java.io.*;
import java.util.*;
import java.nio.charset.*;

import com.phill.libs.ResourceManager;

public class PropertyBundle {
	
	private final String extension = "lng";
	private final String baseFile;
	private Locale currentLocale;
	private Properties bundle;
	private PropertyBundleFiller filler;
	
	public PropertyBundle(final String resource, final Locale locale) {
		
		this.baseFile = ResourceManager.getResource(resource);
		this.currentLocale = (locale != null) ? locale : Locale.getDefault();

		load();
	}
	
	/******************************* User Interface Methods **************************************/
	
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
		return getStringArray(key, ";");
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
		return getIntArray(key, ";");
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
	
	/** List all pairs (key, value) loaded from the current i18n-located files (for debug purposes). */
	public void listKeys() {
		this.bundle.forEach((key,value) -> System.out.printf("Key: '%s' - Value: '%s'\n", key, value));
	}
	
	/******************************** Setters Section ***************************************/
	
	/** Changes the current <code>locale</code> and updates the internal {@link PropertyBundleFiller} components. */
	public void changeLocale(final Locale locale) {
		this.currentLocale = locale;	reload();
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
