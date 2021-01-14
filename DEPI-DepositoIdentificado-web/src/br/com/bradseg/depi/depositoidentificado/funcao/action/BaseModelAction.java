package br.com.bradseg.depi.depositoidentificado.funcao.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ModelDriven;

@Component
public abstract class BaseModelAction<T> extends BaseAction implements ModelDriven<T>,
		SessionAware {

	private static final long serialVersionUID = 4671957667151873914L;

	protected Map<String, Object> sessionData;

	@Resource
	private transient String www3;
	
	public String getWww3() {
		return www3;
	}

	@Override
	public void setSession(Map<String, Object> sessionData) {
		this.sessionData = sessionData;
	}
	
	protected void clearData() {
		String name = getClass().getSimpleName();
		this.sessionData.remove(name);
		this.request.getSession().removeAttribute(name);
	}
	
	public String getEstatico() {
		// @FIXME Deve estar dentro da aplicação, não na intranet: return request.getContextPath() + "/includes";
		
		return getWww3() + "includes";
//		return request.getContextPath() + "/includes";
	}
	
	abstract protected void novaInstanciaModel(); 

}
