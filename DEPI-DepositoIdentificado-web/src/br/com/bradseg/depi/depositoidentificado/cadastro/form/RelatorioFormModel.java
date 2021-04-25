package br.com.bradseg.depi.depositoidentificado.cadastro.form;

import java.io.Serializable;

/**
 *  
 * @author Globality
 */
public class RelatorioFormModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fileNameReport;
	private String acao;
	private String acaoAnterior;
	private String tpcCias = "TRUE";
	private String tpcCiasOrdenadas = "TRUE";
	private String subtituloTela;
	private String tituloTabela;
	private String tipoRelatorio;
	private String visualizacao;
	private int deposito;
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
	private String subtitulo;

	public String getFileNameReport() {
		return fileNameReport;
	}

	public void setFileNameReport(String fileNameReport) {
		this.fileNameReport = fileNameReport;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getAcaoAnterior() {
		return acaoAnterior;
	}

	public void setAcaoAnterior(String acaoAnterior) {
		this.acaoAnterior = acaoAnterior;
	}

	public String getTpcCias() {
		return tpcCias;
	}

	public void setTpcCias(String tpcCias) {
		this.tpcCias = tpcCias;
	}

	public String getTpcCiasOrdenadas() {
		return tpcCiasOrdenadas;
	}

	public void setTpcCiasOrdenadas(String tpcCiasOrdenadas) {
		this.tpcCiasOrdenadas = tpcCiasOrdenadas;
	}

	public String getSubtituloTela() {
		return subtituloTela;
	}

	public void setSubtituloTela(String subtituloTela) {
		this.subtituloTela = subtituloTela;
	}

	public String getTituloTabela() {
		return tituloTabela;
	}

	public void setTituloTabela(String tituloTabela) {
		this.tituloTabela = tituloTabela;
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

	public int getDeposito() {
		return deposito;
	}

	public void setDeposito(int deposito) {
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

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

}
