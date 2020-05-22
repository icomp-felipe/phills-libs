package com.phill.libs;

import java.awt.*;
import javax.swing.*;

public class PaintedOptionPane extends JOptionPane {

	/** Atributos de controle da classe */
	private transient static final long serialVersionUID = 1L;
	private final String arquivoImagem;
	private final int width, height;
	
	/** Instancia a classe e seta os atributos */
	public PaintedOptionPane(String arquivoImagem) {
		this(arquivoImagem,0,0);
	}
	
	public PaintedOptionPane(String arquivoImagem, Dimension d) {
		this(arquivoImagem, (int) d.getWidth(), (int) d.getHeight());
	}
	
	/** Instancia a classe e seta os atributos */
	public PaintedOptionPane(String arquivoImagem, int width, int height) {
		this.arquivoImagem = arquivoImagem;
		this.width  = width;
		this.height = height;
	}
	
	@Override
	public void paint(Graphics graphics) {
		
		Image background = new ImageIcon(ResourceManager.getResource(arquivoImagem)).getImage();
		
		System.out.println(width + " - " + height);
		graphics.drawImage(background, 0, 0, width, height, null);
		
		super.paint(graphics);
		
	}
	
	@Override
	/** Cria o plano de fundo do painel */
	public void paintComponent(Graphics graphics) {
		Image imagem = new ImageIcon(ResourceManager.getResource(arquivoImagem)).getImage();    
		graphics.drawImage(imagem, 0, 0, width, height, this);
	}
	
}
