package br.com.bradseg.depi.depositoidentificado.model.enumerated;

/**
 * Interface para os Enumerator de campos das entidades
 * @author fabio.pinto
 */
public interface IEntidadeCampo {

    /**
     * Obter nome
     * @return nome
     */
    String getNome();

    /**
     * Obter TipoCampo
     * @return TipoCampo
     */
    TipoCampo getTipoCampo();

    /**
     * O tipo campo � cics.
     * @return boolean - se � cics.
     */
    boolean isCics();
    
    /**
     * Tamanho do campo.
     * @return int
     */
    int getSize();

}
