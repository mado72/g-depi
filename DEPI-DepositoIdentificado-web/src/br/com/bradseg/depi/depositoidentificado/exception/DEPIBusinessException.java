package br.com.bradseg.depi.depositoidentificado.exception;

import br.com.bradseg.bsad.framework.core.exception.BusinessException;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;

/**
 * Classe de exce��o de aplica��o com mensagem de negocio amig�vel.
 * @author fabio.pinto
 */
public class DEPIBusinessException extends BusinessException {

    private static final long serialVersionUID = 4693561306696000451L;
    private String chave;
    private String[] parametros;
    
    /**
     * Construtor.
     */
    public DEPIBusinessException() {
        super();
    }

    /**
     * Construtor gen�rico
     * @param chave Chave da mensagem
     * @param params Par�metros da mensagem
     */
    public DEPIBusinessException(String chave, String... params) {
        super(BaseUtil.getInstance().getMensagem(chave, params));
        this.parametros = params;
        this.chave = chave;
    }

    /**
     * Construtor gen�rico
     * @param excecao Exce��o nativa
     * @param chave Chave da mensagem
     * @param params Par�metros da mensagem
     */
    public DEPIBusinessException(Exception excecao, String chave, String... params) {
        super(BaseUtil.getInstance().getMensagem(chave, params), excecao);
        this.parametros = params;
        this.chave = chave;
    }

    /*
     * {@inheritDoc}
     */
    public String getChave() {
        return chave;
    }

    /*
     * {@inheritDoc}
     */
    public String[] getParametros() {
        return this.parametros.clone();
    }

}
