package br.com.bradseg.depi.depositoidentificado.exception;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;

/**
 * Classe de exceção de aplicação com mensagem de negocio amigável.
 * @author Marcelo Damasceno
 */
public class DEPIIntegrationException
    extends IntegrationException {

    private static final long serialVersionUID = 4693561306696000451L;
    private String chave;
    private String[] parametros;

    /**
     * Construtor
     * @param e - DEPIIntegrationException.
     */
    public DEPIIntegrationException(Exception e) {
        super(e);
        if (e instanceof DEPIIntegrationException) {
            DEPIIntegrationException ex = (DEPIIntegrationException) e;
            this.chave = ex.getChave();
            this.parametros = ex.getParametros();
        }
    }

    /**
     * Construtor - Super Call.
     */
    public DEPIIntegrationException() {
        super();
    }

    /**
     * Construtor genérico
     * @param chave Chave da mensagem
     * @param params Parâmetros da mensagem
     */
    public DEPIIntegrationException(String chave, String... params) {
        super(BaseUtil.getInstance().getMensagem(chave, params));
        this.parametros = params;
        this.chave = chave;
    }

    /**
     * Construtor genérico
     * @param excecao Exceção nativa
     * @param chave Chave da mensagem
     * @param params Parâmetros da mensagem
     */
    public DEPIIntegrationException(Exception excecao, String chave, String... params) {
        super(BaseUtil.getInstance().getMensagem(chave, params), excecao);
        this.parametros = params;
        this.chave = chave;
    }

    /**
     * Obter a chave da mensagem
     * @return Retorna a chave da mensagem
     */
    public String getChave() {
        if (chave == null) {
            return "";
        }
        return chave;
    }

    /**
     * Obter os parâmetros da mensagem
     * @return Retorna os parâmetros da mensagem
     */
    public String[] getParametros() {
        if (this.parametros != null) {
            return this.parametros.clone();
        } else {
            return new String[0];
        }
    }

}
