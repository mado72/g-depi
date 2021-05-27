package br.com.bradseg.depi.depositoidentificado.funcao.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm.EstadoCrud;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.IEntidadeCampo;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;

/**
 * Superclasse para Actions que processam formulários de edição
 * 
 * @param <C> Tipo da entidade manipulada pelo CRUD
 * @param <VO> Tipo do Bean manipulado pelo CRUD para utilizar nas camadas de persistência
 * @param <F> Tipo do Model utilizado por esta Action.
 */
@Controller
@Scope("request")
public abstract class EditarFormAction<C extends IEntidadeCampo, VO, F extends CrudForm> extends BaseModelAction<F> {

	private static final long serialVersionUID = -8669859699304965615L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EditarFormAction.class);
	
	private final F model;
	
	private List<String> codigo;
	
	public EditarFormAction() {
		this.model = getCrudHelper().criarCrudModel();
	}
	
	@Override
	public F getModel() {
		return model;
	}
	
	/**
	 * Obtém o helper para processar as ações do formulário
	 * @return instância de {@link CrudHelper}
	 */
	protected abstract CrudHelper<C, VO, F> getCrudHelper();
	
	public void validateExibir() {
		LOGGER.info("Validando exibir");
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(dumpErros());
		}
		clearErrorsAndMessages();
	}
	
	public void validateIncluir() {
		LOGGER.info("Validando incluir. Tem erros: {}", hasErrors());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(dumpErros());
		}
		this.model.preencherDadosIniciais();
		clearErrorsAndMessages();
	}
	
	public void validateAlterar() {
		LOGGER.info("Validando alterar. Tem erros: {}", hasErrors());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(dumpErros());
		}
		clearErrorsAndMessages();
	}
	
	public void validateExcluir() {
		LOGGER.debug("Validando excluir. Tem erros: {}", hasErrors());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(dumpErros());
		}
		// não limpa mensagens de erro de campo 
		clearActionErrors();
		clearMessages();
	}
	
	public void validateRefrescar() {
		LOGGER.debug("Validando refrescar. Tem erros: {}", hasErrors());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(dumpErros());
		}
		
	}
	
	/**
	 * Prepara o model para exibir um registro
	 * @return {@link com.opensymphony.xwork2.Action#INPUT}
	 */
	public String exibir() {
		LOGGER.debug("Preparando formul\u00e1rio para exibir um registro");
		
		CrudHelper<C, VO, F> crudHelper = getCrudHelper();
		model.setEstado(EstadoCrud.EXIBIR);
		model.setSubtitulo(getText(crudHelper.getChaveTituloDetalhar()));
		
		int codUsuario = getCodUsuarioLogado();
		String ipCliente = getIp();

		crudHelper.preencherFormularioEdicao(model, codUsuario, ipCliente);
		return INPUT;
	}
	
	/**
	 * Prepara o model para incluir um registro
	 * @return {@link com.opensymphony.xwork2.Action#INPUT}
	 */
	public String incluir() {
		LOGGER.debug("Preparando formul\u00e1rio para inclus\u00e3o de um novo registro");

		prepararFormularioIncluir();
		
		return INPUT;
	}

	protected void prepararFormularioIncluir() {
		this.model.setEstado(EstadoCrud.INSERIR);
		this.model.setSubtitulo(getText(getCrudHelper().getChaveTituloIncluir()));
	}
	
	/**
	 * Prepara o model para alterar um registro
	 * @return {@link com.opensymphony.xwork2.Action#INPUT}
	 */
	public String alterar() {
		LOGGER.debug("Preparando formul\u00e1rio para alterar um registro");

		prepararFormularioAlterar();
		
		return INPUT;
	}

	protected void prepararFormularioAlterar() {
		CrudHelper<C, VO, F> crudHelper = getCrudHelper();

		this.model.setEstado(EstadoCrud.ALTERAR);
		this.model.setSubtitulo(getText(crudHelper.getChaveTituloAlterar()));
		
		int codUsuario = getCodUsuarioLogado();
		String ipCliente = getIp();
		crudHelper.preencherFormularioEdicao(model, codUsuario, ipCliente);
	}
	
	/**
	 * Processa ação para voltar ao formulário de consulta.
	 * @return "voltar"
	 */
	public String voltar() {
		return "voltar";
	}
	
	/**
	 * Processa a ação de excluir sobre os registros enviados pelo formulário
	 * @return {@link com.opensymphony.xwork2.Action#SUCCESS}
	 */
	public String excluir() {
		String[] codigos = request.getParameterValues("codigo");
		List<VO> listaVO = mapearListaVO(codigos);
		
		try {
			getCrudHelper().excluirRegistros(listaVO);
			addActionMessage(getText(ConstantesDEPI.MSG_EXCLUIR_EXITO));
			
			return SUCCESS;
		} catch (Exception e) {
			addActionError(e.getMessage());
			LOGGER.error("Erro ao excluir registros", e);
			return INPUT;
		}
	}
	
	/**
	 * Método responsável por instanciar lista de VO, a partir da análise dos
	 * códigos
	 * 
	 * @param codigos
	 *            Códigos informados pelo formulário que identificam instâncias
	 *            de VO
	 * @return Lista de VO preenchidos com os códigos.
	 */
	protected abstract List<VO> mapearListaVO(String[] codigos);
	
	public List<String> getCodigo() {
		return codigo;
	}
	
	public void setCodigo(List<String> codigo) {
		this.codigo = codigo;
	}

}
