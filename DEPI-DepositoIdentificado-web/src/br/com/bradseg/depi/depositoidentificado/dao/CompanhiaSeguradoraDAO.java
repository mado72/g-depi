/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;

/**
 * Interface para DAO Companhia Seguradora
 * @author Marcelo Damasceno
 *
 */
public interface CompanhiaSeguradoraDAO {

	/**
	 * Lista as companhias com restrição de grupo acesso 
	 * @param codUsuario Código do usuário logado
	 * @return Lista de companhias
	 */
	List<CompanhiaSeguradoraVO> obterComRestricaoDeGrupoAcesso(
			double codUsuario);

}
