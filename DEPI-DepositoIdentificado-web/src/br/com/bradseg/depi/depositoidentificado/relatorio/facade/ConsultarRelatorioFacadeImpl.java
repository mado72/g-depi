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
import br.com.bradseg.depi.depositoidentificado.dao.delagate.BUCBBusinessDelegate;
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
import br.com.bradseg.depi.depositoidentificado.vo.PessoaVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioBancoContaAware;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioCompanhiaAware;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioDadosComplementaresVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioDescricaoSituacaoAware;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoSinteticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioPessoaAware;


/**
 * Implementação do ConsultarRelatorioFacade 
 * @author Globality
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ConsultarRelatorioFacadeImpl implements ConsultarRelatorioFacade {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultarRelatorioFacadeImpl.class);
    private static final String SEPARADOR_CONTA = " - ";
	@Autowired
	private RelatorioManutencoesDAO daoManutAnalitico;
	
	@Autowired
	private CompanhiaSeguradoraDAO daoCiaSeg;
	
	@Autowired
	private BUCBBusinessDelegate businessDelegate;
	
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
				item.setDescricaoCia(
						new StringBuilder().append(item.getCodigoCia()).append(SEPARADOR_CONTA).append(cia.getDescricaoCompanhia()).toString());
			}
		}
	}

	private void preencheDescricoesBancoConta(Collection<? extends RelatorioBancoContaAware> dados) {
		HashMap<Integer, BancoVO> cacheBancos = new HashMap<>();
		HashMap<String, String> contas = new HashMap<>();
		
		for (RelatorioBancoContaAware item : dados) {
			
			if (! cacheBancos.containsKey(item.getCodigoBanco())) {
				try {
					BancoVO banco = cicsDepiDAO.obterBanco(new BancoVO(item.getCodigoBanco()));
					cacheBancos.put(item.getCodigoBanco(), banco);
				} catch (Exception e) {
					LOGGER.warn("Erro ao obter banco", e);
				}
			}
			
			BancoVO banco = cacheBancos.get(item.getCodigoBanco());
			
			if (banco != null) {
				item.setDescricaoBanco(
						new StringBuilder().append(banco.getCdBancoExterno()).append(SEPARADOR_CONTA).append(banco.getDescricaoBanco()).toString());
				
				ContaCorrenteAutorizadaVO cc = new ContaCorrenteAutorizadaVO(
						banco, item.getCodigoAgencia(), item.getCodigoConta());
				
				String bancoAgenciaConta = cc.toString();
				
				if (! contas.containsKey(bancoAgenciaConta)) {
					cc.setCia(new CompanhiaSeguradoraVO(item.getCodigoCia()));
	
					try {
						cc = cicsDepiDAO.obterContaCorrente(cc);
						
						String descricaoConta = String.format("%d - %09d", cc.getCodigoAgencia(), cc.getContaCorrente());
						contas.put(bancoAgenciaConta, descricaoConta);
					} catch (Exception e) {
						LOGGER.warn("Erro ao obter conta", e);
					}
				}
				
				item.setDescricaoConta(contas.get(bancoAgenciaConta));
			}
		}
	}

	private void preencheDescricaoSituacao(Collection<? extends RelatorioDescricaoSituacaoAware> dados) {
		for (RelatorioDescricaoSituacaoAware item : dados) {
			item.setDescricaoSituacao(obterSituacao(item.getCodigoSituacao()));
		}
	}
	
	private String obterSituacao(int codigo) {
		switch (codigo) {
		case ARQUIVO_ACEITO:
			return "ACEITO";
		case ARQUIVO_CANCELADO:
			return "CANCELADO";
		case ARQUIVO_ENVIADO:
			return "ENVIADO";
		case ARQUIVO_REJEITADO:
			return "REJEITADO";
		default:
			return null;
		}
	}
	
	private void preencheCPFCNPJPessoa(String ipCliente, Integer codResponsavel, Collection<? extends RelatorioPessoaAware> dados) {

		HashMap<Long, PessoaVO> cachePessoa = new HashMap<>();
		
		for (RelatorioPessoaAware item : dados) {
			Long codPessoa = item.getCodigoPessoa();
			if (codPessoa == null) {
				continue;
			}
			
			try {
				if (! cachePessoa.containsKey(codPessoa)) {
					PessoaVO pessoa = businessDelegate.obterDadosPessoa(ipCliente, codResponsavel, codPessoa);
					cachePessoa.put(codPessoa, pessoa);
				}
			} catch (Exception e) {
				throw new DEPIIntegrationException(e);
			}
			
			PessoaVO pessoa = cachePessoa.get(codPessoa);
			if (pessoa.isPessoaFisica()) {
				item.setCpfCnpj(BaseUtil.getCpfFormatado(String.valueOf(pessoa.getCpfCnpj())));
			} 
			else if (pessoa.isPessoaJuridica()) {
				item.setCpfCnpj(BaseUtil.getCnpjFormatado(String.valueOf(pessoa.getCpfCnpj())));
			}
		}

	}
	

	@Override
	public List<RelatorioEnvioRetornoVO> obterDadosEnvioRetornoAnalitico(
			FiltroUtil filtro, String ipCliente, int codResponsavel) {
		
		List<RelatorioEnvioRetornoVO> dados = this.daoRelatorioEnvioRetornoDAO.obterDados(filtro);
		
		preencheDescricaoCia(dados);
		preencheDescricoesBancoConta(dados);
		preencheDescricaoSituacao(dados);
		preencheCPFCNPJPessoa(ipCliente, codResponsavel, dados);
		
		return dados;
	}

	@Override
	public List<RelatorioEnvioRetornoVO> obterDadosEnvioRetornoSintetico(
			FiltroUtil filtro, String ipCliente, int codResponsavel) {
		
		List<RelatorioEnvioRetornoVO> dados = this.obterDadosEnvioRetornoAnalitico(filtro, ipCliente,
						codResponsavel);
		
		if (dados.isEmpty()) {
			return dados;
		}
		
		Map<String, RelatorioEnvioRetornoVO> mapaPorCompanhiaBanco = new LinkedHashMap<>();
		
		AtomicInteger qtdAceitos = new AtomicInteger();
		AtomicInteger qtdCancelados = new AtomicInteger();
		AtomicInteger qtdEnviados = new AtomicInteger();
		AtomicInteger qtdRejeitados = new AtomicInteger();
		
		BigDecimal totalAceitos = BigDecimal.ZERO;
		BigDecimal totalCancelados = BigDecimal.ZERO;
		BigDecimal totalEnviados = BigDecimal.ZERO;
		BigDecimal totalRejeitados = BigDecimal.ZERO;
		
		for (RelatorioEnvioRetornoVO item : dados) {
			String chave = new StringBuilder().append(item.getCodigoCia())
					.append('.').append(item.getCodigoBanco())
					.append('.').append(item.getCodigoAgencia())
					.append('.').append(item.getCodigoConta())
					.toString();
			
			RelatorioEnvioRetornoVO consolidado = mapaPorCompanhiaBanco.get(chave);
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
				
				mapaPorCompanhiaBanco.put(chave, consolidado);
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
		
		return new ArrayList<>(mapaPorCompanhiaBanco.values());
	
	}

	@Override
	public List<RelatorioExtratoAnaliticoVO> obterDadosBancoExtratoAnalitico(
			FiltroUtil filtro, String ipCliente, int codResponsavel) {
		try {
			List<RelatorioExtratoAnaliticoVO> dados = daoRelatorioExtrato.obterDadosAnalitico(filtro);
			
			preencheDescricaoCia(dados);
			preencheDescricoesBancoConta(dados);
			preencheCPFCNPJPessoa(ipCliente, codResponsavel, dados);
			
			for (RelatorioExtratoAnaliticoVO item : dados) {
				item.setSituacao(obterSituacao(item.getSituacaoEnvioRetorno()));
			}

			obterTotais(dados);
			ordenarDadosAnalitico(dados);

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
			
			return sintetizarExtrato(dados);
			
		} catch (Exception e) {
			LOGGER.error("Falha na consulta", e);
			throw new DEPIIntegrationException(ConstantesDEPI.ERRO_CUSTOMIZADA, new String[]{e.getMessage()});
		}
	}

	private ArrayList<RelatorioExtratoSinteticoVO> sintetizarExtrato(
			Collection<RelatorioExtratoSinteticoVO> dados) {
		
		HashMap<String, RelatorioExtratoSinteticoVO> mapaPorCompanhiaBanco = new LinkedHashMap<>();
		
		AtomicInteger qtdAceitos = new AtomicInteger();
		AtomicInteger qtdCancelados = new AtomicInteger();
		AtomicInteger qtdEnviados = new AtomicInteger();
		AtomicInteger qtdRejeitados = new AtomicInteger();
		
		BigDecimal totalAceitos = BigDecimal.ZERO;
		BigDecimal totalCancelados = BigDecimal.ZERO;
		BigDecimal totalEnviados = BigDecimal.ZERO;
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
		
		return new ArrayList<>(mapaPorCompanhiaBanco.values());
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
		List<ManutencoesAnaliticoVO> dados = daoManutAnalitico.obterDadosAnalitico(filtro);
		
		preencheDescricaoCia(dados);
		preencheDescricoesBancoConta(dados);
		
		for (ManutencoesAnaliticoVO vo : dados) {
			if (vo.getValorPago() == null) {
				vo.setValorPago(BigDecimal.ZERO);
			}
		}
		return dados;
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
     * Gera lista com dados para o relatório Sintético usando a lista de dados do relatório Analítico.
     * @param dadosAnaliticos - List<ManutencoesSinteticoVO>.
     * @throws DEPIIntegrationException - DEPIIntegrationException.
     * @return List<ManutencoesSinteticoVO>.
     */
    private List<ManutencoesSinteticoVO> sintetizar(List<ManutencoesAnaliticoVO> dadosAnaliticos) throws DEPIIntegrationException {
    	LinkedHashMap<String, ManutencoesSinteticoVO> map = new LinkedHashMap<String, ManutencoesSinteticoVO>();
        for (ManutencoesAnaliticoVO original : dadosAnaliticos) {
            ManutencoesSinteticoVO sintetico = new ManutencoesSinteticoVO();
            String chave = obterChaveManutencoesAnalitico(original);
            map.put(chave, sintetico);
        }
        
        List<ManutencoesSinteticoVO> dadosSinteticos = new ArrayList<ManutencoesSinteticoVO>();
        for (Map.Entry<String,ManutencoesSinteticoVO> entry : map.entrySet()) {
        	String key = entry.getKey();
        	ManutencoesSinteticoVO sintetico = entry.getValue();
        	if (sintetico.getValor() == null) {
        		sintetico.setValor(BigDecimal.ZERO);
        	}
        	
            // calcula valores por situação
            for (ManutencoesAnaliticoVO original : dadosAnaliticos) {
                String chave = obterChaveManutencoesAnalitico(original);
                
                if (key.equals(chave)) {
                	BigDecimal vlBigDecimal = original.getValorRegistrado();
                	if (vlBigDecimal != null) {
                		BigDecimal valor = sintetico.getValor().add(vlBigDecimal);
                		sintetico.setValor(valor);
                	}
                    sintetico.setQuantidade((sintetico.getQuantidade() + 1));
                }
            }
            dadosSinteticos.add(sintetico);
        }
        
        return dadosSinteticos;
    }

	protected String obterChaveManutencoesAnalitico(ManutencoesAnaliticoVO vo) {
		String chave = new StringBuilder().append(vo.getCodigoBanco()).append(vo.getCodigoCia()).append(
		    vo.getCodigoAgencia()).append(vo.getCodigoConta()).append(vo.getCodigoTipoAcao())
		    .toString();
		return chave;
	}
    
    @Override
	public List<CompanhiaSeguradoraVO> carregarComboCompanhiaUsuLogado(LoginVo loginVO) {
		int usuarioLogadoId =Integer.parseInt(loginVO.getId());
		List<CompanhiaSeguradoraVO> lista = daoCiaSeg.obterComRestricaoDeGrupoAcesso(usuarioLogadoId);
		
		return populaDescricaoListaCompanhia(lista);

	}
	
	private List<CompanhiaSeguradoraVO> populaDescricaoListaCompanhia(List<CompanhiaSeguradoraVO>  lista) {
        for (int i = 0; i < lista.size(); i++) {
        	CompanhiaSeguradoraVO ele = lista.get(i);
            CompanhiaSeguradoraVO cia = cicsDepiDAO.obterCiaPorCodigo(ele.getCodigoCompanhia());
        	cia.setCodigoCompanhia(ele.getCodigoCompanhia());        	
        	lista.set(i, cia);
        }
	
		return lista;

	}
	
	private void obterTotais(List<RelatorioExtratoAnaliticoVO> lista) {
		// TODO validar este código
		ordenarPorDeposito(lista);
		Long id = 0L;
		for (RelatorioExtratoAnaliticoVO vo : lista) {
			if (vo.getValorPago() == null) {
				vo.setValorPago(BigDecimal.ZERO);
			}
			if (vo.getValorRegistrado() == null) {
				vo.setValorRegistrado(BigDecimal.ZERO);
			}
			
			if (!id.equals(vo.getCodigoAutorizador())) {
				id = vo.getCodigoAutorizador();
				vo.setValorPagoUnico(vo.getValorPago());
				vo.setValorRegistradoUnico(vo.getValorRegistrado());
			}
		}
	}

	private void ordenarDadosAnalitico(List<RelatorioExtratoAnaliticoVO> lista) {
		 Comparator<RelatorioExtratoAnaliticoVO> ordenacaoAnalitico = new Comparator<RelatorioExtratoAnaliticoVO>() {

	            @Override
				public int compare(RelatorioExtratoAnaliticoVO p1, RelatorioExtratoAnaliticoVO p2) {

	                StringBuilder chave1 = new StringBuilder();
	                StringBuilder chave2 = new StringBuilder();

	                chave1.append(p1.getCodigoBanco()).append("|").append(p1.getCodigoCia()).append("|").append(p1.getCodigoConta())
	                    .append("|").append(p1.getNomeGrupo()).append("|").append("|").append(p1.getSituacaoEnvioRetorno()).append("|")
	                    .append(p1.getBloquete());

	                chave2.append(p2.getCodigoBanco()).append("|").append(p2.getCodigoCia()).append("|").append(p2.getCodigoConta())
	                    .append("|").append(p2.getNomeGrupo()).append("|").append("|").append(p2.getSituacaoEnvioRetorno()).append("|")
	                    .append(p2.getBloquete());
	                return chave1.toString().compareTo(chave2.toString());
	            };
	        };
	        Collections.sort(lista, ordenacaoAnalitico);
		
	}
	
	private void ordenarPorDeposito(List<RelatorioExtratoAnaliticoVO> lista) {
		Comparator<RelatorioExtratoAnaliticoVO> ordenacaoAnaliticoDeposito = new Comparator<RelatorioExtratoAnaliticoVO>() {
			@Override
			public int compare(RelatorioExtratoAnaliticoVO p1,
					RelatorioExtratoAnaliticoVO p2) {
				return p1.getCodigoAutorizador().compareTo(
						p2.getCodigoAutorizador());
			};
		};
		Collections.sort(lista, ordenacaoAnaliticoDeposito);
	}

}
