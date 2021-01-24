/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.model.enumerated;

import java.util.ArrayList;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;

/**
 * Enumerator dos campos de filtro de DepartamentoCompanhia
 * @author  Marcelo Damasceno
 */
public enum DepartamentoCompanhiaCampo implements IEntidadeCampo {

    /**
     * Código Departamento.
     */
    CodigoDepartamento(ConstantesDEPI.TABELA_DEPARTAMENTO_ID, DepartamentoCampo.Codigo),
    /**
     * Sigla Departamento.
     */
    Sigla(DepartamentoCampo.Sigla),
    /**
     * Nome Departamento.
     */
    NomeDepartamento(DepartamentoCampo.Nome),
    /**	
     * Código Cia.
     */
    CodigoCompanhia(ConstantesDEPI.TABELA_COMPANHIA_DEPARTAMENTO_ID, CompanhiaSeguradoraCampo.CodigoCompanhia);

    private String nome;

    private TipoCampo tipoCampo;

    private boolean cics;

    private int size;

    /**
     * Construtor.
     * @param nome - String.
     * @param campo - IEntidadeCampo.
     */
    DepartamentoCompanhiaCampo(String nome, IEntidadeCampo campo) {
        this.nome = ConstantesDEPI.SCHEMA_BANCO.concat(ConstantesDEPI.DOT).concat(ConstantesDEPI.TABELA_COMPANHIA_DEPARTAMENTO)
            .concat(ConstantesDEPI.DOT).concat(nome);

        this.tipoCampo = campo.getTipoCampo();
        this.cics = campo.isCics();
        this.size = campo.getSize();
    }

    /**
     * Construtor.
     * @param campo - IEntidadeCampo.
     */
    DepartamentoCompanhiaCampo(IEntidadeCampo campo) {
        this.nome = campo.getNome();
        this.tipoCampo = campo.getTipoCampo();
        this.cics = campo.isCics();
        this.size = campo.getSize();
    }

    /**
     * Obter por nome
     * @param nome Campo no banco
     * @return DepartamentoCompanhiaCampo
     */
    public static DepartamentoCompanhiaCampo obterPorNome(String nome) {
        for (DepartamentoCompanhiaCampo campo : DepartamentoCompanhiaCampo.values()) {
            if (campo.getNome().equals(nome)) {
                return campo;
            }
        }
        return null;
    }

    @Override
    public boolean isCics() {
        return cics;
    }

    @Override
    public String getNome() {
        return nome;
    }
    
    @Override
    public TipoCampo getTipoCampo() {
    	return tipoCampo;
    }

    @Override
    public int getSize() {
        return size;
    }

    /**
     * define o nome a ser configurado
     * @param nome o nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define tipoCampo
     * @param tipoCampo o tipoCampo a ser configurado
     */
    public void setTipoCampo(TipoCampo tipoCampo) {
        this.tipoCampo = tipoCampo;
    }

    /**
     * configura valor que diz se pertence ao cics
     * @param cics o cics a ser configurado
     */
    public void setCics(boolean cics) {
        this.cics = cics;
    }

    /**
     * Retorna valores da combo de consulta.
     * @return DepartamentoCampo[].
     */
    public static DepartamentoCompanhiaCampo[] valuesForCriteria() {
        List<DepartamentoCompanhiaCampo> list = new ArrayList<DepartamentoCompanhiaCampo>();
        for (DepartamentoCompanhiaCampo campo : DepartamentoCompanhiaCampo.values()) {
            if (!campo.equals(CodigoDepartamento)) {
                list.add(campo);
            }
        }
        DepartamentoCompanhiaCampo[] deps = new DepartamentoCompanhiaCampo[list.size()];
        for (int i = 0; i < deps.length; i++) {
            deps[i] = list.get(i);
        }
        return deps;
    }
}
