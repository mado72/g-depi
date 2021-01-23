package br.com.bradseg.depi.depositoidentificado.funcao.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper.EstadoRegistro;
import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm.EstadoCrud;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;

/**
 * Superclasse para Actions que processam formulários de edição
 * 
 * @author Marcelo Damasceno
 *
 * @param <VO> Tipo que é manipulado pelo CRUD
 * @param <F> Tipo do Model utilizado por esta Action.
 */
@Controller
public abstract class CrudAction<VO, F extends CrudForm> extends BaseModelAction<F> {

	private static final long serialVersionUID = -8669859699304965615L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrudAction.class);

	private F model;
	
	public CrudAction() {
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
	protected abstract CrudHelper<VO, F> getCrudHelper();
	
	/**
	 * Prepara o model para exibir um registro
	 * @return {@link com.opensymphony.xwork2.Action#INPUT}
	 */
	public String exibir() {
		LOGGER.debug("Preparando formulário para exibir um registro");
		getModel().setEstado(EstadoCrud.EXIBIR);
		
		CrudHelper<VO, F> crudHelper = getCrudHelper();
		setSubtituloChave(crudHelper.getChaveTituloDetalhar());
		crudHelper.preencherFormularioEdicao(model);
		clearErrorsAndMessages();
		
		return INPUT;
	}
	
	/**
	 * Prepara o model para incluir um registro
	 * @return {@link com.opensymphony.xwork2.Action#INPUT}
	 */
	public String incluir() {
		LOGGER.debug("Preparando formulário para inclusão de um novo registro");
		getModel().setEstado(EstadoCrud.INSERIR);

		this.model.limparDados();
		
		setSubtituloChave(getCrudHelper().getChaveTituloIncluir());
		clearErrorsAndMessages();
		
		return INPUT;
	}
	
	/**
	 * Prepara o model para alterar um registro
	 * @return {@link com.opensymphony.xwork2.Action#INPUT}
	 */
	public String alterar() {
		LOGGER.debug("Preparando formulário para alterar um registro");

		getModel().setEstado(EstadoCrud.ALTERAR);
		
		CrudHelper<VO, F> crudHelper = getCrudHelper();

		setSubtituloChave(crudHelper.getChaveTituloAlterar());
		crudHelper.preencherFormularioEdicao(model);
		clearErrorsAndMessages();
		
		return INPUT;
	}
	
	/**
	 * Processa a ação sobre o formulário
	 * @return {@link com.opensymphony.xwork2.Action#SUCCESS}
	 */
	public String processar() {
		LOGGER.info("Processando submissão do formulário");
		F form = getModel();
		
		if ("salvar".equals(form.getAcao())) {
			persistirDados();
		}
		
		return SUCCESS;
	}
	
	/**
	 * Processa a ação de excluir sobre os registros enviados pelo formulário
	 * @return {@link com.opensymphony.xwork2.Action#SUCCESS}
	 */
	public String excluir() {
		excluirRegistros();
		
		return SUCCESS;
	}
	
	private void persistirDados() {
		LOGGER.info("Persistindo dados do formulário");
		
		F model = getModel();
		CrudHelper<VO, F> helper = getCrudHelper();
		
		LoginVo usuarioLogado = getUsuarioLogado();
		
		EstadoRegistro estado = helper.persistirDados(model, usuarioLogado);
		
		if (estado == EstadoRegistro.NOVO) {
			addActionMessage(ConstantesDEPI.MSG_INSERIR_EXITO);
		}
		else {
			addActionMessage(ConstantesDEPI.MSG_ALTERAR_EXITO);
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

	private void excluirRegistros() {
		String[] codigos = request.getParameterValues("codigo");
		List<VO> listaVO = mapearListaVO(codigos);
		
		getCrudHelper().excluirRegistros(listaVO);
		addActionMessage(ConstantesDEPI.MSG_EXCLUIR_EXITO);
	}

}
