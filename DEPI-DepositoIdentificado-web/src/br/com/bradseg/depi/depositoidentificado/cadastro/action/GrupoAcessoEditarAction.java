package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.depi.depositoidentificado.cadastro.form.GrupoAcessoEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.GrupoAcessoCrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.GrupoAcessoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.EditarFormAction;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.GrupoAcessoCampo;
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
	 * @see br.com.bradseg.depi.depositoidentificado.funcao.action.EditarFormAction#alterar()
	 */
	@Override
	public String alterar() {
		String retorno = super.alterar();
		preencherListaCompanhia();
		return retorno;
	}

	/**
	 * 
	 */
	private void preencherListaCompanhia() {
		LoginVo usuarioLogado = getUsuarioLogado();
		int codUsuario = Integer.parseInt(usuarioLogado.getId());
		
		GrupoAcessoEditarFormModel model = getModel();
		List<CompanhiaSeguradoraVO> cias = crudHelper.obterCompanhias(codUsuario);
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
	
	
	public void validateDesalocarFuncionario() {
		List<String> codFuncionarios = getModel().getCodFuncionarios();
		if (CollectionUtils.isEmpty(codFuncionarios)) {
			addFieldError("codFuncionario", getText("msg.erro.grupoAcesso.semFuncionarios"));
			getModel().setJson(getFieldErrors());
		}
	}
	
	public String desalocarFuncionario() {
		List<String> codFuncionarios = getModel().getCodFuncionarios();
		ArrayList<UsuarioVO> usuarios = new ArrayList<UsuarioVO>();
		
		for (String codFuncionario : codFuncionarios) {
			usuarios.add(new UsuarioVO(Integer.parseInt(codFuncionario)));
		}
		
		GrupoAcessoVO vo = new GrupoAcessoVO(Integer.parseInt(getModel().getCodigoGrupoAcesso()));
		
		LoginVo usuarioLogado = getUsuarioLogado();
		vo.setCodigoResponsavelUltimaAtualizacao(new Integer(usuarioLogado.getId()));
		((GrupoAcessoCrudHelper)getCrudHelper()).desalocarFuncionarios(vo, usuarios);
		
		getModel().setJson(Collections.singletonMap("msg", "ok"));
		
		return SUCCESS;
	}
	
}
