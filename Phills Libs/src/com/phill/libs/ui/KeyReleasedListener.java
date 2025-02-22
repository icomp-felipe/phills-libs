package com.phill.libs.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/** This interface is only intended to use with {@link KeyListener} lambdas, as
 *  this one does not allow the programmer to do it.
 *  NOTE: when using {@link KeyReleasedListener}, only the method 'keyReleased' event is captured.
  * @author Felipe André - felipeandre.eng@gmail.com
  * @version 1.5, 17/SEP/2020 */
@FunctionalInterface
public interface KeyReleasedListener extends KeyListener {

	@Override
	default void keyTyped(KeyEvent event) {	}
	
	@Override
	default void keyPressed(KeyEvent event) { }
	
	@Override
	public void keyReleased(KeyEvent event);
	
}
