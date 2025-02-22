package com.phill.libs.ui;

import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.text.*;

/** Implements a bounded-character {@link JTextArea}.
  * @author Felipe André - felipeandre.eng@gmail.com
  * @version 1.0, 02/SEP/2022
  * @see JTextField */
public class JTextAreaBounded extends JTextArea {

	// Serial
	private static final long serialVersionUID = -394412451069141604L;
	
	// Field string size limit
	private int limit;
	
	/** Creates the {@link JTextArea} and bounds it to accept only 'limit' characters.
	 *  @param limit - max String size */
	public JTextAreaBounded(final int limit) {
		super();
		this.limit = limit;
		setDocument(new BoundedDocument());
	}
	
	/** This class implements the character control. */
	private class BoundedDocument extends PlainDocument {
		
		// Serial
		private static final long serialVersionUID = -4911618216734849919L;

		@Override
		public void insertString(int offset, String string, AttributeSet attribute) throws BadLocationException {
			
			// If null, there's nothing to add
			if (string == null)
				return;
			
			// Here I only insert a new string if my internal limit has not been reached
			if ((getLength() + string.length()) <= limit)
				super.insertString(offset,string,attribute);
			else
				Toolkit.getDefaultToolkit().beep();
			
		}
		
	}
	
}