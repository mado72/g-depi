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

    /**
     * Indica se o elemento foi exclu�do ou n�o
     */
    private String cdExcluido;
    /**
     * Indica se o elemento foi encontrado ou n�o
     */
    private String cdExiste;

    /**
     * Campos da entidade
     */
    private Integer cdBancoExterno;

    private Integer cdBancoInterno;

    private float cdPessoa;

    private String descricaoBanco;

    private String cnpjBanco;

    private String dvBanco;

    private String siglaBanco;

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
     * getDescricaoBanco
     * @return descricaoBanco
     */
    public String getDescricaoBanco() {
        return descricaoBanco;
    }

    /**
     * setDescricaoBanco
     * @param descricaoBanco String
     */
    public void setDescricaoBanco(String descricaoBanco) {
        this.descricaoBanco = descricaoBanco;
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
     * Construtor.
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
