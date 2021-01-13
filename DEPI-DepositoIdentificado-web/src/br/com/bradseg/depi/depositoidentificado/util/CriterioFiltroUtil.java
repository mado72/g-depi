package br.com.bradseg.depi.depositoidentificado.util;

import java.text.MessageFormat;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.model.enumerated.CamposGenericos;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.IEntidadeCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoOperacao;

/**
 * Classe de VO de filtro para os critérios
 * @refactoredBy Marcelo Damasceno
 */
public class CriterioFiltroUtil  {
    
	private static final long serialVersionUID = 6136528660463172937L;

	private IEntidadeCampo campo;

	private TipoOperacao operacao;

	private String valor;

	private boolean cics;

	private boolean grupo;

	private List<CriterioFiltroUtil> criterios;

	/**
     * Retorna os crit�rios de pesquisa usando o operador and.
     * @return StringBuilder.
     */
	public StringBuilder getCriterioWithOperatorAnd() {
		StringBuilder sb = new StringBuilder();
		if (this.criterios != null) {
			for (CriterioFiltroUtil criterio : this.criterios) {
				sb.append(" AND ").append(criterio.getClausula());
			}

		}
		return sb;
	}

	/**
     * Construtor.
     */
	public CriterioFiltroUtil() {
		super();
	}

	/**
     * Construtor.
     * @param list - List<CriterioFiltroUtil>.
     */
	public CriterioFiltroUtil(List<CriterioFiltroUtil> list) {
		setCriterios(list);
	}

	/**
     * Obter criterios
     * @return Lista de CriterioFiltroUtil
     */
	public List<CriterioFiltroUtil> getCriterios() {
		return criterios;
	}

	/**
     * Atribuir lista de CriterioFiltroUtil
     * @param criterios Lista de CriterioFiltroUtil
     */
	public void setCriterios(List<CriterioFiltroUtil> criterios) {
		this.criterios = criterios;
	}

	/**
     * Retornao o criterio ativo.
     * @return CriterioFiltroUtil.
     */
	public static CriterioFiltroUtil getDefaultCriterioAtivo() {
		CriterioFiltroUtil criterio = new CriterioFiltroUtil();
		criterio.setCampo(CamposGenericos.ATIVO);
		criterio.setOperacao(TipoOperacao.IgualAlfanumericoObrigatorio);
		criterio.setValor(CamposGenericos.VALUE_ATIVO);
		return criterio;
	}

	/**
     * Retornao o criterio ativo.
     * @return CriterioFiltroUtil.
     */
	public static CriterioFiltroUtil getDefaultCriterioInativo() {
		CriterioFiltroUtil criterio = new CriterioFiltroUtil();
		criterio.setCampo(CamposGenericos.ATIVO);
		criterio.setOperacao(TipoOperacao.IgualAlfanumericoObrigatorio);
		criterio.setValor(CamposGenericos.VALUE_INATIVO);
		return criterio;
	}

	/**
     * retorna o cics
     * @return o cics
     */
	public boolean isCics() {
		return cics;
	}

	/**
     * Obter campo
     * @return IEntidadeCampo
     */
	public IEntidadeCampo getCampo() {
		return campo;
	}

	/**
     * Atribuir IEntidadeCampo
     * @param campo IEntidadeCampo
     */
	public void setCampo(IEntidadeCampo campo) {
		this.campo = campo;
		this.setCics(campo.isCics());
	}

	/**
     * Obter valor
     * @return Valor
     */
	public String getValor() {
		return valor;
	}

	/**
     * Atribuir valor
     * @param valor Valor
     */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
     * Atribuir valor
     * @param valor Valor
     */
	public void setValor(int valor) {
		this.valor = String.valueOf(valor);
	}

	/**
     * Atribuir valor
     * @param valor Valor
     */
	public void setValor(long valor) {
		this.valor = String.valueOf(valor);
	}

	/**
     * Atribuir valor
     * @param valor Valor
     */
	public void setValor(float valor) {
		this.valor = String.valueOf(valor);
	}

	/**
     * Atribuir valor
     * @param valor Valor
     */
	public void setValor(double valor) {
		this.valor = String.valueOf(valor);
	}

	/**
     * Obter TipoOperacao
     * @return TipoOperacao
     */
	public TipoOperacao getOperacao() {
		return operacao;
	}

	/**
     * Atribuir TipoOperacao
     * @param operacao TipoOperacao
     */
	public void setOperacao(TipoOperacao operacao) {
		this.operacao = operacao;
	}

	/**
     * Obter clausula
     * @return Clausula composta
     */
	public String getClausula() {
		String valor = "";
		if (getValor() != null) {
			valor = getValor().toUpperCase();
		}
		return MessageFormat.format(getOperacao().getClausula(), getCampo().getNome(), valor);
	}

	/**
     * Retorna o serialVersionUID.
     * @return O atributo serialVersionUID
     */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
     * Retorna o grupo.
     * @return O atributo grupo
     */
	public boolean isGrupo() {
		return grupo;
	}

	/**
     * Especifica o grupo.
     * @param grupo boolean do grupo a ser setado
     */
	public void setGrupo(boolean grupo) {
		this.grupo = grupo;
	}

	/**
     * Especifica o cics.
     * @param cics boolean do cics a ser setado
     */
	public void setCics(boolean cics) {
		this.cics = cics;
	}
}
