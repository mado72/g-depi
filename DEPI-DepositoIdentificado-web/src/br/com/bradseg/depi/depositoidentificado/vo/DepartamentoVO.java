/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe Anotada de Mapeamento com a tabela DEPTO.
 * @author Globality
 */
@XmlRootElement(name="DepartamentoVO")
public class DepartamentoVO implements Serializable {

    private static final long serialVersionUID = -7156547889624154032L;
    
    /**
     * Construtor.
     */
    public DepartamentoVO() {
    	super();
    }

    /**
     * Construtor.
     * @param codigoDepartamento - int.
     * @param nomeDepartamento - String.
     * @param siglaDepartamento - String.
     */
    public DepartamentoVO(Integer codigoDepartamento, String nomeDepartamento, String siglaDepartamento) {
        this.setCodigoDepartamento(codigoDepartamento);
        this.setNomeDepartamento(nomeDepartamento);
        this.setSiglaDepartamento(siglaDepartamento);
    }

    /**
     * Código seq�encial de departamento.
     */
    private int codigoDepartamento;

    /**
     * Nome Departamento.
     */
    private String nomeDepartamento;

    /**
     * Sigla do Departamento.
     */
    private String siglaDepartamento;
    
    /**
     * Codigo Responsavel Ultima Atualizacao.
     */
    private Integer codigoResponsavelUltimaAtualizacao;

    /**
     * Indicado Registro Ativo.
     */
    private String indicadoRegistroAtivo;
    
    private DepositoVO deposito;
    
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
     * Retorna o nomeDepartamento.
     * @return O atributo nomeDepartamento
     */
    public String getNomeDepartamento() {
        return this.nomeDepartamento;
    }

    /**
     * Especifica o nomeDepartamento.
     * @param nomeDepartamento String do nomeDepartamento a ser setado
     */
    public void setNomeDepartamento(String nomeDepartamento) {
        if (nomeDepartamento != null) {
        	this.nomeDepartamento = nomeDepartamento.trim().toUpperCase();
        }
    }

    /**
     * Retorna o siglaDepartamento.
     * @return O atributo siglaDepartamento
     */
    public String getSiglaDepartamento() {
        return this.siglaDepartamento;
    }

    /**
     * Especifica o siglaDepartamento.
     * @param siglaDepartamento String do siglaDepartamento a ser setado
     */
    public void setSiglaDepartamento(String siglaDepartamento) {
        if (siglaDepartamento != null) {
        	this.siglaDepartamento = siglaDepartamento.trim().toUpperCase();
        }
    }

    /**
     * Retorna o codigoDepartamento.
     * @return O atributo codigoDepartamento
     */
    public int getCodigoDepartamento() {
        return this.codigoDepartamento;
    }

    /**
     * Especifica o codigoDepartamento.
     * @param codigoDepartamento Integer do codigoDepartamento a ser setado
     */
    public void setCodigoDepartamento(int codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    /**
     * Retorna o CodigoResponsavelUltimaAtualizacao.
     * @return O atributo CodigoResponsavelUltimaAtualizacao
     */
	public Integer getCodigoResponsavelUltimaAtualizacao() {
		return codigoResponsavelUltimaAtualizacao;
	}

    /**
     * Especifica o CodigoResponsavelUltimaAtualizacao.
     * @param codigoResponsavelUltimaAtualizacao Integer do codigoDepartamento a ser setado
     */
	public void setCodigoResponsavelUltimaAtualizacao(Integer codigoResponsavelUltimaAtualizacao) {
		this.codigoResponsavelUltimaAtualizacao = codigoResponsavelUltimaAtualizacao;
	}

    /**
     * Retorna o IndicadoRegistroAtivo.
     * @return O atributo indicadoRegistroAtivo
     */
	public String getIndicadoRegistroAtivo() {
		return indicadoRegistroAtivo;
	}
	
    /**
     * Especifica o IndicadoRegistroAtivo.
     * @param indicadoRegistroAtivo String do codigoDepartamento a ser setado
     */
	public void setIndicadoRegistroAtivo(String indicadoRegistroAtivo) {
		this.indicadoRegistroAtivo = indicadoRegistroAtivo;
	}
    
    
}
