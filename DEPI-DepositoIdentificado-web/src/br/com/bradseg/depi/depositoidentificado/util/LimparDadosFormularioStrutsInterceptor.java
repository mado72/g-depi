package br.com.bradseg.depi.depositoidentificado.util;

import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Interceptor para limpar o contexto de formulário utilizado na configuração
 * nas actions do <code>struts.xml</code>.
 * 
 * @author Marcelo Damasceno
 */
public class LimparDadosFormularioStrutsInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 6678209178686848894L;

	/* (não-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws DEPIIntegrationException {
		
		Object action = invocation.getAction();
		String className = action.getClass().getSimpleName();
		className = className.replaceAll("\\$\\$EnhancerByCGLIB.*$", "");
		
		String actionAttributeName = className + "_FORM";
		
		invocation.getInvocationContext().getSession().remove(actionAttributeName);
		try {
			return invocation.invoke();
		} catch (Exception e) {
			throw new DEPIIntegrationException(e, "msg.erro.interno");
		}
	}

}
