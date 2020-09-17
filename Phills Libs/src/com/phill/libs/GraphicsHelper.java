package com.phill.libs;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.border.*;
import javax.swing.text.*;

/** Classe que contém métodos úteis de manipulação de GUI
 *  @author Felipe André
 *  @version 3.0, 22/03/2016 */
public final class GraphicsHelper {
	
	/** Instância atual do singleton */
	private static GraphicsHelper INSTANCE;
	
	// Atributos da classe
	private File ttfFont ;
	private Font baseFont;
	private Font normFont;
	
	/** Acesso ao singleton */
	public static synchronized GraphicsHelper getInstance() {
		
		if (INSTANCE == null)
			INSTANCE = new GraphicsHelper();
		
		return INSTANCE;
	}
	
	public static void setFrameIcon(JFrame frame, String resource) {
		
		try {
			File imagePath = ResourceManager.getResourceAsFile(resource);
			BufferedImage rawImage = ImageIO.read(imagePath);
			frame.setIconImage(rawImage);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
	}
	
	public static synchronized GraphicsHelper getInstanceColor() {
		
		GraphicsHelper helper = new GraphicsHelper();
		helper.setColor(new Color(0x9d8904));
		
		return helper;
	}
	
	private void setColor(Color color) {
		this.color = color;
	}
	
	/** Construtor da Classe */
	private GraphicsHelper() {
		try {
			this.ttfFont  = ResourceManager.getResourceAsFile("fonts/swiss.ttf");
			this.baseFont = createFont(ttfFont);
			this.normFont = deriveFont(baseFont, Font.PLAIN, 15);
		} catch (Exception exception) {
			System.err.println("Falha ao carregar propriedades. Detalhes: " + exception.getMessage());
		}
	}
	
	/** Recursos gráficos */
	private Color color = new Color(31,96,203);
	
	/** Retorna a fonte atual */
	public Font getFont() {
		return normFont;
	}
	
	/** Retorna uma nova fonte com o tamanho especificado */
	public Font getFont(int size) {
		return deriveFont(baseFont, Font.PLAIN, size);
	}
	
	/** Retorna a cor azul */
	public Color getColor() {
		return color;
	}
	
	/** Cria uma Borda Personalizada para os painéis do Java Swing e AWT */
	public TitledBorder getTitledBorder(String title) {
		return new TitledBorder(null, title, TitledBorder.LEADING, TitledBorder.TOP, getFont(), getColor());
	}
	
	public TitledBorder getTitledBorder(String title, Font font) {
		return new TitledBorder(null, title, TitledBorder.LEADING, TitledBorder.TOP, (font == null) ? getFont() : font, getColor());
	}
	
	/** Cria uma Máscara Personalizada para os text fields do Java Swing */
	public MaskFormatter getMascara(String mascara) {
		return getMascara(mascara,' ');
	}
	
	/** Cria uma Máscara Personalizada com preenchimento personalizado para os text fields do Java Swing */
	public MaskFormatter getMascara(String mascara, char preenchimento) {
		MaskFormatter formato = new MaskFormatter();
		formato.setValueContainsLiteralCharacters(false);
		try {
			formato.setMask(mascara);
			formato.setPlaceholderCharacter(preenchimento);
		}
		catch (Exception exception) {  
			exception.printStackTrace();
	    }
		return formato;  
	}
	
	/** Cria uma nova fonte TTF a partir de um arquivo especificado */
	public Font createFont(File arquivoFonte) {
		try {  return Font.createFont(Font.TRUETYPE_FONT, arquivoFonte); }
		catch (Exception exception) { exception.printStackTrace(); return null; }
	}
	
	/** Cria uma fonte personalizada a partir de uma já existente */
	public Font deriveFont(Font arquivoFonte, int tipoFonte, int tamanho) {
		try { return arquivoFonte.deriveFont(tipoFonte, tamanho); }
		catch (Exception exception) { exception.printStackTrace(); return null; }
	}
	
}
