package br.com.bradseg.depi.depositoidentificado.relatorio.vo;

import java.io.Serializable;

public class FiltroVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6638692335506721199L;
	private String acao;

	private String tipoRelatorio;
	private String visualizacao;
	private String codigoCompanhia;
	private String codigoDepartamento;
	private String codigoMotivoDeposito;
	
	private Integer deposito;
	private String situacaoEnvioRetorno;
	private String situacaoManutencoes;
	private String sucursal;
	private String apolice;
	private String endosso;
	private String codigoAutorizador;
	private String cpfCnpj;
	private String codigoContaCorrente;
	private String dataInicial;
	private String dataFinal;
	private String valorInicial;
	private String valorFinal;
	private String descricaoDetalhada;
    
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
		
	public String getTipoRelatorio() {
		return tipoRelatorio;
	}
	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}
	

	public String getVisualizacao() {
		return visualizacao;
	}
	public void setVisualizacao(String visualizacao) {
		this.visualizacao = visualizacao;
	}
	
	public String getCodigoCompanhia() {
		return codigoCompanhia;
	}
	public void setCodigoCompanhia(String codigoCompanhia) {
		this.codigoCompanhia = codigoCompanhia;
	}
	public String getCodigoDepartamento() {
		return codigoDepartamento;
	}
	public void setCodigoDepartamento(String codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

	public String getCodigoMotivoDeposito() {
		return codigoMotivoDeposito;
	}

	public void setCodigoMotivoDeposito(String codigoMotivoDeposito) {
		this.codigoMotivoDeposito = codigoMotivoDeposito;
	}
	
	public Integer getDeposito() {
		return deposito;
	}
	public void setDeposito(Integer deposito) {
		this.deposito = deposito;
	}
	public String getSituacaoEnvioRetorno() {
		return situacaoEnvioRetorno;
	}
	public void setSituacaoEnvioRetorno(String situacaoEnvioRetorno) {
		this.situacaoEnvioRetorno = situacaoEnvioRetorno;
	}
	public String getSituacaoManutencoes() {
		return situacaoManutencoes;
	}
	public void setSituacaoManutencoes(String situacaoManutencoes) {
		this.situacaoManutencoes = situacaoManutencoes;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public String getApolice() {
		return apolice;
	}
	public void setApolice(String apolice) {
		this.apolice = apolice;
	}
	public String getEndosso() {
		return endosso;
	}
	public void setEndosso(String endosso) {
		this.endosso = endosso;
	}
	public String getCodigoAutorizador() {
		return codigoAutorizador;
	}
	public void setCodigoAutorizador(String codigoAutorizador) {
		this.codigoAutorizador = codigoAutorizador;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public String getCodigoContaCorrente() {
		return codigoContaCorrente;
	}
	public void setCodigoContaCorrente(String codigoContaCorrente) {
		this.codigoContaCorrente = codigoContaCorrente;
	}
	public String getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}
	public String getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}
	public String getValorInicial() {
		return valorInicial;
	}
	public void setValorInicial(String valorInicial) {
		this.valorInicial = valorInicial;
	}
	public String getValorFinal() {
		return valorFinal;
	}
	public void setValorFinal(String valorFinal) {
		this.valorFinal = valorFinal;
	}
	public String getDescricaoDetalhada() {
		return descricaoDetalhada;
	}
	public void setDescricaoDetalhada(String descricaoDetalhada) {
		this.descricaoDetalhada = descricaoDetalhada;
	}

	
	@Override
	public String toString() {
		return "FiltroVO [" + (acao != null ? "acao=" + acao + ", " : "")
				+ (tipoRelatorio != null ? "tipoRelatorio=" + tipoRelatorio + ", " : "")
				+ (visualizacao != null ? "visualizacao=" + visualizacao + ", " : "")
				+ (codigoCompanhia != null ? "codigoCompanhia=" + codigoCompanhia + ", " : "")
				+ (codigoDepartamento != null ? "codigoDepartamento=" + codigoDepartamento + ", " : "")
				+ (codigoMotivoDeposito != null ? "codigoMotivoDeposito=" + codigoMotivoDeposito + ", " : "")
				+ (deposito != null ? "deposito=" + deposito + ", " : "")
				+ (situacaoEnvioRetorno != null ? "situacaoEnvioRetorno=" + situacaoEnvioRetorno + ", " : "")
				+ (situacaoManutencoes != null ? "situacaoManutencoes=" + situacaoManutencoes + ", " : "")
				+ (sucursal != null ? "sucursal=" + sucursal + ", " : "")
				+ (apolice != null ? "apolice=" + apolice + ", " : "")
				+ (endosso != null ? "endosso=" + endosso + ", " : "")
				+ (codigoAutorizador != null ? "codigoAutorizador=" + codigoAutorizador + ", " : "")
				+ (cpfCnpj != null ? "cpfCnpj=" + cpfCnpj + ", " : "")
				+ (codigoContaCorrente != null ? "codigoContaCorrente=" + codigoContaCorrente + ", " : "")
				+ (dataInicial != null ? "dataInicial=" + dataInicial + ", " : "")
				+ (dataFinal != null ? "dataFinal=" + dataFinal + ", " : "")
				+ (valorInicial != null ? "valorInicial=" + valorInicial + ", " : "")
				+ (valorFinal != null ? "valorFinal=" + valorFinal + ", " : "")
				+ (descricaoDetalhada != null ? "descricaoDetalhada=" + descricaoDetalhada : "") + "]";
	}

    
	
}
