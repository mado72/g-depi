package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.form.GrupoAcessoEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.GrupoAcessoCrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.GrupoAcessoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.EditarFormAction;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.GrupoAcessoCampo;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.GrupoAcessoVO;
import br.com.bradseg.depi.depositoidentificado.vo.UsuarioVO;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("session")
public class GrupoAcessoEditarAction
		extends EditarFormAction<GrupoAcessoCampo, GrupoAcessoVO, GrupoAcessoEditarFormModel> {

	private static final long serialVersionUID = -7675543657126275320L;
	
	private transient GrupoAcessoCrudHelper crudHelper;
	
	@Override
	protected CrudHelper<GrupoAcessoCampo, GrupoAcessoVO, GrupoAcessoEditarFormModel> getCrudHelper() {
		if (crudHelper == null) {
			crudHelper = new GrupoAcessoCrudHelper();
		}
		return crudHelper;
	}
	
	@Autowired
	public void setFacade(GrupoAcessoFacade facade) {
		crudHelper.setFacade(facade);
	}
	
	@Override
	protected List<GrupoAcessoVO> mapearListaVO(String[] codigos) {
		List<GrupoAcessoVO> lista = new ArrayList<>();
		
		for (String codigo : codigos) {
			GrupoAcessoVO vo = new GrupoAcessoVO();
			vo.setCodigoGrupoAcesso(new Integer(codigo));
			
			vo = crudHelper.obterPorChave(vo);
			lista.add(vo);
		}
		
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.funcao.action.EditarFormAction#incluir()
	 */
	@Override
	public String incluir() {
		String retorno = super.incluir();
		preencherListaCompanhia();
		return retorno;
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.funcao.action.EditarFormAction#validateAlterar()
	 */
	@Override
	public void validateAlterar() {
		super.validateAlterar();
		
		String codigo = getModel().getCodigo();
		if (codigo == null || codigo.isEmpty()) {
			addFieldError("codigo", BaseUtil.getTextoFormatado(
					ConstantesDEPI.Geral.ERRORS_REQUIRED,
					BaseUtil.getTexto(ConstantesDEPI.Geral.ERRO_CODIGO_INVALIDO)));
		}
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.funcao.action.EditarFormAction#alterar()
	 */
	@Override
	public String alterar() {
		String retorno = super.alterar();
		crudHelper.preencherFuncionarios(getModel());
		crudHelper.preencherFormularioEdicao(getModel());
		return retorno;
	}
	
	public String selecionar() {
		listarDepartamentos();
		crudHelper.preencherFuncionarios(getModel());
		return INPUT;
	}
	
	public String refrescar() {
		GrupoAcessoEditarFormModel model = getModel();
		List<Integer> codFuncionariosInt = model.getCodFuncionariosInt();
		List<UsuarioVO> funcionarios = model.getFuncionarios();
		
		for (Iterator<UsuarioVO> iter = funcionarios.iterator(); iter.hasNext(); ) {
			UsuarioVO vo = iter.next();
			if (! codFuncionariosInt.contains(vo.getCodigoUsuario())) {
				iter.remove();
			}
		}
		
		List<DepartamentoVO> deptos = model.getDeptos();
		for (DepartamentoVO vo : deptos) {
			vo.setDeposito(null);
		}

		return "json";
	}

	/**
	 * 
	 */
	private void preencherListaCompanhia() {
		GrupoAcessoEditarFormModel model = getModel();
		List<CompanhiaSeguradoraVO> cias = crudHelper.obterCompanhias();
		model.setCias(cias);
		
		if (! cias.isEmpty()) {
			CompanhiaSeguradoraVO cia = cias.get(0);

			model.setCodCompanhia(String.valueOf(cia.getCodigoCompanhia()));
			listarDepartamentos();
		}
		else {
			model.setDeptos(new ArrayList<DepartamentoVO>());
		}
	}

	public void listarDepartamentos() {
		GrupoAcessoEditarFormModel model = getModel();
		
		int codCompanhia = Integer.parseInt(model.getCodCompanhia()); 
		
		List<DepartamentoVO> deptos = crudHelper
				.obterDepartamentos(new CompanhiaSeguradoraVO(codCompanhia));
		model.setDeptos(deptos);
	}
	
}
