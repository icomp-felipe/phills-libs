package com.phill.libs.ui;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/** Implements only the 'focusLost' method from the {@link FocusListener} class,
 *  intended to be used with lambda functions.
 * @author Felipe André - felipeandresouza@hotmail.com
 * @version 1.0, 06/OCT/2021 */
@FunctionalInterface
public interface FocusLostListener extends FocusListener {
	
	@Override
	public default void focusGained(FocusEvent event) { }
	
	@Override
	public void focusLost(FocusEvent event);

}
