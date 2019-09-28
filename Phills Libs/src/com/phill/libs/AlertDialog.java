package com.phill.libs;

import java.io.*;
import java.awt.*;
import javax.swing.*;

import java.awt.datatransfer.*;

/** Classe que contém métodos úteis de manipulação de telas de diálogo
 *  @author Felipe André
 *  @version 3.0, 22/03/2016 */
public class AlertDialog extends JOptionPane {
	
	private static final long serialVersionUID = 1L;

	public static void jasperError() {
		erro("Erro de Relatório","Falha ao gerar visualização.");
	}
	
	/** Mostra uma mensagem de informação padrão */
	public static void informativo(String mensagem) {
		informativo("Informação", mensagem);
	}
	
	/** Mostra uma mensagem de informação personalizada */
	public static void informativo(String titulo, String mensagem) {
		JOptionPane.showMessageDialog(null,mensagem,titulo,JOptionPane.INFORMATION_MESSAGE);
	}
	
	/** Mostra uma mensagem de erro padrão */
	public static void erro(String mensagem) {
		erro("Tela de Erro",mensagem);
	}
	
	/** Mostra uma mensagem de erro personalizada */
	public static void erro(String titulo, String mensagem) {
		JOptionPane.showMessageDialog(null,mensagem,titulo,JOptionPane.ERROR_MESSAGE);
	}
	
	/** Mostra uma janela de diálogo */
	public static int dialog(String mensagem) {
		return JOptionPane.showConfirmDialog(null,mensagem);
	}
	
	/** Cola um texto na área de transferência */
	public static void pasteToClibpoard(String text) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, null);
    }
	
	public static void showMessageForAWhile(JLabel textField, Color color, String message, int secs) {
		new TemporaryMessage(textField,color,message,secs).start();
	}

	private static class TemporaryMessage extends Thread {
		
		private JLabel textField;
		private Color color;
		private String message;
		private long sleep;
		
		public TemporaryMessage(JLabel textField, Color color, String message, int secs) {
			this.textField = textField;
			this.color = color;
			this.message = message;
			this.sleep = secs * 1000L;
		}

		@Override
		public void run() {
			try {
				updateField(textField, color, message);
				sleep(sleep);
				updateField(textField, color, null);
			}
			catch (InterruptedException exception) {
				return;
			}
		}
		
	}
	
	private static class TextFieldUpdater implements Runnable {
		
		private JLabel textField;
		private Color color;
		private String message;
		
		public TextFieldUpdater(JLabel textField, Color color, String message) {
			this.textField = textField;
			this.message = message;
			this.color = color;
		}

		@Override
		public void run() {
			textField.setForeground(color);
			textField.setText(message);
			
			if (message == null)
				textField.setVisible(false);
			else
				textField.setVisible(true);
			
			textField.repaint();
		}
	}
	
	private static void updateField(JLabel textField, Color color, String message) {
		Runnable job = new TextFieldUpdater(textField,color,message);
		SwingUtilities.invokeLater(job);
	}
	
	/** Copia um texto da área de transferência */
	public static String copyFromClipboard() {
		String result = "";
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(null);
		boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
		if (hasTransferableText) {
		   	try { result = (String)contents.getTransferData(DataFlavor.stringFlavor); }
		   	catch (UnsupportedFlavorException | IOException ex) { ex.printStackTrace(); }
		}
	    return result;
	}

}
