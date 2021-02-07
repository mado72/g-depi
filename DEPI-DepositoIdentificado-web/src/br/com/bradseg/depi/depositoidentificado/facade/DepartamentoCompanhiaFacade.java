package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.List;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO;

/** 
 * DepartamentoCompanhiaFacade.
 */
public interface DepartamentoCompanhiaFacade {

	/**
	 * Obtém pelo código da companhia seguradora
	 * @param vo contém o código
	 * @return instância do banco
	 * @throws IntegrationException
	 */
	public DepartamentoCompanhiaVO obterPorCompanhiaSeguradora(CompanhiaSeguradoraVO vo) throws IntegrationException;

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
	 * 
	 * @param vo Contém a companhia e a relação de Departamentos
	 * @throws IntegrationException
	 */
	public void persistir(DepartamentoCompanhiaVO vo) throws IntegrationException;

	/**
	 * Exclui todas as associações
	 * @param vos Lista de associações
	 * @throws IntegrationException
	 */
	public void excluir(List<DepartamentoCompanhiaVO> vos) throws IntegrationException;

}
