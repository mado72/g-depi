package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;

/**
 * Valor Object - Entidade - Lançamento Depósito.
 * Table(schema = ConstantesDAO.SCHEMA_BANCO, table = "LCTO_DEP_IDTFD")
 */
public class LancamentoDepositoVO implements Serializable{
    private static final long serialVersionUID = 4290884587556051541L;

    /**
     * CDEP_IDTFD 
     */
    private long codigoLancamentoDeposito;

    /**
     * CAG_BCRIA_ACOLH
     */
    private AgenciaVO agenciaAcolhedora;

    /**
     * CDIG_AG_ACOLH 
     */
    private String digito;

    /**
     * VDEP_IDTFD_DINHE
     */
    private BigDecimal valorDinheiro;

    /**
     * VDEP_IDTFD_CHEQ 
     */
    private BigDecimal valorCheq;

    /**
     * VTOT_DEP_IDTFD 
     */
    private BigDecimal valorTotalDeposito;

    /**
     * CTERM_AG_ACOLH
     */
    private long termoAgenciaAcolhedora;

    /**
     * CBCO_CHEQ_LCTO
     */
    private BancoVO bancoCheque;

    /**
     * CAG_BCRIA_CHEQ
     */
    private AgenciaVO agenciaCheque;

    /**
     * CCHEQ_LCTO_DEP
     */
    private long cheque;

    /**
     * CCTA_CORR_CHEQ
     */
    private long contaCheque;
    /**
    * data da criação.
    * DHORA_INCL_REG
    */
   private Date dataInclusao;

   /**
    * data da última atualização.
    * DHORA_ULT_ATULZ
    */
   private Date dataHoraAtualizacao;

   /**
    * código do responsável pela última alteração.
    * CUSUAR_RESP_ATULZ
    */
   private BigDecimal codigoResponsavelUltimaAtualizacao;

   /**
    * indicador que sinaliza se foi excluido logicamente. 'S' = excluída; 'N' = não excluída; 'D' = desativado
    * CIND_REG_ATIVO
    */
   private String codigoIndicativoAtivo = "S";

    /**
	 * Construtor
	 * @param codigoLancamentoDeposito - long
	 */
	public LancamentoDepositoVO(long codigoLancamentoDeposito) {
	    super();
	    this.codigoLancamentoDeposito = codigoLancamentoDeposito;
	}

	/**
	 * Construtor.
	 */
	public LancamentoDepositoVO() {
	    super();
	}

	/**
     * Retorna o valor do atributo valorTotalDeposito.
     * @return o valor do atributo valorTotalDeposito
     */
    public BigDecimal getValorTotalDeposito() {
        return valorTotalDeposito;
    }

    /**
     * Especifica o valor do atributo valorTotalDeposito.
     * @param valorTotalDeposito - double do valorTotalDeposito a ser configurado.
     */
    public void setValorTotalDeposito(BigDecimal valorTotalDeposito) {
        this.valorTotalDeposito = valorTotalDeposito;
    }

    /**
     * Retorna o valor do atributo codigoLancamentoDeposito.
     * @return o valor do atributo codigoLancamentoDeposito
     */
    public long getCodigoLancamentoDeposito() {
        return codigoLancamentoDeposito;
    }

    /**
     * Especifica o valor do atributo codigoLancamentoDeposito.
     * @param codigoLancamentoDeposito - long do codigoLancamentoDeposito a ser configurado.
     */
    public void setCodigoLancamentoDeposito(long codigoLancamentoDeposito) {
        this.codigoLancamentoDeposito = codigoLancamentoDeposito;
    }

    /**
     * Retorna o valor do atributo serialVersionUID.
     * @return o valor do atributo serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * Retorna o valor do atributo agenciaCheque.
     * @return o valor do atributo agenciaCheque
     */
    public AgenciaVO getAgenciaCheque() {
        return agenciaCheque;
    }

    /**
     * Especifica o valor do atributo agenciaCheque.
     * @param agenciaCheque - int do agenciaCheque a ser configurado.
     */
    public void setAgenciaCheque(AgenciaVO agenciaCheque) {
        this.agenciaCheque = agenciaCheque;
    }

    /**
     * Retorna o valor do atributo bancoCheque.
     * @return o valor do atributo bancoCheque
     */
    public BancoVO getBancoCheque() {
        return bancoCheque;
    }

    /**
     * Especifica o valor do atributo bancoCheque.
     * @param bancoCheque - int do bancoCheque a ser configurado.
     */
    public void setBancoCheque(BancoVO bancoCheque) {
        this.bancoCheque = bancoCheque;
    }

    /**
     * Retorna o valor do atributo cheque.
     * @return o valor do atributo cheque
     */
    public long getCheque() {
        return cheque;
    }

    /**
     * Especifica o valor do atributo cheque.
     * @param cheque - long do cheque a ser configurado.
     */
    public void setCheque(long cheque) {
        this.cheque = cheque;
    }

    /**
     * Retorna o valor do atributo contaCheque.
     * @return o valor do atributo contaCheque
     */
    public long getContaCheque() {
        return contaCheque;
    }

    /**
     * Especifica o valor do atributo contaCheque.
     * @param contaCheque - long do contaCheque a ser configurado.
     */
    public void setContaCheque(long contaCheque) {
        this.contaCheque = contaCheque;
    }

    /**
     * Retorna o valor do atributo termoAgenciaAcolhedora.
     * @return o valor do atributo termoAgenciaAcolhedora
     */
    public long getTermoAgenciaAcolhedora() {
        return termoAgenciaAcolhedora;
    }

    /**
     * Especifica o valor do atributo termoAgenciaAcolhedora.
     * @param termoAgenciaAcolhedora - long do termoAgenciaAcolhedora a ser configurado.
     */
    public void setTermoAgenciaAcolhedora(long termoAgenciaAcolhedora) {
        this.termoAgenciaAcolhedora = termoAgenciaAcolhedora;
    }

    /**
     * Retorna o valor do atributo valorCheq.
     * @return o valor do atributo valorCheq
     */
    public BigDecimal getValorCheq() {
        return valorCheq;
    }

    /**
     * Especifica o valor do atributo valorCheq.
     * @param valorCheq - BigDecimal do valorCheq a ser configurado.
     */
    public void setValorCheq(BigDecimal valorCheq) {
        this.valorCheq = valorCheq;
    }

    /**
     * Retorna o valor do atributo valorDinheiro.
     * @return o valor do atributo valorDinheiro
     */
    public BigDecimal getValorDinheiro() {
        return valorDinheiro;
    }

    /**
     * Especifica o valor do atributo valorDinheiro.
     * @param valorDinheiro - BigDecimal do valorDinheiro a ser configurado.
     */
    public void setValorDinheiro(BigDecimal valorDinheiro) {
        this.valorDinheiro = valorDinheiro;
    }

    /**
     * Retorna o valor do atributo agenciaAcolhedora.
     * @return o valor do atributo agenciaAcolhedora
     */
    public AgenciaVO getAgenciaAcolhedora() {
        return agenciaAcolhedora;
    }

    /**
     * Especifica o valor do atributo agenciaAcolhedora.
     * @param agenciaAcolhedora - int do agenciaAcolhedora a ser configurado.
     */
    public void setAgenciaAcolhedora(AgenciaVO agenciaAcolhedora) {
        this.agenciaAcolhedora = agenciaAcolhedora;
    }

    /**
     * Retorna o valor do atributo digito.
     * @return o valor do atributo digito
     */
    public String getDigito() {
        return digito;
    }

    /**
     * Especifica o valor do atributo digito.
     * @param digito - String do digito a ser configurado.
     */
    public void setDigito(String digito) {
        this.digito = digito;
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
	 * Retorna codigoResponsavelUltimaAtualizacao
	 * @return o codigoResponsavelUltimaAtualizacao
	 */
	public BigDecimal getCodigoResponsavelUltimaAtualizacao() {
		return codigoResponsavelUltimaAtualizacao;
	}
	
	/**
	 * Define codigoResponsavelUltimaAtualizacao
	 * @param codigoResponsavelUltimaAtualizacao valor codigoResponsavelUltimaAtualizacao a ser definido
	 */
	public void setCodigoResponsavelUltimaAtualizacao(
			BigDecimal codigoResponsavelUltimaAtualizacao) {
		this.codigoResponsavelUltimaAtualizacao = codigoResponsavelUltimaAtualizacao;
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
}
