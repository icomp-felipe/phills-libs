package com.phill.libs;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.phill.libs.sys.PhillFileUtils;

/** Faz a interface do programa com arquivos externos
 *  @author Felipe André
 *  @version 3.0, 23/03/2016 */
public class ResourceManager {	

	/** Retorna o diretório de trabalho atual */
	public static String getCurrentPath() {
		Path currentRelativePath = Paths.get("");
		return currentRelativePath.toAbsolutePath().toString();
	}
	
	/** Retorna o diretório de trabalho atual */
	public static String getResource(String resource) {
		String baseDirectory = getCurrentPath();
		return (baseDirectory + "/res/" + resource);
	}

	/** Cria um arquivo a partir de um recurso de sistema */
	public static File getResourceAsFile(String resource) {
		return new File(getResource(resource));
	}
	
	/** Carrega um arquivo de texto para uma String */
	public static String getStringFromFile(File file) throws IOException {
		return PhillFileUtils.readFileToString(file).trim();
	}
	
	/** Carrega um formato de String (String.format) para uma String */
	public static String getFormatFromResource(String resource) {
		try { return getStringFromFile(getResourceAsFile(resource)); }
		catch (IOException exception) { exception.printStackTrace(); return null; }
	}
	
	/** Carrega um formato de String SQL (String.format) para uma String */
	private static String getSQLFormat(String sqlResource) throws IOException {
		return getFormatFromResource("sql/" + sqlResource);
	}
	
	private static String getFormattedString(String resource, Object... args) {
		
		try {
			String format = getFormatFromResource(resource);
			return String.format(format, args);
		}
		catch (Exception exception) {
			return null;
		}
	}
	
	public static String getText(Object classe, String resourceName, Object... args) {

		String resourcePath = String.format("text/%s/%s",classe.getClass().getSimpleName(),resourceName);
		
		return getFormattedString(resourcePath, args);
	}
	
	/** Carrega um formato SQL e o preenche utilizando a função String.format */
	public static String getSQLString(String sqlResource, Object... args) throws IOException {
		String format = getSQLFormat(sqlResource);
		return String.format(format, args).replace("\"null\"","null").replace("'null'","null");
	}
	
	/** Carrega um formato SQL e o preenche utilizando a função String.format */
	public static String getSQLString(String format, boolean isFormat, Object... args) throws IOException {
		return String.format(format, args).replace("\"null\"","null").replace("'null'","null");
	}
	
	/** Carrega um ícone a partir de um recurso do sistema e retorna sua cópia redimensionada */
	public static Icon getResizedIcon(String resourceIcon, int width, int height) {
		
		try {
			
			File imagePath = getResourceAsFile(resourceIcon);
			BufferedImage rawImage = ImageIO.read(imagePath);
			Image resized = rawImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			
			return new ImageIcon(resized);
		}
		catch (Exception exception) {
			return null;
		}
		
	}
	
}
