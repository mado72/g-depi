package br.com.bradseg.depi.depositoidentificado.model.book;

import java.util.Date;

import org.apache.log4j.Logger;

import br.com.bradseg.depi.dp06.exception.DEPIIntegrationException;
import br.com.cpmbraxis.framework.cicsannotations.annotation.CicsField;
import br.com.cpmbraxis.framework.cicsannotations.annotation.CicsTransaction;
import br.com.cpmbraxis.framework.cicsannotations.util.CicsConstants.UseType;
import br.com.cpmbraxis.framework.formatter.NumberFormat;
import br.com.cpmbraxis.framework.formatter.exception.FormatException;

/**
 * Classe respons�vel por mapear o book da entidade CIA para ser utilizada nas chamadas ao CICS.
 * @author F�bio Henrique - fabio.almeida@cpmbraxis.com.
 */
@CicsTransaction(program = "GTAB0032", transaction = "GT50", maxLength = 602)
public final class GTAB1412 {
	
    protected static final Logger LOGGER = Logger.getLogger(GTAB1412.class);

	private static final long serialVersionUID = 5028742900062243052L;

	public static final String QUATRO = "0000";

	@CicsField(order = 1, size = 4, pattern = QUATRO)
	private int erroGSBS;

	@CicsField(order = 2, size = 1)
	private int tipoErroGSBS;

	@CicsField(order = 3, size = 8, pattern = "00000000")
	private int sqlCodeGSBS;

	@CicsField(order = 4, size = 7, pattern = "0000000")
	private String filler;

	@CicsField(order = 5, size = 3, pattern = "000")
	private int codigoRetorno;

	@CicsField(order = 6, size = 1)
	private String sinalSqlCode;

	@CicsField(order = 7, size = 4, pattern = QUATRO)
	private int sqlCode;

	@CicsField(order = 8, size = 4, pattern = QUATRO)
	private int sqlErrML;

	@CicsField(order = 9, size = 70)
	private String sqlErrMC;

	@CicsField(order = 10, size = 18)
	private String tabelaErro;

	@CicsField(order = 11, size = 1)
	private String codigoExcluido;

	@CicsField(order = 12, size = 1)
	private String existe;

	@CicsField(order = 13, size = 4, pattern = QUATRO)
	private String ciaExterno;

	@CicsField(order = 14, size = 4, pattern = QUATRO)
	private String ciaInterno;

	@CicsField(order = 15, size = 18, pattern = "000000000000000000", use = UseType.Out)
	private float codigoPessoa;

	@CicsField(order = 16, size = 50, use = UseType.Out)
	private String nome;

	@CicsField(order = 18, size = 14, pattern = "00000000000000", use = UseType.Out)
	private long cnpj;

	@CicsField(order = 19, size = 1, use = UseType.Out)
	private int dv;

	@CicsField(order = 20, size = 30, use = UseType.Out)
	private String nomeLogradouro;

	@CicsField(order = 21, size = 20, use = UseType.Out)
	private String complemento;

	@CicsField(order = 22, size = 6, use = UseType.Out, pattern = "000000")
	private int numeroEndereco;

	@CicsField(order = 23, size = 20, use = UseType.Out)
	private String bairro;

	@CicsField(order = 24, size = 20, use = UseType.Out)
	private String cidade;

	@CicsField(order = 25, size = 8, use = UseType.Out, pattern = "00000000")
	private int cep;

	@CicsField(order = 26, size = 2, use = UseType.Out)
	private String uf;

	@CicsField(order = 27, size = 2, use = UseType.Out, pattern = "00")
	private int dddFone;

	@CicsField(order = 28, size = 10, use = UseType.Out, pattern = "0000000000")
	private int fone;

	@CicsField(order = 29, size = 2, use = UseType.Out, pattern = "000")
	private int dddFax;

	@CicsField(order = 30, size = 10, use = UseType.Out, pattern = "0000000000")
	private int fax;

	@CicsField(order = 31, size = 50, use = UseType.Out)
	private String email;

	@CicsField(order = 32, size = 7, use = UseType.Out, pattern = "0000000")
	private int caixaPostal;

	@CicsField(order = 33, size = 8, use = UseType.Out, pattern = "00000000")
	private int cepCaixaPostal;

	@CicsField(order = 34, size = 4, use = UseType.Out, pattern = QUATRO)
	private int cdBancoCosseguroCia;

	@CicsField(order = 35, size = 5, use = UseType.Out, pattern = "00000")
	private int cdAgenciaCosseguroCia;

	@CicsField(order = 36, size = 14, use = UseType.Out)
	private String contaCosseguro;

	@CicsField(order = 37, size = 20, use = UseType.Out)
	private String sigla;

	@CicsField(order = 38, size = 75, use = UseType.Out)
	private String obs;

	@CicsField(order = 39, size = 6, use = UseType.Out)
	private String lei;

	@CicsField(order = 40, size = 10, use = UseType.Out)
	private String dataPublicacaoLei;

	@CicsField(order = 41, size = 1, use = UseType.Out)
	private String seGrupoBradesco;

	@CicsField(order = 42, size = 5, use = UseType.Out)
	private String cdProcFenaseg;

	@CicsField(order = 43, size = 10, use = UseType.Out)
	private String dtProgGrupoBradesco;

	@CicsField(order = 44, size = 8, use = UseType.Out)
	private String cdResponsavel;

	@CicsField(order = 45, size = 26, pattern = "yyyy-MM-dd-kk.mm.ss.SSSSSS", use = UseType.Out)
	private Date ultimaAtualizacaoCompleta;

	@CicsField(order = 46, size = 10, pattern = "dd.MM.yyyy", use = UseType.Out)
	private Date ultimaAtualizacao;

	/**
     * Construtor.
     */
	public GTAB1412() {
		super();
	}

	/**
     * Static Factory Method.
     * @param codigo - int.
     * @return GTAB1412.
     * @throws DEPIIntegrationException - DEPIIntegrationException.
     */
	public static GTAB1412 getInstancePesquisaDefaultPorCodigo(int codigo)
	    throws DEPIIntegrationException {
		try {
			return getInstancePesquisaDefaultPorCodigo(new NumberFormat().format(codigo, QUATRO).toString());
		} catch (FormatException e) {
		    LOGGER.error(e);
			throw new DEPIIntegrationException(e);
		}
	}

	/**
     * Static Factory Method.
     * @param codigo - int.
     * @return GTAB1412.
     * @throws DEPIIntegrationException - DEPIIntegrationException.
     */
	public static GTAB1412 getInstancePesquisaDefaultPorCodigo(String codigo)
	    throws DEPIIntegrationException {
		GTAB1412 book = new GTAB1412();
        book.setCiaInterno(codigo);
     	//book.setCiaExterno(codigo);
		return book;
	}

	/**
     * Static Factory Method.
     * @param codigo - int.
     * @return GTAB1412.
     * @throws DEPIIntegrationException - DEPIIntegrationException.
     */
	public static GTAB1412 getInstancePesquisaPorCodigoInterno(int codigo)
	    throws DEPIIntegrationException {
		try {
			return getInstancePesquisaPorCodigoInterno(new NumberFormat().format(codigo, QUATRO).toString());
		} catch (FormatException e) {
		    LOGGER.error(e);
			throw new DEPIIntegrationException(e);
		}
	}

	/**
     * Static Factory Method.
     * @param codigo - int.
     * @return GTAB1412.
     * @throws DEPIIntegrationException - DEPIIntegrationException.
     */
	public static GTAB1412 getInstancePesquisaPorCodigoInterno(String codigo)
	    throws DEPIIntegrationException {
		GTAB1412 book = new GTAB1412();
		book.setCiaInterno(codigo);
		return book;
	}

	/**
     * Retorna da BOOK - GTAB1412 o serialVersionUID.
     * @return O atributo serialVersionUID
     */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
     * Retorna da BOOK - GTAB1412 o bairro.
     * @return O atributo bairro
     */
	public String getBairro() {
		return this.bairro;
	}

	/**
     * Especifica o bairro.
     * @param bairro String do bairro a ser setado
     */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
     * Retorna da BOOK - GTAB1412 o caixaPostal.
     * @return O atributo caixaPostal
     */
	public int getCaixaPostal() {
		return this.caixaPostal;
	}

	/**
     * Especifica o caixaPostal.
     * @param caixaPostal int do caixaPostal a ser setado
     */
	public void setCaixaPostal(int caixaPostal) {
		this.caixaPostal = caixaPostal;
	}

	/**
     * Retorna da BOOK - GTAB1412 o cdAgenciaCosseguroCia.
     * @return O atributo cdAgenciaCosseguroCia
     */
	public int getCdAgenciaCosseguroCia() {
		return this.cdAgenciaCosseguroCia;
	}

	/**
     * Especifica o cdAgenciaCosseguroCia.
     * @param cdAgenciaCosseguroCia int do cdAgenciaCosseguroCia a ser setado
     */
	public void setCdAgenciaCosseguroCia(int cdAgenciaCosseguroCia) {
		this.cdAgenciaCosseguroCia = cdAgenciaCosseguroCia;
	}

	/**
     * Retorna da BOOK - GTAB1412 o cdBancoCosseguroCia.
     * @return O atributo cdBancoCosseguroCia
     */
	public int getCdBancoCosseguroCia() {
		return this.cdBancoCosseguroCia;
	}

	/**
     * Especifica o cdBancoCosseguroCia.
     * @param cdBancoCosseguroCia int do cdBancoCosseguroCia a ser setado
     */
	public void setCdBancoCosseguroCia(int cdBancoCosseguroCia) {
		this.cdBancoCosseguroCia = cdBancoCosseguroCia;
	}

	/**
     * Retorna da BOOK - GTAB1412 o cdProcFenaseg.
     * @return O atributo cdProcFenaseg
     */
	public String getCdProcFenaseg() {
		return this.cdProcFenaseg;
	}

	/**
     * Especifica o cdProcFenaseg.
     * @param cdProcFenaseg String do cdProcFenaseg a ser setado
     */
	public void setCdProcFenaseg(String cdProcFenaseg) {
		this.cdProcFenaseg = cdProcFenaseg;
	}

	/**
     * Retorna da BOOK - GTAB1412 o cdResponsavel.
     * @return O atributo cdResponsavel
     */
	public String getCdResponsavel() {
		return this.cdResponsavel;
	}

	/**
     * Especifica o cdResponsavel.
     * @param cdResponsavel String do cdResponsavel a ser setado
     */
	public void setCdResponsavel(String cdResponsavel) {
		this.cdResponsavel = cdResponsavel;
	}

	/**
     * Retorna da BOOK - GTAB1412 o cep.
     * @return O atributo cep
     */
	public int getCep() {
		return this.cep;
	}

	/**
     * Especifica o cep.
     * @param cep int do cep a ser setado
     */
	public void setCep(int cep) {
		this.cep = cep;
	}

	/**
     * Retorna da BOOK - GTAB1412 o cepCaixaPostal.
     * @return O atributo cepCaixaPostal
     */
	public int getCepCaixaPostal() {
		return this.cepCaixaPostal;
	}

	/**
     * Especifica o cepCaixaPostal.
     * @param cepCaixaPostal int do cepCaixaPostal a ser setado
     */
	public void setCepCaixaPostal(int cepCaixaPostal) {
		this.cepCaixaPostal = cepCaixaPostal;
	}

	/**
     * Retorna da BOOK - GTAB1412 o ciaExterno.
     * @return O atributo ciaExterno
     */
	public int getCiaExternoInt() {
		return Integer.valueOf(this.ciaExterno);
	}

	/**
     * Retorna da BOOK - GTAB1412 o ciaInterno.
     * @return O atributo ciaInterno
     */
	public int getCiaInternoInt() {
		return Integer.valueOf(this.ciaInterno);
	}

	/**
     * Retorna da BOOK - GTAB1412 o cidade.
     * @return O atributo cidade
     */
	public String getCidade() {
		return this.cidade;
	}

	/**
     * Especifica o cidade.
     * @param cidade String do cidade a ser setado
     */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/**
     * Retorna da BOOK - GTAB1412 o cnpj.
     * @return O atributo cnpj
     */
	public long getCnpj() {
		return this.cnpj;
	}

	/**
     * Especifica o cnpj.
     * @param cnpj long do cnpj a ser setado
     */
	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	}

	/**
     * Retorna da BOOK - GTAB1412 o codigoExcluido.
     * @return O atributo codigoExcluido
     */
	public String getCodigoExcluido() {
		return this.codigoExcluido;
	}

	/**
     * Especifica o codigoExcluido.
     * @param codigoExcluido String do codigoExcluido a ser setado
     */
	public void setCodigoExcluido(String codigoExcluido) {
		this.codigoExcluido = codigoExcluido;
	}

	/**
     * Retorna da BOOK - GTAB1412 o codigoPessoa.
     * @return O atributo codigoPessoa
     */
	public float getCodigoPessoa() {
		return this.codigoPessoa;
	}

	/**
     * Especifica o codigoPessoa.
     * @param codigoPessoa float do codigoPessoa a ser setado
     */
	public void setCodigoPessoa(float codigoPessoa) {
		this.codigoPessoa = codigoPessoa;
	}

	/**
     * Retorna da BOOK - GTAB1412 o codigoRetorno.
     * @return O atributo codigoRetorno
     */
	public int getCodigoRetorno() {
		return this.codigoRetorno;
	}

	/**
     * Especifica o codigoRetorno.
     * @param codigoRetorno int do codigoRetorno a ser setado
     */
	public void setCodigoRetorno(int codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	/**
     * Retorna da BOOK - GTAB1412 o complemento.
     * @return O atributo complemento
     */
	public String getComplemento() {
		return this.complemento;
	}

	/**
     * Especifica o complemento.
     * @param complemento String do complemento a ser setado
     */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
     * Retorna da BOOK - GTAB1412 o contaCosseguro.
     * @return O atributo contaCosseguro
     */
	public String getContaCosseguro() {
		return this.contaCosseguro;
	}

	/**
     * Especifica o contaCosseguro.
     * @param contaCosseguro String do contaCosseguro a ser setado
     */
	public void setContaCosseguro(String contaCosseguro) {
		this.contaCosseguro = contaCosseguro;
	}

	/**
     * Retorna da BOOK - GTAB1412 o dataPublicacaoLei.
     * @return O atributo dataPublicacaoLei
     */
	public String getDataPublicacaoLei() {
		return this.dataPublicacaoLei;
	}

	/**
     * Especifica o dataPublicacaoLei.
     * @param dataPublicacaoLei String do dataPublicacaoLei a ser setado
     */
	public void setDataPublicacaoLei(String dataPublicacaoLei) {
		this.dataPublicacaoLei = dataPublicacaoLei;
	}

	/**
     * Retorna da BOOK - GTAB1412 o dddFax.
     * @return O atributo dddFax
     */
	public int getDddFax() {
		return this.dddFax;
	}

	/**
     * Especifica o dddFax.
     * @param dddFax int do dddFax a ser setado
     */
	public void setDddFax(int dddFax) {
		this.dddFax = dddFax;
	}

	/**
     * Retorna da BOOK - GTAB1412 o dddFone.
     * @return O atributo dddFone
     */
	public int getDddFone() {
		return this.dddFone;
	}

	/**
     * Especifica o dddFone.
     * @param dddFone int do dddFone a ser setado
     */
	public void setDddFone(int dddFone) {
		this.dddFone = dddFone;
	}

	/**
     * Retorna da BOOK - GTAB1412 o dtProgGrupoBradesco.
     * @return O atributo dtProgGrupoBradesco
     */
	public String getDtProgGrupoBradesco() {
		return this.dtProgGrupoBradesco;
	}

	/**
     * Especifica o dtProgGrupoBradesco.
     * @param dtProgGrupoBradesco String do dtProgGrupoBradesco a ser setado
     */
	public void setDtProgGrupoBradesco(String dtProgGrupoBradesco) {
		this.dtProgGrupoBradesco = dtProgGrupoBradesco;
	}

	/**
     * Retorna da BOOK - GTAB1412 o dv.
     * @return O atributo dv
     */
	public int getDv() {
		return this.dv;
	}

	/**
     * Especifica o dv.
     * @param dv int do dv a ser setado
     */
	public void setDv(int dv) {
		this.dv = dv;
	}

	/**
     * Retorna da BOOK - GTAB1412 o email.
     * @return O atributo email
     */
	public String getEmail() {
		return this.email;
	}

	/**
     * Especifica o email.
     * @param email String do email a ser setado
     */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
     * Retorna da BOOK - GTAB1412 o erroGSBS.
     * @return O atributo erroGSBS
     */
	public int getErroGSBS() {
		return this.erroGSBS;
	}

	/**
     * Especifica o erroGSBS.
     * @param erroGSBS int do erroGSBS a ser setado
     */
	public void setErroGSBS(int erroGSBS) {
		this.erroGSBS = erroGSBS;
	}

	/**
     * Retorna da BOOK - GTAB1412 o existe.
     * @return O atributo existe
     */
	public String getExiste() {
		return this.existe;
	}

	/**
     * Especifica o existe.
     * @param existe String do existe a ser setado
     */
	public void setExiste(String existe) {
		this.existe = existe;
	}

	/**
     * Retorna da BOOK - GTAB1412 o fax.
     * @return O atributo fax
     */
	public int getFax() {
		return this.fax;
	}

	/**
     * Especifica o fax.
     * @param fax int do fax a ser setado
     */
	public void setFax(int fax) {
		this.fax = fax;
	}

	/**
     * Retorna da BOOK - GTAB1412 o filler.
     * @return O atributo filler
     */
	public String getFiller() {
		return this.filler;
	}

	/**
     * Especifica o filler.
     * @param filler String do filler a ser setado
     */
	public void setFiller(String filler) {
		this.filler = filler;
	}

	/**
     * Retorna da BOOK - GTAB1412 o fone.
     * @return O atributo fone
     */
	public int getFone() {
		return this.fone;
	}

	/**
     * Especifica o fone.
     * @param fone int do fone a ser setado
     */
	public void setFone(int fone) {
		this.fone = fone;
	}

	/**
     * Retorna da BOOK - GTAB1412 o lei.
     * @return O atributo lei
     */
	public String getLei() {
		return this.lei;
	}

	/**
     * Especifica o lei.
     * @param lei String do lei a ser setado
     */
	public void setLei(String lei) {
		this.lei = lei;
	}

	/**
     * Retorna da BOOK - GTAB1412 o nome.
     * @return O atributo nome
     */
	public String getNome() {
		return this.nome;
	}

	/**
     * Especifica o nome.
     * @param nome String do nome a ser setado
     */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
     * Retorna da BOOK - GTAB1412 o nomeLogradouro.
     * @return O atributo nomeLogradouro
     */
	public String getNomeLogradouro() {
		return this.nomeLogradouro;
	}

	/**
     * Especifica o nomeLogradouro.
     * @param nomeLogradouro String do nomeLogradouro a ser setado
     */
	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}

	/**
     * Retorna da BOOK - GTAB1412 o numeroEndereco.
     * @return O atributo numeroEndereco
     */
	public int getNumeroEndereco() {
		return this.numeroEndereco;
	}

	/**
     * Especifica o numeroEndereco.
     * @param numeroEndereco int do numeroEndereco a ser setado
     */
	public void setNumeroEndereco(int numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}

	/**
     * Retorna da BOOK - GTAB1412 o obs.
     * @return O atributo obs
     */
	public String getObs() {
		return this.obs;
	}

	/**
     * Especifica o obs.
     * @param obs String do obs a ser setado
     */
	public void setObs(String obs) {
		this.obs = obs;
	}

	/**
     * Retorna da BOOK - GTAB1412 o seGrupoBradesco.
     * @return O atributo seGrupoBradesco
     */
	public String getSeGrupoBradesco() {
		return this.seGrupoBradesco;
	}

	/**
     * Especifica o seGrupoBradesco.
     * @param seGrupoBradesco String do seGrupoBradesco a ser setado
     */
	public void setSeGrupoBradesco(String seGrupoBradesco) {
		this.seGrupoBradesco = seGrupoBradesco;
	}

	/**
     * Retorna da BOOK - GTAB1412 o sigla.
     * @return O atributo sigla
     */
	public String getSigla() {
		return this.sigla;
	}

	/**
     * Especifica o sigla.
     * @param sigla String do sigla a ser setado
     */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	/**
     * Retorna da BOOK - GTAB1412 o sinalSqlCode.
     * @return O atributo sinalSqlCode
     */
	public String getSinalSqlCode() {
		return this.sinalSqlCode;
	}

	/**
     * Especifica o sinalSqlCode.
     * @param sinalSqlCode1412 String do sinalSqlCode a ser setado
     */
	public void setSinalSqlCode(String sinalSqlCode1412) {
		this.sinalSqlCode = sinalSqlCode1412;
	}

	/**
     * Retorna da BOOK - GTAB1412 o sqlCode.
     * @return O atributo sqlCode
     */
	public int getSqlCode() {
		return this.sqlCode;
	}

	/**
     * Especifica o sqlCode.
     * @param sqlCode1412 int do sqlCode a ser setado
     */
	public void setSqlCode(int sqlCode1412) {
		this.sqlCode = sqlCode1412;
	}

	/**
     * Retorna da BOOK - GTAB1412 o sqlCodeGSBS.
     * @return O atributo sqlCodeGSBS
     */
	public int getSqlCodeGSBS() {
		return this.sqlCodeGSBS;
	}

	/**
     * Especifica o sqlCodeGSBS.
     * @param sqlCodeGSBS1412 int do sqlCodeGSBS a ser setado
     */
	public void setSqlCodeGSBS(int sqlCodeGSBS1412) {
		this.sqlCodeGSBS = sqlCodeGSBS1412;
	}

	/**
     * Retorna da BOOK - GTAB1412 o sqlErrMC.
     * @return O atributo sqlErrMC
     */
	public String getSqlErrMC() {
		return this.sqlErrMC;
	}

	/**
     * Especifica o sqlErrMC.
     * @param sqlErrMC1412 String do sqlErrMC a ser setado
     */
	public void setSqlErrMC(String sqlErrMC1412) {
		this.sqlErrMC = sqlErrMC1412;
	}

	/**
     * Retorna da BOOK - GTAB1412 o sqlErrML.
     * @return O atributo sqlErrML
     */
	public int getSqlErrML() {
		return this.sqlErrML;
	}

	/**
     * Especifica o sqlErrML.
     * @param sqlErrML1412 int do sqlErrML a ser setado
     */
	public void setSqlErrML(int sqlErrML1412) {
		this.sqlErrML = sqlErrML1412;
	}

	/**
     * Retorna da BOOK - GTAB1412 o tabelaErro.
     * @return O atributo tabelaErro
     */
	public String getTabelaErro() {
		return this.tabelaErro;
	}

	/**
     * Especifica o tabelaErro.
     * @param tabelaErro1412 String do tabelaErro a ser setado
     */
	public void setTabelaErro(String tabelaErro1412) {
		this.tabelaErro = tabelaErro1412;
	}

	/**
     * Retorna da BOOK - GTAB1412 o tipoErroGSBS.
     * @return O atributo tipoErroGSBS
     */
	public int getTipoErroGSBS() {
		return this.tipoErroGSBS;
	}

	/**
     * Especifica o tipoErroGSBS.
     * @param tipoErroGSBS1412 int do tipoErroGSBS a ser setado
     */
	public void setTipoErroGSBS(int tipoErroGSBS1412) {
		this.tipoErroGSBS = tipoErroGSBS1412;
	}

	/**
     * Retorna da BOOK - GTAB1412 o uf.
     * @return O atributo uf
     */
	public String getUf() {
		return this.uf;
	}

	/**
     * Especifica o uf.
     * @param uf String do uf a ser setado
     */
	public void setUf(String uf) {
		this.uf = uf;
	}

	/**
     * Retorna da BOOK - GTAB1412 o ultimaAtualizacao.
     * @return O atributo ultimaAtualizacao
     */
	public Date getUltimaAtualizacao() {
		return this.ultimaAtualizacao;
	}

	/**
     * Retorna da BOOK - GTAB1412 o ultimaAtualizacaoCompleta.
     * @return O atributo ultimaAtualizacaoCompleta
     */
	public Date getUltimaAtualizacaoCompleta() {
		return this.ultimaAtualizacaoCompleta;
	}

	/**
     * Especifica o ultimaAtualizacaoCompleta.
     * @param ultimaAtualizacaoCompleta Date do ultimaAtualizacaoCompleta a ser setado
     */
	public void setUltimaAtualizacaoCompleta(Date ultimaAtualizacaoCompleta) {
		this.ultimaAtualizacaoCompleta = ultimaAtualizacaoCompleta;
	}

	/**
     * Especifica o ultimaAtualizacao.
     * @param ultimaAtualizacao Date do ultimaAtualizacao a ser setado
     */
	public void setUltimaAtualizacao(Date ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

	/**
     * Especifica o ciaExterno.
     * @param ciaExterno int do ciaExterno a ser setado
     */
	public void setCiaExternoInt(int ciaExterno) {
		this.ciaExterno = String.valueOf(ciaExterno);
	}

	/**
     * Especifica o ciaInterno.
     * @param ciaInterno int do ciaInterno a ser setado
     */
	public void setCiaInternoInt(int ciaInterno) {
		this.ciaInterno = String.valueOf(ciaInterno);
	}

	/**
     * Especifica o ciaExterno.
     * @param ciaExterno String do ciaExterno a ser setado
     */
	public void setCiaExterno(String ciaExterno) {
		this.ciaExterno = ciaExterno;
	}

	/**
     * Especifica o ciaInterno.
     * @param ciaInterno String do ciaInterno a ser setado
     */
	public void setCiaInterno(String ciaInterno) {
		this.ciaInterno = ciaInterno;
	}

	/**
     * Retorna da BOOK - GTAB1412 o ciaExterno.
     * @return O atributo ciaExterno
     */
	public String getCiaExterno() {
		return this.ciaExterno;
	}

	/**
     * Retorna da BOOK - GTAB1412 o ciaInterno.
     * @return O atributo ciaInterno
     */
	public String getCiaInterno() {
		return this.ciaInterno;
	}
}
