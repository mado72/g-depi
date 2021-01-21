package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.depi.depositoidentificado.cadastro.form.DepartamentoEditarForm;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.facade.DepartamentoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.BaseEditorModelAction;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("request")
public class DepartamentoEditarAction extends BaseEditorModelAction<DepartamentoEditarForm> {
	
    protected static final Logger LOGGER = LoggerFactory.getLogger(DepartamentoEditarAction.class);

	private static final long serialVersionUID = -7675543657126275320L;
	
	private static final String TITLE_DEPARTAMENTO_EDITAR = "title.deposito.editar";
	
	private static final String TITLE_DEPARTAMENTO_DETALHAR = "title.deposito.detalhar";

	private static final String TITLE_DEPARTAMENTO_NOVO = "title.deposito.novo";
	
	private DepartamentoEditarForm model;
	
	@Autowired
	private transient DepartamentoFacade facade;
	
	@Override
	public DepartamentoEditarForm getModel() {
		if (sessionData.containsKey(DepartamentoEditarForm.NOME_FORM)) {
			model = (DepartamentoEditarForm) sessionData.get(DepartamentoEditarForm.NOME_FORM);
		}
		else {
			this.novaInstanciaModel();
		}
		return model;
	}
	
	@Override
	protected void novaInstanciaModel() {
		model = new DepartamentoEditarForm();
		sessionData.put(DepartamentoEditarForm.NOME_FORM, model);
	}
	
	public String alterar() {
		preencherFormulario(TITLE_DEPARTAMENTO_EDITAR);
		
		return INPUT;
	}
	
	public String detalhar() {
		preencherFormulario(TITLE_DEPARTAMENTO_DETALHAR);
		
		getModel().setDetalhar(true);
		return INPUT;
	}
	
	/**
	 * Processa o envio do formulário. Processa o parâmetro <code>acao</code>
	 * para identificar se é para salvar (inclusão ou alteração), cancelar ou
	 * voltar.
	 * 
	 * @return {@link com.opensymphony.xwork2.Action#SUCCESS} quando for processado corretamente.
	 */
	public String enviar() {
		DepartamentoEditarForm model = getModel();
		
		if ("salvar".equals(model.getAcao())) {
			persistirDados();
		}

		return SUCCESS;
	}
	
	public String voltar() {
		return SUCCESS;
	}

	@Override
	public String novo() {
		getModel().setDetalhar(false);
		
		setSubtituloChave(TITLE_DEPARTAMENTO_NOVO);
		
		return INPUT;
	}
	
	public String excluir() {
		excluirRegistros();
		
		return SUCCESS;
	}

	@Override
	protected void persistirDados() {
		DepartamentoEditarForm model = getModel();
		
		boolean novo = model.getCodigo() == null || model.getCodigo().trim().isEmpty();
	
		DepartamentoVO instancia;
		
		if (novo) {
			LoginVo usuarioLogado = getUsuarioLogado();
			instancia = new DepartamentoVO();
			
			int usuarioId = Integer.parseInt(usuarioLogado.getId().replace("\\D", ""));
			instancia.setCodigoResponsavelUltimaAtualizacao(usuarioId);
		}
		else {
			instancia = obterPeloCodigo();
		}
	
		instancia.setSiglaDepartamento(model.getSiglaDepartamento());
		instancia.setNomeDepartamento(model.getNomeDepartamento());
	
		try {
			if (novo) {
				facade.inserir(instancia);
				addActionMessage(ConstantesDEPI.MSG_INSERIR_EXITO);
			}
			else {
				facade.alterar(instancia);
				addActionMessage(ConstantesDEPI.MSG_ALTERAR_EXITO);
			}
		} catch (Exception e) {
			throw new DEPIIntegrationException(e, ConstantesDEPI.ERRO_INTERNO);
		}
	}

	private void excluirRegistros() {
		String[] codigos = request.getParameterValues("codigo");
		
		List<DepartamentoVO> lista = new ArrayList<>();
		
		for (String codigo: codigos) {
			DepartamentoVO vo = new DepartamentoVO();
			vo.setCodigoDepartamento(new Integer(codigo));
			
			vo = facade.obterPorChave(vo);
			lista.add(vo);
		}
		
		facade.excluir(lista);
		addActionMessage(ConstantesDEPI.MSG_EXCLUIR_EXITO);
	}
	
	@Override
	protected void preencherFormulario(String subtituloChave) {
		super.preencherFormulario(subtituloChave);
		
		DepartamentoVO instancia = obterPeloCodigo();
		
		DepartamentoEditarForm model = getModel();
		
		model.setDetalhar(false);
		model.setCodigo(String.valueOf(instancia.getCodigoDepartamento()));
		model.setSiglaDepartamento(instancia.getSiglaDepartamento());
		model.setNomeDepartamento(instancia.getNomeDepartamento());
	}

	private DepartamentoVO obterPeloCodigo() {
		int codigo = Integer.parseInt(this.getModel().getCodigo());

		DepartamentoVO vo = new DepartamentoVO();
		vo.setCodigoDepartamento(codigo);
		
		return facade.obterPorChave(vo);
	}
	
}
