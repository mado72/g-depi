package br.com.bradseg.depi.depositoidentificado.listeners;

import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Classe responsável por montar a URL que contém o caminho das imagens e do CSS no servidor
 * @author andre.gomes
 */

public class UrlListener implements ServletContextListener {


	private static final String ESTRUTURA_PASTA_IMAGENS_INTRANET = "padroes_web/intranet/imagens/";
	private static final String ESTRUTURA_PASTA_CSS_INTRANET = "padroes_web/intranet/css/";
	private static final String ESTRUTURA_PASTA_JS_INTRANET = "padroes_web/intranet/js/";
	public static final String PATH_IMAGENS_LOCAL = "/DEPI-DepositoIdentificado/includes/images/";
	


	/**
	 * Executa operações após inicialização do contexto do Listener
	 * @param sce ServletContextEvent
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {

	
		ServletContext servletContext = sce.getServletContext();
		// Pega o contexto do Spring e traz para dentro do listener para que possa se utlizar os Beans do Spring nesse
		// contexto.
		WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		URL www3 = (URL) springContext.getBean("www3");

		String url = www3.toString();

		if (!url.endsWith("/")) {
			url = url.concat("/");
		}

		String caminhoImagens = url + ESTRUTURA_PASTA_IMAGENS_INTRANET;
		String caminhoCss = url + ESTRUTURA_PASTA_CSS_INTRANET;
		String caminhoJs = url +ESTRUTURA_PASTA_JS_INTRANET;
	
		servletContext.setAttribute("caminhoImagens", caminhoImagens);
		servletContext.setAttribute("caminhoCss", caminhoCss);
		servletContext.setAttribute("caminhoJs", caminhoJs);
		servletContext.setAttribute("caminhoImagensLocal", PATH_IMAGENS_LOCAL);
		
		
	}

	/**
	 * Executa operações após destruição do contexto
	 * @param sce ServletContextEvent
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//empty method
	}

}