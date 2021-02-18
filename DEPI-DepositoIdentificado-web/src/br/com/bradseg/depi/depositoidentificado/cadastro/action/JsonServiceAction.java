/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
		
		model.setErro(error);
	}

	public void validateCiaListar() {
		
	}
	
	public String ciaListar() {
		try {
			List<CompanhiaSeguradoraVO> cias = facade
					.obterCompanhias();
			
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
			int codigoCia = Integer.parseInt(model.getCodigoCia());
			CompanhiaSeguradoraVO ciaVO = new CompanhiaSeguradoraVO(codigoCia);
		
			List<DepartamentoVO> deptos = facade.obterDepartamentos(ciaVO);
			
			for (DepartamentoVO vo : deptos) {
				vo.setDeposito(null);
			}
			
			model.setResponse(deptos);
		} catch (Exception e) {
			handleException(e);
		}
		
		return SUCCESS;
	}
	
	public String ciaDeptosComRestricao() {
		try {
			int codigoCia = Integer.parseInt(model.getCodigoCia());
			int codUsuarioLogado = getCodUsuarioLogado();
			
			List<DepartamentoVO> deptos = facade.obterDepartamentos(
					codUsuarioLogado, new CompanhiaSeguradoraVO(codigoCia));
			
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
