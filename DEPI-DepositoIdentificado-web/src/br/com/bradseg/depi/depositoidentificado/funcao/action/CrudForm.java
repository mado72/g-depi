package br.com.bradseg.depi.depositoidentificado.funcao.action;

import java.io.Serializable;

/**
 * Classe abstrata para os formulários que implementam CRUD.
 * @author Marcelo Damasceno
 */
public abstract class CrudForm implements Serializable {
	
	private static final long serialVersionUID = 981209754548198337L;

	/**
	 * Representa um dos estados que o formulário está. 
	 */
	public static enum EstadoCrud {
		EXIBIR, INSERIR, ALTERAR, REMOVER;
	}
	
	private String subtitulo;
	
	private String codigo;
	
	private EstadoCrud estado;
	
	private String ipCliente;
	
	public abstract void preencherDadosIniciais();
	
	public boolean isDetalhar() {
		return this.estado == EstadoCrud.EXIBIR;
	}
	
	public boolean isDesabiltarChave() {
		return this.estado != EstadoCrud.INSERIR;
	}
	
	/**
	 * Retorna Estado
	 * @return estado corrente
	 */
	public EstadoCrud getEstado() {
		return estado;
	}
	
	/**
	 * Define Estado
	 * @param estado para definir
	 */
	public void setEstado(EstadoCrud estado) {
		this.estado = estado;
	}
	
	/**
	 * Obtém o subtítulo
	 * @return subtítulo.
	 */
	public String getSubtitulo() {
		return subtitulo;
	}
	
	/**
	 * Define o subtítlo
	 * @param subtitulo valor a ser definido
	 */
	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}
	
	/**
	 * Retorna o código informado.
	 * @return Retorna o código.
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * Define o código relacionado
	 * @param codigo código
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getIpCliente() {
		return ipCliente;
	}
	
	public void setIpCliente(String ipCliente) {
		this.ipCliente = ipCliente;
	}

}
