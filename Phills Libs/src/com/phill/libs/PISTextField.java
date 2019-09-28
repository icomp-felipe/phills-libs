package com.phill.libs;

import java.awt.Color;
import javax.swing.JFormattedTextField;

/** Cria um campo de texto formatado com a máscara de PIS e
 *  faz validação de dados inseridos, caso o PIS digitado
 *  esteja correto, o campo muda de cor para 'verde', caso
 *  contrário, muda para 'vermelho'. Se o PIS digitado esti
 *  ver incompleto, o campo permanece em 'branco'.
 *  @author Felipe André
 *  @version 2.5, 11/09/2018
 *  @see JFormattedTextField  */
public class PISTextField extends JFormattedTextField {

	private final Color gr_lt  = new Color(0x84efa5);
	private final Color rd_lt  = new Color(0xef8e84);
	private static final long serialVersionUID = 1L;
	
	public PISTextField() {
		super(GraphicsHelper.getInstance().getMascara("###.#####.##-#"));
		this.addKeyListener((KeyboardAdapter) (event) -> parse());
	}
	
	/** Realiza a validação de dados na interface gráfica */
	private void parse() {

		String pis = StringUtils.extractNumbers(getText());
		
		if (pis.length() == 11) {
			
			if (PISParser.parse(pis))
				setBackground(gr_lt);
			else
				setBackground(rd_lt);
			
		}
		else
			setBackground(Color.WHITE);
		
	}
	
	/** Retorna o texto preenchido com ou sem máscara */
	public String getPIS(boolean apenasNumeros) {
		return (apenasNumeros) ? StringUtils.extractNumbers(getText()) : getText();
	}

	@Override
	/** Pinta de branco caso a 'string' seja vazia */
	public void setText(String string) {
		super.setText(string);	parse();
	}
	
	@Override
	/** Pinta de branco caso a 'string' seja vazia */
	public void setValue(Object value) {
		super.setValue(value);	parse();
	}
	
}
