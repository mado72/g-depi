/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;

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
	 * @return Lista de DepartamentoCompanhiaVO
	 */
	List<DepartamentoCompanhiaVO> obterPorCompanhiaSeguradora(CompanhiaSeguradoraVO companhia);

	/**
	 * Persiste associações Companhia x Departamentos. Este DAO não faz exclusões dos departamentos
	 * que já foram inseridos anteriormente e não participam da lista de associações atuais.
	 * @param cia Companhia
	 * @param associacoes Lista de departamentos da associação
	 * @param codUsuario Código do usuário responsável
	 */
	void persistir(CompanhiaSeguradoraVO cia, List<DepartamentoVO> associacoes, int codUsuario);

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

	/**
	 * Obtém uma instância da associação cia x depto
	 * @param vo A associação
	 * @return Dados da associação
	 */
	DepartamentoCompanhiaVO obterPorChave(DepartamentoCompanhiaVO vo);
}
