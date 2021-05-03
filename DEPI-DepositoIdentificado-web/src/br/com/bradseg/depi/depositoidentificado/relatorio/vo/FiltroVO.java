package br.com.bradseg.depi.depositoidentificado.relatorio.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

/**
 * Dados do filtro usado nos relatórios 
 * @author Globality
 */
public class FiltroVO implements Serializable {

	private static final long serialVersionUID = -6638692335506721199L;
	
	private boolean abrirRelatorio;

	private String fileNameReport;
	private String acao;

	private String tituloTabela;
	private String subtituloTela;
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
	
	private List<CompanhiaSeguradoraVO> listaCompanhia = new ArrayList<CompanhiaSeguradoraVO>();
	private List<CompanhiaSeguradoraVO> listaCompanhiaOrd = new ArrayList<CompanhiaSeguradoraVO>();
	private List<DepartamentoVO> listaDepartamentos = new ArrayList<DepartamentoVO>();
	private List<MotivoDepositoVO> listaMotivosDepositos = new ArrayList<MotivoDepositoVO>();
	
	@Override
	public String toString() {
		return new StringBuilder("FiltroVO [" )
				.append(toString("acao", acao, true))
				.append(toString("tipoRelatorio", tipoRelatorio, true))
				.append(toString("visualizacao", visualizacao, true))
				.append(toString("codigoCompanhia", codigoCompanhia, true))
				.append(toString("codigoDepartamento", codigoDepartamento, true))
				.append(toString("codigoMotivoDeposito", codigoMotivoDeposito, true))
				.append(toString("deposito", deposito, true))
				.append(toString("situacaoEnvioRetorno", situacaoEnvioRetorno, true))
				.append(toString("situacaoManutencoes", situacaoManutencoes, true))
				.append(toString("sucursal", sucursal, true))
				.append(toString("apolice", apolice, true))
				.append(toString("endosso", endosso, true))
				.append(toString("codigoAutorizador", codigoAutorizador, true))
				.append(toString("cpfCnpj", cpfCnpj, true))
				.append(toString("codigoContaCorrente", codigoContaCorrente, true))
				.append(toString("dataInicial", dataInicial, true))
				.append(toString("dataFinal", dataFinal, true))
				.append(toString("valorInicial", valorInicial, true))
				.append(toString("valorFinal", valorFinal, true))
				.append(toString("descricaoDetalhada", descricaoDetalhada, false))
				.append(']').toString();
	}
    
    private String toString(String property, Object value, boolean sufix) {
    	StringBuilder sb = new StringBuilder(property).append('=');
    	if (value != null) {
    		sb.append(value);
    	}
    	if (sufix) {
    		sb.append(", ");
    	}
    	return sb.toString();
    }
    
    /**
     * @return true se deve abrir nova janela com relatóroi
     */
    public boolean isAbrirRelatorio() {
		return abrirRelatorio;
	}
    
    /**
     * abrir ou não relatório
     * @param abrirRelatorio true = abrir
     */
    public void setAbrirRelatorio(boolean abrirRelatorio) {
		this.abrirRelatorio = abrirRelatorio;
	}

	/**
	 * fileNameReport
	 * @return fileNameReport
	 */
	public String getFileNameReport() {
		return fileNameReport;
	}

	/**
	 * @param fileNameReport fileNameReport
	 */
	public void setFileNameReport(String fileNameReport) {
		this.fileNameReport = fileNameReport;
	}

	/**
	 * Retorna acao
	 * @return o acao
	 */
	public String getAcao() {
		return acao;
	}

	/**
	 * Define acao
	 * @param acao o acao a ser configurado
	 */
	public void setAcao(String acao) {
		this.acao = acao;
	}

	/**
	 * Retorna tituloTabela
	 * @return o tituloTabela
	 */
	public String getTituloTabela() {
		return tituloTabela;
	}

	/**
	 * Define tituloTabela
	 * @param tituloTabela o tituloTabela a ser configurado
	 */
	public void setTituloTabela(String tituloTabela) {
		this.tituloTabela = tituloTabela;
	}

	/**
	 * Retorna subtituloTela
	 * @return o subtituloTela
	 */
	public String getSubtituloTela() {
		return subtituloTela;
	}

	/**
	 * Define subtituloTela
	 * @param subtituloTela o subtituloTela a ser configurado
	 */
	public void setSubtituloTela(String subtituloTela) {
		this.subtituloTela = subtituloTela;
	}

	/**
	 * Retorna tipoRelatorio
	 * @return o tipoRelatorio
	 */
	public String getTipoRelatorio() {
		return tipoRelatorio;
	}

	/**
	 * Define tipoRelatorio
	 * @param tipoRelatorio o tipoRelatorio a ser configurado
	 */
	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

	/**
	 * Retorna visualizacao
	 * @return o visualizacao
	 */
	public String getVisualizacao() {
		return visualizacao;
	}

	/**
	 * Define visualizacao
	 * @param visualizacao o visualizacao a ser configurado
	 */
	public void setVisualizacao(String visualizacao) {
		this.visualizacao = visualizacao;
	}

	/**
	 * Retorna codigoCompanhia
	 * @return o codigoCompanhia
	 */
	public String getCodigoCompanhia() {
		return codigoCompanhia;
	}

	/**
	 * Define codigoCompanhia
	 * @param codigoCompanhia o codigoCompanhia a ser configurado
	 */
	public void setCodigoCompanhia(String codigoCompanhia) {
		this.codigoCompanhia = codigoCompanhia;
	}

	/**
	 * Retorna codigoDepartamento
	 * @return o codigoDepartamento
	 */
	public String getCodigoDepartamento() {
		return codigoDepartamento;
	}

	/**
	 * Define codigoDepartamento
	 * @param codigoDepartamento o codigoDepartamento a ser configurado
	 */
	public void setCodigoDepartamento(String codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

	/**
	 * Retorna codigoMotivoDeposito
	 * @return o codigoMotivoDeposito
	 */
	public String getCodigoMotivoDeposito() {
		return codigoMotivoDeposito;
	}

	/**
	 * Define codigoMotivoDeposito
	 * @param codigoMotivoDeposito o codigoMotivoDeposito a ser configurado
	 */
	public void setCodigoMotivoDeposito(String codigoMotivoDeposito) {
		this.codigoMotivoDeposito = codigoMotivoDeposito;
	}

	/**
	 * Retorna deposito
	 * @return o deposito
	 */
	public Integer getDeposito() {
		return deposito;
	}

	/**
	 * Define deposito
	 * @param deposito o deposito a ser configurado
	 */
	public void setDeposito(Integer deposito) {
		this.deposito = deposito;
	}

	/**
	 * Retorna situacaoEnvioRetorno
	 * @return o situacaoEnvioRetorno
	 */
	public String getSituacaoEnvioRetorno() {
		return situacaoEnvioRetorno;
	}

	/**
	 * Define situacaoEnvioRetorno
	 * @param situacaoEnvioRetorno o situacaoEnvioRetorno a ser configurado
	 */
	public void setSituacaoEnvioRetorno(String situacaoEnvioRetorno) {
		this.situacaoEnvioRetorno = situacaoEnvioRetorno;
	}

	/**
	 * Retorna situacaoManutencoes
	 * @return o situacaoManutencoes
	 */
	public String getSituacaoManutencoes() {
		return situacaoManutencoes;
	}

	/**
	 * Define situacaoManutencoes
	 * @param situacaoManutencoes o situacaoManutencoes a ser configurado
	 */
	public void setSituacaoManutencoes(String situacaoManutencoes) {
		this.situacaoManutencoes = situacaoManutencoes;
	}

	/**
	 * Retorna sucursal
	 * @return o sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Define sucursal
	 * @param sucursal o sucursal a ser configurado
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Retorna apolice
	 * @return o apolice
	 */
	public String getApolice() {
		return apolice;
	}

	/**
	 * Define apolice
	 * @param apolice o apolice a ser configurado
	 */
	public void setApolice(String apolice) {
		this.apolice = apolice;
	}

	/**
	 * Retorna endosso
	 * @return o endosso
	 */
	public String getEndosso() {
		return endosso;
	}

	/**
	 * Define endosso
	 * @param endosso o endosso a ser configurado
	 */
	public void setEndosso(String endosso) {
		this.endosso = endosso;
	}

	/**
	 * Retorna codigoAutorizador
	 * @return o codigoAutorizador
	 */
	public String getCodigoAutorizador() {
		return codigoAutorizador;
	}

	/**
	 * Define codigoAutorizador
	 * @param codigoAutorizador o codigoAutorizador a ser configurado
	 */
	public void setCodigoAutorizador(String codigoAutorizador) {
		this.codigoAutorizador = codigoAutorizador;
	}

	/**
	 * Retorna cpfCnpj
	 * @return o cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * Define cpfCnpj
	 * @param cpfCnpj o cpfCnpj a ser configurado
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * Retorna codigoContaCorrente
	 * @return o codigoContaCorrente
	 */
	public String getCodigoContaCorrente() {
		return codigoContaCorrente;
	}

	/**
	 * Define codigoContaCorrente
	 * @param codigoContaCorrente o codigoContaCorrente a ser configurado
	 */
	public void setCodigoContaCorrente(String codigoContaCorrente) {
		this.codigoContaCorrente = codigoContaCorrente;
	}

	/**
	 * Retorna dataInicial
	 * @return o dataInicial
	 */
	public String getDataInicial() {
		return dataInicial;
	}

	/**
	 * Define dataInicial
	 * @param dataInicial o dataInicial a ser configurado
	 */
	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	/**
	 * Retorna dataFinal
	 * @return o dataFinal
	 */
	public String getDataFinal() {
		return dataFinal;
	}

	/**
	 * Define dataFinal
	 * @param dataFinal o dataFinal a ser configurado
	 */
	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	/**
	 * Retorna valorInicial
	 * @return o valorInicial
	 */
	public String getValorInicial() {
		return valorInicial;
	}

	/**
	 * Define valorInicial
	 * @param valorInicial o valorInicial a ser configurado
	 */
	public void setValorInicial(String valorInicial) {
		this.valorInicial = valorInicial;
	}

	/**
	 * Retorna valorFinal
	 * @return o valorFinal
	 */
	public String getValorFinal() {
		return valorFinal;
	}

	/**
	 * Define valorFinal
	 * @param valorFinal o valorFinal a ser configurado
	 */
	public void setValorFinal(String valorFinal) {
		this.valorFinal = valorFinal;
	}

	/**
	 * Retorna descricaoDetalhada
	 * @return o descricaoDetalhada
	 */
	public String getDescricaoDetalhada() {
		return descricaoDetalhada;
	}

	/**
	 * Define descricaoDetalhada
	 * @param descricaoDetalhada o descricaoDetalhada a ser configurado
	 */
	public void setDescricaoDetalhada(String descricaoDetalhada) {
		this.descricaoDetalhada = descricaoDetalhada;
	}

	/**
	 * Retorna listaCompanhia
	 * @return o listaCompanhia
	 */
	public List<CompanhiaSeguradoraVO> getListaCompanhia() {
		return listaCompanhia;
	}

	/**
	 * Define listaCompanhia
	 * @param listaCompanhia o listaCompanhia a ser configurado
	 */
	public void setListaCompanhia(List<CompanhiaSeguradoraVO> listaCompanhia) {
		this.listaCompanhia = listaCompanhia;
	}

	/**
	 * Retorna listaCompanhiaOrd
	 * @return o listaCompanhiaOrd
	 */
	public List<CompanhiaSeguradoraVO> getListaCompanhiaOrd() {
		return listaCompanhiaOrd;
	}

	/**
	 * Define listaCompanhiaOrd
	 * @param listaCompanhiaOrd o listaCompanhiaOrd a ser configurado
	 */
	public void setListaCompanhiaOrd(List<CompanhiaSeguradoraVO> listaCompanhiaOrd) {
		this.listaCompanhiaOrd = listaCompanhiaOrd;
	}

	/**
	 * Retorna listaDepartamentos
	 * @return o listaDepartamentos
	 */
	public List<DepartamentoVO> getListaDepartamentos() {
		return listaDepartamentos;
	}

	/**
	 * Define listaDepartamentos
	 * @param listaDepartamentos o listaDepartamentos a ser configurado
	 */
	public void setListaDepartamentos(List<DepartamentoVO> listaDepartamentos) {
		this.listaDepartamentos = listaDepartamentos;
	}

	/**
	 * Retorna listaMotivosDepositos
	 * @return o listaMotivosDepositos
	 */
	public List<MotivoDepositoVO> getListaMotivosDepositos() {
		return listaMotivosDepositos;
	}

	/**
	 * Define listaMotivosDepositos
	 * @param listaMotivosDepositos o listaMotivosDepositos a ser configurado
	 */
	public void setListaMotivosDepositos(
			List<MotivoDepositoVO> listaMotivosDepositos) {
		this.listaMotivosDepositos = listaMotivosDepositos;
	}
	
}
