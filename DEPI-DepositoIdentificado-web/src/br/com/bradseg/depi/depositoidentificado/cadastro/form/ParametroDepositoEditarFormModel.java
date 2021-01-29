package br.com.bradseg.depi.depositoidentificado.cadastro.form;

import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm;

/**
 * FIXME Implementar
 * 
 * Representa o modelo do formulário da consulta para Parâmetro 
 * @author Marcelo Damasceno
 */
public class ParametroDepositoEditarFormModel extends CrudForm {

	private static final long serialVersionUID = 957768938376772158L;

	@Override
	public boolean isDetalhar() {
		return getEstado() == EstadoCrud.EXIBIR;
	}

	@Override
	public void limparDados() {
		setCodigo(null);
	}
	
}
