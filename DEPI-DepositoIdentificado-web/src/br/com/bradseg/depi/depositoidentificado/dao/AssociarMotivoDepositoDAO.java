package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.AssociarMotivoDepositoVO;

/**
 * Interface de AssociarMotivoDepositoDAODB2
 */
public interface AssociarMotivoDepositoDAO {
	/**
     * Obter as Associações de Motivos
     * @param filtro - CriterioFiltroUtil.
     * @return List<AssociarMotivoDepositoVO>.
     */
	List<AssociarMotivoDepositoVO> obterPorFiltro(FiltroUtil filtro);

	
	/**
     * Método de obter por filtro
     * @param filtro parâmetro depósito com o código do objeto requisitado
     * @param codigoUsuario - Código do usuário.
     * @return List<AssociarMotivoDepositoVO>
     */
	List<AssociarMotivoDepositoVO> obterPorFiltroComRestricaoDeGrupoAcesso(FiltroUtil filtro, int codigoUsuario);

	/**
     * Método inserir
     * @param AssociarMotivoDepositoVO
     */
	void inserir(AssociarMotivoDepositoVO vo);

	/**
     * Método excluir
     * @param AssociarMotivoDepositoVO
     */
	void excluir(AssociarMotivoDepositoVO vo);

	/**
     * Método isReferenciado
     * @param AssociarMotivoDepositoVO
     * @return Boolean
     */
	Boolean isReferenciado(AssociarMotivoDepositoVO vo);

	/**
     * Método obterPorChave
     * @param AssociarMotivoDepositoVO
     * @return AssociarMotivoDepositoVO
     */
	AssociarMotivoDepositoVO obterPorChave(AssociarMotivoDepositoVO vo);

}
