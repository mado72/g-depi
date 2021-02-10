package br.com.bradseg.depi.depositoidentificado.cadastro.form;

import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm;

/**
 * Representa o modelo do formulário da consulta para Departamentos 
 * @author Marcelo Damasceno
 */
public class DepartamentoEditarFormModel extends CrudForm {

	private static final long serialVersionUID = 957768938376772158L;

	public static final String NOME_FORM = DepartamentoEditarFormModel.class.getSimpleName();
	
	private String codCompanhia;
	
	private String siglaDepartamento;
	
	private String nomeDepartamento;
	
	/**
	 * Retorna codCompanhia
	 * @return o codCompanhia
	 */
	public String getCodCompanhia() {
		return codCompanhia;
	}
	
	/**
	 * Define codCompanhia
	 * @param codCompanhia valor codCompanhia a ser definido
	 */
	public void setCodCompanhia(String codCompanhia) {
		this.codCompanhia = codCompanhia;
	}
	
	/**
	 * Retorna siglaDepartamento
	 * @return o siglaDepartamento
	 */
	public String getSiglaDepartamento() {
		return siglaDepartamento;
	}
	
	/**
	 * Define siglaDepartamento
	 * @param siglaDepartamento valor siglaDepartamento a ser definido
	 */
	public void setSiglaDepartamento(String siglaDepartamento) {
		this.siglaDepartamento = siglaDepartamento;
	}
	
	/**
	 * Retorna nomeDepartamento
	 * @return o nomeDepartamento
	 */
	public String getNomeDepartamento() {
		return nomeDepartamento;
	}
	
	/**
	 * Define nomeDepartamento
	 * @param nomeDepartamento valor nomeDepartamento a ser definido
	 */
	public void setNomeDepartamento(String nomeDepartamento) {
		this.nomeDepartamento = nomeDepartamento;
	}

	@Override
	public void limparDados() {
		setCodigo(null);
		siglaDepartamento = "";
		nomeDepartamento = "";
	}
	
}
