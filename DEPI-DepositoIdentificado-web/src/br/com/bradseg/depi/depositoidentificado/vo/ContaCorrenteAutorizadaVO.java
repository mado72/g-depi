/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;

/**
 * Classe ContaCorrenteAutorizadaVO
 * @author fabio.pimentel
 */
//@Table(schema = ConstantesDEPI.SCHEMA_BANCO, table = ConstantesDEPI.TABELA_CONTA_CORRENTE_AUTORIZADA)
public final class ContaCorrenteAutorizadaVO implements Serializable {

	private static final long serialVersionUID = -488890761499702708L;
	
	private int codigoResponsavelUltimaAtualizacao;

	/**
     * Construtor
     */
	public ContaCorrenteAutorizadaVO() {
		super();
	}

	/**
	 * Construtor que preenche a chave primária
	 * @param banco Banco
	 * @param codigoAgencia agencia
	 * @param contaCorrente Conta corrente
	 */
	public ContaCorrenteAutorizadaVO(BancoVO banco, int codigoAgencia,
			long contaCorrente) {
		super();
		this.banco = banco;
		this.codigoAgencia = codigoAgencia;
		this.contaCorrente = contaCorrente;
	}

	//	@TableField(name = ConstantesDEPI.TABELA_CONTA_CORRENTE_AUTORIZADA_BANCO, converter = BancoPersistenceConverter.class)
	private BancoVO banco = new BancoVO();

//	@TableField(name = ConstantesDEPI.TABELA_CONTA_CORRENTE_AUTORIZADA_AGENCIA)
	private int codigoAgencia;

//	@TableField(name = "CDIG_AG")
	private String digitoAgencia;

//	@TableField(name = ConstantesDEPI.TABELA_CONTA_CORRENTE_AUTORIZADA_CONTA_CORRENTE)
	private long contaCorrente;	
    
//    @TableField(name = "CPRIM_DIG_CTA")
    private String primeiroDigitoConta;

//	@TableField(name = "CSEGDA_DIG_CTA")
	private String segundoDigitoConta;

//	@TableField(name = "ROBS_CTA_AUTRZ")
	private String observacao;

//	@TableField(name = ConstantesDEPI.TABELA_CONTA_CORRENTE_AUTORIZADA_CONTA_INTERNA)
	private int codigoInternoCC;
	
//	@TableField(name = ConstantesDEPI.TABELA_COMPANHIA_DEPARTAMENTO_ID, converter = CompanhiaSeguradoraPersistenceConverter.class)
	private CompanhiaSeguradoraVO cia = new CompanhiaSeguradoraVO();
    
//  @TableField(name = "NCLI_TRANS_PERSO")
    private long trps;
    
    private DepositoVO deposito;

	/**
     * Retorna o valor do atributo deposito.
     * @return o valor do atributo deposito
     */
    public DepositoVO getDeposito() {
        return deposito;
    }

    /**
     * Especifica o valor do atributo deposito.
     * @param deposito - DepositoVO do deposito a ser configurado.
     */
    public void setDeposito(DepositoVO deposito) {
        this.deposito = deposito;
    }

    /**
     * Retorna o valor do atributo chaveComposta.
     * @return o valor do atributo chaveComposta
     */
	public String getChaveComposta() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getCia().getCodigoCompanhia()).append(".").append(this.getBanco().getCdBancoExterno()).append(".")
		    .append(this.getCodigoAgencia()).append(".").append(this.getContaCorrente());
		return sb.toString();
	}

	/**
     * Retorna o valor do atributo codigoAgencia.
     * @return o valor do atributo codigoAgencia
     */
	public int getCodigoAgencia() {
		return codigoAgencia;
	}

	/**
     * Especifica o valor do atributo codigoAgencia.
     * @param codigoAgencia - int do codigoAgencia a ser configurado.
     */
	public void setCodigoAgencia(int codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}

	/**
     * Retorna o valor do atributo banco.
     * @return o valor do atributo banco
     */
	public BancoVO getBanco() {
		return banco;
	}
    

	/**
     * Especifica o valor do atributo banco.
     * @param banco - BancoVO do banco a ser configurado.
     */
	public void setBanco(BancoVO banco) {
		this.banco = banco;
	}

	/**
     * Retorna o valor do atributo cia.
     * @return o valor do atributo cia
     */
	public CompanhiaSeguradoraVO getCia() {
		return cia;
	}

	/**
     * Especifica o valor do atributo cia.
     * @param cia - CompanhiaSeguradoraVO do cia a ser configurado.
     */
	public void setCia(CompanhiaSeguradoraVO cia) {
		this.cia = cia;
	}

	/**
     * Retorna o valor do atributo codigoInternoCC.
     * @return o valor do atributo codigoInternoCC
     */
	public int getCodigoInternoCC() {
		return codigoInternoCC;
	}

	/**
     * Especifica o valor do atributo codigoInternoCC.
     * @param codigoInternoCC - int do codigoInternoCC a ser configurado.
     */
	public void setCodigoInternoCC(int codigoInternoCC) {
		this.codigoInternoCC = codigoInternoCC;
	}

	/**
     * Retorna o valor do atributo contaCorrente.
     * @return o valor do atributo contaCorrente
     */
	public long getContaCorrente() {
		return contaCorrente;
	}

	/**
     * Especifica o valor do atributo contaCorrente.
     * @param contaCorrente - int do contaCorrente a ser configurado.
     */
	public void setContaCorrente(long contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	/**
     * Retorna o valor do atributo observacao.
     * @return o valor do atributo observacao
     */
	public String getObservacao() {
		return observacao;
	}

	/**
     * Especifica o valor do atributo observacao.
     * @param observacao - String do observacao a ser configurado.
     */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	/**
     * Retorna o valor do atributo digitoAgencia.
     * @return o valor do atributo digitoAgencia
     */
	public String getDigitoAgencia() {
		return digitoAgencia;
	}

	/**
     * Especifica o valor do atributo digitoAgencia.
     * @param digitoAgencia - String do digitoAgencia a ser configurado.
     */
	public void setDigitoAgencia(String digitoAgencia) {
		this.digitoAgencia = digitoAgencia;
	}

	/**
     * Retorna o valor do atributo primeiroDigitoConta.
     * @return o valor do atributo primeiroDigitoConta
     */
	public String getPrimeiroDigitoConta() {
		return primeiroDigitoConta;
	}

	/**
     * Especifica o valor do atributo primeiroDigitoConta.
     * @param primeiroDigitoConta - String do primeiroDigitoConta a ser configurado.
     */
	public void setPrimeiroDigitoConta(String primeiroDigitoConta) {
		this.primeiroDigitoConta = primeiroDigitoConta;
	}

	/**
     * Retorna o valor do atributo segundoDigitoConta.
     * @return o valor do atributo segundoDigitoConta
     */
	public String getSegundoDigitoConta() {
		return segundoDigitoConta;
	}

	/**
     * Especifica o valor do atributo segundoDigitoConta.
     * @param segundoDigitoConta - String do segundoDigitoConta a ser configurado.
     */
	public void setSegundoDigitoConta(String segundoDigitoConta) {
		this.segundoDigitoConta = segundoDigitoConta;
	}

    /**
     * Retorna o valor do atributo trps.
     * @return o valor do atributo trps
     */
    public long getTrps() {
        return trps;
    }

    /**
     * Especifica o valor do atributo trps.
     * @param trps - long do trps a ser configurado.
     */
    public void setTrps(long trps) {
        this.trps = trps;
    }
    
    /**
	 * Retorna codigoResponsavelUltimaAtualizacao
	 * @return o codigoResponsavelUltimaAtualizacao
	 */
	public int getCodigoResponsavelUltimaAtualizacao() {
		return codigoResponsavelUltimaAtualizacao;
	}
	
	/**
	 * Define codigoResponsavelUltimaAtualizacao
	 * @param codigoResponsavelUltimaAtualizacao valor codigoResponsavelUltimaAtualizacao a ser definido
	 */
	public void setCodigoResponsavelUltimaAtualizacao(
			int codigoResponsavelUltimaAtualizacao) {
		this.codigoResponsavelUltimaAtualizacao = codigoResponsavelUltimaAtualizacao;
	}

}
