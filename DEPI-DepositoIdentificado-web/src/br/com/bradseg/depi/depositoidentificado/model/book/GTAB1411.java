package br.com.bradseg.depi.depositoidentificado.model.book;

import br.com.cpmbraxis.framework.cicsannotations.annotation.CicsField;
import br.com.cpmbraxis.framework.cicsannotations.annotation.CicsTransaction;
import br.com.cpmbraxis.framework.cicsannotations.util.CicsConstants.UseType;

/**
 * Classe respons�vel por mapear o book da entidade Ag�ncia para ser utilizada nas chamadas ao CICS
 * @author F�bio Henrique.
 */
@CicsTransaction(program = "GTAB0031", transaction = "GT31", maxLength = 398)
public class GTAB1411 extends BaseBook {

	/**
     * Serial Version.
     */
	private static final long serialVersionUID = -8260218026427437862L;

	@CicsField(order = 0, size = 4, pattern = QUATRO)
	private int erroGSBS;

	@CicsField(order = 1, size = 1)
	private int tipoErroGSBS;

	@CicsField(order = 2, size = 8, pattern = "00000000")
	private int sqlCodeGSBS;

	@CicsField(order = 3, size = 7, pattern = "0000000")
	private String filler;

	/**
     * Codigo de Retorno do GTAB1411
     */
	@CicsField(order = 4, size = 3, pattern = "000")
	private int codigoRetorno;

	/**
     * sinalSqlCode do GTAB1411
     */
	@CicsField(order = 5, size = 1)
	private String sinalSqlCode;

	/**
     * sqlCode do GTAB1411
     */
	@CicsField(order = 6, size = 4, pattern = QUATRO)
	private int sqlCode;

	/**
     * sqlErrML do GTAB1411
     */
	@CicsField(order = 7, size = 4, pattern = QUATRO)
	private int sqlErrML;

	/**
     * sqlErrMC do GTAB1411
     */
	@CicsField(order = 8, size = 70)
	private String sqlErrMC;

	/**
     * tabelaErro do GTAB1411
     */
	@CicsField(order = 9, size = 18)
	private String tabelaErro;

	/**
     * Campos de controle do servi�o Indica se o elemento foi exclu�do ou n�o
     */
	@CicsField(order = 10, size = 1)
	private String cdExcluido;
	/**
     * Indica se o elemento foi encontrado ou n�o
     */
	@CicsField(order = 11, size = 1)
	private String cdExiste;

	/**
     * cdBancoExterno - Campos de Entrada do servi�o
     */
	@CicsField(order = 12, size = 4, pattern = "0000")
	private Integer cdBancoExterno;

	/**
     * cdAgenciaExternodo GTAB1411
     */
	@CicsField(order = 13, size = 5, pattern = "00000")
	private Integer cdAgenciaExterno;

	/**
     * cdBancoInterno do GTAB1411
     */
	@CicsField(order = 14, size = 4, pattern = "0000")
	private Integer cdBancoInterno;

	/**
     * cdAgenciaInterno do GTAB1411
     */
	@CicsField(order = 15, size = 9, pattern = "000000000")
	private Integer cdAgenciaInterno;

	/**
     * Campos de Sa�da retornados pelo servi�o
     */
	@CicsField(order = 16, size = 18, pattern = "000000000000000000", use = UseType.Out)
	private int cdPessoa;

	@CicsField(order = 17, size = 50, use = UseType.Out)
	private String nomeAgencia;


	/**
     * Construtor
     */
	public GTAB1411() {
		super();
	}

	/**
     * Construtor
     * @param codigoBanco - C�digo Banco.
     * @param cdAgenciaExternoGTAB1411 - Integer.
     * @return GTAB1411.
     */
	public static GTAB1411 getInstance(int codigoBanco, int cdAgenciaExternoGTAB1411) {
		GTAB1411 book = new GTAB1411();
		book.setCdBancoExterno(codigoBanco);
		book.setCdAgenciaExterno(cdAgenciaExternoGTAB1411);
		return book;
	}

	/**
     * retorna codigo excluido
     * @return codigo excluido
     */
	public String getCdExcluido() {
		return cdExcluido;
	}

	/**
     * configura codigo excluido
     * @param cdExcluidoGTAB1411 codigo excluido
     */
	public void setCdExcluido(String cdExcluidoGTAB1411) {
		this.cdExcluido = cdExcluidoGTAB1411;
	}

	/**
     * retorna codigo existe
     * @return codigo existe
     */
	public String getCdExiste() {
		return cdExiste;
	}

	/**
     * configura codigo existe
     * @param cdExisteGTAB1411 codigo existe
     */
	public void setCdExiste(String cdExisteGTAB1411) {
		this.cdExiste = cdExisteGTAB1411;
	}

	/**
     * retona codigo agencia externo
     * @return codigo agencia externo
     */
	public Integer getCdAgenciaExterno() {
		return cdAgenciaExterno;
	}

	/**
     * configura codigo agencia externo
     * @param cdAgenciaExternoGTAB1411 codigo agencia externo
     */
	public void setCdAgenciaExterno(Integer cdAgenciaExternoGTAB1411) {
		this.cdAgenciaExterno = cdAgenciaExternoGTAB1411;
	}

	/**
     * retorna codigo agencia interno
     * @return codigo agencia interno
     */
	public Integer getCdAgenciaInterno() {
		return cdAgenciaInterno;
	}

	/**
     * configura codigo agencia interno
     * @param cdAgenciaInternoGTAB1411 codigo agencia interno
     */
	public void setCdAgenciaInterno(Integer cdAgenciaInternoGTAB1411) {
		this.cdAgenciaInterno = cdAgenciaInternoGTAB1411;
	}

	/**
     * retorna codigo banco externo
     * @return codigo banco externo
     */
	public Integer getCdBancoExterno() {
		return cdBancoExterno;
	}

	/**
     * configura codigo banco externo
     * @param cdBancoExternoGTAB1411 codigo banco externo
     */
	public void setCdBancoExterno(Integer cdBancoExternoGTAB1411) {
		this.cdBancoExterno = cdBancoExternoGTAB1411;
	}

	/**
     * retorna codigo banco interno
     * @return codigo banco interno
     */
	public Integer getCdBancoInterno() {
		return cdBancoInterno;
	}

	/**
     * configura codigo banco interno
     * @param cdBancoInternoGTAB1411 codigo banco interno
     */
	public void setCdBancoInterno(Integer cdBancoInternoGTAB1411) {
		this.cdBancoInterno = cdBancoInternoGTAB1411;
	}

	/**
     * retorna nome agencia
     * @return nome agencia
     */
	public String getNomeAgencia() {
		return nomeAgencia;
	}

	/**
     * configura nome agencia
     * @param nomeAgenciaGTAB1411 nome agencia
     */
	public void setNomeAgencia(String nomeAgenciaGTAB1411) {
		this.nomeAgencia = nomeAgenciaGTAB1411;
	}

	/**
     * Retorna o valor do atributo serialVersionUID.
     * @return o valor do atributo serialVersionUID
     */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
     * Retorna o valor do atributo codigoRetorno.
     * @return o valor do atributo codigoRetorno
     */
	public int getCodigoRetorno() {
		return codigoRetorno;
	}

	/**
     * Especifica o valor do atributo codigoRetorno.
     * @param codigoRetornoGTAB1411 - int do codigoRetorno a ser configurado.
     */
	public void setCodigoRetorno(int codigoRetornoGTAB1411) {
		this.codigoRetorno = codigoRetornoGTAB1411;
	}

	/**
     * Retorna o valor do atributo erroGSBS.
     * @return o valor do atributo erroGSBS
     */
	public int getErroGSBS() {
		return erroGSBS;
	}

	/**
     * Especifica o valor do atributo erroGSBS.
     * @param erroGSBSGTAB1411 - int do erroGSBS a ser configurado.
     */
	public void setErroGSBS(int erroGSBSGTAB1411) {
		this.erroGSBS = erroGSBSGTAB1411;
	}

	/**
     * Retorna o valor do atributo filler.
     * @return o valor do atributo filler
     */
	public String getFiller() {
		return filler;
	}

	/**
     * Especifica o valor do atributo filler.
     * @param fillerGTAB1411 - String do filler a ser configurado.
     */
	public void setFiller(String fillerGTAB1411) {
		this.filler = fillerGTAB1411;
	}

	/**
     * Retorna o valor do atributo sinalSqlCode.
     * @return o valor do atributo sinalSqlCode
     */
	public String getSinalSqlCode() {
		return sinalSqlCode;
	}

	/**
     * Especifica o valor do atributo sinalSqlCode.
     * @param sinalSqlCodeGTAB1411 - String do sinalSqlCode a ser configurado.
     */
	public void setSinalSqlCode(String sinalSqlCodeGTAB1411) {
		this.sinalSqlCode = sinalSqlCodeGTAB1411;
	}

	/**
     * Retorna o valor do atributo sqlCode.
     * @return o valor do atributo sqlCode
     */
	public int getSqlCode() {
		return sqlCode;
	}

	/**
     * Especifica o valor do atributo sqlCode.
     * @param sqlCodeGTAB1411 - int do sqlCode a ser configurado.
     */
	public void setSqlCode(int sqlCodeGTAB1411) {
		this.sqlCode = sqlCodeGTAB1411;
	}

	/**
     * Retorna o valor do atributo sqlCodeGSBS.
     * @return o valor do atributo sqlCodeGSBS
     */
	public int getSqlCodeGSBS() {
		return sqlCodeGSBS;
	}

	/**
     * Especifica o valor do atributo sqlCodeGSBS.
     * @param sqlCodeGSBSGTAB1411 - int do sqlCodeGSBS a ser configurado.
     */
	public void setSqlCodeGSBS(int sqlCodeGSBSGTAB1411) {
		this.sqlCodeGSBS = sqlCodeGSBSGTAB1411;
	}

	/**
     * Retorna o valor do atributo sqlErrMC.
     * @return o valor do atributo sqlErrMC
     */
	public String getSqlErrMC() {
		return sqlErrMC;
	}

	/**
     * Especifica o valor do atributo sqlErrMC.
     * @param sqlErrMCGTAB1411 - String do sqlErrMC a ser configurado.
     */
	public void setSqlErrMC(String sqlErrMCGTAB1411) {
		this.sqlErrMC = sqlErrMCGTAB1411;
	}

	/**
     * Retorna o valor do atributo sqlErrML.
     * @return o valor do atributo sqlErrML
     */
	public int getSqlErrML() {
		return sqlErrML;
	}

	/**
     * Especifica o valor do atributo sqlErrML.
     * @param sqlErrMLGTAB1411 - int do sqlErrML a ser configurado.
     */
	public void setSqlErrML(int sqlErrMLGTAB1411) {
		this.sqlErrML = sqlErrMLGTAB1411;
	}

	/**
     * Retorna o valor do atributo tabelaErro.
     * @return o valor do atributo tabelaErro
     */
	public String getTabelaErro() {
		return tabelaErro;
	}

	/**
     * Especifica o valor do atributo tabelaErro.
     * @param tabelaErroGTAB1411 - String do tabelaErro a ser configurado.
     */
	public void setTabelaErro(String tabelaErroGTAB1411) {
		this.tabelaErro = tabelaErroGTAB1411;
	}

	/**
     * Retorna o valor do atributo tipoErroGSBS.
     * @return o valor do atributo tipoErroGSBS
     */
	public int getTipoErroGSBS() {
		return tipoErroGSBS;
	}

	/**
     * Especifica o valor do atributo tipoErroGSBS.
     * @param tipoErroGSBSGTAB1411 - int do tipoErroGSBS a ser configurado.
     */
	public void setTipoErroGSBS(int tipoErroGSBSGTAB1411) {
		this.tipoErroGSBS = tipoErroGSBSGTAB1411;
	}

	/**
     * Retorna o valor do atributo cdPessoa.
     * @return o valor do atributo cdPessoa
     */
	public int getCdPessoa() {
		return cdPessoa;
	}

	/**
     * Especifica o valor do atributo cdPessoa.
     * @param cdPessoaGTAB1411 - int do cdPessoa a ser configurado.
     */
	public void setCdPessoa(int cdPessoaGTAB1411) {
		this.cdPessoa = cdPessoaGTAB1411;
	}

}
