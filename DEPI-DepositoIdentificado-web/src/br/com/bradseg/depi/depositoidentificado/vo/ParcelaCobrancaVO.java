package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe que representa os atributos da tabela de parcela da cobrança(CBBS).
 * @author Globality
 */
public class ParcelaCobrancaVO implements Serializable {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1L;
	
	private DepositoVO deposito;
	
	private Long codigoParcela;
	private String codigoIdentificadorOrigem;
	private Double valorParcelaCobrado;
	private Double valorIofCobrado;
	private Date dataVencimento;
	private Date dataPagamentoBanco;
	private Double valorIofRecebido;
	private Double valorParcelaRecebido;
	
	private Date dataHoraInclusao;
	private Long codigoUsuarioAtualizador;
	private Date dataHoraUltimaAtualizacao;
	
	private String nomePessoa;
	private String cpfCnpj;
	
	/**
	 * Método que retorna o objeto com os dados de depósito.
	 * @return - o objeto com os dados de depósito.
	 */
	public DepositoVO getDeposito() {
		return deposito;
	}
	/**
	 * Método que define o objeto com os dados de depósito.
	 * @param deposito - o objeto com os dados de depósito.
	 */
	public void setDeposito(DepositoVO deposito) {
		this.deposito = deposito;
	}
	/**
	 * Método que retorna o código da parcela.
	 * @return Long - o código da parcela.
	 */
	public Long getCodigoParcela() {
		return codigoParcela;
	}
	/**
	 * Método que define o código da parcela.
	 * @param codigoParcela - o código da parcela.
	 */
	public void setCodigoParcela(Long codigoParcela) {
		this.codigoParcela = codigoParcela;
	}
	/**
	 * Método que retorna o valor da parcela cobrado.
	 * @return Double - o valor da parcela cobrado.
	 */
	public Double getValorParcelaCobrado() {
		return valorParcelaCobrado;
	}
	/**
	 * Método que define o valor da parcela cobrado.
	 * @param valorParcelaCobrado - o valor da parcela cobrado.
	 */
	public void setValorParcelaCobrado(Double valorParcelaCobrado) {
		this.valorParcelaCobrado = valorParcelaCobrado;
	}
	/**
	 * Método que retorna o valor iof cobrado.
	 * @return Double - o valor iof cobrado.
	 */
	public Double getValorIofCobrado() {
		return valorIofCobrado;
	}
	/**
	 * Método que define o valor iof cobrado.
	 * @param valorIofCobrado - o valor iof cobrado.
	 */
	public void setValorIofCobrado(Double valorIofCobrado) {
		this.valorIofCobrado = valorIofCobrado;
	}
	/**
	 * Método que retorna a data de vencimento.
	 * @return Date - a data de vencimento.
	 */
	public Date getDataVencimento() {
		return (Date) dataVencimento.clone();
	}
	/**
	 * Método que define a data de vencimento.
	 * @param dataVencimento - a data de vencimento.
	 */
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = (Date) dataVencimento.clone();
	}
	/**
	 * Método que retorna a data de pagamento do banco.
	 * @return Date - a data de pagamento do banco.
	 */
	public Date getDataPagamentoBanco() {
		return (Date) dataPagamentoBanco.clone();
	}
	/**
	 * Método que define a data de pagamento do banco.
	 * @param dataPagamentoBanco - a data de pagamento do banco.
	 */
	public void setDataPagamentoBanco(Date dataPagamentoBanco) {
		this.dataPagamentoBanco = (Date) dataPagamentoBanco.clone();
	}
	/**
	 * Método que retorna o valor iof recebido.
	 * @return Double - o valor iof recebido.
	 */
	public Double getValorIofRecebido() {
		return valorIofRecebido;
	}
	/**
	 * Método que define o valor iof recebido.
	 * @param valorIofRecebido - o valor iof recebido.
	 */
	public void setValorIofRecebido(Double valorIofRecebido) {
		this.valorIofRecebido = valorIofRecebido;
	}
	/**
	 * Método que retorna o valor parcela recebido.
	 * @return Double - o valor parcela recebido.
	 */
	public Double getValorParcelaRecebido() {
		return valorParcelaRecebido;
	}
	/**
	 * Método que define o valor parcela recebido.
	 * @param valorParcelaRecebido - o valor parcela recebido.
	 */
	public void setValorParcelaRecebido(Double valorParcelaRecebido) {
		this.valorParcelaRecebido = valorParcelaRecebido;
	}
	/**
	 * Método que retorna a data/hora da inclusão.
	 * @return Date - a data/hora da inclusão.
	 */
	public Date getDataHoraInclusao() {
		return (Date) dataHoraInclusao.clone();
	}
	/**
	 * Método que define a data/hora da inclusão.
	 * @param dataHoraInclusao - a data/hora da inclusão.
	 */
	public void setDataHoraInclusao(Date dataHoraInclusao) {
		this.dataHoraInclusao = (Date) dataHoraInclusao.clone();
	}
	/**
	 * Método que retorna o código do usuário atualizador.
	 * @return Long - o código do usuário atualizador.
	 */
	public Long getCodigoUsuarioAtualizador() {
		return codigoUsuarioAtualizador;
	}
	/**
	 * Método que define o código do usuário atualizador.
	 * @param codigoUsuarioAtualizador - o código do usuário atualizador.
	 */
	public void setCodigoUsuarioAtualizador(Long codigoUsuarioAtualizador) {
		this.codigoUsuarioAtualizador = codigoUsuarioAtualizador;
	}
	/**
	 * Método que retorna a data/hora da última atualização.
	 * @return Date - a data/hora da última atualização.
	 */
	public Date getDataHoraUltimaAtualizacao() {
		return (Date) dataHoraUltimaAtualizacao.clone();
	}
	/**
	 * Método que define a data/hora da última atualização.
	 * @param dataHoraUltimaAtualizacao - a data/hora da última atualização.
	 */
	public void setDataHoraUltimaAtualizacao(Date dataHoraUltimaAtualizacao) {
		this.dataHoraUltimaAtualizacao = (Date) dataHoraUltimaAtualizacao.clone();
	}
	/**
	 * Método que retorna o nome da pessoa.
	 * @return String - o nome da pessoa.
	 */
	public String getNomePessoa() {
		return nomePessoa;
	}
	/**
	 * Método que define o nome da pessoa.
	 * @param nomePessoa - o nome da pessoa.
	 */
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}
	/**
	 * Método que retorna o código identificador de origem da parcela.
	 * @return String - o código identificador de origem da parcela.
	 */
	public String getCodigoIdentificadorOrigem() {
		return codigoIdentificadorOrigem;
	}
	/**
	 * Método que define o código identificador de origem da parcela.
	 * @param codigoIdentificadorOrigem - o código identificador de origem da parcela.
	 */
	public void setCodigoIdentificadorOrigem(String codigoIdentificadorOrigem) {
		this.codigoIdentificadorOrigem = codigoIdentificadorOrigem;
	}
	/**
	 * Método que retorna o cpf ou cnpj.
	 * @return the cpfCnpj - o número do documento.
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	/**
	 * Método que define o cpf ou cnpj.
	 * @param cpfCnpj the cpfCnpj to set
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
}
