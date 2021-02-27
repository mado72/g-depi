package br.com.bradseg.depi.depositoidentificado.cics.book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.annotations.CicsField;
import br.com.bradseg.depi.depositoidentificado.util.annotations.CicsField.Direction;
import br.com.bradseg.depi.depositoidentificado.util.annotations.CicsProgram;
import br.com.bradseg.depi.depositoidentificado.vo.EventoContabilVO;

/**
 * BOOK DE COMMAREA CTEV1020
 */
@CicsProgram(programName = "CTEV0020", transactionName = "TV44", commLength = 16252)
public class CTEV0020 implements Serializable {

    private static final long serialVersionUID = 6019176497897596590L;

    @CicsField(order = 10, size = 4, pattern = "0000", direction = Direction.InOut)
    private int codigoTipoObjetoNegocio;

    @CicsField(order = 15, size = 3, pattern = "000", direction = Direction.InOut)
    private int numSeqPagEnt;

    @CicsField(order = 20, size = 1)
    private String stOcorrencias;

    @CicsField(order = 30, size = 3, pattern = "000")
    private int numSeqPagSai;

    @CicsField(order = 40, size = 1, pattern = "0")
    private int retCode;

    @CicsField(order = 50, size = 70)
    private String mensagem;

    @CicsField(order = 60, size = 3, pattern = "000")
    private int sqlCode;

    @CicsField(order = 70, size = 30)
    private String rotina;

    @CicsField(order = 90, size = 30)
    private String tabela;

    @CicsField(order = 100, size = 30)
    private String conteudo;

    @CicsField(order = 110, size = 2)
    private String resultado;

    @CicsField(order = 120, size = 75)
    private String descricaoTipoObjetoNegocio;

    @CicsField(order = 130, occurrences = 30, size = 30 * 80)
    private List<EventoContabilVO> lista = new ArrayList<EventoContabilVO>();

    /**
     * Retorna o valor do atributo serialVersionUID.
     * @return o valor do atributo serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * Retorna o valor do atributo codigoTipoObjetoNegocio.
     * @return o valor do atributo codigoTipoObjetoNegocio
     */
    public int getCodigoTipoObjetoNegocio() {
        return codigoTipoObjetoNegocio;
    }

    /**
     * Especifica o valor do atributo codigoTipoObjetoNegocio.
     * @param codigoTipoObjetoNegocio - int do codigoTipoObjetoNegocio a ser configurado.
     */
    public void setCodigoTipoObjetoNegocio(int codigoTipoObjetoNegocio) {
        this.codigoTipoObjetoNegocio = codigoTipoObjetoNegocio;
    }

    /**
     * Retorna o valor do atributo conteudo.
     * @return o valor do atributo conteudo
     */
    public String getConteudo() {
        return conteudo;
    }

    /**
     * Especifica o valor do atributo conteudo.
     * @param conteudo - String do conteudo a ser configurado.
     */
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    /**
     * Retorna o valor do atributo descricaoTipoObjetoNegocio.
     * @return o valor do atributo descricaoTipoObjetoNegocio
     */
    public String getDescricaoTipoObjetoNegocio() {
        return descricaoTipoObjetoNegocio;
    }

    /**
     * Especifica o valor do atributo descricaoTipoObjetoNegocio.
     * @param descricaoTipoObjetoNegocio - String do descricaoTipoObjetoNegocio a ser configurado.
     */
    public void setDescricaoTipoObjetoNegocio(String descricaoTipoObjetoNegocio) {
        this.descricaoTipoObjetoNegocio = descricaoTipoObjetoNegocio;
    }

    /**
     * Retorna o valor do atributo lista.
     * @return o valor do atributo lista
     */
    public List<EventoContabilVO> getLista() {
        return lista;
    }

    /**
     * Especifica o valor do atributo lista.
     * @param lista20 - List<EventoContabilVO> do lista a ser configurado.
     */
    public void setLista(List<EventoContabilVO> lista20) {
        this.lista = lista20;
    }

    /**
     * Retorna o valor do atributo mensagem.
     * @return o valor do atributo mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * Especifica o valor do atributo mensagem.
     * @param mensagem20 - String do mensagem a ser configurado.
     */
    public void setMensagem(String mensagem20) {
        this.mensagem = mensagem20;
    }

    /**
     * Retorna o valor do atributo numSeqPagEnt.
     * @return o valor do atributo numSeqPagEnt
     */
    public int getNumSeqPagEnt() {
        return numSeqPagEnt;
    }

    /**
     * Especifica o valor do atributo numSeqPagEnt.
     * @param numSeqPagEnt20 - int do numSeqPagEnt a ser configurado.
     */
    public void setNumSeqPagEnt(int numSeqPagEnt20) {
        this.numSeqPagEnt = numSeqPagEnt20;
    }

    /**
     * Retorna o valor do atributo numSeqPagSai.
     * @return o valor do atributo numSeqPagSai
     */
    public int getNumSeqPagSai() {
        return numSeqPagSai;
    }

    /**
     * Especifica o valor do atributo numSeqPagSai.
     * @param numSeqPagSai20 - int do numSeqPagSai a ser configurado.
     */
    public void setNumSeqPagSai(int numSeqPagSai20) {
        this.numSeqPagSai = numSeqPagSai20;
    }

    /**
     * Retorna o valor do atributo resultado.
     * @return o valor do atributo resultado
     */
    public String getResultado() {
        return resultado;
    }

    /**
     * Especifica o valor do atributo resultado.
     * @param resultado20 - String do resultado a ser configurado.
     */
    public void setResultado(String resultado20) {
        this.resultado = resultado20;
    }

    /**
     * Retorna o valor do atributo retCode.
     * @return o valor do atributo retCode
     */
    public int getRetCode() {
        return retCode;
    }

    /**
     * Especifica o valor do atributo retCode.
     * @param retCode20 - int do retCode a ser configurado.
     */
    public void setRetCode(int retCode20) {
        this.retCode = retCode20;
    }

    /**
     * Retorna o valor do atributo rotina.
     * @return o valor do atributo rotina
     */
    public String getRotina() {
        return rotina;
    }

    /**
     * Especifica o valor do atributo rotina.
     * @param rotina20 - String do rotina a ser configurado.
     */
    public void setRotina(String rotina20) {
        this.rotina = rotina20;
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
     * @param sqlCode20 - int do sqlCode a ser configurado.
     */
    public void setSqlCode(int sqlCode20) {
        this.sqlCode = sqlCode20;
    }

    /**
     * Retorna o valor do atributo stOcorrencias.
     * @return o valor do atributo stOcorrencias
     */
    public String getStOcorrencias() {
        return stOcorrencias;
    }

    /**
     * Especifica o valor do atributo stOcorrencias.
     * @param stOcorrencias20 - String do stOcorrencias a ser configurado.
     */
    public void setStOcorrencias(String stOcorrencias20) {
        this.stOcorrencias = stOcorrencias20;
    }

    /**
     * Retorna o valor do atributo tabela.
     * @return o valor do atributo tabela
     */
    public String getTabela() {
        return tabela;
    }

    /**
     * Especifica o valor do atributo tabela.
     * @param tabela20 - String do tabela a ser configurado.
     */
    public void setTabela(String tabela20) {
        this.tabela = tabela20;
    }

}
