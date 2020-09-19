package com.phill.libs.br;

import java.awt.Color;
import javax.swing.JFormattedTextField;

import com.phill.libs.StringUtils;
import com.phill.libs.ui.GraphicsHelper;

/** Cria um campo de texto formatado com a máscara de CPF e
 *  faz validação de dados inseridos, caso o CPF digitado
 *  esteja correto, o campo muda de cor para 'verde', caso
 *  contrário, muda para 'vermelho'. Se o CPF digitado esti
 *  ver incompleto, o campo permanece em 'branco'.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 4.0, 17/SET/2020
 *  @see JFormattedTextField  */
public class CPFTextField extends BRTextField {
	
	// Serial
	private static final long serialVersionUID = 1L;
	
	/** Construtor da classe já aplicando a máscara
	 *  de CPF e adicionando algoritmo de validação. */
	public CPFTextField() {
		super(GraphicsHelper.getInstance().getMask("###.###.###-##"));
	}
	
	/** Realiza a validação de dados na interface gráfica */
	protected void parse() {

		String cpf = StringUtils.extractNumbers(getText());
		
		if (cpf.length() == 11) {
			
			if (CPFParser.parse(cpf))
				setBackground(gr_lt);
			else
				setBackground(rd_lt);
			
		}
		else
			setBackground(Color.WHITE);
		
	}
	
	/** Retorna o texto preenchido com ou sem máscara */
	public String getCPF(boolean apenasNumeros) {
		return (apenasNumeros) ? StringUtils.extractNumbers(getText()) : getText();
	}

}
