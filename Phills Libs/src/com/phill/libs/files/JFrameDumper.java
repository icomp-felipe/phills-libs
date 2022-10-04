package com.phill.libs.files;

import java.awt.*;
import java.awt.image.*;

import java.io.*;

import javax.imageio.*;

import org.apache.commons.io.FilenameUtils;

public class JFrameDumper {

	public static void dump(final Container container, final File file) throws IOException {
		
		BufferedImage bi = new BufferedImage(container.getWidth(), container.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = bi.createGraphics();
		
		container.print(graphics);
		
		ImageIO.write(bi, FilenameUtils.getExtension(file.getName()), file);
		
	}
	
}
