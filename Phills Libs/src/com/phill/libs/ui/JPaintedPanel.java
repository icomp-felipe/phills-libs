package com.phill.libs.ui;

import java.awt.*;
import javax.swing.*;

import com.phill.libs.ResourceManager;

/** This custom implementation of a {@link JPanel} allows you to use an image
 *  file as background of this panel.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 1.5, 18/SET/2020 */
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
		Image imagem = new ImageIcon(ResourceManager.getResource(this.resourcePath)).getImage();    
		graphics.drawImage(imagem, 0, 0, width, height, this);
	}
	
}
