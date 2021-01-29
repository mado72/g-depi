/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.cics.vo;

import static br.com.bradseg.depi.depositoidentificado.util.annotations.CicsField.QUATRO;

import java.io.Serializable;

import br.com.bradseg.depi.depositoidentificado.util.annotations.CicsField;
import br.com.bradseg.depi.depositoidentificado.util.annotations.CicsField.Direction;
import br.com.bradseg.depi.depositoidentificado.util.annotations.CicsProgram;

/**
 * 
 * @author Marcelo Damasceno
 * 
 */
@CicsProgram(programName = "GTAB0032", transactionName = "GT50", commLength = 602)
public class GTAB1412VO implements Serializable {

	private static final long serialVersionUID = -697466839831643647L;

	@CicsField(order = 01, size = 4, pattern = QUATRO)
	private String erroGSBS;

	@CicsField(order = 2, size = 1)
	private String tipoErroGSBS;

	@CicsField(order = 3, size = 8, pattern = "00000000")
	private String sqlCodeGSBS;

	@CicsField(order = 4, size = 3, pattern = "000")
	private String codigoRetorno;

	@CicsField(order = 5, size = 1)
	private String sinalSqlCode;

	@CicsField(order = 6, size = 4, pattern = QUATRO)
	private String sqlCode;

	@CicsField(order = 7, size = 4, pattern = QUATRO)
	private String sqlErrML;

	@CicsField(order = 8, size = 70)
	private String sqlErrMC;

	@CicsField(order = 9, size = 18)
	private String tabelaErro;

	@CicsField(order = 10, size = 1)
	private String codigoExcluido;

	@CicsField(order = 11, size = 1)
	private String existe;

	@CicsField(order = 12, size = 4, pattern = QUATRO)
	private String ciaExterno;

	@CicsField(order = 13, size = 4, pattern = QUATRO, direction=Direction.InOut)
	private String ciaInterno;

	@CicsField(order = 14, size = 18, pattern = "000000000000000000")
	private String codigoPessoa;

	@CicsField(order = 15, size = 50)
	private String nome;

	@CicsField(order = 16, size = 14, pattern = "00000000000000")
	private String cnpj;

	@CicsField(order = 17, size = 30)
	private String nomeLogradouro;

	@CicsField(order = 18, size = 20)
	private String complemento;

	@CicsField(order = 19, size = 6)
	private String numeroEndereco;

	@CicsField(order = 20, size = 20)
	private String bairro;

	@CicsField(order = 21, size = 20)
	private String cidade;

	@CicsField(order = 22, size = 8)
	private String cep;

	@CicsField(order = 23, size = 2)
	private String uf;

	@CicsField(order = 24, size = 2)
	private String dddFax;

	@CicsField(order = 25, size = 10)
	private String dddFone;

	@CicsField(order = 26, size = 2)
	private String fone;

	@CicsField(order = 27, size = 10)
	private String fax;

	@CicsField(order = 28, size = 50)
	private String email;

	@CicsField(order = 29, size = 7)
	private String caixaPostal;

	@CicsField(order = 30, size = 8)
	private String cepCaixaPostal;

	@CicsField(order = 31, size = 4)
	private String cdBancoCosseguroCia;

	@CicsField(order = 32, size = 5)
	private String cdAgenciaCosseguroCia;

	@CicsField(order = 33, size = 14)
	private String contaCosseguro;

	@CicsField(order = 34, size = 20)
	private String sigla;

	@CicsField(order = 35, size = 75)
	private String obs;

	@CicsField(order = 36, size = 6)
	private String lei;

	@CicsField(order = 37, size = 10)
	private String dataprivateacaoLei;

	@CicsField(order = 38, size = 1)
	private String seGrupoBradesco;

	@CicsField(order = 39, size = 5)
	private String cdProcFenaseg;

	@CicsField(order = 40, size = 10)
	private String dtProgGrupoBradesco;

	@CicsField(order = 41, size = 8)
	private String cdResponsavel;

	@CicsField(order = 42, size = 26)
	private String ultimaAtualizacaoCompleta;

	@CicsField(order = 43, size = 0)
	private String ultimaAtualizacao;

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
	 * Retorna ciaExterno
	 * @return o ciaExterno
	 */
	public String getCiaExterno() {
		return ciaExterno;
	}

	/**
	 * Define ciaExterno
	 * @param ciaExterno valor ciaExterno a ser definido
	 */
	public void setCiaExterno(String ciaExterno) {
		this.ciaExterno = ciaExterno;
	}

	/**
	 * Retorna ciaInterno
	 * @return o ciaInterno
	 */
	public String getCiaInterno() {
		return ciaInterno;
	}

	/**
	 * Define ciaInterno
	 * @param ciaInterno valor ciaInterno a ser definido
	 */
	public void setCiaInterno(String ciaInterno) {
		this.ciaInterno = ciaInterno;
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
	 * Retorna nome
	 * @return o nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Define nome
	 * @param nome valor nome a ser definido
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna cnpj
	 * @return o cnpj
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * Define cnpj
	 * @param cnpj valor cnpj a ser definido
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * Retorna nomeLogradouro
	 * @return o nomeLogradouro
	 */
	public String getNomeLogradouro() {
		return nomeLogradouro;
	}

	/**
	 * Define nomeLogradouro
	 * @param nomeLogradouro valor nomeLogradouro a ser definido
	 */
	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}

	/**
	 * Retorna complemento
	 * @return o complemento
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * Define complemento
	 * @param complemento valor complemento a ser definido
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * Retorna numeroEndereco
	 * @return o numeroEndereco
	 */
	public String getNumeroEndereco() {
		return numeroEndereco;
	}

	/**
	 * Define numeroEndereco
	 * @param numeroEndereco valor numeroEndereco a ser definido
	 */
	public void setNumeroEndereco(String numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}

	/**
	 * Retorna bairro
	 * @return o bairro
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * Define bairro
	 * @param bairro valor bairro a ser definido
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * Retorna cidade
	 * @return o cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * Define cidade
	 * @param cidade valor cidade a ser definido
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/**
	 * Retorna cep
	 * @return o cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * Define cep
	 * @param cep valor cep a ser definido
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	 * Retorna uf
	 * @return o uf
	 */
	public String getUf() {
		return uf;
	}

	/**
	 * Define uf
	 * @param uf valor uf a ser definido
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}

	/**
	 * Retorna dddFax
	 * @return o dddFax
	 */
	public String getDddFax() {
		return dddFax;
	}

	/**
	 * Define dddFax
	 * @param dddFax valor dddFax a ser definido
	 */
	public void setDddFax(String dddFax) {
		this.dddFax = dddFax;
	}

	/**
	 * Retorna dddFone
	 * @return o dddFone
	 */
	public String getDddFone() {
		return dddFone;
	}

	/**
	 * Define dddFone
	 * @param dddFone valor dddFone a ser definido
	 */
	public void setDddFone(String dddFone) {
		this.dddFone = dddFone;
	}

	/**
	 * Retorna fone
	 * @return o fone
	 */
	public String getFone() {
		return fone;
	}

	/**
	 * Define fone
	 * @param fone valor fone a ser definido
	 */
	public void setFone(String fone) {
		this.fone = fone;
	}

	/**
	 * Retorna fax
	 * @return o fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * Define fax
	 * @param fax valor fax a ser definido
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * Retorna email
	 * @return o email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Define email
	 * @param email valor email a ser definido
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retorna caixaPostal
	 * @return o caixaPostal
	 */
	public String getCaixaPostal() {
		return caixaPostal;
	}

	/**
	 * Define caixaPostal
	 * @param caixaPostal valor caixaPostal a ser definido
	 */
	public void setCaixaPostal(String caixaPostal) {
		this.caixaPostal = caixaPostal;
	}

	/**
	 * Retorna cepCaixaPostal
	 * @return o cepCaixaPostal
	 */
	public String getCepCaixaPostal() {
		return cepCaixaPostal;
	}

	/**
	 * Define cepCaixaPostal
	 * @param cepCaixaPostal valor cepCaixaPostal a ser definido
	 */
	public void setCepCaixaPostal(String cepCaixaPostal) {
		this.cepCaixaPostal = cepCaixaPostal;
	}

	/**
	 * Retorna cdBancoCosseguroCia
	 * @return o cdBancoCosseguroCia
	 */
	public String getCdBancoCosseguroCia() {
		return cdBancoCosseguroCia;
	}

	/**
	 * Define cdBancoCosseguroCia
	 * @param cdBancoCosseguroCia valor cdBancoCosseguroCia a ser definido
	 */
	public void setCdBancoCosseguroCia(String cdBancoCosseguroCia) {
		this.cdBancoCosseguroCia = cdBancoCosseguroCia;
	}

	/**
	 * Retorna cdAgenciaCosseguroCia
	 * @return o cdAgenciaCosseguroCia
	 */
	public String getCdAgenciaCosseguroCia() {
		return cdAgenciaCosseguroCia;
	}

	/**
	 * Define cdAgenciaCosseguroCia
	 * @param cdAgenciaCosseguroCia valor cdAgenciaCosseguroCia a ser definido
	 */
	public void setCdAgenciaCosseguroCia(String cdAgenciaCosseguroCia) {
		this.cdAgenciaCosseguroCia = cdAgenciaCosseguroCia;
	}

	/**
	 * Retorna contaCosseguro
	 * @return o contaCosseguro
	 */
	public String getContaCosseguro() {
		return contaCosseguro;
	}

	/**
	 * Define contaCosseguro
	 * @param contaCosseguro valor contaCosseguro a ser definido
	 */
	public void setContaCosseguro(String contaCosseguro) {
		this.contaCosseguro = contaCosseguro;
	}

	/**
	 * Retorna sigla
	 * @return o sigla
	 */
	public String getSigla() {
		return sigla;
	}

	/**
	 * Define sigla
	 * @param sigla valor sigla a ser definido
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	/**
	 * Retorna obs
	 * @return o obs
	 */
	public String getObs() {
		return obs;
	}

	/**
	 * Define obs
	 * @param obs valor obs a ser definido
	 */
	public void setObs(String obs) {
		this.obs = obs;
	}

	/**
	 * Retorna lei
	 * @return o lei
	 */
	public String getLei() {
		return lei;
	}

	/**
	 * Define lei
	 * @param lei valor lei a ser definido
	 */
	public void setLei(String lei) {
		this.lei = lei;
	}

	/**
	 * Retorna dataprivateacaoLei
	 * @return o dataprivateacaoLei
	 */
	public String getDataprivateacaoLei() {
		return dataprivateacaoLei;
	}

	/**
	 * Define dataprivateacaoLei
	 * @param dataprivateacaoLei valor dataprivateacaoLei a ser definido
	 */
	public void setDataprivateacaoLei(String dataprivateacaoLei) {
		this.dataprivateacaoLei = dataprivateacaoLei;
	}

	/**
	 * Retorna seGrupoBradesco
	 * @return o seGrupoBradesco
	 */
	public String getSeGrupoBradesco() {
		return seGrupoBradesco;
	}

	/**
	 * Define seGrupoBradesco
	 * @param seGrupoBradesco valor seGrupoBradesco a ser definido
	 */
	public void setSeGrupoBradesco(String seGrupoBradesco) {
		this.seGrupoBradesco = seGrupoBradesco;
	}

	/**
	 * Retorna cdProcFenaseg
	 * @return o cdProcFenaseg
	 */
	public String getCdProcFenaseg() {
		return cdProcFenaseg;
	}

	/**
	 * Define cdProcFenaseg
	 * @param cdProcFenaseg valor cdProcFenaseg a ser definido
	 */
	public void setCdProcFenaseg(String cdProcFenaseg) {
		this.cdProcFenaseg = cdProcFenaseg;
	}

	/**
	 * Retorna dtProgGrupoBradesco
	 * @return o dtProgGrupoBradesco
	 */
	public String getDtProgGrupoBradesco() {
		return dtProgGrupoBradesco;
	}

	/**
	 * Define dtProgGrupoBradesco
	 * @param dtProgGrupoBradesco valor dtProgGrupoBradesco a ser definido
	 */
	public void setDtProgGrupoBradesco(String dtProgGrupoBradesco) {
		this.dtProgGrupoBradesco = dtProgGrupoBradesco;
	}

	/**
	 * Retorna cdResponsavel
	 * @return o cdResponsavel
	 */
	public String getCdResponsavel() {
		return cdResponsavel;
	}

	/**
	 * Define cdResponsavel
	 * @param cdResponsavel valor cdResponsavel a ser definido
	 */
	public void setCdResponsavel(String cdResponsavel) {
		this.cdResponsavel = cdResponsavel;
	}

	/**
	 * Retorna ultimaAtualizacaoCompleta
	 * @return o ultimaAtualizacaoCompleta
	 */
	public String getUltimaAtualizacaoCompleta() {
		return ultimaAtualizacaoCompleta;
	}

	/**
	 * Define ultimaAtualizacaoCompleta
	 * @param ultimaAtualizacaoCompleta valor ultimaAtualizacaoCompleta a ser definido
	 */
	public void setUltimaAtualizacaoCompleta(String ultimaAtualizacaoCompleta) {
		this.ultimaAtualizacaoCompleta = ultimaAtualizacaoCompleta;
	}

	/**
	 * Retorna ultimaAtualizacao
	 * @return o ultimaAtualizacao
	 */
	public String getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	/**
	 * Define ultimaAtualizacao
	 * @param ultimaAtualizacao valor ultimaAtualizacao a ser definido
	 */
	public void setUltimaAtualizacao(String ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

}
