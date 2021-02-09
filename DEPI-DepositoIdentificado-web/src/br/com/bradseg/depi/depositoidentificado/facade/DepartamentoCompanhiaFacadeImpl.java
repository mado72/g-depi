package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.cics.dao.CICSDepiDAO;
import br.com.bradseg.depi.depositoidentificado.dao.DepartamentoCompanhiaDAO;
import br.com.bradseg.depi.depositoidentificado.dao.DepartamentoDAO;
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

	private static final String LABEL_CADASTRO_PARAMETRODEPOSITO_TABELA = "label.cadastro.parametrodeposito.tabela";

	private static final String LABEL_CADASTRO_GRUPOACESSO_TABELA = "label.cadastro.grupoacesso.tabela";

	private static final String LABEL_CADASTRO_MOTIVODEPOSITO_TABELA = "label.cadastro.motivodeposito.tabela";

	/** A Constante LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DepartamentoCompanhiaFacadeImpl.class);

	@Autowired
	private CICSDepiDAO cicsDepiDAO;
	
	@Autowired
	private DepartamentoCompanhiaDAO deptoCiaDAO;
	
	@Autowired
	private DepartamentoDAO departamentoDAO;
	
	@Autowired
	private ParametroDepositoDAO parametroDepositoDAO;
	
	@Autowired
	private GrupoAcessoDAO grupoAcessoDAO;
	
	@Autowired
	private MotivoDepositoDAO motivoDepositoDAO;

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.DepartamentoCompanhiaFacade#persistir(br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO, java.util.List, java.lang.Integer)
	 */
	@Override
	public void persistir(CompanhiaSeguradoraVO cia, List<DepartamentoVO> deptos, Integer codUsuario) {
    	LOGGER.error("Inicio - persistir(DepartamentoCompanhiaVO vo)"); 

    	// Obtém as instâncias de Departamento que estão sem código (apenas siglas)
    	
    	List<String> siglas = new ArrayList<>();
    	
    	// Se não possui código, guarda a sigla para consultar base de dados e remove a instância de Departamento da lista
    	
    	for (Iterator<DepartamentoVO> iter = deptos.iterator(); iter.hasNext(); ) {
    		DepartamentoVO vo = iter.next();
			if (vo.getCodigoDepartamento() < 1) {
				iter.remove();
				siglas.add(vo.getSiglaDepartamento());
			}
		}
    	
    	if (! siglas.isEmpty()) {
    		
    		// Com a lista de siglas, recupera as instâncias de departamento para adicionar novamente à lista de deptos.
    		List<DepartamentoVO> deptosBase = departamentoDAO.obterDeListaSiglas(siglas);
    		deptos.addAll(deptosBase);
    		
    	}
    	
    	deptoCiaDAO.persistir(cia, deptos, codUsuario);

    	LOGGER.error("Fim - alterar(DepartamentoVO vo)"); 
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
	 * @see br.com.bradseg.depi.depositoidentificado.facade.DepartamentoCompanhiaFacade#excluir(br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO)
	 */
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

		if (parametroDepositoDAO.associacaoReferenciada(vo.getCompanhia(),
				vo.getDepartamento())) {
			throw new DEPIIntegrationException(
					ConstantesDEPI.DepartamentoCompanhia.ERRO_DEPENDENCIA,
					vo.getDepartamento().getSiglaDepartamento(), 
					BaseUtil.getTexto(LABEL_CADASTRO_PARAMETRODEPOSITO_TABELA));
		}
	}
	
	private void verificarReferenciadoGrupoAcesso(
			DepartamentoCompanhiaVO vo) {
		if (grupoAcessoDAO.associacaoReferenciada(vo.getCompanhia(), vo.getDepartamento())) {
			throw new DEPIIntegrationException(
					ConstantesDEPI.DepartamentoCompanhia.ERRO_DEPENDENCIA,
					vo.getDepartamento().getSiglaDepartamento(),
					BaseUtil.getTexto(LABEL_CADASTRO_GRUPOACESSO_TABELA));
		}
	}
	
	private void verificarReferenciadoMotivoDeposito(
			DepartamentoCompanhiaVO vo) {
		if (motivoDepositoDAO.associacaoReferenciada(vo.getCompanhia(), vo.getDepartamento())) {
			throw new DEPIIntegrationException(
					ConstantesDEPI.DepartamentoCompanhia.ERRO_DEPENDENCIA,
					vo.getDepartamento().getSiglaDepartamento(),
					BaseUtil.getTexto(LABEL_CADASTRO_MOTIVODEPOSITO_TABELA));
		}
	}

    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.facade.DepartamentoFacade#obterPorFiltro(br.com.bradseg.depi.depositoidentificado.util.FiltroUtil)
     */
    @Override    
    public List<DepartamentoCompanhiaVO> obterPorFiltro(FiltroUtil filtro) throws IntegrationException {
		
    	LOGGER.error("Inicio - obterPorFiltro(FiltroUtil filtro)");
    	
        List<DepartamentoCompanhiaVO> lista = deptoCiaDAO.obterPorFiltro(filtro);
        
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

    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.facade.DepartamentoCompanhiaFacade#obterPorCompanhiaSeguradora(br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO)
     */
    @Override
    public List<DepartamentoCompanhiaVO> obterPorCompanhiaSeguradora(CompanhiaSeguradoraVO cia) throws IntegrationException {
    	
		List<DepartamentoCompanhiaVO> lista = deptoCiaDAO.obterPorCompanhiaSeguradora(cia);
		
		if (!lista.isEmpty()) {
			CompanhiaSeguradoraVO ciaVo = cicsDepiDAO.obterCiaPorCodigo(cia.getCodigoCompanhia());
			
			for (DepartamentoCompanhiaVO vo : lista) {
				vo.setCompanhia(ciaVo);
			}
		}
		return lista;
    }
    
    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.facade.DepartamentoCompanhiaFacade#obterPorChave(br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO)
     */
    @Override
    public DepartamentoCompanhiaVO obterPorChave(DepartamentoCompanhiaVO vo) {
    	return deptoCiaDAO.obterPorChave(vo);
    }

    /**
     * Validar integridade das informações do VO.
     * @param depCia - DepartamentoCompanhiaVO.
     * @throws DEPIIntegrationException - DEPIIntegrationException.
     */
    public void validarIntegridadeBean(DepartamentoCompanhiaVO depCia) throws DEPIIntegrationException {
        BaseUtil.assertTrueThrowException(BaseUtil.isNZB(depCia.getDepartamento()),
            ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Departamento");
        BaseUtil.assertTrueThrowException(BaseUtil.isNZB(depCia.getCompanhia().getCodigoCompanhia()),
            ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Companhia Seguradora");
        BaseUtil.assertTrueThrowException(BaseUtil.isNZB(depCia.getCodigoResponsavelUltimaAtualizacao()),
            ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, ConstantesDEPI.Geral.ERRO_USUARIO_OBRIGATORIO);
    }
    
    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.facade.DepartamentoCompanhiaFacade#obterDepartamentos(java.util.ArrayList)
     */
    @Override
    public List<DepartamentoVO> obterDepartamentos(ArrayList<String> siglas) {
    	return departamentoDAO.obterDeListaSiglas(siglas);
    }
}
