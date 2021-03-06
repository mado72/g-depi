package br.com.bradseg.depi.depositoidentificado.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;

/**
 * <p>FiltroUtil armazena os critérios de consulta e provê métodos utilitários para
 * preparar as consultas no banco de dados utilizando o
 * {@link org.springframework.jdbc.core.JdbcTemplate}.</p>
 */
public class FiltroUtil {

	// FIXME Remover referências diretas aos campos. Utilizar a lista de critérios.
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
    
    private List<CriterioConsultaVO<?>> criterios;
	
	/**
	 * Obtem ip.
	 * @return the situacaoArquivo
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * Define ip
	 * @param ip o ip a ser configurado
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
	 * Define usuario
	 * @param usuario valor usuario a ser definido
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
	 * Define situacaoArquivo
	 * @param situacaoArquivo valor situacaoArquivo a ser definido
	 */
	public void setSituacaoArquivo(Integer situacaoArquivo) {
		this.situacaoArquivo = situacaoArquivo;
	}
	/**
	 * Define situacaoManutencao
	 * @param situacaoManutencao valor situacaoManutencao a ser definido
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
	 * Define situacaoManutencao
	 * @param situacaoManutencao o valor a ser utilizado
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
	
	
	/* ------------------------------------------------------------------------------------ */
	/* ------------------------------------------------------------------------------------ */
	/* ------------------------------------------------------------------------------------ */
	/* ------------------------------------------------------------------------------------ */
	/* ------------------------------------------------------------------------------------ */

	
	/**
	 * Lista com critérios para filtrar consulta
	 * @return list
	 */
	public List<CriterioConsultaVO<?>> getCriterios() {
		return criterios;
	}
	/**
	 * Define a lista com critérios para filtrar consulta
	 * @param criterios Lista com critérios
	 */
	public void setCriterios(List<CriterioConsultaVO<?>> criterios) {
		this.criterios = criterios;
	}
	
	/**
	 * Método utilitário para montar a cláusula WHERE baseado nos
	 * {@link #criterios}
	 * 
	 * @return Cláusula where.
	 */
	public String getClausulaWhereFiltro () {
		
		StringBuilder where = new StringBuilder(" WHERE ")
			.append(getClausulasParciais());
		
	    return where.toString();
	}
	
	/**
	 * Método utilitário para adicionar um critério ao filtro
	 * @param criterio Critério
	 */
	public void adicionaCriterio(CriterioConsultaVO<?> criterio) {
		if (this.criterios == null) {
			this.criterios = new ArrayList<CriterioConsultaVO<?>>();
		}
		this.criterios.add(criterio);
	}
	
	/**
	 * Método utilitário para escrever os critérios de consulta unidos por
	 * <code>AND</code>.
	 * 
	 * @return String contendo os critérios de consulta.
	 */
	public String getClausulasParciais () {
	    return getClausulasParciais(" AND ", false);
	}
	
	/**
	 * Método utilitário para montar as cláusulas parciais da query. É util para
	 * ser utilizado quando os critérios da consulta já possuam uma cláusula
	 * WHERE inicial.
	 * 
	 * @param operador
	 *            Operador que une as partes: AND, OR. Deve adicionar os espaços
	 *            necessários " AND " ou " OR "
	 * @param acrescentaNoInicio
	 *            Quando verdadeiro acrescenta o operador no início do resultado
	 *            desta função
	 * @return Cláusula de consulta baseada nos critérios estabelecidos.
	 */
	public String getClausulasParciais(String operador, boolean acrescentaNoInicio) {
		StringBuilder clausulas = new StringBuilder();
		
		for (CriterioConsultaVO<?> item : criterios) {
			if (acrescentaNoInicio || clausulas.length() > 0) {
				clausulas.append(operador);
			}
			clausulas.append(item.getClausula());
		}
		
		return clausulas.toString();
	}
	
	/**
	 * Prepara um {@link MapSqlParameterSource} com base nos critérios de
	 * consulta.
	 * 
	 * @return parâmetros da consulta.
	 */
	public MapSqlParameterSource getMapParamFiltro () {
		
		MapSqlParameterSource params = new MapSqlParameterSource();

	    for(CriterioConsultaVO<?> criterio : criterios) {
	    	String param = criterio.getParam();
	    	if (param != null) {
	    		String valorFormatado = criterio.getValorFormatado();
	    		params.addValue(param, valorFormatado);
	    	}
	    }
	    
	    return params;
	}

}
