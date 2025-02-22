package com.phill.libs.br;

import java.awt.Color;
import javax.swing.JFormattedTextField;

import com.phill.libs.StringUtils;
import com.phill.libs.ui.GraphicsHelper;

/** Cria um campo de texto formatado com a máscara de PIS e
 *  faz validação de dados inseridos, caso o PIS digitado
 *  esteja correto, o campo muda de cor para 'verde', caso
 *  contrário, muda para 'vermelho'. Se o PIS digitado esti
 *  ver incompleto, o campo permanece em 'branco'.
 *  @author Felipe André - felipeandre.eng@gmail.com
 *  @version 2.6, 24/APR/2020
 *  @see JFormattedTextField  */
public class PISTextField extends BRTextField {

	// Serial
	private static final long serialVersionUID = -754951398044321147L;
	
	/** Construtor da classe já aplicando a máscara
	 *  de PIS e adicionando algoritmo de validação. */
	public PISTextField() {
		super(GraphicsHelper.getMask("###.#####.##-#"));
	}
	
	@Override
	protected void parse() {

		final String pis = StringUtils.extractNumbers(getText());
		
		if (pis.length() == 11) {
			
			if (PISParser.parse(pis))
				setBackground(super.gr_lt);
			else
				setBackground(super.rd_lt);
			
		}
		else
			setBackground(Color.WHITE);
		
	}
	
	/** Retorna o texto preenchido com ou sem máscara */
	public String getPIS(final boolean apenasNumeros) {
		return (apenasNumeros) ? StringUtils.extractNumbers(getText()) : getText();
	}

	@Override
	public boolean valido() {
		return PISParser.parse(getText());
	}
	
}