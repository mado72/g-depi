package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.AssociarMotivoDepositoVO;

/**
 * Interface de AssociarMotivoDepositoDAODB2
 */
public interface AssociarMotivoDepositoDAO {
	/**
     * Obter as Associa��es de Motivos
     * @param filtro - CriterioFiltroUtil.
     * @return List<AssociarMotivoDepositoVO>.
     */
	List<AssociarMotivoDepositoVO> obterPorFiltro(FiltroUtil filtro);

	
	/**
     * M�todo de obter por filtro
     * @param filtro par�metro dep�sito com o c�digo do objeto requisitado
     * @param codigoUsuario - BigDecimal.
     * @return List<AssociarMotivoDepositoVO>
     */
	List<AssociarMotivoDepositoVO> obterPorFiltroComRestricaoDeGrupoAcesso(FiltroUtil filtro, Integer codigoUsuario);

	/**
     * M�todo inserir
     * @param AssociarMotivoDepositoVO
     */
	void inserir(AssociarMotivoDepositoVO vo);

	/**
     * M�todo excluir
     * @param AssociarMotivoDepositoVO
     */
	void excluir(AssociarMotivoDepositoVO vo);

	/**
     * M�todo isReferenciado
     * @param AssociarMotivoDepositoVO
     * @return Boolean
     */
	Boolean isReferenciado(AssociarMotivoDepositoVO vo);

	/**
     * M�todo obterPorChave
     * @param AssociarMotivoDepositoVO
     * @return AssociarMotivoDepositoVO
     */
	AssociarMotivoDepositoVO obterPorChave(AssociarMotivoDepositoVO vo);

}
