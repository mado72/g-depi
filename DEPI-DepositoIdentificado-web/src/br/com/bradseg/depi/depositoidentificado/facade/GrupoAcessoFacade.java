package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.List;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.GrupoAcessoVO;

public interface GrupoAcessoFacade {

	GrupoAcessoVO obterGrupoPorChave(GrupoAcessoVO grupo) throws IntegrationException;

	List<GrupoAcessoVO> obterTodos() throws IntegrationException;

	GrupoAcessoVO obterPorChave(GrupoAcessoVO grupo) throws IntegrationException;

	void validarChaves(GrupoAcessoVO grupo) throws IntegrationException;

	void validarParametrosInclusao(GrupoAcessoVO grupo) throws IntegrationException;

	void validarObjetos(GrupoAcessoVO grupo) throws IntegrationException;

	int inserir(GrupoAcessoVO grupo) throws IntegrationException;

	void excluir(GrupoAcessoVO grupo) throws IntegrationException;

	void excluir(List<GrupoAcessoVO> grupos) throws IntegrationException;

	void alterar(GrupoAcessoVO grupo) throws IntegrationException;

	List<GrupoAcessoVO> obterPorFiltro(FiltroUtil filtro) throws IntegrationException;

}
