package com.phill.libs;

import javax.swing.text.*;

/** Classe que implementa um controlador de limite
  * de inserção de caracteres em um JTextField
  * @author Felipe André
  * @version 1.0, 12/09/2018
  * @see JTextField */
public class JTextFieldLimit extends PlainDocument {
	
	private static final long serialVersionUID = 1L;
	
	/** Limite de caracteres */
	private int limit;
	
	JTextFieldLimit(int limit) {
		super();
		this.limit = limit;
	}
	
	@Override
	/** Implementação do controlador */
	public void insertString(int offset, String string, AttributeSet attribute) throws BadLocationException {
		
		if (string == null)
			return;
		
		if ((getLength() + string.length()) <= limit)
			super.insertString(offset,string,attribute);
		
	}
	
}