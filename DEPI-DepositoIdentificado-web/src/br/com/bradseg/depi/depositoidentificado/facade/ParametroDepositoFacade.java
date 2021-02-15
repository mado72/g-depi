package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.List;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoVO;

public interface ParametroDepositoFacade {

	void excluir(ParametroDepositoVO vo) throws IntegrationException;

	void alterar(ParametroDepositoVO vo) throws IntegrationException;

	void excluir(List<ParametroDepositoVO> lista) throws IntegrationException;

	void inserir(ParametroDepositoVO vo) throws IntegrationException;

	List<ParametroDepositoVO> obterPorFiltro(FiltroUtil filtro) throws IntegrationException;

	ParametroDepositoVO obterPorChave(ParametroDepositoVO vo) throws IntegrationException;

	List<ParametroDepositoVO> obterTodos() throws IntegrationException;

	List<ParametroDepositoVO> obterPorFiltroComRestricaoDeGrupoAcesso(FiltroUtil filtro, Integer codigoUsuario) throws IntegrationException;

	/**
	 * Lista as companhias
	 * @return Lista
	 */
	List<CompanhiaSeguradoraVO> obterCompanhias();

	/**
	 * Obtém a companhia pelo seu código
	 * @param companhia Companhia
	 * @return Companhia
	 */
	CompanhiaSeguradoraVO obterCompanhia(CompanhiaSeguradoraVO companhia);

	/**
	 * Lista departamentos associados a uma companhia
	 * @param companhia Companhia
	 * @return Lista de departamentos
	 */
	List<DepartamentoVO> obterDepartamentos(CompanhiaSeguradoraVO companhia);
	
	/**
	 * Obtém o departamento pelo seu código
	 * @param departamento Contém o código do departamento
	 * @return departamento carregado
	 */
	DepartamentoVO obterDepartamento(DepartamentoVO departamento);

	/**
	 * Lista motivos depósito
	 * @return Lista
	 */
	List<MotivoDepositoVO> obterMotivos();

	/**
	 * Obtém o motivo depósito pelo seu código
	 * @param motivoDeposito Motivo Depósito
	 * @return Motivo Depósito
	 */
	MotivoDepositoVO obterMotivo(MotivoDepositoVO motivoDeposito);

}
