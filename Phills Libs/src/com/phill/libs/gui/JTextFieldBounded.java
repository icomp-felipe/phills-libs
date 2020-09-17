package com.phill.libs.gui;

import javax.swing.*;
import javax.swing.text.*;

/** Implements a bounded-character {@link JTextField}.
  * @author Felipe André - felipeandresouza@hotmail.com
  * @version 1.5, 17/SEP/2020
  * @see JTextField */
public class JTextFieldBounded extends JTextField {

	// Serial
	private static final long serialVersionUID = -394412451069141604L;
	
	// Field string size limit
	private int limit;
	
	/** Creates the {@link JTextField} and bounds it to accept only 'limit' characters.
	 *  @param limit - max String size */
	public JTextFieldBounded(final int limit) {
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
			
		}
		
	}
	
}