package br.com.bradseg.depi.depositoidentificado.model.book;

import java.util.ArrayList;
import java.util.List;

import br.com.bradseg.depi.dp06.model.vo.ItemContabilVO;
import br.com.cpmbraxis.framework.cicsannotations.annotation.CicsField;
import br.com.cpmbraxis.framework.cicsannotations.annotation.CicsOccurs;
import br.com.cpmbraxis.framework.cicsannotations.annotation.CicsTransaction;
import br.com.cpmbraxis.framework.cicsannotations.type.Continuable;
import br.com.cpmbraxis.framework.cicsannotations.util.CicsConstants.UseType;

/**
 * BOOK DE COMMAREA CTEV1021
 * @author F�bio Henrique
 */
@CicsTransaction(program = "CTEV0021", transaction = "TV45", maxLength = 11052)
public class CTEV0021 extends BaseBook implements Continuable {

    private static final long serialVersionUID = 6019176497897596590L;

    /**
     * {@inheritDoc}
     */
    public boolean hasContinuation() {
        return "C".equals(this.getStOcorrencias());
    }
    @CicsField(order = 10, size = 4, pattern = "0000")
    private int codigoTipoEventoNegocio;

    @CicsField(order = 15, size = 3, pattern = "000")
    private int numSeqPagEnt = 0;

    @CicsField(order = 20, size = 1, use = UseType.Out)
    private String stOcorrencias;

    @CicsField(order = 30, size = 3, pattern = "000", use = UseType.Out)
    private int numSeqPagSai;

    @CicsField(order = 40, size = 1, pattern = "0", use = UseType.Out)
    private int retCode;

    @CicsField(order = 50, size = 70, use = UseType.Out)
    private String mensagem;

    @CicsField(order = 60, size = 3, pattern = "000", use = UseType.Out)
    private int sqlCode;

    @CicsField(order = 70, size = 30, use = UseType.Out)
    private String rotina;

    @CicsField(order = 90, size = 30, use = UseType.Out)
    private String tabela;

    @CicsField(order = 100, size = 30, use = UseType.Out)
    private String conteudo;

    @CicsField(order = 110, size = 2, use = UseType.Out)
    private String resultado;

    @CicsField(order = 120, size = 75, use = UseType.Out)
    private String descricaoTipoEventoNegocio;

    @CicsOccurs(order = 130, numberOfItensDependsOn = 30, use = UseType.Out)
    private List<ItemContabilVO> lista = new ArrayList<ItemContabilVO>();

    /**
     * Retorna o valor do atributo serialVersionUID.
     * @return o valor do atributo serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * Retorna o valor do atributo codigoTipoEventoNegocio.
     * @return o valor do atributo codigoTipoEventoNegocio
     */
    public int getCodigoTipoEventoNegocio() {
        return codigoTipoEventoNegocio;
    }

    /**
     * Especifica o valor do atributo codigoTipoEventoNegocio.
     * @param codigoTipoEventoNegocio21 - int do codigoTipoEventoNegocio a ser configurado.
     */
    public void setCodigoTipoEventoNegocio(int codigoTipoEventoNegocio21) {
        this.codigoTipoEventoNegocio = codigoTipoEventoNegocio21;
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
     * @param conteudo21 - String do conteudo a ser configurado.
     */
    public void setConteudo(String conteudo21) {
        this.conteudo = conteudo21;
    }

    /**
     * Retorna o valor do atributo descricaoTipoEventoNegocio.
     * @return o valor do atributo descricaoTipoEventoNegocio
     */
    public String getDescricaoTipoEventoNegocio() {
        return descricaoTipoEventoNegocio;
    }

    /**
     * Especifica o valor do atributo descricaoTipoEventoNegocio.
     * @param descricaoTipoEventoNegocio21 - String do descricaoTipoEventoNegocio a ser configurado.
     */
    public void setDescricaoTipoEventoNegocio(String descricaoTipoEventoNegocio21) {
        this.descricaoTipoEventoNegocio = descricaoTipoEventoNegocio21;
    }

    /**
     * Retorna o valor do atributo lista.
     * @return o valor do atributo lista
     */
    public List<ItemContabilVO> getLista() {
        return lista;
    }

    /**
     * Especifica o valor do atributo lista.
     * @param lista21 - List<ItemContabilVO> do lista a ser configurado.
     */
    public void setLista(List<ItemContabilVO> lista21) {
        this.lista = lista21;
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
     * @param mensagem21 - String do mensagem a ser configurado.
     */
    public void setMensagem(String mensagem21) {
        this.mensagem = mensagem21;
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
     * @param numSeqPagEnt21 - int do numSeqPagEnt a ser configurado.
     */
    public void setNumSeqPagEnt(int numSeqPagEnt21) {
        this.numSeqPagEnt = numSeqPagEnt21;
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
     * @param numSeqPagSai21 - int do numSeqPagSai a ser configurado.
     */
    public void setNumSeqPagSai(int numSeqPagSai21) {
        this.numSeqPagSai = numSeqPagSai21;
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
     * @param resultado21 - String do resultado a ser configurado.
     */
    public void setResultado(String resultado21) {
        this.resultado = resultado21;
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
     * @param retCode21 - int do retCode a ser configurado.
     */
    public void setRetCode(int retCode21) {
        this.retCode = retCode21;
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
     * @param rotina21 - String do rotina a ser configurado.
     */
    public void setRotina(String rotina21) {
        this.rotina = rotina21;
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
     * @param sqlCode21 - int do sqlCode a ser configurado.
     */
    public void setSqlCode(int sqlCode21) {
        this.sqlCode = sqlCode21;
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
     * @param stOcorrencias21 - String do stOcorrencias a ser configurado.
     */
    public void setStOcorrencias(String stOcorrencias21) {
        this.stOcorrencias = stOcorrencias21;
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
     * @param tabela21 - String do tabela a ser configurado.
     */
    public void setTabela(String tabela21) {
        this.tabela = tabela21;
    }

}
