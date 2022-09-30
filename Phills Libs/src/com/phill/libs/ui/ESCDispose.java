package com.phill.libs.ui;

import javax.swing.*;
import java.awt.event.*;

/** Implements a method to dispose a JFrame when the hotkey 'ESC' is pressed.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 1.0, 30/SEP/2022 */
public class ESCDispose {

	/** Disposes a JFrame when the hotkey 'ESC' is pressed.
	 *  @param frame - Window frame for the hotkey to be put */
	public static void register(final JFrame frame) {
		
		JRootPane rootPane = frame.getRootPane();
		
		InputMap  imap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap amap = rootPane.getActionMap();

		imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escdispose");
		amap.put("escdispose", new AbstractAction() {
			
		    private static final long serialVersionUID = 6392521022816235175L;

			@Override
		    public void actionPerformed(ActionEvent event) {
		        frame.dispose();
		    }
			
		});
		
	}
	
}
