package br.com.bradseg.depi.depositoidentificado.exception;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Classe de exceção de aplicação com mensagem de negocio amigável.
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
     * Contrutor utilizado para setar a coleção de erros.
     * @param pErros pErros
     */
    public DEPIIntegrationExceptionsCollection(Collection<DEPIIntegrationException> pErros) {
        erros = pErros;
    }

    /**
     * Método que retornará a Coleção de erros.
     * @return Collection<DEPIIntegrationException>
     */
    public Collection<DEPIIntegrationException> getErros() {
        return erros;
    }
    /**
     * Adiciona um erro a coleção de erros
     * @param chave - ConstantesDAO - Recurso de um .properties.
     * @param parametros - Parametros do Recurso.
     */
    public void addErr(String chave, String... parametros) {
        this.getErros().add(new DEPIIntegrationException(chave, parametros));
    }

    /**
     * configura coleção de erros
     * @param erros - coleção de erros
     */
    public void setErros(Collection<DEPIIntegrationException> erros) {
        this.erros = erros;
    }
}
