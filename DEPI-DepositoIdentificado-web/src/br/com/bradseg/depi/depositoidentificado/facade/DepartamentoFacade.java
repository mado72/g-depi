package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.List;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.enums.Tabelas;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;

/** 
 * A(O) DepartamentoFacade.
 */
public interface DepartamentoFacade {

	public List<DepartamentoVO> obterPorCompanhiaSeguradora(CompanhiaSeguradoraVO vo) throws IntegrationException;

	public List<DepartamentoVO> obterPorFiltro(FiltroUtil filtro);

	public List<DepartamentoVO> obterComRestricaoDeGrupoAcesso(int codigoCia, Double codigoUsuario, Tabelas e) throws IntegrationException;

	public List<DepartamentoVO> obterTodos() throws IntegrationException;

	public DepartamentoVO obterPorChave(DepartamentoVO vo) throws IntegrationException;

	public void inserir(DepartamentoVO vo) throws IntegrationException;

	public void excluir(List<DepartamentoVO> vos) throws IntegrationException;

	public  void excluir(DepartamentoVO vo) throws IntegrationException;

	public void alterar(DepartamentoVO vo) throws IntegrationException;

}
