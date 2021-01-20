package br.com.bradseg.depi.depositoidentificado.funcao.action;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.bsad.filtrologin.filters.LoginUtils;
import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.Funcao;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Login Action  
 * @author Marcelo Damasceno
 */
@Controller
@Scope("request")
public class LoginAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = -4322830132167895863L;
	
	private HttpServletRequest request;
	
	private static final String MSG_LOGIN_USUARIO = "msg.erro.usuario.logado";
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	/**
	 * action execute
	 * @return - ação do sistema
	 */
	public String execute() {
		LoginVo loginVO = LoginUtils.getLoginObject(this.request);
		Funcao<String, Object> requestAttr = new Funcao<String, Object>() {
			
			@Override
			public Object apply(String source) {
				return request.getAttribute(source);
			}
		};

		Funcao<String, Object> requestParam = new Funcao<String, Object>() {
			
			@Override
			public Object apply(String source) {
				return request.getParameter(source);
			}
		};
		
		Funcao<String, Object> requestSession = new Funcao<String, Object>() {
			
			@Override
			public Object apply(String source) {
				return request.getSession().getAttribute(source);
			}
		};
		
		
		System.out.println("PARAMS: ");
		printAttributes(request.getParameterNames(), requestParam);

		System.out.println("REQUEST: ");
		printAttributes(request.getAttributeNames(), requestAttr);
		
		System.out.println("SESSION: ");
		printAttributes(request.getSession().getAttributeNames(), requestSession);
		
        if (BaseUtil.isNZB(loginVO) || BaseUtil.isNZB(loginVO)) {
            throw new IntegrationException(getText(MSG_LOGIN_USUARIO));
        }
		
		return Action.SUCCESS;
	}

	private void printAttributes(Enumeration<String> attributeNames, Funcao<String, Object> funcao) {
		for (Enumeration<String> e = attributeNames; e.hasMoreElements(); ) {
			String name = e.nextElement();
			
			Object attr = funcao.apply(name); 
			System.out.println(name + ": " + attr);
		}
	}

}
