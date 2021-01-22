package br.com.bradseg.depi.depositoidentificado.cadastro.form;

import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm;

/**
 * Representa o modelo do formulário da consulta para Motivo de Depósito 
 * @author Marcelo Damasceno
 */
public class MotivoDepositoEditarForm extends CrudForm {

	private static final long serialVersionUID = 957768938376772158L;

	private String codigo;
	
	private String descricaoBasica;
	
	private String descricaoDetalhada;
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricaoBasica() {
		return descricaoBasica;
	}

	public void setDescricaoBasica(String descricaoBasica) {
		this.descricaoBasica = descricaoBasica;
	}

	public String getDescricaoDetalhada() {
		return descricaoDetalhada;
	}

	public void setDescricaoDetalhada(String descricaoDetalhada) {
		this.descricaoDetalhada = descricaoDetalhada;
	}

	public boolean isDetalhar() {
		return getEstado() == EstadoCrud.EXIBIR;
	}
	
}
