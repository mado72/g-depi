package br.com.bradseg.depi.depositoidentificado.form.cadastro;

import br.com.bradseg.depi.depositoidentificado.form.BaseForm;

/**
 * Representa o modelo do formulário da consulta para Departamentos 
 * @author Marcelo Damasceno
 */
public class DepartamentoEditarForm extends BaseForm {

	private static final long serialVersionUID = 957768938376772158L;

	public static final String NOME_FORM = DepartamentoEditarForm.class.getSimpleName();
	
	private String codigo;
	
	private boolean detalhar;
	
	private String siglaDepartamento;
	
	private String nomeDepartamento;

	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public boolean isDetalhar() {
		return detalhar;
	}
	
	public void setDetalhar(boolean detalhar) {
		this.detalhar = detalhar;
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
	
}
