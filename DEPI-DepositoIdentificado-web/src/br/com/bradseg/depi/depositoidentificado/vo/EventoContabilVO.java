package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;

import br.com.bradseg.depi.depositoidentificado.cics.annotations.CicsField;

/**
 * Classe EventoContabilVO - Entidade Evento Contábil VO.
 * @author Fábio Henrique
 */
public class EventoContabilVO implements Serializable{
    
    private static final long serialVersionUID = 8702404275057731521L;

    @CicsField(order = 10, size = 4, pattern = "0000")
    private int codigoTipo;

    @CicsField(order = 20, size = 75)
    private String descricaoTipo;

    @CicsField(order = 30, size = 1)
    private int codigoIndicativoTipoEvento;

    /**
     * Construtor
     * @param codigoTipo - int
     */
    public EventoContabilVO(int codigoTipo) {
        super();
        this.codigoTipo = codigoTipo;
    }

    /**
     * Retorna o valor do atributo codigoIndicativoTipoEvento.
     * @return o valor do atributo codigoIndicativoTipoEvento
     */
    public int getCodigoIndicativoTipoEvento() {
        return codigoIndicativoTipoEvento;
    }

    /**
     * Especifica o valor do atributo codigoIndicativoTipoEvento.
     * @param codigoIndicativoTipoEvento - int do codigoIndicativoTipoEvento a ser configurado.
     */
    public void setCodigoIndicativoTipoEvento(int codigoIndicativoTipoEvento) {
        this.codigoIndicativoTipoEvento = codigoIndicativoTipoEvento;
    }

    /**
     * Construtor
     */
    public EventoContabilVO() {
        super();
    }

    /**
     * Contrutor
     * @param codigoTipo - int
     * @param descricaoTipo - String
     */
    public EventoContabilVO(int codigoTipo, String descricaoTipo) {
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
        return this.getCodigoIndicativoTipoEvento();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EventoContabilVO)) {
            return false;
        }
        return (((EventoContabilVO) o).getCodigoTipo() == this.getCodigoTipo());
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
		StringBuilder sb = new StringBuilder("EventoContabilVO[")
				.append(codigoTipo).append(',').append(descricaoTipo)
				.append(',').append(codigoIndicativoTipoEvento).append(']').append(super.toString());
    	return sb.toString();
    }
}
