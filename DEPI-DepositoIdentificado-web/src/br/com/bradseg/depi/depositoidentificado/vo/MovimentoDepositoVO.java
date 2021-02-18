package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Classe Anotada de Mapeamento com a tabela MOVTO_FINCR_DEP - Movimentos dos Dep�sitos Identificados.
 * @author Globality
 */
//@Table(schema = ConstantesDEPI.SCHEMA_BANCO, table = "MOVTO_FINCR_DEP")
public class MovimentoDepositoVO implements Serializable {
    private static final long serialVersionUID = 7598925457357586193L;

    /**
     * Construtor
     */
    public MovimentoDepositoVO() {
        super();
    }

    /**
     * Construtor
     * @param codigoMovimento - long
     */
    public MovimentoDepositoVO(long codigoMovimento) {
        super();
        this.codigoMovimento = codigoMovimento;
    }

    /**
     * Objeto de Deposito atrelado.
     */
//    @TableField(name = "CDEP_IDTFD")
    private long codigoMovimento; // Trata o código do Dep�sito como proprio.

    /**
     * A��es. Esse campo dever� ser gravado �A� para Aceite, �R� para Receita, �T� para Transfer�ncia e �D� para Devolu��o.
     */
//   @TableField(name = "CTPO_ACAO_MOVTO")
    private String indicacaoAcao;

    /**
     * Codigo do BancoVO.
     */
//    @TableField(name = "CBCO")
    private long bancoMovimento;

    /**
     * Código da Agência.
     */
//    @TableField(name = "CAG_MOVTO", converter = BigDecimalToLongPersistenceConverter.class)
    private long agenciaMovimento;

    /**
     * Código da Conta do movimento.
     */
//    @TableField(name = "CCTA_MOVTO")
    private long contaMovimento;

    /**
     * N�mero do Cheque.
     */
//    @TableField(name = "CCHEQ_DEVLC")
    private long nroCheque;

    /**
     * Hist�rico
     */
//    @TableField(name = "ROBS_MOVTO_FINCR")
    private String observacao;

    /**
     * data da �ltima atualiza��o.
     */
//    @TableField(name = "DHORA_ULT_ATULZ")
    private Date dataHoraAtualizacao;

    /**
     * código do responsável pela �ltima alteração.
     */
//    @TableField(name = "CUSUAR_RESP_ATULZ")
    private BigDecimal codigoResponsavelUltimaAtualizacao;

    /**
     * indicador que sinaliza se foi excluido logicamente. 'S' = exclu�da; 'N' = não exclu�da; 'D' = desativado
     */
 //   @TableField(name = "CIND_REG_ATIVO")
    private String codigoIndicativoAtivo = "S";

    /**
     * Retorna o serialVersionUID.
     * @return O atributo serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * Retorna o valor do atributo agenciaMovimento.
     * @return o valor do atributo agenciaMovimento
     */
    public long getAgenciaMovimento() {
        return agenciaMovimento;
    }

    /**
     * Especifica o valor do atributo agenciaMovimento.
     * @param agenciaMovimento - long do agenciaMovimento a ser configurado.
     */
    public void setAgenciaMovimento(long agenciaMovimento) {
        this.agenciaMovimento = agenciaMovimento;
    }

    /**
     * Retorna o valor do atributo bancoMovimento.
     * @return o valor do atributo bancoMovimento
     */
    public long getBancoMovimento() {
        return bancoMovimento;
    }

    /**
     * Especifica o valor do atributo bancoMovimento.
     * @param bancoMovimento - long do bancoMovimento a ser configurado.
     */
    public void setBancoMovimento(long bancoMovimento) {
        this.bancoMovimento = bancoMovimento;
    }

    /**
     * Retorna o valor do atributo contaMovimento.
     * @return o valor do atributo contaMovimento
     */
    public long getContaMovimento() {
        return contaMovimento;
    }

    /**
     * Especifica o valor do atributo contaMovimento.
     * @param contaMovimento - long do contaMovimento a ser configurado.
     */
    public void setContaMovimento(long contaMovimento) {
        this.contaMovimento = contaMovimento;
    }

    /**
     * Retorna o valor do atributo observacao.
     * @return o valor do atributo observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * Especifica o valor do atributo observacao.
     * @param observacao - String do observacao a ser configurado.
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * Retorna o valor do atributo indicacaoAcao.
     * @return o valor do atributo indicacaoAcao
     */
    public String getIndicacaoAcao() {
        return indicacaoAcao;
    }

    /**
     * Especifica o valor do atributo indicacaoAcao.
     * @param indicacaoAcao - String do indicacaoAcao a ser configurado.
     */
    public void setIndicacaoAcao(String indicacaoAcao) {
        this.indicacaoAcao = indicacaoAcao;
    }

    /**
     * Retorna o valor do atributo nroCheque.
     * @return o valor do atributo nroCheque
     */
    public long getNroCheque() {
        return nroCheque;
    }

    /**
     * Especifica o valor do atributo nroCheque.
     * @param nroCheque - long do nroCheque a ser configurado.
     */
    public void setNroCheque(long nroCheque) {
        this.nroCheque = nroCheque;
    }

    /**
     * Retorna o valor do atributo codigoIndicativoAtivo.
     * @return o valor do atributo codigoIndicativoAtivo
     */
    public String getCodigoIndicativoAtivo() {
        return codigoIndicativoAtivo;
    }

    /**
     * Especifica o valor do atributo codigoIndicativoAtivo.
     * @param codigoIndicativoAtivo - String do codigoIndicativoAtivo a ser configurado.
     */
    public void setCodigoIndicativoAtivo(String codigoIndicativoAtivo) {
        this.codigoIndicativoAtivo = codigoIndicativoAtivo;
    }

    /**
     * Retorna o valor do atributo codigoResponsavelUltimaAtualizacao.
     * @return o valor do atributo codigoResponsavelUltimaAtualizacao
     */
    public BigDecimal getCodigoResponsavelUltimaAtualizacao() {
        return codigoResponsavelUltimaAtualizacao;
    }

    /**
     * Especifica o valor do atributo codigoResponsavelUltimaAtualizacao.
     * @param codigoResponsavelUltimaAtualizacao - BigDecimal do codigoResponsavelUltimaAtualizacao a ser configurado.
     */
    public void setCodigoResponsavelUltimaAtualizacao(BigDecimal codigoResponsavelUltimaAtualizacao) {
        this.codigoResponsavelUltimaAtualizacao = codigoResponsavelUltimaAtualizacao;
    }

    /**
     * Retorna o valor do atributo dataHoraAtualizacao.
     * @return o valor do atributo dataHoraAtualizacao
     */
    public Date getDataHoraAtualizacao() {
        return (Date) dataHoraAtualizacao.clone();
    }

    /**
     * Especifica o valor do atributo dataHoraAtualizacao.
     * @param dataHoraAtualizacao - Date do dataHoraAtualizacao a ser configurado.
     */
    public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
        this.dataHoraAtualizacao = (Date) dataHoraAtualizacao.clone();
    }

    /**
     * Retorna o valor do atributo codigoMovimento.
     * @return o valor do atributo codigoMovimento
     */
    public long getCodigoMovimento() {
        return codigoMovimento;
    }

    /**
     * Especifica o valor do atributo codigoMovimento.
     * @param codigoMovimento - long do codigoMovimento a ser configurado.
     */
    public void setCodigoMovimento(long codigoMovimento) {
        this.codigoMovimento = codigoMovimento;
    }

}
