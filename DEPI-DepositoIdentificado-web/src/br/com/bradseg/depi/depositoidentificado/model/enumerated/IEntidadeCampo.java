package br.com.bradseg.depi.depositoidentificado.model.enumerated;

/**
 * Interface para os Enumerator de campos das entidades
 * @author fabio.pinto
 */
public interface IEntidadeCampo {

    /**
     * Obter descricao
     * @return descricao
     */
    String getDescricao();

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
     * O tipo campo é cics.
     * @return boolean - se é cics.
     */
    boolean isCics();
    
    /**
     * Tamanho do campo.
     * @return int
     */
    int getSize();


}
