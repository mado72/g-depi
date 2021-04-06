package br.com.bradseg.depi.depositoidentificado.cadastro.form;

import org.apache.commons.lang3.StringUtils;

import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.vo.DepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MovimentoDepositoVO;

/**
 *  
 * @author Globality
 */
public class MovimentoEditarFormModel extends CrudForm {

	private static final long serialVersionUID = 332896009519486803L;
	
	private String salvarAction;
	
	private String codigoMovimento;
	
	private String divAutorizador;
	
	private String motivo;
	
	private String codBanco;
	
	private String codAgencia;
	
	private String conta;
	
	private String codFuncionario;
	
	private String cpfCnpj;
	
	private String nomePessoa;
	
	private String dataDeposito;
	
	private String dataMovimento;
	
	private String vlrDepositoRegistrado;
	
	private String vlrDepositado;
	
	private String codMovimentoAcao;
	
	private String bancoMovimento;
	
	private String agenciaMovimento;
	
	private String contaMovimento;
	
	private String chequeMovimento;
	
	private String historico;

	@Override
	public void preencherDadosIniciais() {
	}

	public void preencherDeposito(DepositoVO vo) {
		setCodigoMovimento(String.valueOf(vo.getCodigoDepositoIdentificado()));
		setDivAutorizador(String.valueOf(vo.getDIVISOR()));
		setCodFuncionario(String.valueOf(vo.getCodigoResponsavelUltimaAtualizacao()));
		setMotivo(vo.getMotivoDeposito().getDescricaoBasica());
		setCodBanco(String.valueOf(vo.getBanco().getCdBancoExterno()));
		setCodAgencia(String.valueOf(vo.getCodigoAgencia()));
		setConta(String.valueOf(vo.getContaCorrente()));
		setNomePessoa(vo.getNomePessoa());
		setDataDeposito(BaseUtil.SDF_DDMMYYYY.format(vo.getDtVencimentoDeposito()));
		setVlrDepositoRegistrado(BaseUtil.formatarValor(vo.getVlrDepositoRegistrado()));
		setVlrDepositado("Sem Lan\u00E7amento");
		
		if (vo.getCpfCnpj().length() < 12) {
			setCpfCnpj(BaseUtil.getCpfFormatado(vo.getCpfCnpj()));
		}
		else {
			setCpfCnpj(BaseUtil.getCnpjFormatado(vo.getCpfCnpj()));
		}
	}
	
	public void preencherMovimento(MovimentoDepositoVO vo) {
		setDataMovimento(BaseUtil.formatData(vo.getDataHoraAtualizacao()));
		setBancoMovimento(marshallLong(vo.getBancoMovimento()));
		setAgenciaMovimento(marshallLong(vo.getAgenciaMovimento()));
		setContaMovimento(marshallLong(vo.getContaMovimento()));
		
		setChequeMovimento(marshallLong(vo.getNroCheque()));
		setHistorico(vo.getObservacao());
		setCodMovimentoAcao(vo.getIndicacaoAcao());
	}

	public MovimentoDepositoVO obterMovimento() {
		MovimentoDepositoVO vo = new MovimentoDepositoVO();
		vo.setCodigoMovimento(Long.parseLong(getCodigoMovimento()));
		
		vo.setIndicacaoAcao(getCodMovimentoAcao());
		vo.setObservacao(getHistorico());
		
		vo.setNroCheque(parseLong(getChequeMovimento()));
		vo.setBancoMovimento(parseLong(getBancoMovimento()));
		vo.setAgenciaMovimento(parseLong(getAgenciaMovimento()));
		vo.setContaMovimento(parseLong(getContaMovimento()));
		
		return vo;
	}
	
	private String marshallLong(long l) {
		if (l == 0L) {
			return null;
		}
		return String.valueOf(l);
	}
	
	private long parseLong(String v) {
		if (StringUtils.isEmpty(v)) {
			return 0L;
		}
		return Long.parseLong(v);
	}
	
	public String getSalvarAction() {
		return salvarAction;
	}
	
	public void setSalvarAction(String salvarAction) {
		this.salvarAction = salvarAction;
	}
	
	public String getCodigoMovimento() {
		return codigoMovimento;
	}

	public void setCodigoMovimento(String codigoAutorizador) {
		this.codigoMovimento = codigoAutorizador;
	}

	public String getDivAutorizador() {
		return divAutorizador;
	}

	public void setDivAutorizador(String divAutorizador) {
		this.divAutorizador = divAutorizador;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getCodBanco() {
		return codBanco;
	}

	public void setCodBanco(String codBanco) {
		this.codBanco = codBanco;
	}

	public String getCodAgencia() {
		return codAgencia;
	}

	public void setCodAgencia(String codAgencia) {
		this.codAgencia = codAgencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getCodFuncionario() {
		return codFuncionario;
	}

	public void setCodFuncionario(String codFuncionario) {
		this.codFuncionario = codFuncionario;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getDataDeposito() {
		return dataDeposito;
	}

	public void setDataDeposito(String dataDeposito) {
		this.dataDeposito = dataDeposito;
	}

	public String getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(String dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	public String getVlrDepositoRegistrado() {
		return vlrDepositoRegistrado;
	}

	public void setVlrDepositoRegistrado(String registroBanco) {
		this.vlrDepositoRegistrado = registroBanco;
	}

	public String getVlrDepositado() {
		return vlrDepositado;
	}

	public void setVlrDepositado(String depositado) {
		this.vlrDepositado = depositado;
	}

	public String getCodMovimentoAcao() {
		return codMovimentoAcao;
	}

	public void setCodMovimentoAcao(String acao) {
		this.codMovimentoAcao = acao;
	}

	public String getBancoMovimento() {
		return bancoMovimento;
	}

	public void setBancoMovimento(String bancoMovimento) {
		this.bancoMovimento = bancoMovimento;
	}

	public String getAgenciaMovimento() {
		return agenciaMovimento;
	}

	public void setAgenciaMovimento(String agenciaMovimento) {
		this.agenciaMovimento = agenciaMovimento;
	}

	public String getContaMovimento() {
		return contaMovimento;
	}

	public void setContaMovimento(String contaMovimento) {
		this.contaMovimento = contaMovimento;
	}

	public String getChequeMovimento() {
		return chequeMovimento;
	}

	public void setChequeMovimento(String chequeMovimento) {
		this.chequeMovimento = chequeMovimento;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

}
