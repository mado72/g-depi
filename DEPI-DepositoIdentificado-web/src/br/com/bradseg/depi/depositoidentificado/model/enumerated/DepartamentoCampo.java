package br.com.bradseg.depi.depositoidentificado.model.enumerated;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;

/**
 * Enumerator dos campos de filtro de Departamento
 * @author fabio.pinto
 * @author F�bio Henrique - fabio.almeida@cpmbraxis.com.
 */
public enum DepartamentoCampo implements IEntidadeCampo {

    /**
     * Código
     */
    Codigo(ConstantesDEPI.TABELA_DEPARTAMENTO_ID, "Código Departamento", TipoCampo.NUM, ConstantesDEPI.SIZE_NAO_DEFINIDO),
    /**
     * Sigla
     */
    Sigla("CSGL_DEPTO_DEP", "Sigla Departamento", TipoCampo.ALFA_OBRIG, ConstantesDEPI.SIZE_NAO_DEFINIDO),
    /**
     * Nome
     */
    Nome("IDEPTO_DEP_IDTFD", "Nome Departamento", TipoCampo.ALFA_OBRIG, ConstantesDEPI.SIZE_NAO_DEFINIDO);

    private String nome;

    private String descricao;

    private TipoCampo tipoCampo;

    private int size;

    /**
     * {@inheritDoc}
     */
    public boolean isCics() {
        return false;
    }

    /**
     * Construtor
     * @param nome Campo no banco
     * @param descricao Descricao do campo
     * @param tipoCampo Tipo do campo
     * @param size - int
     */
    DepartamentoCampo(String nome, String descricao, TipoCampo tipoCampo, int size) {
        this.nome = ConstantesDEPI.SCHEMA_BANCO.concat(ConstantesDEPI.DOT).concat(ConstantesDEPI.TABELA_DEPARTAMENTO).concat(
            ConstantesDEPI.DOT).concat(nome);
        this.descricao = descricao;
        this.tipoCampo = tipoCampo;
        this.size = size;
    }

    /**
     * Obter descricao
     * @return descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Obter TipoCampo
     * @return TipoCampo
     */
    public TipoCampo getTipoCampo() {
        return tipoCampo;
    }

    /**
     * Obter nome
     * @return Nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obter por nome.
     * @param nome Campo no banco.
     * @return DepartamentoCampo.
     * @throws DEPIIntegrationException - Integra��o.
     */
    public static DepartamentoCampo obterPorNome(String nome) throws DEPIIntegrationException {
        for (DepartamentoCampo campo : DepartamentoCampo.values()) {
            if (campo.getNome().equals(nome)) {
                return campo;
            }
        }
        throw new DEPIIntegrationException(ConstantesDEPI.ERRO_DEPARTAMENTO_NOME_NAOENCONTRADO, nome);
    }
    
    /**
     * Obter por descrição.
     * @param descricao Descrição do campo.
     * @return DepartamentoCampo Item encontrado.
     * @throws DEPIIntegrationException - Quando não é localizado.
     */
    public static DepartamentoCampo obterPorDescricao(String descricao) throws DEPIIntegrationException {
    	for (DepartamentoCampo campo : DepartamentoCampo.values()) {
    		if (campo.getDescricao().equals(descricao)) {
    			return campo;
    		}
    	}
    	throw new DEPIIntegrationException(ConstantesDEPI.ERRO_DEPARTAMENTO_DESCRICAO_NAOENCONTRADA, descricao);
    }

    /**
     * Especifica o descricao.
     * @param descricao String do descricao a ser setado
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Especifica o nome.
     * @param nome String do nome a ser setado
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Especifica o tipoCampo.
     * @param tipoCampo TipoCampo do tipoCampo a ser setado
     */
    public void setTipoCampo(TipoCampo tipoCampo) {
        this.tipoCampo = tipoCampo;
    }

    /**
     * Retorna valores da combo de consulta.
     * @return DepartamentoCampo[].
     */
    public static List<DepartamentoCampo> valuesForCriteria() {
        List<DepartamentoCampo> list = new ArrayList<DepartamentoCampo>();
        for (DepartamentoCampo campo : DepartamentoCampo.values()) {
            if (!campo.equals(Codigo)) {
                list.add(campo);
            }
        }
        return Collections.unmodifiableList(list);
    }

    /**
     * Retorna o valor do atributo size.
     * @return o valor do atributo size
     */
    public int getSize() {
        return size;
    }
}
