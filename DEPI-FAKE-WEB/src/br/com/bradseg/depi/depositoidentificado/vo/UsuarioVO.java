package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe Anotada de Mapeamento com a tabela USUAR.
 * @author Globality
 */
@XmlRootElement(name = "Usuario")
public class UsuarioVO implements Serializable {

	private static final long serialVersionUID = -7474657010868812876L;

	/**
	 * Construtor default
	 */
	public UsuarioVO() {
		super();
	}
	
	/**
	 * Construtor para definir codigoUsuario
	 * @param codigoUsuario Código do usuário
	 */
	public UsuarioVO(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
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
	 * Data da inclusão
	 */
	private Date dataInclusao;

	/**
	 * Data da última atualização
	 */
	private Date dataUltimaAtualizacao;

	/**
	 * Indicador se o registro está ativo.
	 */
	private String indicadorRegistroAtivo;

	/**
	 * Código do usuário da última atualização
	 */
	private int responsavelAtualizacao;

	/**
	 * Retorna o data da inclusão
	 * 
	 * @return data
	 */
	public Date getDataInclusao() {
		return (Date) dataInclusao.clone();
	}

	/**
	 * Define a data da inclusão
	 * 
	 * @param dataInclusao
	 *            Data
	 */
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = (Date) dataInclusao.clone();
	}

	/**
	 * Retorna a data da última atualização
	 * 
	 * @return data
	 */
	public Date getDataUltimaAtualizacao() {
		return (Date) dataUltimaAtualizacao.clone();
	}

	/**
	 * Define dataUltimaAtualizacao.
	 * 
	 * @param dataUltimaAtualizacao
	 *            data
	 */
	public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = (Date) dataUltimaAtualizacao.clone();
	}

	/**
	 * Retorna o indicador se está ativo
	 * 
	 * @return indicador
	 */
	public String getIndicadorRegistroAtivo() {
		return indicadorRegistroAtivo;
	}

	/**
	 * Define indicadorRegistroAtivo
	 * @param indicadorRegistroAtivo valor indicadorRegistroAtivo a ser definido
	 */
	public void setIndicadorRegistroAtivo(String indicadorRegistroAtivo) {
		this.indicadorRegistroAtivo = indicadorRegistroAtivo;
	}

	/**
	 * Retorna responsavelAtualizacao
	 * 
	 * @return o responsavelAtualizacao
	 */
	public int getResponsavelAtualizacao() {
		return responsavelAtualizacao;
	}

	/**
	 * Define responsavelAtualizacao
	 * 
	 * @param responsavelAtualizacao
	 *            valor responsavelAtualizacao a ser definido
	 */
	public void setResponsavelAtualizacao(int responsavelAtualizacao) {
		this.responsavelAtualizacao = responsavelAtualizacao;
	}

	/**
	 * Define codigoUsuario
	 * 
	 * @param codigoUsuario
	 *            valor codigoUsuario a ser definido
	 */
	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	/**
	 * Retorna codigoUsuario
	 * 
	 * @return o codigoUsuario
	 */
	public int getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * Retorna o nomeUsuario.
	 * 
	 * @return O atributo nomeUsuario
	 */
	public String getNomeUsuario() {
		return this.nomeUsuario;
	}

	/**
	 * setNomeUsuario
	 * 
	 * @param nomeUsuario
	 *            nomeUsuario
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

}
