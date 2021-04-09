package br.com.bradseg.depi.depositoidentificado.funcao.action;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.bsad.filtrologin.filters.LoginUtils;
import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.IEntidadeCampo;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Classe BaseAction Função da classe
 * 
 * @author dario.monteiro
 */
@Controller
@Scope("request")
public class BaseAction extends ActionSupport implements ServletRequestAware {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseAction.class);

	private static final long serialVersionUID = -6643036342809251102L;

	protected transient HttpServletRequest request;

	private String ip;
	
	private static final String MSG_LOGIN_USUARIO = "msg.erro.usuario.logado";
	
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
        PropertyResourceBundle resources = ((PropertyResourceBundle) ResourceBundle.getBundle(ConstantesDEPI.PATHCONFIG));
 
        return resources;
    }
    
    /* (não-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#getText(java.lang.String)
     */
    @Override
    public String getText(String chave) {
    	return BaseUtil.getTexto(chave);
    }
    
    public String getText(IEntidadeCampo c) {
    	StringBuilder sb = new StringBuilder("enum.")
    		.append(c.getClass().getSimpleName())
    		.append('.')
    		.append(c.name());
    	
    	return getText(sb.toString());
    }

	@Override
	public void setServletRequest(HttpServletRequest request) {
		if (LOGGER.isInfoEnabled()) {
			StringBuilder msg = new StringBuilder("@@@ DEPI Requisitado ").append(request.getRequestURL());
			LOGGER.info(msg.toString());
		}
		this.request = request;
		this.ip = request.getRemoteAddr();
	}

	//===> Métodos

	@Resource
	private transient String www3;
	
	public String getWww3() {
		return www3;
	}

	/**
	 * Retorna o usuário logado
	 * @return Retorna o usuário logado
	 */
	protected LoginVo getUsuarioLogado() {

		LOGGER.debug("Tentando recuperar o usuário logado usando LoginUtils.getLoginObject(this.request)");
		LoginVo loginVO = LoginUtils.getLoginObject(this.request);
		LOGGER.trace("Usuário logado {}", loginVO);
		
        if (BaseUtil.isNZB(loginVO) || BaseUtil.isNZB(loginVO.getId())) {
        	LOGGER.error("Não encontrou usuário logado");
            throw new IntegrationException(getText(MSG_LOGIN_USUARIO));
        }
        LOGGER.info("Sucesso: usuário logado!!! id: {}, nome: {}", loginVO.getId(), loginVO.getNome());
		
		return loginVO;
	}
	
	/**
	 * Retorna ip
	 * @return o ip
	 */
	protected String getIp() {
		return ip;
	}
	
	/**
	 * Retorna a matrícula do usuário logado
	 * @return número da matrícula
	 */
	protected int getCodUsuarioLogado() {
		LoginVo usuarioLogado = getUsuarioLogado();
		return Integer.parseInt(usuarioLogado.getId());
	}
	
	/*
	 * Método para facilitar o desenvolvimento para expôr os arquivos estáticos por URL configurável 
	 */
	public String getEstatico() {
		return request.getContextPath() + "/includes";
	}

	/**
	 * Sempre retorna SUCCESS
	 * @return {@link com.opensymphony.xwork2.Action#SUCCESS}
	 */
	@Override
	public String execute() {
		return SUCCESS;
	}

}
