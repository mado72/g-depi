package br.com.bradseg.depi.depositoidentificado.util;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LimparDadosFormularioStrutsInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 6678209178686848894L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		Object action = invocation.getAction();
		String className = action.getClass().getSimpleName();
		className = className.replaceAll("\\$\\$EnhancerByCGLIB.*$", "");
		
		String actionAttributeName = className + "_FORM";
		
		invocation.getInvocationContext().getSession().remove(actionAttributeName);
		return invocation.invoke();
	}

}
