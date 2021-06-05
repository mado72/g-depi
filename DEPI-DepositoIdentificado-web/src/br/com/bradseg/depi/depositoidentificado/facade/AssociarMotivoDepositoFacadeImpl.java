package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.cics.dao.CICSDepiDAO;
import br.com.bradseg.depi.depositoidentificado.dao.AssociarMotivoDepositoDAO;
import br.com.bradseg.depi.depositoidentificado.dao.BancoDAO;
import br.com.bradseg.depi.depositoidentificado.dao.CompanhiaSeguradoraDAO;
import br.com.bradseg.depi.depositoidentificado.dao.ContaCorrenteDAO;
import br.com.bradseg.depi.depositoidentificado.dao.DepartamentoDAO;
import br.com.bradseg.depi.depositoidentificado.dao.MotivoDepositoDAO;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIBusinessException;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.ContaCorrenteAutorizadaCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.Tabelas;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoOperacao;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.AgenciaVO;
import br.com.bradseg.depi.depositoidentificado.vo.AssociarMotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;
import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.EventoContabilVO;
import br.com.bradseg.depi.depositoidentificado.vo.ItemContabilVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

/**
 * Implementa a associação de motivo depósito
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AssociarMotivoDepositoFacadeImpl implements AssociarMotivoDepositoFacade {

	private static final String MODULO = "Identifica\u00E7\u00E7o Dep\u00F3sitos";
    protected static final Logger LOGGER = LoggerFactory.getLogger(AssociarMotivoDepositoFacadeImpl.class);
    
	/**
	 * Associação Motivo Deposito DAO
	 */
	@Autowired
	private AssociarMotivoDepositoDAO amdDAO;
	
	@Autowired
	private CompanhiaSeguradoraDAO ciaDAO;
	
	@Autowired
	private DepartamentoDAO deptoDAO;
	
	@Autowired
	private MotivoDepositoDAO motDepDAO;
	
	@Autowired
	private BancoDAO bancoDAO;
	
	@Autowired
	private ContaCorrenteDAO contaDAO;
		
	@Autowired
	private CICSDepiDAO cicsDAO;

    /**
     * Excluir AssociarMotivoDepositos
     * @param vos VO's de AssociarMotivoDeposito
     * @throws IntegrationException Exceção de aplicação
     */
    public void excluir(List<AssociarMotivoDepositoVO> vos) throws IntegrationException {
    	
        if (vos == null || vos.isEmpty()) {
            throw new DEPIIntegrationException("Lista inv\u00e1lida!");
        }

        StringBuilder codsAssocMotivos = new StringBuilder();

        for (AssociarMotivoDepositoVO vo : vos) {
            if (amdDAO.isReferenciado(vo)) {
            	
                if (codsAssocMotivos.length() > 0) {
                    codsAssocMotivos.append("; ");
                }
                codsAssocMotivos.append(vo.toString());
            } else {
            	amdDAO.excluir(vo);
            }
        }
        
        if (codsAssocMotivos.length() > 0) {
            throw new DEPIBusinessException(ConstantesDEPI.ERRO_DEPENDENCIA_MODULO, codsAssocMotivos.toString(), MODULO);
        }
    }

    /**
     * Inserir AssociarMotivoDeposito
     * @param vo - AssociarMotivoDepositoVO.
     * @throws IntegrationException - Integração.
     */
    @Override
	public void inserir(AssociarMotivoDepositoVO vo) throws IntegrationException {

    	amdDAO.inserir(vo);
    	
    }

    /**
     * Método de obter por filtro
     * @param codUsuario - BigDecimal.
     * @param filtro parâmetro depósito com o código do objeto requisitado
     * @return List<AssociarMotivoDepositoVO>
     */
	@Override
	public List<AssociarMotivoDepositoVO> obterPorFiltro(int codUsuario,
			FiltroUtil filtro) {

    	return amdDAO.obterPorFiltroComRestricaoDeGrupoAcesso(filtro, codUsuario);
    	
    }

    /**
     * método que retorna o motivo depósito pelo código
     * @param vo - conta conrrente com o código
     * @return - retona o conta conrrente referente ao código passado
     * @throws IntegrationException - trata erros de negócio
     */
    @Override
	public AssociarMotivoDepositoVO obterPorChave(AssociarMotivoDepositoVO vo) throws IntegrationException {
    	
        return amdDAO.obterPorChave(vo);
        
    }

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade#excluirLista(java.util.List)
	 */
	@Override
	public void excluirLista(List<AssociarMotivoDepositoVO> voList) {
		
		StringBuilder referenciados = new StringBuilder();
		for (AssociarMotivoDepositoVO vo : voList) {
			if (amdDAO.isReferenciado(vo)) {
				referenciados.append(BaseUtil.getTextoFormatado(ConstantesDEPI.Geral.ERRO_EXCLUSAO_ITEM, vo.toString()));
			}
		}
		
		if (referenciados.length() > 0) {
			throw new DEPIBusinessException(ConstantesDEPI.ERRO_DEPENDENCIAS,
					referenciados.toString(), "Dep\u00F3sito Identificado");
		}
		
		for (AssociarMotivoDepositoVO vo : voList) {
			amdDAO.excluir(vo);
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
	public List<DepartamentoVO> obterDepartamentosComRestricaoParametroDeposito(int codUsuario,
			CompanhiaSeguradoraVO ciaVO) {
		LOGGER.trace("Obtendo departamentos com restri\u00E7\u00E3o de Parametro Dep\u00F3sito");
		List<DepartamentoVO> deptos = deptoDAO.obterComRestricao(
				ciaVO.getCodigoCompanhia(), codUsuario,
				Tabelas.PARAMETRO_DEPOSITO);
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
						Tabelas.PARAMETRO_DEPOSITO);
		LOGGER.debug("Encontrou {} motivos dep\u00F3sitos", motivos.size());
		return motivos;
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade#obterBancos(br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO)
	 */
	@Override
	public List<BancoVO> obterBancos(CompanhiaSeguradoraVO cia) {
		LOGGER.trace("Obtendo bancos");
		List<BancoVO> bancos = bancoDAO.obterBancos(cia);
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
	 * @see br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade#obterAgencias(br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO, br.com.bradseg.depi.depositoidentificado.vo.BancoVO)
	 */
	@Override
	public List<AgenciaVO> obterAgencias(CompanhiaSeguradoraVO ciaVO,
			BancoVO bancoVO) {
		List<AgenciaVO> agencias = bancoDAO.obterAgencias(ciaVO, bancoVO);
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
	public List<ContaCorrenteAutorizadaVO> obterContas(
			int codUsuario, CompanhiaSeguradoraVO ciaVO, BancoVO bancoVO, AgenciaVO agenciaVO) {

		CriterioConsultaVO<?> criterioCia = new CriterioConsultaVO<>(
				ContaCorrenteAutorizadaCampo.CodigoCia,
				TipoOperacao.IgualNumerico, String.valueOf(ciaVO
						.getCodigoCompanhia()), "param1");
		
		CriterioConsultaVO<?> criterioBanco = new CriterioConsultaVO<>(
				ContaCorrenteAutorizadaCampo.CodigoBanco,
				TipoOperacao.IgualNumerico,
				String.valueOf(bancoVO.getCdBancoExterno()),
				"param2");
		
		CriterioConsultaVO<?> criterioAgencia = new CriterioConsultaVO<>(
				ContaCorrenteAutorizadaCampo.CodigoAgencia,
				TipoOperacao.IgualNumerico,
				String.valueOf(agenciaVO.getCdAgenciaExterno()),
				"param3");

		FiltroUtil filtro = new FiltroUtil();
		filtro.adicionaCriterio(criterioCia);
		filtro.adicionaCriterio(criterioBanco);
		filtro.adicionaCriterio(criterioAgencia);
		
		return contaDAO.obterPorFiltroComRestricao(codUsuario, filtro);
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
	
}
