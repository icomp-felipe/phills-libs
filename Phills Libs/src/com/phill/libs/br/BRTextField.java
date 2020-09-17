package com.phill.libs.br;

import java.awt.Color;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import com.phill.libs.KeyboardAdapter;
import com.phill.libs.StringUtils;

/** Cria um campo de texto formatado com a máscara informada e faz
 *  validação dos dados inseridos de acordo com o método abstrato 'parse'.
 *  Caso o número esteja correto, o campo muda de cor para 'verde', caso
 *  contrário, muda para 'vermelho'. Se o número digitado estiver
 *  ainda incompleto, o campo permanece em 'branco'.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 1.0, 17/SET/2020
 *  @see JFormattedTextField  */
public abstract class BRTextField extends JFormattedTextField {

	// Serial
	private static final long serialVersionUID = -2459356157802778584L;
	
	// Cores de fundo
	protected final Color gr_lt  = new Color(0x84efa5);
	protected final Color rd_lt  = new Color(0xef8e84);
	
	public BRTextField(MaskFormatter mascara) {
		super(mascara);
		this.addKeyListener((KeyboardAdapter) (event) -> parse());
	}

	/** Aplica o texto e pinta este campo de acordo com as cores:<br>
	 *  Verde - caso o número informado tenha sido totalmente preenchido e é válido;<br>
	 *  Vermelho - caso o número informado tenha sido totalmente preenchido e é inválido;<br>
	 *  Branco - caso o número ainda esteja em digitação ou não. */
	@Override
	public void setText(String string) {
		super.setText(string);	parse();
	}
	
	/** Aplica o valor e pinta este campo de acordo com as cores:<br>
	 *  Verde - caso o número informado tenha sido totalmente preenchido e é válido;<br>
	 *  Vermelho - caso o número informado tenha sido totalmente preenchido e é inválido;<br>
	 *  Branco - caso o número ainda esteja em digitação ou não. */
	@Override
	public void setValue(Object value) {
		super.setValue(value);	parse();
	}
	
	/** Verifica se o número contido neste campo é válido.
	 *  @return 'true' se o número estiver completo e for válido ou 'false' caso contrário. */
	public boolean valido() {
		return PISParser.parse(getText());
	}

	/** Verifica se este campo está vazio, ignorando os caracteres da máscara.
	 *  @return 'true' se não há nenhum dígito (0-9) neste campo ou 'false' caso contrário. */
	public boolean isEmpty() {
		return StringUtils.extractNumbers(getText()).isEmpty();
	}
	
	/** Realiza a validação de dados na interface gráfica */
	protected abstract void parse();
	
}
