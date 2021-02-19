package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.List;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;

/** 
 * DepartamentoCompanhiaFacade.
 */
public interface DepartamentoCompanhiaFacade {

	/**
	 * Obtém lista de associações entre a companhia e os departamentos pelo
	 * código da companhia seguradora
	 * 
	 * @param vo
	 *            contém o código
	 * @return lista de associações
	 * @throws IntegrationException
	 */
	public List<DepartamentoCompanhiaVO> obterPorCompanhiaSeguradora(CompanhiaSeguradoraVO vo) throws IntegrationException;

	/**
	 * Processa filtro
	 * @param filtro critérios
	 * @return Lista de VO.
	 * @throws IntegrationException
	 */
	List<DepartamentoCompanhiaVO> obterPorFiltro(FiltroUtil filtro)
			throws IntegrationException;

	/**
	 * Persiste uma ou várias associações de DepartamentoCompanhiaVO. Verifica se algum(uns)
	 * departamento(s) deixou(ram) de participar da associação para desalocá-lo(s)
	 * @param cia Companhia
	 * @param deptos Lista de departamentos
	 * @param codUsuario Responsável
	 */
	void persistir(CompanhiaSeguradoraVO cia, List<DepartamentoVO> deptos,
			Integer codUsuario);

	/**
	 * Exclui associações entre companhia e os departamentos associados
	 * @param vo Companhia que possui as associações
	 * @throws IntegrationException
	 */
	void excluir(DepartamentoCompanhiaVO vo) throws IntegrationException;

	/**
	 * Exclui todas as associações
	 * @param ciaVOList Lista de associações
	 * @throws IntegrationException
	 * @throws IntegrationException
	 */
	void excluir(List<DepartamentoCompanhiaVO> ciaVOList)
			throws IntegrationException;

	/**
	 * Lista os departamentos a partir de uma lista de siglas
	 * @param siglas Siglas
	 * @return Lista
	 */
	public List<DepartamentoVO> obterDepartamentos(List<String> siglas);

	/**
	 * Obter lista de companhias
	 * @return lista
	 */
	public List<CompanhiaSeguradoraVO> obterCompanhias();

}
