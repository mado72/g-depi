package br.com.bradseg.depi.depositoidentificado.relatorio.facade;

import static br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI.ARQUIVO_ACEITO;
import static br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI.ARQUIVO_CANCELADO;
import static br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI.ARQUIVO_ENVIADO;
import static br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI.ARQUIVO_REJEITADO;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.cics.dao.CICSDepiDAO;
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
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ManutencoesAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ManutencoesSinteticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioBancoContaAware;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioCompanhiaAware;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioDadosComplementaresVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoSinteticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioSituacaoAware;


/**
 * Implementação do ConsultarRelatorioFacade 
 * @author Globality
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ConsultarRelatorioFacadeImpl implements ConsultarRelatorioFacade {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultarRelatorioFacadeImpl.class);
    private static final String SEPARADOR_CONTA = " - ";
	private static final String MASCARA_CONTA = "000000000000";
	
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
	
	@Autowired
	private CICSDepiDAO cicsDepiDAO;
	
	private void preencheDescricaoCia(Collection<? extends RelatorioCompanhiaAware> dados) {
		HashMap<Integer, CompanhiaSeguradoraVO> companhias = new HashMap<>();
		
		for (RelatorioCompanhiaAware item : dados) {
			CompanhiaSeguradoraVO cia = null;
			if (! companhias.containsKey(item.getCodigoCia())) {
				try {
					cia = cicsDepiDAO.obterCiaPorCodigo(item.getCodigoCia());
					if (cia != null) {
						companhias.put(cia.getCodigoCompanhia(), cia);
					}
				} catch (Exception e) {
					LOGGER.warn("Erro ao obter companhia", e);
				}
			}
			
			if (cia != null) {
				item.setDescricaoCia(cia.getDescricaoCompanhia());
			}
		}
	}

	private void preencheDescricoesBancoConta(Collection<? extends RelatorioBancoContaAware> dados) {
		HashMap<Integer, BancoVO> bancos = new HashMap<>();
		HashMap<String, String> contas = new HashMap<>();
		
		for (RelatorioBancoContaAware item : dados) {
			BancoVO banco = new BancoVO(item.getCodigoBanco());
			
			if (! bancos.containsKey(banco.getCdBancoExterno())) {
				try {
					banco = cicsDepiDAO.obterBanco(banco);
					bancos.put(banco.getCdBancoExterno(), banco);
				} catch (Exception e) {
					LOGGER.warn("Erro ao obter banco", e);
				}
			}
			
			if (banco != null) {
				item.setDescricaoBanco(banco.getDescricaoBanco());
				
				ContaCorrenteAutorizadaVO cc = new ContaCorrenteAutorizadaVO(
						banco, item.getCodigoAgencia(), item.getCodigoConta());
				
				String bancoAgenciaConta = cc.toString();
				
				if (! contas.containsKey(bancoAgenciaConta)) {
					cc.setCia(new CompanhiaSeguradoraVO(item.getCodigoCia()));
	
					try {
						cc = cicsDepiDAO.obterContaCorrente(cc);
						
						String conta = BaseUtil.formatarValor(cc.getContaCorrente(), MASCARA_CONTA);
						String descricaoConta = new StringBuilder()
								.append(cc.getCodigoAgencia())
								.append(SEPARADOR_CONTA)
								.append(conta)
								.toString();
						
						contas.put(bancoAgenciaConta, descricaoConta);
					} catch (Exception e) {
						LOGGER.warn("Erro ao obter conta", e);
					}
				}
				
				item.setDescricaoConta(contas.get(bancoAgenciaConta));
			}
		}
	}

	private void preencheDescricaoSituacao(Collection<? extends RelatorioSituacaoAware> dados) {
		for (RelatorioSituacaoAware item : dados) {
			switch (item.getCodigoSituacao()) {
			case ARQUIVO_ACEITO:
				item.setDescricaoSituacao("ACEITO");
				break;
			case ARQUIVO_CANCELADO:
				item.setDescricaoSituacao("CANCELADO");
				break;
			case ARQUIVO_ENVIADO:
				item.setDescricaoSituacao("ENVIADO");
				break;
			case ARQUIVO_REJEITADO:
				item.setDescricaoSituacao("REJEITADO");
				break;
			default:
				item.setDescricaoSituacao("");
				break;
			}
		}
	}

	@Override
	public List<RelatorioEnvioRetornoVO> obterDadosEnvioRetornoAnalitico(
			FiltroUtil filtro) {
		
		List<RelatorioEnvioRetornoVO> dados = this.daoRelatorioEnvioRetornoDAO.obterDados(filtro);
		
		preencheDescricaoCia(dados);
		preencheDescricoesBancoConta(dados);
		preencheDescricaoSituacao(dados);
		
		return dados;
	}

	@Override
	public List<RelatorioEnvioRetornoVO> obterDadosEnvioRetornoSintetico(
			FiltroUtil filtro) {
		
		List<RelatorioEnvioRetornoVO> dados = this.obterDadosEnvioRetornoAnalitico(filtro);
		
		HashMap<String, RelatorioEnvioRetornoVO> mapaPorCompanhiaBanco = new LinkedHashMap<>();
		
		AtomicInteger qtdAceitos = new AtomicInteger();
		AtomicInteger qtdCancelados = new AtomicInteger();
		AtomicInteger qtdEnviados = new AtomicInteger();
		AtomicInteger qtdRejeitados = new AtomicInteger();
		
		BigDecimal totalAceitos = BigDecimal.ZERO;
		BigDecimal totalCancelados = BigDecimal.ZERO;;
		BigDecimal totalEnviados = BigDecimal.ZERO;;
		BigDecimal totalRejeitados = BigDecimal.ZERO;
		
		for (RelatorioEnvioRetornoVO item : dados) {
			String companhiaBanco = item.getCodigoCia() + "." + item.getCodigoBanco();
			
			RelatorioEnvioRetornoVO consolidado = mapaPorCompanhiaBanco.get(companhiaBanco);
			if (consolidado == null) {
				consolidado = item;
				
				consolidado.setQtdAceitos(0);
				consolidado.setQtdCancelados(0);
				consolidado.setQtdEnviados(0);
				consolidado.setQtdRejeitados(0);
				
				consolidado.setTotalAceitos(BigDecimal.ZERO);
				consolidado.setTotalCancelados(BigDecimal.ZERO);
				consolidado.setTotalEnviados(BigDecimal.ZERO);
				consolidado.setTotalRejeitados(BigDecimal.ZERO);
				
				mapaPorCompanhiaBanco.put(companhiaBanco, consolidado);
			}
			
			if (item.getCodigoSituacao() == ARQUIVO_ACEITO) {
				totalAceitos = totalAceitos.add(item.getValorRegistrado());
				qtdAceitos.incrementAndGet();
				
				consolidado.setQtdAceitos(consolidado.getQtdAceitos() + 1);
				consolidado.setTotalAceitos(consolidado.getTotalAceitos().add(item.getValorRegistrado()));
			}
			else if (item.getCodigoSituacao() == ARQUIVO_CANCELADO) {
				totalCancelados = totalCancelados.add(item.getValorRegistrado());
				qtdCancelados.incrementAndGet();
				
				consolidado.setQtdCancelados(consolidado.getQtdCancelados() + 1);
				consolidado.setTotalCancelados(consolidado.getTotalCancelados().add(item.getValorRegistrado()));
			}
			else if (item.getCodigoSituacao() == ARQUIVO_ENVIADO) {
				totalEnviados = totalEnviados.add(item.getValorRegistrado());
				qtdEnviados.incrementAndGet();
				
				consolidado.setQtdEnviados(consolidado.getQtdEnviados() + 1);
				consolidado.setTotalEnviados(consolidado.getTotalEnviados().add(item.getValorRegistrado()));
			}
			else if (item.getCodigoSituacao() == ARQUIVO_REJEITADO) {
				totalRejeitados = totalRejeitados.add(item.getValorRegistrado());
				qtdRejeitados.incrementAndGet();
				
				consolidado.setQtdRejeitados(consolidado.getQtdRejeitados() + 1);
				consolidado.setTotalRejeitados(consolidado.getTotalRejeitados().add(item.getValorRegistrado()));
			}
		}
		
		ArrayList<RelatorioEnvioRetornoVO> retorno = new ArrayList<>(mapaPorCompanhiaBanco.values());
		RelatorioEnvioRetornoVO totais = new RelatorioEnvioRetornoVO();
		
		totais.setTotalAceitos(totalAceitos);
		totais.setTotalCancelados(totalCancelados);
		totais.setTotalEnviados(totalEnviados);
		totais.setTotalRejeitados(totalRejeitados);
		
		totais.setQtdAceitos(qtdAceitos.get());
		totais.setQtdCancelados(qtdCancelados.get());
		totais.setQtdEnviados(qtdEnviados.get());
		totais.setQtdRejeitados(qtdRejeitados.get());
		
		totais.setDescricaoSituacao("TODOS");
		retorno.add(totais);
		
		return retorno;
	
	}

	@Override
	public List<RelatorioExtratoAnaliticoVO> obterDadosBancoExtratoAnalitico(
			FiltroUtil filtro) {
		try {
			List<RelatorioExtratoAnaliticoVO> dados = daoRelatorioExtrato.obterDadosAnalitico(filtro);
			
			preencheDescricaoCia(dados);
			preencheDescricoesBancoConta(dados);
			
			return dados;
		} catch (Exception e) {
			LOGGER.error("Falha na consulta", e);
			throw new DEPIIntegrationException(ConstantesDEPI.ERRO_CUSTOMIZADA, new String[]{e.getMessage()});
		}
	}

	@Override
	public List<RelatorioExtratoSinteticoVO> obterDadosBancoExtratoSintetico(
			FiltroUtil filtro) {
		try {
			List<RelatorioExtratoSinteticoVO> dados = daoRelatorioExtrato.obterDadosSintetico(filtro);
			
			preencheDescricaoCia(dados);
			preencheDescricoesBancoConta(dados);
			
			HashMap<String, RelatorioExtratoSinteticoVO> mapaPorCompanhiaBanco = new LinkedHashMap<>();
			
			AtomicInteger qtdAceitos = new AtomicInteger();
			AtomicInteger qtdCancelados = new AtomicInteger();
			AtomicInteger qtdEnviados = new AtomicInteger();
			AtomicInteger qtdRejeitados = new AtomicInteger();
			
			BigDecimal totalAceitos = BigDecimal.ZERO;
			BigDecimal totalCancelados = BigDecimal.ZERO;;
			BigDecimal totalEnviados = BigDecimal.ZERO;;
			BigDecimal totalRejeitados = BigDecimal.ZERO;
			
			for (RelatorioExtratoSinteticoVO item : dados) {
				String companhiaBanco = item.getCodigoCia() + "." + item.getCodigoBanco();
				
				RelatorioExtratoSinteticoVO consolidado = mapaPorCompanhiaBanco.get(companhiaBanco);
				if (consolidado == null) {
					consolidado = item;
					
					consolidado.setQtdAceito(0);
					consolidado.setQtdCancelado(0);
					consolidado.setQtdEnviado(0);
					consolidado.setQtdRejeitado(0);
					
					consolidado.setValorAceito(BigDecimal.ZERO);
					consolidado.setValorCancelado(BigDecimal.ZERO);
					consolidado.setValorEnviado(BigDecimal.ZERO);
					consolidado.setValorRejeitado(BigDecimal.ZERO);
					
					mapaPorCompanhiaBanco.put(companhiaBanco, consolidado);
				}
				
				if (item.getSituacaoEnvioRetorno() == ARQUIVO_ACEITO) {
					totalAceitos = totalAceitos.add(item.getValorRegistrado());
					qtdAceitos.incrementAndGet();
					
					consolidado.setQtdAceito(consolidado.getQtdAceito() + 1);
					consolidado.setValorAceito(consolidado.getValorAceito().add(item.getValorRegistrado()));
				}
				else if (item.getSituacaoEnvioRetorno() == ARQUIVO_CANCELADO) {
					totalCancelados = totalCancelados.add(item.getValorRegistrado());
					qtdCancelados.incrementAndGet();
					
					consolidado.setQtdCancelado(consolidado.getQtdCancelado() + 1);
					consolidado.setValorCancelado(consolidado.getValorCancelado().add(item.getValorRegistrado()));
				}
				else if (item.getSituacaoEnvioRetorno() == ARQUIVO_ENVIADO) {
					totalEnviados = totalEnviados.add(item.getValorRegistrado());
					qtdEnviados.incrementAndGet();
					
					consolidado.setQtdEnviado(consolidado.getQtdEnviado() + 1);
					consolidado.setValorEnviado(consolidado.getValorEnviado().add(item.getValorRegistrado()));
				}
				else if (item.getSituacaoEnvioRetorno() == ARQUIVO_REJEITADO) {
					totalRejeitados = totalRejeitados.add(item.getValorRegistrado());
					qtdRejeitados.incrementAndGet();
					
					consolidado.setQtdRejeitado(consolidado.getQtdRejeitado() + 1);
					consolidado.setValorRejeitado(consolidado.getValorRejeitado().add(item.getValorRegistrado()));
				}
			}
			
			ArrayList<RelatorioExtratoSinteticoVO> retorno = new ArrayList<>(mapaPorCompanhiaBanco.values());
			RelatorioExtratoSinteticoVO totais = new RelatorioExtratoSinteticoVO();
			
			totais.setValorAceito(totalAceitos);
			totais.setValorCancelado(totalCancelados);
			totais.setValorEnviado(totalEnviados);
			totais.setValorRejeitado(totalRejeitados);
			
			totais.setQtdAceito(qtdAceitos.get());
			totais.setQtdCancelado(qtdCancelados.get());
			totais.setQtdEnviado(qtdEnviados.get());
			totais.setQtdRejeitado(qtdRejeitados.get());
			
			totais.setDescricaoSituacao("TODOS");
			retorno.add(totais);
			
			return retorno;
		} catch (Exception e) {
			LOGGER.error("Falha na consulta", e);
			throw new DEPIIntegrationException(ConstantesDEPI.ERRO_CUSTOMIZADA, new String[]{e.getMessage()});
		}
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.relatorio.facade.ConsultarRelatorioFacade#obterMotivoComRestricaoDeDeposito(int, int, br.com.bradseg.bsad.filtrologin.vo.LoginVo)
	 */
	@Override
	public List<MotivoDepositoVO> obterMotivoComRestricaoDeDeposito(int codigCompanhia, int codigoDepartamento, LoginVo loginVO) {
		int codigoUsuario =Integer.parseInt(loginVO.getId());  
		return daoMotivoDeposito.obterComRestricaoDeGrupoAcesso(codigCompanhia, codigoDepartamento, codigoUsuario,Tabelas.DEPOSITO);
	}

	@Override
	   public  List<DepartamentoVO> obterComDepositoRestricaoDeDeposito(int codigCompanhia, LoginVo loginVO){
		int usuarioLogadoId =Integer.parseInt(loginVO.getId());  
	
		
		return daoDepartamento.obterComRestricao(codigCompanhia, usuarioLogadoId, Tabelas.DEPOSITO );
	}

     /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.facade.DepartamentoFacade#obterPorFiltro(br.com.bradseg.depi.depositoidentificado.util.FiltroUtil)
     */
    @Override    
    public List<DepartamentoCompanhiaVO> obterPorFiltro(FiltroUtil filtro) throws IntegrationException {
		
    
    	LOGGER.error("Inicio - obterPorFiltro(FiltroUtil filtro)");
    	
        List<DepartamentoCompanhiaVO> lista = new ArrayList<DepartamentoCompanhiaVO>();// daoDepartamento.obterPorFiltro(filtro);
        
        HashMap<Integer, String> cache = new HashMap<>();
        
        for (DepartamentoCompanhiaVO vo : lista) {
			int codigoCompanhia = vo.getCompanhia().getCodigoCompanhia();
			
			if (! cache.containsKey(codigoCompanhia)) {
				CompanhiaSeguradoraVO cia = cicsDepiDAO.obterCiaPorCodigo(codigoCompanhia);
				cache.put(codigoCompanhia, cia.getDescricaoCompanhia());
			}
			
			String descricaoCompanhia = cache.get(codigoCompanhia);
			vo.getCompanhia().setDescricaoCompanhia(descricaoCompanhia);
		}
        
		return lista;
    }


	@Override
	public List<ManutencoesAnaliticoVO> obterDadosManutencoesAnalitico(
			FiltroUtil filtro) {
		return daoManutAnalitico.obterDadosAnalitico(filtro);
	}

	@Override
	public List<RelatorioDadosComplementaresVO> obterDadosComplementares(
			FiltroUtil filtro) {
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
		return this.sintetizar(daoManutAnalitico.obterDadosAnalitico(filtro));
		
	}


    /**
     * Gera lista com dados para o relat�rio Sint�tico usando a lista de dados do relat�rio An�litico.
     * @param dadosAnaliticos - List<ManutencoesSinteticoVO>.
     * @throws DEPIIntegrationException - DEPIIntegrationException.
     * @return List<ManutencoesSinteticoVO>.
     */
    public List<ManutencoesSinteticoVO> sintetizar(List<ManutencoesAnaliticoVO> dadosAnaliticos) throws DEPIIntegrationException {
    	LinkedHashMap<String, ManutencoesSinteticoVO> map = new LinkedHashMap<String, ManutencoesSinteticoVO>();
        for (ManutencoesAnaliticoVO original : dadosAnaliticos) {
            ManutencoesSinteticoVO sintetico = new ManutencoesSinteticoVO();
            String chave = new StringBuilder().append(original.getCodigoBanco()).append(original.getCodigoCia()).append(
                original.getCodigoAgencia()).append(original.getCodigoConta()).append(original.getCodigoTipoAcao()).toString();
            map.put(chave, sintetico);
        }
        List<ManutencoesSinteticoVO> dadosSinteticos = new ArrayList<ManutencoesSinteticoVO>();
        for (Map.Entry<String,ManutencoesSinteticoVO> entry : map.entrySet()) {
        	String key = entry.getKey();
        	ManutencoesSinteticoVO sintetico = entry.getValue();
            // calcula valores por situa��o
            for (ManutencoesAnaliticoVO original : dadosAnaliticos) {
                String chave = new StringBuilder().append(original.getCodigoBanco()).append(original.getCodigoCia()).append(
                    original.getCodigoAgencia()).append(original.getCodigoConta()).append(original.getCodigoTipoAcao())
                    .toString();
                if (key.equals(chave)) {
                	Double vlDouble = original.getValorRegistrado();
                	BigDecimal vlBigDecimal = new BigDecimal(vlDouble);
                	BigDecimal valor = sintetico.getValor().add(vlBigDecimal);
                     
                	sintetico.setValor(valor);
                    sintetico.setQuantidade((sintetico.getQuantidade() + 1));
                }
            }
            dadosSinteticos.add(sintetico);
        }
        
        return dadosSinteticos;
    }
    
    @Override
	public List<CompanhiaSeguradoraVO> carregarComboCompanhiaUsuLogado(LoginVo loginVO) {
		int usuarioLogadoId =Integer.parseInt(loginVO.getId());
		List<CompanhiaSeguradoraVO> lista = daoCiaSeg.obterComRestricaoDeGrupoAcesso(usuarioLogadoId);
		
		return populaDescricaLstaCompanhia(lista);

	}
	
	public List<CompanhiaSeguradoraVO> populaDescricaLstaCompanhia( List<CompanhiaSeguradoraVO>  Companhias) {
		// TODO Auto-generated method stub	
	
        for (int i = 0; i < Companhias.size(); i++) {
        	CompanhiaSeguradoraVO ele = Companhias.get(i);
            CompanhiaSeguradoraVO cia = cicsDepiDAO.obterCiaPorCodigo(ele.getCodigoCompanhia());
        	//CompanhiaSeguradoraVO cia  = new CompanhiaSeguradoraVO();
        	//cia.setDescricaoCompanhia(ele.getCodigoCompanhia()+"-descricaoCompanhia");
        	cia.setCodigoCompanhia(ele.getCodigoCompanhia());        	
        	Companhias.set(i, cia);
      
        	LOGGER.error("Cod.:"+cia.getCodigoCompanhia() + " - "+cia.getDescricaoCompanhia() );	
        }
	
		return Companhias;

	}
	
	
	
	
	@Override
	public void obterTotais(List<RelatorioExtratoAnaliticoVO> lista) {
	     ordenarPorDeposito(lista);
	        Long id = 0L;
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

	            @Override
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
     * Ordem Situa��o.
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
	            @Override
				public int compare(RelatorioExtratoAnaliticoVO p1, RelatorioExtratoAnaliticoVO p2) {
	                return p1.getCodigoAutorizador().compareTo(p2.getCodigoAutorizador());
	            };
	        };
	        Collections.sort(lista, ordenacaoAnaliticoDeposito);
	    }


}
