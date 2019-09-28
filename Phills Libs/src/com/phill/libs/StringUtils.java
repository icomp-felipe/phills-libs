package com.phill.libs;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	/** Converte os atributos em branco para 'null' em uma String SQL */
	public static String blank_to_null(String query_sql) {
		return query_sql.replace("\"\"","null").replace("\"null\"","null");
	}
	
	public static String getResumedName(String name) {
		
		if (name == null)
			return null;
		
		String[] partes = name.split(" ");
		StringBuilder sb = new StringBuilder();
		
		sb.append(partes[0] + " ");
		sb.append(partes[1]);
			
		if (partes[1].length() <= 3)
			sb.append(" " + partes[2]);
		
		return sb.toString();
		
	}
	
	public static String removeLeadingZeros(String string) {
		return string.replaceFirst("^0+(?!$)", "");
	}
	
	public static String firstLetterLowerCase(String nome) {
		String original = nome;
		
		if (nome == null) return null;
		
		nome = nome.toLowerCase();
		String temp, aux[] = nome.split(" ");
		nome = "";
		try {
			for (int i=0; i<aux.length; i++) {
				
					temp = Character.toUpperCase(aux[i].charAt(0)) + aux[i].substring(1, aux[i].length());
					if (temp.equals("Da") || (temp.equals("De")) || temp.equals("Do") || temp.equals("E") || temp.equals("Das") || temp.equals("Dos"))
						temp = temp.toLowerCase();
					nome += (temp + " ");
					
			}
		}
		catch (Exception e) {
			return original;
		}
		return nome.trim();
	}
	
	public static boolean isOnlyAlfaString(String palavra) {
		return palavra.matches("^[\\p{L} ]+$");
	}
	
	public static String removeEspacosMultiplos(String palavra) {
		return palavra.replaceAll("\\s+"," ").trim();
	}
	
	public static String extraiAlfabeto(String string) {
		return string.replaceAll("[^A-Za-z]+", "").trim();
	}
	
	public static String removeCaracteresEspeciais(String acentuada) {
        if (acentuada == null)
            return acentuada;
        
        char[] acentuados    = new char[] { 'ç', 'á', 'à', 'ã', 'â', 'ä', 'é', 'è', 'ê', 'ë', 'í', 'ì', 'î', 'ï', 'ó', 'ò', 'õ', 'ô', 'ö', 'ú', 'ù', 'û', 'ü' };  
        char[] naoAcentuados = new char[] { 'c', 'a', 'a', 'a', 'a', 'a', 'e', 'e', 'e', 'e', 'i', 'i', 'i', 'i', 'o', 'o', 'o', 'o', 'o', 'u', 'u', 'u', 'u' };  
      
        for (int i = 0; i < acentuados.length; i++) {  
            acentuada = acentuada.replace(acentuados[i], naoAcentuados[i]); 
            acentuada = acentuada.replace(Character.toUpperCase(acentuados[i]), Character.toUpperCase(naoAcentuados[i]));  
        }
        
        return acentuada;  
    }
	
	public static String extraiNome(String nome) {
		
		String[] partes = StringUtils.firstLetterLowerCase(nome).split(" ");
		StringBuilder sb = new StringBuilder();
		
		sb.append(partes[0] + " ");
		sb.append(partes[1]);
		
		if (partes[1].length() <= 3)
			sb.append(" " + partes[2]);
		
		return sb.toString();
		
	}
	
	public static boolean multipleSpaceBetweenWords(String phrase) {
		int index = phrase.indexOf("  ");
		return (index != -1);
	}
	
	public static String detectSpaceBetweenWords(String phrase) {
		
		String retorno = null;
		int index = phrase.indexOf("  ");
		
		if (index != -1) {
			
			String first  = phrase.substring(0,index).trim();
			String second = phrase.substring(index).trim();
			
			retorno = String.format("Há múltiplos espaços entre \"%s\" e \"%s\"", first,second);
		}
		
		if (phrase.matches(".*\\d+.*"))
			retorno += "Há números cadastrados no nome do candidato!";
		
		return retorno;
	}
	
	public static String parseCPF(String cpf) {
		if ( (cpf == null) || (cpf.length() != 11))
			return cpf;
		return String.format("%s.%s.%s-%s",cpf.substring(0,3),cpf.substring(3,6),cpf.substring(6,9),cpf.substring(9));
	}
	
	public static String parsePIS(String pis) {
		
		if ( (pis == null) || (pis.length() != 11))
			return pis;
		
		return String.format("%s.%s.%s-%s",pis.substring(0,3),pis.substring(3,8),pis.substring(8,10),pis.substring(10));
	}
	
	/** Formata o PIS com sua máscara padrão. Retorna 'erro' caso o PIS seja nulo ou inválido */
	public static String parsePIS(String pis, String erro) {
		
		if ( (pis == null) || (pis.length() != 11))
			return pis;
		
		return String.format("%s.%s.%s-%s",pis.substring(0,3),pis.substring(3,8),pis.substring(8,10),pis.substring(10));
	}
	
	public static String parseCEP(String cep) {
		
		cep = extractNumbers(cep);
		
		if ((cep == null) || (cep.length() != 8))
			return null;
		
		return String.format("%s-%s",cep.substring(0,5),cep.substring(5));
	}
	
	public static String extractNumbers(String word) {
		return (word == null) ? null : word.replaceAll("\\D+","").trim();
	}
	

	public static String convertToHTML(String s) {
		
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
	
	public static String fixSpaces(String word) {
		return word.trim().replaceAll("\\s+", " ");
	}
	
	public static boolean detectSpaces(String word) {
		return word.matches(".*  .*") || word.matches(" .*");
	}

	public static String replaceWithParameters(String msg, Map<String, Object> parameters) {
		
		for (Map.Entry<String,Object> set: parameters.entrySet())
			msg = msg.replace(set.getKey(),set.getValue().toString());
		
		return msg;
	}

	/** Apara as pontas de uma string */
	public static String trim(String word) {
		return (word == null) ? null : word.trim();
	}
	
}
