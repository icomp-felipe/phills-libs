package com.phill.libs.i18n;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.*;

import com.phill.libs.ui.GraphicsHelper;

/** Class designed to help filling i18n texts to Java Swing components.
 *  Here the components are stored in a &lt;key, value&gt; map, where the given 'key'
 *  is the same as a {@link PropertyBundle} key. In other words, the same 'key'
 *  is used to identify a given Swing component and get the resource associated value
 *  from the property resource bundle.<br>
 *  Note: the UI update process is controlled by the {@link PropertyBundle} class,
 *  passed as parameter to this class' constructor.
 *  @author Felipe André - felipeandre.eng@gmail.com
 *  @version 1.0, 23/SEP/2020 */
public class PropertyBundleFiller {
	
	/** Source i18n resource bundle */
	private final PropertyBundle bundle;
	
	/** Custom border font */
	private Font borderFont;
	
	/** Swing component lists */
	private final Map<String, JComponent> textList, tooltipList, borderList;
	
	/** Constructor setting attributes and registering its instance
	 *  to the {@link PropertyBundle} instance.<br>
	 *  Note: the {@link PropertyBundle} class must have be already constructed,
	 *  otherwise, this constructor will throw a {@link NullPointerException}.
	 *  @param bundle - property resource bundle */
	public PropertyBundleFiller(final PropertyBundle bundle) {
		
		this.bundle = bundle;
		this.bundle.registerFiller(this);
		
		textList    = new HashMap<String, JComponent>();
		tooltipList = new HashMap<String, JComponent>();
		borderList  = new HashMap<String, JComponent>();
		
	}
	
	/******************************* User Interface Methods **************************************/
	
	/** Changes the font to be displayed in component borders.
	 *  @param borderFont - custom font */
	public void setBorderFont(final Font borderFont) {
		this.borderFont = borderFont;
	}
	
	/** Retrieves the previous set border font.
	 *  @return The previous set border font or 'null' if none was set. */
	public Font getBorderFont() {
		return this.borderFont;
	}
	
	/** Registers the given <code>button</code> to the internal 'set-text' component list.
	 *  The <code>key</code> is used to retrieve the text from the {@link PropertyBundle} class.
	 *  @param button - Swing button (JButton, JToggleButton, JMenuItem, ...)
	 *  @param key - the key whose associated text (in a i18n file) is to be set */
	public void setText(final AbstractButton button, final String key) { 
		this.textList.put(key, button);
	}
	
	/** Registers the given <code>textField</code> to the internal 'set-text' component list.
	 *  The <code>key</code> is used to retrieve the text from the {@link PropertyBundle} class.
	 *  @param textField - Swing text component (JTextField, JFormattedTextField, JTextArea, ...)
	 *  @param key - the key whose associated text (in a i18n file) is to be set */
	public void setText(final JTextComponent textField, final String key) {
		this.textList.put(key, textField);
	}
	
	/** Registers the given <code>label</code> to the internal 'set-text' component list.
	 *  The <code>key</code> is used to retrieve the text from the {@link PropertyBundle} class.
	 *  @param label - Swing label
	 *  @param key - the key whose associated text (in a i18n file) is to be set */
	public void setText(final JLabel label, final String key) {
		this.textList.put(key, label);
	}
	
	/** Registers the given <code>component</code> to the internal 'set-tooltip-text' component list.
	 *  The <code>key</code> is used to retrieve the text from the {@link PropertyBundle} class.
	 *  @param component - Swing component (JButton, JTextField, JTextArea, JPanel, ...)
	 *  @param key - the key whose associated text (in a i18n file) is to be set */
	public void setToolTipText(final JComponent component, final String key) {
		this.tooltipList.put(key, component);
	}
	
	/** Registers the given <code>component</code> to the internal 'set-border-text' component list.
	 *  The <code>key</code> is used to retrieve the text from the {@link PropertyBundle} class.
	 *  @param component - Swing component (JButton, JTextField, JTextArea, JPanel, ...)
	 *  @param key - the key whose associated text (in a i18n file) is to be set */
	public void setBorder(final JComponent component, final String key) {
		this.borderList.put(key, component);
	}
	
	/**************************** PropertyBundle Interface Methods *******************************/
	
	/** Updates all pre-registered texts, tooltip texts and borders. */
	protected void update() {
		setText(); setToolTipText(); setBorder();
	}
	
	/******************************* Class Control Methods ***************************************/
	
	/** Iterates over all pre-registered 'set-text' component
	 *  list and sets their texts according to their given 'keys'. */
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
	
	/** Iterates over all pre-registered 'set-tooltip-text' component
	 *  list and sets their tooltip text according to their given 'keys'. */
	private void setToolTipText() {
		
		this.tooltipList.forEach((key, component) -> {
			
			final String tooltip = this.bundle.getString(key);
			
			component.setToolTipText(tooltip);
			
		});
		
	}
	
	/** Iterates over all pre-registered 'set-border' component list
	 *  and sets their border texts according to their given 'keys'. */
	private void setBorder() {
		
		this.borderList.forEach((key, component) -> {
			
			final String borderTitle  = this.bundle.getString(key);
			final TitledBorder border = GraphicsHelper.getInstance().getTitledBorder(borderTitle, this.borderFont);
			
			component.setBorder(border);
			
		});
		
	}
	
}
