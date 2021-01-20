package br.com.bradseg.depi.depositoidentificado.funcao.action;

public class CrudForm extends BaseForm {
	
	private static final long serialVersionUID = 981209754548198337L;

	/**
	 * Representa um dos estados que o formulário está. 
	 */
	public static enum EstadoCrud {
		EXIBIR, INCLUIR, ALTERAR, REMOVER;
	}
	
	private EstadoCrud estado;
	
	public EstadoCrud getEstado() {
		return estado;
	}
	
	public void setEstado(EstadoCrud estado) {
		this.estado = estado;
	}

}
