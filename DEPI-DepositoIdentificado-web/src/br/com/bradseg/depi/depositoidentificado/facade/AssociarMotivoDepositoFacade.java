package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.AssociarMotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

/**
 * Interface de serviço para atender ao cadastro para associar motivo depósito.
 */
public interface AssociarMotivoDepositoFacade {

	/**
	 * Retorna lista de associações de motivo depósito que atendem aos critérios de consulta
	 * @param codUsuario Código do usuário
	 * @param filtro Critérios de consulta
	 * @return Lista
	 */
	List<AssociarMotivoDepositoVO> obterPorFiltro(int codUsuario,
			FiltroUtil filtro);

	/**
	 * Obtém uma instância com base na chave primária
	 * @param vo Contém a chave primária
	 * @return Entidade encontrada ou null
	 */
	AssociarMotivoDepositoVO obterPorChave(AssociarMotivoDepositoVO vo);

	/**
	 * Insere uma nova instância
	 * @param vo Dados para persistir
	 */
	void inserir(AssociarMotivoDepositoVO vo);

	/**
	 * Exclui uma lista de instâncias de {@link AssociarMotivoDepositoVO}
	 * @param voList Lista
	 */
	void excluirLista(List<AssociarMotivoDepositoVO> voList);

	/**
	 * Lista as companhias a que um usuário tem acesso
	 * @param codUsuario Código do usuário
	 * @return Lista de companhias
	 */
	List<CompanhiaSeguradoraVO> obterCompanhias(int codUsuario);

	/**
	 * Lista os departamentos de uma companhia a que o usuário tem acesso
	 * @param codUsuario Código do usuário
	 * @param ciaVO Companhia
	 * @return Lista de departamentos
	 */
	List<DepartamentoVO> obterDepartamentos(int codUsuario,
			CompanhiaSeguradoraVO ciaVO);

	/**
	 * Lista os motivos de depósito associados a um departamento a que o usuário tem acesso
	 * @param codUsuario Código do usuário
	 * @param depto Departamento
	 * @return Lista de motivos de depósito
	 */
	List<MotivoDepositoVO> obterMotivosDeposito(int codUsuario,
			DepartamentoVO depto);

	/**
	 * Lista os bancos relacionados a uma companhia
	 * @param cia Companhia
	 * @return Lista de bancos
	 */
	List<BancoVO> obterBancos(CompanhiaSeguradoraVO cia);

	/**
	 * Obtém os dados de um banco
	 * @param vo Contém a código do banco
	 * @return Banco
	 */
	BancoVO obterBanco(BancoVO vo);

}
