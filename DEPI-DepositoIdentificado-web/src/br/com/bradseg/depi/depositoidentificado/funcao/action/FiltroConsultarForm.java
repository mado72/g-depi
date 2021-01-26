package br.com.bradseg.depi.depositoidentificado.funcao.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.model.cadastro.EntidadeCampoOperacoesFiltro;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.IEntidadeCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoOperacao;
import br.com.bradseg.depi.depositoidentificado.util.FornecedorObjeto;
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
	
	private transient final Funcao<String, IEntidadeCampo> obterEntidade;

	/**
	 * Ação que trata as consultas pelo filtro.
	 * 
	 * @param fornecedor
	 *            Fornecedor da lista de entidades para montar o formulário
	 * @param obterEntidade
	 *            Função para receber o nome do campo ({@link IEntidadeCampo}) e
	 *            devolve a instância referente.
	 */
	public FiltroConsultarForm(FornecedorObjeto<Collection<T>> fornecedor, Funcao<String, IEntidadeCampo> obterEntidade) {
		
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
	
	public List<CriterioConsultaVO> preencherCriterios(Collection<String> criterioArray) {
		clearCriterios();
		
		if (criterioArray == null) {
			return Collections.emptyList();
		}
		
		for (String criterio: criterioArray) {
			this.addCriterio(criterio);
		}
		
		return obterCriteriosConsulta();
	}

	/**
	 * Obtém os critérios da consulta
	 * @return Lista com os critérios
	 */
	public List<CriterioConsultaVO> obterCriteriosConsulta() {
		List<CriterioConsultaVO> criterios = new ArrayList<>();
		
		int paramIdx = 0;
		
		for (String criterio: criteriosInformados) {
			String[] criterioFiltro = criterio.split(";");
			
			IEntidadeCampo campo = this.obterEntidade.apply(criterioFiltro[0]); 
			TipoOperacao operacao = TipoOperacao.valueOf(criterioFiltro[1]);
			String valor = criterioFiltro[2];
			
			String param = "param" + ++paramIdx;
			
			CriterioConsultaVO criterioConsultaVO = montarCriterioConsulta(
					campo, operacao, valor, param);
			criterios.add(criterioConsultaVO);
		}
		
		return criterios;
	}

	private CriterioConsultaVO montarCriterioConsulta(
			IEntidadeCampo campo, TipoOperacao operacao, String valor,
			String param) {
		String clausula = operacao.formatarClausula(campo.getNome(), param);
		String valorFormatado = operacao.formatarValor(valor);
		return new CriterioConsultaVO(clausula, param, valorFormatado);
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
		if (getCriterios() == null) {
			return null;
		}

		try {
			String json = new String(mapper.writeValueAsBytes(getCriterios()), "UTF-8");
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
		return Collections.unmodifiableList(new ArrayList<>(criteriosInformados));
	}
	
	public void clearCriterios() {
		this.criteriosInformados.clear();
	}

	public void addCriterio(String criterio) {
		this.criteriosInformados.add(criterio);
	}
	
	public void addCriterios(String[] criterios) {
		for (String criterio : criterios) {
			this.criteriosInformados.add(criterio);
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
