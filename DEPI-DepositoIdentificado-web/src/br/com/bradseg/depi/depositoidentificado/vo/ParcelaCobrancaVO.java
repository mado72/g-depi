package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe que representa os atributos da tabela de parcela da cobran�a(CBBS).
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
	 * M�todo que retorna o objeto com os dados de dep�sito.
	 * @return - o objeto com os dados de dep�sito.
	 */
	public DepositoVO getDeposito() {
		return deposito;
	}
	/**
	 * M�todo que define o objeto com os dados de dep�sito.
	 * @param deposito - o objeto com os dados de dep�sito.
	 */
	public void setDeposito(DepositoVO deposito) {
		this.deposito = deposito;
	}
	/**
	 * M�todo que retorna o c�digo da parcela.
	 * @return Long - o c�digo da parcela.
	 */
	public Long getCodigoParcela() {
		return codigoParcela;
	}
	/**
	 * M�todo que define o c�digo da parcela.
	 * @param codigoParcela - o c�digo da parcela.
	 */
	public void setCodigoParcela(Long codigoParcela) {
		this.codigoParcela = codigoParcela;
	}
	/**
	 * M�todo que retorna o valor da parcela cobrado.
	 * @return Double - o valor da parcela cobrado.
	 */
	public Double getValorParcelaCobrado() {
		return valorParcelaCobrado;
	}
	/**
	 * M�todo que define o valor da parcela cobrado.
	 * @param valorParcelaCobrado - o valor da parcela cobrado.
	 */
	public void setValorParcelaCobrado(Double valorParcelaCobrado) {
		this.valorParcelaCobrado = valorParcelaCobrado;
	}
	/**
	 * M�todo que retorna o valor iof cobrado.
	 * @return Double - o valor iof cobrado.
	 */
	public Double getValorIofCobrado() {
		return valorIofCobrado;
	}
	/**
	 * M�todo que define o valor iof cobrado.
	 * @param valorIofCobrado - o valor iof cobrado.
	 */
	public void setValorIofCobrado(Double valorIofCobrado) {
		this.valorIofCobrado = valorIofCobrado;
	}
	/**
	 * M�todo que retorna a data de vencimento.
	 * @return Date - a data de vencimento.
	 */
	public Date getDataVencimento() {
		return (Date) dataVencimento.clone();
	}
	/**
	 * M�todo que define a data de vencimento.
	 * @param dataVencimento - a data de vencimento.
	 */
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = (Date) dataVencimento.clone();
	}
	/**
	 * M�todo que retorna a data de pagamento do banco.
	 * @return Date - a data de pagamento do banco.
	 */
	public Date getDataPagamentoBanco() {
		return (Date) dataPagamentoBanco.clone();
	}
	/**
	 * M�todo que define a data de pagamento do banco.
	 * @param dataPagamentoBanco - a data de pagamento do banco.
	 */
	public void setDataPagamentoBanco(Date dataPagamentoBanco) {
		this.dataPagamentoBanco = (Date) dataPagamentoBanco.clone();
	}
	/**
	 * M�todo que retorna o valor iof recebido.
	 * @return Double - o valor iof recebido.
	 */
	public Double getValorIofRecebido() {
		return valorIofRecebido;
	}
	/**
	 * M�todo que define o valor iof recebido.
	 * @param valorIofRecebido - o valor iof recebido.
	 */
	public void setValorIofRecebido(Double valorIofRecebido) {
		this.valorIofRecebido = valorIofRecebido;
	}
	/**
	 * M�todo que retorna o valor parcela recebido.
	 * @return Double - o valor parcela recebido.
	 */
	public Double getValorParcelaRecebido() {
		return valorParcelaRecebido;
	}
	/**
	 * M�todo que define o valor parcela recebido.
	 * @param valorParcelaRecebido - o valor parcela recebido.
	 */
	public void setValorParcelaRecebido(Double valorParcelaRecebido) {
		this.valorParcelaRecebido = valorParcelaRecebido;
	}
	/**
	 * M�todo que retorna a data/hora da inclus�o.
	 * @return Date - a data/hora da inclus�o.
	 */
	public Date getDataHoraInclusao() {
		return (Date) dataHoraInclusao.clone();
	}
	/**
	 * M�todo que define a data/hora da inclus�o.
	 * @param dataHoraInclusao - a data/hora da inclus�o.
	 */
	public void setDataHoraInclusao(Date dataHoraInclusao) {
		this.dataHoraInclusao = (Date) dataHoraInclusao.clone();
	}
	/**
	 * M�todo que retorna o c�digo do usu�rio atualizador.
	 * @return Long - o c�digo do usu�rio atualizador.
	 */
	public Long getCodigoUsuarioAtualizador() {
		return codigoUsuarioAtualizador;
	}
	/**
	 * M�todo que define o c�digo do usu�rio atualizador.
	 * @param codigoUsuarioAtualizador - o c�digo do usu�rio atualizador.
	 */
	public void setCodigoUsuarioAtualizador(Long codigoUsuarioAtualizador) {
		this.codigoUsuarioAtualizador = codigoUsuarioAtualizador;
	}
	/**
	 * M�todo que retorna a data/hora da �ltima atualiza��o.
	 * @return Date - a data/hora da �ltima atualiza��o.
	 */
	public Date getDataHoraUltimaAtualizacao() {
		return (Date) dataHoraUltimaAtualizacao.clone();
	}
	/**
	 * M�todo que define a data/hora da �ltima atualiza��o.
	 * @param dataHoraUltimaAtualizacao - a data/hora da �ltima atualiza��o.
	 */
	public void setDataHoraUltimaAtualizacao(Date dataHoraUltimaAtualizacao) {
		this.dataHoraUltimaAtualizacao = (Date) dataHoraUltimaAtualizacao.clone();
	}
	/**
	 * M�todo que retorna o nome da pessoa.
	 * @return String - o nome da pessoa.
	 */
	public String getNomePessoa() {
		return nomePessoa;
	}
	/**
	 * M�todo que define o nome da pessoa.
	 * @param nomePessoa - o nome da pessoa.
	 */
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}
	/**
	 * M�todo que retorna o c�digo identificador de origem da parcela.
	 * @return String - o c�digo identificador de origem da parcela.
	 */
	public String getCodigoIdentificadorOrigem() {
		return codigoIdentificadorOrigem;
	}
	/**
	 * M�todo que define o c�digo identificador de origem da parcela.
	 * @param codigoIdentificadorOrigem - o c�digo identificador de origem da parcela.
	 */
	public void setCodigoIdentificadorOrigem(String codigoIdentificadorOrigem) {
		this.codigoIdentificadorOrigem = codigoIdentificadorOrigem;
	}
	/**
	 * M�todo que retorna o cpf ou cnpj.
	 * @return the cpfCnpj - o n�mero do documento.
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	/**
	 * M�todo que define o cpf ou cnpj.
	 * @param cpfCnpj the cpfCnpj to set
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
}
