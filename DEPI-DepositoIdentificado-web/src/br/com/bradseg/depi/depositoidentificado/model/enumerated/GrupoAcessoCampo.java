package br.com.bradseg.depi.depositoidentificado.model.enumerated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;

/**
 * Enumerator dos campos de filtro de GrupoAcesso
 */
public enum GrupoAcessoCampo implements IEntidadeCampo {

	/**
	 * Grupo Acesso Campo.
	 */
	Codigo(ConstantesDEPI.TABELA_GRUPO_ID, TipoCampo.NUM, false, 4),

	/**
	 * Código Cia.
	 */
	CodigoCia(ConstantesDEPI.TABELA_COMPANHIA_DEPARTAMENTO_ID,
			CompanhiaSeguradoraCampo.CodigoCompanhia),

	/**
	 * Código Departamento. DepartamentoCompanhiaCampo é o relacionamento mais
	 * próximo.
	 */
	CodigoDepartamento(ConstantesDEPI.TABELA_DEPARTAMENTO_ID,
			DepartamentoCampo.Codigo),

	/**
	 * Sigla Departamento.
	 */
	SiglaDepartamento(DepartamentoCampo.Sigla),

	/**
	 * Nome Departamento
	 */
	NomeDepartamento(DepartamentoCampo.Nome),

	/**
	 * Matricula Funcionário.
	 */
	MatriculaFuncionario(FuncionarioCampo.Matricula),

	/**
	 * Nome Funcionário.
	 */
	NomeFuncionario(FuncionarioCampo.Nome);

	private String nome;

	private TipoCampo tipoCampo;

	private boolean cics;

	private int size;

	/**
	 * Retorna o valor do atributo size.
	 * 
	 * @return o valor do atributo size
	 */
	@Override
	public int getSize() {
		return size;
	}

	/**
	 * Construtor.
	 * 
	 * @param campo
	 *            - IEntidadeCampo.
	 */
	GrupoAcessoCampo(IEntidadeCampo campo) {
		this.nome = campo.getNome();
		this.tipoCampo = campo.getTipoCampo();
		this.cics = campo.isCics();
		this.size = campo.getSize();
	}

	/**
	 * Construtor.
	 * 
	 * @param nome
	 *            - String.
	 * @param campo
	 *            - IEntidadeCampo.
	 */
	GrupoAcessoCampo(String nome, IEntidadeCampo campo) {
		this.nome = ConstantesDEPI.SCHEMA_BANCO.concat(ConstantesDEPI.DOT)
				.concat(ConstantesDEPI.TABELA_GRUPO).concat(ConstantesDEPI.DOT)
				.concat(nome);
		this.tipoCampo = campo.getTipoCampo();
		this.cics = campo.isCics();
		this.size = campo.getSize();
	}

	/**
	 * @param nome
	 *            - String.
	 * @param tipoCampo
	 *            - String.
	 * @param cics
	 *            - É Cics?
	 * @param size
	 *            - int
	 */
	GrupoAcessoCampo(String nome, TipoCampo tipoCampo, boolean cics, int size) {
		this.nome = ConstantesDEPI.SCHEMA_BANCO.concat(ConstantesDEPI.DOT)
				.concat(ConstantesDEPI.TABELA_GRUPO).concat(ConstantesDEPI.DOT)
				.concat(nome);
		this.tipoCampo = tipoCampo;
		this.cics = cics;
		this.size = size;
	}

	/**
	 * Obter TipoCampo
	 * 
	 * @return TipoCampo
	 */
	@Override
	public TipoCampo getTipoCampo() {
		return tipoCampo;
	}

	/**
	 * Obter nome
	 * 
	 * @return Nome
	 */
	@Override
	public String getNome() {
		return nome;
	}

	/**
	 * retorna valor que diz se pertence ao cics
	 * 
	 * @return o cics
	 */
	@Override
	public boolean isCics() {
		return cics;
	}

	/**
	 * Obter por nome
	 * 
	 * @param nome
	 *            Campo no banco
	 * @return GrupoAcessoCampo
	 */
	public static GrupoAcessoCampo obterPorNome(String nome) {
		for (GrupoAcessoCampo campo : GrupoAcessoCampo.values()) {
			if (campo.getNome().equals(nome)) {
				return campo;
			}
		}
		return null;
	}

	/**
	 * Retorna valores da combo de consulta.
	 * 
	 * @return GrupoAcessoCampo[].
	 */
	public static List<GrupoAcessoCampo> valuesForCriteria() {
		List<GrupoAcessoCampo> list = new ArrayList<>(
				Arrays.asList(GrupoAcessoCampo.values()));
		list.remove(CodigoDepartamento);
		return Collections.unmodifiableList(list);
	}

}
