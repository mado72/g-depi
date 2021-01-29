package br.com.bradseg.depi.depositoidentificado.model.book;

import java.util.ArrayList;
import java.util.List;

import br.com.bradseg.depi.dp06.model.vo.CiaVO;
import br.com.cpmbraxis.framework.cicsannotations.annotation.CicsField;
import br.com.cpmbraxis.framework.cicsannotations.annotation.CicsOccurs;
import br.com.cpmbraxis.framework.cicsannotations.annotation.CicsTransaction;
import br.com.cpmbraxis.framework.cicsannotations.type.Continuable;
import br.com.cpmbraxis.framework.cicsannotations.util.CicsConstants.UseType;

/**
 * Classe GTAB1426.
 * @author F�bio Henrique - fabio.almeida@cpmbraxis.com
 */
//GTAB0046
@CicsTransaction(program = "GTAB0046", transaction = "GT46", maxLength = 23697)
public class GTAB1426 implements Continuable {

	public static final String QUATRO = "0000";

	/**
     * Construtor.
     * @return new GTAB1426.
     */
	public static GTAB1426 getNewInstance() {
		return new GTAB1426();
	}

	@CicsField(order = 1, size = 4, pattern = QUATRO)
	private int erroGSBS;

	@CicsField(order = 2, size = 1)
	private int tipoErroGSBS;

	@CicsField(order = 3, size = 8, pattern = "00000000")
	private int sqlCodeGSBS;

	@CicsField(order = 4, size = 7, pattern = "0000000")
	private String filler;

	@CicsField(order = 5, size = 2, pattern = "00")
	private int codigoRetorno;

	@CicsField(order = 6, size = 1)
	private String sinalSqlCode;

	@CicsField(order = 7, size = 4, pattern = QUATRO)
	private int sqlCode;

	@CicsField(order = 8, size = 4, pattern = QUATRO)
	private int sqlErrML;

	@CicsField(order = 9, size = 70)
	private String sqlErrMC;

	@CicsField(order = 10, size = 18)
	private String tabelaErro;

	@CicsField(order = 11, size = 52)
	private String descricaoErro;

	@CicsField(order = 12, size = 1)
	private String selecaoSituacao;

	@CicsField(order = 13, size = 1)
	private String selecaoGrupo;

	@CicsField(order = 14, size = 4, pattern = QUATRO)
	private int codigoDefaultAnterior;

	@CicsField(order = 15, size = 3, pattern = "000")
	private int qtCia;

	@CicsField(order = 16, size = 9, pattern = "000000000")
	private int total;

	@CicsField(order = 17, size = 9, pattern = "000000000")
	private int retornados;

	@CicsField(order = 18, size = 9, pattern = "000000000")
	private int qtRestantes;

	@CicsOccurs(order = 19, numberOfItensDependsOn = 15, use = UseType.Out)
	private List<CiaVO> cias = new ArrayList<CiaVO>();

	/**
     * Retorna da BOOK - GTAB1426 o cias.
     * @return O atributo cias
     */
	public List<CiaVO> getCias() {
		return this.cias;
	}

	/**
     * Especifica o cias.
     * @param cias List<CiaVO> do cias a ser setado
     */
	public void setCias(List<CiaVO> cias) {
		this.cias = cias;
	}

	/**
     * Retorna da BOOK - GTAB1426 o codigoDefaultAnterior.
     * @return O atributo codigoDefaultAnterior
     */
	public int getCodigoDefaultAnterior() {
		return this.codigoDefaultAnterior;
	}

	/**
     * Especifica o codigoDefaultAnterior.
     * @param codigoDefaultAnterior int do codigoDefaultAnterior a ser setado
     */
	public void setCodigoDefaultAnterior(int codigoDefaultAnterior) {
		this.codigoDefaultAnterior = codigoDefaultAnterior;
	}

	/**
     * Especifica o qtCia.
     * @param qtCia int do qtCia a ser setado
     */
	public void setQtCia(int qtCia) {
		this.qtCia = qtCia;
	}

	/**
     * Retorna da BOOK - GTAB1426 o qtRestantes.
     * @return O atributo qtRestantes
     */
	public int getQtRestantes() {
		return this.qtRestantes;
	}

	/**
     * Especifica o qtRestantes.
     * @param qtRestantes int do qtRestantes a ser setado
     */
	public void setQtRestantes(int qtRestantes) {
		this.qtRestantes = qtRestantes;
	}

	/**
     * Retorna da BOOK - GTAB1426 o retornados.
     * @return O atributo retornados
     */
	public int getRetornados() {
		return this.retornados;
	}

	/**
     * Especifica o retornados.
     * @param retornados int do retornados a ser setado
     */
	public void setRetornados(int retornados) {
		this.retornados = retornados;
	}

	/**
     * Retorna da BOOK - GTAB1426 o selecaoGrupo.
     * @return O atributo selecaoGrupo
     */
	public String getSelecaoGrupo() {
		return this.selecaoGrupo;
	}

	/**
     * Especifica o selecaoGrupo.
     * @param selecaoGrupo String do selecaoGrupo a ser setado
     */
	public void setSelecaoGrupo(String selecaoGrupo) {
		this.selecaoGrupo = selecaoGrupo;
	}

	/**
     * Retorna da BOOK - GTAB1426 o selecaoSituacao.
     * @return O atributo selecaoSituacao
     */
	public String getSelecaoSituacao() {
		return this.selecaoSituacao;
	}

	/**
     * Especifica o selecaoSituacao.
     * @param selecaoSituacao String do selecaoSituacao a ser setado
     */
	public void setSelecaoSituacao(String selecaoSituacao) {
		this.selecaoSituacao = selecaoSituacao;
	}

	/**
     * Especifica o total.
     * @param total int do total a ser setado
     */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
     * {@inheritDoc}
     */
	public boolean hasContinuation() {
		if (this.qtRestantes > 0) {
            codigoDefaultAnterior = cias.get(cias.size() - 1).getCodigoExterno();
			return true;
		}
		while (cias.get(cias.size() - 1).getCodigoExterno() == 0) {
			cias.remove(cias.get(cias.size() - 1));
		}
		return false;
	}

	/**
     * Retorna da BOOK - GTAB1426 o qtCia.
     * @return O atributo qtCia
     */
	public int getQtCia() {
		return this.qtCia;
	}

	/**
     * Retorna da BOOK - GTAB1426 o total.
     * @return O atributo total
     */
	public int getTotal() {
		return this.total;
	}

	/**
     * Retorna da BOOK - GTAB1426 o codigoRetorno.
     * @return O atributo codigoRetorno
     */
	public int getCodigoRetorno() {
		return this.codigoRetorno;
	}

	/**
     * Especifica o codigoRetorno.
     * @param codigoRetorno int do codigoRetorno a ser setado
     */
	public void setCodigoRetorno(int codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	/**
     * Retorna da BOOK - GTAB1426 o descricaoErro.
     * @return O atributo descricaoErro
     */
	public String getDescricaoErro() {
		return this.descricaoErro;
	}

	/**
     * Especifica o descricaoErro.
     * @param descricaoErro String do descricaoErro a ser setado
     */
	public void setDescricaoErro(String descricaoErro) {
		this.descricaoErro = descricaoErro;
	}

	/**
     * Retorna da BOOK - GTAB1426 o erroGSBS.
     * @return O atributo erroGSBS
     */
	public int getErroGSBS() {
		return this.erroGSBS;
	}

	/**
     * Especifica o erroGSBS.
     * @param erroGSBS int do erroGSBS a ser setado
     */
	public void setErroGSBS(int erroGSBS) {
		this.erroGSBS = erroGSBS;
	}

	/**
     * Retorna da BOOK - GTAB1426 o filler.
     * @return O atributo filler
     */
	public String getFiller() {
		return this.filler;
	}

	/**
     * Especifica o filler.
     * @param filler String do filler a ser setado
     */
	public void setFiller(String filler) {
		this.filler = filler;
	}

	/**
     * Retorna da BOOK - GTAB1426 o sinalSqlCode.
     * @return O atributo sinalSqlCode
     */
	public String getSinalSqlCode() {
		return this.sinalSqlCode;
	}

	/**
     * Especifica o sinalSqlCode.
     * @param sinalSqlCode String do sinalSqlCode a ser setado
     */
	public void setSinalSqlCode(String sinalSqlCode) {
		this.sinalSqlCode = sinalSqlCode;
	}

	/**
     * Retorna da BOOK - GTAB1426 o sqlCode.
     * @return O atributo sqlCode
     */
	public int getSqlCode() {
		return this.sqlCode;
	}

	/**
     * Especifica o sqlCode.
     * @param sqlCode int do sqlCode a ser setado
     */
	public void setSqlCode(int sqlCode) {
		this.sqlCode = sqlCode;
	}

	/**
     * Retorna da BOOK - GTAB1426 o sqlCodeGSBS.
     * @return O atributo sqlCodeGSBS
     */
	public int getSqlCodeGSBS() {
		return this.sqlCodeGSBS;
	}

	/**
     * Especifica o sqlCodeGSBS.
     * @param sqlCodeGSBS int do sqlCodeGSBS a ser setado
     */
	public void setSqlCodeGSBS(int sqlCodeGSBS) {
		this.sqlCodeGSBS = sqlCodeGSBS;
	}

	/**
     * Retorna da BOOK - GTAB1426 o sqlErrMC.
     * @return O atributo sqlErrMC
     */
	public String getSqlErrMC() {
		return this.sqlErrMC;
	}

	/**
     * Especifica o sqlErrMC.
     * @param sqlErrMC String do sqlErrMC a ser setado
     */
	public void setSqlErrMC(String sqlErrMC) {
		this.sqlErrMC = sqlErrMC;
	}

	/**
     * Retorna da BOOK - GTAB1426 o sqlErrML.
     * @return O atributo sqlErrML
     */
	public int getSqlErrML() {
		return this.sqlErrML;
	}

	/**
     * Especifica o sqlErrML.
     * @param sqlErrML int do sqlErrML a ser setado
     */
	public void setSqlErrML(int sqlErrML) {
		this.sqlErrML = sqlErrML;
	}

	/**
     * Retorna da BOOK - GTAB1426 o tabelaErro.
     * @return O atributo tabelaErro
     */
	public String getTabelaErro() {
		return this.tabelaErro;
	}

	/**
     * Especifica o tabelaErro.
     * @param tabelaErro String do tabelaErro a ser setado
     */
	public void setTabelaErro(String tabelaErro) {
		this.tabelaErro = tabelaErro;
	}

	/**
     * Retorna da BOOK - GTAB1426 o tipoErroGSBS.
     * @return O atributo tipoErroGSBS
     */
	public int getTipoErroGSBS() {
		return this.tipoErroGSBS;
	}

	/**
     * Especifica o tipoErroGSBS.
     * @param tipoErroGSBS int do tipoErroGSBS a ser setado
     */
	public void setTipoErroGSBS(int tipoErroGSBS) {
		this.tipoErroGSBS = tipoErroGSBS;
	}
}
