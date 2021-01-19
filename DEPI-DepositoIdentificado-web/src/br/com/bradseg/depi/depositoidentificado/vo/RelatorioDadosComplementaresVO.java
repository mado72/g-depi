package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Classe ManutencoesAnaliticoVO
 * @author Globality
 */
public class RelatorioDadosComplementaresVO implements Serializable{
    
    
    /**
     * 
     */
    private static final long serialVersionUID = 2149471107827710398L;

//    @TableField(name = "CDEP_IDTFD")
    private Long codigoAutorizador;
    
//    @TableField(name = "CDIG_DEP_IDTFD")
    private Integer digitoCodigoAutorizador;
    
//    @TableField(name = "CBCO")
    private Integer codigoBanco;
    
//    @TableField(name = "CAG_BCRIA")
    private Integer codigoAgencia;
    
//    @TableField(name = "CCTA_CORR")
    private Long codigoConta;
    
//    @TableField(name = "CTPO_GRP_RECEB")
    private Integer codigoTipoGrupoRecebimento;
    
    private String descricaoTipoGrupoRecebimento;
    
//    @TableField(name = "CSIT_DEP_ARQ_TRNSF")
    private Integer codigoSituacao;
    
    private String situacao;
    
//    @TableField(name = "DHORA_INCL_DEP")
    private Timestamp dataHoraInclusaoDeposito;
    
//    @TableField(name = "VDEP_IDTFD_ORIGN")
    private java.math.BigDecimal valorRegistrado;
    
//    @TableField(name = "DHORA_INCL_LCTO")
    private Timestamp dataHoraInclusaoLancamento;

//    @TableField(name = "VTOT_DEP_IDTFD")
    private java.math.BigDecimal valorPago;

//    @TableField(name = "CINTRN_CIA_SEGDR")
    private Integer codigoCia;
    
//    @TableField(name = "CDEPTO_DEP_IDTFD")
    private Integer codigoDepartamentoDeposito;
    
//    @TableField(name = "CMOTVO_DEP_IDTFD")
    private Integer codigoMotivoDeposito;
    
    private String descricaoMotivoDeposito;
    
//    @TableField(name = "ROBS_PARMZ_DEP")
    private String observacaoParametrizacaoDeposito;
    
//    @TableField(name = "ROBS_DEP_IDTFD")
    private String observacaoDeposito;

    private String descricaoBanco;
    private String descricaoCia;
    private String descricaoConta;
    private String codigoAutorizadorComDv;

	/**
	 * Retorna o valor do atributo codigoAutorizador.
	 * @return o valor do atributo codigoAutorizador
	 */
	public Long getCodigoAutorizador() {
		return codigoAutorizador;
	}

	/**
	 * Especifica o valor do atributo codigoAutorizador.
	 * @param codigoAutorizador - Long do codigoAutorizador a ser configurado.
	 */
	public void setCodigoAutorizador(Long codigoAutorizador) {
		this.codigoAutorizador = codigoAutorizador;
	}

	/**
	 * Retorna o valor do atributo digitoCodigoAutorizador.
	 * @return o valor do atributo digitoCodigoAutorizador
	 */
	public Integer getDigitoCodigoAutorizador() {
		return digitoCodigoAutorizador;
	}

	/**
	 * Especifica o valor do atributo digitoCodigoAutorizador.
	 * @param digitoCodigoAutorizador - Integer do digitoCodigoAutorizador a ser configurado.
	 */
	public void setDigitoCodigoAutorizador(Integer digitoCodigoAutorizador) {
		this.digitoCodigoAutorizador = digitoCodigoAutorizador;
	}

	/**
	 * Retorna o valor do atributo codigoBanco.
	 * @return o valor do atributo codigoBanco
	 */
	public Integer getCodigoBanco() {
		return codigoBanco;
	}

	/**
	 * Especifica o valor do atributo codigoBanco.
	 * @param codigoBanco - Integer do codigoBanco a ser configurado.
	 */
	public void setCodigoBanco(Integer codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	/**
	 * Retorna o valor do atributo codigoAgencia.
	 * @return o valor do atributo codigoAgencia
	 */
	public Integer getCodigoAgencia() {
		return codigoAgencia;
	}

	/**
	 * Especifica o valor do atributo codigoAgencia.
	 * @param codigoAgencia - Integer do codigoAgencia a ser configurado.
	 */
	public void setCodigoAgencia(Integer codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}

	/**
	 * Retorna o valor do atributo codigoConta.
	 * @return o valor do atributo codigoConta
	 */
	public Long getCodigoConta() {
		return codigoConta;
	}

	/**
	 * Especifica o valor do atributo codigoConta.
	 * @param codigoConta - Long do codigoConta a ser configurado.
	 */
	public void setCodigoConta(Long codigoConta) {
		this.codigoConta = codigoConta;
	}

	/**
	 * Retorna o valor do atributo codigoTipoGrupoRecebimento.
	 * @return o valor do atributo codigoTipoGrupoRecebimento
	 */
	public Integer getCodigoTipoGrupoRecebimento() {
		return codigoTipoGrupoRecebimento;
	}

	/**
	 * Especifica o valor do atributo codigoTipoGrupoRecebimento.
	 * @param codigoTipoGrupoRecebimento - Integer do codigoGrupoRecebimento a ser configurado.
	 */
	public void setCodigoTipoGrupoRecebimento(Integer codigoTipoGrupoRecebimento) {
		this.codigoTipoGrupoRecebimento = codigoTipoGrupoRecebimento;
	}

	/**
	 * Retorna o valor do atributo situacaoDepositoArquivo.
	 * @return o valor do atributo situacaoDepositoArquivo
	 */
	public Integer getCodigoSituacao() {
		return codigoSituacao;
	}

	/**
	 * Especifica o valor do atributo situacaoDepositoArquivo.
	 * @param situacaoDepositoArquivo - Integer do situacaoDepositoArquivo a ser configurado.
	 */
	public void setCodigoSituacao(Integer situacaoDepositoArquivo) {
		this.codigoSituacao = situacaoDepositoArquivo;
	}

	/**
	 * Retorna o valor do atributo dataHoraInclusaoDeposito.
	 * @return o valor do atributo dataHoraInclusaoDeposito
	 */
	public Timestamp getDataHoraInclusaoDeposito() {
		return dataHoraInclusaoDeposito;
	}

	/**
	 * Especifica o valor do atributo dataHoraInclusaoDeposito.
	 * @param dataHoraInclusaoDeposito - Timestamp do dataHoraInclusaoDeposito a ser configurado.
	 */
	public void setDataHoraInclusaoDeposito(Timestamp dataHoraInclusaoDeposito) {
		this.dataHoraInclusaoDeposito = dataHoraInclusaoDeposito;
	}

	/**
	 * Retorna o valor do atributo valorRegistrado.
	 * @return o valor do atributo valorRegistrado
	 */
	public java.math.BigDecimal getValorRegistrado() {
		return valorRegistrado;
	}

	/**
	 * Especifica o valor do atributo valorRegistrado.
	 * @param valorRegistrado - java.math.BigDecimal do valorRegistrado a ser configurado.
	 */
	public void setValorRegistrado(java.math.BigDecimal valorRegistrado) {
		this.valorRegistrado = valorRegistrado;
	}

	/**
	 * Retorna o valor do atributo dataHoraInclusaoLancamento.
	 * @return o valor do atributo dataHoraInclusaoLancamento
	 */
	public Timestamp getDataHoraInclusaoLancamento() {
		return dataHoraInclusaoLancamento;
	}

	/**
	 * Especifica o valor do atributo dataHoraInclusaoLancamento.
	 * @param dataHoraInclusaoLancamento - Timestamp do dataHoraInclusaoLancamento a ser configurado.
	 */
	public void setDataHoraInclusaoLancamento(Timestamp dataHoraInclusaoLancamento) {
		this.dataHoraInclusaoLancamento = dataHoraInclusaoLancamento;
	}

	/**
	 * Retorna o valor do atributo valorPago.
	 * @return o valor do atributo valorPago
	 */
	public java.math.BigDecimal getValorPago() {
		return valorPago;
	}

	/**
	 * Especifica o valor do atributo valorPago.
	 * @param valorPago - java.math.BigDecimal do valorPago a ser configurado.
	 */
	public void setValorPago(java.math.BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	/**
	 * Retorna o valor do atributo codigoCia.
	 * @return o valor do atributo codigoCia
	 */
	public Integer getCodigoCia() {
		return codigoCia;
	}

	/**
	 * Especifica o valor do atributo codigoCia.
	 * @param codigoCia - Integer do codigoCia a ser configurado.
	 */
	public void setCodigoCia(Integer codigoCia) {
		this.codigoCia = codigoCia;
	}

	/**
	 * Retorna o valor do atributo codigoDepartamentoDeposito.
	 * @return o valor do atributo codigoDepartamentoDeposito
	 */
	public Integer getCodigoDepartamentoDeposito() {
		return codigoDepartamentoDeposito;
	}

	/**
	 * Especifica o valor do atributo codigoDepartamentoDeposito.
	 * @param codigoDepartamentoDeposito - Integer do codigoDepartamentoDeposito a ser configurado.
	 */
	public void setCodigoDepartamentoDeposito(Integer codigoDepartamentoDeposito) {
		this.codigoDepartamentoDeposito = codigoDepartamentoDeposito;
	}

	/**
	 * Retorna o valor do atributo codigoMotivoDeposito.
	 * @return o valor do atributo codigoMotivoDeposito
	 */
	public Integer getCodigoMotivoDeposito() {
		return codigoMotivoDeposito;
	}

	/**
	 * Especifica o valor do atributo codigoMotivoDeposito.
	 * @param codigoMotivoDeposito - Integer do codigoMotivoDeposito a ser configurado.
	 */
	public void setCodigoMotivoDeposito(Integer codigoMotivoDeposito) {
		this.codigoMotivoDeposito = codigoMotivoDeposito;
	}

	/**
	 * Retorna o valor do atributo observacaoParametrizacaoDeposito.
	 * @return o valor do atributo observacaoParametrizacaoDeposito
	 */
	public String getObservacaoParametrizacaoDeposito() {
		return observacaoParametrizacaoDeposito;
	}

	/**
	 * Especifica o valor do atributo observacaoParametrizacaoDeposito.
	 * @param observacaoParametrizacaoDeposito - String do observacaoParametrizacaoDeposito a ser configurado.
	 */
	public void setObservacaoParametrizacaoDeposito(String observacaoParametrizacaoDeposito) {
		this.observacaoParametrizacaoDeposito = observacaoParametrizacaoDeposito;
	}

	/**
	 * Retorna o valor do atributo observacaoDeposito.
	 * @return o valor do atributo observacaoDeposito
	 */
	public String getObservacaoDeposito() {
		return observacaoDeposito;
	}

	/**
	 * Especifica o valor do atributo observacaoDeposito.
	 * @param observacaoDeposito - String do observacaoDeposito a ser configurado.
	 */
	public void setObservacaoDeposito(String observacaoDeposito) {
		this.observacaoDeposito = observacaoDeposito;
	}

	/**
	 * Retorna o valor do atributo descricaoBanco.
	 * @return o valor do atributo descricaoBanco
	 */
	public String getDescricaoBanco() {
		return descricaoBanco;
	}

	/**
	 * Especifica o valor do atributo descricaoBanco.
	 * @param descricaoBanco - String do descricaoBanco a ser configurado.
	 */
	public void setDescricaoBanco(String descricaoBanco) {
		this.descricaoBanco = descricaoBanco;
	}

	/**
	 * Retorna o valor do atributo descricaoCia.
	 * @return o valor do atributo descricaoCia
	 */
	public String getDescricaoCia() {
		return descricaoCia;
	}

	/**
	 * Especifica o valor do atributo descricaoCia.
	 * @param descricaoCia - String do descricaoCia a ser configurado.
	 */
	public void setDescricaoCia(String descricaoCia) {
		this.descricaoCia = descricaoCia;
	}

	/**
	 * Retorna o valor do atributo descricaoConta.
	 * @return o valor do atributo descricaoConta
	 */
	public String getDescricaoConta() {
		return descricaoConta;
	}

	/**
	 * Especifica o valor do atributo descricaoConta.
	 * @param descricaoConta - String do descricaoConta a ser configurado.
	 */
	public void setDescricaoConta(String descricaoConta) {
		this.descricaoConta = descricaoConta;
	}

	/**
	 * Retorna o valor do atributo situacao.
	 * @return o valor do atributo situacao
	 */
	public String getSituacao() {
		return situacao;
	}

	/**
	 * Especifica o valor do atributo situacao.
	 * @param situacao - String do situacao a ser configurado.
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	/**
	 * Retorna o valor do atributo descricaoMotivoDeposito.
	 * @return o valor do atributo descricaoMotivoDeposito
	 */
	public String getDescricaoMotivoDeposito() {
		return descricaoMotivoDeposito;
	}

	/**
	 * Especifica o valor do atributo descricaoMotivoDeposito.
	 * @param descricaoMotivoDeposito - String do descricaoMotivoDeposito a ser configurado.
	 */
	public void setDescricaoMotivoDeposito(String descricaoMotivoDeposito) {
		this.descricaoMotivoDeposito = descricaoMotivoDeposito;
	}

	/**
	 * Retorna o valor do atributo descricaoTipoGrupoRecebimento.
	 * @return o valor do atributo descricaoTipoGrupoRecebimento
	 */
	public String getDescricaoTipoGrupoRecebimento() {
		return descricaoTipoGrupoRecebimento;
	}

	/**
	 * Especifica o valor do atributo descricaoTipoGrupoRecebimento.
	 * @param descricaoTipoGrupoRecebimento - String do descricaoTipoGrupoRecebimento a ser configurado.
	 */
	public void setDescricaoTipoGrupoRecebimento(
			String descricaoTipoGrupoRecebimento) {
		this.descricaoTipoGrupoRecebimento = descricaoTipoGrupoRecebimento;
	}

    /**
     * Retorna o codigoAutorizadorComDv.
     * @return O atributo codigoAutorizadorComDv
     */
    public String getCodigoAutorizadorComDv() {
        return codigoAutorizadorComDv;
    }

    /**
     * Especifica o codigoAutorizadorComDv.
     * @param codigoAutorizadorComDv String do codigoAutorizadorComDv a ser setado
     */
    public void setCodigoAutorizadorComDv(String codigoAutorizadorComDv) {
        this.codigoAutorizadorComDv = codigoAutorizadorComDv;
    }

}
