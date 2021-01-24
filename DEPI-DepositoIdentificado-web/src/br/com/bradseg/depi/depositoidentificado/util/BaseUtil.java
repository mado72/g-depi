package br.com.bradseg.depi.depositoidentificado.util;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import br.com.bradseg.bsad.framework.core.exception.BusinessException;
import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.bsad.framework.core.message.Message;

/**
 * Classe Utilitária
 * @author Marcelo Damasceno
  */
public final class BaseUtil {

	/** Constante responsavel por armazenar LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(BaseUtil.class);
    /**
     * Construtor privado.
     */
    private BaseUtil() {
        super();
    }

    /**
     * Configura Statement - Para Statements com valores opcionais
     * @param indicativo - S ou N.
     * @param x - Object
     * @return Object
     * @throws SQLException - SQLException.
     */
    public static Object setNullQuandoOpcional(Object x, String indicativo) throws SQLException {
        if (!equalS(indicativo)) {
            return null;
        }
        return x;
    }

    /**
     * Configura Statement - Para Statements com valores opcionais
     * @param indicativo - boolean
     * @param x - Object
     * @return Object
     * @throws SQLException - SQLException.
     */
    public static Object setNullQuandoOpcional(Object x, boolean indicativo) throws SQLException {
        if (indicativo) {
            return null;
        }
        return x;
    }

    /**
     * É igual a S
     * @param str - String
     * @return booelan
     */
    public static boolean equalS(String str) {
        return "S".equals(str);
    }

    /**
     * Formata CPF
     * @param pValue - String.
     * @return String
     */
    public static String getCpfFormatado(String pValue) {
        /*
         * Tira qualquer formatação anterior.
         */
        pValue = retiraMascaraCNPJ(pValue);

        /*
         * Completa se faltar zeros a esquerda
         */

        while (pValue.length() < 11) {
            pValue = "0".concat(pValue);
        }
        return getValueMaskFormat("999.999.999-99", pValue, true);
    }

    /**
     * formatedStringToBigDecimal
     * @param value - String.
     * @return new BigDecimal
     */
    public static BigDecimal stringToBigDecimal(String value) {
        if (isNZB(value)) {
            return BigDecimal.valueOf(0);
        }
        return new BigDecimal(value.replace(".", "").replace(",", "."));
    }

    /**
     * Formata CPF
     * @param pValue - String.
     * @return String
     */
    public static String getCnpjFormatado(String pValue) {

        /*
         * Tira qualquer formatação anterior.
         */
        pValue = retiraMascaraCNPJ(pValue);

        /*
         * Completa se faltar zeros a esquerda
         */
        while (pValue.length() < 14) {
            pValue = "0".concat(pValue);
        }

        return getValueMaskFormat("##.###.###/####-##", pValue, true);
    }

    /**
     * Formata
     * @param pMask - String
     * @param pValue - String
     * @param pReturnValueEmpty - boolean
     * @return String
     */
    public static String getValueMaskFormat(String pMask, String pValue, boolean pReturnValueEmpty) {

        /*
         * Verifica se se foi configurado para nao retornar a mascara se a string for nulo ou vazia se nao retorna somente a
         * mascara.
         */
        if (pReturnValueEmpty && (pValue == null || pValue.trim().equals(""))) {
            return "";
        }

        /*
         * Substituir as mascaras passadas como 9, X, * por # para efetuar a formatcao
         */
        pMask = pMask.replaceAll("9", "#");
        pMask = pMask.toUpperCase().replaceAll("X", "#");

        /*
         * Formata valor com a mascara passada
         */
        for (int i = 0; i < pValue.length(); i++) {
            pMask = pMask.replaceFirst("#", pValue.substring(i, i + 1));
        }

        /*
         * Subistitui por string vazia os digitos restantes da mascara quando o valor passado é menor que a mascara
         */
        return pMask.replaceAll("#", "");
    }

    /**
     * Método Singleton de BaseUtil
     * @return Retorna instância Singleton de BaseUtil
     */
    public static BaseUtil getInstance() {
        return new BaseUtil();
    }

    /**
     * Envelope para o if de lançamento de Exception.
     * @param b - boolean.
     * @param chave - String.
     * @param msg - String.
     * @throws IntegrationException - IntegrationException.
     */
    public static void assertTrueThrowException(boolean b, String chave, List<Message> msg) throws IntegrationException {
        if (b) {
            throw new BusinessException(msg);
        }
    }
    /**
     * Envelope para o if de lançamento de Exception.
     * @param b - boolean.
     * @param chave - String.
     * @param msg - String.
     * @throws IntegrationException - IntegrationException.
     */
    public static void assertTrueThrowException(boolean b, String chave, String msg) throws IntegrationException {
        if (b) {
            throw new BusinessException(msg);
        }
    }    

    /**
     * Validar Parâmetro.
     * @param param - String.
     * @param valor - Object.
     * @param nomeParametro - String.
     * @throws IntegrationException - IntegrationException.
     */
    public static void validarParametro(String param, Object valor, String nomeParametro) throws IntegrationException {
        assertTrueThrowException(ConstantesDEPI.CODIGO_SIM.equals(param) && isNZB(valor),
            ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, nomeParametro);
    }

    /**
     * Validar Parâmetro.
     * @param valor - Object.
     * @param nomeParametro - String.
     * @throws IntegrationException - IntegrationException.
     */
    public static void validarParametro(Object valor, String nomeParametro) throws IntegrationException {
        validarParametro(ConstantesDEPI.CODIGO_SIM, valor, nomeParametro);

    }

    /**
     * Permite a abstração das verificações de preechimento de propriedades e estado válido para acesso à objetos. Quando uma
     * propriedade for refatorada não haverá necessidade de alterar em várias partes as verificações incluidas neste método.
     * @author Fábio Henrique fabio.almeida@cpmbraxis.com NZB - Null; Zero; String Zero Sequencies; Blank; Método utilitário para
     * @param valor - objeto a ser verificado.
     * @return um boolean com true (se o objeto é nulo ou vazio) ou false (se o objeto é diferente de nulo ou vazio)
     */
    @SuppressWarnings("rawtypes")
	public static boolean isNZB(Object valor) {

        if (valor == null) {
            return true;
        }
        if (valor instanceof String) {
            if (((String) valor).trim().length() == 0) {
                return true;
            }
            // if (((String) valor).matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
            if (((String) valor).matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
                return Double.parseDouble(prepareStringToNumber(valor.toString())) <= 0.0;
            }
        }
        if (valor instanceof Collection || valor instanceof List) {
            return ((Collection) valor).isEmpty();
        }

        if (valor instanceof Double || valor instanceof Integer || valor instanceof Long || valor instanceof Float
            || valor instanceof Short) {
            return !(Double.parseDouble(valor.toString()) >= 0.1);
        }

        if (valor instanceof BigDecimal) {
            return ((BigDecimal) valor).doubleValue() <= 0;
        }

        return false;
    }

    /**
     * Método utilitário para verificar se um objeto é nulo ou vazio.
     * @param valor - String
     * @return um boolean com true (se o objeto é nulo ou vazio) ou false (se o objeto é diferente de nulo ou vazio)
     */
    public static boolean isSorN(String valor) {
        if (isNZB(valor)) {
            return false;
        } else {
            if (!ConstantesDEPI.CODIGO_NAO.equals(valor) && !ConstantesDEPI.CODIGO_SIM.equals(valor)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Método utilitário para verificar se um objeto maior que o esperado Retorna true se forma maior que o parametro
     * @param valor - Object
     * @param size - long
     * @return boolean
     */
    @SuppressWarnings("rawtypes")
	public static boolean isGreater(Object valor, long size) {
        if (isNZB(valor)) {
            return false;
        } else {

            if (valor instanceof String) {
                return ((String) valor).trim().length() > size;
            }
            if (valor instanceof Collection) {
                return ((Collection) valor).size() > size;
            }

            if (valor instanceof Double || valor instanceof Integer || valor instanceof Long || valor instanceof Float
                || valor instanceof Short) {
            	
                return Double.parseDouble(valor.toString()) > size;
            }
        }
        return false;
    }

    /**
     * Método utilitário para verificar se um objeto menor que o esperado
     * @param valor - Object
     * @param size - long
     * @return boolean
     */
    @SuppressWarnings("rawtypes")
	public static boolean isLess(Object valor, long size) {
        if (isNZB(valor)) {
            return false;
        } else {

            if (valor instanceof String) {
                return ((String) valor).trim().length() < size;
            }
            if (valor instanceof Collection) {
                return ((Collection) valor).size() < size;
            }

            if (valor instanceof Double || valor instanceof Integer || valor instanceof Long || valor instanceof Float
                || valor instanceof Short) {
                return Double.parseDouble(valor.toString()) < size;
            }
        }
        return false;
    }

    /**
     * NZB - Null; Zero; Blank Método utilitário para verificar se um objeto é nulo ou vazio.
     * @param valor - objeto a ser verificado.
     * @return um boolean com true (se o objeto é nulo ou vazio) ou false (se o objeto é diferente de nulo ou vazio)
     */
    public static boolean isNZB(double valor) {
        return isNZB((Object) valor);
    }

    /**
     * Metodo utilitário pra converter uma objeto String para um objeto tipo Date.
     * @param valor - Valor a ser convertido.
     * @param pattern - O pattern da data.
     * @return Date - A data gerada.
     */
    public static Date parserStringToDate(String valor, String pattern) {

        Date objData = null;

        if (valor == null || ConstantesDEPI.VAZIO.equals(valor.trim())) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        format.setLenient(false);
        try {
            objData = format.parse(valor.trim());
            return objData;
        } catch (ParseException e) {
        	LOGGER.error(e);
            return objData; // Objeto objData será nulo.
        }
    }

    /**
     * Metodo utilitário pra converter uma objeto String para um objeto tipo SQL Date.
     * @param valor - Valor a ser convertido.
     * @return Date - A data gerada.
     */
    public static java.sql.Date parserStringToSQLDate(String valor) {
        return new java.sql.Date(parserStringToDate(valor, "dd/MM/yyyy").getTime());
    }

    /**
     * Metodo utilitário pra converter um objeto SQL Date em uma String com o formato informado.
     * @param data - Date a ser convertido.
     * @param pattern - O pattern da data.
     * @return String - A data formatada.
     */
    public static String parserSQLDateToString(java.sql.Date data, String pattern) {
        return new SimpleDateFormat(pattern).format(java.sql.Date.valueOf(data.toString()));
    }

    /**
     * Parse Double
     * @param valor - String.
     * @return double.
     */
    public static double parseDouble(final String valor) {
        if (isNZB(valor)) {
            return 0;
        }
        return Double.parseDouble(valor.replace(",", "").replace(".", ""));
    }

    /**
     * Método responsável por verificar se um objeto é nulo, caso sim retorna uma strig vazia.
     * @param obj - objeto a ser verificado.
     * @return Object - o proprio objeto se for diferente de nulo ou vazio se igual a nulo.
     */
    public static Object blankIfNull(Object obj) {
        if (obj == null) {
            new Object();
        } else {
            if (isNZB(obj)) {
                if (obj instanceof String) {
                    return "";
                }
                if (obj instanceof Double || obj instanceof Integer || obj instanceof Long || obj instanceof Float
                    || obj instanceof Short) {
                    return 0;
                }
            }
        }
        return obj;
    }

    /**
     * Método responsável por verificar se um objeto é nulo, caso sim retorna uma strig vazia.
     * @param str - objeto a ser verificado.
     * @return Object - o proprio objeto se for diferente de nulo ou vazio se igual a nulo.
     */
    public static String[] blankIfNull(String... str) {
        if (isNZB(str)) {
            return new String[0];
        } else {
            return str;
        }
    }

    /**
     * Método responsável por verificar se um objeto é nulo, caso sim retorna uma strig vazia.
     * @param str - objeto a ser verificado.
     * @return Object - o proprio objeto se for diferente de nulo ou vazio se igual a nulo.
     */
    public static String zeroIfStringNull(String str) {
        if (isNZB(str)) {
            return "0";
        } else {
            return str;
        }
    }

    /**
     * Método auxiliar que retira a mascara do CNPJ.
     * @param str Cnpj com máscara que será desformatado
     * @return String
     */
    public static String retiraMascaraCNPJ(final String str) {
        if (isNZB(str)) {
            return "";
        }
        return str.replaceAll("\\.", "").replaceAll("/", "").replaceAll("-", "");
    }

    /**
     * Remove alguns caracteres especiais
     * @param str String
     * @return String
     */
    public static String prepareStringToNumber(final String str) {
        return str.replaceAll("\\.", "").replaceAll("/", "").replaceAll("-", "");
    }

    /**
     * Recebe uma string no formato hh:mm:ss e verifica se é uma hora válida
     * @return boolean
     * @param hhmmss valor a ser preenchido
     */
    public static boolean validarHora(final String hhmmss) {
        int hor;
        int min;
        int seg;

        try {
            hor = Integer.parseInt(hhmmss.substring(0, 2));
            min = Integer.parseInt(hhmmss.substring(3, 5));
            seg = Integer.parseInt(hhmmss.substring(6));
        } catch (NumberFormatException nfe) {
        	LOGGER.error(nfe);
            return false;
        }

        if (hor < 0 || hor > 24) {
            return false;
        }
        if (min < 0 || min > 59) {
            return false;
        }
        if (seg < 0 || seg > 59) {
            return false;
        }
        return true;
    }

    /**
     * Recebe uma string no formato dd/mm/yyyy e verifica se é uma data válida
     * @return boolean
     * @param ddmmyyyy a ser preenchido
     */

    public static boolean validarData(final String ddmmyyyy) {
        int dia;
        int mes;
        int ano;

        try {
            dia = Integer.parseInt(ddmmyyyy.substring(0, 2));
            mes = Integer.parseInt(ddmmyyyy.substring(3, 5));
            ano = Integer.parseInt(ddmmyyyy.substring(6));
        } catch (NumberFormatException e) {
        	LOGGER.error(e);
            return false;
        }

        if (mes < 1 || mes > 12) {
            return false;
        }
        if (dia < 1 || dia > 31) {
            return false;
        }
        if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && (dia > 30)) {
            return false;
        }
        if ((((ano % 4) == 0) && (mes == 2)) && (dia > 29)) {
            return false;
        }
        if ((mes == 2) && (dia > 28)) {
            return false;
        }
        return true;
    }

    /**
     * Substitui TODAS as occorencias de uma String alvo por uma String de substituicao dentro da string principal eg: StringBuffer
     * sb = new StringBuffer("10 20 30 10 50 60 10"); String alvo = "10"; String substituicao = "07";
     * StringUtils.replace(sb,alvo,substituicao); // sb = "07 20 30 07 50 60 07"
     * @param buffer java.lang.StringBuffer
     * @param find java.lang.String
     * @param replace java.lang.String
     */
    public static void replaceString(final StringBuffer buffer, final String find, final String replace) {
        String strBuff = buffer.toString();
        int i = strBuff.indexOf(find);
        while (i != -1) {
            buffer.replace(i, i + find.length(), replace);
            strBuff = buffer.toString();
            i = strBuff.indexOf(find);
        }
    }

    /**
     * Substitui TODAS as ocorrências de uma String alvo por uma String de substituição dentro da string principal eg: StringBuffer
     * sb = new StringBuffer("10 20 30 10 50 60 10"); String alvo = "10"; String substituição = "07";
     * StringUtils.replace(sb,alvo,substituição); // sb = "07 20 30 07 50 60 07"
     * @param buffer java.lang.StringBuffer
     * @param find java.lang.String
     * @param replace java.lang.String
     */
    public static void sliceString(final StringBuffer buffer, final String find, final String replace) {
        String strBuff = buffer.toString();
        int i = strBuff.indexOf(find);
        while (i != -1) {
            buffer.replace(i, i + find.length(), replace);
            strBuff = buffer.toString();
            i = strBuff.indexOf(find);
        }
    }

    /**
     * Verifica se o valor de um objeto corresponde a um campo vazio de uma tabela
     * @return boolean valor do objeto corresponde a campo vazio?
     * @param string java.lang.String string
     */
    public static boolean vazio(String string) {
        return (string == null) || ("".equals(string.trim()));
    }

    /**
     * Método que compara as datas verificando se a Data Inicial é maior do que a Data Final.
     * @param dIni - parâmetro da data inicial.
     * @param dFim - parâmetro da data final.
     * @return boolean - retorna um booleano.
     * @throws IntegrationException IntegrationException.
     */
    public static boolean compararSeDataInicialEMaiorQueFinal(Date dIni, Date dFim) throws IntegrationException {
        return dIni.getTime() > dFim.getTime();

    }

    /**
     * Método que compara as datas verificando se a Data Inicial é maior do que a Data Final.
     * @param data - parâmetro da data inicial.
     * @return boolean - retorna um booleano.
     * @throws IntegrationException IntegrationException.
     */
    public static boolean verificarSeDataEMaiorQueDataCorrente(Date data) throws IntegrationException {
        return data.getTime() > new Date().getTime();
    }

    /**
     * Método que compara as datas verificando se a Data Inicial é maior do que a Data Final.
     * @param dIni - parâmetro da data inicial.
     * @param dFim - parâmetro da data final.
     * @return boolean - retorna um booleano.
     */
    public static boolean compararSeDataInicialEMaiorQueFinal(String dIni, String dFim) {

        boolean retorno = false;

        String[] newDataIni = dIni.split("\\/");
        String[] newDataFim = dFim.split("\\/");

        String dataIni = newDataIni[2] + newDataIni[1] + newDataIni[0];
        String dataFim = newDataFim[2] + newDataFim[1] + newDataFim[0];

        if (Integer.valueOf(dataIni) > Integer.valueOf(dataFim)) {
            retorno = true;
        }

        return retorno;
    }

    /**
     * Método responsável por converter um data do Tipo Date para XMLGregorianCalendar.
     * @param date Data do java a ser passada para o método.
     * @return xmlDate Data já convertida que será enviada para o WebService.
     * @throws IntegrationException Qualquer erro de integração.
     */
    public static XMLGregorianCalendar dateToXMLDate(Date date) throws IntegrationException {
        XMLGregorianCalendar xmlDate;
        try {
            Calendar c = new GregorianCalendar();
            c.setTime(date);

            int month = c.get(Calendar.MONTH) + 1;
            int day = c.get(Calendar.DAY_OF_MONTH);
            int year = c.get(Calendar.YEAR);

            DatatypeFactory df = DatatypeFactory.newInstance();

            xmlDate = df.newXMLGregorianCalendarDate(year, month, day, c.getTimeZone().getOffset(date.getTime())
                / (60 * 60 * 1000));
        } catch (DatatypeConfigurationException dce) {
        	LOGGER.error(dce);
            throw new IntegrationException(dce);
        }
        return xmlDate;
    }

    /**
     * Retira a máscara de data.
     * @param date Data a ser passada para o método.
     * @return newDate Data já formatada.
     */
    public static String cleanDateMask(String date) {

        String newDate = date.replace("/", "");

        return newDate;
    }

    /**
     * Método responsável por converter um data do Tipo Date para Calendar.
     * @param date Data do java a ser passada para o método.
     * @return calendar Data já convertida que será enviada para o WebService.
     */
    public static Calendar dateToCalendar(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar;
    }
    
    public static String concatenarComHifen(String arg0, String... args) {
		return concatenar(" - ", arg0, args);
	}

	public static String concatenar(String separador, String arg0, String... args) {
		StringBuilder sb = new StringBuilder(arg0);
		
		for (String arg: args) {
			sb.append(separador).append(arg);
		}
		
		return sb.toString();
	}

	/**
	 * Retornar o recurso de mensagens
	 * @return Retorna o recurso de propriedades
	 */
	public PropertyResourceBundle getResources() {
	    return (PropertyResourceBundle) ResourceBundle.getBundle(ConstantesDEPI.PATHCONFIG);
	}

	/**
     * Retornar mensagem
     * @param chave Chave da mensagem
     * @param params Parametros da mensagem
     * @return Retorna a mensagem transformada
     */
    public String getMensagem(String chave, String... params) {
        String mensagem = getResources().getString(chave);
        return MessageFormat.format(mensagem, (Object[]) params);
    }
    
    private final static Pattern PATTERN_REF_PROP = Pattern.compile("\\%(.*)\\%");
    
    public String getText(String chave) {
    	String text = getResources().getString(chave);
    	if (text == null) {
    		return null;
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	
    	int last = 0;
    	Matcher m = PATTERN_REF_PROP.matcher(text);
    	while (m.find()) {
    		if (m.start() > last) {
    			sb.append(text.substring(last, m.start()));
    		}
    		String refTxt = getText(m.group(1));
    		sb.append(refTxt);
    		last = m.end();
    	}
    	if (last < text.length()) {
    		sb.append(text.substring(last, text.length()));
    	}
		return sb.toString();
    }

}
