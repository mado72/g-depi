/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.model.enumerated;

import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;

/**
 * Enumerator dos campos de filtro de CompanhiaSeguradoraCampo
 * @author F�bio Henrique - fabio.almeida@cpmbraxis.com
 */
public enum CompanhiaSeguradoraCampo implements IEntidadeCampo {

    /**
     * Código Companhia
     */
    CodigoCompanhia(ConstantesDEPI.TABELA_CIA_ID, "Código Companhia", TipoCampo.NUM, false, 4),

    /**
     * Descrição Companhia.
     */
	DescricaoCompanhia(ConstantesDEPI.TABELA_CIA_NOME, "Descrição Companhia",
			TipoCampo.ALFA_OBRIG, true, 20);

    private String nome;

    private String descricao;

    private TipoCampo tipoCampo;

    private boolean cics;

    private int size;

    /**
     * Construtor
     * @param nome Campo no banco
     * @param descricao Descricao do campo
     * @param tipoCampo Tipo do campo
     * @param cics - se é consultado a partir do cics.
     * @param size - int.
     */
    CompanhiaSeguradoraCampo(String nome, String descricao, TipoCampo tipoCampo, boolean cics, int size) {
        this.nome = ConstantesDEPI.SCHEMA_BANCO.concat(ConstantesDEPI.DOT).concat(ConstantesDEPI.TABELA_CIA)
            .concat(ConstantesDEPI.DOT).concat(nome);
        this.nome = nome;
        this.descricao = descricao;
        this.tipoCampo = tipoCampo;
        this.cics = cics;
        this.size = size;
    }

    /**
     * metodo que retorna a descicao
     * @return o descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * metodo que retorna o tipoCampo
     * @return o tipoCampo
     */
    @Override
	public TipoCampo getTipoCampo() {
        return tipoCampo;
    }

    /**
     * metodo que retorna o nome
     * @return o nome
     */
    @Override
	public String getNome() {
        return nome;
    }

    /**
     * Obter por nome
     * @param nome Campo no banco
     * @return DepartamentoCompanhiaCampo
     */
    public static CompanhiaSeguradoraCampo obterPorNome(String nome) {
        for (CompanhiaSeguradoraCampo campo : CompanhiaSeguradoraCampo.values()) {
            if (campo.getNome().equals(nome)) {
                return campo;
            }
        }
        return null;
    }

    /**
     * Especifica o valor do atributo descricao.
     * @param descricao - String do descricao a ser configurado.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Especifica o valor do atributo nome.
     * @param nome - String do nome a ser configurado.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Especifica o valor do atributo tipoCampo.
     * @param tipoCampo - TipoCampo do tipoCampo a ser configurado.
     */
    public void setTipoCampo(TipoCampo tipoCampo) {
        this.tipoCampo = tipoCampo;
    }

    /**
     * Retorna o valor do atributo cics.
     * @return o valor do atributo cics
     */
    @Override
	public boolean isCics() {
        return cics;
    }

    /**
     * Especifica o valor do atributo cics.
     * @param cics - boolean do cics a ser configurado.
     */
    public void setCics(boolean cics) {
        this.cics = cics;
    }

    /**
     * Retorna o valor do atributo size.
     * @return o valor do atributo size
     */
    @Override
	public int getSize() {
        return size;
    }
}
