package br.com.bradseg.depi.depositoidentificado.cadastro.form;

import java.util.ArrayList;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.vo.AssociarMotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CodigoValorVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

/**
 * Representa o modelo do formulário da consulta para Conta Corrente
 * 
 * @author Marcelo Damasceno
 */
public class AssociarMotivoDepositoEditarFormModel extends CrudForm {

	private static final long serialVersionUID = 957768938376772158L;
	
	private List<CompanhiaSeguradoraVO> cias = new ArrayList<>();
	
	private List<DepartamentoVO> deptos = new ArrayList<>();
	
	private List<MotivoDepositoVO> motivos = new ArrayList<>();
	
	private List<BancoVO> bancos = new ArrayList<>();
	
	private List<CodigoValorVO> agencias = new ArrayList<>();
	
	private String codigoCompanhia;
	
	private String codigoDepartamento;
	
	private String codigoMotivo;
	
	private String codigoEventoContabil;
	
	private String descricaoEventoContabil;
	
	private String codigoItemContabil;
	
	private String descricaoItemContabil;
	
	private String codigoBanco;
	
	private String descricaoBanco;
	
	private String codigoAgencia;
	
	private String descricaoAgencia;

	private String contaCorrente;

	private String contaInterna;

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm#limparDados()
	 */
	@Override
	public void preencherDadosIniciais() {
		setCias(new ArrayList<CompanhiaSeguradoraVO>());
		setDeptos(new ArrayList<DepartamentoVO>());
		setMotivos(new ArrayList<MotivoDepositoVO>());
		setBancos(new ArrayList<BancoVO>());
		setAgencias(new ArrayList<CodigoValorVO>());
		
		setCodigoAgencia(null);
		setCodigo(null);
		setCodigoBanco(null);
		setCodigoCompanhia(null);
		setContaCorrente(null);
		setContaInterna(null);
		setCodigoDepartamento(null);
		setCodigoEventoContabil(null);
		setCodigoItemContabil(null);
		setCodigoMotivo(null);
		setDescricaoAgencia(null);
		setDescricaoBanco(null);
		setDescricaoEventoContabil(null);
		setDescricaoItemContabil(null);
	}
	
	/**
	 * Preenche os valores de acordo com os dados do
	 * {@link AssociarMotivoDepositoVO} e os outros parâmetros advindos do CICS.
	 * 
	 * @param vo
	 *            AssociarMotivoDepositoVO
	 * @param descricaoAgencia
	 *            Descrição da agência (CICS)
	 * @param descricaoBanco
	 *            Descrição do banco (CICS)
	 * @param descricaoEventoContabil
	 *            Descrição Evento Contábil (CICS)
	 * @param descricaoItemContabil
	 *            Descrição Item Contábil (CICS)
	 */
	public void preencherCampos(AssociarMotivoDepositoVO vo,
			String descricaoAgencia, String descricaoBanco,
			String descricaoEventoContabil, String descricaoItemContabil) {
		
		setCodigo(null);
		setCodigoAgencia(BaseUtil.getValueMaskFormat("99999", BaseUtil.blankIfNull(vo.getCodigoAgencia()), true));
		setCodigoBanco(BaseUtil.getValueMaskFormat("9999", BaseUtil.blankIfNull(vo.getBanco().getCdBancoExterno()), true));
		setCodigoCompanhia(String.valueOf(vo.getCia().getCodigoCompanhia()));
		setContaCorrente(BaseUtil.getValueMaskFormat("0000000000000", BaseUtil.blankIfNull(vo.getContaCorrente()), true));
		setContaInterna(BaseUtil.getValueMaskFormat("999999", BaseUtil.blankIfNull(vo.getBanco().getCdBancoInterno()), true));
		setCodigoDepartamento(String.valueOf(vo.getDepartamento().getCodigoDepartamento()));
		setCodigoEventoContabil(String.valueOf(vo.getMotivoDeposito().getCodigoEventoContabil()));
		setCodigoItemContabil(String.valueOf(vo.getMotivoDeposito().getCodigoItemContabil()));
		setCodigoMotivo(String.valueOf(vo.getMotivoDeposito().getCodigoMotivoDeposito()));
		
		// Campos que necessitam de consulta ao CICS
		setDescricaoAgencia(descricaoAgencia);
		setDescricaoBanco(descricaoBanco);
		setDescricaoEventoContabil(descricaoEventoContabil);
		setDescricaoItemContabil(descricaoItemContabil);
	}
	
	/**
	 * Preenche os dados em uma instância de {@link AssociarMotivoDepositoVO} a partir dos
	 * valores do formulário
	 * @param vo Instância de {@link AssociarMotivoDepositoVO}
	 */
	public void obterValores(AssociarMotivoDepositoVO vo) {
		
		vo.setCodigoAgencia(Integer.parseInt(getCodigoAgencia()));
		vo.setBanco(new BancoVO(Integer.parseInt(getCodigoBanco())));
		vo.setCia(new CompanhiaSeguradoraVO(Integer.parseInt(getCodigoCompanhia())));
		vo.setContaCorrente(Long.parseLong(getContaCorrente()));
		vo.setDepartamento(new DepartamentoVO(Integer.parseInt(getCodigoDepartamento())));
		vo.setMotivoDeposito(new MotivoDepositoVO(Integer.parseInt(getCodigoMotivo())));
		
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
	 * Retorna bancos
	 * @return o bancos
	 */
	public List<BancoVO> getBancos() {
		return bancos;
	}

	/**
	 * Define bancos
	 * @param bancos valor bancos a ser definido
	 */
	public void setBancos(List<BancoVO> bancos) {
		this.bancos = bancos;
	}

	/**
	 * Retorna agencias
	 * @return o agencias
	 */
	public List<CodigoValorVO> getAgencias() {
		return agencias;
	}

	/**
	 * Define agencias
	 * @param agencias valor agencias a ser definido
	 */
	public void setAgencias(List<CodigoValorVO> agencias) {
		this.agencias = agencias;
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
	 * Retorna codigoMotivo
	 * @return o codigoMotivo
	 */
	public String getCodigoMotivo() {
		return codigoMotivo;
	}

	/**
	 * Define codigoMotivo
	 * @param codigoMotivo valor codigoMotivo a ser definido
	 */
	public void setCodigoMotivo(String codigoMotivo) {
		this.codigoMotivo = codigoMotivo;
	}

	/**
	 * Retorna codigoEventoContabil
	 * @return o codigoEventoContabil
	 */
	public String getCodigoEventoContabil() {
		return codigoEventoContabil;
	}

	/**
	 * Define codigoEventoContabil
	 * @param codigoEventoContabil valor codigoEventoContabil a ser definido
	 */
	public void setCodigoEventoContabil(String codigoEventoContabil) {
		this.codigoEventoContabil = codigoEventoContabil;
	}

	/**
	 * Retorna descricaoEventoContabil
	 * @return o descricaoEventoContabil
	 */
	public String getDescricaoEventoContabil() {
		return descricaoEventoContabil;
	}

	/**
	 * Define descricaoEventoContabil
	 * @param descricaoEventoContabil valor descricaoEventoContabil a ser definido
	 */
	public void setDescricaoEventoContabil(String descricaoEventoContabil) {
		this.descricaoEventoContabil = descricaoEventoContabil;
	}

	/**
	 * Retorna codigoItemContabil
	 * @return o codigoItemContabil
	 */
	public String getCodigoItemContabil() {
		return codigoItemContabil;
	}

	/**
	 * Define codigoItemContabil
	 * @param codigoItemContabil valor codigoItemContabil a ser definido
	 */
	public void setCodigoItemContabil(String codigoItemContabil) {
		this.codigoItemContabil = codigoItemContabil;
	}

	/**
	 * Retorna dscricaoItemContabil
	 * @return o dscricaoItemContabil
	 */
	public String getDescricaoItemContabil() {
		return descricaoItemContabil;
	}

	/**
	 * Define dscricaoItemContabil
	 * @param dscricaoItemContabil valor dscricaoItemContabil a ser definido
	 */
	public void setDescricaoItemContabil(String dscricaoItemContabil) {
		this.descricaoItemContabil = dscricaoItemContabil;
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
	 * Retorna descricaoBanco
	 * @return o descricaoBanco
	 */
	public String getDescricaoBanco() {
		return descricaoBanco;
	}

	/**
	 * Define descricaoBanco
	 * @param descricaoBanco valor descricaoBanco a ser definido
	 */
	public void setDescricaoBanco(String descricaoBanco) {
		this.descricaoBanco = descricaoBanco;
	}

	/**
	 * Retorna agencia
	 * @return o agencia
	 */
	public String getCodigoAgencia() {
		return codigoAgencia;
	}

	/**
	 * Define agencia
	 * @param agencia valor agencia a ser definido
	 */
	public void setCodigoAgencia(String agencia) {
		this.codigoAgencia = agencia;
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

}
