package br.com.bradseg.depi.depositoidentificado.model.enumerated;

import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;

/**
 * Fornece campos genéricos
 * 
 * @author Fábio Henrique fabio.almeida@cpmbraxis.com
 * @author Marcelo Damasceno (refatorado)
 */
public enum CamposGenericos implements IEntidadeCampo {

	ATIVO("CIND_REG_ATIVO", "Registro Ativo", TipoCampo.ALFA_OBRIG, "S");

	public static final String VALUE_ATIVO = "S";
	public static final String VALUE_INATIVO = "N";

	private String nome;

	private String descricao;

	private TipoCampo tipoCampo;

	private String valorDefault;
    
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
     * @param valorDefault - String.
     */
	CamposGenericos(String nome, String descricao, TipoCampo tipoCampo, String valorDefault) {

		this.nome = nome;
		this.descricao = descricao;
		this.tipoCampo = tipoCampo;
		this.valorDefault = valorDefault;
	}

	/**
     * Obter descricao
     * @return descricao
     */
	public String getDescricao() {
		return descricao;
	}

	/**
     * Obter nome
     * @return Nome
     */
	public String getNome() {
		return nome;
	}
	/**
     * Obter TipoCampo
     * @return TipoCampo
     */
	public TipoCampo getTipoCampo() {
		return tipoCampo;
	}

	/**
     * Especifica o descricao.
     * @param descr String do descricao a ser setado
     */
	public void setDescricao(String descr) {
		this.descricao = descr;
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
     * @param tpCampo TipoCampo do tipoCampo a ser setado
     */
	public void setTipoCampo(TipoCampo tpCampo) {
		this.tipoCampo = tpCampo;
	}

	/**
     * Retorna o valorDefault.
     * @return O atributo valorDefault
     */
	public String getValorDefault() {
		return this.valorDefault;
	}

	/**
     * Especifica o valorDefault.
     * @param vlrDefault String do valorDefault a ser setado
     */
	public void setValorDefault(String vlrDefault) {
		this.valorDefault = vlrDefault;
	}

    /**
     * Retorna o valor do atributo size.
     * @return o valor do atributo size
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Obter por nome.
     * @param nome Campo no banco.
     * @return DepartamentoCampo.
     * @throws DEPIIntegrationException - Integração.
     */
	public static DepartamentoCampo obterPorNome(String nome)
	    throws DEPIIntegrationException {

		for (DepartamentoCampo campo : DepartamentoCampo.values()) {
			if (campo.getNome().equals(nome)) {
				return campo;
			}
		}
		throw new DEPIIntegrationException("N\u00e3o foi possivel localizar o enum: ".concat(nome));
	}
}
