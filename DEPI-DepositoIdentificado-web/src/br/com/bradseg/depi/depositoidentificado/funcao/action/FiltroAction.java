package br.com.bradseg.depi.depositoidentificado.funcao.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIBusinessException;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.IEntidadeCampo;
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
 * @param <C>
 *            Tipo do Campo manipulado pelo filtro
 * @param <T>
 *            Tipo do Model deste formulário
 */
@Controller
@Scope("request")
public abstract class FiltroAction<C extends IEntidadeCampo, T extends FiltroConsultarForm<C>> extends BaseModelAction<T> {

	private static final long serialVersionUID = 935947361413242271L;

	private static final Logger LOGGER = LoggerFactory.getLogger(FiltroAction.class);
	
	private final T model;
	
	protected abstract CrudHelper<C, ?, ?> getFiltroHelper();
	
	private boolean consultado;

	@SuppressWarnings("unchecked")
	public FiltroAction() {
		this.model = (T) getFiltroHelper().criarFiltroModel();
	}
	
	/**
	 * Limpa as mensagens gerais e de erros e reseta o formulário
	 */
	public void validateExecute() {
		LOGGER.info("Validando execute");
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(dumpErros());
		}
		clearErrorsAndMessages();
		model.limparDados();
	}
	
	/**
	 * Limpa as mensagens de erro e reseta o formulário
	 */
	public void validateRetornar() {
		LOGGER.info("Validando retornar");
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(dumpErros());
		}
		clearErrors();
		model.limparDados();
	}
	
	/**
	 * Limpa as mensagens da ação e de erro
	 */
	public void validateRefrescar() {
		LOGGER.info("Validando refrescar");
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(dumpErros());
		}
		clearErrorsAndMessages();
	}
	
	/**
	 * Limpa as mensagens da ação e de erro e valida os critérios
	 */
	public void validateConsultar() {
		LOGGER.info("Validando consultar");
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(dumpErros());
		}
		clearErrorsAndMessages();
		for (CriterioConsultaVO<C> criterio : model.obterCriteriosConsulta()) {
			validarCriterio(criterio);
		}
	}
	
	/**
	 * Chama {@link #validateConsultar()}
	 */
	public void validateJson() {
		LOGGER.info("Validando json");
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(dumpErros());
		}
		validateConsultar();
	}
	
	/**
	 * Método que deve ser sobrescrito pelas consultas para validar os critérios
	 * utilizados na consulta
	 * 
	 * @param criterio
	 *            Critérios da consulta
	 */
	protected void validarCriterio(CriterioConsultaVO<C> criterio) {
		String valor = criterio.getValor();
		C campo = criterio.getCampo();
		if (valor == null) {
			addFieldError("criterio", getText(ConstantesDEPI.Geral.ERRO_CRITERIO_INVALIDO, new String[]{
				getText(campo.name()),
				getText(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, new String[]{"Valor"})
			}));
		}
		else if (campo.getSize() > 0 && valor.length() > campo.getSize()) {
			addFieldError("criterio", getText(ConstantesDEPI.Geral.ERRO_CAMPO_EXCESSO, new String[]{
					getText(campo),
					String.valueOf(campo.getSize())
			}));
		}
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate() {
		LOGGER.info("Validate()");
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(dumpErros());
		}
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
		clearErrorsAndMessages();
		return realizarConsulta();
	}

	/**
	 * Realiza a consulta com base nos critérios. Chama internamente o método
	 * {@link #processarCriterios(List)}.
	 * 
	 * @return Caso ocorra algum erro, retorna <code>error</code>, senão
	 *         <code>success</code>
	 */
	protected String realizarConsulta() {
		try {
			model.setColecaoDados(new ArrayList<>());
			
			int codUsuario = getCodUsuarioLogado();
			
			List<CriterioConsultaVO<C>> criterios = model.obterCriteriosConsulta();

			return processarCriterios(codUsuario, criterios);
		} catch (DEPIIntegrationException e) {
			LOGGER.error("Falha na consulta", e);
			addActionError(e.getMessage());
			
			return ERROR;
		}
	}

	private String processarCriterios(int codUsuario, List<CriterioConsultaVO<C>> criterios) {
		
		consultado = true;
		try {
			List<?> lista = getFiltroHelper().processarCriterios(codUsuario, criterios);
			model.setColecaoDados(lista);
			
			if (lista == null || lista.isEmpty()) {
				addActionError(getText(ConstantesDEPI.ERRO_SEMRESULTADO));
			}
		} catch (DEPIIntegrationException e) {
			getModel().setColecaoDados(Collections.emptyList());
			addActionError(e.getMessage());
		} catch (DEPIBusinessException e) {
			getModel().setColecaoDados(Collections.emptyList());
			addActionError(e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Falha não tratada ao processar criterios de consulta", e);
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
		
		List<CriterioConsultaVO<C>> criterios = model.obterCriteriosConsulta();
		return processarCriterios(getCodUsuarioLogado(), criterios);
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
		
		if (model != null) {
			if (model.getColecaoDados() != null) {
				model.setColecaoDados(null);
			}
			if (model.getCriterios() != null) {
				model.clearCriterios();
			}
		}
		
		consultado = false;
	}
	
	/**
	 * Retorna consultado
	 * @return true se consultado
	 */
	public boolean isConsultado() {
		return consultado;
	}
	
	
	public String json() {
		return "json";
	}

}
