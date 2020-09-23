package com.phill.libs.i18n;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;

import com.phill.libs.ResourceManager;

public class PropertyBundle {
	
	public static void main(String[] args) {
		String res = "i18n/strings";
		new PropertyBundle(res,new Locale("pt", "BR", "Manaus"));
	}
	
	private final String extension = "lng";
	private final String baseFile;
	private Locale currentLocale;
	private Properties bundle;
	private PropertyBundleFiller filler;
	
	public PropertyBundle(final String resource, final Locale locale) {
		
		this.baseFile = ResourceManager.getResource(resource);
		this.currentLocale = (locale != null) ? locale : Locale.getDefault();

		load();
		print();
	}
	
	/*************************************************************************/
	/************************* Getters Section *******************************/
	
	public String getString(final String key) {
		return this.bundle.getProperty(key);
	}
	
	public String[] getStringArray(final String key) {
		return getStringArray(key, ";");
	}
	
	public String[] getStringArray(final String key, final String regex) {
		return this.bundle.getProperty(key).split(regex);
	}
	
	public int getInt(final String key) {
		String value = this.bundle.getProperty(key);
		try { return Integer.parseInt(value); }
		catch (NumberFormatException exception) { return -1; }
	}
	
	public int[] getIntArray(final String key) {
		return getIntArray(key, ";");
	}
	
	public int[] getIntArray(final String key, final String regex) {
		String[] numbers = getStringArray(key, regex);
		return Arrays.stream(numbers).mapToInt(Integer::parseInt).toArray();
	}
	
	public Properties getBundle() {
		return this.bundle;
	}
	
	public Locale getCurrentLocate() {
		return this.currentLocale;
	}
	
	/************************* Setters Section *******************************/
	
	public void changeLocale(final Locale locale) {
		
		this.currentLocale = locale;
		load();
		
	}
	
	public void reload() {
		load();
	}
	
	public void refresh() {
		this.filler.update();
	}
	
	/*************************************************************************/
	
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
			load  (language);
			
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
		
		if (this.filler != null)
			this.filler.update();
		
	}
	
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
	
	/*************************************************************************/
	
	public void print() {
		this.bundle.forEach((key,value) -> System.out.printf("Key: '%s' - Value: '%s'\n", key, value));
	}

	protected void registerFiller(PropertyBundleFiller filler) {
		this.filler = filler;
	}

}
