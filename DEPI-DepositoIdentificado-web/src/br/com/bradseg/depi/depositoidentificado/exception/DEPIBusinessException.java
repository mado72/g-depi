package br.com.bradseg.depi.depositoidentificado.exception;

import br.com.bradseg.bsad.framework.core.exception.BusinessException;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;

/**
 * Classe de exceção de aplicação com mensagem de negocio amigável.
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
     * Construtor genérico
     * @param chave Chave da mensagem
     * @param params Parâmetros da mensagem
     */
    public DEPIBusinessException(String chave, String... params) {
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
