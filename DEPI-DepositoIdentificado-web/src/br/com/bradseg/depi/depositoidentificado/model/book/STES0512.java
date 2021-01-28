package br.com.bradseg.depi.depositoidentificado.model.book;

import java.io.Serializable;
import java.util.Date;

import br.com.bradseg.depi.dp06.model.util.ConstantesModel;
import br.com.cpmbraxis.framework.cicsannotations.annotation.CicsField;
import br.com.cpmbraxis.framework.cicsannotations.annotation.CicsTransaction;
import br.com.cpmbraxis.framework.cicsannotations.util.CicsConstants.UseType;

/**
 * Book Conta Corrente.
 * @author F�bio Henrique
 */
@CicsTransaction(program = "STES0502", transaction = "ST83", maxLength = 226)
public class STES0512 implements Serializable {

	/**
     * Serial Version.
     */
	private static final long serialVersionUID = 167676789697L;

	@CicsField(order = 1, size = 3, use = UseType.InOut, pattern = ConstantesModel.PATTERN_TRES)
	private int codigoCia;

	@CicsField(order = 2, size = 3, use = UseType.InOut, pattern = ConstantesModel.PATTERN_TRES)
	private int codigoBanco;

	@CicsField(order = 3, size = 5, use = UseType.InOut, pattern = ConstantesModel.PATTERN_CINCO)
	private int codigoAgencia;

	@CicsField(order = 5, size = 13, use = UseType.InOut, pattern = ConstantesModel.PATTERN_TREZE)
	private long conta;

	@CicsField(order = 8, size = 7, pattern = "0000000", use = UseType.Out)
	private int qtOcorrencias;

	@CicsField(order = 9, size = 1, use = UseType.Out)
	private String stOcorrencias;

	@CicsField(order = 10, size = 4, pattern = ConstantesModel.PATTERN_QUATRO, use = UseType.Out)
	private int retCode;

	@CicsField(order = 11, size = 79, use = UseType.Out)
	private String mensagem;

	@CicsField(order = 12, size = 3, pattern = ConstantesModel.PATTERN_TRES, use = UseType.Out)
	private int sqlCode;

	@CicsField(order = 13, size = 40, use = UseType.Out)
	private String rotina;

	@CicsField(order = 14, size = 40, use = UseType.Out)
	private String tabela;

	@CicsField(order = 15, size = 2, use = UseType.Out)
	private String resultado;

	@CicsField(order = 16, size = 6, use = UseType.Out, pattern = ConstantesModel.PATTERN_SEIS)
	private int contaCorrenteInterna;

	@CicsField(order = 17, size = 8, use = UseType.Out, pattern = "ddmmyyyy")
	private Date dataAbertura;

	@CicsField(order = 18, size = 8, use = UseType.Out, pattern = "ddmmyyyy")
	private Date dataEncerramento;

	@CicsField(order = 19, size = 1, use = UseType.Out)
	private String digitoAgencia;

	@CicsField(order = 20, size = 1, use = UseType.Out)
	private String primeiroDigitoConta;

	@CicsField(order = 21, size = 1, pattern = ConstantesModel.PATTERN_SEIS, use = UseType.Out)
	private String segundoDigitoConta;

	/**
     * Retorna da BOOK - STES0512 o valor do atributo serialVersionUID.
     * @return o valor do atributo serialVersionUID
     */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
     * Retorna da BOOK - STES0512 o valor do atributo codigoAgencia.
     * @return o valor do atributo codigoAgencia
     */
	public int getCodigoAgencia() {
		return codigoAgencia;
	}

	/**
     * Especifica para BOOK - STES0512 o valor do atributo codigoAgencia.
     * @param codigoAgencia - int do codigoAgencia a ser configurado.
     */
	public void setCodigoAgencia(int codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}

	/**
     * Retorna da BOOK - STES0512 o valor do atributo codigoBanco.
     * @return o valor do atributo codigoBanco
     */
	public int getCodigoBanco() {
		return codigoBanco;
	}

	/**
     * Especifica para BOOK - STES0512 o valor do atributo codigoBanco.
     * @param codigoBanco - int do codigoBanco a ser configurado.
     */
	public void setCodigoBanco(int codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	/**
     * Retorna da BOOK - STES0512 o valor do atributo codigoCia.
     * @return o valor do atributo codigoCia
     */
	public int getCodigoCia() {
		return codigoCia;
	}

	/**
     * Especifica para BOOK - STES0512 o valor do atributo codigoCia.
     * @param codigoCia - int do codigoCia a ser configurado.
     */
	public void setCodigoCia(int codigoCia) {
		this.codigoCia = codigoCia;
	}

	/**
     * Retorna da BOOK - STES0512 o valor do atributo conta.
     * @return o valor do atributo conta
     */
	public long getConta() {
		return conta;
	}

	/**
     * Especifica para BOOK - STES0512 o valor do atributo conta.
     * @param conta - long do conta a ser configurado.
     */
	public void setConta(long conta) {
		this.conta = conta;
	}

	/**
     * Retorna da BOOK - STES0512 o valor do atributo contaCorrenteInterna.
     * @return o valor do atributo contaCorrenteInterna
     */
	public int getContaCorrenteInterna() {
		return contaCorrenteInterna;
	}

	/**
     * Especifica para BOOK - STES0512 o valor do atributo contaCorrenteInterna.
     * @param contaCorrenteInterna - int do contaCorrenteInterna a ser configurado.
     */
	public void setContaCorrenteInterna(int contaCorrenteInterna) {
		this.contaCorrenteInterna = contaCorrenteInterna;
	}

	/**
     * Retorna da BOOK - STES0512 o valor do atributo dataAbertura.
     * @return o valor do atributo dataAbertura
     */
	public Date getDataAbertura() {
		return dataAbertura;
	}

	/**
     * Especifica para BOOK - STES0512 o valor do atributo dataAbertura.
     * @param dataAbertura - Date do dataAbertura a ser configurado.
     */
	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	/**
     * Retorna da BOOK - STES0512 o valor do atributo dataEncerramento.
     * @return o valor do atributo dataEncerramento
     */
	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	/**
     * Especifica para BOOK - STES0512 o valor do atributo dataEncerramento.
     * @param dataEncerramento - String do dataEncerramento a ser configurado.
     */
	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	/**
     * Retorna da BOOK - STES0512 o valor do atributo digitoAgencia.
     * @return o valor do atributo digitoAgencia
     */
    public String getDigitoAgencia() {
    	return digitoAgencia;
    }

	/**
     * Especifica para BOOK - STES0512 o valor do atributo digitoAgencia.
     * @param digitoAgencia - String do digitoAgencia a ser configurado.
     */
    public void setDigitoAgencia(String digitoAgencia) {
    	this.digitoAgencia = digitoAgencia;
    }

	/**
     * Retorna da BOOK - STES0512 o valor do atributo mensagem.
     * @return o valor do atributo mensagem
     */
	public String getMensagem() {
		return mensagem;
	}

	/**
     * Especifica para BOOK - STES0512 o valor do atributo mensagem.
     * @param mensagem - String do mensagem a ser configurado.
     */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/**
     * Retorna da BOOK - STES0512 o valor do atributo primeiroDigitoConta.
     * @return o valor do atributo primeiroDigitoConta
     */
	public String getPrimeiroDigitoConta() {
		return primeiroDigitoConta;
	}

	/**
     * Especifica para BOOK - STES0512 o valor do atributo primeiroDigitoConta.
     * @param primeiroDigitoConta - String do primeiroDigitoConta a ser configurado.
     */
	public void setPrimeiroDigitoConta(String primeiroDigitoConta) {
		this.primeiroDigitoConta = primeiroDigitoConta;
	}

	/**
     * Retorna da BOOK - STES0512 o valor do atributo qtOcorrencias.
     * @return o valor do atributo qtOcorrencias
     */
	public int getQtOcorrencias() {
		return qtOcorrencias;
	}

	/**
     * Especifica para BOOK - STES0512 o valor do atributo qtOcorrencias.
     * @param qtOcorrencias - int do qtOcorrencias a ser configurado.
     */
	public void setQtOcorrencias(int qtOcorrencias) {
		this.qtOcorrencias = qtOcorrencias;
	}

	/**
     * Retorna da BOOK - STES0512 o valor do atributo resultado.
     * @return o valor do atributo resultado
     */
	public String getResultado() {
		return resultado;
	}

	/**
     * Especifica para BOOK - STES0512 o valor do atributo resultado.
     * @param resultado - String do resultado a ser configurado.
     */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	/**
     * Retorna da BOOK - STES0512 o valor do atributo retCode.
     * @return o valor do atributo retCode
     */
	public int getRetCode() {
		return retCode;
	}

	/**
     * Especifica para BOOK - STES0512 o valor do atributo retCode.
     * @param retCode - int do retCode a ser configurado.
     */
	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}

	/**
     * Retorna da BOOK - STES0512 o valor do atributo rotina.
     * @return o valor do atributo rotina
     */
	public String getRotina() {
		return rotina;
	}

	/**
     * Especifica para BOOK - STES0512 o valor do atributo rotina.
     * @param rotina - String do rotina a ser configurado.
     */
	public void setRotina(String rotina) {
		this.rotina = rotina;
	}

	/**
     * Retorna da BOOK - STES0512 o valor do atributo segundoDigitoConta.
     * @return o valor do atributo segundoDigitoConta
     */
	public String getSegundoDigitoConta() {
		return segundoDigitoConta;
	}

	/**
     * Especifica para BOOK - STES0512 o valor do atributo segundoDigitoConta.
     * @param segundoDigitoConta - String do segundoDigitoConta a ser configurado.
     */
	public void setSegundoDigitoConta(String segundoDigitoConta) {
		this.segundoDigitoConta = segundoDigitoConta;
	}

	/**
     * Retorna da BOOK - STES0512 o valor do atributo sqlCode.
     * @return o valor do atributo sqlCode
     */
	public int getSqlCode() {
		return sqlCode;
	}

	/**
     * Especifica para BOOK - STES0512 o valor do atributo sqlCode.
     * @param sqlCode - int do sqlCode a ser configurado.
     */
	public void setSqlCode(int sqlCode) {
		this.sqlCode = sqlCode;
	}

	/**
     * Retorna da BOOK - STES0512 o valor do atributo stOcorrencias.
     * @return o valor do atributo stOcorrencias
     */
	public String getStOcorrencias() {
		return stOcorrencias;
	}

	/**
     * Especifica para BOOK - STES0512 o valor do atributo stOcorrencias.
     * @param stOcorrencias - String do stOcorrencias a ser configurado.
     */
	public void setStOcorrencias(String stOcorrencias) {
		this.stOcorrencias = stOcorrencias;
	}

	/**
     * Retorna da BOOK - STES0512 o valor do atributo tabela.
     * @return o valor do atributo tabela
     */
	public String getTabela() {
		return tabela;
	}

	/**
     * Especifica para BOOK - STES0512 o valor do atributo tabela.
     * @param tabela - String do tabela a ser configurado.
     */
	public void setTabela(String tabela) {
		this.tabela = tabela;
	}
}
