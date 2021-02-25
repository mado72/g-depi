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
	 * @param vo Departamento a excluir
	 */
	public void excluir(DepartamentoVO vo);

	/**
	 * Verifica se o departamento é referenciado
	 * @param vo Departamento
	 * @return true se for referenciado
	 */
	public boolean isReferenciado(DepartamentoVO vo);

	/**
	 * Exclui uma lista de departamentos
	 * @param vos Lista
	 */
	public void excluir(List<DepartamentoVO> vos);

	/**
	 * Persiste alteração de um departamento
	 * @param vo Departamento
	 */
	public void alterar(DepartamentoVO vo);

	/**
	 * Insere um novo Departamento
	 * @param vo Departamento
	 */
	public void inserir(DepartamentoVO vo);

	/**
	 * Obtém um departamento pela sua chave
	 * @param vo Departamento que possui a chave preenchida
	 * @return Departamento com dados obtidos do repositório
	 */
	public DepartamentoVO obterPorChave(DepartamentoVO vo);

	/**
	 * Lista os Departamentos que atendem aos critérios de consulta  
	 * @param filtro Filtro com critérios de consulta
	 * @return Lista de Departamento
	 */
	public List<DepartamentoVO>  obterPorFiltro(FiltroUtil filtro);

	/**
	 * Obtém um departamento pela sua sigla quando associado a uma companhia
	 * @param vo Companhia
	 * @param deptoVO Possui a sigla do departamento
	 * @return Departamento quando existe departamento associado à Companhia Seguradora
	 */
	DepartamentoVO obterPorCompanhiaSeguradora(CompanhiaSeguradoraVO vo,
			DepartamentoVO deptoVO);

	/**
	 * Busca os departamentos que correspondem a uma lista de siglas
	 * @param siglas Lista de Siglas
	 * @return Lista de departamentos
	 */
	List<DepartamentoVO> obterDeListaSiglas(List<String> siglas);

}
