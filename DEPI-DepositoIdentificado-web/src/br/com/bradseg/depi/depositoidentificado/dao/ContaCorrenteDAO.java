/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;

/**
 * Oferece métodos para consultar e atualizar dados de conta corrente no
 * repositório.
 * 
 * @author Marcelo Damasceno
 */
public interface ContaCorrenteDAO {

	/**
	 * Inclui uma instância de conta corrente no repositório
	 * @param vo Conta Corrente
	 */
	void incluir(ContaCorrenteAutorizadaVO vo);

	/**
	 * Altera uma instância de conta corrente
	 * @param vo Conta Corrente
	 */
	void alterar(ContaCorrenteAutorizadaVO vo);

	/**
	 * Exclui uma instância de conta corrente
	 * @param vo Conta corrente
	 */
	void excluir(ContaCorrenteAutorizadaVO vo);

	/**
	 * Verifica se uma conta corrente é referenciada
	 * @param vo Conta corrente
	 * @return true se for referenciada
	 */
	boolean isReferenciado(ContaCorrenteAutorizadaVO vo);
	
	/**
	 * Consulta as contas corrente com base nos critérios de filtro.
	 * @param filtro Critérios de filtro
	 * @return Lista de Contas corrente
	 */
	List<ContaCorrenteAutorizadaVO> obterPorFiltro(FiltroUtil filtro);

	/**
	 * Consulta as contas corrente que um usuário tem acesso com base nos critérios de filtro.
	 * @param codUsuario Código do usuário
	 * @param filtro Critérios de filtro
	 * @return Lista de Contas corrente
	 */
	List<ContaCorrenteAutorizadaVO> obterPorFiltroComRestricao(int codUsuario,
			FiltroUtil filtro);

}
