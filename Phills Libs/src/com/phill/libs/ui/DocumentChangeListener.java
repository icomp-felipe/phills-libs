package com.phill.libs.ui;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/** This interface is only intended to use with {@link DocumentListener} lambdas, as
 *  this one does not allow the programmer to do it.
 *  NOTE: when using {@link DocumentListener}, the methods 'insertUpdate' and 'removeUpdate'
 *  are redirected to the 'changedUpdate' method.
 * @author Felipe André - felipeandresouza@hotmail.com
 * @version 1.5, 17/SEP/2020 */
@FunctionalInterface
public interface DocumentChangeListener extends DocumentListener {

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
