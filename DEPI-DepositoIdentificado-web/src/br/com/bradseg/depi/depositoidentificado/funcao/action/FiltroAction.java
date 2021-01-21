package br.com.bradseg.depi.depositoidentificado.funcao.action;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.model.enumerated.IEntidadeCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoOperacao;
import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;

/**
 * Superclasse para Actions que processam filtros de consulta.
 * 
 * <p>
 * {@link FiltroAction#iniciarFormulario()} prepara os dados para gerar os
 * dropbox de consulta, preparando lista de campos de uma entidade e as
 * operações relacionadas. Além disso, limpa os critérios de consulta, que
 * possam estar na sessão.
 * </p>
 * 
 * <p>
 * {@link FiltroAction#prepararFiltro()} cria uma nova instância do Model para a
 * sessão e limpa a coleção de dados.
 * </p>
 * 
 * <p>
 * {@link FiltroAction#persistirContextoFiltro()} adiciona os dados do model na
 * sessão corrente.
 * </p>
 * 
 * <p>
 * {@link FiltroAction#montarCriterioConsulta(IEntidadeCampo, TipoOperacao, String, String)}
 * processa os dados do filtro para gerar os critérios de filtro para pesquisar
 * a fonte de dados.
 * </p>
 * 
 * 
 * @author Marcelo Damasceno
 * 
 * @param <T>
 *            Tipo do Model deste formulário
 */
@Controller
public abstract class FiltroAction<T extends FiltroConsultarForm<?>> extends BaseModelAction<T> {

	private static final long serialVersionUID = 935947361413242271L;

	private static final Logger LOGGER = LoggerFactory.getLogger(FiltroAction.class);
	
	protected void prepararFiltro() {
		LOGGER.info("Preparando contexto de filtro da consulta");
		
		if (getModel() != null && getModel().getColecaoDados() != null) {
			getModel().setColecaoDados(null);
		}
		
		LOGGER.debug("Removendo formulário do contexto de sessão e criando nova instância");
		String actionName = this.getClass().getSimpleName();
		sessionData.remove(actionName);
		this.getModel().setColecaoDados(null);
		
		this.novaInstanciaModel();
		
		this.getModel().setColecaoDados(null);
	}
	
	protected void persistirContextoFiltro() {
		T model = getModel();
		
		String actionName = this.getClass().getSimpleName();
		sessionData.put(actionName, model);
	}

	protected CriterioConsultaVO montarCriterioConsulta(
			IEntidadeCampo campo, TipoOperacao operacao, String valor,
			String param) {
		String clausula = operacao.formatarClausula(campo.getNome(), param);
		String valorFormatado = operacao.formatarValor(valor);
		return new CriterioConsultaVO(clausula, param, valorFormatado);
	}
	
	/**
	 * Primeiro método chamado da Action. Cria uma nova instância de formulário
	 * na sessão e armazena-a na sessão do usuário.
	 * 
	 * @return {@link com.opensymphony.xwork2.Action#SUCCESS}
	 */
	public String iniciarFormulario() {
		
		this.prepararFiltro();
		
		this.persistirContextoFiltro();
		
		return SUCCESS;
	}
	
	protected abstract void processarCriterios(Collection<String> criterioColecao);
	
	/**
	 * Limpa os resultados da consulta anterior e envia para a lista.
	 * 
	 * @return {@link com.opensymphony.xwork2.Action#SUCCESS}
	 */
	public String refrescar() {
		getModel().setColecaoDados(null);
		processarCriterios(getModel().getCriterios());
		
		return SUCCESS;
	}

}
