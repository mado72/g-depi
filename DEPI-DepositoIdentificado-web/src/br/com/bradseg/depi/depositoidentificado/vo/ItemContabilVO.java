package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;

import br.com.bradseg.depi.depositoidentificado.cics.annotations.CicsField;

/**
 * Classe ItemContabilVO - Item Contábil VO.
 */
public class ItemContabilVO implements Serializable{

    private static final long serialVersionUID = 1L;
        
    @CicsField(order = 10, size = 4)
    private int codigoTipo;

    @CicsField(order = 20, size = 50)
    private String descricaoTipo;

    /**
     * Construtor
     */
    public ItemContabilVO() {
        super();
    }

    /**
     * Construtor
     * @param codigoTipo - int
     */
    public ItemContabilVO(int codigoTipo) {
        super();
        this.codigoTipo = codigoTipo;
    }

    /**
     * Construtor
     * @param codigoTipo - int
     * @param descricaoTipo - String.
     */
    public ItemContabilVO(int codigoTipo, String descricaoTipo) {
        super();
        this.codigoTipo = codigoTipo;
        this.descricaoTipo = descricaoTipo;
    }

    /**
     * Retorna o valor do atributo codigoTipo.
     * @return o valor do atributo codigoTipo
     */
    public int getCodigoTipo() {
        return codigoTipo;
    }

    /**
     * Especifica o valor do atributo codigoTipo.
     * @param codigoTipo - int do codigoTipo a ser configurado.
     */
    public void setCodigoTipo(int codigoTipo) {
        this.codigoTipo = codigoTipo;
    }

    /**
     * Retorna o valor do atributo descricaoTipo.
     * @return o valor do atributo descricaoTipo
     */
    public String getDescricaoTipo() {
        return descricaoTipo.trim();
    }

    /**
     * Especifica o valor do atributo descricaoTipo.
     * @param descricaoTipo - String do descricaoTipo a ser configurado.
     */
    public void setDescricaoTipo(String descricaoTipo) {
        this.descricaoTipo = descricaoTipo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return this.getCodigoTipo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ItemContabilVO)) {
            return false;
        }
        return (((ItemContabilVO) o).getCodigoTipo() == this.getCodigoTipo());
    }
}
