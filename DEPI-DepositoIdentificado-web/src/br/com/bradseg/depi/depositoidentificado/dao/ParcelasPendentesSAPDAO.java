package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.vo.ParcelaCobrancaVO;

/**
 * Acesso ao SAP
 * 
 * @author Globality
 */
public interface ParcelasPendentesSAPDAO {

	/**
	 * Método que lista os códigos de parcela associadas.
	 * 
	 * @param parcelaCobranca
	 *            - objeto com os parâmetros.
	 * @return List<ParcelaCobrancaVO> - lista de retorno.
	 * @throws IntegrationException
	 *             - exception.
	 */
	public abstract List<ParcelaCobrancaVO> listarParcelasSAP(
			ParcelaCobrancaVO parcelaCobranca) throws IntegrationException;

}
