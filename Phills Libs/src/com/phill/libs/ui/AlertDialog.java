package com.phill.libs.ui;

import javax.swing.*;

/** This class implements some cool shortcuts to manipulate dialogs
 *  using {@link JOptionPane}. 
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 4.0, 19/SEP/2020 */
public class AlertDialog extends JOptionPane {
	
	private static final long serialVersionUID = 7599713151740658702L;

	/** Shows an informative message (JOptionPane.INFORMATION_MESSAGE) using the default Frame.
	 *  @param title - Custom dialog title
	 *  @param message - Message to be displayed
	 *  @see JOptionPane */
	public static void info(final String title, final String message) {
		JOptionPane.showMessageDialog(null,message,title,JOptionPane.INFORMATION_MESSAGE);
	}
	
	/** Shows an informative message (JOptionPane.INFORMATION_MESSAGE) with the default
	 *  title "Info". It also uses the default frame available.
	 *  @param message - Message to be displayed
	 *  @see JOptionPane */
	public static void info(final String message) {
		info("Info", message);
	}
	
	/** Shows an error message (JOptionPane.ERROR_MESSAGE) using the default Frame.
	 *  @param title - Custom dialog title
	 *  @param message - Message to be displayed
	 *  @see JOptionPane */
	public static void error(final String title, final String message) {
		JOptionPane.showMessageDialog(null,message,title,JOptionPane.ERROR_MESSAGE);
	}
	
	/** Shows an error message (JOptionPane.ERROR_MESSAGE) with the default
	 *  title "Error". It also uses the default frame available.
	 *  @param message - Message to be displayed
	 *  @see JOptionPane */
	public static void error(final String message) {
		error("Error", message);
	}
	
	/** Displays an input dialog with a custom message using {@link JOptionPane}.
	 *  @param message - custom dialog message
	 *  @see JOptionPane */
	public static int dialog(final String message) {
		return JOptionPane.showConfirmDialog(null,message);
	}
	
	/** Displays an input dialog with a custom title and message using {@link JOptionPane}.
	 *  @param title - custom title
	 *  @param message - custom dialog message
	 *  @see JOptionPane */
	public static int dialog(final String title, final String message) {
		return JOptionPane.showConfirmDialog(null, message, title, YES_NO_CANCEL_OPTION, QUESTION_MESSAGE, null);
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
