package br.com.bradseg.depi.depositoidentificado.dao;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.DepositoDataMapper;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIBusinessException;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.IgnorarLog;
import br.com.bradseg.depi.depositoidentificado.util.QuerysDepi;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.LogDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoVO;

/**
 * Dao que representa a entidade GrupoAcessoDAO
 */
@Repository
public class DepositoDAOImpl extends JdbcDao implements DepositoDAO {

	private static final String PREFIX_WHERE_PARAM = "whr";
	private static final String PREFIX_PARAM = "prm";

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
     * Método de obter por filtro
     * @param filtro parâmetro depósito com o código do objeto requisitado
     * @param codigoUsuario - código.
     * @return List<DepositoVO>
     */
	@Override
    public List<DepositoVO> obterPorFiltroComRestricaoDeGrupoAcesso(FiltroUtil filtro, Integer codigoUsuario) {

		StringBuilder query = new StringBuilder(QuerysDepi.DEPOSITO_OBTERPORFILTROCOMRESTRICAODEGRUPOACESSO);
    	
		try {

			MapSqlParameterSource params = filtro.getMapParamFiltro();
			
			if (! filtro.getCriterios().isEmpty()) {
				String complemento = filtro.getClausulasParciais(" AND ", true);
				query.append(complemento);
			}
			
			prepararQuery(params, PREFIX_WHERE_PARAM, codigoUsuario);
			
    		List<DepositoVO> depositosVo = getJdbcTemplate().query(query.toString(), params, new DepositoDataMapper());
    		
    		return depositosVo;
            
        } finally {
        	LOGGER.info("obterPorFiltroComRestricaoDeGrupoAcesso(CriterioFiltroUtil filtro, BigDecimal codigoUsuario)"); 
        }
        
    }

    @Override
    public void inserir(DepositoVO vo) {
        throw new IntegrationException("O método inserir não foi implementado.");
    }
    
    private void prepararQuery(MapSqlParameterSource params, String prefix, Object... dados) {
    	int paramIdx = 0;
    	for (Object dado : dados) {
			String paramName = new StringBuilder(prefix).append(++paramIdx).toString();
			params.addValue(paramName, dado);
		}
    }

    @Override
    public void inserir(DepositoVO vo, ParametroDepositoVO param) {
    	
    	try {
    		
        	MapSqlParameterSource params = new MapSqlParameterSource();
        	
        	prepararQuery(params, PREFIX_PARAM,
        			0,
        			vo.getCia().getCodigoCompanhia(),
        			vo.getDepartamento().getCodigoDepartamento(),
        			vo.getMotivoDeposito().getCodigoMotivoDeposito(),
        			vo.getBanco().getCdBancoExterno(),
        			vo.getCodigoAgencia(),
        			BaseUtil.setNullQuandoOpcional(vo.getRamo(), param.getCodigoRamo()),
        			vo.getContaCorrente(),
        			BaseUtil.setNullQuandoOpcional(vo.getSucursal(), param.getCodigoSucursal()),
        			BaseUtil.setNullQuandoOpcional(vo.getItem(), param.getCodigoItem()),
        			BaseUtil.setNullQuandoOpcional(vo.getProtocolo(), param.getCodigoProtocolo()),
        			BaseUtil.setNullQuandoOpcional(vo.getTipoDocumento(), param.getCodigoTipo()),
        			BaseUtil.setNullQuandoOpcional(vo.getApolice(), param.getCodigoApolice()),
        			vo.getPessoaDepositante(), // CPSSOA_DEPST
        			BaseUtil.setNullQuandoOpcional(vo.getDossie(), param.getCodigoDossie()), // CPROCS_JURID
        			BaseUtil.setNullQuandoOpcional(vo.getBloqueto(), param.getCodigoBloqueto()), // NBLETO_COBR
        			BaseUtil.setNullQuandoOpcional(vo.getEndosso(), param.getCodigoEndosso()), // NENDSS
        			BaseUtil.setNullQuandoOpcional(vo.getParcela(), param.getCodigoParcela()), // NPCELA_PRMIO
        			vo.getTipoGrupoRecebimento(), // CTPO_GRP_RECEB
        			vo.getDtVencimentoDeposito(), // DVCTO_DEP_IDTFD
        			vo.getVlrDepositoRegistrado(), // VDEP_IDTFD_ORIGN
        			vo.getCodigoResponsavelUltimaAtualizacao(), // CUSUAR_RESP_ATULZ
        			vo.getObservacaoDeposito(), // ROBS_DEP_IDTFD
        			vo.getCodigoSituacaoDeposito() // CSIT_DEP_IDTFD                                             
        	);
            		
            GeneratedKeyHolder key = new GeneratedKeyHolder();
            
            int count = getJdbcTemplate().update(QuerysDepi.DEPOSITO_INSERT, params, key);
            
            if (count != 1) {
            	throw new DEPIBusinessException(ConstantesDEPI.Geral.ERRO_INCLUSAO);
            }
            
            vo.setCodigoDepositoIdentificado(key.getKey().intValue());
            vo.setCodigoDigitoDeposito((int) vo.getDv());
            inserirDV(vo);

        } catch (SQLException e) {
        	throw new DEPIIntegrationException(e);
        }
    }

    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.dao.DepositoDAO#inserirDV(br.com.bradseg.depi.depositoidentificado.vo.DepositoVO)
     */
    @Override
    public void inserirDV(DepositoVO vo)  {

    	StringBuilder query = new StringBuilder(QuerysDepi.DEPOSITO_INSERIRDV);

    	MapSqlParameterSource params = new MapSqlParameterSource();
    	prepararQuery(params, PREFIX_PARAM, 
    			vo.getCodigoDigitoDeposito(), // CDIG_DEP_IDTFD
    			vo.getCodigoDepositoIdentificado()); // CDEP_IDTFD
    	
    	int count = getJdbcTemplate().update(query.toString(), params);
    	
    	if (count != 1) {
    		throw new DEPIIntegrationException(ConstantesDEPI.Geral.ERRO_INCLUSAO);
    	}
    }

    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.dao.DepositoDAO#atualizar(br.com.bradseg.depi.depositoidentificado.vo.DepositoVO, br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoVO)
     */
    @Override
    public void atualizar(DepositoVO vo, ParametroDepositoVO param) {

    	StringBuilder query = new StringBuilder(QuerysDepi.DEPOSITO_UPDATE);
    	
        try {
        	
        	MapSqlParameterSource params = new MapSqlParameterSource();
        	prepararQuery(params, PREFIX_PARAM, 
        			vo.getCia().getCodigoCompanhia(), // CCIA_SEGDR
        			vo.getSituacaoArquivoTransferencia(), // CSIT_DEP_ARQ_TRNSF
        			vo.getDepartamento().getCodigoDepartamento(), // CDEPTO_DEP_IDTFD
        			vo.getMotivoDeposito().getCodigoMotivoDeposito(), // CMOTVO_DEP_IDTFD
        			vo.getBanco().getCdBancoExterno(), // CBCO
        			vo.getCodigoAgencia(), // CAG_BCRIA
        			BaseUtil.setNullQuandoOpcional(vo.getRamo(), param.getCodigoRamo()), // CRAMO_SEGUR
        			vo.getContaCorrente(), // CCTA_CORR
        			BaseUtil.setNullQuandoOpcional(vo.getSucursal(), param.getCodigoSucursal()), // CSUCUR_EMISR
        			BaseUtil.setNullQuandoOpcional(vo.getItem(), param.getCodigoItem()), // NITEM_APOLC
        			BaseUtil.setNullQuandoOpcional(vo.getProtocolo(), param.getCodigoProtocolo()), // NPROT
        			BaseUtil.setNullQuandoOpcional(vo.getTipoDocumento(), param.getCodigoTipo()), // CTPO_DOCTO
        			BaseUtil.setNullQuandoOpcional(vo.getApolice(), param.getCodigoApolice()), // NAPOLC
        			vo.getPessoaDepositante(), // CPSSOA_DEPST
        			BaseUtil.setNullQuandoOpcional(vo.getDossie(), param.getCodigoDossie()), // CPROCS_JURID
        			BaseUtil.setNullQuandoOpcional(vo.getBloqueto(), param.getCodigoBloqueto()), // NBLETO_COBR
        			BaseUtil.setNullQuandoOpcional(vo.getEndosso(), param.getCodigoEndosso()), // NENDSS
        			BaseUtil.setNullQuandoOpcional(vo.getParcela(), param.getCodigoParcela()), // NPCELA_PRMIO
        			vo.getTipoGrupoRecebimento(), // CTPO_GRP_RECEB
        			vo.getObservacaoDeposito(), // ROBS_DEP_IDTFD
        			vo.getDtVencimentoDeposito(), // DVCTO_DEP_IDTFD
        			vo.getVlrDepositoRegistrado(), // VDEP_IDTFD_ORIGN
        			vo.getCodigoResponsavelUltimaAtualizacao(), // CUSUAR_RESP_ATULZ
        			vo.getCodigoDepositoIdentificado() // CDEP_IDTFD
        			);

            int count = getJdbcTemplate().update(query.toString(), params);

            if (count != 1) {
            	throw new DEPIIntegrationException(ConstantesDEPI.Geral.ERRO_ALTERACAO);
            }

        } catch (SQLException e) {
        	throw new DEPIIntegrationException(e);
        }
    }

    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.dao.DepositoDAO#prorrogar(br.com.bradseg.depi.depositoidentificado.vo.DepositoVO)
     */
    @Override
    public void prorrogar(DepositoVO vo) {

    	MapSqlParameterSource params = new MapSqlParameterSource();
    	
    	prepararQuery(params, PREFIX_PARAM, 
    			vo.getSituacaoArquivoTransferencia(),
    			vo.getDataProrrogacao(),
    			vo.getCodigoResponsavelUltimaAtualizacao());
    	
    	prepararQuery(params, PREFIX_WHERE_PARAM, 
    			vo.getCodigoDepositoIdentificado());  
    	
    	int count = getJdbcTemplate().update(QuerysDepi.DEPOSITO_PRORROGAR, params);
    	
    	if (count != 1) {
    		throw new DEPIIntegrationException(ConstantesDEPI.Geral.ERRO_ALTERACAO);
    	}
    }

    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.dao.DepositoDAO#cancelar(br.com.bradseg.depi.depositoidentificado.vo.DepositoVO)
     */
    @Override
    public void cancelar(DepositoVO deposito)  {
    	
    	MapSqlParameterSource params = new MapSqlParameterSource();
    	
    	prepararQuery(params, PREFIX_PARAM, 
    			deposito.getSituacaoArquivoTransferencia(),
    			deposito.getCodigoResponsavelUltimaAtualizacao());
    	
    	prepararQuery(params, PREFIX_WHERE_PARAM, 
    			deposito.getCodigoDepositoIdentificado());
    	
    	Integer count = getJdbcTemplate().update(QuerysDepi.DEPOSITO_CANCELAR, params);
    	
    	if (count == 0) {
    		throw new DEPIIntegrationException(ConstantesDEPI.Geral.ERRO_ALTERACAO);
    	}

    }

    @Override
    public void excluir(DepositoVO vo)  {

    	MapSqlParameterSource params = new MapSqlParameterSource();
    	
    	prepararQuery(params, PREFIX_WHERE_PARAM, 
    			vo.getCodigoResponsavelUltimaAtualizacao(),
    			vo.getCodigoDepositoIdentificado());
    	
    	Integer count = getJdbcTemplate().update(QuerysDepi.DEPOSITO_INATIVAR, params);
    	
    	if (count == 0) {
    		throw new DEPIIntegrationException(ConstantesDEPI.Geral.ERRO_EXCLUSAO);
    	}
    }

    @Override
    public List<LogDepositoVO> registrarLogs(DepositoVO oldObj, DepositoVO newObj, String actionName) {

    	try {
            List<LogDepositoVO> logs = gerarLogs(oldObj, newObj, actionName);
            for (LogDepositoVO log : logs) {
            	log.setIdLog(registrarLog(log));
            }

        	LOGGER.info("registrarLogs(DepositoVO oldObj, DepositoVO newObj, String actionName)"); 
            return logs;
        } finally {
         	LOGGER.info("registrarLogs(DepositoVO oldObj, DepositoVO newObj, String actionName)"); 
        }

    }

	@Override
	public long updateLog(DepositoVO dep) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		prepararQuery(params, PREFIX_PARAM, 
				dep.getCodigoResponsavelUltimaAtualizacao());
		
		prepararQuery(params, PREFIX_WHERE_PARAM, 
				dep.getCodigoDepositoIdentificado());
		
		GeneratedKeyHolder key = new GeneratedKeyHolder();
		
		getJdbcTemplate().update(QuerysDepi.DEPOSITO_UPDATELOGS, params, key);
		
		return key.getKey().longValue();
	}
    
    private long registrarLog(LogDepositoVO log) {    	
    	MapSqlParameterSource params = new MapSqlParameterSource();
    	
		prepararQuery(params, PREFIX_PARAM,
				log.getCodigo(), 
				log.getFieldName(),
                log.getValorAntigo(), 
                log.getValorNovo(), 
                log.getUsuarioAntigo(), 
                log.getUsuarioNovo());
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(QuerysDepi.DEPOSITO_LOGS_INSERT, params, keyHolder);
		
		return keyHolder.getKey().longValue();
	}

	/**
	 * @param oldObj -
	 *            DepositoVO
	 * @param newObj -
	 *            DepositoVO
	 * @param actionName -
	 *            String
	 * @return Generated Key - long
	 */
    private List<LogDepositoVO> gerarLogs(DepositoVO oldObj, DepositoVO newObj, String actionName) {
        List<LogDepositoVO> logs = new ArrayList<LogDepositoVO>();
        
        List<LogDepositoVO> logsDeposito = new ArrayList<LogDepositoVO>();
        
        extractModifiedValues(oldObj, newObj, actionName, logs);
        
        for (LogDepositoVO log : logs) {
        	LogDepositoVO logDep = log;
        	logDep.setCodigo(newObj.getCodigoDepositoIdentificado());
        	if (log.getFieldName().length() > 20) {
        		log.setFieldName(log.getFieldName().substring(1, 20));
        	}
        	log.setUsuarioAntigo(newObj.getCodigoResponsavelUltimaAtualizacao());
        	log.setUsuarioNovo(oldObj.getCodigoResponsavelUltimaAtualizacao());
        	logsDeposito.add(logDep);
        }
        
        return logsDeposito; 
    }
    
    private void extractModifiedValues(final Object oldObj, final Object newObj, final String actionName,
            final List<LogDepositoVO> logs) {
    	
    	Set<String> propertyNamesToAvoid = new HashSet<String>();
    	propertyNamesToAvoid.add("codigoResponsavelUltimaAtualizacao");
    	
    	Field[] fields = oldObj.getClass().getDeclaredFields();
    	for (Field field : fields) {
			if (field.getAnnotation(IgnorarLog.class) != null) {
				propertyNamesToAvoid.add(field.getName());
			}
		}
		Map<String, Object[]> difProps = BaseUtil.compararObjects(oldObj, newObj, propertyNamesToAvoid, 1L);
		
		for (String propName : difProps.keySet()) {
			LogDepositoVO log = new LogDepositoVO();
			log.setFieldName(propName);
			
			Object[] valores = difProps.get(propName);
			if (valores[0] == null) {
				log.setValorAntigo("");
			}
			else {
				log.setValorAntigo(String.valueOf(valores[0]));
			}
			
			if (valores[1] == null) {
				log.setValorNovo("");
			}
			else {
				log.setValorNovo(String.valueOf(valores[1]));
			}
			
			logs.add(log);
		}
    }
    
    @Override
	public DepositoVO obterDepositoPorChave(DepositoVO deposito)  {

    	MapSqlParameterSource params = new MapSqlParameterSource();
    	
    	prepararQuery(params, PREFIX_WHERE_PARAM, deposito.getCodigoDepositoIdentificado());
    	
		try {
			DepositoVO vo = getJdbcTemplate().queryForObject(
					QuerysDepi.DEPOSITO_OBTERPORCHAVE, params,
					new DepositoDataMapper());
			return vo;
		} catch (EmptyResultDataAccessException e) {
			throw new DEPIIntegrationException(ConstantesDEPI.ERRO_REGISTRO_INEXISTENTE);
		}
	}

    @Override
	public DepositoVO obterPorMotivo(MotivoDepositoVO vo)  {
/*    	beginMethod(LOGGER, "obterPorMotivo(MotivoDepositoVO vo)");
        DataSource ds;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = this.getSQL("deposito.obterPorMotivo");
        DepositoVO deposito = new DepositoVO();

        try {

            ds = getDAO().getDataSource();
            conn = ds.getConnection();

            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, vo.getCodigoMotivoDeposito());

            rs = pstm.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    deposito.setCodigoDepositoIdentificado(rs.getLong(1));
                    deposito.setSituacaoArquivoTransferencia(rs.getInt(1));
                }
            }
            return deposito;
        } catch (DAOException pe) {
        	LOGGER.error(pe);
            tratarExcecao(pe);
        } catch (SQLException pe) {
        	LOGGER.error(pe);
            tratarExcecao(pe);
        } finally {
        	closeResultSet(rs);
        	closeStatement(pstm);
        	closeConnection(conn);
        	endMethod(LOGGER, "obterPorMotivo(MotivoDepositoVO vo)");
        }
        return null;*/
    	 return null;
    }

    @Override
	public DepositoVO obterPorDepartamento(DepartamentoVO vo)  {
    	/* beginMethod(LOGGER, "obterPorDepartamento(DepartamentoVO vo)");
        DataSource ds;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = this.getSQL("deposito.obterPorDepartamento");
        DepositoVO deposito = new DepositoVO();

        try {

            ds = getDAO().getDataSource();
            conn = ds.getConnection();

            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, vo.getCodigoDepartamento());

            rs = pstm.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    deposito.setCodigoDepositoIdentificado(rs.getLong(1));
                    deposito.setSituacaoArquivoTransferencia(rs.getInt(1));
                }
            }
            return deposito;
        } catch (DAOException pe) {
        	LOGGER.error(pe);
            tratarExcecao(pe);
        } catch (SQLException pe) {
        	LOGGER.error(pe);
            tratarExcecao(pe);
        } finally {
          	closeResultSet(rs);
        	closeStatement(pstm);
        	closeConnection(conn);
        	endMethod(LOGGER, "obterPorDepartamento(DepartamentoVO vo)");
        }
        return null; */
    	return null;
    }

    /**
     * Método de obter dep�sito por conta corrente
     * @param vo - DepartamentoVO vo
     * @throws IntegrationException - trata erro de neg�cio
     * @return DepositoVO
     */
    @Override
	public DepositoVO obterPorContaCorrente(ContaCorrenteAutorizadaVO vo)  {
		return null;
   /* 	beginMethod(LOGGER, "obterPorContaCorrente(ContaCorrenteAutorizadaVO vo)");
        DataSource ds;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = this.getSQL("deposito.obterPorContaCorrente");
        DepositoVO deposito = new DepositoVO();

        try {

            ds = getDAO().getDataSource();
            conn = ds.getConnection();

            pstm = conn.prepareStatement(sql);

            pstm.setLong(1, vo.getContaCorrente());
            pstm.setInt(2, vo.getBanco().getCdBancoExterno());
            pstm.setInt(3, vo.getCodigoAgencia());

            rs = pstm.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    deposito.setCodigoDepositoIdentificado(rs.getLong(1));
                    deposito.setSituacaoArquivoTransferencia(rs.getInt(1));
                }
            }
            return deposito;
        } catch (DAOException pe) {
        	LOGGER.error(pe);
            tratarExcecao(pe);
        } catch (SQLException pe) {
        	LOGGER.error(pe);
            tratarExcecao(pe);
        } finally {
          	closeResultSet(rs);
        	closeStatement(pstm);
        	closeConnection(conn);
        	endMethod(LOGGER, "obterPorContaCorrente(ContaCorrenteAutorizadaVO vo)");
        }
        return null; */
    }

    @Override
	public long obterSituacaoDeposito(DepositoVO deposito)  {
		return 0;
    /*	beginMethod(LOGGER, "obterSituacaoDeposito(DepositoVO deposito)");
        DataSource ds;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Long situacaoDeposito = null;
        String sql = this.getSQL("deposito.obterSituacao");

        try {
            ds = getDAO().getDataSource();
            conn = ds.getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.setLong(1, deposito.getCodigoDepositoIdentificado());

            rs = pstm.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    situacaoDeposito = rs.getLong(1);
                }
                
            }
        } catch (DAOException pe) {
        	LOGGER.error(pe);
            tratarExcecao(pe);
        } catch (SQLException pe) {
        	LOGGER.error(pe);
            tratarExcecao(pe);
        } finally {
        	closeResultSet(rs);
        	closeStatement(pstm);
        	closeConnection(conn);
        	endMethod(LOGGER, "obterSituacaoDeposito(DepositoVO deposito)");
        }
        return situacaoDeposito; */
    }

    @Override
	public boolean verificarLancamentoDeposito(DepositoVO deposito){
		return false;
/*    	beginMethod(LOGGER, "verificarLancamentoDeposito(DepositoVO deposito)");
        DataSource ds;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Boolean lancamento = false;
        String sql = this.getSQL("deposito.verificarLancamento");

        try {
            ds = getDAO().getDataSource();
            conn = ds.getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.setLong(1, deposito.getCodigoDepositoIdentificado());

            rs = pstm.executeQuery();

            if (rs.next()) {
                lancamento = true;
             }else{
                lancamento = false;
             }
        } catch (DAOException pe) {
        	LOGGER.error(pe);
            tratarExcecao(pe);
        } catch (SQLException pe) {
        	LOGGER.error(pe);
            tratarExcecao(pe);
        } finally {
        	closeResultSet(rs);
        	closeStatement(pstm);
        	closeConnection(conn);
        	endMethod(LOGGER, "verificarLancamentoDeposito(DepositoVO deposito)");
        }
        return lancamento; */

    }

    @Override
	public boolean verificarEnvioArquivoTransferencia(DepositoVO deposito)  {
		return false;
/*    	beginMethod(LOGGER, "verificarEnvioArquivoTransferencia(DepositoVO deposito)");
        DataSource ds;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Boolean arquivo = null;
        String sql = this.getSQL("deposito.verificarArquivoTransferencia");

        try {
            ds = getDAO().getDataSource();
            conn = ds.getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.setLong(1, deposito.getCodigoDepositoIdentificado());

            rs = pstm.executeQuery();

            if (rs.next()) {
                arquivo = true;
            }else{
                arquivo = false;
                }
        } catch (DAOException pe) {
        	LOGGER.error(pe);
            tratarExcecao(pe);
        } catch (SQLException pe) {
        	LOGGER.error(pe);
            tratarExcecao(pe);
        } finally {
        	closeResultSet(rs);
        	closeStatement(pstm);
        	closeConnection(conn);
        	endMethod(LOGGER, "verificarEnvioArquivoTransferencia(DepositoVO deposito)");
        }
        return arquivo; */

   
    }

}
