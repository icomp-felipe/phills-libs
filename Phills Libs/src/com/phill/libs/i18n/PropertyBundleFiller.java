package com.phill.libs.i18n;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.*;

import com.phill.libs.ResourceManager;
import com.phill.libs.ui.GraphicsHelper;

public class PropertyBundleFiller {
	
	private final PropertyBundle bundle;
	private final Map<String, JComponent> textList, tooltipList, borderList;
	
	
	public PropertyBundleFiller(final PropertyBundle bundle) {
		
		this.bundle = bundle;
		this.bundle.registerFiller(this);
		
		textList    = new HashMap<String, JComponent>();
		tooltipList = new HashMap<String, JComponent>();
		borderList  = new HashMap<String, JComponent>();
		
	}
	
	protected void update() {
		setText(); setTooltip(); setBorder();
	}
	
	public void text(AbstractButton button, String key) { 
		this.textList.put(key, button);
	}
	
	public void text(JTextComponent textField, String key) {
		this.textList.put(key, textField);
	}
	
	public void text(JLabel label, String key) {
		this.textList.put(key, label);
	}
	
	private void setText() {
		
		this.textList.forEach((key, component) -> {
			
			final String text = this.bundle.getString(key);
			
			if (component instanceof AbstractButton)
				((AbstractButton) component).setText(text);
			
			else if (component instanceof JTextComponent)
				((JTextComponent) component).setText(text);
			
			else if (component instanceof JLabel)
				((JLabel) component).setText(text);
			
		});
		
	}
	
	
	public void tooltip(JComponent component, String key) {
		this.tooltipList.put(key, component);
	}
	
	private void setTooltip() {
		
		this.tooltipList.forEach((key, component) -> {
			
			final String tooltip = this.bundle.getString(key);
			
			component.setToolTipText(tooltip);
			
		});
		
	}
	
	public void border(JComponent component, String key) {
		this.borderList.put(key, component);
	}
	
	private void setBorder() {
		
		final Font customFont = (bundle.getCurrentLocate().getLanguage().equals("ja")) ? getJapaneseFont() : null;
		
		this.borderList.forEach((key, component) -> {
			
			final String borderTitle  = this.bundle.getString(key);
			final TitledBorder border = GraphicsHelper.getInstance().getTitledBorder(borderTitle, customFont);
			
			component.setBorder(border);
			
		});
		
	}
	
	private Font getJapaneseFont() {
		
		try {
			
			final File path = ResourceManager.getResourceAsFile("fonts/japanese.ttf");
			final Font base = Font.createFont(Font.TRUETYPE_FONT, path);
			final Font font = base.deriveFont(Font.BOLD, 15f);
			
			return font;
		}
		catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
