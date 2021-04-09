package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import java.util.Date;

import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;

/**
 * Classe RelatorioEnvioRetornoAnaliticoVO.
 * @author fabio.almeida@cpmbraxis.com.
 */
public class RelatorioEnvioRetornoAnaliticoVO implements Serializable{

    
    /**
     * 
     */
    private static final long serialVersionUID = -4759118749891158867L;
    
//    @TableField(name = "CSIT_DEP_ARQ_TRNSF")
    private Integer codigoSituacao;
    private String situacao;

//    @TableField(name = "CDIG_DEP_IDTFD")
    private Integer digitoCodigoAutorizador;
    
//    @TableField(name = "NBLETO_COBR")
    private Long bloquete;

//    @TableField(name = "CSUCUR_EMISR")
    private Integer sucursal;

//    @TableField(name = "CRAMO_SEGUR")
    private String ramo;

//    @TableField(name = "NAPOLC")
    private Integer apolice;

//    @TableField(name = "NITEM_APOLC")
    private Integer item;

//    @TableField(name = "CTPO_DOCTO")
    private Integer tipo;

//    @TableField(name = "NENDSS")
    private Integer endosso;

//    @TableField(name = "NPCELA_PRMIO")
    private Integer parcela;

    private String cpfCnpj;

//    @TableField(name = "CDEP_IDTFD")
    private Long codigoAutorizador;
    
//    @TableField(name = "CDIG_DEP_IDTFD")
    private Long codigoDigitoIdentificador;
    
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
    
    private String descricaoBanco;
    private String descricaoCia;
    private String descricaoConta;
    private String codigoAutorizadorComDv;

    
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
     * Retorna o cpfCnpj.
     * @return O atributo cpfCnpj
     */
    public String getCpfCnpj() {
        return cpfCnpj;
    }

    /**
     * Especifica o cpfCnpj.
     * @param cpfCnpj String do cpfCnpj a ser setado
     */
    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    /**
     * Especifica o descricaoCia.
     * @param descricaoCia String do descricaoCia a ser setado
     */
    public void setDescricaoCia(String descricaoCia) {
        this.descricaoCia = descricaoCia;
    }

    /**
     * Retorna o descricaoConta.
     * @return O atributo descricaoConta
     */
    public String getDescricaoConta() {
        return descricaoConta;
    }

    /**
     * Especifica o descricaoConta.
     * @param descricaoConta String do descricaoConta a ser setado
     */
    public void setDescricaoConta(String descricaoConta) {
        this.descricaoConta = descricaoConta;
    }

    /**
     * Retorna o descricaoCia.
     * @return O atributo descricaoCia
     */
    public String getDescricaoCia() {
        return descricaoCia;
    }

    /**
     * Retorna o descricaoBanco.
     * @return O atributo descricaoBanco
     */
    public String getDescricaoBanco() {
        return descricaoBanco;
    }

    /**
     * Especifica o descricaoBanco.
     * @param descricaoBanco String do descricaoBanco a ser setado
     */
    public void setDescricaoBanco(String descricaoBanco) {
        this.descricaoBanco = descricaoBanco;
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
     * Retorna o ramo.
     * @return O atributo ramo
     */
    public String getRamo() {
        return ramo;
    }

    /**
     * Especifica o ramo.
     * @param ramo Integer do ramo a ser setado
     */
    public void setRamo(String ramo) {
        this.ramo = ramo;
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
     * Retorna o valor do atributo apolice.
     * @return o valor do atributo apolice
     */
    public Integer getApolice() {
        return apolice;
    }

    /**
     * Especifica o valor do atributo apolice.
     * @param apolice - BigDecimal do apolice a ser configurado.
     */
    public void setApolice(Integer apolice) {
        this.apolice = apolice;
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
     * Retorna o tipo.
     * @return O atributo tipo
     */
    public Integer getTipo() {
        return tipo;
    }

    /**
     * Especifica o tipo.
     * @param tipo Integer do tipo a ser setado
     */
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
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
     * Retorna o valorPago.
     * @return O atributo valorPago
     */
    public java.math.BigDecimal getValorPago() {
        return valorPago;
    }

    /**
     * Especifica o valorPago.
     * @param valorPago java.math.BigDecimal do valorPago a ser setado
     */
    public void setValorPago(java.math.BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    /**
     * Retorna o vencimento.
     * @return O atributo vencimento
     */
    public Date getVencimento() {
         return BaseUtil.getDate(vencimento);
    }

    /**
     * Retorna o valorRegistrado.
     * @return O atributo valorRegistrado
     */
    public java.math.BigDecimal getValorRegistrado() {
        return valorRegistrado;
    }

    /**
     * Especifica o valorRegistrado.
     * @param valorRegistrado java.math.BigDecimal do valorRegistrado a ser setado
     */
    public void setValorRegistrado(java.math.BigDecimal valorRegistrado) {
        this.valorRegistrado = valorRegistrado;
    }

    /**
     * Especifica o vencimento.
     * @param vencimento Date do vencimento a ser setado
     */
    public void setVencimento(Date vencimento) {
        this.vencimento = (Date) vencimento.clone();
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
     * Retorna o valor do atributo codigoPessoa.
     * @return o valor do atributo codigoPessoa
     */
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
     * Retorna o codigoDigitoIdentificador.
     * @return O atributo codigoDigitoIdentificador
     */
    public Long getCodigoDigitoIdentificador() {
        return codigoDigitoIdentificador;
    }

    /**
     * Especifica o codigoDigitoIdentificador.
     * @param codigoDigitoIdentificador Long do codigoDigitoIdentificador a ser setado
     */
    public void setCodigoDigitoIdentificador(Long codigoDigitoIdentificador) {
        this.codigoDigitoIdentificador = codigoDigitoIdentificador;
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

    /**
     * Retorna o digitoCodigoAutorizador.
     * @return O atributo digitoCodigoAutorizador
     */
    public Integer getDigitoCodigoAutorizador() {
        return digitoCodigoAutorizador;
    }

    /**
     * Especifica o digitoCodigoAutorizador.
     * @param digitoCodigoAutorizador Integer do digitoCodigoAutorizador a ser setado
     */
    public void setDigitoCodigoAutorizador(Integer digitoCodigoAutorizador) {
        this.digitoCodigoAutorizador = digitoCodigoAutorizador;
    }
    
    @Override
	public String toString() {
		return "RelatorioEnvioRetornoAnaliticoVO ["
				+ (codigoSituacao != null ? "codigoSituacao=" + codigoSituacao + ", " : "")
				+ (situacao != null ? "situacao=" + situacao + ", " : "")
				+ (digitoCodigoAutorizador != null ? "digitoCodigoAutorizador=" + digitoCodigoAutorizador + ", " : "")
				+ (bloquete != null ? "bloquete=" + bloquete + ", " : "")
				+ (sucursal != null ? "sucursal=" + sucursal + ", " : "") + (ramo != null ? "ramo=" + ramo + ", " : "")
				+ (apolice != null ? "apolice=" + apolice + ", " : "") + (item != null ? "item=" + item + ", " : "")
				+ (tipo != null ? "tipo=" + tipo + ", " : "") + (endosso != null ? "endosso=" + endosso + ", " : "")
				+ (parcela != null ? "parcela=" + parcela + ", " : "")
				+ (cpfCnpj != null ? "cpfCnpj=" + cpfCnpj + ", " : "")
				+ (codigoAutorizador != null ? "codigoAutorizador=" + codigoAutorizador + ", " : "")
				+ (codigoDigitoIdentificador != null ? "codigoDigitoIdentificador=" + codigoDigitoIdentificador + ", " : "")
				+ (codigoPessoa != null ? "codigoPessoa=" + codigoPessoa + ", " : "")
				+ (vencimento != null ? "vencimento=" + vencimento + ", " : "")
				+ (matricula != null ? "matricula=" + matricula + ", " : "")
				+ (valorRegistrado != null ? "valorRegistrado=" + valorRegistrado + ", " : "")
				+ (valorPago != null ? "valorPago=" + valorPago + ", " : "")
				+ (codigoBanco != null ? "codigoBanco=" + codigoBanco + ", " : "")
				+ (codigoAgencia != null ? "codigoAgencia=" + codigoAgencia + ", " : "")
				+ (codigoConta != null ? "codigoConta=" + codigoConta + ", " : "")
				+ (codigoCia != null ? "codigoCia=" + codigoCia + ", " : "")
				+ (descricaoBanco != null ? "descricaoBanco=" + descricaoBanco + ", " : "")
				+ (descricaoCia != null ? "descricaoCia=" + descricaoCia + ", " : "")
				+ (descricaoConta != null ? "descricaoConta=" + descricaoConta + ", " : "")
				+ (codigoAutorizadorComDv != null ? "codigoAutorizadorComDv=" + codigoAutorizadorComDv : "") + "]";
    }
   
    
}