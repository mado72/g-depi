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
			int codUsuario);

	/**
	 * Obtém a companhia com restrição de acesso ao usuário logado
	 * @param usuarioLogadoId Código do usuário logado
	 * @param codCompanhia Código da companhia
	 * @return Instância
	 */
	CompanhiaSeguradoraVO obterComRestricaoDeGrupoAcesso(int usuarioLogadoId,
			int codCompanhia);

}
