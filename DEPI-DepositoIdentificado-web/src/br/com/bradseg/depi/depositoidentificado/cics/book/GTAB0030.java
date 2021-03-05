/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.cics.book;

import static br.com.bradseg.depi.depositoidentificado.cics.annotations.CicsField.QUATRO;
import static br.com.bradseg.depi.depositoidentificado.cics.annotations.CicsField.Direction.InOut;

import java.io.Serializable;

import br.com.bradseg.depi.depositoidentificado.cics.annotations.CicsField;
import br.com.bradseg.depi.depositoidentificado.cics.annotations.CicsProgram;

/**
 * Classe que representa o book para o GTAB1412. 
 * @author Marcelo Damasceno
 */
@CicsProgram(programName = "GTAB0030", transactionName = "GT30", commLength = 272)
public class GTAB0030 implements Serializable {

	private static final long serialVersionUID = -697466839831643647L;

	@CicsField(order = 1, size = 4, pattern = QUATRO, direction = InOut)
	private String erroGSBS;

	@CicsField(order = 2, size = 1, direction = InOut)
	private String tipoErroGSBS;

	@CicsField(order = 3, size = 8, pattern = "00000000", direction = InOut)
	private String sqlCodeGSBS;

	@CicsField(order = 4, size = 7, pattern = "0000000", direction = InOut)
	private String filler;

	@CicsField(order = 5, size = 3, pattern = "000", direction = InOut)
	private String codigoRetorno;

	@CicsField(order = 6, size = 1, direction = InOut)
	private String sinalSqlCode;

	@CicsField(order = 7, size = 4, pattern = QUATRO, direction = InOut)
	private String sqlCode;

	@CicsField(order = 8, size = 4, pattern = QUATRO, direction = InOut)
	private String sqlErrML;

	@CicsField(order = 9, size = 70, direction = InOut)
	private String sqlErrMC;

	@CicsField(order = 10, size = 18, direction = InOut)
	private String tabelaErro;

	@CicsField(order = 11, size = 1, direction = InOut)
	private String codigoExcluido;

	@CicsField(order = 12, size = 1, direction = InOut)
	private String existe;

	@CicsField(order = 13, size = 4, pattern = QUATRO, direction = InOut)
	private String cdBancoExterno;

	@CicsField(order = 14, size = 4, pattern = QUATRO, direction = InOut)
	private String cdBancoInterno;

	@CicsField(order = 15, size = 18, pattern = "000000000000000000")
	private String codigoPessoa;

	@CicsField(order = 16, size = 50)
	private String nomeBanco;

	@CicsField(order = 17, size = 14, pattern = "00000000000000")
	private String cnpjBanco;

	@CicsField(order = 18, size = 1)
	private String dvBanco;

	@CicsField(order = 19, size = 15)
	private String siglaBanco;

	@CicsField(order = 20, size = 44)
	private String fillerFinal;

	/**
	 * Retorna erroGSBS
	 * @return o erroGSBS
	 */
	public String getErroGSBS() {
		return erroGSBS;
	}

	/**
	 * Define erroGSBS
	 * @param erroGSBS valor erroGSBS a ser definido
	 */
	public void setErroGSBS(String erroGSBS) {
		this.erroGSBS = erroGSBS;
	}

	/**
	 * Retorna tipoErroGSBS
	 * @return o tipoErroGSBS
	 */
	public String getTipoErroGSBS() {
		return tipoErroGSBS;
	}

	/**
	 * Define tipoErroGSBS
	 * @param tipoErroGSBS valor tipoErroGSBS a ser definido
	 */
	public void setTipoErroGSBS(String tipoErroGSBS) {
		this.tipoErroGSBS = tipoErroGSBS;
	}

	/**
	 * Retorna sqlCodeGSBS
	 * @return o sqlCodeGSBS
	 */
	public String getSqlCodeGSBS() {
		return sqlCodeGSBS;
	}

	/**
	 * Define sqlCodeGSBS
	 * @param sqlCodeGSBS valor sqlCodeGSBS a ser definido
	 */
	public void setSqlCodeGSBS(String sqlCodeGSBS) {
		this.sqlCodeGSBS = sqlCodeGSBS;
	}

	/**
	 * Retorna filler
	 * @return o filler
	 */
	public String getFiller() {
		return filler;
	}

	/**
	 * Define filler
	 * @param filler valor filler a ser definido
	 */
	public void setFiller(String filler) {
		this.filler = filler;
	}

	/**
	 * Retorna codigoRetorno
	 * @return o codigoRetorno
	 */
	public String getCodigoRetorno() {
		return codigoRetorno;
	}

	/**
	 * Define codigoRetorno
	 * @param codigoRetorno valor codigoRetorno a ser definido
	 */
	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	/**
	 * Retorna sinalSqlCode
	 * @return o sinalSqlCode
	 */
	public String getSinalSqlCode() {
		return sinalSqlCode;
	}

	/**
	 * Define sinalSqlCode
	 * @param sinalSqlCode valor sinalSqlCode a ser definido
	 */
	public void setSinalSqlCode(String sinalSqlCode) {
		this.sinalSqlCode = sinalSqlCode;
	}

	/**
	 * Retorna sqlCode
	 * @return o sqlCode
	 */
	public String getSqlCode() {
		return sqlCode;
	}

	/**
	 * Define sqlCode
	 * @param sqlCode valor sqlCode a ser definido
	 */
	public void setSqlCode(String sqlCode) {
		this.sqlCode = sqlCode;
	}

	/**
	 * Retorna sqlErrML
	 * @return o sqlErrML
	 */
	public String getSqlErrML() {
		return sqlErrML;
	}

	/**
	 * Define sqlErrML
	 * @param sqlErrML valor sqlErrML a ser definido
	 */
	public void setSqlErrML(String sqlErrML) {
		this.sqlErrML = sqlErrML;
	}

	/**
	 * Retorna sqlErrMC
	 * @return o sqlErrMC
	 */
	public String getSqlErrMC() {
		return sqlErrMC;
	}

	/**
	 * Define sqlErrMC
	 * @param sqlErrMC valor sqlErrMC a ser definido
	 */
	public void setSqlErrMC(String sqlErrMC) {
		this.sqlErrMC = sqlErrMC;
	}

	/**
	 * Retorna tabelaErro
	 * @return o tabelaErro
	 */
	public String getTabelaErro() {
		return tabelaErro;
	}

	/**
	 * Define tabelaErro
	 * @param tabelaErro valor tabelaErro a ser definido
	 */
	public void setTabelaErro(String tabelaErro) {
		this.tabelaErro = tabelaErro;
	}

	/**
	 * Retorna codigoExcluido
	 * @return o codigoExcluido
	 */
	public String getCodigoExcluido() {
		return codigoExcluido;
	}

	/**
	 * Define codigoExcluido
	 * @param codigoExcluido valor codigoExcluido a ser definido
	 */
	public void setCodigoExcluido(String codigoExcluido) {
		this.codigoExcluido = codigoExcluido;
	}

	/**
	 * Retorna existe
	 * @return o existe
	 */
	public String getExiste() {
		return existe;
	}

	/**
	 * Define existe
	 * @param existe valor existe a ser definido
	 */
	public void setExiste(String existe) {
		this.existe = existe;
	}

	/**
	 * Retorna cdBancoExterno
	 * @return o cdBancoExterno
	 */
	public String getCdBancoExterno() {
		return cdBancoExterno;
	}

	/**
	 * Define cdBancoExterno
	 * @param cdBancoExterno valor cdBancoExterno a ser definido
	 */
	public void setCdBancoExterno(String cdBancoExterno) {
		this.cdBancoExterno = cdBancoExterno;
	}

	/**
	 * Retorna cdBancoInterno
	 * @return o cdBancoInterno
	 */
	public String getCdBancoInterno() {
		return cdBancoInterno;
	}

	/**
	 * Define cdBancoInterno
	 * @param cdBancoInterno valor cdBancoInterno a ser definido
	 */
	public void setCdBancoInterno(String cdBancoInterno) {
		this.cdBancoInterno = cdBancoInterno;
	}

	/**
	 * Retorna codigoPessoa
	 * @return o codigoPessoa
	 */
	public String getCodigoPessoa() {
		return codigoPessoa;
	}

	/**
	 * Define codigoPessoa
	 * @param codigoPessoa valor codigoPessoa a ser definido
	 */
	public void setCodigoPessoa(String codigoPessoa) {
		this.codigoPessoa = codigoPessoa;
	}

	/**
	 * Retorna nomeBanco
	 * @return o nomeBanco
	 */
	public String getNomeBanco() {
		return nomeBanco;
	}

	/**
	 * Define nomeBanco
	 * @param nomeBanco valor nomeBanco a ser definido
	 */
	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	/**
	 * Retorna cnpjBanco
	 * @return o cnpjBanco
	 */
	public String getCnpjBanco() {
		return cnpjBanco;
	}

	/**
	 * Define cnpjBanco
	 * @param cnpjBanco valor cnpjBanco a ser definido
	 */
	public void setCnpjBanco(String cnpjBanco) {
		this.cnpjBanco = cnpjBanco;
	}

	/**
	 * Retorna dvBanco
	 * @return o dvBanco
	 */
	public String getDvBanco() {
		return dvBanco;
	}

	/**
	 * Define dvBanco
	 * @param dvBanco valor dvBanco a ser definido
	 */
	public void setDvBanco(String dvBanco) {
		this.dvBanco = dvBanco;
	}

	/**
	 * Retorna siglaBanco
	 * @return o siglaBanco
	 */
	public String getSiglaBanco() {
		return siglaBanco;
	}

	/**
	 * Define siglaBanco
	 * @param siglaBanco valor siglaBanco a ser definido
	 */
	public void setSiglaBanco(String siglaBanco) {
		this.siglaBanco = siglaBanco;
	}

	/**
	 * Retorna fillerFinal
	 * @return o fillerFinal
	 */
	public String getFillerFinal() {
		return fillerFinal;
	}

	/**
	 * Define fillerFinal
	 * @param fillerFinal valor fillerFinal a ser definido
	 */
	public void setFillerFinal(String fillerFinal) {
		this.fillerFinal = fillerFinal;
	}

}
