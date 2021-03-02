/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.model.enumerated;

import java.util.Arrays;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;

/**
 * Filtro AssociarMotivoDepositoCampo
 */
public enum AssociarMotivoDepositoCampo implements IEntidadeCampo {

    /**
     * Cia.
     */
    CodigoCia(ConstantesDEPI.TABELA_COMPANHIA_DEPARTAMENTO_ID, CompanhiaSeguradoraCampo.CodigoCompanhia),

    /**
     * Descrição Cia. DescricaoCia(CompanhiaSeguradoraCampo.DescricaoCompanhia),
     */

    /**
     * Código Departamento.
     */
    CodigoDepartamento(ConstantesDEPI.TABELA_DEPARTAMENTO_ID, DepartamentoCampo.Codigo),

    /**
     * Sigla Departamento.
     */
    SiglaDepartamento(DepartamentoCampo.Sigla),

    /**
     * Nome Departamento.
     */
    NomeDepartamento(DepartamentoCampo.Nome),

    /**
     * Código Motivo.
     */
    CodigoMotivo(ConstantesDEPI.TABELA_MOTIVO_ID, MotivoDepositoCampo.Codigo),

    /**
     * Motivo Depósito.
     */
    MotivoDepositoDescricaoBasica(MotivoDepositoCampo.DescricaoBasica),

    /**
     * Banco.
     */
    Banco(ConstantesDEPI.TABELA_CONTA_CORRENTE_AUTORIZADA_BANCO, ContaCorrenteAutorizadaCampo.CodigoBanco),

    /**
     * Banco.
     */
    Agencia(ConstantesDEPI.TABELA_CONTA_CORRENTE_AUTORIZADA_AGENCIA, ContaCorrenteAutorizadaCampo.CodigoAgencia),

    /**
     * Conta Corrente.
     */
    ContaCorrente(ConstantesDEPI.TABELA_CONTA_CORRENTE_AUTORIZADA_CONTA_CORRENTE, ContaCorrenteAutorizadaCampo.CodigoContaCorrente);
    
    private final static List<AssociarMotivoDepositoCampo> CRITERIAS = Arrays.asList(
    		CodigoCia,
    		SiglaDepartamento,
    		NomeDepartamento,
    		MotivoDepositoDescricaoBasica,
    		Banco,
    		Agencia,
    		ContaCorrente);

    private String nome;

    private TipoCampo tipoCampo;

    private boolean cics;

    private int size;

    /**
     * Construtor.
     * @param campo - IEntidadeCampo.
     */
    AssociarMotivoDepositoCampo(IEntidadeCampo campo) {
        this.nome = campo.getNome();
        this.tipoCampo = campo.getTipoCampo();
        this.cics = campo.isCics();
        this.size = campo.getSize();
    }

    /**
     * Construtor.
     * @param nome - String.
     * @param campo - IEntidadeCampo.
     */
    AssociarMotivoDepositoCampo(String nome, IEntidadeCampo campo) {
        this.nome = ConstantesDEPI.SCHEMA_BANCO.concat(ConstantesDEPI.DOT).concat(
            ConstantesDEPI.TABELA_CONTA_CORRENTE_MOTIVO_DEPOSITO).concat(ConstantesDEPI.DOT).concat(nome);
        this.tipoCampo = campo.getTipoCampo();
        this.cics = campo.isCics();
        this.size = campo.getSize();
    }

    /**
     * retorna o tipo
     * @return - retorna o tipo do campo
     */
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
     * Retorna o cics.
     * @return O atributo cics
     */
    @Override
	public boolean isCics() {
        return cics;
    }

    /**
     * retorna o nome
     * @param nome - nome do campo de motivo depósito
     * @return - retorna o nome do campo
     */
    public static AssociarMotivoDepositoCampo obterPorNome(String nome) {

        for (AssociarMotivoDepositoCampo campo : AssociarMotivoDepositoCampo.values()) {
            if (campo.getNome().equals(nome)) {
                return campo;
            }
        }
        return null;
    }

    /**
     * Retorna valores da combo de consulta.
     * @return AssociarMotivoDepositoCampo[].
     */
    public static List<AssociarMotivoDepositoCampo> valuesForCriteria() {
        return CRITERIAS;
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
