package br.com.bradseg.depi.depositoidentificado.model.enumerated;

import java.util.Arrays;
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
    Codigo(ConstantesDEPI.TABELA_DEPARTAMENTO_ID, TipoCampo.NUM, ConstantesDEPI.SIZE_NAO_DEFINIDO),
    /**
     * Sigla
     */
    Sigla("CSGL_DEPTO_DEP", TipoCampo.ALFA_OBRIG, 3),
    /**
     * Nome
     */
    Nome("IDEPTO_DEP_IDTFD", TipoCampo.ALFA_OBRIG, ConstantesDEPI.SIZE_NAO_DEFINIDO), 
    
    /**
     * Ativo 
     */
    Ativo("CIND_REG_ATIVO", TipoCampo.ALFA_OBRIG, 1);

    private String nome;

    private TipoCampo tipoCampo;

    private int size;
    
    private final static List<DepartamentoCampo> CRITERIAS = Arrays.asList(Sigla, Nome);

    /**
     * {@inheritDoc}
     */
    @Override
	public boolean isCics() {
        return false;
    }

    /**
     * Construtor
     * @param nome Campo no banco
     * @param tipoCampo Tipo do campo
     * @param size - int
     */
    DepartamentoCampo(String nome, TipoCampo tipoCampo, int size) {
        this.nome = ConstantesDEPI.SCHEMA_BANCO.concat(ConstantesDEPI.DOT).concat(ConstantesDEPI.TABELA_DEPARTAMENTO).concat(
            ConstantesDEPI.DOT).concat(nome);
        this.tipoCampo = tipoCampo;
        this.size = size;
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
     * Obter por nome.
     * @param nome Campo no banco.
     * @return DepartamentoCampo.
     * @throws DEPIIntegrationException - Integração.
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
     * Retorna valores da combo de consulta.
     * @return DepartamentoCampo[].
     */
    public static List<DepartamentoCampo> valuesForCriteria() {
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
