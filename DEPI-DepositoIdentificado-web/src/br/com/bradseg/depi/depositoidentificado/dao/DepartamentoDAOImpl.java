package br.com.bradseg.depi.depositoidentificado.dao;

import static br.com.bradseg.depi.depositoidentificado.util.BaseUtil.concatenarComHifen;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.core.exception.BusinessException;
import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.DepartamentoDataMapper;
import br.com.bradseg.depi.depositoidentificado.enums.Tabelas;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.QuerysDepi;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;

/**
 * Dao que representa a entidade Departamento
 * @author Globality
 */
@Repository
public class DepartamentoDAOImpl extends JdbcDao implements DepartamentoDAO {

	private static final String PARAM_PRM1 = "prm1";
	
	private static final String PARAM_PRM2 = "prm2";
	
	private static final String PARAM_PRM3 = "prm3";

	private static final String PARAM_WHR1 = "whr1";
	
	private static final String PARAM_WHR2 = "whr2";

	/** A Constante LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DepartamentoDAOImpl.class);
	
	/** A(O) data source. */
	@Autowired
	private DataSource dataSource;
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.bsad.framework.core.jdbc.JdbcDao#getDataSource()
	 */
	@Override
	public DataSource getDataSource() {		
		return dataSource;
	}
    /**
     * {@inheritDoc}
     */
	@Override
    public List<DepartamentoVO> obterComRestricaoDeGrupoAcesso(int codigoCia, Double codigoUsuario, Tabelas e)    {
 
    	StringBuilder query = null;
        final String msg;
       
        try {

            if (e.equals(Tabelas.GRUPO_ACESSO)) {
            	query = new StringBuilder (QuerysDepi.DEPARTAMENTO_OBTERCOMRESTRICAODEGRUPOACESSO);
                msg = " um Grupo de Acesso vinculado ao usuário.";
            } else if (e.equals(Tabelas.PARAMETRO_DEPOSITO)) {
            	query = new StringBuilder (QuerysDepi.DEPARTAMENTO_OBTERCOMRESTRICAODEPARAMETRODEPOSITO);
                msg = " um Parametro de Depósito ou Grupo de Acesso vinculado ao usuário.";
            } else if (e.equals(Tabelas.CONTA_CORRENTE_MOTIVO_DEPOSITO)) {
            	query = new StringBuilder (QuerysDepi.DEPARTAMENTO_OBTERCOMRESTRICAODECONTACORRENTEMOTIVODEPOSITO);
                msg = " uma Associação de Motivo ou Grupo de Acesso vinculado ao usuário.";
            } else if (e.equals(Tabelas.DEPOSITO)) {
            	query = new StringBuilder (QuerysDepi.DEPARTAMENTO_OBTERCOMRESTRICAODEDEPOSITO);
                msg = " um Depósito ou Grupo de Acesso vinculado ao usuário.";
            } else {
                throw new IntegrationException("Enum inválido.");
            }

            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue(PARAM_WHR1,codigoCia);
            params.addValue(PARAM_WHR2,codigoUsuario);
            
            List<DepartamentoVO> departamentoVO = getJdbcTemplate() .query(query.toString(), params, new DepartamentoDataMapper());
            
            
            if (departamentoVO == null || departamentoVO.isEmpty() ) {
                    throw new IntegrationException(ConstantesDEPI.ERRO_RELACIONAMENTOS_NAO_CADASTRADOS + " - " + new StringBuilder(msg).toString());
                }
                return departamentoVO;
            } finally {
            	LOGGER.info("obterComRestricaoDeGrupoAcesso(int codigoCia, BigDecimal codigoUsuario, Tabelas e)");
        }
        
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public void alterar(DepartamentoVO vo){
    	 
        	
       	try {
       		verificarRegistroExistente(vo);
            
            StringBuilder queryUpd = new StringBuilder(QuerysDepi.DEPARTAMENTO_UPDATE);

			MapSqlParameterSource paramsUpd = new MapSqlParameterSource();

			paramsUpd.addValue(PARAM_PRM1, vo.getSiglaDepartamento());
			paramsUpd.addValue(PARAM_PRM2, vo.getNomeDepartamento());
			paramsUpd.addValue(PARAM_PRM3, vo.getCodigoResponsavelUltimaAtualizacao());
			paramsUpd.addValue(PARAM_WHR1, vo.getCodigoDepartamento());
    		
			Integer count = getJdbcTemplate().update(queryUpd.toString(), paramsUpd);

            if (count == 0) {
                throw new BusinessException(ConstantesDEPI.ERRO_REGISTRO_INEXISTENTE);
            }

        } finally {
        	LOGGER.info("alterar(DepartamentoVO vo)"); 
        }
    }
	private void verificarRegistroExistente(DepartamentoVO vo) {
		StringBuilder query = new StringBuilder(QuerysDepi.DEPARTAMENTO_EXISTS);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(PARAM_WHR1, vo.getSiglaDepartamento().trim());

		List<DepartamentoVO> departamentos = getJdbcTemplate().query(query.toString(), params, new DepartamentoDataMapper());
		  
		if (!departamentos.isEmpty()) {
			for (DepartamentoVO depto: departamentos) {
				
				if (!(vo.getCodigoDepartamento() == depto.getCodigoDepartamento())
						&& depto.getIndicadoRegistroAtivo().equals(ConstantesDEPI.INDICADOR_ATIVO)) {
					
					throw new IntegrationException(
							BaseUtil.concatenarComHifen(ConstantesDEPI.ERRO_REGISTRO_INEXISTENTE2,
									new StringBuilder(" Sigla: ").append(
											vo.getSiglaDepartamento())
											.toString()));
				}
			}
		}
	}
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void excluir(List<DepartamentoVO> vos) {
    	try { 
            for (DepartamentoVO dep : vos) {
                this.excluir(dep);
            }
    	} finally {
    		LOGGER.info("excluir(List<DepartamentoVO> vos)"); 
    	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isReferenciado(DepartamentoVO vo) {

    	StringBuilder query = new StringBuilder(QuerysDepi.DEPARTAMENTO_REFERENCIADO_COMPANHIADEPARTAMENTO);

        try {
			
        	MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(PARAM_WHR1, vo.getCodigoDepartamento());

			
			 List<DepartamentoVO> departamentoVO = getJdbcTemplate() .query(query.toString(), params, new DepartamentoDataMapper());
			 
			 if (!departamentoVO.isEmpty()) {
				 return true;
			 }

 
			 
        } finally {
        	LOGGER.info("isReferenciado(DepartamentoVO vo)"); 
        }
        return false;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public DepartamentoVO obterPorChave(DepartamentoVO vo) {

    	StringBuilder query = new StringBuilder(QuerysDepi.DEPARTAMENTO_OBTERPORCHAVE);

        try {
			
        	MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(PARAM_WHR1, vo.getCodigoDepartamento());
			
			List<DepartamentoVO> departamentoVO = getJdbcTemplate() .query(query.toString(), params, new DepartamentoDataMapper());
			return departamentoVO.get(0);
			 
        } finally {
        	LOGGER.info("obterPorChave(DepartamentoVO vo) "); 
        }
     
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<DepartamentoVO> obterPorFiltro(FiltroUtil filtro) {

    	StringBuilder query = new StringBuilder(QuerysDepi.DEPARTAMENTO_OBTERTODOS);

        try {
			
        	MapSqlParameterSource params = null;
        	
	        /**
	         * Parametros.
	         */
			if (!filtro.getCriterios().isEmpty()) {
				query.append(filtro.getClausaWhereFiltro());
				params = filtro.getMapParamFiltro();
			} 
			
			return getJdbcTemplate() .query(query.toString(), params, new DepartamentoDataMapper());
			 
        } finally {
        	LOGGER.info("obterPorChave(DepartamentoVO vo) "); 
        }
     
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<DepartamentoVO> obterTodos() {

    	StringBuilder query = new StringBuilder(QuerysDepi.DEPARTAMENTO_OBTERTODOS);

        try {
			
        	MapSqlParameterSource params = new MapSqlParameterSource();
			
        	return getJdbcTemplate() .query(query.toString(), params, new DepartamentoDataMapper());
			 
        } finally {
        	LOGGER.info("obterPorTodos() "); 
        }
     
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void excluir(DepartamentoVO vo) {
 
     	StringBuilder query = new StringBuilder(QuerysDepi.DEPARTAMENTO_INATIVAR);
     	
        try {
        	
        	MapSqlParameterSource params = new MapSqlParameterSource();
        	
        	params.addValue("prm1", vo.getCodigoResponsavelUltimaAtualizacao());
        	params.addValue(PARAM_WHR1, vo.getCodigoDepartamento());
  
			getJdbcTemplate().update(query.toString(), params);

        } finally {
        	LOGGER.info("excluir(DepartamentoVO vo)"); 
        } 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void inserir(DepartamentoVO vo) {

    	StringBuilder query = new StringBuilder(QuerysDepi.DEPARTAMENTO_EXISTS);
        
    	try {
    		
    		MapSqlParameterSource params = new MapSqlParameterSource();
    
    		params.addValue(PARAM_WHR1, vo.getSiglaDepartamento());

			 List<DepartamentoVO> departamentoVO = getJdbcTemplate() .query(query.toString(), params, new DepartamentoDataMapper());
			 
        	if (!departamentoVO.isEmpty()) {
           
            	int i = 0;
                while (i <= departamentoVO.size()) {
                    if (departamentoVO.get(i).getIndicadoRegistroAtivo().equals(ConstantesDEPI.INDICADOR_ATIVO)) {
						throw new IntegrationException(concatenarComHifen(
								ConstantesDEPI.ERRO_REGISTRO_JA_CADASTRADO,
								new StringBuilder(" Sigla: ").append(
										vo.getSiglaDepartamento()).toString()));
                    }
                    ++i;
               }
        	}
        	
        	query = new StringBuilder(QuerysDepi.DEPARTAMENTO_INSERT);

    		MapSqlParameterSource paramsIns = new MapSqlParameterSource();
    	    
    		params.addValue("prm1", vo.getSiglaDepartamento());        	
    		params.addValue("prm2", vo.getNomeDepartamento());
    		params.addValue("prm3", vo.getCodigoResponsavelUltimaAtualizacao());

    		getJdbcTemplate().update(query.toString(), paramsIns);
    		
    		
    	} finally {
        	LOGGER.info( "inserir(DepartamentoVO vo)"); 
        }
    }

    /**
     * Obtém registros de Departamento por filtro
     * @param vo CompanhiaSeguradoraVO
     * @return List Retorna um lista de VO de Departamentos filtrados.
     * @Exceção de aplicação
     */
    @Override
    public List<DepartamentoVO> obterPorCompanhiaSeguradora(CompanhiaSeguradoraVO vo) {
    	
    	StringBuilder query = new StringBuilder(QuerysDepi.DEPARTAMENTO_OBTERPORCOMPANHIASEGURADORA);
        
        try {
        	
        	MapSqlParameterSource params = new MapSqlParameterSource();
        	params.addValue(PARAM_WHR1, vo.getCodigoCompanhia());
        	
        	List<DepartamentoVO> departamentoVO = getJdbcTemplate() .query(query.toString(), params, new DepartamentoDataMapper());       
        	
        	return departamentoVO;

        } finally {
        	LOGGER.info( "obterPorCompanhiaSeguradora(CompanhiaSeguradoraVO vo)"); 
        }

    }
    
}