package br.com.bradseg.depi.depositoidentificado.cadastro.form;

import java.util.ArrayList;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;

/**
 * Representa o modelo do formulário da consulta para Conta Corrente
 * 
 * @author Marcelo Damasceno
 */
public class AssociacaoMotivoDepositoEditarFormModel extends CrudForm {

	private static final long serialVersionUID = 957768938376772158L;
	
	private List<CompanhiaSeguradoraVO> cias = new ArrayList<>();
	
	private String codigoCompanhia;
	
	private String codigoBanco;
	
	private String descricaoBanco;
	
	private String agencia;
	
	private String descricaoAgencia;

	private String contaCorrente;

	private String contaInterna;

	private String trps;

	private String observacao;
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm#limparDados()
	 */
	@Override
	public void preencherDadosIniciais() {
		setCodigoCompanhia(null);

		setCias(new ArrayList<CompanhiaSeguradoraVO>());
		
		setAgencia(null);
		setCodigo(null);
		setCodigoBanco(null);
		setContaCorrente(null);
		setContaInterna(null);
		setObservacao(null);
		setTrps(null);
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
	 * Retorna codigoCompanhia
	 * @return o codigoCompanhia
	 */
	public String getCodigoCompanhia() {
		return codigoCompanhia;
	}

	/**
	 * Define codigoCompanhia
	 * @param codigoCompanhia valor codigoCompanhia a ser definido
	 */
	public void setCodigoCompanhia(String codigoCompanhia) {
		this.codigoCompanhia = codigoCompanhia;
	}

	/**
	 * Retorna codigoBanco
	 * @return o codigoBanco
	 */
	public String getCodigoBanco() {
		return codigoBanco;
	}

	/**
	 * Define codigoBanco
	 * @param codigoBanco valor codigoBanco a ser definido
	 */
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	/**
	 * Retorna nomeBanco
	 * @return o nomeBanco
	 */
	public String getDescricaoBanco() {
		return descricaoBanco;
	}
	
	/**
	 * Define nomeBanco
	 * @param nomeBanco valor nomeBanco a ser definido
	 */
	public void setDescricaoBanco(String nomeBanco) {
		this.descricaoBanco = nomeBanco;
	}
	
	/**
	 * Retorna agencia
	 * @return o agencia
	 */
	public String getAgencia() {
		return agencia;
	}

	/**
	 * Define agencia
	 * @param agencia valor agencia a ser definido
	 */
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	
	/**
	 * Retorna descricaoAgencia
	 * @return o descricaoAgencia
	 */
	public String getDescricaoAgencia() {
		return descricaoAgencia;
	}
	
	/**
	 * Define descricaoAgencia
	 * @param descricaoAgencia valor descricaoAgencia a ser definido
	 */
	public void setDescricaoAgencia(String descricaoAgencia) {
		this.descricaoAgencia = descricaoAgencia;
	}

	/**
	 * Retorna contaCorrente
	 * @return o contaCorrente
	 */
	public String getContaCorrente() {
		return contaCorrente;
	}

	/**
	 * Define contaCorrente
	 * @param contaCorrente valor contaCorrente a ser definido
	 */
	public void setContaCorrente(String contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	/**
	 * Retorna contaInterna
	 * @return o contaInterna
	 */
	public String getContaInterna() {
		return contaInterna;
	}

	/**
	 * Define contaInterna
	 * @param contaInterna valor contaInterna a ser definido
	 */
	public void setContaInterna(String contaInterna) {
		this.contaInterna = contaInterna;
	}

	/**
	 * Retorna trps
	 * @return o trps
	 */
	public String getTrps() {
		return trps;
	}

	/**
	 * Define trps
	 * @param trps valor trps a ser definido
	 */
	public void setTrps(String trps) {
		this.trps = trps;
	}

	/**
	 * Retorna historico
	 * @return o historico
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * Define historico
	 * @param historico valor historico a ser definido
	 */
	public void setObservacao(String historico) {
		this.observacao = historico;
	}

}
