package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * OBTENCAO DE UM BANCO BOOK UTILIZADA PELA ROTINA DE SERVICO GTAB0010 E GTAB0030. Classe utilizada para mapear os dados de um
 * BancoVO durante a chamada ao CICS. A SEGUIR : WS-CD-BANCO-EXTERNO OU WS-CD-BANCO-INTERNO SE AMBOS OS CAMPOS FOREM PREENCHIDOS O
 * SUBPROGRAM DE OBTENCAO DE BANCO RETORNARA UM CODIGO DE ERRO.
* @author Globality
*/
// @CicsTransaction(program = "GTAB0030", transaction = "GT30", maxLength = 272)

@XmlRootElement(name="BancoVO")
public class BancoVO implements Serializable {

    /**
     * Serial Version.
     */
    private static final long serialVersionUID = -2105399068665061223L;

//    @CicsField(order = 0, size = 4, pattern = QUATRO)
    private int erroGSBS;

//    @CicsField(order = 1, size = 1)
    private int tipoErroGSBS;

//    @CicsField(order = 2, size = 8, pattern = "00000000")
    private int sqlCodeGSBS;

//    @CicsField(order = 3, size = 7)
    private String filler;

//    @CicsField(order = 4, size = 3, pattern = ConstantesModel.PATTERN_TRES)
    private int codigoRetorno;

//    @CicsField(order = 5, size = 1)
    private String sinalSqlCode;

//    @CicsField(order = 6, size = 4, pattern = QUATRO)
    private int sqlCode;

//    @CicsField(order = 7, size = 4, pattern = QUATRO)
    private int sqlErrML;

//    @CicsField(order = 8, size = 70)
    private String sqlErrMC;

//    @CicsField(order = 9, size = 18)
    private String tabelaErro;

    /**
     * Indica se o elemento foi excluído ou não
     */
//    @CicsField(order = 10, size = 1)
    private String cdExcluido;
    /**
     * Indica se o elemento foi encontrado ou não
     */
//    @CicsField(order = 11, size = 1)
    private String cdExiste;

    /**
     * Campos da entidade
     */
//    @ConsiderLog(action = ConstantesModel.ATUALIZAR_DEPOSITO)
//    @CicsField(order = 12, size = 4, pattern = QUATRO)
    private Integer cdBancoExterno;

//    @CicsField(order = 13, size = 4, pattern = QUATRO)
    private Integer cdBancoInterno;

//    @CicsField(order = 14, size = 18, pattern = "000000000000000000", use = UseType.Out)
    private float cdPessoa;

//    @CicsField(order = 15, size = 50, use = UseType.Out)
    private String nomeBanco;

//    @CicsField(order = 16, size = 14, pattern = "00000000000000", use = UseType.Out)
    private String cnpjBanco;

//    @CicsField(order = 17, size = 1, use = UseType.Out)
    private String dvBanco;

//    @CicsField(order = 18, size = 15, use = UseType.Out)
    private String siglaBanco;

//    @CicsField(order = 19, size = 44, use = UseType.Out)
    private String fillerFinal = "";

    /**
     * Campos que faltam, mas o sistema não precisa deles.
     */
    // 05 WS-DATA-CADASTRO-BANCO PIC X(10).
    // 05 WS-CD-RESP-BANCO PIC X(8).
    // DATA HORA ULTRIMA ATUALIZACAO (26)
    /**
     * getCdExcluido
     * @return String
     */

    public String getCdExcluido() {
        return cdExcluido;
    }

    /**
     * setCdExcluido
     * @param cdExcluido cdExcluido
     */
    public void setCdExcluido(String cdExcluido) {
        this.cdExcluido = cdExcluido;
    }

    /**
     * getCdExiste
     * @return String
     */
    public String getCdExiste() {
        return cdExiste;
    }

    /**
     * setCdExiste
     * @param cdExiste String
     */
    public void setCdExiste(String cdExiste) {
        this.cdExiste = cdExiste;
    }

    /**
     * Retorna o valor do atributo cdPessoa.
     * @return o valor do atributo cdPessoa
     */
    public float getCdPessoa() {
        return cdPessoa;
    }

    /**
     * Especifica o valor do atributo cdPessoa.
     * @param cdPessoa - float do cdPessoa a ser configurado.
     */
    public void setCdPessoa(float cdPessoa) {
        this.cdPessoa = cdPessoa;
    }

    /**
     * getCnpjBanco
     * @return String
     */
    public String getCnpjBanco() {
        return cnpjBanco;
    }

    /**
     * setCnpjBanco cnpjBanco
     * @param cnpjBanco cnpjBanco
     */
    public void setCnpjBanco(String cnpjBanco) {
        this.cnpjBanco = cnpjBanco;
    }

    /**
     * getDvBanco
     * @return String
     */
    public String getDvBanco() {
        return dvBanco;
    }

    /**
     * setDvBanco
     * @param dvBanco String
     */
    public void setDvBanco(String dvBanco) {
        this.dvBanco = dvBanco;
    }

    /**
     * getNomeBanco
     * @return nomeBanco
     */
    public String getNomeBanco() {
        return nomeBanco;
    }

    /**
     * setNomeBanco
     * @param nomeBanco String
     */
    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    /**
     * getSiglaBanco
     * @return siglaBanco
     */
    public String getSiglaBanco() {
        return siglaBanco;
    }

    /**
     * setSiglaBanco
     * @param siglaBanco siglaBanco
     */
    public void setSiglaBanco(String siglaBanco) {
        this.siglaBanco = siglaBanco;
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
     * @param codigoRetorno - int do codigoRetorno a ser configurado.
     */
    public void setCodigoRetorno(int codigoRetorno) {
        this.codigoRetorno = codigoRetorno;
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
     * @param erroGSBS - int do erroGSBS a ser configurado.
     */
    public void setErroGSBS(int erroGSBS) {
        this.erroGSBS = erroGSBS;
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
     * @param filler - String do filler a ser configurado.
     */
    public void setFiller(String filler) {
        this.filler = filler;
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
     * @param sinalSqlCode - String do sinalSqlCode a ser configurado.
     */
    public void setSinalSqlCode(String sinalSqlCode) {
        this.sinalSqlCode = sinalSqlCode;
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
     * @param sqlCode - int do sqlCode a ser configurado.
     */
    public void setSqlCode(int sqlCode) {
        this.sqlCode = sqlCode;
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
     * @param sqlCodeGSBS - int do sqlCodeGSBS a ser configurado.
     */
    public void setSqlCodeGSBS(int sqlCodeGSBS) {
        this.sqlCodeGSBS = sqlCodeGSBS;
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
     * @param sqlErrMC - String do sqlErrMC a ser configurado.
     */
    public void setSqlErrMC(String sqlErrMC) {
        this.sqlErrMC = sqlErrMC;
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
     * @param sqlErrML - int do sqlErrML a ser configurado.
     */
    public void setSqlErrML(int sqlErrML) {
        this.sqlErrML = sqlErrML;
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
     * @param tabelaErro - String do tabelaErro a ser configurado.
     */
    public void setTabelaErro(String tabelaErro) {
        this.tabelaErro = tabelaErro;
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
     * @param tipoErroGSBS - int do tipoErroGSBS a ser configurado.
     */
    public void setTipoErroGSBS(int tipoErroGSBS) {
        this.tipoErroGSBS = tipoErroGSBS;
    }

    /**
     * Retorna o valor do atributo fillerFinal.
     * @return o valor do atributo fillerFinal
     */
    public String getFillerFinal() {
        return fillerFinal;
    }

    /**
     * Especifica o valor do atributo fillerFinal.
     * @param fillerFinal - String do fillerFinal a ser configurado.
     */
    public void setFillerFinal(String fillerFinal) {
        this.fillerFinal = fillerFinal;
    }

    /**
     * Construtor.
     * @param cdBancoExterno
     */
    public BancoVO() {
        super();
    }

    /**
     * Construtor.
     * @param cdBancoExterno - int.
     */
    public BancoVO(int cdBancoExterno) {
        this.setCdBancoExterno(cdBancoExterno);
    }

    /**
     * Construtor por Código usando Código Externo de banco.
     * @param cdBancoExterno - String.
     * @return BancoVO.
     */
    public static BancoVO getInstance(int cdBancoExterno) {
        BancoVO book = new BancoVO();
        book.setCdBancoExterno(cdBancoExterno);
        return book;
    }

    /**
     * Retorna o valor do atributo cdBancoExterno.
     * @return o valor do atributo cdBancoExterno
     */
    public Integer getCdBancoExterno() {
        return cdBancoExterno;
    }

    /**
     * Especifica o valor do atributo cdBancoExterno.
     * @param cdBancoExterno - Integer do cdBancoExterno a ser configurado.
     */
    public void setCdBancoExterno(Integer cdBancoExterno) {
        this.cdBancoExterno = cdBancoExterno;
    }

    /**
     * Retorna o valor do atributo cdBancoInterno.
     * @return o valor do atributo cdBancoInterno
     */
    public Integer getCdBancoInterno() {
        return cdBancoInterno;
    }

    /**
     * Especifica o valor do atributo cdBancoInterno.
     * @param cdBancoInterno - Integer do cdBancoInterno a ser configurado.
     */
    public void setCdBancoInterno(Integer cdBancoInterno) {
        this.cdBancoInterno = cdBancoInterno;
    }

}
