package com.phill.libs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@FunctionalInterface
public interface KeyboardAdapter extends KeyListener {

	@Override
	default void keyTyped(KeyEvent event) {	}
	
	@Override
	default void keyPressed(KeyEvent event) { }
	
	@Override
	public void keyReleased(KeyEvent event);
	
}
