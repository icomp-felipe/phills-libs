package com.phill.libs.ui;

import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import com.phill.libs.ResourceManager;

/** This custom implementation of a {@link JPanel} allows you to use an image
 *  file as background of this panel.
 *  @author Felipe André - felipeandre.eng@gmail.com
 *  @version 2.0, 19/SET/2020 */
public class JPaintedPanel extends JPanel {

	// Serial
	private static final long serialVersionUID = 3725730618990722909L;
	
	// Image path and properties
	private final String resourcePath;
	private final int width, height;

	/** Builds a default {@link JPaintedPanel} with an image coming from <code>resourcePath</code> as background.<br>
	 *  Note: here the background area will be filled using image's dimensions.
	 *  @param resourcePath - image resource path. For more info, please refer to see also section.
	 *  @see ResourceManager */
	public JPaintedPanel(final String resourcePath) {
		this(resourcePath,0,0);
	}
	
	/** Builds a {@link JPaintedPanel} with an image coming from <code>resourcePath</code> as background.<br>
	 *  Note: here the background area will be filled using the values of <code>resolution</code> as bounds.
	 *  @param resourcePath - image resource path. For more info, please refer to see also section.
	 *  @param resolution - desired resolution (usually the same as your Swing window size)
	 *  @see ResourceManager */
	public JPaintedPanel(final String resourcePath, final Dimension resolution) {
		this(resourcePath, (int) resolution.getWidth(), (int) resolution.getHeight());
	}

	/** Builds a {@link JPaintedPanel} with an image coming from <code>resourcePath</code> as background.<br>
	 *  Note: here the background area will be filled using the values of <code>width</code> and <code>height</code> as bounds.
	 *  @param resourcePath - image resource path. For more info, please refer to see also section.
	 *  @param width - desired width (usually the same as your Swing window width)
	 *  @param height - desired height (usually the same as your Swing window height)
	 *  @see ResourceManager */
	public JPaintedPanel(final String resourcePath, int width, int height) {
		this.resourcePath = resourcePath;
		this.width  = width;
		this.height = height;
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		
		// If an exception is thrown, the frame creation is not interrupted
		try {
			
			int width, height;
			
			// Reading image
			BufferedImage background = ImageIO.read(new File(ResourceManager.getResource(this.resourcePath)));
			
			super.paintComponent(graphics);
			
			// If a fixed width and height was set to the background, it won't be resized...
			if ((this.width > 0) && (this.height > 0)) {
				
				width  = this.width ;
				height = this.height;
				
			}
			
			// ...otherwise, a new size is calculated every time the frame has its resolution changed
			else {
				
				double widthScaleFactor  = getWidth () / (double) background.getWidth ();
				double heightScaleFactor = getHeight() / (double) background.getHeight();
				double scaleFactor       = (widthScaleFactor > heightScaleFactor) ? heightScaleFactor : widthScaleFactor;

				width  = (int)(background.getWidth () * scaleFactor);
				height = (int)(background.getHeight() * scaleFactor);
				
			}

			// After all, we need to display the changes
			graphics.drawImage(background, 0, 0, width, height, null);
			
		}
		catch (IOException exception) {
			System.err.println(":: JPaintedPanel: failed to load background image: " + exception.getMessage());
		}
		
	}
	
}
