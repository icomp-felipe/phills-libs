package com.phill.libs;

import java.io.*;
import java.util.*;

/** Implements some useful methods to deal with properties files in Java.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 1.5, 21/SEP/2020 */
public class PropertiesManager {
	
	/** Atualiza uma propriedade */
	public static boolean setProperty(String key, String value) {
		try {
			Properties properties = getProperties();
			properties.setProperty(key, value);
			FileOutputStream os = new FileOutputStream(getPropertiesFile());
			properties.store(os, null);
			return true;
		}
		catch (IOException exception) {
			exception.printStackTrace();
			return false;
		}
	}
	
	/** Atualiza uma propriedade (array) */
	public static boolean setProperty(String key, String[] array) {
		
		try {
			
			Properties properties = getProperties();
			properties.setProperty(key, serialize(array));
			FileOutputStream os = new FileOutputStream(getPropertiesFile());
			properties.store(os, null);
			
			return true;
		}
		catch (IOException exception) {
			exception.printStackTrace();
			return false;
		}
	}
	
	/** Converte um array de String para uma String delimitada por '#' */
	private static String serialize(String[] array) {
		
		if (array == null) return "";
		
		StringBuilder builder = new StringBuilder();
		
		for (String aux: array)
			builder.append(aux + ";");
		
		return builder.toString();
	}
	
	/** Converte uma string delimitada por '#' para um array de String */
	private static String[] deserialize(String raw_array) {
		return raw_array.split(";");
	}
	
	/** Retorna uma propriedade */
	public static String getProperty(String key) {
		try {
			Properties properties = getProperties();
			String property = properties.getProperty(key);
			return property;
		}
		catch (IOException exception) {
			exception.printStackTrace();
			return null;
		}
	}
	
	/** Retorna uma propriedade (array) */
	public static String[] getPropertyAsArray(String key) {
		try {
			Properties properties = getProperties();
			String property = properties.getProperty(key);
			return deserialize(property);
		}
		catch (IOException exception) {
			exception.printStackTrace();
			return null;
		}
	}
	
	public static int getPropertyAsInteger(String key) {
		return Integer.parseInt(getProperty(key));
	}
	
	/** Instancia a classe de propriedades do sistema */
	private static Properties getProperties() throws IOException {
		Properties props = new Properties();
		File arquivo = getPropertiesFile();
		FileInputStream stream = new FileInputStream(arquivo);
		props.load(stream);
		return props;
	}

	/** Busca o arquivo de propriedades do sistema */
	private static File getPropertiesFile() {
		String arquivo = ResourceManager.getResource("config/program.properties");
		return new File(arquivo);
	}
	
}
