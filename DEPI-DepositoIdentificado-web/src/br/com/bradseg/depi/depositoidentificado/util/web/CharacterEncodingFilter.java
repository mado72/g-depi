package br.com.bradseg.depi.depositoidentificado.util.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filtro que inclui no cabeçalho do {@link ServletResponse} informações para
 * informar conteúdo codificado como UTF-8.
 * 
 * @author Marcelo Damasceno
 * 
 */
public class CharacterEncodingFilter implements Filter {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(CharacterEncodingFilter.class);

	/* (não-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.debug("Utilizando filtro para codificar em UTF-8");
	}

	/* (não-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		servletRequest.setCharacterEncoding("UTF-8");
		servletResponse.setContentType("text/html; charset=UTF-8");
		filterChain.doFilter(servletRequest, servletResponse);
	}

	/* (não-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		LOGGER.debug("Finalizando o filtro para codificar em UTF-8");
	}
}