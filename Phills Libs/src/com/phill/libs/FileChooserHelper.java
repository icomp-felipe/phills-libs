package com.phill.libs;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

import com.phill.libs.ui.AlertDialog;

/** Encapsula as operações de abertura e fechamento de arquivo do Swing
 *  @author Felipe André
 *  @version 1.0, 02/08/2015 */
public class FileChooserHelper {
	
	/* Constantes úteis */
	public static final File HOME_DIRECTORY = new File(System.getProperty("user.home"));
	
	public static File loadFile(Component component, FileNameExtensionFilter tipoArquivo, String titulo, boolean salvarAbrir, File sugestao) {
		return loadFile(component, new FileNameExtensionFilter[]{tipoArquivo}, titulo, salvarAbrir, sugestao);
	}
	
	public static File loadFile(Component component, FileNameExtensionFilter[] tiposArquivo, String titulo, boolean salvarAbrir, File sugestao) {
		JFileChooser chooser = new JFileChooser();
		
		//chooser.setSelectedFile(sugestao);
		chooser.setCurrentDirectory(sugestao);
		
		chooser.setDialogTitle(titulo);
		
		for (FileNameExtensionFilter filtro: tiposArquivo)
			chooser.addChoosableFileFilter(filtro);
		
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setMultiSelectionEnabled(false);	// Impede seleções múltiplas
		
		String dialogoBotao = (salvarAbrir) ? "Salvar" : "Abrir";
		
		int resultado = chooser.showDialog(component, dialogoBotao);
	    if (resultado != JFileChooser.APPROVE_OPTION)
	        return null;
	  
	    String filename  = chooser.getSelectedFile().getAbsolutePath();
	    String extension = "";
	    
	    for (FileNameExtensionFilter filtro: tiposArquivo) {
	    	if (chooser.getFileFilter().equals(filtro)) {
	    		extension = filtro.getExtensions()[0];
	    		break;
	    	}
	    }

	    if (!filename.endsWith(extension))
	    	filename += ("." + extension);
	    
	    File arquivo = new File(filename);
	    
	    if ( (salvarAbrir) && arquivo.exists() )
	    	if (AlertDialog.dialog("O arquivo selecionado já existe!\nDeseja sobrescrevê-lo?") != AlertDialog.OK_OPTION)
	    		return null;
	    
	    return arquivo;
	}
	
	public static File loadDir(Component component, String titulo, boolean salvarAbrir, File sugestao) {
		
		JFileChooser chooser = new JFileChooser();
		
		chooser.setCurrentDirectory(sugestao);
		chooser.setDialogTitle(titulo);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setMultiSelectionEnabled(false);	// Impede seleções múltiplas
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		String dialogoBotao = (salvarAbrir) ? "Salvar" : "Abrir";
		
		int resultado = chooser.showDialog(component, dialogoBotao);
	    if (resultado != JFileChooser.APPROVE_OPTION)
	        return null;
	  
	    String filename  = chooser.getSelectedFile().getAbsolutePath();
	    
	    return new File(filename);
	}
	
	/** Exibe um diálogo de escolha de arquivos */
	public static File dialog(Component context, String extension, boolean salvar) {
		JFileChooser chooser = new JFileChooser();
		
		String dot_extension = "." + extension;
		
		chooser.setDialogTitle("Selecione o arquivo");
		chooser.setCurrentDirectory(HOME_DIRECTORY);
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("Arquivo de Dados (*" + dot_extension + ")", extension)); // Define o filtro de seleção.
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setMultiSelectionEnabled(false);	// Impede seleções múltiplas
		
		int resultado;
		
		if (salvar)
			resultado = chooser.showSaveDialog(context);
		else
			resultado = chooser.showOpenDialog(context);
		
	    if (resultado != JFileChooser.APPROVE_OPTION)
	        return null;
	  
	    String filename = chooser.getSelectedFile().getAbsolutePath();
	    
	    if (!filename.endsWith(dot_extension))
	    	filename += dot_extension;
	    
	    File novo = new File(filename);
	    
	    if (novo.exists() && salvar) {
	    	int res = JOptionPane.showConfirmDialog(context,"O arquivo \"" + novo.getName() + "\" já existe!\nDeseja sobrescrevê-lo?");
	    	
	    	return (res == JOptionPane.OK_OPTION) ? novo : null;
	    }
	    
	    return novo;
	}

}
