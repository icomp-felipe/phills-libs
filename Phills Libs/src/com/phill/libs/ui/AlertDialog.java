package com.phill.libs.ui;

import java.awt.*;
import javax.swing.*;

/** This class implements some cool shortcuts to manipulate dialogs
 *  using {@link JOptionPane}. 
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 4.5, 21/SEP/2020 */
public class AlertDialog extends JOptionPane {
	
	private static final long serialVersionUID = 7599713151740658702L;

	/** Shows an informative message (JOptionPane.INFORMATION_MESSAGE) using the default Frame.
	 *  @param title - custom dialog title
	 *  @param message - message to be displayed
	 *  @see JOptionPane */
	public static void info(final String title, final String message) {
		JOptionPane.showMessageDialog(null,message,title,JOptionPane.INFORMATION_MESSAGE);
	}
	
	/** Shows an informative message (JOptionPane.INFORMATION_MESSAGE) with the default
	 *  title "Info". It also uses the default frame available.
	 *  @param message - message to be displayed
	 *  @see JOptionPane */
	public static void info(final String message) {
		info("Info", message);
	}
	
	/** Shows an error message (JOptionPane.ERROR_MESSAGE) using the default Frame.
	 *  @param title - custom dialog title
	 *  @param message - message to be displayed
	 *  @see JOptionPane */
	public static void error(final String title, final String message) {
		JOptionPane.showMessageDialog(null,message,title,JOptionPane.ERROR_MESSAGE);
	}
	
	/** Shows an error message (JOptionPane.ERROR_MESSAGE) with the default
	 *  title "Error". It also uses the default frame available.
	 *  @param message - message to be displayed
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
	 *  @param title - custom dialog title
	 *  @param message - message to be displayed
	 *  @see JOptionPane */
	public static String input(final String title, final String message) {
		return JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE);
	}
	
	/** Displays a text input dialog with a custom message using {@link JOptionPane}.
	 *  @param message - message to be displayed
	 *  @see JOptionPane */
	public static String input(final String message) {
		return JOptionPane.showInputDialog(null, message, "Input", JOptionPane.QUESTION_MESSAGE);
	}
	
	/** Displays a password input dialog with a custom <code>message</code>.
	 *  @param message - message to be displayed
	 *  @return The password typed or 'null' if the dialog is canceled. */
	public static String password(final String message) {
		return password("Password input", message, (Character) UIManager.get("PasswordField.echoChar"));
	}
	
	/** Displays a password input dialog with a custom <code>title</code> and <code>message</code>.
	 *  @param title - custom dialog title
	 *  @param message - message to be displayed
	 *  @return The password typed or 'null' if the dialog is canceled. */
	public static String password(final String title, final String message) {
		return password(title, message, (Character) UIManager.get("PasswordField.echoChar"));
	}
	
	/** Displays a password input dialog with a custom <code>title</code>, <code>message</code> and <code>echoCharacter</code>.
	 *  @param title - custom dialog title
	 *  @param message - message to be displayed
	 *  @param echoCharacter - character to be placed instead of the original ones
	 *  @return The password typed or 'null' if the dialog is canceled. */
	public static String password(final String title, final String message, final char echoCharacter) {
		
		// Retrieving resources
		final Font   font = GraphicsHelper.getInstance().getFont ();
		final Color color = GraphicsHelper.getInstance().getColor(); 
		
		// Building panel
		final JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
		
		final JLabel label = new JLabel(message);
		label.setFont(font);
		panel.add(label);
		
		final JPasswordField passwordField = new JPasswordField(20);
		passwordField.setFont(font);
		passwordField.setForeground(color);
		passwordField.setEchoChar(echoCharacter);
		panel.add(passwordField);
		
		// Building dialog
		int option = JOptionPane.showOptionDialog(null, panel, title,
									 			  JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
									 			  null, null, null);
		
		return (option == JOptionPane.OK_OPTION) ? new String(passwordField.getPassword()) : null;
	}
	
}
