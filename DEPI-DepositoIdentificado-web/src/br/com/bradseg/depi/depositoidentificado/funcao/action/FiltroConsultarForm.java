package br.com.bradseg.depi.depositoidentificado.funcao.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.model.enumerated.IEntidadeCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoOperacao;
import br.com.bradseg.depi.depositoidentificado.util.Fornecedor;
import br.com.bradseg.depi.depositoidentificado.util.Funcao;
import br.com.bradseg.depi.depositoidentificado.util.json.DepiObjectMapper;
import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;

/**
 * Representa o modelo do formulário de filtro, com entidade alinhadas com as
 * operações disponíveis, elegíveis por duas dropbox: a primeira, para definir a
 * entidade e a segunda a operação (cláusula de consulta).
 * 
 * @author Marcelo Damasceno
 *
 * @param <T> Tipo que implementa {@link IEntidadeCampo}
 */
public class FiltroConsultarForm<T extends IEntidadeCampo> implements Serializable {

	private static final long serialVersionUID = -1473697395894253132L;
	
	private String subtitulo;
	
	private String namespaceEditar;

	/**
	 * Usado internamente para montar um JSON usado na passagem de valores para
	 * os dropbox.
	 */
	private final DepiObjectMapper mapper = new DepiObjectMapper();

	/**
	 * Possui a lista de entidades relacionadas com as possíveis operações de
	 * consulta
	 */
	private final List<EntidadeCampoOperacoesFiltro<T>> parametroFiltroList;

	/**
	 * Armazena os critérios enviados (selecionados) pelo usuário
	 */
	private List<String> criteriosInformados = new ArrayList<>();

	/**
	 * Armazena os dados, resultado da consulta.
	 */
	private List<?> colecaoDados;
	
	private transient final Funcao<String, T> obterEntidade;

	/**
	 * Ação que trata as consultas pelo filtro.
	 * 
	 * @param fornecedor
	 *            Fornecedor da lista de entidades para montar o formulário
	 * @param obterEntidade
	 *            Função para receber o nome do campo ({@link IEntidadeCampo}) e
	 *            devolve a instância referente.
	 */
	public FiltroConsultarForm(Fornecedor<Collection<T>> fornecedor, Funcao<String, T> obterEntidade) {
		
		this.obterEntidade = obterEntidade;
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
	
	public void limparDados() {
		setNamespaceEditar(null);
		setColecaoDados(null);
//		setCriteriosInformados(null);
	}
	
	public String getSubtitulo() {
		return subtitulo;
	}
	
	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}
	
	public String getNamespaceEditar() {
		return namespaceEditar;
	}
	
	public void setNamespaceEditar(String namespaceEditar) {
		this.namespaceEditar = namespaceEditar;
	}

	/**
	 * Obtém os critérios da consulta
	 * @return Lista com os critérios
	 */
	public List<CriterioConsultaVO<T>> obterCriteriosConsulta() {
		if (criteriosInformados == null) {
			return Collections.emptyList();
		}
		
		List<CriterioConsultaVO<T>> criterios = new ArrayList<>();
		
		int paramIdx = 0;
		
		for (String criterio: criteriosInformados) {
			String[] criterioFiltro = criterio.split(";");
			
			// Apenas processa quando há 3 argumentos.
			if (criterioFiltro.length == 3) {
				
				T campo = this.obterEntidade.apply(criterioFiltro[0]); 
				TipoOperacao operacao = TipoOperacao.valueOf(criterioFiltro[1]);
				String valor = criterioFiltro[2];
				
				String param = "param" + ++paramIdx;
				
				CriterioConsultaVO<T> criterioConsultaVO = new CriterioConsultaVO<T>(
						campo, operacao, valor, param);
				criterios.add(criterioConsultaVO);
				
			}
		}
		
		return criterios;
	}

	/**
	 * Fornece um JSON que representa a estrutura de dados do filtro
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

	/**
	 * Fornece Json que representa a lista de critérios defnidos de um filtro
	 * @return Json
	 */
	public String getRecipienteListJson() {
		List<String> criterios = getCriterios();
		if (criterios == null) {
			return null;
		}
		criterios = new ArrayList<>(criterios);
		
		for (Iterator<String> iterator = criterios.iterator(); iterator.hasNext();) {
			String[] string = iterator.next().split(";");
			if (string.length != 3) {
				iterator.remove();
			}
		}

		try {
			String json = new String(mapper.writeValueAsBytes(criterios), "UTF-8");
			return json;
		} catch (IOException e) {
			throw new RuntimeException("Falha ao converter em json", e);
		}
	}

	/**
	 * Devolve uma cópia dos critérios definidos na consulta
	 * @return critérios.
	 */
	public List<String> getCriterios() {
		if (criteriosInformados == null) {
			return Collections.emptyList();
		}
		return Collections.unmodifiableList(new ArrayList<>(criteriosInformados));
	}
	
	public void clearCriterios() {
		if (this.criteriosInformados != null) {
			this.criteriosInformados.clear();
		}
	}

	public void setCriteriosInformados(List<String> criteriosInformados) {
		this.criteriosInformados = criteriosInformados;
	}

	public List<?> getColecaoDados() {
		return colecaoDados;
	}

	public void setColecaoDados(List<?> colecaoDados) {
		this.colecaoDados = colecaoDados;
	}
}
