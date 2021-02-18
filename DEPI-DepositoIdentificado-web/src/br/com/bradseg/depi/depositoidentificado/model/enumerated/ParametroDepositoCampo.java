package br.com.bradseg.depi.depositoidentificado.model.enumerated;

import java.util.Arrays;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;

/**
 * Classe Enum ParametroDepositoCampo
 * @author igor.almeida
 * @author F�bio Henrique
 */

public enum ParametroDepositoCampo implements IEntidadeCampo {
    /**
     * Código Departamento.
     */
    DepartamentoCodigo(ConstantesDEPI.TABELA_DEPARTAMENTO_ID, DepartamentoCampo.Codigo),

    /**
     * Código Cia
     */
    CodigoCia(ConstantesDEPI.TABELA_COMPANHIA_DEPARTAMENTO_ID, CompanhiaSeguradoraCampo.CodigoCompanhia),

    /**
     * Sigla Departamento.
     */
    DepartamentoSigla(DepartamentoCampo.Sigla),
    /**
     * Nome Departamento.
     */
    DepartamentoNome(DepartamentoCampo.Nome),
    /**
     * Descrição Básica do Motivo
     */
    MotivoDescricaoBasica(MotivoDepositoCampo.DescricaoBasica),
    /**
     * Descrição Detalhada do Motivo
     */
    MotivoDescricaoDetalhada(MotivoDepositoCampo.DescricaoDetalhada);
    
	private final static List<ParametroDepositoCampo> CRITERIAS = Arrays
			.asList(CodigoCia, DepartamentoSigla, DepartamentoNome,
					MotivoDescricaoBasica, MotivoDescricaoDetalhada);

    private String nome;

    private TipoCampo tipoCampo;

    private boolean cics;

    private int size;

  
    /**
     * Construtor.
     * @param nome <String>
     * @param campo - IEntidadeCampo.
     */
    ParametroDepositoCampo(String nome, IEntidadeCampo campo) {
        this.nome = ConstantesDEPI.SCHEMA_BANCO.concat(ConstantesDEPI.DOT).concat(ConstantesDEPI.TABELA_PARAMETRIZACAO).concat(
            ConstantesDEPI.DOT).concat(nome);
        this.tipoCampo = campo.getTipoCampo();
        this.cics = campo.isCics();
        this.size = campo.getSize();
    }

    /**
     * Construtor.
     * @param campo - IEntidadeCampo.
     */
    ParametroDepositoCampo(IEntidadeCampo campo) {
        this.nome = campo.getNome();
        this.tipoCampo = campo.getTipoCampo();
        this.cics = campo.isCics();
        this.size = campo.getSize();
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
     * Altera nome
     * @param nome nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Altera tipoCampo
     * @param tipoCampo tipoCampo
     */
    public void setTipoCampo(TipoCampo tipoCampo) {
        this.tipoCampo = tipoCampo;
    }

    /**
     * retorna o nome
     * @param nome - nome do campo de parâmetro depósito
     * @return retorna o nome campo
     */
    public static ParametroDepositoCampo obterPorNome(String nome) {

        for (ParametroDepositoCampo campo : ParametroDepositoCampo.values()) {
            if (campo.getNome().equals(nome)) {
                return campo;

            }
        }
        return null;
    }

    /**
     * Retorna Tipo Campo
     * @return tipoCampo
     */
    @Override
	public TipoCampo getTipoCampo() {
        return tipoCampo;
    }

    /**
     * Retorna nome
     * @return nome
     */
    @Override
	public String getNome() {
        return nome;
    }

    /**
     * Retorna valores da combo de consulta.
     * @return ParametroDepositoCampo[].
     */
    public static List<ParametroDepositoCampo> valuesForCriteria() {
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
