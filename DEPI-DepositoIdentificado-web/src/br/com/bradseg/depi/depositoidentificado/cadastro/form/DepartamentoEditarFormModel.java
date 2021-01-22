package br.com.bradseg.depi.depositoidentificado.cadastro.form;

import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm;

/**
 * Representa o modelo do formulário da consulta para Departamentos 
 * @author Marcelo Damasceno
 */
public class DepartamentoEditarFormModel extends CrudForm {

	private static final long serialVersionUID = 957768938376772158L;

	public static final String NOME_FORM = DepartamentoEditarFormModel.class.getSimpleName();
	
	private String codigo;
	
	private String siglaDepartamento;
	
	private String nomeDepartamento;

	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getSiglaDepartamento() {
		return siglaDepartamento;
	}

	public void setSiglaDepartamento(String siglaDepartamento) {
		this.siglaDepartamento = siglaDepartamento;
	}

	public String getNomeDepartamento() {
		return nomeDepartamento;
	}

	public void setNomeDepartamento(String nomeDepartamento) {
		this.nomeDepartamento = nomeDepartamento;
	}

	@Override
	public void limparDados() {
		codigo = "";
		siglaDepartamento = "";
		nomeDepartamento = "";
	}
	
}
