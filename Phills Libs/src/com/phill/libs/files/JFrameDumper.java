package com.phill.libs.files;

import java.awt.*;
import java.awt.image.*;

import java.io.*;
import javax.imageio.*;

public class JFrameDumper {

	public static void dump(final Container container, final File file) throws IOException {
		
		BufferedImage bi = new BufferedImage(container.getWidth(), container.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = bi.createGraphics();
		
		container.print(graphics);

		String fileName = file.getName();
		String extension = fileName != null && fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".") + 1) : "";
		
		ImageIO.write(bi, extension, file);
		
	}
	
}
