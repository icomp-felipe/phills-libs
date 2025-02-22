package com.phill.libs.ui;

import java.awt.*;
import javax.swing.*;

/** Here you can find some methods to deal with JLabels in your applications.
 *  @author Felipe André - felipeandre.eng@gmail.com
 *  @version 1.0, 23/JUN/2020 */
public class LabelUtils {

	/** Displays a 'color'ed 'message' in the given 'label' for some 'secs'.
	 *  This method uses a new {@link Thread} to control the timing operations. 
	 *  @param label - {@link JLabel} component to be filled with a message text
	 *  @param color - {@link Color} text
	 *  @param message - a message to be displayed
	 *  @param secs - amount of time (in seconds) for the message to be displayed */
	public static void timedDisplay(JLabel label, Color color, String message, int secs) {
		
		// Setting the job
		Runnable job = () -> {
			
			try {
				
				updateField(label, color, message);
				Thread.sleep(secs * 1000L);
				updateField(label, color, null);
				
			}
			catch (InterruptedException exception) {
				return;
			}
			
		};
		
		Thread thread = new Thread(job);
		thread.setName("LabelUtils.timedDisplay thread");
		thread.start();
		
	}
	
	/** Invokes a Swing thread to update the UI.
	 *  Inner method used by <code>timedDisplay()</code>. */
	private static void updateField(JLabel label, Color color, String message) {
		
		SwingUtilities.invokeLater(() -> {
			
			label.setForeground(color);
			label.setText(message);
			
			// If the message is null, the label is hidden
			label.setVisible( message != null );
			
		});
		
	}
	
}
