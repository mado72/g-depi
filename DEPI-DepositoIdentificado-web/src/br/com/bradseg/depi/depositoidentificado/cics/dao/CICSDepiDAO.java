/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.cics.dao;

import java.util.Collection;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;
import br.com.bradseg.depi.depositoidentificado.vo.EventoContabilVO;
import br.com.bradseg.depi.depositoidentificado.vo.ItemContabilVO;

/**
 * Interface para acesso a funções CICS a fim de atender ao DEPI. 
 * @author Marcelo Damasceno
 */
public interface CICSDepiDAO {

	/**
	 * Obtém informações da Companhia pelo seu código.
	 * 
	 * @param codigoCompanhia
	 *            Código da companhia
	 * @return Companhia recuperada
	 */
	CompanhiaSeguradoraVO obterCiaPorCodigo(int codigoCompanhia);
	
	/**
	 * Método utilitário para obter informações das companhias
	 * @param cias Lista de companhias
	 * @return Lista
	 */
	List<CompanhiaSeguradoraVO> obterCias(Collection<CompanhiaSeguradoraVO> cias);

	/**
	 * Obtém informações de uma conta corrente no Cics
	 * @param cc Conta corrente
	 * @return Conta corrente com informações do Cics
	 */
	ContaCorrenteAutorizadaVO obterContaCorrente(ContaCorrenteAutorizadaVO cc);

	/**
	 * Obtém informações de uma lista de contas corrente do Cics
	 * @param contas Lista de contas corrente
	 * @return Lista com informações do Cics
	 */
	List<ContaCorrenteAutorizadaVO> obterContasCorrente(
			Collection<ContaCorrenteAutorizadaVO> contas);

	/**
	 * Obtém informações de um banco do Cics
	 * @param banco Banco
	 * @return Banco
	 */
	BancoVO obterBanco(BancoVO banco);
	
	/**
	 * Obtém informações de uma lista de bancos do Cics
	 * @param bancos Lista de bancos
	 * @return Lista com informações do Cics
	 */
	List<BancoVO> obterBancos(
			Collection<BancoVO> bancos);

	/**
	 * Obtém o nome da agência
	 * @param codigoBanco Banco
	 * @param codigoAgencia Agência
	 * @return Nome da Agência
	 */
	String obterAgencia(int codigoBanco, int codigoAgencia);

	/**
	 * Obtém um evento contábil
	 * @param codigoEvento Código do evento
	 * @return Evento contábil
	 */
	EventoContabilVO obterEventoContabil(int codigoEvento);

	/**
	 * Obtém um item contábil
	 * @param codigoTipoEventoNegocio Código do tipo de evento
	 * @param codigoItemContabil Código do item contábil
	 * @return Item contábil
	 */
	ItemContabilVO obterItemContabil(int codigoTipoEventoNegocio, int codigoItemContabil);

}
