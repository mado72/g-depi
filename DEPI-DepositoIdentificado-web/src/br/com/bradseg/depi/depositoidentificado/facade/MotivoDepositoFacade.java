package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.model.enumerated.Tabelas;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

/** 
 * A(O) MotivoDepositoFacade.
 */
public interface MotivoDepositoFacade {
	
	public MotivoDepositoVO obterPorChave(MotivoDepositoVO vo);

	public void alterar(MotivoDepositoVO vo);

	public void excluir(MotivoDepositoVO vo);

	public boolean isReferenciado(MotivoDepositoVO vo);

	public void inserir(MotivoDepositoVO vo);

	public List<MotivoDepositoVO> obterPorFiltroMotivoDepositvo(FiltroUtil filtro);

	public List<MotivoDepositoVO> obterTodosMotivoDepositvo();

	public List<MotivoDepositoVO> obterComRestricaoDeGrupoAcesso(int codigoCia, int codigoDep, int codigoUsuario, Tabelas e);

	public void excluirLista(List<MotivoDepositoVO> vo);

}
