package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.List;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoVO;

/**
 * Interface para manutenção de parâmetros depósito 
 * @author Marcelo Damasceno
 */
public interface ParametroDepositoFacade {

	void excluir(ParametroDepositoVO vo) throws IntegrationException;

	void alterar(ParametroDepositoVO vo) throws IntegrationException;

	void excluir(List<ParametroDepositoVO> lista) throws IntegrationException;

	void inserir(ParametroDepositoVO vo) throws IntegrationException;

	ParametroDepositoVO obterPorChave(ParametroDepositoVO vo) throws IntegrationException;

	List<ParametroDepositoVO> obterPorFiltroComRestricaoDeGrupoAcesso(int codigoUsuario, FiltroUtil filtro) throws IntegrationException;

	/**
	 * Lista as companhias
	 * @param codUsuario Código do usuário logado
	 * @return Lista
	 */
	List<CompanhiaSeguradoraVO> obterCompanhias(int codUsuario);

	/**
	 * Obtém a companhia pelo seu código
	 * @param companhia Companhia
	 * @return Companhia
	 */
	CompanhiaSeguradoraVO obterCompanhia(CompanhiaSeguradoraVO companhia);

	/**
	 * Lista de um grupo de acesso os departamentos associados a uma companhia
	 * @param codUsuario Código do usuário logado
	 * @param companhia Companhia
	 * @return Lista de departamentos
	 */
	List<DepartamentoVO> obterComRestricaoGrupoAcesso(int codUsuario, CompanhiaSeguradoraVO companhia);
	
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
