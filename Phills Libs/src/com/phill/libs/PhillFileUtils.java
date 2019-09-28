package com.phill.libs;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class PhillFileUtils {

	private static final Comparator<File> comparadorArquivo = (a1,a2) -> a1.getName().compareTo(a2.getName());
	
	public static File[] findFilesInDir(File directory, String extension){
	    //File dir = new File(dirName);

	    return directory.listFiles(new FilenameFilter() { 
	             @Override
				public boolean accept(File dir, String filename)
	                  { return filename.toLowerCase().endsWith(extension); }
	    } );

	}
	
	public static File getHome() {
		return new File(System.getProperty("user.home"));
	}
	
	public static String readFileToString(File file) {
		try { return org.apache.commons.io.FileUtils.readFileToString(file,"UTF-8"); }
		catch (IOException exception) { exception.printStackTrace(); return null; }
	}
	
	/** Retorna uma lista ordenada dos arquivos de um diretório informado */
	public static File[] orderedListFiles(File directory) {
		
		File[] listaArquivos = directory.listFiles();
		Arrays.sort(listaArquivos,comparadorArquivo);
		
		return listaArquivos;
		
	}
	
}
