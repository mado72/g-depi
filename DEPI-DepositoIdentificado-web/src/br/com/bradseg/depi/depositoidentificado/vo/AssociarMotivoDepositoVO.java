/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import java.util.Date;

import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;

/**
 * Classe Anotada de Mapeamento com a tabela CTA_CORR_MOTVO.
 */
//table = "CTA_CORR_MOTVO_DEP")
public class AssociarMotivoDepositoVO implements Serializable {

	private static final long serialVersionUID = -1914046307346454242L;

	/**
     * Código da Companhia Seguradora.
     */
	//ConstantesDAO.TABELA_COMPANHIA_DEPARTAMENTO_ID)
	private CompanhiaSeguradoraVO cia = new CompanhiaSeguradoraVO();

	/**
     * codigo do departamento - DepartamentoVO obtido atraves da classe DepartamentoConverter
     */
	//ConstantesDAO.TABELA_DEPARTAMENTO_ID)
	private DepartamentoVO departamento = new DepartamentoVO();

	/**
     * codigo do departamento - MotivoDepositoVO obtido atraves da classe MotivoDepositoConverter
     */
	//ConstantesDAO.TABELA_MOTIVO_ID)
	private MotivoDepositoVO motivoDeposito = new MotivoDepositoVO();

	//ConstantesDAO.TABELA_CONTA_CORRENTE_AUTORIZADA_BANCO)
	private BancoVO banco = new BancoVO();

	//ConstantesDAO.TABELA_CONTA_CORRENTE_AUTORIZADA_AGENCIA)
	private Integer codigoAgencia;

	//ConstantesDAO.TABELA_CONTA_CORRENTE_AUTORIZADA_CONTA_CORRENTE)
	private Long contaCorrente;
	
	private Integer codigoResponsavelUltimaAtualizacao;
	
	private Date dataInclusao;
	
	private Date dataHoraAtualizacao;
	
    /**
     * Indicado Registro Ativo.
     */
    private String indicadoRegistroAtivo;
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder(" Associa\u00E7\u00E3o de Motivo: [Cia: ")
    		.append(getCia().getCodigoCompanhia())
    		.append("; Departamento: ").append(getDepartamento().getCodigoDepartamento())
    		.append("; Motivo: ").append(getMotivoDeposito().getCodigoMotivoDeposito())
    		.append("; Banco: ").append(getBanco().getCdBancoExterno())
    		.append(", Ag.: ").append(getCodigoAgencia())
    		.append(", CC.: ").append(getContaCorrente()).append("]");
        return sb.toString();
    }

	/**
     * Retorna a chave composta.
     * @return String - chave composta.
     */
	public String getChaveComposta() {
		String dot = ".";
		return new StringBuilder().append(this.getCia().getCodigoCompanhia()).append(dot).append(
		    this.getDepartamento().getCodigoDepartamento()).append(dot).append(
		    this.getMotivoDeposito().getCodigoMotivoDeposito()).append(dot).append(this.getBanco().getCdBancoExterno())
		    .append(dot).append(this.getCodigoAgencia()).append(dot).append(this.getContaCorrente()).toString();
	}

	/**
     * Retorna o valor do atributo banco.
     * @return o valor do atributo banco
     */
	public BancoVO getBanco() {
		return banco;
	}

	/**
     * Especifica o valor do atributo banco.
     * @param banco - BancoVO do banco a ser configurado.
     */
	public void setBanco(BancoVO banco) {
		this.banco = banco;
	}

	/**
     * Retorna o valor do atributo codigoAgencia.
     * @return o valor do atributo codigoAgencia
     */
	public int getCodigoAgencia() {
		return codigoAgencia;
	}

	/**
     * Especifica o valor do atributo codigoAgencia.
     * @param codigoAgencia - int do codigoAgencia a ser configurado.
     */
	public void setCodigoAgencia(Integer codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}

	/**
     * Retorna o valor do atributo contaCorrente.
     * @return o valor do atributo contaCorrente
     */
	public Long getContaCorrente() {
		return contaCorrente;
	}

	/**
     * Especifica o valor do atributo contaCorrente.
     * @param contaCorrente - long do contaCorrente a ser configurado.
     */
	public void setContaCorrente(Long contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	/**
     * construtor do vo
     */
	public AssociarMotivoDepositoVO() {
		super();
	}

	/**
     * retorna o objeto DepartamentoVO
     * @return o departamento
     */
	public DepartamentoVO getDepartamento() {
		return departamento;
	}

	/**
     * configura o DepartamentoVO
     * @param pDepartamento o departamento a ser configurado
     */
	public void setDepartamento(DepartamentoVO pDepartamento) {
		this.departamento = pDepartamento;
	}

	/**
     * retorna o objeto CompanhiaSeguradoraVO
     * @return a companhia seguradora
     */

	public CompanhiaSeguradoraVO getCia() {
		return cia;
	}

	/**
     * configura o CompanhiaSeguradoraVO
     * @param cia a companhia seguradora a ser configurado
     */

	public void setCia(CompanhiaSeguradoraVO cia) {
		this.cia = cia;
	}

	/**
     * retorna o objeto MotivoDepositoVO
     * @return o motivoDeposito
     */
	public MotivoDepositoVO getMotivoDeposito() {
		return motivoDeposito;
	}

	/**
     * configura o MotivoDepositoVO
     * @param pMotivoDeposito o motivoDeposito a ser configurado
     */
	public void setMotivoDeposito(MotivoDepositoVO pMotivoDeposito) {
		this.motivoDeposito = pMotivoDeposito;
	}

	/**
     * retorna o objeto codigoResponsavelUltimaAtualizacao
     * @return o codigoResponsavelUltimaAtualizacao
     */
	public Integer getCodigoResponsavelUltimaAtualizacao() {
		return codigoResponsavelUltimaAtualizacao;
	}
	
	/**
     * configura o codigoResponsavelUltimaAtualizacao
     * @param codigoResponsavelUltimaAtualizacao o codigoResponsavelUltimaAtualizacao a ser configurado
     */
	public void setCodigoResponsavelUltimaAtualizacao(Integer codigoResponsavelUltimaAtualizacao) {
		this.codigoResponsavelUltimaAtualizacao = codigoResponsavelUltimaAtualizacao;
	}

	
    /**
     * Retorna o IndicadoRegistroAtivo.
     * @return O atributo indicadoRegistroAtivo
     */
	public String getIndicadoRegistroAtivo() {
		return indicadoRegistroAtivo;
	}
	
    /**
     * Especifica o IndicadoRegistroAtivo.
     * @param indicadoRegistroAtivo String do codigoDepartamento a ser setado
     */
	public void setIndicadoRegistroAtivo(String indicadoRegistroAtivo) {
		this.indicadoRegistroAtivo = indicadoRegistroAtivo;
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
