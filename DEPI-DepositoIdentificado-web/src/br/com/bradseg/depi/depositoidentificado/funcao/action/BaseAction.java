package br.com.bradseg.depi.depositoidentificado.funcao.action;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.bsad.filtrologin.filters.LoginUtils;
import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;

import com.opensymphony.xwork2.Action;
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

	protected transient HttpServletRequest request;
	
	private static final String MSG_LOGIN_USUARIO = "msg.erro.usuario.logado";
	
	private String subtitulo;


	/**
	 * Construtor da classe BaseAction
	 */

	public BaseAction() {
		super();

	}
	
    /**
     * Retornar o recurso de mensagens
     * @return Retorna o recurso de propriedades
     */
    protected PropertyResourceBundle getResources() {
        PropertyResourceBundle resources = null;
            resources = ((PropertyResourceBundle) ResourceBundle.getBundle(ConstantesDEPI.PATHCONFIG));
 
        return resources;
    }
    
    @Override
    public String getText(String chave) {
    	String valor = getResources().getString(chave);
    	return valor;
    }

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	//===> Métodos

	@Resource
	private transient String www3;
	
	public String getWww3() {
		return www3;
	}

	protected LoginVo getUsuarioLogado() {

		LoginVo loginVO = LoginUtils.getLoginObject(this.request);
		
        if (BaseUtil.isNZB(loginVO) || BaseUtil.isNZB(loginVO.getId())) {
            throw new IntegrationException(getText(MSG_LOGIN_USUARIO));
        }
		
		return loginVO;

	}
	
	public String getEstatico() {
		// @FIXME Deve estar dentro da aplicação, não na intranet: return request.getContextPath() + "/includes";
		
		return getWww3() + "includes";
//		return request.getContextPath() + "/includes";
	}

	/**
	 * @return {@link Action#SUCCESS}
	 */
	public String execute() {
		return SUCCESS;
	}
	
	public String getSubtitulo() {
		return subtitulo;
	}
	
	protected void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}
	
	protected void setSubtituloChave(String chave) {
		String subtitulo = getText(chave);
		setSubtitulo(subtitulo);
	}

}
