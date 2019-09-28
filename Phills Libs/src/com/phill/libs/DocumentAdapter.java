package com.phill.libs;

import javax.swing.event.*;

@FunctionalInterface
public interface DocumentAdapter extends DocumentListener {

	@Override
	public default void insertUpdate(DocumentEvent event) {
		changedUpdate(event);
	}
	
	@Override
	public default void removeUpdate(DocumentEvent event) {
		changedUpdate(event);
	}
	
	@Override
	public void changedUpdate(DocumentEvent event);

}
