package com.phill.libs.br;

import java.awt.Color;
import javax.swing.JFormattedTextField;

import com.phill.libs.StringUtils;
import com.phill.libs.ui.GraphicsHelper;

/** Cria um campo de texto formatado com a máscara de CNPJ e
 *  faz validação de dados inseridos, caso o CNPJ digitado
 *  esteja correto, o campo muda de cor para 'verde', caso
 *  contrário, muda para 'vermelho'. Se o CNPJ digitado esti-
 *  ver incompleto, o campo permanece em 'branco'.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 1.1, 25/APR/2021
 *  @see JFormattedTextField  */
public class CNPJTextField extends BRTextField {
	
	// Serial
	private static final long serialVersionUID = 1L;
	
	/** Construtor da classe já aplicando a máscara
	 *  de CPF e adicionando algoritmo de validação. */
	public CNPJTextField() {
		super(GraphicsHelper.getMask("##.###.###./####-##"));
	}
	
	/** Realiza a validação de dados na interface gráfica */
	protected void parse() {

		final String cnpj = StringUtils.extractNumbers(getText());
		
		if (cnpj.length() == 14) {
			
			if (CNPJParser.parse(cnpj))
				setBackground(gr_lt);
			else
				setBackground(rd_lt);
			
		}
		else
			setBackground(Color.WHITE);
		
	}
	
	/** Verifica se o número de CNPJ deste campo é válido.
	 *  @return Validade do CNPJ (cálculo numérico).
	 *  @since 1.1, 25/APR/2021 */
	public boolean valido() {
		return CNPJParser.parse(getText());
	}
	
	/** Retorna o texto preenchido com ou sem máscara */
	public String getCNPJ(final boolean apenasNumeros) {
		return (apenasNumeros) ? StringUtils.extractNumbers(getText()) : getText();
	}

}