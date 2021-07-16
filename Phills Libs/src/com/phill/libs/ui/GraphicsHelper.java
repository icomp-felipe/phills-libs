package com.phill.libs.ui;

import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;

import com.phill.libs.ResourceManager;

/** Singleton class used to be a helper to some methods related to graphic
 *  components personalization in Java like frames, textfields and many more.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 5.0, 24/SEP/2020 */
public final class GraphicsHelper {
	
	// Current singleton instamce
	private static GraphicsHelper INSTANCE;

	// Custom fonts
	private Font swissFont, swissDefaultFont, ubuntuFont, ubuntuDefaultFont;
	
	// Custom color
	private Color color;

	/** This is a singleton class, so this method returns its only instance or
	 *  creates and then returns a new one during the firt call of this method.
	 *  This instance has an embedded color (blue by default) used to paint some
	 *  components such as panel borders, and a base font 'swiss.ttf' found in
	 *  the 'res' directory.
	 *  @return The only instance of this class. */
	public static synchronized GraphicsHelper getInstance() {
		return getInstance(new Color(0x1F60CB));
	}
	
	/** This is a singleton class, so this method returns its only instance or
	 *  creates and then returns a new one during the firt call of this method.
	 *  This instance has an embedded color (blue by default) used to paint some
	 *  components such as panel borders, and a base font 'swiss.ttf' found in
	 *  the 'res' directory.
	 *  @param color - custom color specified to all panel borders and graphics
	 *  @return The only instance of this class. */
	public static synchronized GraphicsHelper getInstance(final Color color) {
		
		if (INSTANCE == null) {
			INSTANCE = new GraphicsHelper();
			INSTANCE.setColor(color);
		}
		
		return INSTANCE;
	}
	
	/** Main constructor setting parameters. */
	private GraphicsHelper() {
		
		try {
			
			String swiss  = "/com/phill/libs/fonts/swiss.ttf";
			String ubuntu = "/com/phill/libs/fonts/Ubuntu-R.ttf";
			
			InputStream swissStream  = GraphicsHelper.class.getResourceAsStream(swiss );
			InputStream ubuntuStream = GraphicsHelper.class.getResourceAsStream(ubuntu);
			
			this.swissFont = Font.createFont(Font.TRUETYPE_FONT, swissStream);
			this.swissDefaultFont = ResourceManager.deriveFont(swissFont, Font.PLAIN, 15);
			
			this.ubuntuFont        = Font.createFont(Font.TRUETYPE_FONT, ubuntuStream);
			this.ubuntuDefaultFont = ResourceManager.deriveFont(ubuntuFont, Font.PLAIN, 14);
			
			swissStream.close();
			
		} catch (Exception exception) {
			System.err.println(":: GraphicsHelper: Failed to load resources: " + exception.getMessage());
		}
		
	}
	
	/** Class default color getter.
	 *  @return Current class color. */
	public Color getColor() {
		return this.color;
	}

	/** Class default font getter.
	 *  @return Current class font. */
	public Font getFont() {
		return this.swissDefaultFont;
	}

	/** Returns a new font with the specified <code>size</code>.
	 *  @param size - font size
	 *  @return A new font with the specified <code>size</code>. */
	public Font getFont(final int size) {
		return ResourceManager.deriveFont(this.swissFont, Font.PLAIN, size);
	}
	
	/** Ubuntu default font getter.
	 *  @return Current class font. */
	public Font getUbuntuFont() {
		return this.ubuntuDefaultFont;
	}
	
	/** Returns a new font with the specified <code>size</code>.
	 *  @param size - font size
	 *  @return A new font with the specified <code>size</code>. */
	public Font getUbuntuFont(final int size) {
		return ResourceManager.deriveFont(this.ubuntuFont, Font.PLAIN, size);
	}
	
	/** Creates a {@link TitledBorder} with the given <code>title</code> at the
	 *  top and justification at the left using the class default color and font.
	 *  @param title - border title
	 *  @return A new {@link TitledBorder} with parameters described above. */
	public TitledBorder getTitledBorder(final String title) {
		return new TitledBorder(null, title, TitledBorder.LEADING, TitledBorder.TOP, getFont(), getColor());
	}
	
	/** Creates a {@link TitledBorder} with the given <code>title</code> at the top and
	 *  justification at the left using the class default color and the given <code>font</code>.<br>
	 *  Note: if the <code>font</code> parameter is null, the class default font is loaded.
	 *  @param title - border title
	 *  @param font - custom font
	 *  @return A new {@link TitledBorder} with parameters described above. */
	public TitledBorder getTitledBorder(final String title, final Font font) {
		return new TitledBorder(null, title, TitledBorder.LEADING, TitledBorder.TOP, (font == null) ? getFont() : font, getColor());
	}
	
	/** Creates a {@link TitledBorder} with the given parameters.
	 *  @param title - border title
	 *  @param font - custom font
	 *  @param titlejustification - the justification for the title
	 *  @param titlePosition - the position for the title
	 *  @return A new {@link TitledBorder} with parameters described above. */
	public TitledBorder getTitledBorder(final String title, final Font font, final int titlejustification, final int titlePosition) {
		return new TitledBorder(null, title, titlejustification, titlePosition, (font == null) ? getFont() : font, getColor());
	}
	
	/** Changes this class default color.
	 *  @param color - new color */
	public void setColor(final Color color) {
		this.color = color;
	}
	
	/******************************** Static Methods Section *************************************/
	
	/** Creates a {@link MaskFormatter} with the given <code>mask</code> and a single space as placeholder.
	 *  @param mask - String containing legal characters to be used here. This will throw a ParseException if mask is not valid
	 *  @return A new {@link MaskFormatter} with parameters described above. */
	public static MaskFormatter getMask(final String mask) {
		return getMask(mask,' ');
	}
	
	/** Creates a {@link MaskFormatter} with the given <code>mask</code> and <code>placeholder</code>.
	 *  @param mask - String containing legal characters to be used here. This will throw a ParseException if mask is not valid
	 *  @param placeholder - character used when formatting if the value does not completely fill the mask
	 *  @return A new {@link MaskFormatter} with parameters described above. */
	public static MaskFormatter getMask(final String mask, final char placeholder) {
		
		MaskFormatter formatter = new MaskFormatter();
		formatter.setValueContainsLiteralCharacters(false);
		
		try {
			formatter.setMask(mask);
			formatter.setPlaceholderCharacter(placeholder);
		}
		catch (Exception exception) {
			System.err.println(":: GraphicsHelper: failed to create MaskFormatter: " + exception.getMessage());
	    }
		
		return formatter;  
	}
	
	/** Retrieves the system's screen size.
	 *  @return System desktop size.  */
	public static Dimension getScreenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	
	/** Sets an image icon coming from the given <code>resourcePath</code> in the window <code>frame</code>. 
	 *  This icon is shown in your system's title bar, instead of the default Java one.
	 *  @param frame - Swing window frame
	 *  @param resourcePath - image resource path. For more info, please refer to see also section.
	 *  @see ResourceManager */
	public static void setFrameIcon(final JFrame frame, final String resourcePath) {
		
		try {
			
			File imagePath = ResourceManager.getResourceAsFile(resourcePath);
			BufferedImage rawImage = ImageIO.read(imagePath);
			
			frame.setIconImage(rawImage);
			
		} catch (IOException exception) {
			System.err.println(":: GraphicsHelper: could not read the icon image file: " + exception.getMessage());
		}
		
	}
	
}
