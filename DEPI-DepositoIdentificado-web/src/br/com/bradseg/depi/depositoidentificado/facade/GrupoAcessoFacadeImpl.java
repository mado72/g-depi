package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bradseg.bsad.framework.core.exception.BusinessException;
import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.cics.dao.CICSDepiDAO;
import br.com.bradseg.depi.depositoidentificado.dao.CompanhiaSeguradoraDAO;
import br.com.bradseg.depi.depositoidentificado.dao.DepartamentoDAO;
import br.com.bradseg.depi.depositoidentificado.dao.GrupoAcessoDAO;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.Tabelas;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.GrupoAcessoVO;

/**
 * Bean implementation class for Enterprise Bean: GrupoAcessoSessionFacade
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class GrupoAcessoFacadeImpl implements GrupoAcessoFacade {

	/** A Constante LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(GrupoAcessoFacadeImpl.class);
    
	@Autowired
	private GrupoAcessoDAO grupoAcessoDAO;
	
	@Autowired
	private CICSDepiDAO cicsDepiDAO;
	
	@Autowired
	private CompanhiaSeguradoraDAO ciaDAO;
	
	@Autowired
	private DepartamentoDAO deptoDAO;

    /**
     * alterar
     * @param grupo vo que será alterado
     * @throws IntegrationException - Integração.
     */
    @Override
    public void alterar(GrupoAcessoVO grupo) throws IntegrationException {

    	validarParametrosInclusao(grupo);
        try {
        	grupoAcessoDAO.alterar(grupo);
        } catch (IntegrationException e) {
        	LOGGER.error("alterar(GrupoAcessoVO grupo)", e);
            throw new IntegrationException("alterar(GrupoAcessoVO grupo)",e);
        }
    }

    /**
     * Excluir
     * @param grupos - List<GrupoAcessoVO>.
     * @throws IntegrationException - IntegrationException.
     */
    @Override
    public void excluir(List<GrupoAcessoVO> grupos) throws IntegrationException {
        StringBuilder sb = new StringBuilder();
        if (grupos == null || grupos.isEmpty()) {
            throw new BusinessException("Lista de Grupos de Acesso inv�lida na atualiza��o do Grupo de Acesso.");
        }
        for (GrupoAcessoVO grupo : grupos) {
            if (grupoAcessoDAO.isReferenciado(grupo)) {
                GrupoAcessoVO grp = grupoAcessoDAO.obterGrupoPorChave(grupo);
                if (sb.length() > 0) {
                    sb.append("; ");
                }
                sb.append("Grupo de Acesso: ").append(grp.getNomeGrupoAcesso());
            } else {
            	grupoAcessoDAO.excluir(grupo);
            }
        }
        if (sb.length() > 0) {
            throw new BusinessException(ConstantesDEPI.ERRO_DEPENDENCIA + " - " + sb.toString() + " - " + "Par�metros de Dep�sito");
        }
    }

    /**
     * Excluir
     * @param grupo - GrupoAcessoVO.
     * @throws IntegrationException - IntegrationException.
     */
    @Override
    public void excluir(GrupoAcessoVO grupo) throws IntegrationException {
        validarObjetos(grupo);
        validarChaves(grupo);
        try {
        	grupoAcessoDAO.excluir(grupo);
        } catch (IntegrationException e) {
        	LOGGER.error("excluir(GrupoAcessoVO grupo)",e);
            throw new IntegrationException("excluir(GrupoAcessoVO grupo)",e);
        }
    }

    /**
     * Inserir um novo Grupo de Acesso.
     * @param grupo - GrupoAcessoVO.
     * @return int - Código grupo.
     * @throws IntegrationException - IntegrationException.
     */
    @Override
    public int inserir(GrupoAcessoVO grupo) throws IntegrationException {
        try {
            validarObjetos(grupo);
            validarChaves(grupo);
            validarParametrosInclusao(grupo);
            grupoAcessoDAO.inserir(grupo);
            return grupo.getCodigoGrupoAcesso();
        } catch (IntegrationException e) {
        	LOGGER.error("inserir(GrupoAcessoVO grupo)",e);
            throw new IntegrationException("inserir(GrupoAcessoVO grupo)",e);
        }
    }

    /**
     * Validar Objetos.
     * @param grupo - GrupoAcessoVO.
     * @throws IntegrationException - IntegrationException.
     */
    @Override
    public void validarObjetos(GrupoAcessoVO grupo) throws IntegrationException {
        BaseUtil.validarParametro(grupo, "grupo - GrupoAcessoVO");
        BaseUtil.validarParametro(grupo.getCia(), "Cia");
        BaseUtil.validarParametro(grupo.getDepto(), "Departamento");
    }

    /**
     * Validar Parametros.
     * @param grupo - GrupoAcessoVO.
     * @throws IntegrationException - IntegrationException.
     */
    @Override
    public void validarParametrosInclusao(GrupoAcessoVO grupo) throws IntegrationException {
        BaseUtil.validarParametro(grupo.getUsuarios(), "Funcion�rios");
        BaseUtil.validarParametro(grupo.getCodigoResponsavelUltimaAtualizacao(), "Usu�rio Logado");
    }

    /**
     * Validar Chaves.
     * @param grupo - GrupoAcessoVO.
     * @throws IntegrationException - IntegrationException.
     */
    @Override
    public void validarChaves(GrupoAcessoVO grupo) throws IntegrationException {
        BaseUtil.assertTrueThrowException(BaseUtil.isNZB(grupo), ConstantesDEPI.MSG_CUSTOMIZADA, "Grupo Acesso: grupo - GrupoAcessoVO � null");
        BaseUtil.validarParametro(grupo.getCia().getCodigoCompanhia(), "Cia");
        BaseUtil.validarParametro(grupo.getDepto().getCodigoDepartamento(), "Departamento");
    }

    /**
     * obterPorChave
     * @param grupo - GrupoAcessoVO.
     * @return GrupoAcessoVO.
     * @throws IntegrationException - Integração.
     */
    @Override
    public GrupoAcessoVO obterPorChave(GrupoAcessoVO grupo) throws IntegrationException {
        // BaseUtil.validarParametro(grupo, "grupo - GrupoAcessoVO");
        // BaseUtil.validarParametro(grupo.getCodigoGrupoAcesso(), "getCodigoGrupoAcesso - GrupoAcessoVO");
        return grupoAcessoDAO.obterGrupoPorChave(grupo);
    }

    /**
     * Consulta usando filtro.
     * @param filtro - CriterioFiltroUtil.
     * @return List.
     * @throws IntegrationException - Integração.
     */
    @Override
    public List<GrupoAcessoVO> obterPorFiltro(FiltroUtil filtro) throws IntegrationException {
        // TraduzCriterioUtil.convert(filtro.getCriterios());
        List<GrupoAcessoVO> listaGrupoAcessoVO = new ArrayList<>();
        listaGrupoAcessoVO = grupoAcessoDAO.obterPorFiltro(filtro);
        
        Map<Integer, CompanhiaSeguradoraVO> mapCacheCompanhia = new HashMap<>();
        
        for (GrupoAcessoVO vo : listaGrupoAcessoVO) {
        	int codigoCompanhia = vo.getCia().getCodigoCompanhia();
			CompanhiaSeguradoraVO ciaPorCodigo;
			
			if (mapCacheCompanhia.containsKey(codigoCompanhia)) {
				ciaPorCodigo = mapCacheCompanhia.get(codigoCompanhia);
			}
			else {
				ciaPorCodigo = cicsDepiDAO.obterCiaPorCodigo(codigoCompanhia);
				mapCacheCompanhia.put(codigoCompanhia, ciaPorCodigo);
			}
			if (ciaPorCodigo != null) {
				vo.setCia(ciaPorCodigo);
			}
		}

        return listaGrupoAcessoVO;
   }

    /**
     * obterTodos
     * @return - List<GrupoAcessoVO>.
     * @throws IntegrationException - Integração.
     */
    @Override
    public List<GrupoAcessoVO> obterTodos() throws IntegrationException {
		return grupoAcessoDAO.obterPorFiltro(new FiltroUtil());
    }

    @Override
    public GrupoAcessoVO obterGrupoPorChave(GrupoAcessoVO grupo) throws IntegrationException{
        return grupoAcessoDAO.obterGrupoPorChave(grupo);
    }
    
    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.facade.GrupoAcessoFacade#obterCompanhias(java.lang.Integer)
     */
    @Override
    public List<CompanhiaSeguradoraVO> obterCompanhias(Double codUsuario) {
		List<CompanhiaSeguradoraVO> lista = ciaDAO
				.obterComRestricaoDeGrupoAcesso(codUsuario);

		for (CompanhiaSeguradoraVO vo : lista) {
			CompanhiaSeguradoraVO cia = cicsDepiDAO.obterCiaPorCodigo(vo
					.getCodigoCompanhia());
			vo.setDescricaoCompanhia(cia.getDescricaoCompanhia());
		}
		
		return lista;
    }

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.GrupoAcessoFacade#obterDepartamentos(java.lang.Integer)
	 */
	@Override
	public List<DepartamentoVO> obterDepartamentos(Integer codCia, double codUsuario) {
		return deptoDAO.obterComRestricaoDeGrupoAcesso(codCia, codUsuario, Tabelas.GRUPO_ACESSO);
	}
}
