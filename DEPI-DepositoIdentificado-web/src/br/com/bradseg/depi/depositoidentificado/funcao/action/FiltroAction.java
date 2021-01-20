package br.com.bradseg.depi.depositoidentificado.funcao.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;

/**
 * Superclasse para Actions que processam filtros de consulta.
 * 
 * <ul>
 * <p>
 * Os estados possíveis da Action são:</p>
 * <li><b>iniciar</b>: inicia o formulário</li>
 * <li><b>consultar</b>: processa o formulário de filtro</li>
 * <li><b>refrescar</b>: força a renovação da consulta com base nos dados de filtro já editados anteriormente</li>
 * </ul>
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
	
	private T model;
	
	protected abstract CrudHelper<?, ?> getFiltroHelper();
	
	@SuppressWarnings("unchecked")
	public FiltroAction() {
		this.model = (T) getFiltroHelper().criarFiltroModel();
	}

	/**
	 * Sobrescreve para retornar INPUT
	 * @return {@link com.opensymphony.xwork2.Action#INPUT}
	 */
	public String execute() {
		setSubtituloChave(getFiltroHelper().getChaveTituloConsultar());
		
		this.prepararFiltro();
		
		return INPUT;
	}
	
	/**
	 * Apresenta o estado atual do formulário e da lista
	 * @return {@link com.opensymphony.xwork2.Action#SUCCESS}
	 */
	public String listar() {
		setSubtituloChave(getFiltroHelper().getChaveTituloListar());
		
		return SUCCESS;
	}
	
	/**
	 * Processa os dados do filtro.
	 * 
	 * @return {@link com.opensymphony.xwork2.Action#INPUT}, quando consegue realizar a consulta. Senão {@link com.opensymphony.xwork2.Action#ERROR}
	 */
	public String consultar() {
		try {
			List<String> criteriosCol = Arrays.asList(request.getParameterValues("criterio"));
			
			T model = getModel();
			
			List<CriterioConsultaVO> criterios = new ArrayList<>(model
					.preencherCriterios(criteriosCol));

			List<?> lista = getFiltroHelper().processarCriterios(criterios);
			model.setColecaoDados(lista);
			
			if (lista == null || lista.isEmpty()) {
				String message = super.getText(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO);
				addActionMessage(message);
			}
			
			return INPUT;
		} catch (DEPIIntegrationException e) {
			LOGGER.error("Falha na consulta", e);
			addActionError(e.getMessage());
			
			return ERROR;
		}
	}
	
	/**
	 * Limpa os resultados da consulta anterior e envia para a lista.
	 * 
	 * @return {@link com.opensymphony.xwork2.Action#INPUT}
	 */
	public String refrescar() {
		T model = getModel();
		model.setColecaoDados(null);
		
		List<CriterioConsultaVO> criterios = model.preencherCriterios(model
				.getCriterios());
		List<?> lista = getFiltroHelper().processarCriterios(criterios);
		model.setColecaoDados(lista);
		
		return INPUT;
	}
	
	@Override
	public final T getModel() {
		return model;
	}
	
	protected String getSimpleName() {
		String simpleName = this.getClass().getSimpleName().replaceAll("\\$\\$Enhancer.*$", "");
		LOGGER.debug("SimpleName: {}", simpleName);
		return simpleName;
	}
	
	protected String getFormName() {
		String formName = getSimpleName() + "_FORM";
		LOGGER.debug("FormName: {}", formName);
		return formName;
	}
	
	private void prepararFiltro() {
		LOGGER.info("Preparando contexto de filtro da consulta");
		
		if (model != null && model.getColecaoDados() != null) {
			model.setColecaoDados(null);
		}
		
		this.getModel().setColecaoDados(null);

		if (this.getModel().getCriterios() != null) {
			this.getModel().clearCriterios();
		}
	}

}
