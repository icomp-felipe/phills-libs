package com.phill.libs.ui;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/** Implements only the 'focusGained' method from the {@link FocusListener} class,
 *  intended to be used with lambda functions.
 * @author Felipe André - felipeandresouza@hotmail.com
 * @version 1.0, 06/OCT/2021 */
@FunctionalInterface
public interface FocusGainedListener extends FocusListener {
	
	@Override
	public default void focusLost(FocusEvent event) { }
	
	@Override
	public void focusGained(FocusEvent event);

}
