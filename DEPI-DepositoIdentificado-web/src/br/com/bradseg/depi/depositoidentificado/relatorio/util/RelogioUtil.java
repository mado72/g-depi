package br.com.bradseg.depi.depositoidentificado.relatorio.util;


import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Calendar;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;

public class RelogioUtil {
	
	
	public static final String FMT_DECIMAL_2   = "00";
	public static final String FMT_DECIMAL_3   = "000";
	public static final String FMT_DECIMAL_4   = "0000";
	public static final String FMT_DECIMAL_6   = "000000";
	public static final String FMT_DECIMAL_7   = "0000000";
	public static final String FMT_DECIMAL_9   = "000000000";
	public static final String FMT_DECIMAL_100 = "0000000000";

	public static final char NUM_2_CH_A = '1';
	public static final char NUM_2_CH_B = '2';
	public static final char NUM_2_CH_C = '3';
	public static final char NUM_2_CH_D = '4';
	public static final char NUM_2_CH_E = '5';
	public static final char NUM_2_CH_F = '6';
	public static final char NUM_2_CH_G = '7';
	public static final char NUM_2_CH_H = '8';
	public static final char NUM_2_CH_I = '9';
	public static final char NUM_2_CH_J = '0';
	
	public static final char CH_A_2_NUM = 'A';
	public static final char CH_B_2_NUM = 'B';
	public static final char CH_C_2_NUM = 'C';
	public static final char CH_D_2_NUM = 'D';
	public static final char CH_E_2_NUM = 'E';
	public static final char CH_F_2_NUM = 'F';
	public static final char CH_G_2_NUM = 'G';
	public static final char CH_H_2_NUM = 'H';
	public static final char CH_I_2_NUM = 'I';
	public static final char CH_J_2_NUM = 'J';
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RelogioUtil.class);

	
	
	public static String retornaMimeType(String path){
		
		String mime = "";
		
		if (path.endsWith(".doc")){
			mime = ConstantesDEPI.MIME_TYPE_DOC;
		}
		else if(path.endsWith(".docx")){
			mime = ConstantesDEPI.MIME_TYPE_DOCX;
		}
		else if(path.endsWith(".xls")){
			mime = ConstantesDEPI.MIME_TYPE_XLS;
		}
		else if(path.endsWith(".xlsx")){
			mime = ConstantesDEPI.MIME_TYPE_XLSX;
		}
		//O uso do .jrxml é porque esse documento será salvo em pdf
		else if( (path.endsWith(".pdf")) || path.endsWith(".jrxml") ){
			mime = ConstantesDEPI.MIME_TYPE_PDF;
		}
		else if( (path.endsWith(".htm")) || path.endsWith(".html") ){
			mime = ConstantesDEPI.MIME_TYPE_HTML;
		}
		else if( (path.endsWith(".jpg")) || path.endsWith(".jpeg") ){
			mime = ConstantesDEPI.MIME_TYPE_JPEG;
		}
		else if(path.endsWith(".gif")){
			mime = ConstantesDEPI.MIME_TYPE_GIF;
		}
		else if(path.endsWith(".png")){
			mime = ConstantesDEPI.MIME_TYPE_PNG;
		}
		else if(path.endsWith(".bmp")){
			mime = ConstantesDEPI.MIME_TYPE_BMP;
		}

		return mime;
		
	}
	public static String retornaMesAtualExtenso(){
		
		String mesExt = "";
		int mes = Calendar.getInstance().get(Calendar.MONTH);
		
		switch (mes) {

			case Calendar.JANUARY: 
				mesExt = "Janeiro";
				break;

			case Calendar.FEBRUARY: 
				mesExt = "Fevereiro";
				break;
			
			case Calendar.MARCH: 
				mesExt = "Março";
				break;
			
			case Calendar.APRIL: 
				mesExt = "Abril";
				break;
			
			case Calendar.MAY: 
				mesExt = "Maio";
				break;
			
			case Calendar.JUNE: 
				mesExt = "Junho";
				break;
			
			case Calendar.JULY: 
				mesExt = "Julho";
				break;
			
			case Calendar.AUGUST: 
				mesExt = "Agosto";
				break;
			
			case Calendar.SEPTEMBER: 
				mesExt = "Setembro";
				break;
			
			case Calendar.OCTOBER: 
				mesExt = "Outubro";
				break;
			
			case Calendar.NOVEMBER: 
				mesExt = "Novembro";
				break;
			
			case Calendar.DECEMBER: 
				mesExt = "Dezembro";
				break;

			default:
			break;
		}
		
		return mesExt;
		
	}
	
	
	
	
	public static long calcularPrazo(Date inicio, Date fim) {
		long prazoMiliseg = fim.getTime() - inicio.getTime();
		// Quantidade de milissegundos em um dia 
		int tempoPorDia = 1000 * 60 * 60 * 24;

		return prazoMiliseg / tempoPorDia;
	}
	
	public static java.sql.Date dateToSql(Date data) {
		return new java.sql.Date(data.getTime());
	}

	public static String formataTimestampP8(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return sdf.format(data);
	}
	public static String formataTimestamp(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(data);
	}

	// Verifica se a String data de nascimento digitada é uma data válida
	public static Date validaData(String dataStr) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);

			return sdf.parse(dataStr);
		} catch (ParseException pe) {
			LOGGER.error(pe.getMessage(), pe);
			return null;
		}
	}
	
	public static String bigDecimalToString(BigDecimal valor) {
		/*Transformando em 2 casas decimais*/
		DecimalFormat fmt = new DecimalFormat("0.00"); //limita o número de casas decimais     
		String string = fmt.format(valor);
		String[] part = string.split("[,]");
		return part[0] + "." + part[1];
	}
	public static String decimalToString(Double valor) {
		/*Transformando em 2 casas decimais*/
		DecimalFormat fmt = new DecimalFormat("0.00"); //limita o número de casas decimais     
		String string = fmt.format(valor);
		String[] part = string.split("[,]");
		return part[0] + "." + part[1];
	}

	public static String removeAcentos(String str) {
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = str.replaceAll("[^\\p{ASCII}]", "");
		return str;
	}
	
	public static String formataDate(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(data);
	}
	
	public static Double stringToDouble(String valor) {
		valor = valor.replaceAll("[.]", "").replaceAll("[,]", ".");
		return Double.parseDouble(valor);
	}
	public static String doubleToString(Double valor){
		NumberFormat nf = new DecimalFormat("###,##00.00");   
		 String s = nf.format(valor);   
		  return s.replace('.', ',');  
	}
	public static BigDecimal stringToBigDecimal(String valor) {
		valor = valor.replaceAll("[.]", "").replaceAll("[,]", ".");
		return new BigDecimal(valor);
	}
	
	public static String getPathAnexos() {
    	ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/AppContext.xml");
    	URL uploadFolder = (URL) context.getBean("uploadFolder");
		String url = uploadFolder.toString();
		//if (url.startsWith("file://")) {
			url = url.substring(7);
		//}
		if (!url.endsWith("/")) {
			url = url.concat("/");
		}
		return url;
	}
	
	public static String formataTimestampParaCics(Date timeStamp) {
		String dataFormatada =  timeStamp.toString();
		dataFormatada = dataFormatada.replaceAll(" ", "-");
		dataFormatada = dataFormatada.replaceAll(":", ".");
		return dataFormatada;
	}
	
	/**
	 * 
	 * Método: formataTimestampFuncaoOracle
	 * Objetivo: Monta uma string preparada com uma função resposável por formatar
	 * 			 um timestamp oriundo do DB2 para o formato do Oracle (Banco de dados usado no P8) 
	 * @param timestamp String
	 * @return String
	 */
	public static String formataTimestampFuncaoOracle(String timestamp){
		
		StringBuffer sql = new StringBuffer(150);
					 sql.append("SELECT SUBSTR ( TO_CHAR ( ");
					 sql.append(timestamp);
					 sql.append(", 'yyyy-mm-dd-hh24.mi.ssxff'), 1, 19) || '.' || SUBSTR( TO_CHAR ( ");
					 sql.append(timestamp);
					 sql.append(", 'yyyy-mm-dd-hh24.mi.ssxff'), 21, 6) FROM DUAL ");
		return sql.toString();
		
	}
	
	// Verifica se a String data de nascimento digitada é uma data válida
	public static Date formataDataP8(String dataStr) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
			sdf.setLenient(false);
			if (dataStr != null) {
				dataStr = dataStr.substring(0, 19);
			}
			return sdf.parse(dataStr);
		} catch (ParseException pe) {
			LOGGER.error(pe.getMessage(), pe);
			return null;
		}
	}
	
	
	/**
	 * 
	 * Método formataDataStrDb2ForDateP8
	 * Objetivo do Método: Converte uma data(String) do DB2 em um formato Date do Java.
	 * @param String dataStr
	 * @return Date
	 */
	public static Date formataDataStrDb2ForDateP8(String dataStr){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss.SSS");
		sdf.setLenient(false);
		
		// O Java somente suporta os milisegundos entre 1000 (0 a 999), por isso só conseguimos formatar até 3º dígito do milisegundo. 
		if (dataStr.length()>23){
			dataStr = dataStr.substring(0, 23);
		}
		
		try {
			
			//synchronized (sdf){
				Date aux = sdf.parse(dataStr);					
				return  aux;
			//}

		} 
		catch (Exception pe) {
			LOGGER.error(pe.getMessage());
			return null;
		}
	}
	
	
	/**
	 * 
	 * Método:   formataTimestampParaOracle
	 * Objetivo: Altera uma string que representa o timestamp no formato DB2 e retorna esta em formato
	 * 			 timestamp Oracle.
	 * @param    timestampDb2 String
	 * @return   String
	 */
	public static String formataTimestampParaOracle(String timestampDb2){
		
		//Formato DB2:     yyyy-mm-dd-hh24.mi.ss.ssssss  --- Ex: '2012-12-11-13.31.15.123456'
		//Formato Oracle   dd/mm/yyyy hh24:mi:ss,ssssss  --- Ex: '11/12/2012 13:31:15,123456'
		
		String ano  = timestampDb2.substring(0,  4);  		//Ex: 2012
		String mes  = timestampDb2.substring(5,  7);  		//Ex: 12
		String dia  = timestampDb2.substring(8,  10);  		//Ex: 11
		String horaMinSeg = timestampDb2.substring(11, 19); //Ex: 13.31.15
		String miliSeg = timestampDb2.substring(20, 26);	//Ex: 123456
		horaMinSeg = horaMinSeg.replace(".", ":"); 			//Ex: 13:31:15
		String timeOracle = ( dia + "/" + mes + "/" + ano + " " + horaMinSeg + "," + miliSeg );
		
		return timeOracle;
	}
	
	/**
	 * Método: javaDateToSqlTimestamp
	 * Objetivo: Transforma uma data do padrão Java para um String no formato Timestamp do DB2.
	 * @param data Date
	 * @return String
	 */
	public static String javaDateToSqlTimestamp(Date data) {
		java.sql.Timestamp dataFormatada = new java.sql.Timestamp(data.getTime());
		String novaData = dataFormatada.toString();
		novaData = novaData.replace(" ", "-");
		novaData = novaData.replace(":", ".");
		return  novaData;
	}
	
	public static String cidUsuar2NUM(String cidusuar){
		String resut = cidusuar.toUpperCase();
		if(!isNumeric(cidusuar) ){
			switch (resut.charAt(0)) {
			case 'A':resut =  NUM_2_CH_A + resut.substring(1);				
				break;
			case 'B':resut =  NUM_2_CH_B + resut.substring(1);				
				break;
			case 'C':resut =  NUM_2_CH_C + resut.substring(1);				
				break;
			case 'D':resut =  NUM_2_CH_D + resut.substring(1);				
				break;
			case 'E':resut =  NUM_2_CH_E + resut.substring(1);				
				break;
			case 'F':resut =  NUM_2_CH_F + resut.substring(1);				
				break;
			case 'G':resut =  NUM_2_CH_G + resut.substring(1);				
				break;
			case 'H':resut =  NUM_2_CH_H + resut.substring(1);				
				break;
			case 'I':resut =  NUM_2_CH_I + resut.substring(1);				
				break;
			default:resut =  NUM_2_CH_J + resut.substring(1);
				break;
			}
		}
		
		return  formataCodigo(resut, FMT_DECIMAL_7);
	}
	
	public static boolean isNumeric (String str){
		boolean result = Boolean.TRUE;
		try {
			Integer.parseInt(str);
			
		} catch (Exception e) {
			result = Boolean.FALSE;
		}
		return result;
	}
	
	public static String formataCodigo(String valor,String formato){
		NumberFormat format = new DecimalFormat(formato);
    	String codFormatado = format.format(new Integer(valor));
		return codFormatado;
	}

	
	public static BigDecimal nullToZeroBigDecimal(BigDecimal valor ){
		BigDecimal result = new BigDecimal(0);
		if (valor != null){
			result = valor;
		}
		
		return result;
	}
	
	public static Integer nullToZeroInteger(Integer valor ){
		Integer result = new Integer(0);
		if (valor != null){
			result = valor;
		}
		
		return result;
	}
	
	public static String nullToZeroString(String valor ){
		String result = "0";
		if (valor != null){
			if(!valor.trim().equals("")){
				result = valor;
			}
		}
		
		return result;
	}
	
	public static String formatarCpfCnpjSoNumeros(String str) {
		str = str.replace("/", "");
		str = str.replace("-", "");
		str = str.replace(".", "");
		return str;
	}
	
	public static String formataDataHora(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return sdf.format(data);
	}
	
	public static Date formataDataHoraTraco(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
		sdf.format(data);
		
		return data;
	}
	
	public static String formataDataPPE(String data) {
		Date date = null;
		String dataFormatada = null;
		try {
			date = new SimpleDateFormat("yyyyMMdd").parse(data);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dataFormatada = sdf.format(date);
		} catch (ParseException e) {
			LOGGER.error("Não foi possivel converter a data.");
		}  
		
		return dataFormatada;
	}
	
	/*
	public static String formataCPFPPE(Long cpfLong) {
		String cpf = cpfLong.to
		String subCPF1 = cpf.substring(3);
		String subCPF2 = cpf.substring(3,6);
		String subCPF3 = cpf.substring(6,9);
		String digito = cpf.substring(9,11);
		
		return subCPF1.concat(".").concat(subCPF2).concat(".").concat(subCPF3).concat("-").concat(digito);
	}*/
	
	public static String formataValorParaMoedaLocal(double valor, Locale regiao) {
		
		String valorFormatadoEmMoeda = null;
		
		if (regiao == null) {
			regiao = new Locale("pt", "BR");
		}
		
		try {
			DecimalFormat formatacaoMoeda = new DecimalFormat("###,###,###,###,##0.00", new DecimalFormatSymbols(regiao));
			formatacaoMoeda.setMinimumFractionDigits(2); 
			formatacaoMoeda.setParseBigDecimal(true);
			
			valorFormatadoEmMoeda = formatacaoMoeda.format(valor);
		} catch (Exception e) {
			valorFormatadoEmMoeda = null;
		}
		
		return valorFormatadoEmMoeda;
	}
	
	
	public static String obterDataCorrenteFormatada(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return sdf.format(new Date());
	}


	public static String obterHoraCorrenteFormatada(){
		SimpleDateFormat sdf = new SimpleDateFormat("HH.mm.ss");
		return sdf.format(new Date());

	}
	public static String obterSequencial() {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmm");
		return sdf.format(new Date());
	}
}