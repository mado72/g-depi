/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;

/**
 * Interface para regras de negócio no cadastro de conta corrente.
 * 
 * @author Marcelo Damasceno
 */
public interface ContaCorrenteFacade {

	/**
	 * Obtém uma companhia seguradora
	 * @param cia Chave da companhia
	 * @return Cia
	 */
	CompanhiaSeguradoraVO obterCompanhia(CompanhiaSeguradoraVO cia);

	/**
	 * Obtém companhias associadas ao usuário
	 * @param codUsuario Código do usuário
	 * @return Lista de companhias
	 */
	List<CompanhiaSeguradoraVO> obterCompanhias(int codUsuario);

	/**
	 * Obtém a instância do banco de dados
	 * @param vo Contém os dados da chave
	 * @return ContaCorrenteAutorizadaVO
	 */
	ContaCorrenteAutorizadaVO obterPorChave(ContaCorrenteAutorizadaVO vo);

	/**
	 * Insere um novo registro
	 * @param instancia Instância a ser inserida.
	 */
	void inserir(ContaCorrenteAutorizadaVO instancia);

	/**
	 * Altera um registro
	 * @param instancia registro a ser alterado
	 */
	void alterar(ContaCorrenteAutorizadaVO instancia);

	/**
	 * Exclui lista de registros
	 * @param voList lista
	 */
	void excluirLista(List<ContaCorrenteAutorizadaVO> voList);

}
