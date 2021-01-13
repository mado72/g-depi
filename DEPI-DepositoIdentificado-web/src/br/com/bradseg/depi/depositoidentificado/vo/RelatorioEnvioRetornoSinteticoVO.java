package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Classe RelatorioEnvioRetornoSinteticoVO
 */
public class RelatorioEnvioRetornoSinteticoVO implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -7297474535129412767L;
//    @TableField(name = "CSIT_DEP_ARQ_TRNSF")
    private Integer codigoSituacao;
    private String situacao;

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

    private Integer qtdEnviados;
    private Integer qtdAceitos;
    private Integer qtdRejeitados;
    private Integer qtdCancelados;
    private BigDecimal totalEnviados;
    private BigDecimal totalAceitos;
    private BigDecimal totalRejeitados;
    private BigDecimal totalCancelados;

    /**
     * Retorna o qtdAceitos.
     * @return O atributo qtdAceitos
     */
    public Integer getQtdAceitos() {
        return this.qtdAceitos;
    }

    /**
     * Especifica o qtdAceitos.
     * @param qtdAceitos Integer do qtdAceitos a ser setado
     */
    public void setQtdAceitos(Integer qtdAceitos) {
        this.qtdAceitos = qtdAceitos;
    }

    /**
     * Retorna o qtdCancelados.
     * @return O atributo qtdCancelados
     */
    public Integer getQtdCancelados() {
        return this.qtdCancelados;
    }

    /**
     * Especifica o qtdCancelados.
     * @param qtdCancelados Integer do qtdCancelados a ser setado
     */
    public void setQtdCancelados(Integer qtdCancelados) {
        this.qtdCancelados = qtdCancelados;
    }

    /**
     * Retorna o qtdEnviados.
     * @return O atributo qtdEnviados
     */
    public Integer getQtdEnviados() {
        return this.qtdEnviados;
    }

    /**
     * Especifica o qtdEnviados.
     * @param qtdEnviados Integer do qtdEnviados a ser setado
     */
    public void setQtdEnviados(Integer qtdEnviados) {
        this.qtdEnviados = qtdEnviados;
    }

    /**
     * Retorna o qtdRejeitados.
     * @return O atributo qtdRejeitados
     */
    public Integer getQtdRejeitados() {
        return this.qtdRejeitados;
    }

    /**
     * Especifica o qtdRejeitados.
     * @param qtdRejeitados Integer do qtdRejeitados a ser setado
     */
    public void setQtdRejeitados(Integer qtdRejeitados) {
        this.qtdRejeitados = qtdRejeitados;
    }

    /**
     * Retorna o totalAceitos.
     * @return O atributo totalAceitos
     */
    public BigDecimal getTotalAceitos() {
        return this.totalAceitos;
    }

    /**
     * Especifica o totalAceitos.
     * @param totalAceitos BigDecimal do totalAceitos a ser setado
     */
    public void setTotalAceitos(BigDecimal totalAceitos) {
        this.totalAceitos = totalAceitos;
    }

    /**
     * Retorna o totalCancelados.
     * @return O atributo totalCancelados
     */
    public BigDecimal getTotalCancelados() {
        return this.totalCancelados;
    }

    /**
     * Especifica o totalCancelados.
     * @param totalCancelados BigDecimal do totalCancelados a ser setado
     */
    public void setTotalCancelados(BigDecimal totalCancelados) {
        this.totalCancelados = totalCancelados;
    }

    /**
     * Retorna o totalEnviados.
     * @return O atributo totalEnviados
     */
    public BigDecimal getTotalEnviados() {
        return this.totalEnviados;
    }

    /**
     * Especifica o totalEnviados.
     * @param totalEnviados BigDecimal do totalEnviados a ser setado
     */
    public void setTotalEnviados(BigDecimal totalEnviados) {
        this.totalEnviados = totalEnviados;
    }

    /**
     * Retorna o totalRejeitados.
     * @return O atributo totalRejeitados
     */
    public BigDecimal getTotalRejeitados() {
        return this.totalRejeitados;
    }

    /**
     * Especifica o totalRejeitados.
     * @param totalRejeitados BigDecimal do totalRejeitados a ser setado
     */
    public void setTotalRejeitados(BigDecimal totalRejeitados) {
        this.totalRejeitados = totalRejeitados;
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
     * Retorna o valor do atributo codigoSituacao.
     * @return o valor do atributo codigoSituacao
     */
    public Integer getCodigoSituacao() {
        return codigoSituacao;
    }

    /**
     * Especifica o valor do atributo codigoSituacao.
     * @param codigoSituacao - Integer do codigoSituacao a ser configurado.
     */
    public void setCodigoSituacao(Integer codigoSituacao) {
        this.codigoSituacao = codigoSituacao;
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
}
