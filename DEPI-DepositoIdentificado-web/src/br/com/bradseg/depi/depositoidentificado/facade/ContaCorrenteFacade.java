/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
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
	 * @param codUsuario Código do usuário
	 * @param cia Chave da companhia
	 * @return Cia
	 */
	CompanhiaSeguradoraVO obterCompanhia(int codUsuario,
			CompanhiaSeguradoraVO cia);

	/**
	 * Obtém companhias associadas ao usuário
	 * @param codUsuario Código do usuário
	 * @return Lista de companhias
	 */
	List<CompanhiaSeguradoraVO> obterCompanhias(int codUsuario);

	/**
	 * Retorna por Cia os Bancos das Contas Correntes Autorizadas.
	 * 
	 * @param cia Companhia
	 * @return Lista de bancos
	 */
	List<BancoVO> obterBancos(CompanhiaSeguradoraVO cia);

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

	/**
	 * Retorna lista de contas correntes que atendem aos critérios de consulta
	 * @param codUsuario Código do usuário 
	 * @param filtro Critérios
	 * @return Lista
	 */
	List<ContaCorrenteAutorizadaVO> obterPorFiltro(int codUsuario, FiltroUtil filtro);

	/**
	 * Obtém um banco pelo seu código
	 * @param vo Contém o código do banco (código externo)
	 * @return Dados do banco
	 */
	BancoVO obterBanco(BancoVO vo);

	/**
	 * Obter descrição da agência com base no código externo do banco e no
	 * código externo da agência
	 * 
	 * @param ccVO Contém os códigos
	 * @return Descrição da Agência
	 */
	String obterAgencia(ContaCorrenteAutorizadaVO ccVO);

	/**
	 * Obter número da conta interna
	 * @param ccVO Dados da conta
	 * @return número da conta interna
	 */
	Integer obterContaInterna(ContaCorrenteAutorizadaVO ccVO);

}
