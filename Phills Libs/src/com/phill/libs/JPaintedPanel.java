package com.phill.libs;

import java.awt.*;
import javax.swing.*;

/** Cria um painel personalizado com imagem de fundo
*	@author Felipe André
*	@version 2.5, 22/01/2015 */
public class JPaintedPanel extends JPanel {

	/** Atributos de controle da classe */
	private transient static final long serialVersionUID = 1L;
	private final String arquivoImagem;
	private final int width, height;

	/** Instancia a classe e seta os atributos */
	public JPaintedPanel(String arquivoImagem) {
		this(arquivoImagem,0,0);
	}
	
	public JPaintedPanel(String arquivoImagem, Dimension d) {
		this(arquivoImagem, (int) d.getWidth(), (int) d.getHeight());
	}
	
	/** Instancia a classe e seta os atributos */
	public JPaintedPanel(String arquivoImagem, int width, int height) {
		this.arquivoImagem = arquivoImagem;
		this.width  = width;
		this.height = height;
	}
	
	@Override
	/** Cria o plano de fundo do painel */
	public void paintComponent(Graphics graphics) {
		Image imagem = new ImageIcon(ResourceManager.getResource(arquivoImagem)).getImage();    
		graphics.drawImage(imagem, 0, 0, width, height, this);
	}
	
}
