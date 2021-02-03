package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.List;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
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

	/**
	 * Obtém as companhias disponíveis para o cadastro de Grupo de Acesso
	 * 
	 * @param codUsuario
	 *            Código do usuário logado
	 * @return Lista de Companhias
	 */
	List<CompanhiaSeguradoraVO> obterCompanhias(Double codUsuario);

	/**
	 * Obtém os departamentos disponíveis para o cadastro de Grupo de Acesso
	 * 
	 * @param codUsuario
	 *            Código do usuário logado
	 * @param codCia
	 *            Código da companhia
	 * @return Lista de Departamentos
	 */
	List<DepartamentoVO> obterDepartamentos(Integer codCia, double codUsuario);

}
