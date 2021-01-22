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
	
	private EstadoCrud estado;
	
	private String acao;
	
	public abstract void limparDados();
	
	public boolean isDetalhar() {
		return this.estado == EstadoCrud.EXIBIR;
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
	 * Retorna Ação
	 * @return Ação
	 */
	public String getAcao() {
		return acao;
	}
	
	/**
	 * Define a ação
	 * @param acao Ação
	 */
	public void setAcao(String acao) {
		this.acao = acao;
	}

}
