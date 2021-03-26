package br.com.bradseg.depi.depositoidentificado.model.enumerated;

import java.util.Arrays;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;

/**
 * Enumerator dos campos de filtro de Deposito
 */
public enum DepositoCampo implements IEntidadeCampo {

    /**
     * Cia.
     */
	CodigoAutorizador(ConstantesDEPI.TABELA_DEPOSITO_IDENTIFICADO_ID,
			TipoCampo.NUM, false, 5),
			
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
    ContaCorrente(ConstantesDEPI.TABELA_CONTA_CORRENTE_AUTORIZADA_CONTA_CORRENTE, ContaCorrenteAutorizadaCampo.CodigoContaCorrente),

    /**
     * Tipo Recebimento.
     */
	TipoRecebimento(
			ConstantesDEPI.TABELA_DEPOSITO_IDENTIFICADO_TIPO_GRUPO_RECEBIMENTO,
			TipoCampo.ALFA_OBRIG, false, 1);

    private String nome;

    private TipoCampo tipoCampo;

    private boolean cics;

    private int size;

	private final static List<DepositoCampo> CRITERIAS = Arrays.asList(
			CodigoAutorizador, CodigoCia, SiglaDepartamento, NomeDepartamento,
			MotivoDepositoDescricaoBasica, Banco, Agencia, ContaCorrente);
    
    /**
     * Retorna o valor do atributo size.
     * @return o valor do atributo size
     */
    @Override
	public int getSize() {
        return size;
    }

    /**
     * Especifica o valor do atributo cics.
     * @param cics - boolean do cics a ser configurado.
     */
    public void setCics(boolean cics) {
        this.cics = cics;
    }

    /**
     * {@inheritDoc}
     */
    @Override
	public boolean isCics() {
        return cics;
    }

    /**
     * Construtor.
     * @param campo - IEntidadeCampo.
     */
    DepositoCampo(IEntidadeCampo campo) {
        this.nome = campo.getNome();
        this.tipoCampo = campo.getTipoCampo();
        this.cics = campo.isCics();
        this.size = campo.getSize();
    }

    /**
     * Construtor.
     * @param nome - String - nome do campo
     * @param campo - IEntidadeCampo.
     */
    DepositoCampo(String nome, IEntidadeCampo campo) {
        this.nome = ConstantesDEPI.SCHEMA_BANCO.concat(ConstantesDEPI.DOT).concat(ConstantesDEPI.TABELA_DEPOSITO_IDENTIFICADO)
            .concat(ConstantesDEPI.DOT).concat(nome);
        this.tipoCampo = campo.getTipoCampo();
        this.cics = campo.isCics();
        this.size = campo.getSize();
    }

    /**
     * @param nome - String - nome do campo
     * @param tipoCampo - String - tipo do campo.
     * @param cics - boolean.
     * @param size - int
     */
    DepositoCampo(String nome, TipoCampo tipoCampo, boolean cics, int size) {
        this.nome = ConstantesDEPI.SCHEMA_BANCO.concat(ConstantesDEPI.DOT).concat(ConstantesDEPI.TABELA_DEPOSITO_IDENTIFICADO)
            .concat(ConstantesDEPI.DOT).concat(nome);
        this.tipoCampo = tipoCampo;
        this.cics = cics;
        this.size = size;
    }

    /**
     * Obter por nome
     * @param nome Campo no banco
     * @return DepositoCampo
     */
    public static DepositoCampo obterPorNome(String nome) {

        for (DepositoCampo campo : DepositoCampo.values()) {
            if (campo.getNome().equals(nome)) {
                return campo;
            }
        }
        return null;
    }
    
    /**
     * Retorna valores da combo de consulta.
     * @return DepositoCampo[].
     */
    public static List<DepositoCampo> valuesForCriteria() {
    	return CRITERIAS;
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
     * metodo que retorna o tipoCampo
     * @return o tipoCampo
     */
    @Override
	public TipoCampo getTipoCampo() {
        return tipoCampo;
    }

}
