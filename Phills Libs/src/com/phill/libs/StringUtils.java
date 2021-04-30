package com.phill.libs;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Contains useful methods to manipulate {@link String} in Java applications.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 2.6, 30/APR/2021 */
public class StringUtils {

	/** Converts all blank or empty fields ('null',"null",'',"") in a SQL string to a SQL null field.
	 *  @param sql - SQL string to be cleaned
	 *  @return A SQL string without blank or empty fields. */
	public static String blankToNull(final String sql) {
		return sql.replace("\"\"","null")
				  .replace("\"null\"","null")
				  .replace("''","null")
				  .replace("'null'","null");
	}
	
	/** Extracts only alphabets from the given string.
	 *  @param string - String
	 *  @param ignoreSpaces - enable or disable removing spaces from the string
	 *  @return A new string containing alphanumeric characters (w/o) spaces.
	 *  @return A new string containing only alphabets. */
	public static String extractAlphabet(final String string, final String replacement, final boolean ignoreSpaces) {
		return (string == null) ? null : (ignoreSpaces) ? string.replaceAll("[^A-Za-z]+", replacement).trim() : string.replaceAll("[^A-Za-z ]+", replacement);
	}
	
	/** Extracts only numbers from the given string.
	 *  @param string - String
	 *  @return A new string containing only numbers. */
	public static String extractNumbers(final String string) {
		return (string == null) ? null : string.replaceAll("\\D+","").trim();
	}
	
	/** Extracts only alphanumeric characters from the given string.
	 *  @param string - String
	 *  @param replacement - a replacement string to the invalid characters
	 *  @param ignoreSpaces - enable or disable removing spaces from the string
	 *  @return A new string containing alphanumeric characters (w/o) spaces.
	 *  @since 2.3, 21/APR/2021 */
	public static String extractAlphaNumeric(final String string, final String replacement, final boolean ignoreSpaces) {
		return (string == null) ? null : (ignoreSpaces) ? string.replaceAll("[^a-zA-Z0-9]", replacement) : string.replaceAll("[^a-zA-Z0-9 ]", replacement);
	}
	
	/** Extracts a short name from a full name following these rules:<br>
	 *  1. The first and second names are always included;<br>
	 *  2. If the second 'name' has size less than 4, it also includes the 3rd name (if available).
	 *  @param name - full name
	 *  @return A short name following the rules descripted above, or the given <code>name</code> if it has not more than two 'subnames'. */
	public static String getShortName(final String name) {
		
		if (name == null)
			return null;
		
		try {
			
			String[] subnames = name.split(" ");
			StringBuilder sb = new StringBuilder();
			
			sb.append(subnames[0] + " ");
			sb.append(subnames[1]);
				
			if (subnames[1].length() <= 3)
				if (subnames.length >= 3 )
					sb.append(" " + subnames[2]);
			
			return sb.toString();
			
		}
		catch (ArrayIndexOutOfBoundsException exception) {
			return name;
		}
		
	}
	
	/** Detects if the given string has multiple spaces between words.
	 *  @param string - String
	 *  @return 'true' if the given string has at least one multiple space, or 'false' otherwise. */
	public static boolean hasMultipleSpacedWords(final String string) {
		int index = string.indexOf("  ");
		return (index != -1);
	}
	
	/** Tells if the given 'string' has only alphabetic characters.
	 *  @param string - String
	 *  @return 'true' if the given string has only alphabetic characters, or 'false' otherwise. */
	public static boolean isAlphaStringOnly(final String string) {
		return string.matches("^[\\p{L} ]+$");
	}
	
	/** Tells if the given 'string' has only alphanumeric characters.
	 *  @param string - String
	 *  @param ignoreSpaces - enables or disables the space character verification ' '
	 *  @return 'true' if the given string has only alphanumeric characters, or 'false' otherwise. */
	public static boolean isAlphanumericStringOnly(final String string, final boolean ignoreSpaces) {
		return ignoreSpaces ? string.matches("[a-zA-Z0-9]*") : string.matches("[a-zA-Z0-9 ]*") ;
	}
	
	/** Replaces reserved words coming from the given <code>string</code> with data read from <code>parameters</code> map.<br>
	 *  Note: keys described inside the Map object must be the same written in the given <code>string</code>.<br>
	 *  Quick example:<br><br>
	 *  // Code<br><br>
	 *  <code>String string = "This is a $OBJECT_TYPE to be replaced with $MAP data!";</code><br>
	 *  <code>Map&lt;String,Object&gt; parameters = new HashMap&lt;String,Object&gt;();</code><br><br>
	 *  <code>parameters.put("$OBJECT_TYPE","string"); parameters.put("$MAP","map");</code><br><br>
	 *  <code>System.out.println( StringUtils.populate(string,parameters) );</code><br><br>
	 *  // Expected output<br><br>
	 *  This is a string to be replaced with map data!
	 *  @param string - string with reserved words in format $FOO_BAR.
	 *  @param parameters - A map containing keys and values to fill the given 'string'.
	 *  @return A new string with reserved words replaced by data coming from incoming the parameters map. */
	public static String populate(String string, Map<String, Object> parameters) {
	
		for (Map.Entry<String,Object> set: parameters.entrySet()) {
			
			Object o = set.getValue();
			
			if (o == null)
				System.err.println(":: NULL Parameter: " + set.getKey());
			else
				string = string.replace(set.getKey(),set.getValue().toString());
		}
	
		return string;
	}
	
	/** Converts the given string to a new HTML formatted string.
	 *  Basically replacing some characters like '<' to '&lt' and many more.
	 *  @param s - Plain text string
	 *  @return HTML formatted string. */
	public static String toHTML(String s) {
		
        StringBuilder builder = new StringBuilder();
        
        builder.append("<html>");
        
        boolean previousWasASpace = false;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                if (previousWasASpace) {
                    builder.append("&nbsp;");
                    previousWasASpace = false;
                    continue;
                }
                previousWasASpace = true;
            } else {
                previousWasASpace = false;
            }
            switch (c) {
                case '<':
                    builder.append("&lt;");
                    break;
                case '>':
                    builder.append("&gt;");
                    break;
                case '&':
                    builder.append("&amp;");
                    break;
                case '"':
                    builder.append("&quot;");
                    break;
                case '\n':
                    builder.append("<br>");
                    break;
                // We need Tab support here, because we print StackTraces as HTML
                case '\t':
                    builder.append("&nbsp; &nbsp; &nbsp;");
                    break;
                default:
                    builder.append(c);

            }
        }
        
        builder.append("</html>");
        
        String converted = builder.toString();
        String str = "(?i)\\b((?:https?://|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:\'\".,<>?«»“”‘’]))";
        Pattern patt = Pattern.compile(str);
        Matcher matcher = patt.matcher(converted);
        
        converted = matcher.replaceAll("<a href=\"$1\">$1</a>");
        
        return converted;
    }
	
	/** Converts a <code>string</code> to a URL link format.
	 *  @param string - a string
	 *  @return A URL encoded string.
	 *  @since 2.6, 30/APR/2021 */
	public static String toURL(final String string) {
		return URLEncoder.encode(string, StandardCharsets.UTF_8);
	}
	
	/** Safe implementation of String::trim method. It does not
	 *  throw exceptions when the given 'string' is null.
	 *  @param string - String
	 *  @return Trimmed string.*/
	public static String trim(final String string) {
		return (string == null) ? null : string.trim();
	}
	
	/** Removes leading zeroes from the given string.
	 *  @param string - String
	 *  @return A new string without leading zeroes. */
	public static String wipeLeadingZeros(final String string) {
		return string.replaceFirst("^0+(?!$)", "");
	}
	
	/** Removes multiple spaces between words in the given string.
	 *  @param string - String
	 *  @return A new string with only single spaces between words. */
	public static String wipeMultipleSpaces(final String string) {
		return string.replaceAll("\\s+"," ").trim();
	}
	
	/** Turns the special characters in the given string to normal ones.
	 *  basically removing accents from vowels and the letter 'ç' -> 'c'.
	 *  @param string - String
	 *  @return A new string without special characters in vowels an letter c. */
	public static String wipeSpecialCharacters(final String string) {
		
        return (string == null) ? string : Normalizer.normalize(string, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
	
	/** (EN) Class designed to handle common Brazilian strings like names, adresses and currency.<br>
	 *  (PT) Classe dedicada a manupulação de algumas strings clássicas do BR, tais como nomes, endereços e moeda.
	 *  @author Felipe André - felipeandresouza@hotmail.com 
	 *  @version 1.0, 17/SEP/2020 */
	public static class BR {
		
		/** Aplica a máscara de CEP em um 'cep' informado.
		 *  @param cep - CEP
		 *  @return Nova string contendo um CEP formatado com a máscara de CEP. */
		public static String formataCEP(String cep) {
			
			cep = extractNumbers(cep);
			
			if ((cep == null) || (cep.length() != 8))
				return null;
			
			return String.format("%s-%s",cep.substring(0,5),cep.substring(5));
		}
		
		/** Aplica a máscara de CNPJ em um 'cnpj' informado.
		 *  @param cnpj - CNPJ
		 *  @return Nova string contendo um CNPJ formatado com a máscara de CNPJ.
		 *  @since 2.4, 21/APR/2021 */
		public static String formataCNPJ(String cnpj) {
			
			cnpj = extractNumbers(cnpj);
			
			if ((cnpj == null) || (cnpj.length() != 14))
				return cnpj;
			
			return String.format("%s.%s.%s/%s-%s", cnpj.substring(0,2), cnpj.substring(2,5), cnpj.substring(5,8), cnpj.substring(8,12), cnpj.substring(12) );
		}
		
		/** Normaliza um nome, ou seja, converte suas iniciais em letra maiúscula,
		 *  exceto os pronomes (de, da, do, etc) e conectivos (e).
		 *  @param nome - Nome próprio
		 *  @return Nome normalizado. */
		public static String normaliza(final String nome) {
			
			// Se a entrada for nula, quebro o código aqui
			if (nome == null)
				return null;
			
			// Aqui já converto todo o nome pra caracteres minúsculos e crio um array de partes do nome
			String array[] = nome.toLowerCase().split(" ");
			String nova = "";
			
			try {
				
				// Iterando sobre as sub-partes do nome
				for (String parte: array)
					
					// Se algum dos pronomes ou conectivos abaixo for encontrado, deixo do jeito que está
					if (parte.equals("da") || (parte.equals("de")) || parte.equals("do") || parte.equals("e") || parte.equals("das") || parte.equals("dos"))
						nova += parte + " ";
					
					// senão, converto a primeira letra pra maiúscula
					else
						nova += Character.toUpperCase(parte.charAt(0)) + parte.substring(1) + " ";
				
			}
			
			// Se algum erro foi encontrado durante o processamento, simplesmente retorno o nome original
			catch (Exception exception) {
				return nome;
			}
			
			// Por fim, aparo as pontas deixadas pelo laço
			return trim(nova);
		}
		
		/** Converte um valor decimal para notação de moeda (R$) em extenso.<br>
		 *  Nota: este método converte números até 999 trilhões de reais.
		 *  @param valor - Valor a ser convertido em extenso
		 *  @return O texto por extenso referente ao valor informado. */
		public static String praReais(double valor) {
			
			// O código acaba aqui se o valor for zero
			if (valor == 0f)
				return("zero");
			
			long inteiro  = (long) Math.abs(valor); // parte inteira do valor
			double resto  = (valor - inteiro);      // parte fracionária do valor
			String valorS = String.valueOf(inteiro);
			
			// Valor limite suportado
			if (valorS.length() > 15)
				return("Erro: valor superior a 999 trilhões.");
			
			String s = "", saux, valorP;
		    String centavos = String.valueOf((int) Math.round(resto * 100));
		    
		    String[] unidade = {"", "um", "dois", "três", "quatro", "cinco",
		    					"seis", "sete", "oito", "nove", "dez", "onze",
		    					"doze", "treze", "quatorze", "quinze", "dezesseis",
		    					"dezessete", "dezoito", "dezenove"};
		    String[] centena = {"", "cento", "duzentos", "trezentos",
		    					"quatrocentos", "quinhentos", "seiscentos",
		    					"setecentos", "oitocentos", "novecentos"};
		    String[] dezena  = {"", "", "vinte", "trinta", "quarenta", "cinquenta",
		    					"sessenta", "setenta", "oitenta", "noventa"};
		    String[] qualificaS = {"", "mil", "milhão", "bilhão", "trilhão"};
		    String[] qualificaP = {"", "mil", "milhões", "bilhões", "trilhões"};
		    
		    // Definindo o extenso da parte inteira do valor
		    int n, unid, dez, cent, tam, i = 0;
		    boolean umReal = false, tem = false;
		    
		    // Retira do valor a 1a. parte, 2a. parte, por exemplo, para 123456789:
			// 1a. parte = 789 (centena)
			// 2a. parte = 456 (mil)
			// 3a. parte = 123 (milhões)
		    while (!valorS.equals("0")) {
		    	
		    	tam = valorS.length();
		    	
		    	if (tam > 3) {
		    		valorP = valorS.substring(tam-3, tam);
			        valorS = valorS.substring(0, tam-3);
		    	}
		    	else { // última parte do valor
		    		valorP = valorS;
			        valorS = "0";
		    	}
		    	
		    	if (!valorP.equals("000")) {
		    		
		    		saux = "";
		    		
		    		if (valorP.equals("100"))
		    			saux = "cem";
		    		
		    		else {
		    			
		    			n = Integer.parseInt(valorP, 10);  // para n = 371, tem-se:
		    			cent = (n / 100);                  // cent = 3 (centena trezentos)
		    			dez  = (n % 100) / 10;             // dez  = 7 (dezena setenta)
		    			unid = (n % 100) % 10;             // unid = 1 (unidade um)
		    			
		    			if (cent != 0)
		    				saux = centena[cent];
		    			
		    			if ((n % 100) <= 19) {
		    				if (saux.length() != 0)
		    					saux = saux + " e " + unidade[n % 100];
		    				else saux = unidade[n % 100];
		    			}
		    			
		    			else {
		    				if (saux.length() != 0)
		    					saux = saux + " e " + dezena[dez];
		    				else
		    					saux = dezena[dez];
		    				
		    				if (unid != 0) {
		    					if (saux.length() != 0)
		    						saux = saux + " e " + unidade[unid];
		    					else
		    						saux = unidade[unid];
		    				}
		    			}
		    		}
		    		
		    		if (valorP.equals("1") || valorP.equals("001")) {
		    			if (i == 0) // 1a. parte do valor (um real)
		    				umReal = true;
		    			else
		    				saux = saux + " " + qualificaS[i];
		    		}
		    		else if (i != 0)
		    			saux = saux + " " + qualificaP[i];
		    			if (s.length() != 0)
		    				s = saux + ", " + s;
		    			else
		    				s = saux;
		    	}
		    	
		    	if (((i == 0) || (i == 1)) && s.length() != 0)
		    		tem = true; // tem centena ou mil no valor
		    		i = i + 1;  // próximo qualificador: 1- mil, 2- milhão, 3- bilhão, ...
		    }
		    
		    if (s.length() != 0) {
		    	if (umReal)
		    		s = s + " real";
		    	else if (tem)
		    		s = s + " reais";
		    	else
		    		s = s + " de reais";
		    }
		    
		    // Definindo o extenso dos centavos do valor
		    if (!centavos.equals("0")) { // valor com centavos
		    	if (s.length() != 0)     // se não é valor somente com centavos
		    		s = s + " e ";
		    	if (centavos.equals("1"))
		    		s = s + "um centavo";
		    	else {
		    		n = Integer.parseInt(centavos, 10);
		    		if (n <= 19)
		    			s = s + unidade[n];
		    		else {               // para n = 37, tem-se:
		    			
		    			unid = n % 10;   // unid = 37 % 10 = 7 (unidade sete)
		    			dez = n / 10;    // dez  = 37 / 10 = 3 (dezena trinta)
		    			s = s + dezena[dez];
		    			
		    			if (unid != 0)
		    				s = s + " e " + unidade[unid];
		    			
		    		}
		    		
		    		s = s + " centavos";
		    	}
		    }
		    return s;
		}
		
	}
	
}
