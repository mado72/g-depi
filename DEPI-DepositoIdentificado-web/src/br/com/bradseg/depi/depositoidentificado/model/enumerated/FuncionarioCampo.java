package br.com.bradseg.depi.depositoidentificado.model.enumerated;

import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;

/**
 * Enumerator dos campos de filtro de Funcionário
 */
public enum FuncionarioCampo implements IEntidadeCampo {

    /**
     * Funcionário Campo - Usuário Campo.
     */

    /**
     * Matricula.
     */
	Matricula(ConstantesDEPI.TABELA_USUARIO_ID, TipoCampo.NUM, 7),

    /**
     * Nome.
     */
	Nome(ConstantesDEPI.TABELA_USUARIO_NOME, TipoCampo.ALFA_OBRIG, 40);

    private String nome;

    private TipoCampo tipoCampo;

    private int size;

    /**
     * Construtor
     * @param nome Campo no banco
     * @param tipoCampo Tipo do campo
     * @param size - int
     */
    FuncionarioCampo(String nome, TipoCampo tipoCampo, int size) {
		this.nome = ConstantesDEPI.SCHEMA_BANCO.concat(ConstantesDEPI.DOT)
				.concat(ConstantesDEPI.TABELA_USUARIO)
				.concat(ConstantesDEPI.DOT).concat(nome);
        this.tipoCampo = tipoCampo;
        this.size = size;
    }

    /**
     * Retorna o valor do atributo size.
     * @return o valor do atributo size
     */
    @Override
	public int getSize() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
	public boolean isCics() {
        return false;
    }

    /**
     * Obter TipoCampo
     * @return TipoCampo
     */
    @Override
	public TipoCampo getTipoCampo() {
        return tipoCampo;
    }

    /**
     * Obter nome
     * @return Nome
     */
    @Override
	public String getNome() {
        return nome;
    }

    /**
     * Obter por nome
     * @param nome Campo no banco
     * @return FuncionarioCampo
     */
    public static FuncionarioCampo obterPorNome(String nome) {
        for (FuncionarioCampo campo : FuncionarioCampo.values()) {
            if (campo.getNome().equals(nome)) {
                return campo;
            }
        }
        return null;
    }

}
