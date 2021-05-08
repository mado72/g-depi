package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import java.util.Date;

import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;

/**
 * Método RelatorioExtratoAnaliticoVO
 * @author Globality
 */
public class RelatorioExtratoAnaliticoVO implements Serializable,
		RelatorioCompanhiaAware, RelatorioBancoContaAware, RelatorioPessoaAware {
    
    private static final long serialVersionUID = 4286054803965245801L;

//    @TableField(name = "CSIT_DEP_ARQ_TRNSF")
    private int situacaoEnvioRetorno;
    private String situacao;
    private String nomeGrupo;

//    @TableField(name = "NBLETO_COBR")
    private Long bloquete;

//    @TableField(name = "CSUCUR_EMISR")
    private Integer sucursal;

//    @TableField(name = "CRAMO_SEGUR")
    private Integer ramo;

//    @TableField(name = "NAPOLC")
    private Integer apolice;

//    @TableField(name = "NITEM_APOLC")
    private Integer item;

//    @TableField(name = "CTPO_GRP_RECEB")
    private Integer tipo;

//    @TableField(name = "NENDSS")
    private Integer endosso;

//    @TableField(name = "NPCELA_PRMIO")
    private Integer parcela;

    private String cpfCnpj;

//    @TableField(name = "CDEP_IDTFD")
    private Long codigoAutorizador;

//    @TableField(name = "CPSSOA_DEPST")
    private Long codigoPessoa;

//    @TableField(name = "DVCTO_DEP_IDTFD")
    private Date vencimento;

//    @TableField(name = "CUSUAR_RESP_ATULZ")
    private Long matricula;

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

    private java.math.BigDecimal valorRegistradoUnico;

    private java.math.BigDecimal valorPagoUnico;

    private String descricaoBanco;
    private String descricaoCia;
    private String descricaoConta;
    private String codigoAutorizadorComDv;
    
    /**
     * Retorna o valor do atributo apolice.
     * @return o valor do atributo apolice
     */
    public Integer getApolice() {
        return apolice;
    }

    /**
     * Especifica o valor do atributo apolice.
     * @param apolice - Integer do apolice a ser configurado.
     */
    public void setApolice(Integer apolice) {
        this.apolice = apolice;
    }

    /**
     * Retorna o valor do atributo bloquete.
     * @return o valor do atributo bloquete
     */
    public Long getBloquete() {
        return bloquete;
    }

    /**
     * Especifica o valor do atributo bloquete.
     * @param bloquete - Long do bloquete a ser configurado.
     */
    public void setBloquete(Long bloquete) {
        this.bloquete = bloquete;
    }

    /**
     * Retorna o valor do atributo codigoAgencia.
     * @return o valor do atributo codigoAgencia
     */
    @Override
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
    @Override
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
    @Override
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
    @Override
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
     * Retorna o valor do atributo codigoPessoa.
     * @return o valor do atributo codigoPessoa
     */
    @Override
	public Long getCodigoPessoa() {
        return codigoPessoa;
    }

    /**
     * Especifica o valor do atributo codigoPessoa.
     * @param codigoPessoa - Long do codigoPessoa a ser configurado.
     */
    public void setCodigoPessoa(Long codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
    }

    /**
     * Retorna o valor do atributo cpfCnpj.
     * @return o valor do atributo cpfCnpj
     */
    public String getCpfCnpj() {
        return cpfCnpj;
    }

    /**
     * Especifica o valor do atributo cpfCnpj.
     * @param cpfCnpj - String do cpfCnpj a ser configurado.
     */
    @Override
	public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
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
    @Override
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
    @Override
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
    @Override
	public void setDescricaoConta(String descricaoConta) {
        this.descricaoConta = descricaoConta;
    }

    /**
     * Retorna o valor do atributo endosso.
     * @return o valor do atributo endosso
     */
    public Integer getEndosso() {
        return endosso;
    }

    /**
     * Especifica o valor do atributo endosso.
     * @param endosso - Integer do endosso a ser configurado.
     */
    public void setEndosso(Integer endosso) {
        this.endosso = endosso;
    }

    /**
     * Retorna o valor do atributo item.
     * @return o valor do atributo item
     */
    public Integer getItem() {
        return item;
    }

    /**
     * Especifica o valor do atributo item.
     * @param item - Integer do item a ser configurado.
     */
    public void setItem(Integer item) {
        this.item = item;
    }

    /**
     * Retorna o valor do atributo matricula.
     * @return o valor do atributo matricula
     */
    public Long getMatricula() {
        return matricula;
    }

    /**
     * Especifica o valor do atributo matricula.
     * @param matricula - Long do matricula a ser configurado.
     */
    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    /**
     * Retorna o valor do atributo parcela.
     * @return o valor do atributo parcela
     */
    public Integer getParcela() {
        return parcela;
    }

    /**
     * Especifica o valor do atributo parcela.
     * @param parcela - Integer do parcela a ser configurado.
     */
    public void setParcela(Integer parcela) {
        this.parcela = parcela;
    }

    /**
     * Retorna o valor do atributo ramo.
     * @return o valor do atributo ramo
     */
    public Integer getRamo() {
        return ramo;
    }

    /**
     * Especifica o valor do atributo ramo.
     * @param ramo - Integer do ramo a ser configurado.
     */
    public void setRamo(Integer ramo) {
        this.ramo = ramo;
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
     * Retorna o valor do atributo situacaoEnvioRetorno.
     * @return o valor do atributo situacaoEnvioRetorno
     */
    public int getSituacaoEnvioRetorno() {
        return situacaoEnvioRetorno;
    }

    /**
     * Especifica o valor do atributo situacaoEnvioRetorno.
     * @param situacaoEnvioRetorno - String do situacaoEnvioRetorno a ser configurado.
     */
    public void setSituacaoEnvioRetorno(int situacaoEnvioRetorno) {
        this.situacaoEnvioRetorno = situacaoEnvioRetorno;
    }

    /**
     * Retorna o valor do atributo sucursal.
     * @return o valor do atributo sucursal
     */
    public Integer getSucursal() {
        return sucursal;
    }

    /**
     * Especifica o valor do atributo sucursal.
     * @param sucursal - Integer do sucursal a ser configurado.
     */
    public void setSucursal(Integer sucursal) {
        this.sucursal = sucursal;
    }

    /**
     * Retorna o valor do atributo tipo.
     * @return o valor do atributo tipo
     */
    public Integer getTipo() {
        return tipo;
    }

    /**
     * Especifica o valor do atributo tipo.
     * @param tipo - Integer do tipo a ser configurado.
     */
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
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
     * Retorna o valor do atributo vencimento.
     * @return o valor do atributo vencimento
     */
    public Date getVencimento() {
        return BaseUtil.getDate(vencimento);
    }

    /**
     * Especifica o valor do atributo vencimento.
     * @param vencimento - Date do vencimento a ser configurado.
     */
    public void setVencimento(Date vencimento) {
        this.vencimento = BaseUtil.getDate(vencimento); 
    }

    /**
     * Retorna o valor do atributo nomeGrupo.
     * @return o valor do atributo nomeGrupo
     */
    public String getNomeGrupo() {
        return nomeGrupo;
    }

    /**
     * Especifica o valor do atributo nomeGrupo.
     * @param nomeGrupo - String do nomeGrupo a ser configurado.
     */
    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    /**
     * Retorna o valor do atributo valorPagoUnico.
     * @return o valor do atributo valorPagoUnico
     */
    public java.math.BigDecimal getValorPagoUnico() {
        return valorPagoUnico;
    }

    /**
     * Especifica o valor do atributo valorPagoUnico.
     * @param valorPagoUnico - java.math.BigDecimal do valorPagoUnico a ser configurado.
     */
    public void setValorPagoUnico(java.math.BigDecimal valorPagoUnico) {
        this.valorPagoUnico = valorPagoUnico;
    }

    /**
     * Retorna o valor do atributo valorRegistradoUnico.
     * @return o valor do atributo valorRegistradoUnico
     */
    public java.math.BigDecimal getValorRegistradoUnico() {
        return valorRegistradoUnico;
    }

    /**
     * Especifica o valor do atributo valorRegistradoUnico.
     * @param valorRegistradoUnico - java.math.BigDecimal do valorRegistradoUnico a ser configurado.
     */
    public void setValorRegistradoUnico(java.math.BigDecimal valorRegistradoUnico) {
        this.valorRegistradoUnico = valorRegistradoUnico;
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