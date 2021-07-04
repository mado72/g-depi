package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.vo.ParcelaCobrancaVO;

/**
 * Interface DAO de parcelas pendentes.
 * @author M68887
 */
public interface ParcelasPendentesDAO {
	
	/**
	 * Método que insere um registro relativo é um deposito.
	 * @param listaParcelas - objeto com os dados populados.
	 * @exception IntegrationException - exception.
	 */
	/*public void inserirDepositoCobranca(List<ParcelaCobrancaVO> listaParcelas);
	*/
	/**
	 * Método que lista os códigos das parcelas associadas.
	 * @param parcelaCobranca - objeto com os parâmetros.
	 * @return List<ParcelaCobrancaVO> - lista com os dados de retorno.
	 */
	public List<ParcelaCobrancaVO> listarParcelasAssociadas(ParcelaCobrancaVO parcelaCobranca);
	
	/**
	 * Método que lista os detalhes das parcelas associadas.
	 * @param parcelaCobranca - objeto com os parâmetros de consulta.
	 * @return List<ParcelaCobrancaVO> - lista com as parcelas populadas.
	 * @throws IntegrationException - exception.
	 */
	/*public List<ParcelaCobrancaVO> detalharParcelasAssociadas(ParcelaCobrancaVO parcelaCobranca);
	*/
	/**
	 * Método que exclui os registros da tabela.
	 * @param parcela - a ser excluída.
	 * @throws IntegrationException - exceção.
	 */
	/*public void excluirRegistro(Long parcela) ;*/
}
