package br.com.bradseg.depi.depositoidentificado.model.book;

import java.util.ArrayList;
import java.util.List;

import br.com.bradseg.depi.dp06.model.vo.SucursalVO;
import br.com.cpmbraxis.framework.cicsannotations.annotation.CicsField;
import br.com.cpmbraxis.framework.cicsannotations.annotation.CicsOccurs;
import br.com.cpmbraxis.framework.cicsannotations.annotation.CicsTransaction;
import br.com.cpmbraxis.framework.cicsannotations.type.Continuable;
import br.com.cpmbraxis.framework.cicsannotations.util.CicsConstants.UseType;

/**
 * Classe respons�vel por mapear o book da entidade Sucursal para ser utilizada nas chamadas ao CICS. UTILIZADA PELAS ROTINAS DE
 * SERVICO GTAB0027 E GTAB0047 OBTER CODIGO, NOME, NOME ABREVIADO, TIPO, SITUACAO E CODIGO SUCURSAL VINCULADA.
 * Commarea: 22407
 * @author F�bio Henrique - fabio.almeida@cpmbraxis.com.
 */
@CicsTransaction(program = "GTAB0047", transaction = "", maxLength = 22407)
public class GTAB1427 implements Continuable {

	public static final String QUATRO = "0000";

	@CicsField(order = 0, size = 4, pattern = QUATRO)
	private int erroGSBS;

	@CicsField(order = 0, size = 1)
	private int tipoErroGSBS;

	@CicsField(order = 0, size = 2, pattern = "00000000")
	private int sqlCodeGSBS;

	@CicsField(order = 0, size = 7, pattern = "0000000")
	private String filler;

	@CicsField(order = 0, size = 2, pattern = "00")
	private int codigoRetorno;

	@CicsField(order = 0, size = 1)
	private String sinalSqlCode;

	@CicsField(order = 0, size = 4, pattern = QUATRO)
	private int sqlCode;

	@CicsField(order = 0, size = 4, pattern = QUATRO)
	private int sqlErrML;

	@CicsField(order = 0, size = 70)
	private String sqlErrMC;

	@CicsField(order = 0, size = 18)
	private String tabelaErro;

	@CicsField(order = 0, size = 52)
	private String descricaoErro;

	@CicsField(order = 0, size = 1)
	private String order;

	@CicsField(order = 0, size = 1)
	private String selecao;

	@CicsField(order = 0, size = 4, pattern = QUATRO)
	private int cdAnterior;

	@CicsField(order = 1, size = 3, pattern = "000")
	private int quantidade;

	@CicsField(order = 2, size = 9, pattern = "000000000")
	private int total;

	@CicsField(order = 3, size = 9, pattern = "000000000")
	private int retornados;

	@CicsField(order = 3, size = 9, pattern = "000000000")
	private int restantes;

	@CicsOccurs(order = 19, numberOfItensDependsOn = 1, use = UseType.Out)
	private List<SucursalVO> lista = new ArrayList<SucursalVO>();

	/**
     * {@inheritDoc}
     */
	public boolean hasContinuation() {
		if (this.restantes > 0) {
			cdAnterior = lista.get(lista.size() - 1).getCodigo();
			return true;
		}
		while (lista.get(lista.size() - 1).getCodigo() == 0) {
			lista.remove(lista.get(lista.size() - 1));
		}
		return false;
	}

	/**
     * Retorna da BOOK - GTAB1427 o valor do atributo cdAnterior.
     * @return o valor do atributo cdAnterior
     */
    public int getCdAnterior() {
    	return cdAnterior;
    }

	/**
     * Especifica o valor do atributo cdAnterior.
     * @param cdAnterior - int do cdAnterior a ser configurado.
     */
    public void setCdAnterior(int cdAnterior) {
    	this.cdAnterior = cdAnterior;
    }

	/**
     * Retorna da BOOK - GTAB1427 o valor do atributo codigoRetorno.
     * @return o valor do atributo codigoRetorno
     */
    public int getCodigoRetorno() {
    	return codigoRetorno;
    }

	/**
     * Especifica o valor do atributo codigoRetorno.
     * @param codigoRetorno - int do codigoRetorno a ser configurado.
     */
    public void setCodigoRetorno(int codigoRetorno) {
    	this.codigoRetorno = codigoRetorno;
    }

	/**
     * Retorna da BOOK - GTAB1427 o valor do atributo descricaoErro.
     * @return o valor do atributo descricaoErro
     */
    public String getDescricaoErro() {
    	return descricaoErro;
    }

	/**
     * Especifica o valor do atributo descricaoErro.
     * @param descricaoErro - String do descricaoErro a ser configurado.
     */
    public void setDescricaoErro(String descricaoErro) {
    	this.descricaoErro = descricaoErro;
    }

	/**
     * Retorna da BOOK - GTAB1427 o valor do atributo erroGSBS.
     * @return o valor do atributo erroGSBS
     */
    public int getErroGSBS() {
    	return erroGSBS;
    }

	/**
     * Especifica o valor do atributo erroGSBS.
     * @param erroGSBS - int do erroGSBS a ser configurado.
     */
    public void setErroGSBS(int erroGSBS) {
    	this.erroGSBS = erroGSBS;
    }

	/**
     * Retorna da BOOK - GTAB1427 o valor do atributo filler.
     * @return o valor do atributo filler
     */
    public String getFiller() {
    	return filler;
    }

	/**
     * Especifica o valor do atributo filler.
     * @param filler - String do filler a ser configurado.
     */
    public void setFiller(String filler) {
    	this.filler = filler;
    }

	/**
     * Retorna da BOOK - GTAB1427 o valor do atributo lista.
     * @return o valor do atributo lista
     */
    public List<SucursalVO> getLista() {
    	return lista;
    }

	/**
     * Especifica o valor do atributo lista.
     * @param lista - List<SucursalVO> do lista a ser configurado.
     */
    public void setLista(List<SucursalVO> lista) {
    	this.lista = lista;
    }

	/**
     * Retorna da BOOK - GTAB1427 o valor do atributo order.
     * @return o valor do atributo order
     */
    public String getOrder() {
    	return order;
    }

	/**
     * Especifica o valor do atributo order.
     * @param order - String do order a ser configurado.
     */
    public void setOrder(String order) {
    	this.order = order;
    }

	/**
     * Retorna da BOOK - GTAB1427 o valor do atributo quantidade.
     * @return o valor do atributo quantidade
     */
    public int getQuantidade() {
    	return quantidade;
    }

	/**
     * Especifica o valor do atributo quantidade.
     * @param quantidade - int do quantidade a ser configurado.
     */
    public void setQuantidade(int quantidade) {
    	this.quantidade = quantidade;
    }

	/**
     * Retorna da BOOK - GTAB1427 o valor do atributo restantes.
     * @return o valor do atributo restantes
     */
    public int getRestantes() {
    	return restantes;
    }

	/**
     * Especifica o valor do atributo restantes.
     * @param restantes - int do restantes a ser configurado.
     */
    public void setRestantes(int restantes) {
    	this.restantes = restantes;
    }

	/**
     * Retorna da BOOK - GTAB1427 o valor do atributo retornados.
     * @return o valor do atributo retornados
     */
    public int getRetornados() {
    	return retornados;
    }

	/**
     * Especifica o valor do atributo retornados.
     * @param retornados - int do retornados a ser configurado.
     */
    public void setRetornados(int retornados) {
    	this.retornados = retornados;
    }

	/**
     * Retorna da BOOK - GTAB1427 o valor do atributo selecao.
     * @return o valor do atributo selecao
     */
    public String getSelecao() {
    	return selecao;
    }

	/**
     * Especifica o valor do atributo selecao.
     * @param selecao - String do selecao a ser configurado.
     */
    public void setSelecao(String selecao) {
    	this.selecao = selecao;
    }

	/**
     * Retorna da BOOK - GTAB1427 o valor do atributo sinalSqlCode.
     * @return o valor do atributo sinalSqlCode
     */
    public String getSinalSqlCode() {
    	return sinalSqlCode;
    }

	/**
     * Especifica o valor do atributo sinalSqlCode.
     * @param sinalSqlCodeGTAB0047 - String do sinalSqlCode a ser configurado.
     */
    public void setSinalSqlCode(String sinalSqlCodeGTAB0047) {
    	this.sinalSqlCode = sinalSqlCodeGTAB0047;
    }

	/**
     * Retorna da BOOK - GTAB1427 o valor do atributo sqlCode.
     * @return o valor do atributo sqlCode
     */
    public int getSqlCode() {
    	return sqlCode;
    }

	/**
     * Especifica o valor do atributo sqlCode.
     * @param sqlCodeGTAB0047 - int do sqlCode a ser configurado.
     */
    public void setSqlCode(int sqlCodeGTAB0047) {
    	this.sqlCode = sqlCodeGTAB0047;
    }

	/**
     * Retorna da BOOK - GTAB1427 o valor do atributo sqlCodeGSBS.
     * @return o valor do atributo sqlCodeGSBS
     */
    public int getSqlCodeGSBS() {
    	return sqlCodeGSBS;
    }

	/**
     * Especifica o valor do atributo sqlCodeGSBS.
     * @param sqlCodeGSBSGTAB0047 - int do sqlCodeGSBS a ser configurado.
     */
    public void setSqlCodeGSBS(int sqlCodeGSBSGTAB0047) {
    	this.sqlCodeGSBS = sqlCodeGSBSGTAB0047;
    }

	/**
     * Retorna da BOOK - GTAB1427 o valor do atributo sqlErrMC.
     * @return o valor do atributo sqlErrMC
     */
    public String getSqlErrMC() {
    	return sqlErrMC;
    }

	/**
     * Especifica o valor do atributo sqlErrMC.
     * @param sqlErrMCGTAB0047 - String do sqlErrMC a ser configurado.
     */
    public void setSqlErrMC(String sqlErrMCGTAB0047) {
    	this.sqlErrMC = sqlErrMCGTAB0047;
    }

	/**
     * Retorna da BOOK - GTAB1427 o valor do atributo sqlErrML.
     * @return o valor do atributo sqlErrML
     */
    public int getSqlErrML() {
    	return sqlErrML;
    }

	/**
     * Especifica o valor do atributo sqlErrML.
     * @param sqlErrMLGTAB0047 - int do sqlErrML a ser configurado.
     */
    public void setSqlErrML(int sqlErrMLGTAB0047) {
    	this.sqlErrML = sqlErrMLGTAB0047;
    }

	/**
     * Retorna da BOOK - GTAB1427 o valor do atributo tabelaErro.
     * @return o valor do atributo tabelaErro
     */
    public String getTabelaErro() {
    	return tabelaErro;
    }

	/**
     * Especifica o valor do atributo tabelaErro.
     * @param tabelaErroGTAB0047 - String do tabelaErro a ser configurado.
     */
    public void setTabelaErro(String tabelaErroGTAB0047) {
    	this.tabelaErro = tabelaErroGTAB0047;
    }

	/**
     * Retorna da BOOK - GTAB1427 o valor do atributo tipoErroGSBS.
     * @return o valor do atributo tipoErroGSBS
     */
    public int getTipoErroGSBS() {
    	return tipoErroGSBS;
    }

	/**
     * Especifica o valor do atributo tipoErroGSBS.
     * @param tipoErroGSBSGTAB0047 - int do tipoErroGSBS a ser configurado.
     */
    public void setTipoErroGSBS(int tipoErroGSBSGTAB0047) {
    	this.tipoErroGSBS = tipoErroGSBSGTAB0047;
    }

	/**
     * Retorna da BOOK - GTAB1427 o valor do atributo total.
     * @return o valor do atributo total
     */
    public int getTotal() {
    	return total;
    }

	/**
     * Especifica o valor do atributo total.
     * @param total - int do total a ser configurado.
     */
    public void setTotal(int total) {
    	this.total = total;
    }

}
