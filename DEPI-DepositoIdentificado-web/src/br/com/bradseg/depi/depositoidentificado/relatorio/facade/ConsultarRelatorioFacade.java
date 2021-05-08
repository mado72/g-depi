package br.com.bradseg.depi.depositoidentificado.relatorio.facade;

import java.util.List;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ManutencoesAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ManutencoesSinteticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioDadosComplementaresVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoSinteticoVO;

/**
 * Consultar Relatório Facade 
 * @author Globality
 */
public interface ConsultarRelatorioFacade {
	
   /**
    * Lista as companhias para o usuário logado
    * @param loginVO Dados do login
    * @return Lista de Companhias
    */
   public List<CompanhiaSeguradoraVO>  carregarComboCompanhiaUsuLogado(LoginVo loginVO);

   /**
    * Obtém motivos com restrição
    * @param codigoCompanhia Código de companhia
    * @param codigoDepartamento Código do departamento
    * @param loginVO Dados do login
    * @return Lista de movimentos
    */
	public List<MotivoDepositoVO> obterMotivoComRestricaoDeDeposito(
			int codigoCompanhia, int codigoDepartamento, LoginVo loginVO);
   
   /**
    * Obtém departamentos com restrição
    * @param codigoCompanhia Código de companhia
    * @param loginVO Dados do login
    * @return Lista de departamentos
    */
   public List<DepartamentoVO> obterComDepositoRestricaoDeDeposito(int codigoCompanhia, LoginVo loginVO);
   /**
    * Obtém departamentos companhia por filtro
    * @param filtro Filtro
    * @return Lista de departamentos x cia
    * @throws IntegrationException Erro na consulta
    */
   public List<DepartamentoCompanhiaVO> obterPorFiltro(FiltroUtil filtro) throws IntegrationException;


	/**
	 * Obtém dados de manutenções para relatório analítico
	 * 
	 * @param filtro
	 *            Filtro
	 * @param ipCliente
	 *            IP corrente
	 * @param uerId
	 *            Código do usuário logado 
	 * @return ManutencoesAnaliticoVO
	 */
	public List<ManutencoesAnaliticoVO> obterDadosManutencoesAnalitico(
			FiltroUtil filtro, String ipCliente, int uerId);

   /**
    * Obtém dados complementares
    * @param filtro Filtro
    * @return Lista de relatórios complementares
    */
   public List<RelatorioDadosComplementaresVO> obterDadosComplementares(FiltroUtil filtro);

   	/**
	 * Obtém dados manutenções sintético
	 * 
	 * @param filtro
	 *            Filtro
	 * @param clienteIp
	 *            IP corrente
	 * @param userId
	 *            usuário logado
	 * @return ManutencoesSinteticoVO
	 */
   public List<ManutencoesSinteticoVO> obterDadosManutencoesSintetico(FiltroUtil filtro, String clienteIp, int userId);

	/**
	 * Obtém dados de envio analítico
	 * 
	 * @param filtro
	 *            Filtro
	 * @param ipCliente
	 *            IP corrente
	 * @param codResponsavel
	 *            Código do usuário logado
	 * @return lista de RelatorioEnvioRetornoVO
	 */
	public List<RelatorioEnvioRetornoVO> obterDadosEnvioRetornoAnalitico(
			FiltroUtil filtro, String ipCliente, int codResponsavel);
   
	/**
	 * Obtém dados de envio sintético
	 * 
	 * @param filtro
	 *            Filtro
	 * @param ipCliente
	 *            IP corrente
	 * @param codResponsavel
	 *            Código do usuário logado
	 * @return lista de RelatorioEnvioRetornoVO
	 */
	public List<RelatorioEnvioRetornoVO> obterDadosEnvioRetornoSintetico(
			FiltroUtil filtro, String ipCliente, int codResponsavel);
   
	/**
	 * Obtém dados banco extrato analítico
	 * 
	 * @param filtro
	 *            Filtro
	 * @param ipCliente
	 *            IP corrente
	 * @param codResponsavel
	 *            Código do usuário logado
	 * @return Lista de RelatorioEnvioRetornoAnaliticoVO
	 */
	public List<RelatorioExtratoAnaliticoVO> obterDadosBancoExtratoAnalitico(
			FiltroUtil filtro, String ipCliente, int codResponsavel);

	/**
	 * Obter dados extrato sintetico
	 * 
	 * @param filtro
	 *            Filtro
	 * @return Lista de RelatorioExtratoSinteticoVO
	 */
	public List<RelatorioExtratoSinteticoVO> obterDadosBancoExtratoSintetico(
			FiltroUtil filtro);

}
