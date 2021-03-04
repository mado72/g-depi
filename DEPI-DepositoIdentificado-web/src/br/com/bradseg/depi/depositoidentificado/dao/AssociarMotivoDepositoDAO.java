package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.AssociarMotivoDepositoVO;

/**
 * Interface de AssociarMotivoDepositoDAODB2
 */
public interface AssociarMotivoDepositoDAO {

	/**
	 * Método de obter por filtro
	 * 
	 * @param filtro
	 *            parâmetro depósito com o código do objeto requisitado
	 * @param codigoUsuario
	 *            - Código do usuário.
	 * @return List<AssociarMotivoDepositoVO>
	 */
	List<AssociarMotivoDepositoVO> obterPorFiltroComRestricaoDeGrupoAcesso(
			FiltroUtil filtro, int codigoUsuario);

	/**
	 * Método inserir
	 * 
	 * @param AssociarMotivoDepositoVO
	 *            - item a ser inserido
	 */
	void inserir(AssociarMotivoDepositoVO vo);

	/**
	 * Método excluir
	 * 
	 * @param AssociarMotivoDepositoVO
	 *            - item a ser excluído
	 */
	void excluir(AssociarMotivoDepositoVO vo);

	/**
	 * Método isReferenciado
	 * 
	 * @param AssociarMotivoDepositoVO
	 *            - item a ser verificado
	 * @return Boolean - True quando é referenciado, False quando não é, Null
	 *         quando não disponível
	 */
	Boolean isReferenciado(AssociarMotivoDepositoVO vo);

	/**
	 * Método obterPorChave
	 * 
	 * @param AssociarMotivoDepositoVO
	 *            - Contém a chave de pesquisa
	 * @return AssociarMotivoDepositoVO - Item encontrado ou nulo.
	 */
	AssociarMotivoDepositoVO obterPorChave(AssociarMotivoDepositoVO vo);

}
