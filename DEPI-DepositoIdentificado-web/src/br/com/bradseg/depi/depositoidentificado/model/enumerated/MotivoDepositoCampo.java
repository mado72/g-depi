package br.com.bradseg.depi.depositoidentificado.model.enumerated;

import java.util.Arrays;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;

/**
 * Fornece os critérios para consultas em Motivo Depósito.
 * 
 * @author fernando.menezes
 * @author Marcelo Damasceno
 */
public enum MotivoDepositoCampo implements IEntidadeCampo {
    /**
     * Código
     */
    Codigo(ConstantesDEPI.TABELA_MOTIVO_ID, TipoCampo.NUM, 3),
    /**
     * Descrição
     */
    DescricaoBasica("RMOTVO_DEP_IDTFD", TipoCampo.ALFA_OBRIG, 20),
    /**
     * Descrição Detalhada.
     */
    DescricaoDetalhada("RDETLH_MOTVO_DEP", TipoCampo.ALFA_OBRIG_BIG, ConstantesDEPI.SIZE_DUZENTOS), 
    
    /**
     * Ativo 
     */
    Ativo("CIND_REG_ATIVO", TipoCampo.ALFA_OBRIG, 1);
    
    private final static List<MotivoDepositoCampo> CRITERIAS = Arrays.asList(DescricaoBasica, DescricaoDetalhada);
    
    private String nome;

    private TipoCampo tipoCampo;

    private int size;
    
    /**
     * @param nome - nome do campo
     * @param tipoCampo - tipo do campo
     * @param size - int
     */
    MotivoDepositoCampo(String nome, TipoCampo tipoCampo, int size) {
    	this.nome = ConstantesDEPI.SCHEMA_BANCO.concat(ConstantesDEPI.DOT).concat(ConstantesDEPI.TABELA_MOTIVO).concat(
    			ConstantesDEPI.DOT).concat(nome);
    	this.tipoCampo = tipoCampo;
    	this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isCics() {
        return false;
    }

    @Override
    public TipoCampo getTipoCampo() {
        return tipoCampo;
    }

    /**
     * retorna o nome
     * @return - retorna o nome do campo
     */
    @Override
	public String getNome() {
        return nome;
    }

    /**
     * retorna o nome
     * @param nome - nome do campo de motivo depósito
     * @return - retorna o nome do campo
     */
    public static MotivoDepositoCampo obterPorNome(String nome) {
        for (MotivoDepositoCampo campo : MotivoDepositoCampo.values()) {
            if (campo.getNome().equals(nome)) {
                return campo;
            }
        }
        return null;
    }

    /**
     * Retorna valores da combo de consulta.
     * @return DepartamentoCampo[].
     */
    public static List<MotivoDepositoCampo> valuesForCriteria() {
        return CRITERIAS;
    }
}
