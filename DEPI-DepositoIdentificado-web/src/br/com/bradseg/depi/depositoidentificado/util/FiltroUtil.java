package br.com.bradseg.depi.depositoidentificado.util;

import java.util.Date;
/**
 * A(O)FiltroUtil.
 * @return the FiltroUtil
 */
public class FiltroUtil {

	private Date    dataInicio;
	private Date    dataFinal;
	private Integer codigoCia;
	private Integer codigoDepartamento;
	private Integer codigoMotivo;
	private Integer apolice;
	private Integer sucursal;
	private Integer endosso;
	private Integer tipoDeposito;
	private Integer codigoAutorizador;
	private Integer situacaoManutencao;
	private Double  valorInicial;
	private Double  valorFinal;
	private String  cpfCnpj;
	private Integer situacaoArquivo; 
	private String  ip; 
	private String  usuario; 
	private String  descricaoBasica;
    private String  descricaoDetalhada;
	private String  sigla;
    private String  nome;
	
	/**
	 * Obtem ip.
	 * @return the situacaoArquivo
	 */	
	public String getIp() {
		return ip;
	}
	/**
	 * Define ip.
	 * {@inheritDoc}
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * Obtem usuario.
	 * @return the situacaoArquivo
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * Define usuario.
	 * {@inheritDoc}
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * Obtem situacaoArquivo.
	 * @return the situacaoArquivo
	 */
	public Integer getSituacaoArquivo() {
		return situacaoArquivo;
	}
	/**
	 * Define situacaoArquivo.
	 * @param situacaoArquivo the situacaoArquivo to set
	 * {@inheritDoc}
	 */
	public void setSituacaoArquivo(Integer situacaoArquivo) {
		this.situacaoArquivo = situacaoArquivo;
	}
	/**
	 * Define situacaoManutencao.
	 * @param situacaoManutencao the situacaoManutencao to set
	 * {@inheritDoc}
	 */
	public void setSituacaoManutencao(Integer situacaoManutencao) {
		this.situacaoManutencao = situacaoManutencao;
	}
	/**
	 * Obtem dataInicio.
	 * @return the dataInicio
	 */
	public Date getDataInicio() {
		return (Date) dataInicio.clone();
	}
	/**
	 * Define dataInicio.
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = (Date) dataInicio.clone();
	}
	/**
	 * Obtem dataFinal.
	 * @return the dataFinal
	 */
	public Date getDataFinal() {
		return (Date) dataFinal.clone();
	}
	/**
	 * Define dataFinal.
	 * @param dataFinal the dataFinal to set
	 */
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = (Date) dataFinal.clone();
	}
	/**
	 * Obtem codigoCia.
	 * @return the codigoCia
	 */
	public Integer getCodigoCia() {
		return codigoCia;
	}
	/**
	 * Define codigoCia.
	 * @param codigoCia the codigoCia to set
	 */
	public void setCodigoCia(Integer codigoCia) {
		this.codigoCia = codigoCia;
	}
	/**
	 * Obtem codigoDepartamento.
	 * @return the codigoDepartamento
	 */
	public Integer getCodigoDepartamento() {
		return codigoDepartamento;
	}
	/**
	 * Define codigoDepartamento.
	 * @param codigoDepartamento the codigoDepartamento to set
	 */
	public void setCodigoDepartamento(Integer codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}
	/**
	 * Obtem codigoMotivo.
	 * @return the codigoMotivo
	 */
	public Integer getCodigoMotivo() {
		return codigoMotivo;
	}
	/**
	 * Define codigoMotivo.
	 * @param codigoMotivo the codigoMotivo to set
	 */
	public void setCodigoMotivo(Integer codigoMotivo) {
		this.codigoMotivo = codigoMotivo;
	}
	/**
	 * Obtem apolice.
	 * @return the apolice
	 */
	public Integer getApolice() {
		return apolice;
	}
	/**
	 * Define apolice.
	 * @param apolice the apolice to set
	 */
	public void setApolice(Integer apolice) {
		this.apolice = apolice;
	}
	/**
	 * Obtem sucursal.
	 * @return the sucursal
	 */
	public Integer getSucursal() {
		return sucursal;
	}
	/**
	 * Define Sucursal.
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}
	/**
	 * Obtem endosso.
	 * @return the endosso
	 */
	public Integer getEndosso() {
		return endosso;
	}
	/**
	 * Define endosso.
	 * @param endosso the endosso to set
	 */
	public void setEndosso(Integer endosso) {
		this.endosso = endosso;
	}
	/**
	 * Obtem tipoDeposito.
	 * @return the tipoDeposito
	 */
	public Integer getTipoDeposito() {
		return tipoDeposito;
	}
	/**
	 * Define tipoDeposito.
	 * @param tipoDeposito the tipoDeposito to set
	 */
	public void setTipoDeposito(Integer tipoDeposito) {
		this.tipoDeposito = tipoDeposito;
	}
	/**
	 * Obtem codigoAutorizador.
	 * @return the codigoAutorizador
	 */
	public Integer getCodigoAutorizador() {
		return codigoAutorizador;
	}
	/**
	 * Define codigoAutorizador.
	 * @param codigoAutorizador the codigoAutorizador to set
	 */
	public void setCodigoAutorizador(Integer codigoAutorizador) {
		this.codigoAutorizador = codigoAutorizador;
	}
	/**
	 * Obtem situacaoManutencao.
	 * @return the situacaoManutecao
	 */
	public Integer getSituacaoManutencao() {
		return situacaoManutencao;
	}
	/**
	 * Define situacaoManutencao.
	 * {@inheritDoc}
	 */
	public void setSituacaoManutecao(Integer situacaoManutencao) {
		this.situacaoManutencao = situacaoManutencao;
	}
	/**
	 * Obtem valorInicial.
	 * @return the valorInicial
	 */
	public Double getValorInicial() {
		return valorInicial;
	}
	/**
	 * Define valorInicial.
	 * @param valorInicial the valorInicial to set
	 */
	public void setValorInicial(Double valorInicial) {
		this.valorInicial = valorInicial;
	}
	/**
	 * Obtem valorFinal.
	 * @return the valorFinal
	 */
	public Double getValorFinal() {
		return valorFinal;
	}
	/**
	 * Define valorFinal.
	 * @param valorFinal the valorFinal to set
	 */
	public void setValorFinal(Double valorFinal) {
		this.valorFinal = valorFinal;
	}
	/**
	 * Obtem cpfCnpj.
	 * @return the cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	/**
	 * Define cpfCnpj.
	 * @param cpfCnpj the cpfCnpj to set
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	
	
	public String getDescricaoBasica() {
		return descricaoBasica;
	}
	public void setDescricaoBasica(String descricaoBasica) {
		this.descricaoBasica = descricaoBasica;
	}
	public String getDescricaoDetalhada() {
		return descricaoDetalhada;
	}
	public void setDescricaoDetalhada(String descricaoDetalhada) {
		this.descricaoDetalhada = descricaoDetalhada;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
