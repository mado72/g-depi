/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO;

/**
 * Interface de GrupoAcessoDAO
 */
public interface DepartamentoCompanhiaDAO {
    /**
     * isReferenciado 
     * @param grupo - GrupoAcessoVO.
     */
	Boolean isReferenciado(DepartamentoCompanhiaVO grupo);

	/**
	 * obtém DepartamentoCompanhiaVO
	 * @param companhia contem o código da companhia
	 * @return DepartamentoCompanhiaVO
	 */
	DepartamentoCompanhiaVO obterPorCompanhiaSeguradora(CompanhiaSeguradoraVO companhia);

	/**
	 * Persiste associações Companhia x Departamentos
	 * @param vo Contém as associações
	 */
	void persistir(DepartamentoCompanhiaVO vo);

	/**
	 * Exclui associações da companhia com os departamentos. Para cada par é
	 * necessário ter verificado na camada de negócio se existe dependência com
	 * parâmetros de depósito, motivo de depósito e com grupo de acesso. Este
	 * método apenas inativa as associações, sem validações.
	 * 
	 * @param vo
	 *            Contém a companhia que terá as suas associações excluídas
	 */
	void excluir(DepartamentoCompanhiaVO vo);

	/**
	 * Obtém as associações companhia x departamentos com base nos critérios de filtro.
	 * @param filtro Critérios de filtro
	 * @return Lista de Companhia x Departamento. 
	 */
	List<DepartamentoCompanhiaVO> obterPorFiltro(FiltroUtil filtro);
}
