package br.com.bradseg.depi.depositoidentificado.funcao.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper.EstadoRegistro;
import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm.EstadoCrud;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;

import com.opensymphony.xwork2.Action;

/**
 * Superclasse para Actions que processam formulários de edição
 * 
 * @author Marcelo Damasceno
 *
 * @param <T> Tipo do Model utilizado por esta Action.
 */
public abstract class CrudAction<T extends CrudForm, VO> extends BaseModelAction<T> {

	private static final long serialVersionUID = -8669859699304965615L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrudAction.class);

	private T model;
	
	@SuppressWarnings("unchecked")
	public CrudAction() {
		this.model = (T) getCrudHelper().criarCrudModel();
	}
	
	@Override
	public T getModel() {
		return model;
	}
	
	/**
	 * Obtém o helper para processar as ações do formulário
	 * @return instância de {@link CrudHelper}
	 */
	protected abstract CrudHelper<?, VO> getCrudHelper();
	
	public boolean isDetalhar() {
		return getModel().getEstado() == EstadoCrud.EXIBIR;
	}
	
	/**
	 * Prepara o model para exibir um registro
	 * @return {@link Action#INPUT}
	 */
	public String exibir() {
		LOGGER.debug("Preparando formulário para exibir um registro");
		getModel().setEstado(EstadoCrud.EXIBIR);
		
		CrudHelper<?, ?> crudHelper = getCrudHelper();
		setSubtituloChave(crudHelper.getChaveTituloDetalhar());
		crudHelper.preencherFormularioEdicao(model);
		
		return INPUT;
	}
	
	/**
	 * Prepara o model para incluir um registro
	 * @return {@link Action#INPUT}
	 */
	public String incluir() {
		LOGGER.debug("Preparando formulário para inclusão de um novo registro");
		getModel().setEstado(EstadoCrud.INCLUIR);
		
		setSubtituloChave(getCrudHelper().getChaveTituloIncluir());
		
		return INPUT;
	}
	
	/**
	 * Prepara o model para alterar um registro
	 * @return {@link Action#INPUT}
	 */
	public String alterar() {
		LOGGER.debug("Preparando formulário para alterar um registro");

		getModel().setEstado(EstadoCrud.ALTERAR);
		CrudHelper<?, ?> crudHelper = getCrudHelper();
		
		setSubtituloChave(crudHelper.getChaveTituloAlterar());
		crudHelper.preencherFormularioEdicao(model);
		
		return INPUT;
	}
	
	/**
	 * Processa a ação sobre o formulário
	 * @return {@link Action#SUCCESS}
	 */
	public String processar() {
		LOGGER.info("Processando submissão do formulário");
		T form = getModel();
		
		if ("salvar".equals(form.getAcao())) {
			persistirDados();
		}
		
		return SUCCESS;
	}
	
	/**
	 * Processa a ação de excluir sobre os registros enviados pelo formulário
	 * @return {@link Action#SUCCESS}
	 */
	public String excluir() {
		excluirRegistros();
		
		return SUCCESS;
	}
	
	private void persistirDados() {
		LOGGER.info("Persistindo dados do formulário");
		
		T model = getModel();
		CrudHelper<?, ?> helper = getCrudHelper();
		
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
