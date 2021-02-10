package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.List;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.GrupoAcessoVO;
import br.com.bradseg.depi.depositoidentificado.vo.UsuarioVO;

/**
 * Interface que define os métodos necessários para o cadastro de grupo acesso. 
 * @author Marcelo Damasceno
 */
public interface GrupoAcessoFacade {

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
	 * Obtém as companhias disponíveis na base
	 * 
	 * @return Lista de Companhias
	 */
	List<CompanhiaSeguradoraVO> obterCompanhias();

	/**
	 * Obtém os departamentos disponíveis para o cadastro de Grupo de Acesso
	 * 
	 * @param vo Companhia Seguradora
	 * @return Lista de Departamentos
	 */
	List<DepartamentoVO> obterDepartamentos(CompanhiaSeguradoraVO vo);

	/**
	 * Obtém dados da companhia
	 * @param vo Companhia
	 * @return Dados da companhia
	 */
	CompanhiaSeguradoraVO obterCompanhia(CompanhiaSeguradoraVO vo);

	/**
	 * Obtém dados do Departamento
	 * @param vo Companhia
	 * @param deptoVO Departamento
	 * @return Departamento
	 */
	DepartamentoVO obterDepartamento(CompanhiaSeguradoraVO vo, DepartamentoVO deptoVO);

	/**
	 * Obtém lista de funcionários
	 * @param codFuncionarios Códigos dos funcionários
	 * @return lista
	 */
	List<UsuarioVO> obterUsuarios(List<Integer> codFuncionarios);

}
