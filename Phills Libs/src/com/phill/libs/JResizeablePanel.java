package com.phill.libs;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

/** Cria um painel personalizado com imagem de fundo
*	@author Felipe André
*	@version 2.5, 22/01/2015 */
public class JResizeablePanel extends JPanel {

	/** Atributos de controle da classe */
	private transient static final long serialVersionUID = 1L;
	private String arquivoImagem;

	/** Instancia a classe e seta os atributos */
	public JResizeablePanel(String arquivoImagem) {
		this.arquivoImagem = arquivoImagem;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		File f = new File(ResourceManager.getResource(arquivoImagem));
		
		BufferedImage originalImage = null;
		try {
			originalImage = ImageIO.read(f);
		} catch (IOException e) {
			return;
		}
		super.paintComponent(g);

		double widthScaleFactor = getWidth() / (double)originalImage.getWidth();
		double heightScaleFactor = getHeight() / (double)originalImage.getHeight();
		double scaleFactor = (widthScaleFactor > heightScaleFactor)? heightScaleFactor : widthScaleFactor;

		int width = (int)(originalImage.getWidth() * scaleFactor);
		int height = (int)(originalImage.getHeight() * scaleFactor);

		g.drawImage(originalImage, 0, 0, width, height, null);
	}
	
}
