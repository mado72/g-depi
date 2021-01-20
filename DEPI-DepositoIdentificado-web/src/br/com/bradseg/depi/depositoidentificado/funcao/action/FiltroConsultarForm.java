package br.com.bradseg.depi.depositoidentificado.funcao.action;

import java.io.IOException;
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

import com.opensymphony.xwork2.ActionSupport;

/**
 * Representa o modelo do formulário de filtro, com entidade alinhadas com as
 * operações disponíveis, elegíveis por duas dropbox: a primeira, para definir a
 * entidade e a segunda a operação (cláusula de consulta).
 * 
 * @author Marcelo Damasceno
 *
 * @param <T> Tipo que implementa {@link IEntidadeCampo}
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
	
	public List<CriterioConsultaVO> preencherCriterios(String[] criterioArray) {
		clearCriterios();
		
		if (criterioArray == null) {
			return Collections.emptyList();
		}
		
		List<CriterioConsultaVO> criterios = new ArrayList<>();
		
		int paramIdx = 0;
		
		for (String criterio: criterioArray) {
			this.addCriterio(criterio);
			
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

	public List<String> getCriterios() {
		return Collections.unmodifiableList(criteriosInformados);
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

	public List<?> getColecaoDados() {
		return colecaoDados;
	}

	public void setColecaoDados(List<?> colecaoDados) {
		this.colecaoDados = colecaoDados;
	}
}
