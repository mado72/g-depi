/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.vo.AgenciaVO;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;

/**
 * 
 * Define os métodos para consultar bancos.
 * @author Marcelo Damasceno 
 */
public interface BancoDAO {

	/**
	 * Retorna por Cia os Bancos das Contas Correntes Autorizadas.
	 * 
	 * @param cia Companhia
	 * @return Lista de bancos
	 */
	List<BancoVO> obterBancos(CompanhiaSeguradoraVO cia);

	/**
	 * Obtém as agências 
	 * @param ciaVO Companhia
	 * @param bancoVO Banco
	 * @return Agências
	 */
	List<AgenciaVO> obterAgencias(CompanhiaSeguradoraVO ciaVO, BancoVO bancoVO);

}
