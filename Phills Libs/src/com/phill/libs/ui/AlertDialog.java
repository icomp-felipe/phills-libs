package com.phill.libs.ui;

import java.awt.*;
import javax.swing.*;

/** This class implements some cool shortcuts to manipulate dialogs
 *  using {@link JOptionPane}. 
 *  @author Felipe André - felipeandre.eng@gmail.com
 *  @version 5.1, 16/MAR/2023 */
public class AlertDialog extends JOptionPane {
	
	private static final long serialVersionUID = 7599713151740658702L;

	/** Shows an informative message (JOptionPane.INFORMATION_MESSAGE) using the default Frame.
	 *  @param parentWindow - determines the <code>Frame</code> in which the dialog is displayed; if <code>null</code>, or if the <code>parentComponent</code> has no <code>Frame</code>, a default <code>Frame</code> is used
	 *  @param title - custom dialog title
	 *  @param message - message to be displayed
	 *  @see JOptionPane */
	public static void info(final Component parentWindow, final String title, final String message) {
		JOptionPane.showMessageDialog(parentWindow, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/** Shows an informative message (JOptionPane.INFORMATION_MESSAGE) with the default
	 *  title "Info". It also uses the default frame available.
	 *  @param parentWindow - determines the <code>Frame</code> in which the dialog is displayed; if <code>null</code>, or if the <code>parentComponent</code> has no <code>Frame</code>, a default <code>Frame</code> is used
	 *  @param message - message to be displayed
	 *  @see JOptionPane */
	public static void info(final Component parentWindow, final String message) {
		info(parentWindow, "Info", message);
	}
	
	/** Shows an error message (JOptionPane.ERROR_MESSAGE) using the default Frame.
	 *  @param parentWindow - determines the <code>Frame</code> in which the dialog is displayed; if <code>null</code>, or if the <code>parentComponent</code> has no <code>Frame</code>, a default <code>Frame</code> is used
	 *  @param title - custom dialog title
	 *  @param message - message to be displayed
	 *  @see JOptionPane */
	public static void error(final Component parentWindow, final String title, final String message) {
		JOptionPane.showMessageDialog(parentWindow, message, title, JOptionPane.ERROR_MESSAGE);
	}
	
	/** Shows an error message (JOptionPane.ERROR_MESSAGE) with the default
	 *  title "Error". It also uses the default frame available.
	 *  @param parentWindow - determines the <code>Frame</code> in which the dialog is displayed; if <code>null</code>, or if the <code>parentComponent</code> has no <code>Frame</code>, a default <code>Frame</code> is used
	 *  @param message - message to be displayed
	 *  @see JOptionPane */
	public static void error(final Component parentWindow, final String message) {
		error(parentWindow, "Error", message);
	}
	
	/** Shows a warning message (JOptionPane.WARNING_MESSAGE) using the default Frame.
	 *  @param parentWindow - determines the <code>Frame</code> in which the dialog is displayed; if <code>null</code>, or if the <code>parentComponent</code> has no <code>Frame</code>, a default <code>Frame</code> is used
	 *  @param title - custom dialog title
	 *  @param message - message to be displayed
	 *  @see JOptionPane */
	public static void warning(final Component parentWindow, final String title, final String message) {
		JOptionPane.showMessageDialog(parentWindow, message, title, JOptionPane.WARNING_MESSAGE);
	}
	
	/** Shows a warning message (JOptionPane.WARNING_MESSAGE) with the default
	 *  title "Warning". It also uses the default frame available.
	 *  @param parentWindow - determines the <code>Frame</code> in which the dialog is displayed; if <code>null</code>, or if the <code>parentComponent</code> has no <code>Frame</code>, a default <code>Frame</code> is used
	 *  @param message - message to be displayed
	 *  @see JOptionPane */
	public static void warning(final Component parentWindow, final String message) {
		warning(parentWindow, "Warning", message);
	}
	
	/** Displays an input dialog with a custom message using {@link JOptionPane}.
	 *  @param parentWindow - determines the <code>Frame</code> in which the dialog is displayed; if <code>null</code>, or if the <code>parentComponent</code> has no <code>Frame</code>, a default <code>Frame</code> is used
	 *  @param message - custom dialog message
	 *  @see JOptionPane */
	public static int dialog(final Component parentWindow, final String message) {
		return JOptionPane.showConfirmDialog(parentWindow, message);
	}
	
	/** Displays an input dialog with a custom title and message using {@link JOptionPane}.
	 *  @param parentWindow - determines the <code>Frame</code> in which the dialog is displayed; if <code>null</code>, or if the <code>parentComponent</code> has no <code>Frame</code>, a default <code>Frame</code> is used
	 *  @param title - custom title
	 *  @param message - custom dialog message
	 *  @see JOptionPane */
	public static int dialog(final Component parentWindow, final String title, final String message) {
		return JOptionPane.showConfirmDialog(parentWindow, message, title, YES_NO_CANCEL_OPTION, QUESTION_MESSAGE, null);
	}
	
	/** Displays a text input dialog with a custom message using {@link JOptionPane}.
	 *  @param parentWindow - determines the <code>Frame</code> in which the dialog is displayed; if <code>null</code>, or if the <code>parentComponent</code> has no <code>Frame</code>, a default <code>Frame</code> is used
	 *  @param title - custom dialog title
	 *  @param message - message to be displayed
	 *  @see JOptionPane */
	public static String input(final Component parentWindow, final String title, final String message) {
		return JOptionPane.showInputDialog(parentWindow, message, title, JOptionPane.QUESTION_MESSAGE);
	}
	
	/** Displays a text input dialog with a custom message using {@link JOptionPane}.
	 *  @param parentWindow - determines the <code>Frame</code> in which the dialog is displayed; if <code>null</code>, or if the <code>parentComponent</code> has no <code>Frame</code>, a default <code>Frame</code> is used
	 *  @param message - message to be displayed
	 *  @see JOptionPane */
	public static String input(final Component parentWindow, final String message) {
		return JOptionPane.showInputDialog(parentWindow, message, "Input", JOptionPane.QUESTION_MESSAGE);
	}
	
	/** Displays a text input dialog with a custom message using {@link JOptionPane}.
	 *  @param parentWindow - determines the <code>Frame</code> in which the dialog is displayed; if <code>null</code>, or if the <code>parentComponent</code> has no <code>Frame</code>, a default <code>Frame</code> is used
	 *  @param title - custom dialog title
	 *  @param message - message to be displayed
	 *  @param initialValue - a string to be placed at the prompt text field
	 *  @see JOptionPane */
	public static String input(final Component parentWindow, final String title, final String message, final String initialValue) {
		return (String) JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE, null, null, initialValue);
	}
	
	/** Displays a password input dialog with a custom <code>message</code>.
	 *  @param parentWindow - determines the <code>Frame</code> in which the dialog is displayed; if <code>null</code>, or if the <code>parentComponent</code> has no <code>Frame</code>, a default <code>Frame</code> is used
	 *  @param message - message to be displayed
	 *  @return The password typed or 'null' if the dialog is canceled. */
	public static String password(final Component parentWindow, final String message) {
		return password(parentWindow, "Password input", message, (Character) UIManager.get("PasswordField.echoChar"));
	}
	
	/** Displays a password input dialog with a custom <code>title</code> and <code>message</code>.
	 *  @param parentWindow - determines the <code>Frame</code> in which the dialog is displayed; if <code>null</code>, or if the <code>parentComponent</code> has no <code>Frame</code>, a default <code>Frame</code> is used
	 *  @param title - custom dialog title
	 *  @param message - message to be displayed
	 *  @return The password typed or 'null' if the dialog is canceled. */
	public static String password(final Component parentWindow, final String title, final String message) {
		return password(parentWindow, title, message, (Character) UIManager.get("PasswordField.echoChar"));
	}
	
	/** Displays a password input dialog with a custom <code>title</code>, <code>message</code> and <code>echoCharacter</code>.
	 *  @param parentWindow - determines the <code>Frame</code> in which the dialog is displayed; if <code>null</code>, or if the <code>parentComponent</code> has no <code>Frame</code>, a default <code>Frame</code> is used
	 *  @param title - custom dialog title
	 *  @param message - message to be displayed
	 *  @param echoCharacter - character to be placed instead of the original ones
	 *  @return The password typed or 'null' if the dialog is canceled. */
	public static String password(final Component parentWindow, final String title, final String message, final char echoCharacter) {
		
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
		
		JOptionPane pane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void selectInitialValue() {
				passwordField.requestFocusInWindow();
			}
			
		};
		
		// Building dialog
		pane.createDialog(parentWindow, title).setVisible(true);
		
		return passwordField.getPassword().length == 0 ? null : new String(passwordField.getPassword());
	}
	
	/** Displays the given <code>message</code> for some <code>secs</code> and then, a newline character
	 *  is printed in the System standart output.
	 *  @param message - message to be displayed
	 *  @param secs - time to print a new line character */
	public static void timedDisplay(final String message, final int secs) {
		
		// Setting the job
		Runnable job = () -> {
			
			try {
				
				System.out.println(message);
				Thread.sleep(secs * 1000L);
				System.out.println();
				
			}
			catch (InterruptedException exception) {
				return;
			}
			
		};
		
		Thread thread = new Thread(job);
		thread.setName("AlertDialog.timedDisplay thread");
		thread.start();
		
	}
	
}
