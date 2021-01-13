package br.com.bradseg.depi.depositoidentificado.form.cadastro;

import br.com.bradseg.depi.depositoidentificado.form.AdmfinBPFiltroForm;

/**
 * Representa o modelo do formulário da consulta para Motivo de Depósito 
 * @author Marcelo Damasceno
 */
public class MotivoDepositoEditarForm extends AdmfinBPFiltroForm {

	private static final long serialVersionUID = 957768938376772158L;

	public static final String NOME_FORM = MotivoDepositoEditarForm.class.getSimpleName();
	
	private String codigo;
	
	private String descricaoBasica;
	
	private String descricaoDetalhada;
	
	private boolean detalhar;
	
	@Override
	public String getContextoFiltro() {
		return NOME_FORM;
	}
	
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
		return detalhar;
	}
	
	public void setDetalhar(boolean detalhar) {
		this.detalhar = detalhar;
	}
	
}
