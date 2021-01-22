/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.enums.Tabelas;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

/**
 * Interface de MotivoDepositoDAO.
 * @author Globality
 */
public interface MotivoDepositoDAO{
	
	/**
     * Obtém apenas os Motivos que estão associados a Parametros de Depósito.
     * @param codigoCia - int.
     * @param codigoDep - int.
     * @param codigoUsuario - String.
     * @param e - Tabelas.
     * @return List<MotivoDepositoVO>.
     */
	List<MotivoDepositoVO> obterComRestricaoDeGrupoAcesso(final int codigoCia, final int codigoDep, final Double codigoUsuario, final Tabelas e);

	public void inserir(MotivoDepositoVO vo );

	public void alterar(MotivoDepositoVO vo );

	public void excluir(MotivoDepositoVO vo );

	public Boolean isReferenciado(MotivoDepositoVO vo);

	public MotivoDepositoVO obterPorChave(MotivoDepositoVO vo);

	public List<MotivoDepositoVO> obterPorFiltro(FiltroUtil filtro);

	public List<MotivoDepositoVO> obterTodos();

	void excluirLista(List<MotivoDepositoVO> listvo);

}
