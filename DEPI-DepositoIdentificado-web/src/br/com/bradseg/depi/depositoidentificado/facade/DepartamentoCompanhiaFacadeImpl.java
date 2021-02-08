package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.dao.DepartamentoCompanhiaDAO;
import br.com.bradseg.depi.depositoidentificado.dao.GrupoAcessoDAO;
import br.com.bradseg.depi.depositoidentificado.dao.MotivoDepositoDAO;
import br.com.bradseg.depi.depositoidentificado.dao.ParametroDepositoDAO;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIBusinessException;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;

/**
 * Bean implementation class for Enterprise Bean: DepartamentoFacade
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class DepartamentoCompanhiaFacadeImpl implements DepartamentoCompanhiaFacade {

    /** A Constante LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DepartamentoCompanhiaFacadeImpl.class);

	@Autowired
	private DepartamentoCompanhiaDAO deptoCiaDAO;
	
	@Autowired
	private ParametroDepositoDAO parametroDepositoDAO;
	
	@Autowired
	private GrupoAcessoDAO grupoAcessoDAO;
	
	@Autowired
	private MotivoDepositoDAO motivoDepositoDAO;

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.DepartamentoCompanhiaFacade#persistir(br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO)
	 */
	@Override
	public void persistir(DepartamentoCompanhiaVO vo) {
    	LOGGER.error("Inicio - persistir(DepartamentoCompanhiaVO vo)"); 

    	validarIntegridadeBean(vo);

//    	FIXME Verificar se este código é necessário
//		DepartamentoCompanhiaVO persistido = deptoCiaDAO
//				.obterPorCompanhiaSeguradora(vo.getCompanhia());
//		
//		if (persistido != null) {
//			
//		}
    	deptoCiaDAO.persistir(vo);

    	LOGGER.error("Fim - alterar(DepartamentoVO vo)"); 
    }
	
	@Override
	public void excluir(DepartamentoCompanhiaVO vo) throws IntegrationException {
		
		LOGGER.error("Fim - excluir(DepartamentoVO vo)");
		
		verificarReferenciadoParametrosDeposito(vo);
		verificarReferenciadoGrupoAcesso(vo);
		verificarReferenciadoMotivoDeposito(vo);

		deptoCiaDAO.excluir(vo);
		
		LOGGER.error("Fim - excluir(DepartamentoVO vo)");
		
	}

	private void verificarReferenciadoParametrosDeposito(
			DepartamentoCompanhiaVO vo) {
		List<String> referenciados = new ArrayList<>();
		for (DepartamentoVO departamentoVO : vo.getDeptos()) {
			if (parametroDepositoDAO.associacaoReferenciada(vo.getCompanhia(), departamentoVO)) {
				referenciados.add(departamentoVO.getSiglaDepartamento());
			}
		}
		
		if (! referenciados.isEmpty()) {
			String codCompanhia = String.valueOf(vo.getCompanhia().getCodigoCompanhia());
			throw new DEPIIntegrationException(
					ConstantesDEPI.DepartamentoCompanhia.ERRO_PARAMETRODEPOSITO_REFERENCIADO,
					codCompanhia, gerarListaMensagem(referenciados));
		}
	}
	
	private void verificarReferenciadoGrupoAcesso(
			DepartamentoCompanhiaVO vo) {
		List<String> referenciados = new ArrayList<>();
		for (DepartamentoVO departamentoVO : vo.getDeptos()) {
			if (grupoAcessoDAO.associacaoReferenciada(vo.getCompanhia(), departamentoVO)) {
				referenciados.add(departamentoVO.getSiglaDepartamento());
			}
		}
		
		if (! referenciados.isEmpty()) {
			String codCompanhia = String.valueOf(vo.getCompanhia().getCodigoCompanhia());
			throw new DEPIIntegrationException(
					ConstantesDEPI.DepartamentoCompanhia.ERRO_GRUPODEPARTAMENTO_REFERENCIADO,
					codCompanhia, gerarListaMensagem(referenciados));
		}
	}
	
	private void verificarReferenciadoMotivoDeposito(
			DepartamentoCompanhiaVO vo) {
		List<String> referenciados = new ArrayList<>();
		for (DepartamentoVO departamentoVO : vo.getDeptos()) {
			if (motivoDepositoDAO.associacaoReferenciada(vo.getCompanhia(), departamentoVO)) {
				referenciados.add(departamentoVO.getSiglaDepartamento());
			}
		}
		
		if (! referenciados.isEmpty()) {
			String codCompanhia = String.valueOf(vo.getCompanhia().getCodigoCompanhia());
			throw new DEPIIntegrationException(
					ConstantesDEPI.DepartamentoCompanhia.ERRO_GRUPODEPARTAMENTO_REFERENCIADO,
					codCompanhia, gerarListaMensagem(referenciados));
		}
	}
	
	private String gerarListaMensagem(Collection<?> itens) {
		StringBuilder msg = new StringBuilder();
		for (Object item : itens) {
			if (msg.length() > 0) {
				msg.append("; ");
			}
			msg.append(item);
		}
		return msg.toString();
	}

    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.facade.DepartamentoFacade#excluir(java.util.List)
     */
    @Override
    public void excluir(List<DepartamentoCompanhiaVO> ciaVOList) throws IntegrationException {
        StringBuilder msg = new StringBuilder();

        for (DepartamentoCompanhiaVO item : ciaVOList) {
        	
        	try {
        		excluir(item);
        	} catch (DEPIBusinessException e) {
        		msg.append("<li>").append(e.getMessage()).append("</li>");
        	}
        	
        }
        if (msg.length() > 0) {
			throw new DEPIIntegrationException(ConstantesDEPI.ERRO_DEPENDENCIAS, msg.toString());
        }
    }

    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.facade.DepartamentoFacade#obterPorFiltro(br.com.bradseg.depi.depositoidentificado.util.FiltroUtil)
     */
    @Override    
    public List<DepartamentoCompanhiaVO> obterPorFiltro(FiltroUtil filtro) throws IntegrationException {
		
    	LOGGER.error("Inicio - obterPorFiltro(FiltroUtil filtro)");
    	
        return deptoCiaDAO.obterPorFiltro(filtro);
    }

    /**
     * método que obtem lista de departamentos por companhia
     * @param vo - CompanhiaSeguradoraVO
     * @return List de VO's de departamentos
     * @throws IntegrationException trata exceção
     */
    @Override
    public DepartamentoCompanhiaVO obterPorCompanhiaSeguradora(CompanhiaSeguradoraVO vo) throws IntegrationException {
    	
		return deptoCiaDAO.obterPorCompanhiaSeguradora(vo);
    }

    /**
     * Validar integridade das informações do VO.
     * @param depCia - DepartamentoCompanhiaVO.
     * @throws DEPIIntegrationException - DEPIIntegrationException.
     */
    public void validarIntegridadeBean(DepartamentoCompanhiaVO depCia) throws DEPIIntegrationException {
        BaseUtil.assertTrueThrowException(BaseUtil.isNZB(depCia.getDeptos()),
            ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Departamentos");
        BaseUtil.assertTrueThrowException(BaseUtil.isNZB(depCia.getCompanhia().getCodigoCompanhia()),
            ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Companhia Seguradora");
        BaseUtil.assertTrueThrowException(BaseUtil.isNZB(depCia.getCodigoResponsavelUltimaAtualizacao()),
            ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, ConstantesDEPI.Geral.ERRO_USUARIO_OBRIGATORIO);
    }
    
}
