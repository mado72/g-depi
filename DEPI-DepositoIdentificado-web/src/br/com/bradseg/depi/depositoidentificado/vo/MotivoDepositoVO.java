package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import java.util.Date;


/**
 * Classe Anotada de Mapeamento com a tabela MOTVO_DEP_CTA_CORR.
 * @author Globality
 */
public class MotivoDepositoVO implements Serializable {


    /**
     * 
     */
    private static final long serialVersionUID = -2114106102918079784L;

    
    /**
     * C�digo do Arquivo.
     */
    private Integer codigoMotivoDeposito;

    /**
     * descri�ao b�sica.
     */
//    @TableField(name = "RMOTVO_DEP_IDTFD")
    private String descricaoBasica;

    /**
     * descri��o detalhada.
     */
//    @TableField(name = "RDETLH_MOTVO_DEP")
    private String descricaoDetalhada;

    /**
     * Evento Cont�bil
     */
//    @TableField(name = "CTPO_EVNTO_NEGOC")
    private Integer codigoEventoContabil;

    /**
     * Item Cont�bil
     */
//    @TableField(name = "CTPO_ITEM_NEGOC")
    private Integer codigoItemContabil;

    /**
     * Item Cont�bil
     */
//    @TableField(name = "CIND_REG_ATIVO")
    private String indicadorAtivo;
    
    /**
     * Atributo que receber� um dep�sito vinculado ao motivo
     */
    private DepositoVO deposito;
   
    
    /**
     * Atributo que receber� um dep�sito vinculado ao motivo
     */   
    private Integer codigoResponsavelUltimaAtualizacao;
    
    
    /**
     * Atributo que receber� um dep�sito vinculado ao motivo
     */   
    private Date ultimaAtualizacao;
       
    
    
	/**
     * Retorna o responsavel pela ultima atualizacao
     * @return o responsavel pela ultima atualizacao
     */ 
	public Integer getCodigoResponsavelUltimaAtualizacao() {
		return codigoResponsavelUltimaAtualizacao;
	}

    /**
     * Especifica o responsavel pela ultima atualizacao
     * @param codigoResponsavelUltimaAtualizacao - responsavel pela ultima atualizacao
     */
	public void setCodigoResponsavelUltimaAtualizacao(
			Integer codigoResponsavelUltimaAtualizacao) {
		this.codigoResponsavelUltimaAtualizacao = codigoResponsavelUltimaAtualizacao;
	}

	/**
     * Retorna o indicador ativo
     * @return o indicador ativo
     */
    public String getIndicadorAtivo() {
		return indicadorAtivo;
	}

    /**
     * Especifica o campo indicador ativo
     * @param indicadorAtivo - indicador ativo
     */
	public void setIndicadorAtivo(String indicadorAtivo) {
		this.indicadorAtivo = indicadorAtivo;
	}

	/**
     * Retorna o valor do atributo codigoItemContabil.
     * @return o valor do atributo codigoItemContabil
     */
    public int getCodigoItemContabil() {
        return codigoItemContabil;
    }
    
    /**
     * Especifica o valor do atributo codigoItemContabil.
     * @param codigoItemContabil - int do codigoItemContabil a ser configurado.
     */
    public void setCodigoItemContabil(int codigoItemContabil) {
        this.codigoItemContabil = codigoItemContabil;
    }

    /**
     * Retorna o valor do atributo codigoEventoContabil.
     * @return o valor do atributo codigoEventoContabil
     */
    public int getCodigoEventoContabil() {
        return codigoEventoContabil;
    }

    /**
     * Construtor do vo
     * @param pCodigoMotivoDeposito - int
     * @param descricaoBasica - String
     * @param descricaoDetalhada - String
     */
    public MotivoDepositoVO(int pCodigoMotivoDeposito,String descricaoBasica, String descricaoDetalhada) {
        this.codigoMotivoDeposito = pCodigoMotivoDeposito;
        this.descricaoBasica = descricaoBasica;
        this.descricaoDetalhada = descricaoDetalhada;
    }

    public MotivoDepositoVO() {
		super();
	}

	/**
     * Especifica o campo codigoMotivoDeposito
     * @param pCodigoMotivoDeposito - c�digo do motivo de dep�sito
     */
    public void setCodigoMotivoDeposito(int pCodigoMotivoDeposito) {
        this.codigoMotivoDeposito = pCodigoMotivoDeposito;
    }

    /**
     * Retorna o campo descricaoBasica
     * @return o descricaoBasica
     */
    public String getDescricaoBasica() {
        return descricaoBasica;
    }

    /**
     * Especifica o campo descricaoBasica
     * @param pDescricaoBasica - descri��o b�sica
     */
    public void setDescricaoBasica(String pDescricaoBasica) {
        if (pDescricaoBasica != null) {
        	this.descricaoBasica = pDescricaoBasica.trim().toUpperCase();
        }
    }

    /**
     * Retorna o campo descricaoDetalhada
     * @return o descricaoDetalhada
     */
    public String getDescricaoDetalhada() {
        return descricaoDetalhada;
    }

    /**
     * Especifica o campo descricaoDetalhada
     * @param pDescricaoDetalhada - descri��o detalhada
     */
    public void setDescricaoDetalhada(String pDescricaoDetalhada) {
        if (pDescricaoDetalhada != null) {
        	this.descricaoDetalhada = pDescricaoDetalhada.trim().toUpperCase();
        }
    }

    /**
     * Retorna o codigoMotivoDeposito.
     * @return O atributo codigoMotivoDeposito
     */
    public int getCodigoMotivoDeposito() {
        return codigoMotivoDeposito;
    }

    /**
     * Especifica o valor do atributo codigoEventoContabil.
     * @param codigoEventoContabil - int do codigoEventoContabil a ser configurado.
     */
    public void setCodigoEventoContabil(int codigoEventoContabil) {
        this.codigoEventoContabil = codigoEventoContabil;
    }

    /**
     * Retorna o valor do atributo deposito.
     * @return o valor do atributo deposito
     */
    public DepositoVO getDeposito() {
        return deposito;
    }

    /**
     * Especifica o valor do atributo deposito.
     * @param deposito - DepositoVO do deposito a ser configurado.
     */
    public void setDeposito(DepositoVO deposito) {
        this.deposito = deposito;
    }
    
    
    /**
     * Retorna o ultima atualizacao.
     * @return O atributo ultima atualizacao.
     */
    public Date getUltimaAtualizacao() {
        return (Date) ultimaAtualizacao.clone();
    }

      /**
     * Especifica o data ultima atualizacao.
     * @param ultima atualizacao a ser setado
     */
    public void setUltimaAtualizacao(Date ultimaAtualizacao) {
        this.ultimaAtualizacao = (Date) ultimaAtualizacao.clone();
    }

}
