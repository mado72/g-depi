/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cics.dao.CICSDepiDAO;
import br.com.bradseg.depi.depositoidentificado.facade.ContaCorrenteFacade;
import br.com.bradseg.depi.depositoidentificado.facade.GrupoAcessoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.BaseModelAction;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.EventoContabilVO;
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
	private GrupoAcessoFacade grupoAcessoFacade;
	
	@Autowired
	private ContaCorrenteFacade contaCorrenteFacade;
	
	private static final long serialVersionUID = 8999882840693772747L;
	
	private final JsonRequestVO model = new JsonRequestVO();
	
	@Autowired
	private CICSDepiDAO cicsDAO;
	
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
			List<CompanhiaSeguradoraVO> cias = grupoAcessoFacade
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
		
			List<DepartamentoVO> deptos = grupoAcessoFacade.obterDepartamentos(ciaVO);
			
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
			
			List<DepartamentoVO> deptos = grupoAcessoFacade.obterDepartamentos(
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
	
	public String agencia() {
		
		try {
			int codBanco = Integer.parseInt(model.getCodigoBanco());
			int codAgencia = Integer.parseInt(model.getCodigoAgencia());
			
			ContaCorrenteAutorizadaVO ccVO = new ContaCorrenteAutorizadaVO(new BancoVO(codBanco), codAgencia, 0);
			String descricaoAgencia = contaCorrenteFacade.obterAgencia(ccVO);
			model.setResponse(Collections.singletonMap("agencia", descricaoAgencia));
		}
		catch (Exception e) {
			handleException(e);
		}
		
		return SUCCESS;
	}
	
	public String contaInterna() {
		
		try {
			int codCia = Integer.parseInt(model.getCodigoCia());
			int codBanco = Integer.parseInt(model.getCodigoBanco());
			int codAgencia = Integer.parseInt(model.getCodigoAgencia());
			int codCC = Integer.parseInt(model.getContaCorrente());
			
			ContaCorrenteAutorizadaVO ccVO = new ContaCorrenteAutorizadaVO(new BancoVO(codBanco), codAgencia, codCC);
			ccVO.setCia(new CompanhiaSeguradoraVO(codCia));
			
			Integer contaInterna = contaCorrenteFacade.obterContaInterna(ccVO);
			model.setResponse(Collections.singletonMap("contaInterna", contaInterna));
		}
		catch (Exception e) {
			handleException(e);
		}
		
		return SUCCESS;
		
	}
	
	public String descricaoEventoContabil() {
		int codigoEvento = Integer.parseInt(model.getCodigo().get("evento"));
		
		EventoContabilVO evento = cicsDAO.obterEventoContabil(codigoEvento);
		model.setResponse(evento);
		return SUCCESS;
	}
	
	// FIXME Retirar isso
	public String consultaCics() {
		
		try {
			String programaCics = model.getCodigo().get("CICS");
			String eventoId = model.getCodigo().get("evento");
			String pagina = model.getCodigo().get("pagina");
			
			if (eventoId == null) {
				eventoId = "361";
			}
			
			if (pagina == null) {
				pagina = "0";
			}
			
			System.out.println(programaCics);
			
//			Object evento = cicsDAO.chamarTesteBSIS0028(Integer.parseInt(eventoId), Integer.parseInt(pagina));
			EventoContabilVO evento = cicsDAO.obterEventoContabil(Integer.parseInt(eventoId));
			model.setResponse(evento);
			
			
		}
		catch (Exception e) {
			handleException(e);
		}
		
		return SUCCESS;
	}

}
