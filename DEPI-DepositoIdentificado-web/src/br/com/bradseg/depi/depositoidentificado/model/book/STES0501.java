package br.com.bradseg.depi.depositoidentificado.model.book;

import java.io.Serializable;

import br.com.bradseg.depi.dp06.model.util.ConstantesModel;
import br.com.bradseg.depi.dp06.model.util.converter.BancoConverter;
import br.com.bradseg.depi.dp06.model.util.converter.CompanhiaSeguradoraConverter;
import br.com.bradseg.depi.dp06.model.vo.BancoVO;
import br.com.bradseg.depi.dp06.model.vo.CompanhiaSeguradoraVO;
import br.com.cpmbraxis.framework.cicsannotations.annotation.CicsField;
import br.com.cpmbraxis.framework.cicsannotations.annotation.CicsTransaction;
import br.com.cpmbraxis.framework.cicsannotations.type.Continuable;
import br.com.cpmbraxis.framework.cicsannotations.util.CicsConstants.UseType;

/**
 * Book Conta Corrente.
 * @author F�bio Henrique
 */
@CicsTransaction(program = "STES0501", transaction = "ST81", maxLength = 332)
public class STES0501 implements Serializable, Continuable {

	/**
     * Serial Version.
     */
	private static final long serialVersionUID = 1L;

	@CicsField(order = 0, size = 6, pattern = ConstantesModel.PATTERN_SEIS)
	private int codigoInternoCC;

	@CicsField(order = 1, size = 7, pattern = "0000000", use = UseType.Out)
	private int qtOcorrencias;

	@CicsField(order = 2, size = 1, use = UseType.Out)
	private String stOcorrencias;

	@CicsField(order = 3, size = 4, pattern = ConstantesModel.PATTERN_QUATRO, use = UseType.Out)
	private int retCode;

	@CicsField(order = 4, size = 79, use = UseType.Out)
	private String mensagem;

	@CicsField(order = 5, size = 3, pattern = ConstantesModel.PATTERN_TRES, use = UseType.Out)
	private int sqlCode;

	@CicsField(order = 6, size = 40, use = UseType.Out)
	private String rotina;

	@CicsField(order = 7, size = 40, use = UseType.Out)
	private String tabela;

	@CicsField(order = 8, size = 2, use = UseType.Out)
	private String resultado;

	@CicsField(order = 9, size = 3, pattern = ConstantesModel.PATTERN_TRES, 
		use = UseType.Out, converter = CompanhiaSeguradoraConverter.class)
	private CompanhiaSeguradoraVO cia;

	@CicsField(order = 10, size = 1, use = UseType.Out)
	private int dvCia;

	@CicsField(order = 11, size = 3, pattern = ConstantesModel.PATTERN_TRES, use = UseType.Out, converter = BancoConverter.class)
	private BancoVO banco;

	@CicsField(order = 12, size = 1, use = UseType.Out)
	private String codigoDigitoAg;

	@CicsField(order = 13, size = 13, pattern = "0000000000000", use = UseType.Out)
	private int codigoContaCorrente;

	@CicsField(order = 14, size = 5, pattern = ConstantesModel.PATTERN_CINCO, use = UseType.Out)
	private int codigoAgencia;
	private GTAB1411 agencia;

	/**
     * Retorna da BOOK - STES0501 o valor do atributo agencia.
     * @return o valor do atributo agencia
     */
	public GTAB1411 getAgencia() {
		return agencia;
	}

	/**
     * Especifica para BOOK - STES0501 o valor do atributo agencia.
     * @param agencia - GTAB1411 do agencia a ser configurado.
     */
	public void setAgencia(GTAB1411 agencia) {
		this.agencia = agencia;
	}

	/**
     * Retorna da BOOK - STES0501 o valor do atributo banco.
     * @return o valor do atributo banco
     */
	public BancoVO getBanco() {
		return banco;
	}

	/**
     * Especifica para BOOK - STES0501 o valor do atributo banco.
     * @param banco - BancoVO do banco a ser configurado.
     */
	public void setBanco(BancoVO banco) {
		this.banco = banco;
	}

	/**
     * Retorna da BOOK - STES0501 o valor do atributo codigoAgencia.
     * @return o valor do atributo codigoAgencia
     */
	public int getCodigoAgencia() {
		return codigoAgencia;
	}

	/**
     * Especifica para BOOK - STES0501 o valor do atributo codigoAgencia.
     * @param codigoAgencia - int do codigoAgencia a ser configurado.
     */
	public void setCodigoAgencia(int codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}

	/**
     * {@inheritDoc}
     */
	public boolean hasContinuation() {
		return "C".equals(String.valueOf(this.getQtOcorrencias()));
	}

	/**
     * Construtor
     * @param codigoInternoCC - int.
     * @return STES0501
     */
	public static STES0501 getInstance(int codigoInternoCC) {
		STES0501 book = new STES0501();
		book.setCodigoInternoCC(codigoInternoCC);
		return book;
	}

	/**
     * Construtor
     */
	public STES0501() {
		super();
	}

	/**
     * Retorna da BOOK - STES0501 o valor do atributo serialVersionUID.
     * @return o valor do atributo serialVersionUID
     */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
     * Retorna da BOOK - STES0501 o valor do atributo cia.
     * @return o valor do atributo cia
     */
	public CompanhiaSeguradoraVO getCia() {
		return cia;
	}

	/**
     * Especifica para BOOK - STES0501 o valor do atributo cia.
     * @param cia - CompanhiaSeguradoraVO do cia a ser configurado.
     */
	public void setCia(CompanhiaSeguradoraVO cia) {
		this.cia = cia;
	}

	/**
     * Retorna da BOOK - STES0501 o valor do atributo codigoDigitoAg.
     * @return o valor do atributo codigoDigitoAg
     */
	public String getCodigoDigitoAg() {
		return codigoDigitoAg;
	}

	/**
     * Especifica para BOOK - STES0501 o valor do atributo codigoDigitoAg.
     * @param codigoDigitoAg - String do codigoDigitoAg a ser configurado.
     */
	public void setCodigoDigitoAg(String codigoDigitoAg) {
		this.codigoDigitoAg = codigoDigitoAg;
	}

	/**
     * Retorna da BOOK - STES0501 o valor do atributo mensagem.
     * @return o valor do atributo mensagem
     */
	public String getMensagem() {
		return mensagem;
	}

	/**
     * Especifica para BOOK - STES0501 o valor do atributo mensagem.
     * @param mensagem - String do mensagem a ser configurado.
     */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/**
     * Retorna da BOOK - STES0501 o valor do atributo qtOcorrencias.
     * @return o valor do atributo qtOcorrencias
     */
	public int getQtOcorrencias() {
		return qtOcorrencias;
	}

	/**
     * Especifica para BOOK - STES0501 o valor do atributo qtOcorrencias.
     * @param qtOcorrencias - int do qtOcorrencias a ser configurado.
     */
	public void setQtOcorrencias(int qtOcorrencias) {
		this.qtOcorrencias = qtOcorrencias;
	}

	/**
     * Retorna da BOOK - STES0501 o valor do atributo resultado.
     * @return o valor do atributo resultado
     */
	public String getResultado() {
		return resultado;
	}

	/**
     * Especifica para BOOK - STES0501 o valor do atributo resultado.
     * @param resultado - String do resultado a ser configurado.
     */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	/**
     * Retorna da BOOK - STES0501 o valor do atributo retCode.
     * @return o valor do atributo retCode
     */
	public int getRetCode() {
		return retCode;
	}

	/**
     * Especifica para BOOK - STES0501 o valor do atributo retCode.
     * @param retCode - int do retCode a ser configurado.
     */
	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}

	/**
     * Retorna da BOOK - STES0501 o valor do atributo rotina.
     * @return o valor do atributo rotina
     */
	public String getRotina() {
		return rotina;
	}

	/**
     * Especifica para BOOK - STES0501 o valor do atributo rotina.
     * @param rotina - String do rotina a ser configurado.
     */
	public void setRotina(String rotina) {
		this.rotina = rotina;
	}

	/**
     * Retorna da BOOK - STES0501 o valor do atributo sqlCode.
     * @return o valor do atributo sqlCode
     */
	public int getSqlCode() {
		return sqlCode;
	}

	/**
     * Especifica para BOOK - STES0501 o valor do atributo sqlCode.
     * @param sqlCode - int do sqlCode a ser configurado.
     */
	public void setSqlCode(int sqlCode) {
		this.sqlCode = sqlCode;
	}

	/**
     * Retorna da BOOK - STES0501 o valor do atributo stOcorrencias.
     * @return o valor do atributo stOcorrencias
     */
	public String getStOcorrencias() {
		return stOcorrencias;
	}

	/**
     * Especifica para BOOK - STES0501 o valor do atributo stOcorrencias.
     * @param stOcorrencias - String do stOcorrencias a ser configurado.
     */
	public void setStOcorrencias(String stOcorrencias) {
		this.stOcorrencias = stOcorrencias;
	}

	/**
     * Retorna da BOOK - STES0501 o valor do atributo tabela.
     * @return o valor do atributo tabela
     */
	public String getTabela() {
		return tabela;
	}

	/**
     * Especifica para BOOK - STES0501 o valor do atributo tabela.
     * @param tabela - String do tabela a ser configurado.
     */
	public void setTabela(String tabela) {
		this.tabela = tabela;
	}

	/**
     * Retorna da BOOK - STES0501 o valor do atributo codigoInternoCC.
     * @return o valor do atributo codigoInternoCC
     */
	public int getCodigoInternoCC() {
		return codigoInternoCC;
	}

	/**
     * Especifica para BOOK - STES0501 o valor do atributo codigoInternoCC.
     * @param codigoInternoCC - int do codigoInternoCC a ser configurado.
     */
	public void setCodigoInternoCC(int codigoInternoCC) {
		this.codigoInternoCC = codigoInternoCC;
	}

	/**
     * Retorna da BOOK - STES0501 o valor do atributo codigoContaCorrente.
     * @return o valor do atributo codigoContaCorrente
     */
	public int getCodigoContaCorrente() {
		return codigoContaCorrente;
	}

	/**
     * Especifica para BOOK - STES0501 o valor do atributo codigoContaCorrente.
     * @param codigoContaCorrente - int do codigoContaCorrente a ser configurado.
     */
	public void setCodigoContaCorrente(int codigoContaCorrente) {
		this.codigoContaCorrente = codigoContaCorrente;
	}

	/**
     * Retorna da BOOK - STES0501 o valor do atributo dvCia.
     * @return o valor do atributo dvCia
     */
	public int getDvCia() {
		return dvCia;
	}

	/**
     * Especifica para BOOK - STES0501 o valor do atributo dvCia.
     * @param dvCia - int do dvCia a ser configurado.
     */
	public void setDvCia(int dvCia) {
		this.dvCia = dvCia;
	}

}
