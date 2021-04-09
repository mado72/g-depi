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
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoSinteticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoSinteticoVO;

/**
 * Consultar Relatório Facade 
 * @author Globality
 */
public interface ConsultarRelatorioFacade {
	
	/**
	 * Lista as companhias
	 * @return Lista de CompanhiaSeguradoraVO
	 */
	public List<CompanhiaSeguradoraVO>  carregarComboCompanhia();
   
   /**
    * Lista as companhias para o usuário logado
    * @param loginVO Dados do login
    * @return Lista de Companhias
    */
   public List<CompanhiaSeguradoraVO>  carregarComboCompanhiaUsuLogado(LoginVo loginVO);

   /**
    * Lista departamentos
    * @return Lista os departamentos
    */
   public List<DepartamentoVO>  carregarComboDepartamentos();

   /**
    * Obtém motivos com restrição
    * @param codigoCompanhia Código de companhia
    * @param codigoDepartamento Código do departamento
    * @param loginVO Dados do login
    * @return Lista de movimentos
    */
   public List<MotivoDepositoVO>  obterMotivoComRestricaoDeDeposito(int codigoCompanhia, int codigoDepartamento, LoginVo loginVO);

   
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
    * @param filtro Filtro
    * @return ManutencoesAnaliticoVO
    */
   public List<ManutencoesAnaliticoVO> obterDadosManutencoesAnalitico(FiltroUtil filtro);

   /**
    * Obtém dados complementares
    * @param filtro Filtro
    * @return Lista de relatórios complementares
    */
   public List<RelatorioDadosComplementaresVO> obterDadosComplementares(FiltroUtil filtro);

   /**
    * Obtém dados manutenções sintético
    * @param filtro Filtro
    * @return ManutencoesSinteticoVO
    */
   public List<ManutencoesSinteticoVO> obterDadosManutencoesSintetico(FiltroUtil filtro);

   /**
    * Obtém dados envio retorno analítico
    * @param filtro Filtro
    * @return lista de RelatorioEnvioRetornoAnaliticoVO
    */
   public List<RelatorioEnvioRetornoAnaliticoVO> obterDadosEnvioRetornoAnalitico(FiltroUtil filtro); 

   /**
    * Obtém dados envio retorno sintético
    * @param filtro Filtro
    * @return Lista de RelatorioEnvioRetornoSinteticoVO
    */
   public List<RelatorioEnvioRetornoSinteticoVO> obterDadosEnvioRetornoSintetico(FiltroUtil filtro); 
   
   /**
    * Obtém dados banco extrato analítico
    * @param filtro Filtro
    * @return Lista de RelatorioEnvioRetornoAnaliticoVO
    */
   public List<RelatorioEnvioRetornoAnaliticoVO> obterDadosBancoExtratoAnalitico(FiltroUtil filtro);

   /**
    * Obter dados extrato sintetico
    * @param filtro Filtro
    * @return Lista de RelatorioExtratoSinteticoVO
    */
   public List<RelatorioExtratoSinteticoVO> obterDadosExtratoSintetico(FiltroUtil filtro);

   /**
    * Preenche dados de totais
    * @param dados Dados
    */
   public void obterTotais(List<RelatorioExtratoAnaliticoVO> dados);

   /**
    * Preenche dados analítico
    * @param dados Dados
    */
   public void ordenarDadosAnalitico(List<RelatorioExtratoAnaliticoVO> dados); 

}
