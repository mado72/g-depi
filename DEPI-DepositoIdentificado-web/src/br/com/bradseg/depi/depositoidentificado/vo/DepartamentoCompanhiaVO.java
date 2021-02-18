/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import java.util.Date;

import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;

/**
 * Classe para a tabela CIA_DEPTO_DEP.
 */
public class DepartamentoCompanhiaVO implements Serializable {

	private static final long serialVersionUID = 7238460393435546073L;

	/**
     * Código da Companhia - obtido atraves da classe
     */
	private CompanhiaSeguradoraVO companhia = new CompanhiaSeguradoraVO();
	
	/**
	 * Departamentos associado.
	 */
	private DepartamentoVO departamento = new DepartamentoVO();
	
	/**
	 * Código Responsável Ultima Atualização.
	 */
	private Integer codigoResponsavelUltimaAtualizacao;

	/**
	 * Indica se o registro está ativo 
	 */
	private String codigoIndicativoAtivo = "S";
	
    /**
     * Data da inclusão do registro 
     */
    private Date dataInclusao;

    /**
     * data da última atualização
     */
    private Date dataHoraAtualizacao;


	/**
     * Construtor default
     */
	public DepartamentoCompanhiaVO() {
		super();
	}
	
	/**
	 * Construtor para definir companhia e departamento
	 * @param companhia Companhia
	 * @param departamento Departamento
	 */
	public DepartamentoCompanhiaVO(CompanhiaSeguradoraVO companhia,
			DepartamentoVO departamento) {
		super();
		this.companhia = companhia;
		this.departamento = departamento;
	}

	/**
	 * Retorna um código composto que representa a relação Depto x Cia
	 * @return Código composto
	 */
	public String getCodigoComposto() {
		StringBuilder sb = new StringBuilder();
		sb.append(getCompanhia().getCodigoCompanhia())
				.append(";")
				.append(getDepartamento().getCodigoDepartamento());
		return sb.toString();
	}

	/**
	 * Retorna companhia
	 * @return o companhia
	 */
	public CompanhiaSeguradoraVO getCompanhia() {
		return companhia;
	}

	/**
	 * Define companhia
	 * @param companhia valor companhia a ser definido
	 */
	public void setCompanhia(CompanhiaSeguradoraVO companhia) {
		this.companhia = companhia;
	}

	/**
	 * Retorna departamento
	 * @return o departamento
	 */
	public DepartamentoVO getDepartamento() {
		return departamento;
	}
	
	/**
	 * Define departamento
	 * @param departamento valor departamento a ser definido
	 */
	public void setDepartamento(DepartamentoVO departamento) {
		this.departamento = departamento;
	}

	/**
	 * Retorna codigoResponsavelUltimaAtualizacao
	 * @return o codigoResponsavelUltimaAtualizacao
	 */
	public Integer getCodigoResponsavelUltimaAtualizacao() {
		return codigoResponsavelUltimaAtualizacao;
	}

	/**
	 * Define codigoResponsavelUltimaAtualizacao
	 * @param codigoResponsavelUltimaAtualizacao valor codigoResponsavelUltimaAtualizacao a ser definido
	 */
	public void setCodigoResponsavelUltimaAtualizacao(
			Integer codigoResponsavelUltimaAtualizacao) {
		this.codigoResponsavelUltimaAtualizacao = codigoResponsavelUltimaAtualizacao;
	}

	/**
	 * Retorna codigoIndicativoAtivo
	 * @return o codigoIndicativoAtivo
	 */
	public String getCodigoIndicativoAtivo() {
		return codigoIndicativoAtivo;
	}

	/**
	 * Define codigoIndicativoAtivo
	 * @param codigoIndicativoAtivo valor codigoIndicativoAtivo a ser definido
	 */
	public void setCodigoIndicativoAtivo(String codigoIndicativoAtivo) {
		this.codigoIndicativoAtivo = codigoIndicativoAtivo;
	}

	/**
	 * Retorna dataInclusao
	 * @return o dataInclusao
	 */
	public Date getDataInclusao() {
		return BaseUtil.getDate(dataInclusao);
	}

	/**
	 * Define dataInclusao
	 * @param dataInclusao valor dataInclusao a ser definido
	 */
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = BaseUtil.getDate(dataInclusao);
	}

	/**
	 * Retorna dataHoraAtualizacao
	 * @return o dataHoraAtualizacao
	 */
	public Date getDataHoraAtualizacao() {
		return BaseUtil.getDate(dataHoraAtualizacao);
	}

	/**
	 * Define dataHoraAtualizacao
	 * @param dataHoraAtualizacao valor dataHoraAtualizacao a ser definido
	 */
	public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
		this.dataHoraAtualizacao = BaseUtil.getDate(dataHoraAtualizacao);
	}
	
}
