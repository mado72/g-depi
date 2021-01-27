package br.com.bradseg.depi.depositoidentificado.funcao.action;

import java.util.ArrayList;
import java.util.Collections;
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
 * Os estados possíveis da Action são:
 * </p>
 * <li><b>iniciar</b>: inicia o formulário</li>
 * <li><b>consultar</b>: processa o formulário de filtro</li>
 * <li><b>refrescar</b>: força a renovação da consulta com base nos dados de
 * filtro já editados anteriormente</li>
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
	
	private final T model;
	
	protected abstract CrudHelper<?, ?> getFiltroHelper();
	
	@SuppressWarnings("unchecked")
	public FiltroAction() {
		this.model = (T) getFiltroHelper().criarFiltroModel();
	}
	
	public void validateExecute() {
		clearErrorsAndMessages();
		model.limparDados();
	}
	
	public void validateRetornar() {
		clearErrors();
		model.limparDados();
	}
	
	public void validateRefrescar() {
		clearErrorsAndMessages();
	}
	
	public void validateConsultar() {
		clearErrorsAndMessages();
	}
	
	@Override
	public void validate() {
		LOGGER.debug("Tem erros {}", hasErrors());
	}

	/**
	 * Sobrescreve para retornar INPUT
	 * @return {@link com.opensymphony.xwork2.Action#INPUT}
	 */
	@Override
	public String execute() {
		getModel().setSubtitulo(getText(getFiltroHelper().getChaveTituloConsultar()));
		
		clearErrors();
		
		this.prepararFiltro();
		
		return INPUT;
	}
	
	public String retornar() {
		return execute();
	}
	
	/**
	 * Apresenta o estado atual do formulário e da lista
	 * @return {@link com.opensymphony.xwork2.Action#SUCCESS}
	 */
	public String listar() {
		getModel().setSubtitulo(getText(getFiltroHelper().getChaveTituloListar()));
		
		return SUCCESS;
	}
	
	/**
	 * Processa os dados do filtro.
	 * 
	 * @return {@link com.opensymphony.xwork2.Action#INPUT}, quando consegue
	 *         realizar a consulta. Senão
	 *         {@link com.opensymphony.xwork2.Action#ERROR}
	 */
	public String consultar() {
		try {
			model.setColecaoDados(new ArrayList<>());
			
			T model = getModel();
			
			List<CriterioConsultaVO> criterios = model.obterCriteriosConsulta();

			return processarCriterios(criterios);
		} catch (DEPIIntegrationException e) {
			LOGGER.error("Falha na consulta", e);
			addActionError(e.getMessage());
			
			return ERROR;
		}
	}

	private String processarCriterios(List<CriterioConsultaVO> criterios) {
		try {
			List<?> lista = getFiltroHelper().processarCriterios(criterios);
			model.setColecaoDados(lista);
			
			if (lista == null || lista.isEmpty()) {
				addActionError(getText(ConstantesDEPI.ERRO_SEMRESULTADO));
			}
		} catch (Exception e) {
			getModel().setColecaoDados(Collections.emptyList());
			addActionError(e.getMessage());
		}
		return INPUT;
	}
	
	/**
	 * Limpa os resultados da consulta anterior e envia para a lista.
	 * 
	 * @return {@link com.opensymphony.xwork2.Action#INPUT}
	 */
	public String refrescar() {
		T model = getModel();
		
		if (model.getCriterios() == null || model.getCriterios().isEmpty()) {
			return listar();
		}
		
		model.setColecaoDados(null);
		
		List<CriterioConsultaVO> criterios = model.obterCriteriosConsulta();
		return processarCriterios(criterios);
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
