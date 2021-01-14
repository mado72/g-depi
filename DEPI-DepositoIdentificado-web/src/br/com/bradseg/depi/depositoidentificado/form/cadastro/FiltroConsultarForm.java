package br.com.bradseg.depi.depositoidentificado.form.cadastro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.bradseg.depi.depositoidentificado.model.cadastro.EntidadeCampoOperacoesFiltro;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.IEntidadeCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoOperacao;
import br.com.bradseg.depi.depositoidentificado.util.FornecedorObjeto;
import br.com.bradseg.depi.depositoidentificado.util.json.DepiObjectMapper;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Representa o modelo do formulário de filtro, com entidades alinhadas com as
 * operações.
 * 
 * @author Marcelo Damasceno
 */
public class FiltroConsultarForm<T extends IEntidadeCampo> extends ActionSupport {

	private static final long serialVersionUID = -1473697395894253132L;

	private DepiObjectMapper mapper = new DepiObjectMapper();
	
	private List<EntidadeCampoOperacoesFiltro<T>> parametroFiltroList;
	
	private List<String> criterios;
	
	private List<?> colecaoDados;
	
    /**
     * Todos os filtros usados na página.
     */
    private List<String> campo;

    private List<String> operacao;

    private List<String> valor;

	
	public FiltroConsultarForm(FornecedorObjeto<Collection<T>> fornecedor) {
		Collection<T> lista = fornecedor.get();
		
		parametroFiltroList = new ArrayList<>();
		for (T entidade : lista) {
			List<TipoOperacao> operacoes = TipoOperacao.obterPorTipoCampo(entidade.getTipoCampo());
			
			EntidadeCampoOperacoesFiltro<T> item = new EntidadeCampoOperacoesFiltro<>();
			item.setEntidadeCampo(entidade);
			item.setOperacoes(operacoes);
			
			parametroFiltroList.add(item);
		}
	}

	/**
	 * @return JSON com a estrutura de dados utilizados para o funcionamento do filtro.
	 */
	public String getParametrosFiltroJson() {
		try {
			return new String(mapper.writeValueAsBytes(parametroFiltroList), "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException("Falha ao converter em json", e);
		}
	}

	public String getRecipienteListJson() {
		if (getCriterios() == null)
			return null;
		
		try {
			List<Object> data = new ArrayList<>();
			for (int i = 0; i < getCampo().size(); i++) {
				
				Map<String, String> criterio = new HashMap<>();
				criterio.put("campo", getCampo().get(i));
				criterio.put("operacao", getOperacao().get(i));
				criterio.put("valor", getValor().get(i));
				criterio.put("texto", getCriterios().get(i));
				data.add(criterio);
			}
			return new String(mapper.writeValueAsBytes(data), "UTF-8");
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

	public List<String> getCampo() {
		return campo;
	}

	public void setCampo(List<String> campo) {
		this.campo = campo;
	}

	public List<String> getOperacao() {
		return operacao;
	}

	public void setOperacao(List<String> operacao) {
		this.operacao = operacao;
	}

	public List<String> getValor() {
		return valor;
	}

	public void setValor(List<String> valor) {
		this.valor = valor;
	}
}
