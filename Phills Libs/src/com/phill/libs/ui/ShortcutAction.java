package com.phill.libs.ui;

import javax.swing.*;
import java.awt.event.*;

/** Implements an {@link AbstractAction} with embedded mnemonic and accelerator.
 *  @author Felipe André - felipeandre.eng@gmail.com
 *  @version 1.0, 04/MAY/2021
 *  @see ShortcutActionInterface */
public class ShortcutAction extends AbstractAction {
	
	// Serial
	private static final long serialVersionUID = 837560008863432266L;
	
	// Action interface
	private final ShortcutActionInterface actionInterface;
	
	/** Constructs an {@link AbstractAction} with the given <code>title</code>, sets the <code>mnemonic</code> and <code>accelerator</code> objects,
	 *  and defines an <code>actionInterface</code> to be run when this Action is fired by Java Swing.
	 *  @param title - the action title
	 *  @param mnemonic - a mnemonic character (usually coming from {@link KeyEvent}), can be null
	 *  @param accelerator - an accelerator object (usually coming from {@link KeyStroke}), can be null
	 *  @param actionInterface - actual action to be run. If null, nothing happens */
	public ShortcutAction(final String title, final Object mnemonic, final Object accelerator, final ShortcutActionInterface actionInterface) {
		super(title);
		
		putValue(Action.MNEMONIC_KEY, mnemonic);
		putValue(Action.ACCELERATOR_KEY, accelerator);
		
		this.actionInterface = actionInterface;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if (this.actionInterface != null)
			this.actionInterface.actionPerformed(event);
		
	}

}
