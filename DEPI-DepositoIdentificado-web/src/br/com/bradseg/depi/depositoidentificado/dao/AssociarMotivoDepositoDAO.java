package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.AssociarMotivoDepositoVO;

/**
 * Interface para acesso aos dados de AssociarMotivoDeposito
 */
public interface AssociarMotivoDepositoDAO {

	/**
	 * Consulta com restrição de acesso
	 * 
	 * @param filtro
	 *            Critérios de consultas
	 * @param codigoUsuario
	 *            Código do usuario
	 * @return Lista de associações
	 */
	List<AssociarMotivoDepositoVO> obterPorFiltroComRestricaoDeGrupoAcesso(
			FiltroUtil filtro, int codigoUsuario);

	/**
	 * Insere um registro
	 * 
	 * @param vo
	 *            item a ser inserido
	 */
	void inserir(AssociarMotivoDepositoVO vo);

	/**
	 * Exclui um registro
	 * 
	 * @param vo
	 *            Item a ser excluído
	 */
	void excluir(AssociarMotivoDepositoVO vo);

	/**
	 * Verifica se o registro é referenciado
	 * 
	 * @param vo
	 *            Registro a ser verificado
	 * @return true quando é referenciado
	 */
	Boolean isReferenciado(AssociarMotivoDepositoVO vo);

	/**
	 * Obtém um registro pela sua chave
	 * 
	 * @param vo
	 *            Contém a chave
	 * @return Registro encontrado ou nulo.
	 */
	AssociarMotivoDepositoVO obterPorChave(AssociarMotivoDepositoVO vo);

}
