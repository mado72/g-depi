/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao;

import static br.com.bradseg.depi.depositoidentificado.util.BaseUtil.concatenarComHifen;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.core.exception.BusinessException;
import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.MotivoDepositoDataMapper;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIBusinessException;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.Tabelas;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.QueriesDepi;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

/**
 * Dao que representa a entidade MOTVO_DEP_IDTFD
 * @author Globality
 */
@Repository
public class MotivoDepositoDAOImpl extends JdbcDao implements MotivoDepositoDAO {

	private static final String PARAM_PRM1 = "prm1";

	private static final String PARAM_PRM2 = "prm2";

	private static final String PARAM_PRM3 = "prm3";

	private static final String PARAM_PRM4 = "prm4";

	private static final String PARAM_PRM5 = "prm5";

	private static final String PARAM_WHR1 = "whr1";
	
	private static final String PARAM_WHR2 = "whr2";
	
	private static final String PARAM_WHR3 = "whr3";

	/** A Constante LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MovimentoDepositoDAOImpl.class);
	
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
     * Método responsável por inserir um Motivo Depósito.
     * @param vo - Motivo Depósito a ser inserido.
     */
    @Override
    public void inserir(MotivoDepositoVO vo) {

    	
    	try {
    		
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(PARAM_WHR1, vo.getDescricaoBasica().trim());

			List<MotivoDepositoVO> motivoDepto = getJdbcTemplate().query(
					QueriesDepi.MOTIVODEPOSITO_EXISTS, params,
					new MotivoDepositoDataMapper());

			if (! motivoDepto.isEmpty()) {
				
				MotivoDepositoVO motivoDepositoVO = motivoDepto.get(0);
				if (motivoDepositoVO.getIndicadorAtivo().equals("S")) {
					throw new DEPIBusinessException(
							ConstantesDEPI.MotivoDeposito.DESC_BSCO_JA_CADASTRADA,
							vo.getDescricaoBasica());
				}
				vo.setCodigoMotivoDeposito(motivoDepositoVO.getCodigoMotivoDeposito());
				
                queryAtivar(vo);
        					
            } else {

            	queryInsert(vo);
                
            }

        }  finally {
        	LOGGER.info("inserir(MotivoDepositoVO vo)"); 
        }
    }

	/**
     * Método responsável por alterar um Motivo Depósito.
     * @param vo - Motivo Depósito a ser alterado.
     */
    @Override
    public void alterar(MotivoDepositoVO vo){
 
       	try {
        		
			MotivoDepositoVO motivoExistente = obterPorChave(vo); 

            if (motivoExistente.getIndicadorAtivo().equals("N")) {
            	throw new DEPIBusinessException(ConstantesDEPI.MotivoDeposito.STATUSINATIVO, vo.getDescricaoBasica());
            }
            
			queryUpdate(vo);

        }  finally {
        	LOGGER.info("alterar(MotivoDepositoVO vo)"); 
        }
    }

	/**
     * Método responsável por excluir um Motivo Depósito.
     * @param vo - Motivo Depósito a ser excluído.
     */
    @Override
    public void excluir(MotivoDepositoVO vo) {

        try {
        	
			queryInativar(vo);

        } finally {
        	LOGGER.info("excluir(MotivoDepositoVO vo)"); 
        }
    }

	/**
     * Método responsável por excluir lista um Motivo Depósito.
     * @param listvo - Motivo Depósito a ser excluído.
     */
    @Override
    public void excluirLista(List<MotivoDepositoVO> listvo) {

        StringBuilder codsMotivos = new StringBuilder();
        for (MotivoDepositoVO vo : listvo) {
            if (isReferenciado(vo)) {
                if (codsMotivos.length() > 0) {
                    codsMotivos.append("; ");
                }
                MotivoDepositoVO m = obterPorChave(vo);
                codsMotivos.append("Motivo Depósito: ").append(m.getDescricaoBasica());
            } else {
            	excluir(vo);
            }
        }
        if (codsMotivos.length() > 0) {
            throw new IntegrationException(concatenarComHifen(ConstantesDEPI.ERRO_DEPENDENCIA_MODULO, codsMotivos.toString(), "Parâmetros de Depósito"));
        }
        
    }
    
    
    /**
     * isReferenciado retorna se o registro é utilizado nas tabelas como FK. {@inheritDoc}
     */
    @Override
    public synchronized Boolean isReferenciado(MotivoDepositoVO vo){
    	
    	LOGGER.error("Inicio - isReferenciado(MotivoDepositoVO vo)"); 

    	
    	try {
    		
			Integer count = queryCountReferenceParametroDeposito(vo);

            if (count >= 1) { 
            	return true;
            }
            
    	} catch (EmptyResultDataAccessException e) {
   			return false;
        } finally {
        	LOGGER.info("isReferenciado(MotivoDepositoVO vo)"); 
        }
        return false;
    }

    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.dao.MotivoDepositoDAO#obterComRestricaoDeGrupoAcesso(int, int, int, br.com.bradseg.depi.depositoidentificado.model.enumerated.Tabelas)
     */
    @Override
	public List<MotivoDepositoVO> obterComRestricaoDeGrupoAcesso(final int codigoCia, final int codigoDep, final int codigoUsuario, final Tabelas e){
    	
    	LOGGER.error("obterComRestricaoDeGrupoAcesso(final int codigoCia, final int codigoDep,final Double codigoUsuario, final Tabelas e)"); 

		StringBuilder query = new StringBuilder();
    	
    	try {

			MapSqlParameterSource params = new MapSqlParameterSource();   		
            final String msg;

            if (e.equals(Tabelas.PARAMETRO_DEPOSITO)) {
            	query.append(QueriesDepi.MOTIVODEPOSITO_OBTERCOMRESTRICAODEPARAMETRODEPOSITO);
                msg = " um Parâmetro de Depósito ou Grupo de Acesso vinculado ao usuário.";
            } else if (e.equals(Tabelas.CONTA_CORRENTE_MOTIVO_DEPOSITO)) {
            	query.append(QueriesDepi.MOTIVODEPOSITO_OBTERCOMRESTRICAODECONTACORRENTEMOTIVODEPOSITO);
                msg = " uma Associação de Motivo ou Grupo de Acesso vinculado ao usuário";
            } else if (e.equals(Tabelas.DEPOSITO)) {
            	query.append(QueriesDepi.MOTIVODEPOSITO_OBTERCOMRESTRICAODEDEPOSITO);
                msg = " um Depósito ou Grupo de Acesso vinculado ao usuário";
            } else {
                throw new IntegrationException("Enum inválido.");
            }

            /**
             * Parametros.
             */
        	params.addValue(PARAM_WHR1, codigoCia);
            params.addValue(PARAM_WHR2, codigoDep);
            params.addValue(PARAM_WHR3, codigoUsuario);

			List<MotivoDepositoVO> motivoDepto = getJdbcTemplate().query(query.toString(), params, new MotivoDepositoDataMapper());
			
            if (motivoDepto == null || motivoDepto.isEmpty()) {
                throw new DEPIBusinessException(ConstantesDEPI.ERRO_RELACIONAMENTOS_NAO_CADASTRADOS, new String[]{msg});
            }
                
            return motivoDepto;
            
        } finally {
        	LOGGER.info("obterComRestricaoDeGrupoAcesso(final int codigoCia, final int codigoDep, final BigDecimal codigoUsuario, final Tabelas e)"); 
        }
    }
    
    /**
     * Obtém apenas os Motivos por Chave.
     * @param vo - MotivoDepositoVO
     * @return List<MotivoDepositoVO>.
     */
    @Override
    public MotivoDepositoVO obterPorChave(MotivoDepositoVO vo){
    	
    	LOGGER.error("MotivoDepositoVO obterPorChave(MotivoDepositoVO vo)"); 

		try {

			MapSqlParameterSource params = new MapSqlParameterSource();   		

            /**
             * Parametros.
             */
        	// params.addValue("whr1", BigDecimal.valueOf(vo.getCodigoMotivoDeposito()));
			params.addValue(PARAM_WHR1, vo.getCodigoMotivoDeposito());

			List<MotivoDepositoVO> motivoDepto = getJdbcTemplate().query(
					QueriesDepi.MOTIVODEPOSITO_OBTERPORCHAVE, params,
					new MotivoDepositoDataMapper());

			if (motivoDepto == null) {
				throw new DEPIBusinessException(
						ConstantesDEPI.MotivoDeposito.NAOCADASTRADO,
						vo.getDescricaoBasica());
			}
            
            return motivoDepto.get(0);
            
        } finally {
        	LOGGER.info("obterPorChave(MotivoDepositoVO vo)"); 
        }
    }

    /**
     * Obtém apenas os Motivos por Filtro.
     * @param filtro - critérios da consulta.
     * @return List<MotivoDepositoVO>.
     */
	@Override
	public List<MotivoDepositoVO> obterPorFiltro(FiltroUtil filtro) {
		
		StringBuilder query = new StringBuilder();
    	query.append(QueriesDepi.MOTIVODEPOSITO_ALL);
    	
		try {

			MapSqlParameterSource params = null;    		

	        /**
	         * Parametros.
	         */
			if (!filtro.getCriterios().isEmpty()) {
				query.append(filtro.getClausulaWhereFiltro());
				params = filtro.getMapParamFiltro();
			} 
			
        	List<MotivoDepositoVO> motivoDepto = getJdbcTemplate().query(query.toString(), params, new MotivoDepositoDataMapper());
			
            if (motivoDepto == null || motivoDepto.isEmpty() ) {
                throw new DEPIBusinessException(ConstantesDEPI.ERRO_SEMRESULTADO);
            }
                
            return motivoDepto;
            
        } finally {
        	LOGGER.info("obterPorFiltro(FiltroUtil filtro)"); 
        }
	}

	private void queryInsert(MotivoDepositoVO vo) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(PARAM_PRM1, vo.getDescricaoBasica().trim());
		params.addValue(PARAM_PRM2, vo.getDescricaoDetalhada());
		params.addValue(PARAM_PRM3, vo.getCodigoEventoContabil());
		params.addValue(PARAM_PRM4, vo.getCodigoItemContabil());
		params.addValue(PARAM_PRM5, vo.getCodigoResponsavelUltimaAtualizacao());
	
		getJdbcTemplate().update(QueriesDepi.MOTIVODEPOSITO_INSERT, params);
	}

	private void queryUpdate(MotivoDepositoVO vo) {
		MapSqlParameterSource paramsUpd = new MapSqlParameterSource();
	
		paramsUpd.addValue(PARAM_PRM1, vo.getDescricaoBasica().trim());
		paramsUpd.addValue(PARAM_PRM2, vo.getDescricaoDetalhada());
		paramsUpd.addValue(PARAM_PRM3, vo.getCodigoEventoContabil());
		paramsUpd.addValue(PARAM_PRM4, vo.getCodigoItemContabil());
		paramsUpd.addValue(PARAM_PRM5, vo.getCodigoResponsavelUltimaAtualizacao());
		paramsUpd.addValue(PARAM_WHR1, vo.getCodigoMotivoDeposito());
	
		Integer count;
		try {
			count = getJdbcTemplate().update(QueriesDepi.MOTIVODEPOSITO_UPDATE, paramsUpd);
		} catch (DuplicateKeyException e) {
			throw new DEPIBusinessException(e, 
					ConstantesDEPI.MotivoDeposito.DESC_BSCO_JA_CADASTRADA, vo.getDescricaoBasica());
		}
	
		if (count == 0) {
		    throw new DEPIBusinessException(ConstantesDEPI.ERRO_REGISTRO_INEXISTENTE);
		}
	}

	private void queryAtivar(MotivoDepositoVO vo) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
	
		params.addValue(PARAM_PRM1, vo.getDescricaoDetalhada().trim());
		params.addValue(PARAM_PRM2, vo.getCodigoEventoContabil());
		params.addValue(PARAM_PRM3, vo.getCodigoItemContabil());
		params.addValue(PARAM_PRM4, vo.getCodigoResponsavelUltimaAtualizacao());
		params.addValue(PARAM_WHR1, vo.getCodigoMotivoDeposito());
	
		getJdbcTemplate().update(QueriesDepi.MOTIVODEPOSITO_ATIVAR, params);
	}

	private void queryInativar(MotivoDepositoVO vo) {
		MapSqlParameterSource params = new MapSqlParameterSource();
	
		params.addValue(PARAM_PRM1, vo.getCodigoResponsavelUltimaAtualizacao());
		params.addValue(PARAM_WHR1, vo.getCodigoMotivoDeposito());
	
		Integer count = getJdbcTemplate().update(QueriesDepi.MOTIVODEPOSITO_INATIVAR, params);
		
		if (count == 0) {
		    throw new BusinessException(ConstantesDEPI.ERRO_REGISTRO_INEXISTENTE);
		}
	}

	private Integer queryCountReferenceParametroDeposito(MotivoDepositoVO vo) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		/**
		 * Where
		 */
		params.addValue(PARAM_WHR1, vo.getCodigoMotivoDeposito());
		
		Integer count = getJdbcTemplate().queryForInt(
				QueriesDepi.MOTIVODEPOSITO_REFERENCIADO_PARAMETRODEPOSITO,
				params);
		return count;
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.MotivoDepositoDAO#associacaoReferenciada(br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO, br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO)
	 */
	@Override
	public boolean associacaoReferenciada(CompanhiaSeguradoraVO companhia,
			DepartamentoVO departamentoVO) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(PARAM_WHR1, departamentoVO.getCodigoDepartamento());
		params.addValue(PARAM_WHR2, companhia.getCodigoCompanhia());
		
		try {
			int count = getJdbcTemplate().queryForInt(
							QueriesDepi.DEPARTAMENTOCOMPANHIA_REFERENCIADO_PARAMETRODEPOSITO,
							params);
			return count > 0;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
	
}
