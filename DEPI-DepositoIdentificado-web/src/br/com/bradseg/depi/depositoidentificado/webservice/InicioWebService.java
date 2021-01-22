package br.com.bradseg.depi.depositoidentificado.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.stereotype.Service;

import br.com.bradseg.bsad.framework.core.exception.BusinessException;
import br.com.bradseg.bsad.framework.core.exception.IntegrationException;

/**
 * Documenta��o da classe que representa um WebService da funcionalidade
 * 
 * @author Bradesco Seguros
 */
@WebService
@Service
public class InicioWebService {

	//@Autowired
	//private InicioServiceFacade inicioService;

	/**
	 * Documenta��o do método
	 * 
	 * @param nome do argumento
	 * @return String
	 */
	@WebMethod
	public String consultarSaudacao(String nome) throws IntegrationException, BusinessException {
		// Sempre declarar no método as poss�veis exceptions para que o CXF possa serializa-las no WSDL gerado.

		//return inicioService.consultarSaudacao(nome);
		return null;
	}

	//@WebMethod(exclude = true)
	//public void setInicioService(InicioServiceFacade obj) {
		//this.inicioService = obj;
	//}

}
