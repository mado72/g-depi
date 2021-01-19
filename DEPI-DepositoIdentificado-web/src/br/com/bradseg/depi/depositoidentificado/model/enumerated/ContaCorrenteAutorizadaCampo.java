package br.com.bradseg.depi.depositoidentificado.model.enumerated;

import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;

/**
 * Classe enum ContaCorrenteAutorizadaCampo
 * @author F�bio Henrique
 */
public enum ContaCorrenteAutorizadaCampo implements IEntidadeCampo {

    /**
     * Cia.
     */
    CodigoCia(ConstantesDEPI.TABELA_COMPANHIA_DEPARTAMENTO_ID, CompanhiaSeguradoraCampo.CodigoCompanhia),

    /**
     * Código Banco
     */
    CodigoBanco(ConstantesDEPI.TABELA_CONTA_CORRENTE_AUTORIZADA_BANCO, "Banco", TipoCampo.NUM, false,
        ConstantesDEPI.SIZE_NAO_DEFINIDO),

    /**
     * Código Ag�ncia
     */
    CodigoAgencia(ConstantesDEPI.TABELA_CONTA_CORRENTE_AUTORIZADA_AGENCIA, "Ag�ncia", TipoCampo.NUM, false,
        ConstantesDEPI.SIZE_NAO_DEFINIDO),
    
    /**
     * Código Conta Corrente
     */
    CodigoContaCorrente(ConstantesDEPI.TABELA_CONTA_CORRENTE_AUTORIZADA_CONTA_CORRENTE, "Conta Corrente", TipoCampo.NUM,
        false, ConstantesDEPI.SIZE_NAO_DEFINIDO),

    /**
     * Numero Interno Conta Corrente.
     */
    NumeroInternoContaCorrente(ConstantesDEPI.TABELA_CONTA_CORRENTE_AUTORIZADA_CONTA_INTERNA, "Conta Interna", TipoCampo.NUM,
        false, ConstantesDEPI.SIZE_NAO_DEFINIDO),
    
    /**
     * Numero cliente transacao personalizada
     */
    trps(ConstantesDEPI.TABELA_CONTA_CORRENTE_AUTORIZADA_TRPS, "TRPS", TipoCampo.NUM, false,
            ConstantesDEPI.SIZE_NAO_DEFINIDO);


    private String nome;

    private String descricao;

    private TipoCampo tipoCampo;

    private boolean cics;

    private int size;

    /**
     * Retorna o valor do atributo size.
     * @return o valor do atributo size
     */
    public int getSize() {
        return size;
    }

    /**
     * Construtor.
     * @param nome - String.
     * @param campo - IEntidadeCampo.
     */
    ContaCorrenteAutorizadaCampo(String nome, IEntidadeCampo campo) {
        this.nome = ConstantesDEPI.SCHEMA_BANCO.concat(ConstantesDEPI.DOT).concat(ConstantesDEPI.TABELA_CONTA_CORRENTE_AUTORIZADA)
            .concat(ConstantesDEPI.DOT).concat(nome);
        this.descricao = campo.getDescricao();
        this.tipoCampo = campo.getTipoCampo();
        this.cics = campo.isCics();
        this.size = campo.getSize();
    }

    /**
     * @param nome String
     * @param descricao String
     * @param tipoCampo TipoCampo
     * @param cics - boolean - s� � consultado com servi�o cics.
     * @param size int
     */
    ContaCorrenteAutorizadaCampo(String nome, String descricao, TipoCampo tipoCampo, boolean cics, int size) {
        this.nome = ConstantesDEPI.SCHEMA_BANCO.concat(ConstantesDEPI.DOT).concat(ConstantesDEPI.TABELA_CONTA_CORRENTE_AUTORIZADA)
            .concat(ConstantesDEPI.DOT).concat(nome);
        this.descricao = descricao;
        this.tipoCampo = tipoCampo;
        this.cics = cics;
        this.size = size;
    }

    /**
     * getDescricao
     * @return descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * getTipoCampo
     * @return tipoCampo
     */
    public TipoCampo getTipoCampo() {
        return tipoCampo;
    }

    /**
     * getNome
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o valor do atributo cics.
     * @return o valor do atributo cics
     */
    public boolean isCics() {
        return cics;
    }

    /**
     * ObterPorNome
     * @param nome nome
     * @return campo
     */
    public static ContaCorrenteAutorizadaCampo obterPorNome(String nome) {
        for (ContaCorrenteAutorizadaCampo campo : ContaCorrenteAutorizadaCampo.values()) {
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
    public static ContaCorrenteAutorizadaCampo[] valuesForCriteria() {
        return ContaCorrenteAutorizadaCampo.values();
    }

}
