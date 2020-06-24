package com.phill.libs;

import javax.swing.filechooser.FileNameExtensionFilter;

public class FileFilters {

	public static final FileNameExtensionFilter XLS = new FileNameExtensionFilter("Planilha do Excel 2003 (.xls)","xls");
	public static final FileNameExtensionFilter CSV = new FileNameExtensionFilter("Contatos do Google (.csv)","csv");
	public static final FileNameExtensionFilter BSF   = new FileNameExtensionFilter("Arquivo de Compilação (.bsf)", "bsf");
	public static final FileNameExtensionFilter XLSX  = new FileNameExtensionFilter("Planilha do Excel 2007/2010/2013 (.xlsx)","xlsx");
	public static final FileNameExtensionFilter CSV_N = new FileNameExtensionFilter("Texto Separado por Vírgula (.csv)", "csv");
	public static final FileNameExtensionFilter TSV   = new FileNameExtensionFilter("Texto Separado por Tabulação (.tsv)", "tsv");
	public static final FileNameExtensionFilter SISTAC_SEND = new FileNameExtensionFilter("Arquivo de Envio Sistac (.txt)","txt");
	public static final FileNameExtensionFilter SISTAC_RETV = new FileNameExtensionFilter("Arquivo de Retorno Sistac (.txt)","txt");
	
	public static final FileNameExtensionFilter PDF = new FileNameExtensionFilter("Documento PDF (.pdf)","pdf");
	public static final FileNameExtensionFilter EPS = new FileNameExtensionFilter("Documento EPS (.eps)","eps");
	public static final FileNameExtensionFilter JPG = new FileNameExtensionFilter("Imagem JPEG (.jpg)","jpg");
	public static final FileNameExtensionFilter SVG = new FileNameExtensionFilter("Imagem SVG (.svg)","svg");
	public static final FileNameExtensionFilter PNG = new FileNameExtensionFilter("Imagem PNG (.png)","png");
	
	public static final FileNameExtensionFilter MKV = new FileNameExtensionFilter("Matroska Video File (.mkv)","mkv");
	public static final FileNameExtensionFilter MP4 = new FileNameExtensionFilter("MP4 Video File (.mp4)","mp4");

	public static final FileNameExtensionFilter[] SISTAC_INPUT = new FileNameExtensionFilter[]{FileFilters.CSV_N,FileFilters.TSV,FileFilters.XLSX};
	
}
