/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.model.enumerated.Tabelas;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

/**
 * Interface de MotivoDepositoDAO.
 * @author Globality
 */
public interface MotivoDepositoDAO{
	
	/**
     * Obtém apenas os Motivos que est�o associados a Parâmetros de Depósito.
	 * @param codigoCia código da companhia
	 * @param codigoDep código do departamento
	 * @param codigoUsuario código do usuário
	 * @param e Tabela de restrição 
	 * @return List<MotivoDepositoVO>.
	 */
	List<MotivoDepositoVO> obterComRestricaoDeGrupoAcesso(final int codigoCia, final int codigoDep, final int codigoUsuario, final Tabelas e);

	public void inserir(MotivoDepositoVO vo );

	public void alterar(MotivoDepositoVO vo );

	public void excluir(MotivoDepositoVO vo );

	public Boolean isReferenciado(MotivoDepositoVO vo);

	public MotivoDepositoVO obterPorChave(MotivoDepositoVO vo);

	public List<MotivoDepositoVO> obterPorFiltro(FiltroUtil filtro);

	public List<MotivoDepositoVO> obterTodos();

	void excluirLista(List<MotivoDepositoVO> listvo);

}
