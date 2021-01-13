package br.com.bradseg.depi.depositoidentificado.model.enumerated;

import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;

/**
 * @author fernando.menezes
 * @refactor Marcelo Damasceno
 */
public enum MotivoDepositoCampo implements IEntidadeCampo {
    /**
     * Código
     */
    Codigo(ConstantesDEPI.TABELA_MOTIVO_ID, "Código Motivo", TipoCampo.NUM, ConstantesDEPI.SIZE_NAO_DEFINIDO),
    /**
     * Descrição
     */
    DescricaoBasica("RMOTVO_DEP_IDTFD", "Descrição Básica Motivo", TipoCampo.ALFA_OBRIG, ConstantesDEPI.SIZE_NAO_DEFINIDO),
    /**
     * Descrição Detalhada.
     */
    DescricaoDetalhada("RDETLH_MOTVO_DEP", "Descrição Detalhada Motivo", TipoCampo.ALFA_OBRIG_BIG, ConstantesDEPI.SIZE_DUZENTOS);
    
    private final static MotivoDepositoCampo[] CRITERIAS = {
    	DescricaoBasica, DescricaoDetalhada
    };
    
    private String nome;

    private String descricao;

    private TipoCampo tipoCampo;

    private int size;
    
    /**
     * @param nome - nome do campo
     * @param descricao - descrição exibida
     * @param tipoCampo - tipo do campo
     * @param size - int
     */
    MotivoDepositoCampo(String nome, String descricao, TipoCampo tipoCampo, int size) {
    	this.nome = ConstantesDEPI.SCHEMA_BANCO.concat(ConstantesDEPI.DOT).concat(ConstantesDEPI.TABELA_MOTIVO).concat(
    			ConstantesDEPI.DOT).concat(nome);
    	this.descricao = descricao;
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
    public String getDescricao() {
        return descricao;
    }

    @Override
    public TipoCampo getTipoCampo() {
        return tipoCampo;
    }

    /**
     * retorna o nome
     * @return - retorna o nome do campo
     */
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
    public static MotivoDepositoCampo[] valuesForCriteria() {
        return CRITERIAS;
    }
}
