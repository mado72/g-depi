package br.com.bradseg.depi.depositoidentificado.funcao.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.bsad.filtrologin.filters.LoginUtils;
import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.facade.MotivoDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Classe BaseAction Função da classe
 * 
 * @author dario.monteiro
 */
@Controller
@Scope("request")
public class BaseAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = -6643036342809251102L;

	@Autowired
	private transient MotivoDepositoFacade servicosIntegracaoFacade;


	protected transient HttpServletRequest request;
	
	private static final String MSG_LOGIN_USUARIO = "Não foi possivél obter o usuário logado. A sessão pode ter expirado.";


	/**
	 * Construtor da classe BaseAction
	 */

	public BaseAction() {
		super();

	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	//===> Métodos

	protected LoginVo getUsuarioLogado() {

		LoginVo loginVO = LoginUtils.getLoginObject(this.request);
		
        if (BaseUtil.isNZB(loginVO) || BaseUtil.isNZB(loginVO.getId())) {
            throw new IntegrationException(MSG_LOGIN_USUARIO);
        }
		
		return loginVO;

	}

	/**
	 * Método: montarMenu Função: do método
	 * 
	 * @return String
	 */
	public String montarMenu() {
		
//		LoginVo login = getUsuarioLogado(); 
		
		return SUCCESS;
	}

}
