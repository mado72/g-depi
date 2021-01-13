package br.com.bradseg.depi.depositoidentificado.util;

import org.slf4j.Logger;


/**
 * 
 * Classe LogUtil.
 */
public class LogUtil {
	
	/** A Constante TRACO. */
	public static final String TRACO = " - ";
	
	
	/**
	 * logger.
	 *
	 * @param logger a(o) logger
	 */
	public static void logger(Logger logger){
		logger.error(Constante.NOME_SISTEMA.concat(TRACO).concat(Thread.currentThread().getStackTrace()[3].getClassName()).concat(TRACO).concat(Thread.currentThread().getStackTrace()[3].getMethodName()));
	}


	/**
	 * logger.
	 *
	 * @param logger a(o) logger
	 * @param mensagemErro a(o) mensagem erro
	 */
	public static void logger(Logger logger,String mensagemErro){
		logger.error(Constante.NOME_SISTEMA.concat(TRACO).concat(Thread.currentThread().getStackTrace()[3].getClassName()).concat(Thread.currentThread().getStackTrace()[3].getMethodName().concat(TRACO).concat(mensagemErro)));
	}

}
