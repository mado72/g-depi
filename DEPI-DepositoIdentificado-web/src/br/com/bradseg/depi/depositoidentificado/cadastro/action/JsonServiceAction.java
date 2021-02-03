/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.depi.depositoidentificado.facade.GrupoAcessoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.BaseModelAction;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.JsonRequestVO;

/**
 * Ação para retornar um JSON com a lista de departamentos de acordo com o
 * código da companhia selecionada e o usuário logado.
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("request")
public class JsonServiceAction extends BaseModelAction<JsonRequestVO> {

	@Autowired
	private GrupoAcessoFacade facade;
	
	private static final long serialVersionUID = 8999882840693772747L;
	
	private final JsonRequestVO model = new JsonRequestVO();
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public JsonRequestVO getModel() {
		return model;
	}
	
	private void handleException(Exception e) {
		HashMap<String, Object> error = new HashMap<>();
		error.put("erro", e.getMessage());
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		error.put("stackstrace", sw.toString());
		
		model.setErro(error);
	}

	public void validateCiaListar() {
		
	}
	
	public String ciaListar() {
		try {
			LoginVo usuarioLogado = getUsuarioLogado();
			
			List<CompanhiaSeguradoraVO> cias = facade
					.obterCompanhias(new Double(usuarioLogado.getId()));
			
			model.setResponse(cias);
		} catch (Exception e) {
			handleException(e);
		}
		
		return SUCCESS;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	public void validateCiaDepartamentos() {
		String codigoCia = model.getCodigoCia();
		if (codigoCia == null || codigoCia.isEmpty()) {
			addFieldError("codigoCia", ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO);
		}
	}
	
	public String ciaDepartamentos() {
		try {
			LoginVo usuarioLogado = getUsuarioLogado();

			int codigoCia = Integer.parseInt(model.getCodigoCia());
		
			List<DepartamentoVO> deptos = new ArrayList<>();
			deptos = facade.obterDepartamentos(codigoCia,
					Double.parseDouble(usuarioLogado.getId()));
			
			for (DepartamentoVO vo : deptos) {
				vo.setDeposito(null);
			}
			
			model.setResponse(deptos);
		} catch (Exception e) {
			handleException(e);
		}
		
		return SUCCESS;
	}

}
