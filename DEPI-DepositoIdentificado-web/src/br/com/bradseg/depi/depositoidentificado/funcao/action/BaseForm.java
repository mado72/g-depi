package br.com.bradseg.depi.depositoidentificado.funcao.action;

import java.io.Serializable;

/**
 * Abstração dos forms da aplicação.
 * @author Marcelo Damasceno
 */
public abstract class BaseForm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Atributo referente a ação anterior que foi executada.
     */
    private String acao = "exibir", acaoAnterior = "exibir";

    private String tipoAcaoVoltar = "exibir";
    

    /**
     * Chave Composta utilizada para não enviar uma url com muitos parametros. Para simplificar.
     */
    private String chaveComposta;

    /**
     * Par�metro para fuction JavaScript setFocusOnLoad.
     */
    private String focusOn;

    /**
     * Se atendeu as validações.
     */
    private boolean frmOK;

    /**
     * Retorna o frmOK.
     * @return O atributo frmOK
     */
    public boolean isFrmOK() {
        return frmOK;
    }

    /**
     * Especifica o frmOK.
     * @param frmOK boolean do frmOK a ser setado
     */
    public void setFrmOK(boolean frmOK) {
        this.frmOK = frmOK;
    }

    /**
     * Retorna o focusOn.
     * @return O atributo focusOn
     */
    public String getFocusOn() {
        return focusOn;
    }

    /**
     * Especifica o focusOn.
     * @param focusOn String do focusOn a ser setado
     */
    public void setFocusOn(String focusOn) {
        this.focusOn = focusOn;
    }

    /**
     * Retorna o chaveComposta.
     * @return O atributo chaveComposta
     */
    public String getChaveComposta() {
        return chaveComposta;
    }

    /**
     * Especifica o chaveComposta.
     * @param chaveComposta String do chaveComposta a ser setado
     */
    public void setChaveComposta(String chaveComposta) {
        this.chaveComposta = chaveComposta;
    }

    /**
     * Retorna o serialVersionUID.
     * @return O atributo serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * Retorna o atributo acao.
     * @return o atributo acao
     */
    public String getAcao() {
        return acao;
    }

    /**
     * Seta um valor no atributo acao.
     * @param pAcao - novo valor para o atributo acao
     */
    public void setAcao(String pAcao) {
        this.acao = pAcao;
    }

    /**
     * Retorna o atributo acaoAnterior.
     * @return o atributo acaoAnterior
     */
    public String getAcaoAnterior() {
        return acaoAnterior;
    }

    /**
     * Especifica o acaoAnterior.
     * @param acaoAnterior String do acaoAnterior a ser setado
     */
    public void setAcaoAnterior(String acaoAnterior) {
        this.acaoAnterior = acaoAnterior;
    }

    /**
     * Retorna o tipoAcaoVoltar.
     * @return O atributo tipoAcaoVoltar
     */
    public String getTipoAcaoVoltar() {
        return tipoAcaoVoltar;
    }

    /**
     * Especifica o tipoAcaoVoltar.
     * @param pTipoAcaoVoltar String do tipoAcaoVoltar a ser setado
     */
    public void setTipoAcaoVoltar(String pTipoAcaoVoltar) {
        tipoAcaoVoltar = pTipoAcaoVoltar;
    }
}
