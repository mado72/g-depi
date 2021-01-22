package br.com.bradseg.depi.depositoidentificado.funcao.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Action para controlar e versionar o sistema. 
 */
@Controller
@Scope("request")
public class VersaoAction extends ActionSupport {
	/**
	 * Serial Version.
	 */
	private static final long serialVersionUID = 6647505076264386999L;
	
	@Override
	public String execute()  {

		return SUCCESS;
	}
	
}
