/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.cics.vo;

import static br.com.bradseg.depi.depositoidentificado.util.annotations.CicsField.CINCO;
import static br.com.bradseg.depi.depositoidentificado.util.annotations.CicsField.QUATRO;
import static br.com.bradseg.depi.depositoidentificado.util.annotations.CicsField.TRES;
import static br.com.bradseg.depi.depositoidentificado.util.annotations.CicsField.Direction.InOut;

import java.io.Serializable;

import br.com.bradseg.depi.depositoidentificado.util.annotations.CicsField;
import br.com.bradseg.depi.depositoidentificado.util.annotations.CicsProgram;

/**
 * Classe que representa o book para o GTAB1412. 
 * @author Marcelo Damasceno
 */
@CicsProgram(programName = "STES0502", transactionName = "ST83", commLength = 226)
public class STES0512VO implements Serializable {

	private static final long serialVersionUID = -697466839831643647L;

	@CicsField(order = 1, size = 3, pattern = TRES, direction = InOut)
	private int codigoCia;

	@CicsField(order = 2, size = 3, pattern = TRES, direction = InOut)
	private int codigoBanco;

	@CicsField(order = 3, size = 5, pattern = CINCO, direction = InOut)
	private int codigoAgencia;

	@CicsField(order = 4, size = 13, pattern = "0000000000000", direction = InOut)
	private String conta;

	@CicsField(order = 5, size = 7, pattern = "0000000")
	private int qtOcorrencias;

	@CicsField(order = 6, size = 1)
	private String stOcorrencias;
	
	@CicsField(order = 7, size = 4, pattern = QUATRO)
	private int retCode;

	@CicsField(order = 8, size = 79)
	private String mensagem;

	@CicsField(order = 9, size = 3, pattern = TRES)
	private String sqlCode;

	@CicsField(order = 10, size = 40)
	private String rotina;

	@CicsField(order = 11, size = 40)
	private String tabela;

	@CicsField(order = 12, size = 2)
	private String resultado;

	@CicsField(order = 13, size = 6, pattern = "000000")
	private String contaCorrenteInterna;

	@CicsField(order = 13, size = 8, pattern = "ddmmyyyy")
	private Integer dataAbertura;

	@CicsField(order = 14, size = 8, pattern = "ddmmyyyy")
	private String dataEncerramento;

	@CicsField(order = 15, size = 1)
	private String digitoAgencia;
	
	@CicsField(order = 16, size = 1)
	private String primeiroDigitoConta;

	@CicsField(order = 17, size = 1, pattern = "000000")
	private int segundoDigitoConta;

	/**
	 * Retorna codigoCia
	 * @return o codigoCia
	 */
	public int getCodigoCia() {
		return codigoCia;
	}

	/**
	 * Define codigoCia
	 * @param codigoCia valor codigoCia a ser definido
	 */
	public void setCodigoCia(int codigoCia) {
		this.codigoCia = codigoCia;
	}

	/**
	 * Retorna codigoBanco
	 * @return o codigoBanco
	 */
	public int getCodigoBanco() {
		return codigoBanco;
	}

	/**
	 * Define codigoBanco
	 * @param codigoBanco valor codigoBanco a ser definido
	 */
	public void setCodigoBanco(int codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	/**
	 * Retorna codigoAgencia
	 * @return o codigoAgencia
	 */
	public int getCodigoAgencia() {
		return codigoAgencia;
	}

	/**
	 * Define codigoAgencia
	 * @param codigoAgencia valor codigoAgencia a ser definido
	 */
	public void setCodigoAgencia(int codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}

	/**
	 * Retorna conta
	 * @return o conta
	 */
	public String getConta() {
		return conta;
	}

	/**
	 * Define conta
	 * @param conta valor conta a ser definido
	 */
	public void setConta(String conta) {
		this.conta = conta;
	}

	/**
	 * Retorna qtOcorrencias
	 * @return o qtOcorrencias
	 */
	public int getQtOcorrencias() {
		return qtOcorrencias;
	}

	/**
	 * Define qtOcorrencias
	 * @param qtOcorrencias valor qtOcorrencias a ser definido
	 */
	public void setQtOcorrencias(int qtOcorrencias) {
		this.qtOcorrencias = qtOcorrencias;
	}

	/**
	 * Retorna stOcorrencias
	 * @return o stOcorrencias
	 */
	public String getStOcorrencias() {
		return stOcorrencias;
	}

	/**
	 * Define stOcorrencias
	 * @param stOcorrencias valor stOcorrencias a ser definido
	 */
	public void setStOcorrencias(String stOcorrencias) {
		this.stOcorrencias = stOcorrencias;
	}

	/**
	 * Retorna retCode
	 * @return o retCode
	 */
	public int getRetCode() {
		return retCode;
	}

	/**
	 * Define retCode
	 * @param retCode valor retCode a ser definido
	 */
	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}

	/**
	 * Retorna mensagem
	 * @return o mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * Define mensagem
	 * @param mensagem valor mensagem a ser definido
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/**
	 * Retorna sqlCode
	 * @return o sqlCode
	 */
	public String getSqlCode() {
		return sqlCode;
	}

	/**
	 * Define sqlCode
	 * @param sqlCode valor sqlCode a ser definido
	 */
	public void setSqlCode(String sqlCode) {
		this.sqlCode = sqlCode;
	}

	/**
	 * Retorna rotina
	 * @return o rotina
	 */
	public String getRotina() {
		return rotina;
	}

	/**
	 * Define rotina
	 * @param rotina valor rotina a ser definido
	 */
	public void setRotina(String rotina) {
		this.rotina = rotina;
	}

	/**
	 * Retorna tabela
	 * @return o tabela
	 */
	public String getTabela() {
		return tabela;
	}

	/**
	 * Define tabela
	 * @param tabela valor tabela a ser definido
	 */
	public void setTabela(String tabela) {
		this.tabela = tabela;
	}

	/**
	 * Retorna resultado
	 * @return o resultado
	 */
	public String getResultado() {
		return resultado;
	}

	/**
	 * Define resultado
	 * @param resultado valor resultado a ser definido
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	/**
	 * Retorna contaCorrenteInterna
	 * @return o contaCorrenteInterna
	 */
	public String getContaCorrenteInterna() {
		return contaCorrenteInterna;
	}

	/**
	 * Define contaCorrenteInterna
	 * @param contaCorrenteInterna valor contaCorrenteInterna a ser definido
	 */
	public void setContaCorrenteInterna(String contaCorrenteInterna) {
		this.contaCorrenteInterna = contaCorrenteInterna;
	}

	/**
	 * Retorna dataAbertura
	 * @return o dataAbertura
	 */
	public Integer getDataAbertura() {
		return dataAbertura;
	}

	/**
	 * Define dataAbertura
	 * @param dataAbertura valor dataAbertura a ser definido
	 */
	public void setDataAbertura(Integer dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	/**
	 * Retorna dataEncerramento
	 * @return o dataEncerramento
	 */
	public String getDataEncerramento() {
		return dataEncerramento;
	}

	/**
	 * Define dataEncerramento
	 * @param dataEncerramento valor dataEncerramento a ser definido
	 */
	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	/**
	 * Retorna digitoAgencia
	 * @return o digitoAgencia
	 */
	public String getDigitoAgencia() {
		return digitoAgencia;
	}

	/**
	 * Define digitoAgencia
	 * @param digitoAgencia valor digitoAgencia a ser definido
	 */
	public void setDigitoAgencia(String digitoAgencia) {
		this.digitoAgencia = digitoAgencia;
	}

	/**
	 * Retorna primeiroDigitoConta
	 * @return o primeiroDigitoConta
	 */
	public String getPrimeiroDigitoConta() {
		return primeiroDigitoConta;
	}

	/**
	 * Define primeiroDigitoConta
	 * @param primeiroDigitoConta valor primeiroDigitoConta a ser definido
	 */
	public void setPrimeiroDigitoConta(String primeiroDigitoConta) {
		this.primeiroDigitoConta = primeiroDigitoConta;
	}

	/**
	 * Retorna segundoDigitoConta
	 * @return o segundoDigitoConta
	 */
	public int getSegundoDigitoConta() {
		return segundoDigitoConta;
	}

	/**
	 * Define segundoDigitoConta
	 * @param segundoDigitoConta valor segundoDigitoConta a ser definido
	 */
	public void setSegundoDigitoConta(int segundoDigitoConta) {
		this.segundoDigitoConta = segundoDigitoConta;
	}
	
}
