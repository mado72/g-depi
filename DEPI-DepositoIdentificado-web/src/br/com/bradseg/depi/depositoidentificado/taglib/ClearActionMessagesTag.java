package br.com.bradseg.depi.depositoidentificado.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Taglib para limpar as mensages de sistema 
 * @author Marcelo Damasceno
 */
public class ClearActionMessagesTag extends TagSupport {

	private static final long serialVersionUID = 7289181489855885223L;
	
	private boolean fieldErrors = false;

	private boolean actionErrors = false;
	
	private boolean messages = false;
	
	@Override
	public int doEndTag() throws JspException {
		ActionInvocation invocation = ActionContext.getContext().getActionInvocation();
		if (invocation != null && invocation.getAction() != null) {
			if (invocation.getAction() instanceof ActionSupport) {
				limparMensagens((ActionSupport) invocation.getAction());
			}
		}
		return super.doEndTag();
	}

	private void limparMensagens(ActionSupport action) {
		if (messages && action.hasActionMessages()) {
			action.clearMessages();
		}
		if (fieldErrors && action.hasFieldErrors()) {
			action.clearFieldErrors();
		}
		if (actionErrors && action.hasActionErrors()) {
			action.clearActionErrors();
		}
	}

	public void setActionErrors(boolean actionErrors) {
		this.actionErrors = actionErrors;
	}
	
	public void setFieldErrors(boolean fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
	
	public void setMessages(boolean messages) {
		this.messages = messages;
	}

}
