package br.com.bradseg.depi.depositoidentificado.relatorio.facade;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.depi.depositoidentificado.dao.CompanhiaSeguradoraDAO;
import br.com.bradseg.depi.depositoidentificado.dao.DepartamentoDAO;
import br.com.bradseg.depi.depositoidentificado.dao.MotivoDepositoDAO;
import br.com.bradseg.depi.depositoidentificado.dao.RelatorioDadosComplementaresDAO;
import br.com.bradseg.depi.depositoidentificado.dao.RelatorioEnvioRetornoDAO;
import br.com.bradseg.depi.depositoidentificado.dao.RelatorioExtratoDAO;
import br.com.bradseg.depi.depositoidentificado.dao.RelatorioManutencoesDAO;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.Tabelas;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
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
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(ConsultarRelatorioFacadeImpl.class);
	
	@Autowired
	private RelatorioManutencoesDAO daoManutAnalitico;
	@Autowired
	private CompanhiaSeguradoraDAO daoCiaSeg;
	@Autowired
	private DepartamentoDAO daoDepartamento;
	
	@Autowired
	private MotivoDepositoDAO daoMotivoDeposito;
	@Autowired
	private RelatorioEnvioRetornoDAO daoRelatorioEnvioRetornoDAO;
	
	@Autowired
	private RelatorioDadosComplementaresDAO daoRelatorioDadosComplementares;
	
	@Autowired
	private RelatorioManutencoesDAO daoRelatorioManutencoes;
	
	@Autowired
	private RelatorioExtratoDAO daoRelatorioExtrato;
	
	
	

	@Override
	public List<CompanhiaSeguradoraVO> carregarComboCompanhia() {
	
		return daoCiaSeg.obterCias();
	}
	
	

	@Override
	public List<DepartamentoVO> carregarComboDepartamentos() {
		
		return null;
	}

	@Override
	public List<MotivoDepositoVO> obterMotivoComRestricaoDeDeposito(int codigCompanhia, int codigoDepartamento, LoginVo loginVO) {
		int codigoUsuario =Integer.parseInt(loginVO.getId());  
		return daoMotivoDeposito.obterComRestricaoDeGrupoAcesso(codigCompanhia, codigoDepartamento, codigoUsuario,Tabelas.DEPOSITO);
	}

	@Override
	   public  List<DepartamentoVO> obterComDepositoRestricaoDeDeposito(int codigCompanhia, LoginVo loginVO){
		int usuarioLogadoId =Integer.parseInt(loginVO.getId());  
		return daoDepartamento.obterComRestricaoDeGrupoAcesso(codigCompanhia, usuarioLogadoId, Tabelas.DEPOSITO);
	}

	@Override
	public List<ManutencoesAnaliticoVO> obterDadosManutencoesAnalitico(
			FiltroUtil filtro) {
		// TODO Auto-generated method stub
		return daoManutAnalitico.obterDadosAnalitico(filtro);
	}

	@Override
	public List<RelatorioDadosComplementaresVO> obterDadosComplementares(
			FiltroUtil filtro) {
		// TODO Auto-generated method stub RelatorioDadosComplementaresDAO
		try {
			return daoRelatorioDadosComplementares.obterDadosComplementaresAnalitico(filtro);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
            throw new DEPIIntegrationException(e.getMessage());
		}
	}

	@Override
	public List<ManutencoesSinteticoVO> obterDadosManutencoesSintetico(
			FiltroUtil filtro) {
		// TODO Auto-generated method stub RelatorioManutencoesDAOImpl
		return this.sintetizar(daoManutAnalitico.obterDadosAnalitico(filtro));
		
	}


    /**
     * Gera lista com dados para o relatório Sintético usando a lista de dados do relatório Análitico.
     * @param dadosAnaliticos - List<ManutencoesSinteticoVO>.
     * @throws DEPIIntegrationException - DEPIIntegrationException.
     * @return List<ManutencoesSinteticoVO>.
     */
    public List<ManutencoesSinteticoVO> sintetizar(List<ManutencoesAnaliticoVO> dadosAnaliticos) throws DEPIIntegrationException {
    	LinkedHashMap<String, ManutencoesSinteticoVO> map = new LinkedHashMap<String, ManutencoesSinteticoVO>();
        for (ManutencoesAnaliticoVO original : dadosAnaliticos) {
            ManutencoesSinteticoVO sintetico = new ManutencoesSinteticoVO();
            try {
               // AssemblerUtil.copy(original, sintetico);
            } catch (Exception e) {
            	LOGGER.error(e.getMessage());
                throw new DEPIIntegrationException(e);
            }
            String chave = new StringBuilder().append(original.getCodigoBanco()).append(original.getCodigoCia()).append(
                original.getCodigoAgencia()).append(original.getCodigoConta()).append(original.getCodigoTipoAcao()).toString();
            map.put(chave, sintetico);
        }
        List<ManutencoesSinteticoVO> dadosSinteticos = new ArrayList<ManutencoesSinteticoVO>();
        Set<String> chaves = map.keySet();
        for (String key : chaves) {
            ManutencoesSinteticoVO sintetico = map.get(key);
            // calcula valores por situação
            for (ManutencoesAnaliticoVO original : dadosAnaliticos) {
                String chave = new StringBuilder().append(original.getCodigoBanco()).append(original.getCodigoCia()).append(
                    original.getCodigoAgencia()).append(original.getCodigoConta()).append(original.getCodigoTipoAcao())
                    .toString();
                if (key.equals(chave)) {
                	Double vlDouble = original.getValorRegistrado();
                	BigDecimal vlBigDecimal = new BigDecimal(vlDouble);
                	BigDecimal valor = (BigDecimal) sintetico.getValor().add(vlBigDecimal);
                     
                	sintetico.setValor(valor);
                    sintetico.setQuantidade((sintetico.getQuantidade() + 1));
                }
            }
            dadosSinteticos.add(sintetico);
        }
        
        return dadosSinteticos;
    }

	@Override
	public List<RelatorioEnvioRetornoAnaliticoVO> obterDadosEnvioRetornoAnalitico(
			FiltroUtil filtro) {
		
		try {
			return this.daoRelatorioEnvioRetornoDAO.obterDadosAnalitico(filtro);
		} catch (SQLException e) {
        	LOGGER.error(e.getMessage());
            throw new DEPIIntegrationException(e.getMessage());
		}
		
		
	}

	@Override
	public List<RelatorioEnvioRetornoSinteticoVO> obterDadosEnvioRetornoSintetico(
			FiltroUtil filtro) {
		try{
			return this.daoRelatorioEnvioRetornoDAO.obterDadosSintetico(filtro);
		} catch (SQLException e) {
	       	LOGGER.error(e.getMessage());
	        throw new DEPIIntegrationException(e.getMessage());
		}
	}

	@Override
	public List<RelatorioEnvioRetornoAnaliticoVO> obterDadosBancoExtratoAnalitico(
			FiltroUtil filtro) {
		// TODO Auto-generated method stub
		try {
			return this.daoRelatorioEnvioRetornoDAO.obterDadosAnalitico(filtro);
		} catch (SQLException e) {
	       	LOGGER.error(e.getMessage());
            throw new DEPIIntegrationException(e.getMessage());
		}
	}
	
	
	@Override
	public List<RelatorioExtratoSinteticoVO> obterDadosExtratoSintetico(
			FiltroUtil filtro) {
		// TODO Auto-generated method stub
		try{
			return daoRelatorioExtrato.obterDadosSintetico(filtro);
		} catch (SQLException e) {
		       	LOGGER.error(e.getMessage());
	            throw new DEPIIntegrationException(e.getMessage());
		}
		
	}

	@Override
	public List<CompanhiaSeguradoraVO> carregarComboCompanhiaUsuLogado(LoginVo loginVO) {
		// TODO Auto-generated method stub		
		int usuarioLogadoId =Integer.parseInt(loginVO.getId());  
		return daoCiaSeg.obterComRestricaoDeGrupoAcesso(usuarioLogadoId);
	}
	
	

	@Override
	public void obterTotais(List<RelatorioExtratoAnaliticoVO> lista) {
	     ordenarPorDeposito(lista);
	        Long id = new Long(0);
	        for (RelatorioExtratoAnaliticoVO vo : lista) {
	            if (!id.equals(vo.getCodigoAutorizador())) {
	                id = vo.getCodigoAutorizador();
	                vo.setValorPagoUnico(vo.getValorPago());
	                vo.setValorRegistradoUnico(vo.getValorRegistrado());
	            }
	        }
	}

	@Override
	public void ordenarDadosAnalitico(List<RelatorioExtratoAnaliticoVO> lista) {
		 Comparator<RelatorioExtratoAnaliticoVO> ordenacaoAnalitico = new Comparator<RelatorioExtratoAnaliticoVO>() {

	            public int compare(RelatorioExtratoAnaliticoVO p1, RelatorioExtratoAnaliticoVO p2) {

	                StringBuilder chave1 = new StringBuilder();
	                StringBuilder chave2 = new StringBuilder();

	                chave1.append(p1.getCodigoBanco()).append("|").append(p1.getCodigoCia()).append("|").append(p1.getCodigoConta())
	                    .append("|").append(p1.getNomeGrupo()).append("|").append("|").append(obterOrdemSituacao(p1)).append("|")
	                    .append(p1.getBloquete());

	                chave2.append(p2.getCodigoBanco()).append("|").append(p2.getCodigoCia()).append("|").append(p2.getCodigoConta())
	                    .append("|").append(p2.getNomeGrupo()).append("|").append("|").append(obterOrdemSituacao(p2)).append("|")
	                    .append(p2.getBloquete());
	                return chave1.toString().compareTo(chave2.toString());
	            };
	        };
	        Collections.sort(lista, ordenacaoAnalitico);
		
	}

	/**
     * Ordem Situação.
     * @param situacao - RelatorioExtratoAnaliticoVO.
     * @return String.
     */
    public String obterOrdemSituacao(RelatorioExtratoAnaliticoVO situacao) {

        if (!BaseUtil.isNZB(situacao.getSituacaoEnvioRetorno())) {

            if ("ENVIADOS".equals(situacao.getSituacaoEnvioRetorno())) {
                return "0";
            } else if ("ACEITOS".equals(situacao.getSituacaoEnvioRetorno())) {
                return "1";
            } else if ("REJEITADOS".equals(situacao.getSituacaoEnvioRetorno())) {
                return "2";
            } else if ("CANCELADOS".equals(situacao.getSituacaoEnvioRetorno())) {
                return "3";
            } else {
                return "4";
            }
        } else {
            if ("A".equals(situacao.getSituacaoManutencao())) {
                return "5";
            } else if ("T".equals(situacao.getSituacaoManutencao())) {
                return "6";
            } else if ("D".equals(situacao.getSituacaoManutencao())) {
                return "7";
            } else if ("R".equals(situacao.getSituacaoManutencao())) {
                return "8";
            } else {
                return "9";
            }
        }
    }

	
	 public void ordenarPorDeposito(List<RelatorioExtratoAnaliticoVO> lista) {
	        Comparator<RelatorioExtratoAnaliticoVO> ordenacaoAnaliticoDeposito = new Comparator<RelatorioExtratoAnaliticoVO>() {
	            public int compare(RelatorioExtratoAnaliticoVO p1, RelatorioExtratoAnaliticoVO p2) {
	                return p1.getCodigoAutorizador().compareTo(p2.getCodigoAutorizador());
	            };
	        };
	        Collections.sort(lista, ordenacaoAnaliticoDeposito);
	    }


}
