package br.com.bradseg.depi.depositoidentificado.cadastro.form;

import java.util.ArrayList;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;

/**
 * Representa o modelo do formulário da consulta para associação de Companhia e Departamentos 
 */
public class DepartamentoCompanhiaEditarFormModel extends CrudForm {

	private static final long serialVersionUID = 957768938376772158L;

	private List<CompanhiaSeguradoraVO> cias = new ArrayList<>();
	
	private List<DepartamentoVO> deptos = new ArrayList<>();
	
	private List<Integer> codDepartamentos = new ArrayList<>();

	@Override
	public void limparDados() {
		setCodigo(null);
		if (codDepartamentos != null) {
			codDepartamentos.clear();
		}
		if (cias != null) {
			cias.clear();
		}
		if (deptos != null) {
			deptos.clear();
		}
	}

	/**
	 * Retorna cias
	 * @return o cias
	 */
	public List<CompanhiaSeguradoraVO> getCias() {
		return cias;
	}

	/**
	 * Define cias
	 * @param cias valor cias a ser definido
	 */
	public void setCias(List<CompanhiaSeguradoraVO> cias) {
		this.cias = cias;
	}

	/**
	 * Retorna deptos
	 * @return o deptos
	 */
	public List<DepartamentoVO> getDeptos() {
		return deptos;
	}

	/**
	 * Define deptos
	 * @param deptos valor deptos a ser definido
	 */
	public void setDeptos(List<DepartamentoVO> deptos) {
		this.deptos = deptos;
	}

	/**
	 * Retorna codDepartamentos
	 * @return o codDepartamentos
	 */
	public List<Integer> getCodDepartamentos() {
		return codDepartamentos;
	}

	/**
	 * Define codDepartamentos
	 * @param codDepartamentos valor codDepartamentos a ser definido
	 */
	public void setCodDepartamentos(List<Integer> codDepartamentos) {
		this.codDepartamentos = codDepartamentos;
	}
	
}