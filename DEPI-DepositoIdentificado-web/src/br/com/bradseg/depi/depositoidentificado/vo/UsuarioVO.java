package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe Anotada de Mapeamento com a tabela USUAR.
 * @author Globality
 */
@XmlRootElement(name="Usuario")
public class UsuarioVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7474657010868812876L;

    /**
     * Construtor default
     */
	public UsuarioVO() {
		super();
	}

	/**
     * Código de usuario.
     */
	private int codigoUsuario;

	/**
     * Nome Usuario.
     */
	private String nomeUsuario;

	/**
     * XXXXXXXXXXX
     */
	private Date dataInclusao;

	/**
     * XXXXXXXXXXX
     */
	private Date dataUltimaAtualizacao;

	/**
     * Nome Usuario.
     */

	private String indicadorRegistroAtivo; 

	/**
     * XXXXXXXXXXX
     */
	private int codigoUsuarioAtualizacao; 

	/**
     * Retorna o XXXXXXXXXXX
     * @return XXXXXXXXXXX
     */
	public Date getDataInclusao() {
		return (Date) dataInclusao.clone();
	}

	/**
     * Define dataInclusao.
     * {@inheritDoc}
     */
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = (Date) dataInclusao.clone();
	}

	/**
     * Retorna o XXXXXXXXXXX
     * @return XXXXXXXXXXX
     */
	public Date getDataUltimaAtualizacao() {
		return (Date) dataUltimaAtualizacao.clone();
	}

	/**
     * Define dataUltimaAtualizacao.
     * {@inheritDoc}
     */
	public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = (Date) dataUltimaAtualizacao.clone();
	}

	/**
     * Retorna o XXXXXXXXXXX
     * @return XXXXXXXXXXX
     */
	public String getIndicadorRegistroAtivo() {
		return indicadorRegistroAtivo;
	}

	/**
     * Define indicadorRegistroAtivo.
     * {@inheritDoc}
     */
	public void setIndicadorRegistroAtivo(String indicadorRegistroAtivo) {
		this.indicadorRegistroAtivo = indicadorRegistroAtivo;
	}

	/**
     * Retorna o XXXXXXXXXXX
     * @return XXXXXXXXXXX
     */
	public int getCodigoUsuarioAtualizacao() {
		return codigoUsuarioAtualizacao;
	}

	/**
     * Define codigoUsuarioAtualizacao.
     * {@inheritDoc}
     */
	public void setCodigoUsuarioAtualizacao(int codigoUsuarioAtualizacao) {
		this.codigoUsuarioAtualizacao = codigoUsuarioAtualizacao;
	}

	/**
     * setCodigoUsuario
     * @param codigoUsuario codigoUsuario
     */
	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	/**
     * Retorna o codigoUsuario.
     * @return O atributo codigoUsuario
     */
	public int getCodigoUsuario() {
		return this.codigoUsuario;
	}

	/**
     * Retorna o nomeUsuario.
     * @return O atributo nomeUsuario
     */
	public String getNomeUsuario() {
		return this.nomeUsuario;
	}

	/**
     * setNomeUsuario
     * @param nomeUsuario nomeUsuario
     */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

}
