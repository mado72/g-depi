/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * Representa os dados de entrada para geração do relatório Manutenções Sintético 
 *
 */
public class ManutencoesSinteticoVO implements Serializable {

	private static final long serialVersionUID = -3381081013840447732L;

	
    private String codigoTipoAcao;

    private String situacao;

    private Integer codigoBanco = 0;

    private Integer codigoAgencia = 0;

    private Long codigoConta = 0L;

    private Integer codigoCia = 0;

    private String descricaoBanco = "";
    private String descricaoCia = "";
    private String descricaoConta = "";

    private BigDecimal valor = BigDecimal.ZERO;

    private Long quantidade = 0L;

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
     * Retorna o valor do atributo codigoTipoAcao.
     * @return o valor do atributo codigoTipoAcao
     */
    public String getCodigoTipoAcao() {
        return codigoTipoAcao;
    }

    /**
     * Especifica o valor do atributo codigoTipoAcao.
     * @param codigoTipoAcao - String do codigoTipoAcao a ser configurado.
     */
    public void setCodigoTipoAcao(String codigoTipoAcao) {
        this.codigoTipoAcao = codigoTipoAcao;
    }

    /**
     * Retorna o descricaoBanco.
     * @return O atributo descricaoBanco
     */
    public String getDescricaoBanco() {
        return this.descricaoBanco;
    }

    /**
     * Especifica o descricaoBanco.
     * @param descricaoBanco String do descricaoBanco a ser setado
     */
    public void setDescricaoBanco(String descricaoBanco) {
        this.descricaoBanco = descricaoBanco;
    }

    /**
     * Retorna o descricaoCia.
     * @return O atributo descricaoCia
     */
    public String getDescricaoCia() {
        return this.descricaoCia;
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
        return this.descricaoConta;
    }

    /**
     * Especifica o descricaoConta.
     * @param descricaoConta String do descricaoConta a ser setado
     */
    public void setDescricaoConta(String descricaoConta) {
        this.descricaoConta = descricaoConta;
    }

    /**
     * Retorna o situacao.
     * @return O atributo situacao
     */
    public String getSituacao() {
        return this.situacao;
    }

    /**
     * Especifica o situacao.
     * @param situacao String do situacao a ser setado
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    /**
     * Retorna o valor.
     * @return O atributo valor
     */
    public BigDecimal getValor() {
        return this.valor;
    }

    /**
     * Especifica o valor.
     * @param valor BigDecimal do valor a ser setado
     */
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    /**
     * Retorna o valor do atributo quantidade.
     * @return o valor do atributo quantidade
     */
    public Long getQuantidade() {
        return quantidade;
    }

    /**
     * Especifica o valor do atributo quantidade.
     * @param quantidade - Long do quantidade a ser configurado.
     */
    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }	
	
	

}
