package br.com.bradseg.depi.depositoidentificado.model.enumerated;

import java.util.Arrays;
import java.util.List;

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
    CodigoBanco(ConstantesDEPI.TABELA_CONTA_CORRENTE_AUTORIZADA_BANCO, TipoCampo.NUM, false,
        ConstantesDEPI.SIZE_NAO_DEFINIDO),

    /**
     * Código Agência
     */
    CodigoAgencia(ConstantesDEPI.TABELA_CONTA_CORRENTE_AUTORIZADA_AGENCIA, TipoCampo.NUM, false,
        ConstantesDEPI.SIZE_NAO_DEFINIDO),
    
    /**
     * Código Conta Corrente
     */
    CodigoContaCorrente(ConstantesDEPI.TABELA_CONTA_CORRENTE_AUTORIZADA_CONTA_CORRENTE, TipoCampo.NUM,
        false, ConstantesDEPI.SIZE_NAO_DEFINIDO),

    /**
     * Numero Interno Conta Corrente.
     */
    NumeroInternoContaCorrente(ConstantesDEPI.TABELA_CONTA_CORRENTE_AUTORIZADA_CONTA_INTERNA, TipoCampo.NUM,
        false, ConstantesDEPI.SIZE_NAO_DEFINIDO),
    
    /**
     * Numero cliente transacao personalizada
     */
    trps(ConstantesDEPI.TABELA_CONTA_CORRENTE_AUTORIZADA_TRPS, TipoCampo.NUM, false,
            ConstantesDEPI.SIZE_NAO_DEFINIDO);

    private final static List<ContaCorrenteAutorizadaCampo> CRITERIAS = Arrays.asList(values());

    private String nome;

    private TipoCampo tipoCampo;

    private boolean cics;

    private int size;

    /**
     * Retorna o valor do atributo size.
     * @return o valor do atributo size
     */
    @Override
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
        this.tipoCampo = campo.getTipoCampo();
        this.cics = campo.isCics();
        this.size = campo.getSize();
    }

    /**
     * @param nome String
     * @param tipoCampo TipoCampo
     * @param cics - boolean - só é consultado com serviço cics.
     * @param size int
     */
    ContaCorrenteAutorizadaCampo(String nome, TipoCampo tipoCampo, boolean cics, int size) {
        this.nome = ConstantesDEPI.SCHEMA_BANCO.concat(ConstantesDEPI.DOT).concat(ConstantesDEPI.TABELA_CONTA_CORRENTE_AUTORIZADA)
            .concat(ConstantesDEPI.DOT).concat(nome);
        this.tipoCampo = tipoCampo;
        this.cics = cics;
        this.size = size;
    }

    /**
     * getTipoCampo
     * @return tipoCampo
     */
    @Override
	public TipoCampo getTipoCampo() {
        return tipoCampo;
    }

    /**
     * getNome
     * @return nome
     */
    @Override
	public String getNome() {
        return nome;
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
     * @return Critérios.
     */
    public static List<ContaCorrenteAutorizadaCampo> valuesForCriteria() {
        return CRITERIAS;
    }

}
