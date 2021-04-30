/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe Anotada de Mapeamento com a tabela CIA_SEGDR.
 * @author Globality
 */
@XmlRootElement(name="CompanhiaSeguradoraVO")
public class CompanhiaSeguradoraVO implements Serializable {

    /**
     * Serial Version.
     */
    private static final long serialVersionUID = 3985002053131501638L;

    /**
     * Código da Companhia. maxLenght = 4
     */
    private int codigoCompanhia;

    /**
     * Descrição da Cia
     */
    private String descricaoCompanhia;

    /**
     * Construtor padrão
     */
    public CompanhiaSeguradoraVO() {
        super();
    }

    /**
     * Construtor
     * @param codigoCompanhia - int.
     */
    public CompanhiaSeguradoraVO(int codigoCompanhia) {
        super();
        this.codigoCompanhia = codigoCompanhia;
    }

    /**
     * retorna o codigo companhia
     * @return o codigoCompanhia
     */
    public int getCodigoCompanhia() {
        return codigoCompanhia;
    }

    /**
     * configura codigo companhia
     * @param codigoCompanhia o codigoCompanhia a ser configurado
     */
    public void setCodigoCompanhia(int codigoCompanhia) {
        this.codigoCompanhia = codigoCompanhia;
    }

    /**
     * retorna descrição
     * @return o descricaoCompanhia
     */
    public String getDescricaoCompanhia() {
        return descricaoCompanhia;
    }

    /**
     * configura descrição
     * @param descricaoCompanhia o descricaoCompanhia a ser configurado
     */
    public void setDescricaoCompanhia(String descricaoCompanhia) {
        this.descricaoCompanhia = descricaoCompanhia;
    }

}
