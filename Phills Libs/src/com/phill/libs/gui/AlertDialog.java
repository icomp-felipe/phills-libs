package com.phill.libs.gui;

import javax.swing.*;

/** This class implements some cool shortcuts to manipulate dialogs
 *  using {@link JOptionPane}. 
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 3.5, 23/JUN/2020 */
public class AlertDialog extends JOptionPane {
	
	private static final long serialVersionUID = 7599713151740658702L;

	/** Shows an informative message (JOptionPane.INFORMATION_MESSAGE) using the default Frame.
	 *  @param title - Custom dialog title
	 *  @param message - Message to be displayed
	 *  @see JOptionPane */
	public static void info(String title, String message) {
		JOptionPane.showMessageDialog(null,message,title,JOptionPane.INFORMATION_MESSAGE);
	}
	
	/** Shows an informative message (JOptionPane.INFORMATION_MESSAGE) with the default
	 *  title "Info". It also uses the default frame available.
	 *  @param message - Message to be displayed
	 *  @see JOptionPane */
	public static void info(String message) {
		info("Info", message);
	}
	
	/** Shows an error message (JOptionPane.ERROR_MESSAGE) using the default Frame.
	 *  @param title - Custom dialog title
	 *  @param message - Message to be displayed
	 *  @see JOptionPane */
	public static void error(String title, String message) {
		JOptionPane.showMessageDialog(null,message,title,JOptionPane.ERROR_MESSAGE);
	}
	
	/** Shows an error message (JOptionPane.ERROR_MESSAGE) with the default
	 *  title "Error". It also uses the default frame available.
	 *  @param message - Message to be displayed
	 *  @see JOptionPane */
	public static void error(String message) {
		error("Error", message);
	}
	
	/** Displays an input dialog with a custom message using {@link JOptionPane}.
	 *  @param message - custom dialog message
	 *  @see JOptionPane */
	public static int dialog(String message) {
		return JOptionPane.showConfirmDialog(null,message);
	}
	
	/** Displays a text input dialog with a custom message using {@link JOptionPane}.
	 *  @param title - Custom dialog title
	 *  @param message - Message to be displayed
	 *  @see JOptionPane */
	public static String input(final String title, final String message) {
		return JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE);
	}
	
	/** Displays a text input dialog with a custom message using {@link JOptionPane}.
	 *  @param message - Message to be displayed
	 *  @see JOptionPane */
	public static String input(final String message) {
		return JOptionPane.showInputDialog(null, message, "Input", JOptionPane.QUESTION_MESSAGE);
	}
	
}
