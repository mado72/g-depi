/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.model.enumerated.Tabelas;
import br.com.bradseg.depi.depositoidentificado.vo.AgenciaVO;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

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
	 * Retorna os bancos para a relação cia x depto x motivo
	 * @param cia Cia
	 * @param depto Depto
	 * @param motivo Motivo
	 * @return Bancos
	 */
	List<BancoVO> obterBancosMotivoDeposito(CompanhiaSeguradoraVO cia,
			DepartamentoVO depto, MotivoDepositoVO motivo);

	/**
	 * Obtém as agências 
	 * @param ciaVO Companhia
	 * @param bancoVO Banco
	 * @return Agências
	 */
	List<AgenciaVO> obterAgencias(CompanhiaSeguradoraVO ciaVO, BancoVO bancoVO);

	/**
	 * @param depto TODO
	 * @param contaCorrenteMotivoDeposito Restrição de acesso
	 * @param codMotivo Código do motivo
	 * @return Lista de agências
	 */
	/**
	 * Obtém as agências com restrição de acesso
	 * @param ciaVO Companhia
	 * @param depto Depto
	 * @param bancoVO Banco
	 * @param motivoVO Motivo
	 * @param tabela Tabela de restrição
	 * @return Lista de agências
	 */
	List<AgenciaVO> obterAgenciasComRestricaoMotivo(
			CompanhiaSeguradoraVO ciaVO, DepartamentoVO depto, BancoVO bancoVO,
			MotivoDepositoVO motivoVO, Tabelas tabela);

}
