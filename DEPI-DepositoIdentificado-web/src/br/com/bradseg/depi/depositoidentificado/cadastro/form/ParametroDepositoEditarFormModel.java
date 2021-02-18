package br.com.bradseg.depi.depositoidentificado.cadastro.form;

import java.util.ArrayList;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

/**
 * Representa o modelo do formulário da consulta para Parâmetro
 * 
 * @author Marcelo Damasceno
 */
public class ParametroDepositoEditarFormModel extends CrudForm {

	public static final String VALOR_NAO = "N";

	public static final String VALOR_SIM = "S";

	private static final long serialVersionUID = 957768938376772158L;
	
	private List<CompanhiaSeguradoraVO> cias;
	
	private List<DepartamentoVO> deptos;
	
	private List<MotivoDepositoVO> motivos;
	
	private String codigoCompanhia;
	
	private String codigoDepartamento;
	
	private String codigoMotivoDeposito;
	
	private String codigoBancoVencimento;

	private String descricaoBancoVencimento;

	private String numeroDiasAposVencimento;

	private String codigoItem;

	private String codigoRamo;

	private String codigoTipo;

	private String codigoSucursal;

	private String codigoProtocolo;

	private String codigoApolice;

	private String codigoCpfCnpj;

	private String codigoDossie;

	private String codigoBloqueto;

	private String codigoEndosso;

	private String codigoParcela;

	private String outrosDocumentosNecessarios;

	private String referenciadoDeposito;

	private String descricaoDeposito;

	private String descricaoBasicaMotivo;

	private String descricaoDetalhadaMotivo;
	
	/**
	 * Prepara o formulário com os valores padrões
	 */
	public ParametroDepositoEditarFormModel() {
		preencherDadosIniciais();
	}
	
	@Override
	public boolean isDetalhar() {
		return getEstado() == EstadoCrud.EXIBIR;
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
	 * Retorna motivos
	 * @return o motivos
	 */
	public List<MotivoDepositoVO> getMotivos() {
		return motivos;
	}

	/**
	 * Define motivos
	 * @param motivos valor motivos a ser definido
	 */
	public void setMotivos(List<MotivoDepositoVO> motivos) {
		this.motivos = motivos;
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
	 * Retorna codigoDepartamento
	 * @return o codigoDepartamento
	 */
	public String getCodigoDepartamento() {
		return codigoDepartamento;
	}

	/**
	 * Define codigoDepartamento
	 * @param codigoDepartamento valor codigoDepartamento a ser definido
	 */
	public void setCodigoDepartamento(String codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

	/**
	 * Retorna codigoMotivoDeposito
	 * @return o codigoMotivoDeposito
	 */
	public String getCodigoMotivoDeposito() {
		return codigoMotivoDeposito;
	}

	/**
	 * Define codigoMotivoDeposito
	 * @param codigoMotivoDeposito valor codigoMotivoDeposito a ser definido
	 */
	public void setCodigoMotivoDeposito(String codigoMotivoDeposito) {
		this.codigoMotivoDeposito = codigoMotivoDeposito;
	}

	/**
	 * Retorna codigoBancoVencimento
	 * @return o codigoBancoVencimento
	 */
	public String getCodigoBancoVencimento() {
		return codigoBancoVencimento;
	}

	/**
	 * Define codigoBancoVencimento
	 * @param codigoBancoVencimento valor codigoBancoVencimento a ser definido
	 */
	public void setCodigoBancoVencimento(String codigoBancoVencimento) {
		this.codigoBancoVencimento = codigoBancoVencimento;
	}

	/**
	 * Retorna descricaoBancoVencimento
	 * @return o descricaoBancoVencimento
	 */
	public String getDescricaoBancoVencimento() {
		return descricaoBancoVencimento;
	}

	/**
	 * Define descricaoBancoVencimento
	 * @param descricaoBancoVencimento valor descricaoBancoVencimento a ser definido
	 */
	public void setDescricaoBancoVencimento(String descricaoBancoVencimento) {
		this.descricaoBancoVencimento = descricaoBancoVencimento;
	}

	/**
	 * Retorna numeroDiasAposVencimento
	 * @return o numeroDiasAposVencimento
	 */
	public String getNumeroDiasAposVencimento() {
		return numeroDiasAposVencimento;
	}

	/**
	 * Define numeroDiasAposVencimento
	 * @param numeroDiasAposVencimento valor numeroDiasAposVencimento a ser definido
	 */
	public void setNumeroDiasAposVencimento(String numeroDiasAposVencimento) {
		this.numeroDiasAposVencimento = numeroDiasAposVencimento;
	}

	/**
	 * Retorna codigoItem
	 * @return o codigoItem
	 */
	public String getCodigoItem() {
		return codigoItem;
	}

	/**
	 * Define codigoItem
	 * @param codigoItem valor codigoItem a ser definido
	 */
	public void setCodigoItem(String codigoItem) {
		this.codigoItem = codigoItem;
	}

	/**
	 * Retorna codigoRamo
	 * @return o codigoRamo
	 */
	public String getCodigoRamo() {
		return codigoRamo;
	}

	/**
	 * Define codigoRamo
	 * @param codigoRamo valor codigoRamo a ser definido
	 */
	public void setCodigoRamo(String codigoRamo) {
		this.codigoRamo = codigoRamo;
	}

	/**
	 * Retorna codigoTipo
	 * @return o codigoTipo
	 */
	public String getCodigoTipo() {
		return codigoTipo;
	}

	/**
	 * Define codigoTipo
	 * @param codigoTipo valor codigoTipo a ser definido
	 */
	public void setCodigoTipo(String codigoTipo) {
		this.codigoTipo = codigoTipo;
	}

	/**
	 * Retorna codigoSucursal
	 * @return o codigoSucursal
	 */
	public String getCodigoSucursal() {
		return codigoSucursal;
	}

	/**
	 * Define codigoSucursal
	 * @param codigoSucursal valor codigoSucursal a ser definido
	 */
	public void setCodigoSucursal(String codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	/**
	 * Retorna codigoProtocolo
	 * @return o codigoProtocolo
	 */
	public String getCodigoProtocolo() {
		return codigoProtocolo;
	}

	/**
	 * Define codigoProtocolo
	 * @param codigoProtocolo valor codigoProtocolo a ser definido
	 */
	public void setCodigoProtocolo(String codigoProtocolo) {
		this.codigoProtocolo = codigoProtocolo;
	}

	/**
	 * Retorna codigoApolice
	 * @return o codigoApolice
	 */
	public String getCodigoApolice() {
		return codigoApolice;
	}

	/**
	 * Define codigoApolice
	 * @param codigoApolice valor codigoApolice a ser definido
	 */
	public void setCodigoApolice(String codigoApolice) {
		this.codigoApolice = codigoApolice;
	}

	/**
	 * Retorna codigoCpfCnpj
	 * @return o codigoCpfCnpj
	 */
	public String getCodigoCpfCnpj() {
		return codigoCpfCnpj;
	}

	/**
	 * Define codigoCpfCnpj
	 * @param codigoCpfCnpj valor codigoCpfCnpj a ser definido
	 */
	public void setCodigoCpfCnpj(String codigoCpfCnpj) {
		this.codigoCpfCnpj = codigoCpfCnpj;
	}

	/**
	 * Retorna codigoDossie
	 * @return o codigoDossie
	 */
	public String getCodigoDossie() {
		return codigoDossie;
	}

	/**
	 * Define codigoDossie
	 * @param codigoDossie valor codigoDossie a ser definido
	 */
	public void setCodigoDossie(String codigoDossie) {
		this.codigoDossie = codigoDossie;
	}

	/**
	 * Retorna codigoBloqueto
	 * @return o codigoBloqueto
	 */
	public String getCodigoBloqueto() {
		return codigoBloqueto;
	}

	/**
	 * Define codigoBloqueto
	 * @param codigoBloqueto valor codigoBloqueto a ser definido
	 */
	public void setCodigoBloqueto(String codigoBloqueto) {
		this.codigoBloqueto = codigoBloqueto;
	}

	/**
	 * Retorna codigoEndosso
	 * @return o codigoEndosso
	 */
	public String getCodigoEndosso() {
		return codigoEndosso;
	}

	/**
	 * Define codigoEndosso
	 * @param codigoEndosso valor codigoEndosso a ser definido
	 */
	public void setCodigoEndosso(String codigoEndosso) {
		this.codigoEndosso = codigoEndosso;
	}

	/**
	 * Retorna codigoParcela
	 * @return o codigoParcela
	 */
	public String getCodigoParcela() {
		return codigoParcela;
	}

	/**
	 * Define codigoParcela
	 * @param codigoParcela valor codigoParcela a ser definido
	 */
	public void setCodigoParcela(String codigoParcela) {
		this.codigoParcela = codigoParcela;
	}

	/**
	 * Retorna outrosDocumentosNecessarios
	 * @return o outrosDocumentosNecessarios
	 */
	public String getOutrosDocumentosNecessarios() {
		return outrosDocumentosNecessarios;
	}

	/**
	 * Define outrosDocumentosNecessarios
	 * @param outrosDocumentosNecessarios valor outrosDocumentosNecessarios a ser definido
	 */
	public void setOutrosDocumentosNecessarios(String outrosDocumentosNecessarios) {
		this.outrosDocumentosNecessarios = outrosDocumentosNecessarios;
	}

	/**
	 * Retorna referenciadoDeposito
	 * @return o referenciadoDeposito
	 */
	public String getReferenciadoDeposito() {
		return referenciadoDeposito;
	}

	/**
	 * Define referenciadoDeposito
	 * @param referenciadoDeposito valor referenciadoDeposito a ser definido
	 */
	public void setReferenciadoDeposito(String referenciadoDeposito) {
		this.referenciadoDeposito = referenciadoDeposito;
	}

	/**
	 * Retorna descricaoDeposito
	 * @return o descricaoDeposito
	 */
	public String getDescricaoDeposito() {
		return descricaoDeposito;
	}

	/**
	 * Define descricaoDeposito
	 * @param descricaoDeposito valor descricaoDeposito a ser definido
	 */
	public void setDescricaoDeposito(String descricaoDeposito) {
		this.descricaoDeposito = descricaoDeposito;
	}

	/**
	 * Retorna descricaoBasicaMotivo
	 * @return o descricaoBasicaMotivo
	 */
	public String getDescricaoBasicaMotivo() {
		return descricaoBasicaMotivo;
	}

	/**
	 * Define descricaoBasicaMotivo
	 * @param descricaoBasicaMotivo valor descricaoBasicaMotivo a ser definido
	 */
	public void setDescricaoBasicaMotivo(String descricaoBasicaMotivo) {
		this.descricaoBasicaMotivo = descricaoBasicaMotivo;
	}

	/**
	 * Retorna descricaoDetalhadaMotivo
	 * @return o descricaoDetalhadaMotivo
	 */
	public String getDescricaoDetalhadaMotivo() {
		return descricaoDetalhadaMotivo;
	}

	/**
	 * Define descricaoDetalhadaMotivo
	 * @param descricaoDetalhadaMotivo valor descricaoDetalhadaMotivo a ser definido
	 */
	public void setDescricaoDetalhadaMotivo(String descricaoDetalhadaMotivo) {
		this.descricaoDetalhadaMotivo = descricaoDetalhadaMotivo;
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm#limparDados()
	 */
	@Override
	public void preencherDadosIniciais() {
		setCodigoCompanhia(null);
		setCodigoDepartamento(null);
		setCodigoMotivoDeposito(null);

		setCodigoApolice(VALOR_SIM);
		setCodigoCpfCnpj(VALOR_SIM);
		setCodigoEndosso(VALOR_SIM);
		setCodigoRamo(VALOR_SIM);
		setCodigoSucursal(VALOR_SIM);
		setCodigoItem(VALOR_SIM);

		setCodigoBancoVencimento(VALOR_NAO);
		setCodigoBloqueto(VALOR_NAO);
		setCodigoDossie(VALOR_NAO);
		setCodigoParcela(VALOR_NAO);
		setCodigoProtocolo(VALOR_NAO);
		setCodigoTipo(VALOR_NAO);
		
		setDescricaoBancoVencimento(null);
		setDescricaoBasicaMotivo(null);
		setDescricaoDeposito(null);
		setDescricaoDetalhadaMotivo(null);
		setNumeroDiasAposVencimento("0");
		setOutrosDocumentosNecessarios(null);
		setReferenciadoDeposito(null);
		
		setCias(new ArrayList<CompanhiaSeguradoraVO>());
		setDeptos(new ArrayList<DepartamentoVO>());
		setMotivos(new ArrayList<MotivoDepositoVO>());
	}

}
