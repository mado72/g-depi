package br.com.bradseg.depi.depositoidentificado.relatorio.facade;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioDadosComplementaresVO;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ManutencoesAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ManutencoesSinteticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoSinteticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoSinteticoVO;

public interface ConsultarRelatorioFacade {
	
   public List<CompanhiaSeguradoraVO>  carregarComboCompanhia();
   public List<CompanhiaSeguradoraVO>  carregarComboCompanhiaUsuLogado(LoginVo loginVO);
   public List<DepartamentoVO>  carregarComboDepartamentos();
   public List<MotivoDepositoVO>  obterMotivoComRestricaoDeDeposito(int codigCompanhia, int codigoDepartamento, LoginVo loginVO);

   
   public List<DepartamentoVO> obterComDepositoRestricaoDeDeposito(int codigCompanhia, LoginVo loginVO);
   public List<DepartamentoCompanhiaVO> obterPorFiltro(FiltroUtil filtro) throws IntegrationException;
   
   
   public List<ManutencoesAnaliticoVO> obterDadosManutencoesAnalitico(FiltroUtil filtro);
   public List<RelatorioDadosComplementaresVO> obterDadosComplementares(FiltroUtil filtro);
   public List<ManutencoesSinteticoVO> obterDadosManutencoesSintetico(FiltroUtil filtro);
   public List<RelatorioEnvioRetornoAnaliticoVO> obterDadosEnvioRetornoAnalitico(FiltroUtil filtro); 
   public List<RelatorioEnvioRetornoSinteticoVO> obterDadosEnvioRetornoSintetico(FiltroUtil filtro); 
   public List<RelatorioEnvioRetornoAnaliticoVO> obterDadosBancoExtratoAnalitico(FiltroUtil filtro);
   public List<RelatorioExtratoSinteticoVO> obterDadosExtratoSintetico(FiltroUtil filtro);
   
   public void obterTotais(List<RelatorioExtratoAnaliticoVO> dados);
   public void ordenarDadosAnalitico(List<RelatorioExtratoAnaliticoVO> dados); 
   
   
   
   
   
   
   
   
   
   
   
   
   
   
	

}
