package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Método RelatorioExtratoSinteticoVO
 * @author Fábio Henrique
 */
public class RelatorioExtratoSinteticoVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6493528908534168005L;

//    @TableField(name = "CSIT_DEP_ARQ_TRNSF")
    private Integer situacaoEnvioRetorno;

//    @TableField(name = "CDEP_IDTFD")
    private Long codigoAutorizador;

//    @TableField(name = "VDEP_IDTFD_ORIGN")
    private java.math.BigDecimal valorRegistrado;

//    @TableField(name = "VTOT_DEP_IDTFD")
    private java.math.BigDecimal valorPago;

//    @TableField(name = "CBCO")
    private Integer codigoBanco;
    
//    @TableField(name = "CAG_BCRIA")
    private Integer codigoAgencia;

//    @TableField(name = "CCTA_CORR")
    private Long codigoConta;

//    @TableField(name = "CINTRN_CIA_SEGDR")
    private Integer codigoCia;

    private String descricaoBanco;
    private String descricaoCia;
    private String descricaoConta;
    private String descricaoSituacao;

    /**
     * Totais Envio e Retorno por Grupo
     */
    private long qtdEnviado;
    private BigDecimal valorEnviado = BigDecimal.ZERO;
    private long qtdAceito;
    private BigDecimal valorAceito = BigDecimal.ZERO;
    private long qtdRejeitado;
    private BigDecimal valorRejeitado = BigDecimal.ZERO;
    private long qtdCancelado;
    private BigDecimal valorCancelado = BigDecimal.ZERO;

    /**
     * Totais Manutenção por Grupo
     */
    private long qtdAceite;
    private BigDecimal valorAceite = BigDecimal.ZERO;
    private long qtdTransferencia;
    private BigDecimal valorTransferencia = BigDecimal.ZERO;
    private long qtdDevolucao;
    private BigDecimal valorDevolucao = BigDecimal.ZERO;
    private long qtdReceita;
    private BigDecimal valorReceita = BigDecimal.ZERO;

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
     * Retorna o valor do atributo descricaoSituacao.
     * @return o valor do atributo descricaoSituacao
     */
    public String getDescricaoSituacao() {
        return descricaoSituacao;
    }

    /**
     * Especifica o valor do atributo descricaoSituacao.
     * @param descricaoSituacao - String do descricaoSituacao a ser configurado.
     */
    public void setDescricaoSituacao(String descricaoSituacao) {
        this.descricaoSituacao = descricaoSituacao;
    }

    /**
     * Retorna o valor do atributo situacaoEnvioRetorno.
     * @return o valor do atributo situacaoEnvioRetorno
     */
    public Integer getSituacaoEnvioRetorno() {
        return situacaoEnvioRetorno;
    }

    /**
     * Especifica o valor do atributo situacaoEnvioRetorno.
     * @param situacaoEnvioRetorno - Integer do situacaoEnvioRetorno a ser configurado.
     */
    public void setSituacaoEnvioRetorno(Integer situacaoEnvioRetorno) {
        this.situacaoEnvioRetorno = situacaoEnvioRetorno;
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
     * Retorna o valor do atributo valorAceite.
     * @return o valor do atributo valorAceite
     */
    public BigDecimal getValorAceite() {
        return valorAceite;
    }

    /**
     * Especifica o valor do atributo valorAceite.
     * @param valorAceite - BigDecimal do valorAceite a ser configurado.
     */
    public void setValorAceite(BigDecimal valorAceite) {
        this.valorAceite = valorAceite;
    }

    /**
     * Retorna o valor do atributo valorAceito.
     * @return o valor do atributo valorAceito
     */
    public BigDecimal getValorAceito() {
        return valorAceito;
    }

    /**
     * Especifica o valor do atributo valorAceito.
     * @param valorAceito - BigDecimal do valorAceito a ser configurado.
     */
    public void setValorAceito(BigDecimal valorAceito) {
        this.valorAceito = valorAceito;
    }

    /**
     * Retorna o valor do atributo valorCancelado.
     * @return o valor do atributo valorCancelado
     */
    public BigDecimal getValorCancelado() {
        return valorCancelado;
    }

    /**
     * Especifica o valor do atributo valorCancelado.
     * @param valorCancelado - BigDecimal do valorCancelado a ser configurado.
     */
    public void setValorCancelado(BigDecimal valorCancelado) {
        this.valorCancelado = valorCancelado;
    }

    /**
     * Retorna o valor do atributo valorDevolucao.
     * @return o valor do atributo valorDevolucao
     */
    public BigDecimal getValorDevolucao() {
        return valorDevolucao;
    }

    /**
     * Especifica o valor do atributo valorDevolucao.
     * @param valorDevolucao - BigDecimal do valorDevolucao a ser configurado.
     */
    public void setValorDevolucao(BigDecimal valorDevolucao) {
        this.valorDevolucao = valorDevolucao;
    }

    /**
     * Retorna o valor do atributo valorEnviado.
     * @return o valor do atributo valorEnviado
     */
    public BigDecimal getValorEnviado() {
        return valorEnviado;
    }

    /**
     * Especifica o valor do atributo valorEnviado.
     * @param valorEnviado - BigDecimal do valorEnviado a ser configurado.
     */
    public void setValorEnviado(BigDecimal valorEnviado) {
        this.valorEnviado = valorEnviado;
    }

    /**
     * Retorna o valor do atributo valorReceita.
     * @return o valor do atributo valorReceita
     */
    public BigDecimal getValorReceita() {
        return valorReceita;
    }

    /**
     * Especifica o valor do atributo valorReceita.
     * @param valorReceita - BigDecimal do valorReceita a ser configurado.
     */
    public void setValorReceita(BigDecimal valorReceita) {
        this.valorReceita = valorReceita;
    }

    /**
     * Retorna o valor do atributo valorRejeitado.
     * @return o valor do atributo valorRejeitado
     */
    public BigDecimal getValorRejeitado() {
        return valorRejeitado;
    }

    /**
     * Especifica o valor do atributo valorRejeitado.
     * @param valorRejeitado - BigDecimal do valorRejeitado a ser configurado.
     */
    public void setValorRejeitado(BigDecimal valorRejeitado) {
        this.valorRejeitado = valorRejeitado;
    }

    /**
     * Retorna o valor do atributo valorTransferencia.
     * @return o valor do atributo valorTransferencia
     */
    public BigDecimal getValorTransferencia() {
        return valorTransferencia;
    }

    /**
     * Especifica o valor do atributo valorTransferencia.
     * @param valorTransferencia - BigDecimal do valorTransferencia a ser configurado.
     */
    public void setValorTransferencia(BigDecimal valorTransferencia) {
        this.valorTransferencia = valorTransferencia;
    }

    /**
     * Retorna o valor do atributo qtdAceite.
     * @return o valor do atributo qtdAceite
     */
    public long getQtdAceite() {
        return qtdAceite;
    }

    /**
     * Especifica o valor do atributo qtdAceite.
     * @param qtdAceite - long do qtdAceite a ser configurado.
     */
    public void setQtdAceite(long qtdAceite) {
        this.qtdAceite = qtdAceite;
    }

    /**
     * Retorna o valor do atributo qtdAceito.
     * @return o valor do atributo qtdAceito
     */
    public long getQtdAceito() {
        return qtdAceito;
    }

    /**
     * Especifica o valor do atributo qtdAceito.
     * @param qtdAceito - long do qtdAceito a ser configurado.
     */
    public void setQtdAceito(long qtdAceito) {
        this.qtdAceito = qtdAceito;
    }

    /**
     * Retorna o valor do atributo qtdCancelado.
     * @return o valor do atributo qtdCancelado
     */
    public long getQtdCancelado() {
        return qtdCancelado;
    }

    /**
     * Especifica o valor do atributo qtdCancelado.
     * @param qtdCancelado - long do qtdCancelado a ser configurado.
     */
    public void setQtdCancelado(long qtdCancelado) {
        this.qtdCancelado = qtdCancelado;
    }

    /**
     * Retorna o valor do atributo qtdDevolucao.
     * @return o valor do atributo qtdDevolucao
     */
    public long getQtdDevolucao() {
        return qtdDevolucao;
    }

    /**
     * Especifica o valor do atributo qtdDevolucao.
     * @param qtdDevolucao - long do qtdDevolucao a ser configurado.
     */
    public void setQtdDevolucao(long qtdDevolucao) {
        this.qtdDevolucao = qtdDevolucao;
    }

    /**
     * Retorna o valor do atributo qtdEnviado.
     * @return o valor do atributo qtdEnviado
     */
    public long getQtdEnviado() {
        return qtdEnviado;
    }

    /**
     * Especifica o valor do atributo qtdEnviado.
     * @param qtdEnviado - long do qtdEnviado a ser configurado.
     */
    public void setQtdEnviado(long qtdEnviado) {
        this.qtdEnviado = qtdEnviado;
    }

    /**
     * Retorna o valor do atributo qtdReceita.
     * @return o valor do atributo qtdReceita
     */
    public long getQtdReceita() {
        return qtdReceita;
    }

    /**
     * Especifica o valor do atributo qtdReceita.
     * @param qtdReceita - long do qtdReceita a ser configurado.
     */
    public void setQtdReceita(long qtdReceita) {
        this.qtdReceita = qtdReceita;
    }

    /**
     * Retorna o valor do atributo qtdRejeitado.
     * @return o valor do atributo qtdRejeitado
     */
    public long getQtdRejeitado() {
        return qtdRejeitado;
    }

    /**
     * Especifica o valor do atributo qtdRejeitado.
     * @param qtdRejeitado - long do qtdRejeitado a ser configurado.
     */
    public void setQtdRejeitado(long qtdRejeitado) {
        this.qtdRejeitado = qtdRejeitado;
    }

    /**
     * Retorna o valor do atributo qtdTransferencia.
     * @return o valor do atributo qtdTransferencia
     */
    public long getQtdTransferencia() {
        return qtdTransferencia;
    }

    /**
     * Especifica o valor do atributo qtdTransferencia.
     * @param qtdTransferencia - long do qtdTransferencia a ser configurado.
     */
    public void setQtdTransferencia(long qtdTransferencia) {
        this.qtdTransferencia = qtdTransferencia;
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

}