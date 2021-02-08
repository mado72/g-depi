package br.com.bradseg.depi.depositoidentificado.relatorio.facade;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.depi.depositoidentificado.dao.RelatorioManutencoesDAO;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ManutencoesAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ManutencoesSinteticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioDadosComplementaresVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoSinteticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoSinteticoVO;


@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ConsultarRelatorioFacadeImpl implements ConsultarRelatorioFacade {
	
	@Autowired
	private RelatorioManutencoesDAO dao;

	@Override
	public List<CompanhiaSeguradoraVO> carregarComboCompanhia() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DepartamentoVO> carregarComboDepartamentos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MotivoDepositoVO> carregarComboMotivos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompanhiaSeguradoraVO> obterComRestricaoDeDeposito(
			LoginVo loginVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ManutencoesAnaliticoVO> obterDadosManutencoesAnalitico(
			FiltroUtil filtro) {
		// TODO Auto-generated method stub
		return dao.obterDadosAnalitico(filtro);
	}

	@Override
	public List<RelatorioDadosComplementaresVO> obterDadosComplementares(
			FiltroUtil filtro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ManutencoesSinteticoVO> obterDadosManutencoesSintetico(
			FiltroUtil filtro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RelatorioEnvioRetornoAnaliticoVO> obterDadosEnvioRetornoAnalitico(
			FiltroUtil filtro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RelatorioEnvioRetornoSinteticoVO> obterDadosEnvioRetornoSintetico(
			FiltroUtil filtro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RelatorioEnvioRetornoAnaliticoVO> obterDadosBancoExtratoAnalitico(
			FiltroUtil filtro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void obterTotais(List<RelatorioExtratoAnaliticoVO> dados) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ordenarDadosAnalitico(List<RelatorioExtratoAnaliticoVO> dados) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RelatorioExtratoSinteticoVO> obterDadosExtratoSintetico(
			FiltroUtil filtro) {
		// TODO Auto-generated method stub
		return null;
	}

}
