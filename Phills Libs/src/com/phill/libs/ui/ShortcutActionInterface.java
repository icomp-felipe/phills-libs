package com.phill.libs.ui;

import java.awt.event.ActionEvent;

/** Defines an action to be executed when using the {@link ShortcutAction} class.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 1.0, 04/MAY/2021 */
@FunctionalInterface
public interface ShortcutActionInterface {
	
	public void actionPerformed(ActionEvent event);

}
