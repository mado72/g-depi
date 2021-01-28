package br.com.bradseg.depi.depositoidentificado.funcao.action;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.vo.LoginPostVO;

import com.opensymphony.xwork2.ModelDriven;

/**
 * Action de entrada no sistema.
 * 
 * @author Marcelo Damasceno
 *
 */
@Controller
@Scope("request")
public class IndexAction extends BaseAction implements SessionAware, ServletRequestAware, ModelDriven<LoginPostVO> {

	private static final long serialVersionUID = 8105460905681770564L;

	private static final Logger LOGGER = LoggerFactory.getLogger(IndexAction.class);

	@Resource
	private transient String www3;
	private transient Map<String, Object> session;
	private transient HttpServletRequest request;
	
	private LoginPostVO model = new LoginPostVO();
	
	@Override
	public LoginPostVO getModel() {
		return model;
	};

	public String index() {
		
		try {
			URL url = new URL(www3);
			session.put("www3", url);
		} catch (MalformedURLException e) {
			LOGGER.warn("Falha na construção da URL", e);
		}

		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public String getWww3() {
		return www3;
	}

	public void setWww3(String www3) {
		this.www3 = www3;
	}

}
