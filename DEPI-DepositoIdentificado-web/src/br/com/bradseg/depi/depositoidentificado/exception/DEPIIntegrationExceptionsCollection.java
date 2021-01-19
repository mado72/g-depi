package br.com.bradseg.depi.depositoidentificado.exception;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Classe de exce��o de aplica��o com mensagem de negocio amig�vel.
 */
public class DEPIIntegrationExceptionsCollection extends Exception {

    private static final long serialVersionUID = 4471613120933080996L;
    private Collection<DEPIIntegrationException> erros = null;

    /**
     * Construtor - Super Call.
     */
    public DEPIIntegrationExceptionsCollection() {
        super();
        erros = new ArrayList<DEPIIntegrationException>();
    }

    /**
     * Contrutor utilizado para setar a cole��o de erros.
     * @param pErros pErros
     */
    public DEPIIntegrationExceptionsCollection(Collection<DEPIIntegrationException> pErros) {
        erros = pErros;
    }

    /**
     * Método que retornar� a Cole��o de erros.
     * @return Collection<DEPIIntegrationException>
     */
    public Collection<DEPIIntegrationException> getErros() {
        return erros;
    }
    /**
     * Adiciona um erro a cole��o de erros
     * @param chave - ConstantesDAO - Recurso de um .properties.
     * @param parametros - Parametros do Recurso.
     */
    public void addErr(String chave, String... parametros) {
        this.getErros().add(new DEPIIntegrationException(chave, parametros));
    }

    /**
     * configura cole��o de erros
     * @param erros - cole��od e erros
     */
    public void setErros(Collection<DEPIIntegrationException> erros) {
        this.erros = erros;
    }
}
