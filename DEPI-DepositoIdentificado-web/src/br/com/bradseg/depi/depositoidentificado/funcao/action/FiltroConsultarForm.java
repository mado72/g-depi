package br.com.bradseg.depi.depositoidentificado.funcao.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.model.cadastro.EntidadeCampoOperacoesFiltro;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.IEntidadeCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoOperacao;
import br.com.bradseg.depi.depositoidentificado.util.FornecedorObjeto;
import br.com.bradseg.depi.depositoidentificado.util.json.DepiObjectMapper;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Representa o modelo do formulário de filtro, com entidade alinhadas com as
 * operações disponíveis, elegíveis por duas dropbox: a primeira, para definir a
 * entidade e a segunda a operação (cláusula de consulta).
 * 
 * @author Marcelo Damasceno
 */
public class FiltroConsultarForm<T extends IEntidadeCampo> extends
		ActionSupport {

	private static final long serialVersionUID = -1473697395894253132L;

	/**
	 * Usado internamente para montar um JSON usado na passagem de valores para
	 * os dropbox.
	 */
	private DepiObjectMapper mapper = new DepiObjectMapper();

	/**
	 * Possui a lista de entidades relacionadas com as possíveis operações de
	 * consulta
	 */
	private List<EntidadeCampoOperacoesFiltro<T>> parametroFiltroList;

	/**
	 * Armazena os critérios enviados (selecionados) pelo usuário
	 */
	private List<String> criterios;

	/**
	 * Armazena os dados, resultado da consulta.
	 */
	private List<?> colecaoDados;

	/**
	 * @param fornecedor
	 */
	public FiltroConsultarForm(FornecedorObjeto<Collection<T>> fornecedor) {
		Collection<T> lista = fornecedor.get();

		parametroFiltroList = new ArrayList<>();
		for (T entidade : lista) {
			List<TipoOperacao> operacoes = TipoOperacao
					.obterPorTipoCampo(entidade.getTipoCampo());

			EntidadeCampoOperacoesFiltro<T> item = new EntidadeCampoOperacoesFiltro<>();
			item.setEntidadeCampo(entidade);
			item.setOperacoes(operacoes);

			parametroFiltroList.add(item);
		}
	}

	/**
	 * @return JSON com a estrutura de dados utilizados para o funcionamento do
	 *         filtro.
	 */
	public String getParametrosFiltroJson() {
		try {
			String json = new String(mapper.writeValueAsBytes(parametroFiltroList),
					"UTF-8");
			return json;
		} catch (IOException e) {
			throw new RuntimeException("Falha ao converter em json", e);
		}
	}

	public String getRecipienteListJson() {
		if (getCriterios() == null)
			return null;

		try {
			String json = new String(mapper.writeValueAsBytes(getCriterios()), "UTF-8");
			return json;
		} catch (IOException e) {
			throw new RuntimeException("Falha ao converter em json", e);
		}
	}

	public List<String> getCriterios() {
		return criterios;
	}

	public void setCriterios(List<String> criterios) {
		this.criterios = criterios;
	}

	public List<?> getColecaoDados() {
		return colecaoDados;
	}

	public void setColecaoDados(List<?> colecaoDados) {
		this.colecaoDados = colecaoDados;
	}
}
