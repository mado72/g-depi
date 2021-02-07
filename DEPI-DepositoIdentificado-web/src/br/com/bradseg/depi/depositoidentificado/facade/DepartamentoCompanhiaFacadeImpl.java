package br.com.bradseg.depi.depositoidentificado.facade;

import static br.com.bradseg.depi.depositoidentificado.util.BaseUtil.concatenarComHifen;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.dao.DepartamentoDAO;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.Tabelas;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI.Geral;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;

/**
 * Bean implementation class for Enterprise Bean: DepartamentoFacade
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class DepartamentoCompanhiaFacadeImpl implements DepartamentoFacade {

    private static final String CODIGO_RESPONSAVEL = "Código do Responsável";
    
	/** A Constante LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DepartamentoCompanhiaFacadeImpl.class);

	@Autowired
	private DepartamentoDAO departamentoDAO;

    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.facade.DepartamentoFacade#obterPorChave(br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO)
     */
    @Override
    public DepartamentoVO obterPorChave(DepartamentoVO vo) throws IntegrationException {
        return departamentoDAO.obterPorChave(vo);
    }

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.DepartamentoFacade#alterar(br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO)
	 */
	@Override
    public void alterar(DepartamentoVO vo) throws IntegrationException {
		
    	LOGGER.error("Inicio - alterar(DepartamentoVO vo)"); 

    	validarAlterar(vo);
    	validaChave(vo);
    	
    	departamentoDAO.obterPorChave(vo);
    	departamentoDAO.alterar(vo);

    	LOGGER.error("Fim - alterar(DepartamentoVO vo)"); 
    	
    }
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.DepartamentoFacade#excluir(br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO)
	 */
	@Override
	public void excluir(DepartamentoVO vo) throws IntegrationException {
		
		LOGGER.error("Fim - excluir(DepartamentoVO vo)"); 

		departamentoDAO.excluir(vo);
		
		LOGGER.error("Fim - excluir(DepartamentoVO vo)");
		
	}

    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.facade.DepartamentoFacade#excluir(java.util.List)
     */
    @Override
    public void excluir(List<DepartamentoVO> vos) throws IntegrationException {
        StringBuilder sb = new StringBuilder();

        for (DepartamentoVO vo : vos) {
            validarExcluir(vo);
            
            if (departamentoDAO.isReferenciado(vo)) {
                if (sb.length() > 0) {
                    sb.append("; ");
                }
                sb.append(" Departamento: ").append(departamentoDAO.obterPorChave(vo).getSiglaDepartamento());
            } else {
            	departamentoDAO.excluir(vo);
            }
        }
        if (sb.length() > 0) {
			throw new DEPIIntegrationException(ConstantesDEPI.ERRO_DEPENDENCIA, sb.toString(),
							"Associação Departamentos x Companhia");
        }
    }

    private void validarAlterar(DepartamentoVO vo) throws IntegrationException {
        
    	validarInserir(vo);
    	
    }

    /**
     * Validar o VO de exclusão
     * @param vo VO de Departamento
     * @throws IntegrationException Exceção de aplicação
     */
    private void validarExcluir(DepartamentoVO vo) throws IntegrationException {
        if (BaseUtil.isNZB(vo.getCodigoResponsavelUltimaAtualizacao())) {
            throw new IntegrationException(concatenarComHifen(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, CODIGO_RESPONSAVEL));
        }
        if (vo.getCodigoResponsavelUltimaAtualizacao() > 9999999) {
            throw new IntegrationException(concatenarComHifen(Geral.ERRO_CAMPO_EXCESSO, CODIGO_RESPONSAVEL, "9999999"));
        }
    }

    /**
     * Inserir Departamento
     * @param vo VO de Departamento
     * @throws IntegrationException Exceção de aplicação
     */
    @Override
    public void inserir(DepartamentoVO vo) throws IntegrationException {
        validarInserir(vo);

        departamentoDAO.inserir(vo);

    }

    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.facade.DepartamentoFacade#obterPorFiltro(br.com.bradseg.depi.depositoidentificado.util.FiltroUtil)
     */
    @Override    
    public List<DepartamentoVO> obterPorFiltro(FiltroUtil filtro) throws IntegrationException {
		
    	LOGGER.error("Inicio - obterPorFiltro(FiltroUtil filtro)");
    	
        return departamentoDAO.obterPorFiltro(filtro);
    }

    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.facade.DepartamentoFacade#obterTodos()
     */
    @Override
    public List<DepartamentoVO> obterTodos() throws IntegrationException {
		
    	LOGGER.error("Inicio - obterTodos()");
    	
        return departamentoDAO.obterTodos();
    }

    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.facade.DepartamentoFacade#obterComRestricaoDeGrupoAcesso(int, java.lang.Double, br.com.bradseg.depi.depositoidentificado.enums.Tabelas)
     */
    @Override
    public List<DepartamentoVO> obterComRestricaoDeGrupoAcesso(int codigoCia, int codigoUsuario, Tabelas e) throws IntegrationException {
		
		LOGGER.error("Inicio - obterComRestricaoDeGrupoAcesso(int codigoCia, Double codigoUsuario, Tabelas e)"); 

    	return departamentoDAO.obterComRestricaoDeGrupoAcesso(codigoCia,codigoUsuario, e);
    	
    }

    /**
     * método que obtem lista de departamentos por companhia
     * @param vo - CompanhiaSeguradoraVO
     * @return List de VO's de departamentos
     * @throws IntegrationException trata exceção
     */
    @Override
    public List<DepartamentoVO> obterPorCompanhiaSeguradora(CompanhiaSeguradoraVO vo) throws IntegrationException {
    	
    	LOGGER.error("Inicio - obterPorCompanhiaSeguradora(int codigoCia, Double codigoUsuario, Tabelas e)"); 

    	return departamentoDAO.obterPorCompanhiaSeguradora(vo);
    }

	/**
	 * Validar o VO de inserção
	 * @param vo VO de Departamento
	 * @throws IntegrationException Exceção de aplicação
	 */
	private void validarInserir(DepartamentoVO vo) throws IntegrationException {
		
	    if (BaseUtil.isNZB(vo.getSiglaDepartamento())) {
	        throw new IntegrationException(concatenarComHifen(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Sigla do Departamento"));
	    }
	    if (vo.getSiglaDepartamento().length() > ConstantesDEPI.MAX_SIZE_SIGLA_DEPARTAMENTO) {
	        throw new IntegrationException(concatenarComHifen(Geral.ERRO_CAMPO_EXCESSO, "Sigla do Departamento", String.valueOf(ConstantesDEPI.MAX_SIZE_SIGLA_DEPARTAMENTO)));
	    }
	    if (BaseUtil.isNZB(vo.getNomeDepartamento())) {
	        throw new IntegrationException(concatenarComHifen(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Nome do Departamento"));
	    }
	    if (vo.getNomeDepartamento().length() > ConstantesDEPI.MAX_SIZE_DEFAULT_NOME) {
	        throw new IntegrationException(concatenarComHifen(Geral.ERRO_CAMPO_EXCESSO, "Nome do Departamento", String.valueOf(ConstantesDEPI.MAX_SIZE_DEFAULT_NOME)));
	    }
	    if (vo.getCodigoResponsavelUltimaAtualizacao() == 0) {
	        throw new IntegrationException(concatenarComHifen(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, CODIGO_RESPONSAVEL));
	    }
	    if (vo.getCodigoResponsavelUltimaAtualizacao() > ConstantesDEPI.MAX_SIZE_CODIGO_USUARIO) {
	        throw new IntegrationException(concatenarComHifen(Geral.ERRO_CAMPO_EXCESSO, CODIGO_RESPONSAVEL, String.valueOf(ConstantesDEPI.MAX_SIZE_CODIGO_USUARIO)));
	    }
	}

    /**
     * método que válida a chave primária
     * @param vo - objeto que será validado
     * @throws IntegrationException - trata erros
     */
    private void validaChave(DepartamentoVO vo) throws IntegrationException {
    	
    	LOGGER.error("Inicio - validaResponsavel(MotivoDepositoVO vo)"); 
    	
        if (vo.getCodigoDepartamento() == 0) {
			throw new DEPIIntegrationException(
					ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO,
					"Código do Departamento");
        }
    }

    
}
