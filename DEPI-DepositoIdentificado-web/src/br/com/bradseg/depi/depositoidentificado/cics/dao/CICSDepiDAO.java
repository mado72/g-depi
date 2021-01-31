/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.cics.dao;

import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;

/**
 * Interface para acesso a funções CICS a fim de atender ao DEPI. 
 * @author Marcelo Damasceno
 */
public interface CICSDepiDAO {

	/**
	 * Obtém informações da Companhia pelo seu código.
	 * 
	 * @param codigoCompanhia
	 *            Código da companhia
	 * @return Companhia recuperada
	 */
	CompanhiaSeguradoraVO obterCiaPorCodigo(int codigoCompanhia);

}
