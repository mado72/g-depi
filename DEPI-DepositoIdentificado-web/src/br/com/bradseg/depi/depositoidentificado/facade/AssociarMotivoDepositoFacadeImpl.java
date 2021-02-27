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
import br.com.bradseg.depi.depositoidentificado.dao.DepartamentoDAO;
import br.com.bradseg.depi.depositoidentificado.dao.MotivoDepositoDAO;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIBusinessException;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.Tabelas;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.AssociarMotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
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

	private static final int TIPO_EVENTO_CONTABIL = 19;
	
	private static final String STR1 = "Identificação Depósitos";
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
	private CICSDepiDAO cicsDAO;

    /**
     * Excluir AssociarMotivoDepositos
     * @param vos VO's de AssociarMotivoDeposito
     * @throws IntegrationException Exceção de aplicação
     */
    public void excluir(List<AssociarMotivoDepositoVO> vos) throws IntegrationException {
    	
        if (vos == null || vos.isEmpty()) {
            throw new IntegrationException("Lista inválida!");
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
            throw new IntegrationException(ConstantesDEPI.ERRO_DEPENDENCIA_MODULO + " - " + codsAssocMotivos.toString() + " - " + STR1);
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
	public String obterAgencia(BancoVO banco, int codigoAgencia) {
		return cicsDAO.obterAgencia(banco.getCdBancoExterno(), codigoAgencia);
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
		return cicsDAO.obterEventoContabil(TIPO_EVENTO_CONTABIL, codigoEvento);
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade#obterItemContabil(int)
	 */
	@Override
	public ItemContabilVO obterItemContabil(int codigoTipoEventoNegocio, int codigoItemContábil) {
		return cicsDAO.obterItemContabil(codigoTipoEventoNegocio, codigoItemContábil);
	}

}
