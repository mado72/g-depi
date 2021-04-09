package br.com.bradseg.depi.depositoidentificado.cadastro.form;

import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.vo.DepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.LancamentoDepositoVO;

/**
 * Representa um lançamento. 
 * @author Globality
 */
public class ConsultarLancamentoFormModel extends CrudForm {

	private static final long serialVersionUID = 332896009519486803L;
	
	private String codigoCompanhia;
	private String descricaoCompanhia;
	private String siglaDepartamento;
	private String nomeDepartamento;
	private String codigoMotivoDeposito;
	private String descricaoMotivoDetalhada;
	private String codBanco;
	private String nomeBanco;
	private String codAgencia;
	private String nomeAgencia;
	private String contaCorrente;
	private String agenciaAcolhedora;
	private String nomeAgenciaAcolhedora;
	private String valorDinheiro;
	private String bancoCheque;
	private String nomeBancoCheque;
	private String agenciaCheque;
	private String nomeAgenciaCheque;
	private String cheque; // format="###############"
	private String contaCheque; // "############"
	private String valorCheque; // "#,###,##0.00"
	private String valorTotalDeposito; // "#,###,##0.00"
	private String codigoDepositoIdentificado;
	private String codigoDigitoDeposito;
	
	@Override
	public void preencherDadosIniciais() {
		// Sem preenchimento
	}

	public void preencherDeposito(DepositoVO vo) {
		setCodigoCompanhia(String.valueOf(vo.getCia().getCodigoCompanhia()));
		setDescricaoCompanhia(vo.getCia().getDescricaoCompanhia());
		setSiglaDepartamento(vo.getDepartamento().getSiglaDepartamento());
		setNomeAgencia(vo.getDepartamento().getNomeDepartamento());
		setCodigoMotivoDeposito(String.valueOf(vo.getMotivoDeposito().getCodigoMotivoDeposito()));
		setDescricaoMotivoDetalhada(vo.getMotivoDeposito().getDescricaoDetalhada());
		setCodBanco(String.valueOf(vo.getBanco().getCdBancoExterno()));
		setNomeBanco(vo.getBanco().getDescricaoBanco());
		setCodAgencia(String.valueOf(vo.getAgencia().getCdAgenciaExterno()));
		setNomeAgencia(vo.getAgencia().getDescricaoAgencia());
		setContaCorrente(String.valueOf(vo.getContaCorrente()));
		setCodigoDepositoIdentificado(String.valueOf(vo.getCodigoDepositoIdentificado()));
		setCodigoDigitoDeposito(String.valueOf(vo.getCodigoDigitoDeposito()));
	}
	
	public void preencherLancamento(LancamentoDepositoVO vo) {
		setValorDinheiro(BaseUtil.formatarValor(vo.getValorDinheiro()));

		if (vo.getBancoCheque() != null) {
			setBancoCheque(String.valueOf(vo.getBancoCheque().getCdBancoExterno()));
			setNomeBancoCheque(vo.getBancoCheque().getDescricaoBanco());
		}
		else {
			setBancoCheque("");
			setNomeBancoCheque("");
		}
		
		if (vo.getAgenciaCheque() != null) {
			setAgenciaCheque(String.valueOf(vo.getAgenciaCheque().getCdAgenciaExterno()));
			setNomeAgenciaCheque(vo.getAgenciaCheque().getDescricaoAgencia());
		}
		else {
			setAgenciaCheque("");
			setNomeAgenciaCheque("");
		}
		
		if (vo.getAgenciaAcolhedora() != null) {
			setAgenciaAcolhedora(String.valueOf(vo.getAgenciaAcolhedora().getCdAgenciaExterno()));
			setNomeAgenciaAcolhedora(vo.getAgenciaAcolhedora().getDescricaoAgencia());
		}
		else {
			setAgenciaAcolhedora("");
			setNomeAgenciaAcolhedora("");
		}
		
		setCheque(BaseUtil.formatarValor(vo.getCheque(), "000000000000000"));
		setContaCheque(BaseUtil.formatarValor(vo.getContaCheque(), "000000000000"));
		setValorCheque(BaseUtil.formatarValor(vo.getValorCheq()));
		setValorTotalDeposito(BaseUtil.formatarValor(vo.getValorTotalDeposito()));
	}
	
	public String getCodigoCompanhia() {
		return codigoCompanhia;
	}

	public void setCodigoCompanhia(String codigoCompanhia) {
		this.codigoCompanhia = codigoCompanhia;
	}

	public String getDescricaoCompanhia() {
		return descricaoCompanhia;
	}

	public void setDescricaoCompanhia(String descricaoCompanhia) {
		this.descricaoCompanhia = descricaoCompanhia;
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

	public String getCodigoMotivoDeposito() {
		return codigoMotivoDeposito;
	}

	public void setCodigoMotivoDeposito(String codigoMotivoDeposito) {
		this.codigoMotivoDeposito = codigoMotivoDeposito;
	}

	public String getDescricaoMotivoDetalhada() {
		return descricaoMotivoDetalhada;
	}

	public void setDescricaoMotivoDetalhada(String descricaoMotivoDetalhada) {
		this.descricaoMotivoDetalhada = descricaoMotivoDetalhada;
	}

	public String getCodBanco() {
		return codBanco;
	}

	public void setCodBanco(String codBanco) {
		this.codBanco = codBanco;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public String getCodAgencia() {
		return codAgencia;
	}

	public void setCodAgencia(String codAgencia) {
		this.codAgencia = codAgencia;
	}

	public String getNomeAgencia() {
		return nomeAgencia;
	}

	public void setNomeAgencia(String nomeAgencia) {
		this.nomeAgencia = nomeAgencia;
	}

	public String getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(String contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	public String getAgenciaAcolhedora() {
		return agenciaAcolhedora;
	}

	public void setAgenciaAcolhedora(String agenciaAcolhedora) {
		this.agenciaAcolhedora = agenciaAcolhedora;
	}

	public String getNomeAgenciaAcolhedora() {
		return nomeAgenciaAcolhedora;
	}

	public void setNomeAgenciaAcolhedora(String nomeAgenciaAcolhedora) {
		this.nomeAgenciaAcolhedora = nomeAgenciaAcolhedora;
	}

	public String getValorDinheiro() {
		return valorDinheiro;
	}

	public void setValorDinheiro(String valorDinheiro) {
		this.valorDinheiro = valorDinheiro;
	}

	public String getBancoCheque() {
		return bancoCheque;
	}

	public void setBancoCheque(String bancoCheque) {
		this.bancoCheque = bancoCheque;
	}

	public String getNomeBancoCheque() {
		return nomeBancoCheque;
	}

	public void setNomeBancoCheque(String nomeBancoCheque) {
		this.nomeBancoCheque = nomeBancoCheque;
	}

	public String getAgenciaCheque() {
		return agenciaCheque;
	}

	public void setAgenciaCheque(String agenciaCheque) {
		this.agenciaCheque = agenciaCheque;
	}

	public String getNomeAgenciaCheque() {
		return nomeAgenciaCheque;
	}

	public void setNomeAgenciaCheque(String nomeAgenciaCheque) {
		this.nomeAgenciaCheque = nomeAgenciaCheque;
	}

	public String getCheque() {
		return cheque;
	}

	public void setCheque(String cheque) {
		this.cheque = cheque;
	}

	public String getContaCheque() {
		return contaCheque;
	}

	public void setContaCheque(String contaCheque) {
		this.contaCheque = contaCheque;
	}

	public String getValorCheque() {
		return valorCheque;
	}

	public void setValorCheque(String valorCheque) {
		this.valorCheque = valorCheque;
	}

	public String getValorTotalDeposito() {
		return valorTotalDeposito;
	}

	public void setValorTotalDeposito(String valorTotalDeposito) {
		this.valorTotalDeposito = valorTotalDeposito;
	}

	public String getCodigoDepositoIdentificado() {
		return codigoDepositoIdentificado;
	}

	public void setCodigoDepositoIdentificado(String codigoDepositoIdentificado) {
		this.codigoDepositoIdentificado = codigoDepositoIdentificado;
	}

	public String getCodigoDigitoDeposito() {
		return codigoDigitoDeposito;
	}

	public void setCodigoDigitoDeposito(String codigoDigitoDeposito) {
		this.codigoDigitoDeposito = codigoDigitoDeposito;
	}

}
