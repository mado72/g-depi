package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.core.exception.BusinessException;
import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.DepartamentoDataMapper;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.MotivoDepositoDataMapper;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.ParametroDepositosDataMapper;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.QuerysDepi;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoVO;

	/**
	 * A(O) Class ParametroDepositoDAOImpl.
	 */
	@Repository
	public class ParametroDepositoDAOImpl extends JdbcDao implements ParametroDepositoDAO {

		private static final String PARAM_WHR1 = "whr1";

		private static final String PARAM_WHR2 = "whr2";

		private static final String PARAM_WHR3 = "whr3";

		private static final String PARAM_PRM1 = "prm1";

		private static final String PARAM_PRM2 = "prm2";

		private static final String PARAM_PRM3 = "prm3";

		private static final String PARAM_PRM4 = "prm4";

		private static final String PARAM_PRM5 = "prm5";

		private static final String PARAM_PRM6 = "prm6";

		private static final String PARAM_PRM7 = "prm7";

		private static final String PARAM_PRM8 = "prm8";

		private static final String PARAM_PRM9 = "prm9";

		private static final String PARAM_PRM10 = "prm10";

		private static final String PARAM_PRM11 = "prm11";

		private static final String PARAM_PRM12 = "prm12";

		private static final String PARAM_PRM13 = "prm13";

		private static final String PARAM_PRM14 = "prm14";

		private static final String PARAM_PRM15 = "prm15";

		private static final String PARAM_PRM16 = "prm16";

		private static final String PARAM_PRM17 = "prm17";

		private static final String PARAM_PRM18 = "prm18";

		private static final String PARAM_PRM19 = "prm19";

		/** A Constante LOGGER. */
		private static final Logger LOGGER = LoggerFactory.getLogger(ParametroDepositoDAOImpl.class);
		
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
     * Método de obter por filtro
     * @param filtro parâmetro depósito com o código do objeto requisitado
     * @param codigoUsuario - Double.
     * @return List<ParametroDepositoVO>
     */
	@Override
    public List<ParametroDepositoVO> obterPorFiltroComRestricaoDeGrupoAcesso(FiltroUtil filtro, Integer codigoUsuario) {
    	
    	List<ParametroDepositoVO> parametros = null;
    	
        StringBuilder query = new StringBuilder(QuerysDepi.PARAMETRODEPOSITO_OBTERPORFILTROCOMRESTRICAODEGRUPOACESSO);
        
        StringBuilder sb = new StringBuilder();
        
        try {
			
			MapSqlParameterSource params = new MapSqlParameterSource();
			
            sb.append(" AND DBPROD.ALOC_USUAR_GRP_DEP.CUSUAR_DEP_IDTFD = :codusuario ");
            params.addValue("codusuario", codigoUsuario);

            query.replace(query.indexOf("#"), query.indexOf("#")+1 , sb.toString());
			parametros = getJdbcTemplate().query(query.toString(), params,
					new ParametroDepositosDataMapper(true, true));
            return parametros;
            
        } finally {
            LOGGER.info("obterPorFiltroComRestricaoDeGrupoAcesso(FiltroUtil filtro, Double codigoUsuario)"); 
        }
    }

    /**
     * Método de obter por filtro
     * @param filtro parâmetro depósito com o código do objeto requisitado
     * @return List<ParametroDepositoVO>
     */
    @Override
    public List<ParametroDepositoVO> obterPorFiltro(FiltroUtil filtro) {

    	List<ParametroDepositoVO> parametros = null;
    	
        StringBuilder query = new StringBuilder(QuerysDepi.PARAMETRODEPOSITO_OBTERPORFILTRO);
        
        try {
			
			final MapSqlParameterSource params;
			
			if (filtro != null && ! filtro.getCriterios().isEmpty()) {
				query.append(filtro.getClausulasParciais(" AND ", true));
				params = filtro.getMapParamFiltro();
			}
			else {
				params = null;
			}
			
			query.append(QuerysDepi.PARAMETRODEPOSITO_ORDERBY_DHORA_ULT_ATULZ_DESC);
			
			parametros = getJdbcTemplate().query(query.toString(), params,
					new ParametroDepositosDataMapper(true, true));
            return parametros;
            
        } finally {
            LOGGER.info("obterPorFiltroComRestricaoDeGrupoAcesso(CriterioFiltroUtil filtro, BigDecimal codigoUsuario)"); 
        }
    }

    /**
     * Atualiza um Parametro Depósito.
     * @param parametro - ParametroDepositoVO.
     * @param referenciado - boolean
     * {@inheritDoc}
     */
    @Override
    public void alterar(ParametroDepositoVO parametro , boolean referenciado) {

        try {

			MapSqlParameterSource params = new MapSqlParameterSource();
        	
            /**
             * Verificação de preenchimento de Campo obrigatório.
             */
            if (parametro.getCompanhia().getCodigoCompanhia() <= 0) {
                throw new BusinessException("Código Cia não informado para o DAO");
            }

            /**
             * Verificação de preenchimento de Campo obrigatório.
             */
            if (parametro.getMotivoDeposito().getCodigoMotivoDeposito() <= 0) {
                throw new BusinessException("Código do Motivo não informado para o DAO");
            }

            /**
             * Verificação de preenchimento de Campo obrigatório.
             */
            if (parametro.getDepartamento().getCodigoDepartamento() <= 0) {
                throw new BusinessException("Código Departamento não informado para o DAO");
            }

            if (referenciado) {
                params.addValue(PARAM_PRM1, parametro.getOutrosDocumentosNecessarios());
                params.addValue(PARAM_PRM2, parametro.getMotivoDeposito().getCodigoMotivoDeposito());                
                params.addValue(PARAM_WHR1, parametro.getCompanhia().getCodigoCompanhia());        	
                params.addValue(PARAM_WHR2, parametro.getDepartamento().getCodigoDepartamento());  
                params.addValue(PARAM_WHR3, parametro.getMotivoDeposito().getCodigoMotivoDeposito());  
                
            } else {
               /**
                 * Entre o indicativo (se tem valor), e (ter o valor de fato) eu prefiro (usar o valor para saber se tem
                 * indicativo). FHV
                 */
                if (parametro.getNumeroDiasAposVencimento() <= 0) {
                    params.addValue(PARAM_PRM1, ConstantesDEPI.NAO);
                    params.addValue(PARAM_PRM2, null);
                } else {
                    params.addValue(PARAM_PRM1, ConstantesDEPI.SIM);
                    params.addValue(PARAM_PRM2, parametro.getNumeroDiasAposVencimento());
                }
                params.addValue(PARAM_PRM3, parametro.getCodigoSucursal());
                params.addValue(PARAM_PRM4, parametro.getCodigoApolice());
                params.addValue(PARAM_PRM5, parametro.getCodigoEndosso());
                params.addValue(PARAM_PRM6, parametro.getCodigoItem());
                params.addValue(PARAM_PRM7, parametro.getCodigoBloqueto());
                params.addValue(PARAM_PRM8, ConstantesDEPI.SIM);
                params.addValue(PARAM_PRM9, parametro.getCodigoDossie());
                params.addValue(PARAM_PRM10, parametro.getCodigoProtocolo());
                params.addValue(PARAM_PRM11, parametro.getCodigoTipo());
                params.addValue(PARAM_PRM12, parametro.getCodigoRamo());
                params.addValue(PARAM_PRM13, parametro.getCodigoParcela());
                params.addValue(PARAM_PRM14, parametro.getOutrosDocumentosNecessarios());
                params.addValue(PARAM_PRM15, parametro.getMotivoDeposito().getCodigoMotivoDeposito());

                /**
                 * Where
                 */
                params.addValue(PARAM_WHR1, parametro.getCompanhia().getCodigoCompanhia());
                params.addValue(PARAM_WHR2, parametro.getDepartamento().getCodigoDepartamento());
                params.addValue(PARAM_WHR3, parametro.getMotivoDeposito().getCodigoMotivoDeposito());

            }
            
            if (referenciado) {
    			getJdbcTemplate().update(QuerysDepi.PARAMETRODEPOSITO_UPDATEREFERENCIADO, params);	
            } else {
    			getJdbcTemplate().update(QuerysDepi.PARAMETRODEPOSITO_UPDATENAOREFERENCIADO, params);	
            } 

        } catch(DataAccessException e){
			LOGGER.error(e.getMessage());
		}  finally {
            LOGGER.info("alterar(ParametroDepositoVO parametro, String usuarioAtualizacao , boolean referenciado) "); 
        }
    }

    /**
     * Excluir
     * @param vo - ParametroDepositoVO.
     * {@inheritDoc}
     */
    @Override
    public void excluir(ParametroDepositoVO vo) {

    	try {

			MapSqlParameterSource params = new MapSqlParameterSource();

            params.addValue(PARAM_PRM1, vo.getCodigoResponsavelUltimaAtualizacao());
            
            /**
             * Where
             */
            params.addValue(PARAM_WHR1, vo.getCompanhia().getCodigoCompanhia());
            params.addValue(PARAM_WHR2, vo.getDepartamento().getCodigoDepartamento());
            params.addValue(PARAM_WHR3, vo.getMotivoDeposito().getCodigoMotivoDeposito());

            getJdbcTemplate().update(QuerysDepi.PARAMETRODEPOSITO_INATIVAR, params);	
            
        } catch(DataAccessException e){
			throw new BusinessException("\nA exclus�o falhou!\n");
		} finally {
        	LOGGER.info("excluir(ParametroDepositoVO vo)"); 
        }
    }

    /**
     * isReferenciado. Se a tabela possui dependência.
     * @return Boolean.
     * @param vo - ParametroDepositoVO.
     */
    @Override
    public synchronized Boolean isReferenciado(ParametroDepositoVO vo)  {

        StringBuilder query = new StringBuilder(QuerysDepi.PARAMETRODEPOSITO_ASSOCIACAOMOTIVODEPOSITO_REFERENCIADO);
    	
        try {

        	MapSqlParameterSource params = new MapSqlParameterSource();
            
            params.addValue(PARAM_PRM1, vo.getCompanhia().getCodigoCompanhia());
            params.addValue(PARAM_PRM2, vo.getDepartamento().getCodigoDepartamento());
            params.addValue(PARAM_PRM3, vo.getMotivoDeposito().getCodigoMotivoDeposito());
            
            int retorno =  getJdbcTemplate().queryForInt(query.toString(), params);
            
            if (retorno >= 1) {return true;}

        } finally {

        	LOGGER.info("isReferenciado(ParametroDepositoVO vo)"); 
        }
        return false;
    }

    /**
     * isReferenciado. Se a tabela possui dependência.
     * @return Boolean.
     * @param vo - ParametroDepositoVO.
     */
    @Override
    public synchronized Boolean isReferenciadoDeposito(ParametroDepositoVO vo) {


        StringBuilder query = new StringBuilder(QuerysDepi.PARAMETRODEPOSITO_DEPOSITO_REFERENCIADO);
    	
    	
    	try {

        	MapSqlParameterSource params = new MapSqlParameterSource();
            
            params.addValue(PARAM_PRM1, vo.getCompanhia().getCodigoCompanhia());
            params.addValue(PARAM_PRM2, vo.getDepartamento().getCodigoDepartamento());
            params.addValue(PARAM_PRM3, vo.getMotivoDeposito().getCodigoMotivoDeposito());
            
            int retorno =  getJdbcTemplate().queryForInt(query.toString(), params);
            if (retorno >= 1) {return true;}

    	} catch (EmptyResultDataAccessException e) {
    		LOGGER.info("Não encontrou registros para o parametro: Depto({}, Motivo Dep({}), Cia ({})", 
    				vo.getDepartamento().getCodigoDepartamento(), 
    				vo.getMotivoDeposito().getCodigoMotivoDeposito(),
    				vo.getCompanhia().getCodigoCompanhia());
    		
    		return false;
    		
        } finally {
        	LOGGER.info("isReferenciadoDeposito(ParametroDepositoVO vo)"); 
        }
        return false;
    }

    /**
     * Inserir registro de ParametroDepositoVO
     * @param parametro de ParametroDepositoVO
     */
    @Override
    public void inserir(ParametroDepositoVO parametro) {


    	try {

        	MapSqlParameterSource params = new MapSqlParameterSource();

            /**
             * Verificação de preenchimento de Campo obrigatório.
             */
            if (parametro.getCompanhia().getCodigoCompanhia() <= 0) {
                throw new BusinessException("Código Cia não informado para o DAO");
            }

            /**
             * Verificação de preenchimento de Campo obrigatório.
             */
            if (parametro.getMotivoDeposito().getCodigoMotivoDeposito() <= 0) {
                throw new BusinessException("Código do Motivo não informado para o DAO");
            }

            /**
             * Verificação de preenchimento de Campo obrigatório.
             */
            if (parametro.getDepartamento().getCodigoDepartamento() <= 0) {
                throw new BusinessException("Código Departamento não informado para o DAO");
            }

            //* Verifica se j� existe um parametro cadastrado e ativo com os dados informados
            StringBuilder query = new StringBuilder(QuerysDepi.PARAMETRODEPOSITO_EXISTS);
            
            params.addValue(PARAM_PRM1, parametro.getCompanhia().getCodigoCompanhia());
            params.addValue(PARAM_PRM2, parametro.getDepartamento().getCodigoDepartamento());
            params.addValue(PARAM_PRM3, parametro.getMotivoDeposito().getCodigoMotivoDeposito());

            List<String> retorno =  getJdbcTemplate().queryForList(query.toString(), params, String.class);

            if (retorno.isEmpty()) {
                if (retorno.get(1).equals(ConstantesDEPI.INDICADOR_ATIVO)) { // campo not null.
                	
                    StringBuilder queryDepto = new StringBuilder(QuerysDepi.DEPARTAMENTO_OBTERPORCHAVE);
                	MapSqlParameterSource paramsDepto = new MapSqlParameterSource();
                	paramsDepto.addValue(PARAM_PRM1, parametro.getDepartamento().getCodigoDepartamento());
                	
                	DepartamentoVO dep = (DepartamentoVO) getJdbcTemplate().query(queryDepto.toString(), params, new DepartamentoDataMapper());
                	
                    StringBuilder queryMotDepto = new StringBuilder(QuerysDepi.MOTIVODEPOSITO_OBTERPORCHAVE);
                	MapSqlParameterSource paramsMotDepto = new MapSqlParameterSource();
                	paramsMotDepto.addValue(PARAM_WHR1, parametro.getMotivoDeposito().getCodigoMotivoDeposito());
                	
                	MotivoDepositoVO mot = (MotivoDepositoVO) getJdbcTemplate().query(queryMotDepto.toString(), params, new MotivoDepositoDataMapper());

                	String msg = new StringBuilder("Parâmetros de Depósito: ").append(" já cadastrado para a Cia: ").append(
                                    parametro.getCompanhia().getCodigoCompanhia()).append(", para o Departamento: ").append(
                                    dep.getSiglaDepartamento()).append(" e para o Motivo: ").append(mot.getDescricaoBasica()).toString();
                	
                    throw new BusinessException(msg) ;
                } else {
                	
                    MapSqlParameterSource paramsReativar = new MapSqlParameterSource();

                    /**
                     * Novos valores.
                     */
                    paramsReativar.addValue(PARAM_PRM1, parametro.getCodigoBancoVencimento());
                    
                    if (parametro.getNumeroDiasAposVencimento() <= 0) {
                    	paramsReativar.addValue(PARAM_PRM2, null);
                    } else {
                    	paramsReativar.addValue(PARAM_PRM2, parametro.getNumeroDiasAposVencimento());
                    }
                    paramsReativar.addValue(PARAM_PRM3, parametro.getCodigoSucursal());
                    paramsReativar.addValue(PARAM_PRM4, parametro.getCodigoApolice());
                    paramsReativar.addValue(PARAM_PRM5, parametro.getCodigoEndosso());
                    paramsReativar.addValue(PARAM_PRM6, parametro.getCodigoItem());
                    paramsReativar.addValue(PARAM_PRM7, parametro.getCodigoBloqueto());
                    paramsReativar.addValue(PARAM_PRM8, "S");
                    paramsReativar.addValue(PARAM_PRM9, parametro.getCodigoDossie());
                    paramsReativar.addValue(PARAM_PRM10, parametro.getCodigoProtocolo());
                    paramsReativar.addValue(PARAM_PRM11, parametro.getCodigoTipo());
                    paramsReativar.addValue(PARAM_PRM12, parametro.getCodigoRamo());
                    paramsReativar.addValue(PARAM_PRM13, parametro.getCodigoParcela());
                    paramsReativar.addValue(PARAM_PRM14, parametro.getOutrosDocumentosNecessarios());
                    paramsReativar.addValue(PARAM_PRM15, parametro.getCodigoResponsavelUltimaAtualizacao());
                    
                    /**
                     * Where
                     */
                    paramsReativar.addValue(PARAM_WHR1, parametro.getCompanhia().getCodigoCompanhia());
                    paramsReativar.addValue(PARAM_WHR2, parametro.getDepartamento().getCodigoDepartamento());
                    paramsReativar.addValue(PARAM_WHR3, parametro.getMotivoDeposito().getCodigoMotivoDeposito());
                    
                    if (getJdbcTemplate().update(QuerysDepi.PARAMETRODEPOSITO_REATIVAR, paramsReativar) == 0) {
                        throw new BusinessException("A reativação falhou!");
                    }
                }
            } else {
            	
                MapSqlParameterSource paramsInsert = new MapSqlParameterSource();

                /**
                 * Novos valores.
                 */
				paramsInsert.addValue(PARAM_PRM1, parametro.getCompanhia()
						.getCodigoCompanhia());
				paramsInsert.addValue(PARAM_PRM2,
						parametro.getNumeroDiasAposVencimento());
                paramsInsert.addValue(PARAM_PRM3, parametro.getDepartamento().getCodigoDepartamento());
                paramsInsert.addValue(PARAM_PRM4, parametro.getMotivoDeposito().getCodigoMotivoDeposito());
                paramsInsert.addValue(PARAM_PRM5, parametro.getCodigoBancoVencimento());
                
                if (parametro.getNumeroDiasAposVencimento() <= 0) {
                	paramsInsert.addValue(PARAM_PRM6, null);
                } else {
                	paramsInsert.addValue(PARAM_PRM6, parametro.getNumeroDiasAposVencimento());
                }
                
                paramsInsert.addValue(PARAM_PRM7, parametro.getCodigoSucursal());
                paramsInsert.addValue(PARAM_PRM8, parametro.getCodigoApolice());
                paramsInsert.addValue(PARAM_PRM9, parametro.getCodigoEndosso());
                paramsInsert.addValue(PARAM_PRM10, parametro.getCodigoItem());
                paramsInsert.addValue(PARAM_PRM11, parametro.getCodigoBloqueto());
                paramsInsert.addValue(PARAM_PRM12, parametro.getCodigoCpfCnpj());
                paramsInsert.addValue(PARAM_PRM13, parametro.getCodigoDossie());
                paramsInsert.addValue(PARAM_PRM14, parametro.getCodigoProtocolo());
                paramsInsert.addValue(PARAM_PRM15, parametro.getCodigoTipo());
                paramsInsert.addValue(PARAM_PRM16, parametro.getCodigoRamo());
                paramsInsert.addValue(PARAM_PRM17, parametro.getCodigoParcela());
                paramsInsert.addValue(PARAM_PRM18, parametro.getOutrosDocumentosNecessarios());
                paramsInsert.addValue(PARAM_PRM19, parametro.getMotivoDeposito().getCodigoMotivoDeposito());

                getJdbcTemplate().update(QuerysDepi.PARAMETRODEPOSITO_INSERT, paramsInsert) ;
                }

        } finally {
        	LOGGER.info("inserir(ParametroDepositoVO parametro)"); 
        }
    }
    
    /**
     * Inserir registro de ParametroDepositoVO
     * @param parametro de ParametroDepositoVO
     */
    @Override
    public ParametroDepositoVO obterPorChave(ParametroDepositoVO parametro) {

        //* Verifica se j� existe um parametro cadastrado e ativo com os dados informados
        StringBuilder query = new StringBuilder(QuerysDepi.PARAMETRODEPOSITO_EXISTS);
        
        MapSqlParameterSource params = new MapSqlParameterSource();
        
        params.addValue(PARAM_PRM1, parametro.getCompanhia().getCodigoCompanhia());
        params.addValue(PARAM_PRM2, parametro.getDepartamento().getCodigoDepartamento());
        params.addValue(PARAM_PRM3, parametro.getMotivoDeposito().getCodigoMotivoDeposito());

		try {
			return getJdbcTemplate().queryForObject(query.toString(), params,
					new ParametroDepositosDataMapper(false, false));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

    }
    
    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.dao.ParametroDepositoDAO#associacaoReferenciada(br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO, br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO)
     */
    @Override
    public boolean associacaoReferenciada(CompanhiaSeguradoraVO companhia,
    		DepartamentoVO departamentoVO) {
    	MapSqlParameterSource params = new MapSqlParameterSource();
    	
    	params.addValue(PARAM_WHR1, departamentoVO.getCodigoDepartamento());
    	params.addValue(PARAM_WHR2, companhia.getCodigoCompanhia());
    	
    	try {
			
			getJdbcTemplate().queryForObject(
					QuerysDepi.PARAMETRODEPOSITO_REFERENCIADO_DEPTO_CIA,
					params, Integer.class);
			return true;
			
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
    }

}
