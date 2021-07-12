package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bradseg.bsad.framework.core.exception.BusinessException;
import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.bsad.framework.core.message.Message;
import br.com.bradseg.bucb.servicos.model.pessoa.vo.ListarPessoaPorFiltroSaidaVO;
import br.com.bradseg.depi.depositoidentificado.cics.dao.CICSDepiDAO;
import br.com.bradseg.depi.depositoidentificado.dao.AssociarMotivoDepositoDAO;
import br.com.bradseg.depi.depositoidentificado.dao.BancoDAO;
import br.com.bradseg.depi.depositoidentificado.dao.CompanhiaSeguradoraDAO;
import br.com.bradseg.depi.depositoidentificado.dao.ContaCorrenteDAO;
import br.com.bradseg.depi.depositoidentificado.dao.DepartamentoDAO;
import br.com.bradseg.depi.depositoidentificado.dao.DepositoDAO;
import br.com.bradseg.depi.depositoidentificado.dao.LancamentoDepositoDAO;
import br.com.bradseg.depi.depositoidentificado.dao.MotivoDepositoDAO;
import br.com.bradseg.depi.depositoidentificado.dao.MovimentoDepositoDAO;
import br.com.bradseg.depi.depositoidentificado.dao.ParametroDepositoDAO;
import br.com.bradseg.depi.depositoidentificado.dao.ParcelasPendentesDAO;
import br.com.bradseg.depi.depositoidentificado.dao.delagate.BUCBBusinessDelegate;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIBusinessException;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.ContaCorrenteAutorizadaCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.Tabelas;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoOperacao;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.AgenciaVO;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;
import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.EventoContabilVO;
import br.com.bradseg.depi.depositoidentificado.vo.ItemContabilVO;
import br.com.bradseg.depi.depositoidentificado.vo.LancamentoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MovimentoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ParcelaCobrancaVO;
import br.com.bradseg.depi.depositoidentificado.vo.PessoaVO;

/**
 * Implementa a associação de motivo depósito
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class DepositoFacadeImpl implements DepositoFacade {

	private static final String MODULO = "Identifica\u00E7\u00E7o Dep\u00F3sitos";
    
	protected static final Logger LOGGER = LoggerFactory.getLogger(DepositoFacadeImpl.class);

    @Autowired
    private DepositoDAO depositoDAO;
    
	@Autowired
	private AssociarMotivoDepositoDAO amdDAO;
	
	@Autowired
	private CompanhiaSeguradoraDAO ciaDAO;
	
	@Autowired
	private DepartamentoDAO deptoDAO;
	
	@Autowired
	private MotivoDepositoDAO motDepDAO;
	
	@Autowired
	private MovimentoDepositoDAO movimentoDAO;
	
	@Autowired
	private LancamentoDepositoDAO lancamentoDAO;
	
	@Autowired
	private BancoDAO bancoDAO;
	
	@Autowired
	private ContaCorrenteDAO contaDAO;
		
	@Autowired
	private CICSDepiDAO cicsDAO;
	
	@Autowired
	private BUCBBusinessDelegate businessDelegate;
	
	@Autowired
	private ParametroDepositoDAO parametroDAO;
	
	@Autowired
	private ParcelasPendentesDAO parcelasDAO;

    /**
     * Excluir AssociarMotivoDepositos
     * @param vos VO's de AssociarMotivoDeposito
     * @throws IntegrationException Exceção de aplicação
     */
    public void excluir(List<DepositoVO> vos) throws IntegrationException {
    	
        if (vos == null || vos.isEmpty()) {
            throw new DEPIIntegrationException(ConstantesDEPI.ERRO_LISTA_INVALIDA);
        }

        StringBuilder codsAssocMotivos = new StringBuilder();

/*
        for (DepositoVO vo : vos) {
            if (amdDAO.isReferenciado(vo)) {
            	
                if (codsAssocMotivos.length() > 0) {
                    codsAssocMotivos.append("; ");
                }
                codsAssocMotivos.append(vo.toString());
            } else {
            	amdDAO.excluir(vo);
            }
        }
*/        
        if (codsAssocMotivos.length() > 0) {
            throw new DEPIBusinessException(ConstantesDEPI.ERRO_DEPENDENCIA_MODULO, codsAssocMotivos.toString(), MODULO);
        }
    }

    /**
     * Inserir AssociarMotivoDeposito
     * @param deposito - DepositoVO.
     * @throws IntegrationException - Integração.
     */
    @Override
	public void inserir(DepositoVO deposito) throws IntegrationException {
        ParametroDepositoVO param = new ParametroDepositoVO();
        param.setCompanhia(new CompanhiaSeguradoraVO(deposito.getCia().getCodigoCompanhia()));
        param.setDepartamento(new DepartamentoVO(deposito.getDepartamento().getCodigoDepartamento(), null, deposito.getDepartamento().getSiglaDepartamento()));
        param.setMotivoDeposito(new MotivoDepositoVO(deposito.getMotivoDeposito().getCodigoMotivoDeposito(), null, null));
        param = parametroDAO.obterPorChave(param);
                
        validarObjetos(deposito);
        validarChave(deposito);

        validarParametros(deposito, param);
        depositoDAO.inserir(deposito, param);
        
        complementaListaParcelas(deposito);
        
        if(!deposito.getListaParcelas().isEmpty()) {
        	parcelasDAO.inserirDepositoCobranca(deposito.getListaParcelas());
        }
    }
    
    private void complementaListaParcelas(DepositoVO deposito) {
		for(ParcelaCobrancaVO parcela : deposito.getListaParcelas()) {
			DepositoVO dep = new DepositoVO();
			dep.setCodigoDepositoIdentificado(deposito.getCodigoDepositoIdentificado());
			parcela.setDeposito(dep);
		}
	}

	private void validarObjetos(DepositoVO deposito) {
    	BaseUtil.validarParametro(deposito, "Dep\u00f3sito");
    	BaseUtil.validarParametro(deposito.getCia(), "Cia");
    	BaseUtil.validarParametro(deposito.getDepartamento(), "Departamento");
    	BaseUtil.validarParametro(deposito.getMotivoDeposito(), "Motivo");
    	BaseUtil.validarParametro(deposito.getBanco(), "Banco");
	}

	@Override
    public void alterar(DepositoVO deposito) {
        ParametroDepositoVO param = new ParametroDepositoVO();
        param.setCompanhia(deposito.getCia());
        param.setDepartamento(deposito.getDepartamento());
        param.setMotivoDeposito(deposito.getMotivoDeposito());
        param = parametroDAO.obterPorChave(param);

        validarChave(deposito);
        validarParametros(deposito, param);
        DepositoVO oldObj = depositoDAO.obterDepositoPorChave(deposito);

        /**
         * enviar - ok reeviar - ok rejeitado - ok
         */
        if (oldObj.getSituacaoArquivoTransferencia() == ConstantesDEPI.ARQUIVO_ENVIADO) {
            throw new DEPIBusinessException("msg.erro.deposito.naoPodeAlterar.tramite", String.valueOf(oldObj.getCodigoDepositoIdentificado()));
        }

        if (oldObj.getSituacaoArquivoTransferencia() == ConstantesDEPI.ARQUIVO_ACEITO) {
            throw new DEPIBusinessException("msg.erro.deposito.naoPodeAlterar.aceito", String.valueOf(oldObj.getCodigoDepositoIdentificado()));
        }

        if (oldObj.getSituacaoArquivoTransferencia() == ConstantesDEPI.ARQUIVO_CANCELADO
            || ConstantesDEPI.SIM.equals(oldObj.getIndicadorDepositoCancelado())) {
            throw new DEPIBusinessException("msg.erro.deposito.naoPodeAlterar.cancelado", String.valueOf(oldObj.getCodigoDepositoIdentificado()));
        }
        
        depositoDAO.atualizar(deposito, param);
    }

    private void validarParametros(DepositoVO deposito,
			ParametroDepositoVO param) {
        BaseUtil.assertTrueThrowException(BaseUtil.isNZB(deposito), ConstantesDEPI.MSG_CUSTOMIZADA,
                "Dep\u00f3sito: deposito - DepositoVO \u00e9 null");

            BaseUtil.assertTrueThrowException(BaseUtil.isNZB(param), ConstantesDEPI.MSG_CUSTOMIZADA,
                "Par\u00e2metro: param - ParametroDepositoVO \u00e9 null");
            
            if (BaseUtil.isNZB(deposito.getObservacaoDeposito()) && BaseUtil.isNZB(param.getOutrosDocumentosNecessarios())){
            	throw new DEPIBusinessException(ConstantesDEPI.MSG_CUSTOMIZADA,  
            		"Obrigat\u00f3rio o preenchimento do campo 'Hist\u00f3rico de Dep\u00f3sito'");
            }
            
            /**
             * Linhas em ordem de preechimento.
             */

            BaseUtil.validarParametro(deposito.getIpCliente(), "IP do cliente");

            BaseUtil.validarParametro(deposito.getPessoaDepositante(), "Depositante");

            //LINHA PARA ACERTO NA INCLUS�O DE C�DIGO DEVIDO � INTEGRA��O COM AS PARCELAS DO CBBS.
            if(!"GRV".equals(param.getDepartamento().getSiglaDepartamento()) && !"GRC".equals(param.getDepartamento().getSiglaDepartamento())) {
            	BaseUtil.validarParametro(param.getCodigoSucursal(), deposito.getSucursal(), "Sucursal");
            	BaseUtil.validarParametro(param.getCodigoApolice(), deposito.getApolice(), "Ap\u00f3lice");
            	BaseUtil.validarParametro(param.getCodigoRamo(), deposito.getRamo(), "Ramo");
            	BaseUtil.validarParametro(param.getCodigoEndosso(), deposito.getEndosso(), "Endosso");
            	BaseUtil.validarParametro(param.getCodigoTipo(), deposito.getTipoDocumento(), "Tipo");
            	BaseUtil.validarParametro(param.getCodigoItem(), deposito.getItem(), "ItemContabil");
            }
            
            BaseUtil.validarParametro(param.getCodigoBloqueto(), deposito.getBloqueto(), "Bloqueto");
            
            BaseUtil.validarParametro(param.getCodigoProtocolo(), deposito.getProtocolo(), "Protocolo");
            
            BaseUtil.validarParametro(param.getCodigoDossie(), deposito.getDossie(), "Dossie");
            BaseUtil.validarParametro(param.getCodigoParcela(), deposito.getParcela(), "Parcela");

            BaseUtil.validarParametro(deposito.getTipoGrupoRecebimento(), "Tipo Grupo Recebimento");
            BaseUtil.validarParametro(deposito.getDtVencimentoDeposito(), "Data Vencimento");

            BaseUtil.validarParametro(deposito.getVlrDepositoRegistrado(), "Valor");
	}

	/**
     * Método de obter por filtro
     * @param codUsuario - BigDecimal.
     * @param filtro parâmetro depósito com o código do objeto requisitado
     * @return List<DepositoVO>
     */
	@Override
	public List<DepositoVO> obterPorFiltro(int codUsuario,
			FiltroUtil filtro) {
		return depositoDAO.obterPorFiltroComRestricaoDeGrupoAcesso(filtro, codUsuario);
    }

    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.facade.DepositoFacade#obterPorChave(br.com.bradseg.depi.depositoidentificado.vo.DepositoVO, java.lang.String, int)
     */
    @Override
	public DepositoVO obterPorChave(DepositoVO chave, int codUsuario, String ipCliente) throws IntegrationException {
    	
        DepositoVO vo = depositoDAO.obterDepositoPorChave(chave);
        try {
			PessoaVO pessoaVO = businessDelegate.obterDadosPessoa(ipCliente,
					codUsuario, vo.getPessoaDepositante());

			if (pessoaVO.isPessoaFisica()) {
				vo.setCpfCnpj(BaseUtil.getCpfFormatado(String.valueOf(pessoaVO.getCpfCnpj())));
			}
			else if (pessoaVO.isPessoaJuridica()) {
				vo.setCpfCnpj(BaseUtil.getCpfFormatado(String.valueOf(pessoaVO.getCpfCnpj())));
			}
        	
        	vo.setMotivoDeposito(motDepDAO.obterPorChave(vo.getMotivoDeposito()));
        	vo.setListaParcelas(montarListaParcelas(vo));
			
			return vo;
		} catch (Exception e) {
			throw new DEPIIntegrationException(e);
		}
    }
    
    private List<ParcelaCobrancaVO> montarListaParcelas(DepositoVO deposito) {
    	
    	ParcelaCobrancaVO parcelaCobranca = new ParcelaCobrancaVO();
    	parcelaCobranca.setDeposito(deposito);
		return parcelasDAO.listarParcelasAssociadas(parcelaCobranca);
		
	}

	@Override
    public List<ListarPessoaPorFiltroSaidaVO> listarPessoas(String cpfCnpj, String ipCliente, int codUsuario) {
        List<ListarPessoaPorFiltroSaidaVO> lista = businessDelegate.listarPessoaPorFiltro(ipCliente,
        		codUsuario, BaseUtil.retiraMascaraCNPJ(cpfCnpj));
        
        return lista;
    }

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade#excluirLista(java.util.List)
	 */
	@Override
	public void excluirLista(List<DepositoVO> voList) {
		List<Message> messages = new ArrayList<>();
        
		for (DepositoVO vo : voList) {
			
			validarChave(vo);
			BaseUtil.validarParametro(vo.getCodigoResponsavelUltimaAtualizacao(), "Usu\u00e1rio Logado");
			DepositoVO dep = depositoDAO.obterDepositoPorChave(vo);
			
			if (dep.getSituacaoArquivoTransferencia() == ConstantesDEPI.ARQUIVO_ENVIADO) {

				messages.add(new Message("msg.erro.deposito.naoPodeExcluir.tramite", dep.getCodigoDepositoIdentificado()));
				
			} else if (dep.getSituacaoArquivoTransferencia() == ConstantesDEPI.ARQUIVO_ACEITO) {
				
				messages.add(new Message("msg.erro.deposito.naoPodeExcluir.aceito", dep.getCodigoDepositoIdentificado()));

			} else if (dep.getSituacaoArquivoTransferencia() == ConstantesDEPI.ARQUIVO_CANCELADO
					|| ConstantesDEPI.SIM.equals(dep.getIndicadorDepositoCancelado())) {

				messages.add(new Message("msg.erro.deposito.naoPodeExcluir.cancelado", dep.getCodigoDepositoIdentificado()));
				
			} else {
				
				depositoDAO.excluir(dep);
				
			}
		}
		
        if (!messages.isEmpty()) {
			throw new BusinessException(messages);
        }
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade#obterCompanhias(int)
	 */
	@Override
	public List<CompanhiaSeguradoraVO> obterCompanhias(int codUsuario) {
		LOGGER.trace("Obtendo cias com restri\u00E7\u00E3o de Grupo Acesso");
		List<CompanhiaSeguradoraVO> cias = ciaDAO.obterComRestricaoDeGrupoAcesso(codUsuario);
		LOGGER.debug("Encontrou {} cias", cias.size());
		return cicsDAO.obterCias(cias);
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade#obterDepartamentos(int, br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO)
	 */
	@Override
	public List<DepartamentoVO> obterDepartamentosComRestricaoDeposito(int codUsuario,
			CompanhiaSeguradoraVO ciaVO) {
		LOGGER.trace("Obtendo departamentos com restri\u00E7\u00E3o de Parametro Dep\u00F3sito");
		List<DepartamentoVO> deptos = deptoDAO.obterComRestricao(
				ciaVO.getCodigoCompanhia(), codUsuario,
				Tabelas.CONTA_CORRENTE_MOTIVO_DEPOSITO);
		LOGGER.debug("Encontrou {} deptos", deptos.size());
		return deptos;
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade#obterMotivosDeposito(int, br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO)
	 */
	@Override
	public List<MotivoDepositoVO> obterMotivosDeposito(int codUsuario,
			DepartamentoCompanhiaVO deptoCia) {
		LOGGER.trace("Obtendo departamentos com restri\u00E7\u00E3o de Parametro Dep\u00F3sito");
		List<MotivoDepositoVO> motivos = motDepDAO
				.obterComRestricaoDeGrupoAcesso(
						deptoCia.getCompanhia().getCodigoCompanhia(), 
						deptoCia.getDepartamento().getCodigoDepartamento(), 
						codUsuario,
						Tabelas.CONTA_CORRENTE_MOTIVO_DEPOSITO);
		LOGGER.debug("Encontrou {} motivos dep\u00F3sitos", motivos.size());
		return motivos;
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade#obterBancos(br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO)
	 */
	@Override
	public List<BancoVO> obterBancos(CompanhiaSeguradoraVO cia, DepartamentoVO depto, MotivoDepositoVO motivoVO) {
		LOGGER.trace("Obtendo bancos");
		List<BancoVO> bancos = bancoDAO.obterBancosMotivoDeposito(cia, depto, motivoVO);
		LOGGER.debug("Encontrou {} bancos", bancos.size());
		return cicsDAO.obterBancos(bancos);
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade#obterBanco(br.com.bradseg.depi.depositoidentificado.vo.BancoVO)
	 */
	@Override
	public BancoVO obterBanco(BancoVO vo) {
		return cicsDAO.obterBanco(vo);
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade#obterAgencia(br.com.bradseg.depi.depositoidentificado.vo.BancoVO, int)
	 */
	@Override
	public AgenciaVO obterAgencia(BancoVO banco, int codigoAgencia) {
		String descricao = cicsDAO.obterAgencia(banco.getCdBancoExterno(), codigoAgencia);
		
		AgenciaVO agVO = new AgenciaVO(codigoAgencia);
		agVO.setDescricaoAgencia(descricao);
		return agVO;
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade#obterMotivoDeposito(br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO)
	 */
	@Override
	public MotivoDepositoVO obterMotivoDeposito(MotivoDepositoVO motivoDeposito) {
		return motDepDAO.obterPorChave(motivoDeposito);
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade#obterEventoContabil(int)
	 */
	@Override
	public EventoContabilVO obterEventoContabil(int codigoEvento) {
		return cicsDAO.obterEventoContabil(codigoEvento);
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade#obterItemContabil(int)
	 */
	@Override
	public ItemContabilVO obterItemContabil(int codigoTipoEventoNegocio, int codigoItemContabil) {
		return cicsDAO.obterItemContabil(codigoTipoEventoNegocio, codigoItemContabil);
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade#obterCompanhiaSeguradora(br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO)
	 */
	@Override
	public CompanhiaSeguradoraVO obterCompanhiaSeguradora(
			CompanhiaSeguradoraVO companhiaSeguradoraVO) {
		CompanhiaSeguradoraVO vo = ciaDAO.obterPorChave(companhiaSeguradoraVO);
		return cicsDAO.obterCiaPorCodigo(vo.getCodigoCompanhia());
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade#obterDepartamento(br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO)
	 */
	@Override
	public DepartamentoVO obterDepartamento(DepartamentoVO departamentoVO) {
		return deptoDAO.obterPorChave(departamentoVO);
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.DepositoFacade#obterAgenciasMotivo(br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO, br.com.bradseg.depi.depositoidentificado.vo.BancoVO, br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO)
	 */
	@Override
	public List<AgenciaVO> obterAgenciasMotivo(CompanhiaSeguradoraVO ciaVO,
			DepartamentoVO depto, BancoVO bancoVO, MotivoDepositoVO motivoVO) {
		List<AgenciaVO> agencias = bancoDAO.obterAgenciasComRestricaoMotivo(
				ciaVO, depto, bancoVO, motivoVO,
				Tabelas.CONTA_CORRENTE_MOTIVO_DEPOSITO);
		for (AgenciaVO vo : agencias) {
			String nomeAgencia = cicsDAO.obterAgencia(vo.getCdBancoExterno(), vo.getCdAgenciaExterno());
			vo.setDescricaoAgencia(nomeAgencia);
		}
		return agencias;
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade#obterContas(br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO, br.com.bradseg.depi.depositoidentificado.vo.BancoVO, br.com.bradseg.depi.depositoidentificado.vo.AgenciaVO)
	 */
	@Override
	public List<ContaCorrenteAutorizadaVO> obterContasComRestricaoAssociacaoMotivos(
			CompanhiaSeguradoraVO ciaVO, DepartamentoVO depto,
			MotivoDepositoVO motivoVO, BancoVO bancoVO, AgenciaVO agenciaVO) {

		return contaDAO.obterComRestricaoAssociacaoMotivos(ciaVO, depto,
				motivoVO, bancoVO, agenciaVO);
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade#obterConta(br.com.bradseg.depi.depositoidentificado.vo.BancoVO, int, java.lang.Long)
	 */
	@Override
	public ContaCorrenteAutorizadaVO obterConta(BancoVO bancoVO,
			int codigoAgencia, Long contaCorrente) {

		CriterioConsultaVO<?> criterioBanco = new CriterioConsultaVO<>(
				ContaCorrenteAutorizadaCampo.CodigoBanco,
				TipoOperacao.IgualNumerico,
				String.valueOf(bancoVO.getCdBancoExterno()),
				"param1");
		
		CriterioConsultaVO<?> criterioAgencia = new CriterioConsultaVO<>(
				ContaCorrenteAutorizadaCampo.CodigoAgencia,
				TipoOperacao.IgualNumerico,
				String.valueOf(codigoAgencia),
				"param2");
		
		CriterioConsultaVO<?> criterioNumeroConta = new CriterioConsultaVO<>(
				ContaCorrenteAutorizadaCampo.CodigoContaCorrente,
				TipoOperacao.IgualNumerico,
				String.valueOf(contaCorrente),
				"param3");

		FiltroUtil filtro = new FiltroUtil();
		filtro.adicionaCriterio(criterioBanco);
		filtro.adicionaCriterio(criterioAgencia);
		filtro.adicionaCriterio(criterioNumeroConta);
		
		List<ContaCorrenteAutorizadaVO> contas = contaDAO.obterPorFiltro(filtro);
		if (contas.isEmpty()) {
			return null;
		}

		return contas.get(0);
	}
	
	@Override
	public ParametroDepositoVO obterParametro(DepositoVO vo) {
		ParametroDepositoVO parametro = new ParametroDepositoVO(
				vo.getDepartamento().getCodigoDepartamento(), 
				vo.getMotivoDeposito().getCodigoMotivoDeposito());
		parametro.setCompanhia(vo.getCia());
		
		return parametroDAO.obterPorChave(parametro);
	}
	
	@Override
	public void prorrogar(DepositoVO vo, String ipCliente) {
		validarChave(vo);
		
		DepositoVO dep = obterPorChave(vo,
				vo.getCodigoResponsavelUltimaAtualizacao(), ipCliente);
		
		dep.setIndicadorDepositoProrrogado(ConstantesDEPI.INDICADOR_ATIVO);
		dep.setDataProrrogacao(vo.getDataProrrogacao());
		dep.setCodigoResponsavelUltimaAtualizacao(vo.getCodigoResponsavelUltimaAtualizacao());
		
		if (depositoDAO.verificarLancamentoDeposito(dep)) {
			throw new DEPIBusinessException("msg.erro.deposito.comLancamento");
		}
		
		if (dep.getSituacaoArquivoTransferencia() != ConstantesDEPI.ARQUIVO_A_ENVIAR
				&& depositoDAO.verificarEnvioArquivoTransferencia(dep)) {
			
			dep.setSituacaoArquivoTransferencia(ConstantesDEPI.ARQUIVO_RENVIAR);
			
		} else {
			
			dep.setSituacaoArquivoTransferencia(ConstantesDEPI.ARQUIVO_A_ENVIAR);
			
		}
		
		validarProrrogacao(dep);
		
		// Recarrega do banco
		vo = obterPorChave(vo, vo.getCodigoResponsavelUltimaAtualizacao(), ipCliente);
		try {
			depositoDAO.registrarLogs(vo, dep, ConstantesDEPI.PRORROGAR_DEPOSITO);
			depositoDAO.prorrogar(dep);
		} catch (BusinessException e) {
			throw new DEPIBusinessException(e, "msg.erro.deposito.prorrogar");
		}

	}
	
	@Override
	public void cancelar(DepositoVO vo, String ipCliente) {
		validarChave(vo);
		
		DepositoVO dep = obterPorChave(vo,
				vo.getCodigoResponsavelUltimaAtualizacao(), ipCliente);
		
		dep.setIndicadorDepositoCancelado(ConstantesDEPI.INDICADOR_ATIVO);
		dep.setDtCancelamentoDepositoIdentificado(vo.getDtCancelamentoDepositoIdentificado());
		dep.setCodigoResponsavelUltimaAtualizacao(vo.getCodigoResponsavelUltimaAtualizacao());
		
		if (depositoDAO.verificarLancamentoDeposito(dep)) {
			throw new DEPIBusinessException("msg.erro.deposito.comLancamento");
		}
		
		if (dep.getSituacaoArquivoTransferencia() != ConstantesDEPI.ARQUIVO_A_ENVIAR
				&& depositoDAO.verificarEnvioArquivoTransferencia(dep)) {
			
			dep.setSituacaoArquivoTransferencia(ConstantesDEPI.ARQUIVO_RENVIAR);
			
		} else {
			
			dep.setSituacaoArquivoTransferencia(ConstantesDEPI.ARQUIVO_CANCELADO);
			
		}
		
		// Recarrega do banco
		vo = obterPorChave(vo, vo.getCodigoResponsavelUltimaAtualizacao(), ipCliente);
		try {
			depositoDAO.registrarLogs(vo, dep, ConstantesDEPI.PRORROGAR_DEPOSITO);
			depositoDAO.cancelar(dep);
		} catch (BusinessException e) {
			throw new DEPIBusinessException(e, "msg.erro.deposito.cancelar");
		}
	}
	
	@Override
	public MovimentoDepositoVO obterMovimentoDeposito(DepositoVO vo) {
		return movimentoDAO.obterPorChave(new MovimentoDepositoVO(vo
				.getCodigoDepositoIdentificado()));
	}
	
	@Override
	public void inserirMovimento(MovimentoDepositoVO vo, String ipCliente) {
		try {
			validarBancoAgenciaConta(vo);
		} catch (BusinessException e) {
			DepositoVO dep = obterPorChave(
					new DepositoVO(vo.getCodigoMovimento()),
					(int) vo.getCodigoResponsavelUltimaAtualizacao(), ipCliente);
			vo.setBancoMovimento(dep.getBanco().getCdBancoExterno());
			vo.setAgenciaMovimento(dep.getAgencia().getCdAgenciaExterno());
			vo.setContaMovimento(dep.getContaCorrente());
		}
		validarInformacoesMovimento(vo);
		movimentoDAO.inserir(vo);
	}
	
	@Override
	public void alterarMovimento(MovimentoDepositoVO vo) {
		validarInformacoesMovimento(vo);
		movimentoDAO.alterar(vo);
	}

    private void validarInformacoesMovimento(MovimentoDepositoVO vo) throws DEPIBusinessException {
    	try {
			switch (vo.getIndicacaoAcao()) {
			case "T":
				validarBancoAgenciaConta(vo);
				break;
			case "D":
				validarBancoAgenciaConta(vo);
				BaseUtil.validarParametro(vo.getNroCheque(), "N\u00FAmero do Cheque");
				break;

			default:
				break;
			}
		} catch (IntegrationException e) {
			throw new DEPIBusinessException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, e.getMessage());
		}
    }

	private void validarBancoAgenciaConta(MovimentoDepositoVO vo) {
		BaseUtil.validarParametro(vo.getBancoMovimento(), "Banco");
		BaseUtil.validarParametro(vo.getAgenciaMovimento(), "Ag\u00EAncia");
		BaseUtil.validarParametro(vo.getContaMovimento(), "Conta Corrente");
	}
    
    @Override
    public LancamentoDepositoVO obterLancamentoDeposito(DepositoVO vo) {
    	return lancamentoDAO.obterPorDeposito(vo);
    }

	private void validarChave(DepositoVO vo) {
		if (BaseUtil.isNZB(vo)) {
			throw new DEPIBusinessException(ConstantesDEPI.MSG_CUSTOMIZADA,
                "Dep\u00F3sito: vo - DepositoVO \u00E9 null");
		}
		if (BaseUtil.isNZB(vo.getCodigoDepositoIdentificado())) {
			throw new DEPIBusinessException(ConstantesDEPI.MSG_CUSTOMIZADA,
					"C\u00F3digo Dep\u00F3sito");
		}
	}

	private void validarProrrogacao(DepositoVO dep) {
        if (BaseUtil.isNZB(dep.getDtVencimentoDeposito())) {
        	throw new DEPIBusinessException("msg.erro.deposito.obrigatorio", "dtVencimentoDeposito");
        }

        if (BaseUtil.verificarSeAsDatasSaoIguais(dep.getDataProrrogacao(), dep.getDtVencimentoDeposito())) {
        	throw new DEPIBusinessException("msg.erro.deposito.dataProrrogacaoIgualDtVencimento");
        }
        
        if (dep.getDtVencimentoDeposito().compareTo(dep.getDataProrrogacao()) > 0) {
        	throw new DEPIBusinessException("msg.erro.deposito.dataProrrogacaoMenorDtVencimento");
        }

        if (dep.getDataProrrogacao().compareTo(new Date()) < 0) {
        	throw new DEPIBusinessException("msg.erro.deposito.dataProrrogacaoMenorDtAtual");
        }
	}
	
}
