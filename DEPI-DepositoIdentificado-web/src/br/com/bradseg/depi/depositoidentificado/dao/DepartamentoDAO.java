/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.model.enumerated.Tabelas;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;


/**
 * Interface de DepartamentoDAO
 * @author fabio.pinto
 */
public interface DepartamentoDAO {

	/**
     * método para obter companhias
     * @param vo - objeto companhia seguradora
     * @return - objetos compatives com o parametro
     */
	List<DepartamentoVO> obterPorCompanhiaSeguradora(CompanhiaSeguradoraVO vo);

	/**
     * Obter Departamentos por Usuário.
     * @param codigoCia - int.
     * @param codigoUsuario - int.
     * @param e - EQueryJoinForward. 
     * @return List<DepartamentoVO>.
     */
	public List<DepartamentoVO> obterComRestricaoDeGrupoAcesso(int codigoCia, int codigoUsuario, Tabelas e);
	//List<DepartamentoVO> obterComRestricaoDeGrupoAcesso(int codigoCia, BigDecimal codigoUsuario, Tabelas e);

	/**
	 * Obter um departamento
	 * @param codigoCia Companhia
	 * @param usuarioLogadoId Usuário
	 * @param grupoAcesso Tabela de restrição do Grupo Acesso
	 * @param siglaDepto Sigla do Departamento
	 * @return Departamento
	 */
	DepartamentoVO obterComRestricaoDeGrupoAcesso(int codigoCia,
			int usuarioLogadoId, Tabelas grupoAcesso, String siglaDepto);

	/**
	 * Excluir Departamentos. 
	 */
	public void excluir(DepartamentoVO vo);

	public boolean isReferenciado(DepartamentoVO vo);

	public void excluir(List<DepartamentoVO> vos);

	public void alterar(DepartamentoVO vo);

	public void inserir(DepartamentoVO vo);

	public DepartamentoVO obterPorChave(DepartamentoVO vo);

	public List<DepartamentoVO> obterTodos();

	public List<DepartamentoVO>  obterPorFiltro(FiltroUtil filtro);

	DepartamentoVO obterPorCompanhiaSeguradora(CompanhiaSeguradoraVO vo,
			DepartamentoVO deptoVO);

	/**
	 * Busca os departamentos que correspondem a uma lista de siglas
	 * @param siglas Lista de Siglas
	 * @return Lista de departamentos
	 */
	List<DepartamentoVO> obterDeListaSiglas(List<String> siglas);

}
