package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.List;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoVO;

public interface ParametroDepositoFacade {

	void excluir(ParametroDepositoVO vo) throws IntegrationException;

	void alterar(ParametroDepositoVO vo) throws IntegrationException;

	void excluir(List<ParametroDepositoVO> lista) throws IntegrationException;

	void inserir(ParametroDepositoVO vo) throws IntegrationException;

	List<ParametroDepositoVO> obterPorFiltro(FiltroUtil filtro) throws IntegrationException;

	ParametroDepositoVO obterPorChave(ParametroDepositoVO vo) throws IntegrationException;

	List<ParametroDepositoVO> obterTodos() throws IntegrationException;

	List<ParametroDepositoVO> obterPorFiltroComRestricaoDeGrupoAcesso(FiltroUtil filtro, Integer codigoUsuario) throws IntegrationException;

}
