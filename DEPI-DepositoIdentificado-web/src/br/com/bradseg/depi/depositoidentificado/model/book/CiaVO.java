package br.com.bradseg.depi.depositoidentificado.model.book;

import java.io.Serializable;

import br.com.cpmbraxis.framework.cicsannotations.annotation.CicsField;
import br.com.cpmbraxis.framework.cicsannotations.util.CicsConstants.UseType;

/**
 * Classe que representa as informa��es de uma CiaVO em Especifico e que ser� utilizada dentro de GTAB1426.
 * @author Lazaro
 */
public class CiaVO implements Serializable {

	/**
     * Serial Version
     */
	private static final long serialVersionUID = 2839245722643991847L;

	/**
     * Dados da Entidade
     */
	@CicsField(order = 0, size = 4, pattern = "0000", use = UseType.Out)
	//private int codigoDefault;  
    private int codigoExterno;

	@CicsField(order = 1, size = 1, pattern = "0", use = UseType.Out)
	private int digito;

	@CicsField(order = 2, size = 4, pattern = "0000", use = UseType.Out)
	//private int codigoInterno;
    private int codigoDefault;      
	
	@CicsField(order = 3, size = 50, use = UseType.Out)
	private String nome;

	@CicsField(order = 4, size = 20, use = UseType.Out)
	private String sigla;

	@CicsField(order = 5, size = 1, use = UseType.Out)
	private String situacao;

	@CicsField(order = 6, size = 1, use = UseType.Out)
	private String grupo;
   
	/**
     * Retorna o c�digo da Companhia Formatado.
     * @return String.
     */
	public String getCodigoCompanhiaFormatado() {
		return String.valueOf(getCodigoExterno());
	}

	/**
     * M�todo que retorna o valor que ser� mostrado(Label) no combo companhia
     * @return codigoDefault ou codigoExterno
     */
	public int getCodigoCompanhiaLabel() {
		return getCodigoDefault();
	}

	/**
     * {@inheritDoc}
     */
	public boolean equals(Object obj) {
		return (obj instanceof CiaVO) && ((((CiaVO) obj).getCodigoDefault() == this.getCodigoDefault()));
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public int hashCode() {
        if(getGrupo() != null){
            return getGrupo().hashCode();
        }else{
            return 0;
        }
	}

	/**
     * Retorna o serialVersionUID.
     * @return O atributo serialVersionUID
     */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
     * Retorna o valor do atributo codigoDefault.
     * @return o valor do atributo codigoDefault
     */
    public int getCodigoDefault() {
    	return codigoDefault;
    }

	/**
     * Especifica o valor do atributo codigoDefault.
     * @param codigoDefault - int do codigoDefault a ser configurado.
     */
    public void setCodigoDefault(int codigoDefault) {
    	this.codigoDefault = codigoDefault;
    }


	/**
     * Retorna o nome.
     * @return O atributo nome
     */
	public String getNome() {
		return this.nome;
	}

	/**
     * Especifica o nome.
     * @param nome String do nome a ser setado
     */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
     * Retorna o sigla.
     * @return O atributo sigla
     */
	public String getSigla() {
		return this.sigla;
	}

	/**
     * Especifica o sigla.
     * @param sigla String do sigla a ser setado
     */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	/**
     * Retorna o grupo.
     * @return O atributo grupo
     */
	public String getGrupo() {
		return this.grupo;
	}

	/**
     * Especifica o grupo.
     * @param grupo String do grupo a ser setado
     */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
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
     * Retorna o valor do atributo digito.
     * @return o valor do atributo digito
     */
	public int getDigito() {
		return digito;
	}

	/**
     * Especifica o valor do atributo digito.
     * @param digito - int do digito a ser configurado.
     */
	public void setDigito(int digito) {
		this.digito = digito;
	}

    /**
     * Retorna o valor do atributo codigoExterno.
     * @return o valor do atributo codigoExterno
     */
    public int getCodigoExterno() {
        return codigoExterno;
    }

    /**
     * Especifica o valor do atributo codigoExterno.
     * @param codigoExterno - int do codigoExterno a ser configurado.
     */
    public void setCodigoExterno(int codigoExterno) {
        this.codigoExterno = codigoExterno;
    }

}
