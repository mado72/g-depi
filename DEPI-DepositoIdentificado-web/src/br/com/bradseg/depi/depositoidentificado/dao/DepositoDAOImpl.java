package br.com.bradseg.depi.depositoidentificado.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.core.exception.BusinessException;
import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.DepositoDataMapper;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.QuerysDepi;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.LogDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoVO;

/**
 * Dao que representa a entidade GrupoAcessoDAO
 * @author Globality
 */
@Repository
public class DepositoDAOImpl extends JdbcDao implements DepositoDAO {
	
    private static final String MSG_NENHUM_REGISTRO_AFETADO = "A atualiza��o n�o afetou nenhum registro.";

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
     * @param filtro par�metro dep�sito com o código do objeto requisitado
     * @param codigoUsuario - código.
     * @return List<DepositoVO>
     */
	@Override
    public List<DepositoVO> obterPorFiltroComRestricaoDeGrupoAcesso(FiltroUtil filtro, Integer codigoUsuario) {

    	
    	StringBuilder query = new StringBuilder(QuerysDepi.DEPOSITO_OBTERPORFILTROCOMRESTRICAODEGRUPOACESSO);
    	
		try {

			MapSqlParameterSource params = ajustarParametrosQuery(filtro, query); 

    		params.addValue("whr1",codigoUsuario);
			
    		List<DepositoVO> depositosVo = getJdbcTemplate() .query(query.toString(), params, new DepositoDataMapper());
    		
    		return depositosVo;
            
        } finally {
        	LOGGER.info("obterPorFiltroComRestricaoDeGrupoAcesso(CriterioFiltroUtil filtro, BigDecimal codigoUsuario)"); 
        }
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void inserir(DepositoVO vo) {
        throw new IntegrationException("O método inserir n�o foi implementado.");
    }

    /**
     * Método repons�vel pela inclus�o de um registro de Dep�sito
     * @param vo - DepositoVO.
     * @param param - ParametroDepositoVO.
     */
    @Override
    public void inserir(DepositoVO vo, ParametroDepositoVO param) {
    	
    	LOGGER.error("inserir(DepositoVO vo, ParametroDepositoVO param"); 
    	
    	StringBuilder query = new StringBuilder(QuerysDepi.DEPOSITO_INSERT);

    	try {
    		
    		
        	MapSqlParameterSource params = new MapSqlParameterSource();

            /**
             * Novos valores
             */
        	params.addValue("prm1", 00 );
        	params.addValue("prm2", vo.getCia().getCodigoCompanhia() );
        	params.addValue("prm3", vo.getDepartamento().getCodigoDepartamento());
        	params.addValue("prm4", vo.getMotivo().getCodigoMotivoDeposito());
        	params.addValue("prm5", vo.getBanco().getCdBancoExterno());
        	params.addValue("prm6", vo.getAgencia());
        	params.addValue("prm7", BaseUtil.setNullQuandoOpcional(vo.getRamo(), param.getCodigoRamo()) );
        	params.addValue("prm8", vo.getContaCorrente() );
        	params.addValue("prm9", BaseUtil.setNullQuandoOpcional(vo.getSucursal(), param.getCodigoSucursal()) );
        	params.addValue("prm10", BaseUtil.setNullQuandoOpcional(vo.getItem(), param.getCodigoItem())  );
        	params.addValue("prm11", BaseUtil.setNullQuandoOpcional(vo.getProtocolo(), param.getCodigoProtocolo()) );
        	params.addValue("prm12", BaseUtil.setNullQuandoOpcional(vo.getTipoDocumento(), param.getCodigoTipo()) );
        	params.addValue("prm13", BaseUtil.setNullQuandoOpcional(vo.getApolice(), param.getCodigoApolice()) );
        	params.addValue("prm14", vo.getPessoaDepositante()); // CPSSOA_DEPST
        	params.addValue("prm15", BaseUtil.setNullQuandoOpcional(vo.getDossie(), param.getCodigoDossie())); // CPROCS_JURID
        	params.addValue("prm16", BaseUtil.setNullQuandoOpcional(vo.getBloqueto(), param.getCodigoBloqueto())); // NBLETO_COBR
        	params.addValue("prm17", BaseUtil.setNullQuandoOpcional(vo.getEndosso(), param.getCodigoEndosso())); // NENDSS
        	params.addValue("prm18", BaseUtil.setNullQuandoOpcional(vo.getParcela(), param.getCodigoParcela())); // NPCELA_PRMIO
        	params.addValue("prm19", vo.getTipoGrupoRecebimento()); // CTPO_GRP_RECEB
        	params.addValue("prm20", vo.getDtVencimentoDeposito()); // DVCTO_DEP_IDTFD
        	params.addValue("prm21", vo.getVlrDepositoRegistrado()); // VDEP_IDTFD_ORIGN
            params.addValue("prm22", vo.getCodigoResponsavelUltimaAtualizacao()); // CUSUAR_RESP_ATULZ
            params.addValue("prm23", vo.getObservacaoDeposito()); // ROBS_DEP_IDTFD
            params.addValue("prm23", vo.getCodigoSituacaoDeposito()); // CSIT_DEP_IDTFD
            		
            GeneratedKeyHolder key = new GeneratedKeyHolder();
            
            Integer count = getJdbcTemplate().update(query.toString(), params, key);
            
            if (count > 1 ) {
                vo.setCodigoDepositoIdentificado(key.getKey().intValue());
                vo.setCodigoDigitodeposito((int) vo.getDv());
                inserirDV(vo);
            }

        } catch (SQLException e) {
        	LOGGER.error("inserir(DepositoVO vo, ParametroDepositoVO param", e); 
		} finally {
        	LOGGER.error("inserir(DepositoVO vo, ParametroDepositoVO param"); 
        }
    }

    /**
     * Método repons�vel pela inserá�o de um DV para um dep�sito inserido
     * @param vo - DepositoVO.
     */
    @Override
    public void inserirDV(DepositoVO vo)  {

    	StringBuilder query = new StringBuilder(QuerysDepi.DEPOSITO_INSERIRDV);

    	try {
    		
    		
        	MapSqlParameterSource params = new MapSqlParameterSource();

        	params.addValue("prm1", vo.getCodigoDigitodeposito()); // CDIG_DEP_IDTFD
        	params.addValue("prm2", vo.getCodigoDepositoIdentificado()); // CDEP_IDTFD

            Integer count = getJdbcTemplate().update(query.toString(), params);

            if (count == 0) {
                throw new BusinessException(MSG_NENHUM_REGISTRO_AFETADO);
            }

        } finally {
        	LOGGER.info("inserirDV(DepositoVO vo)"); 
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void alterar(DepositoVO vo)  {
        throw new IntegrationException("O método inserir n�o foi implementado.");
    }

    /**
     * Método repons�vel pela atualiza��o normal de um registro de Dep�sito
     * @param vo - DepositoVO.
     * @param param - ParametroDepositoVO.
     */
    @Override
    public void atualizar(DepositoVO vo, ParametroDepositoVO param) {

    	StringBuilder query = new StringBuilder(QuerysDepi.DEPOSITO_UPDATE);

    	
        try {
        	
        	MapSqlParameterSource params = new MapSqlParameterSource();

        	params.addValue("prm1", vo.getCia().getCodigoCompanhia()); // CCIA_SEGDR
        	params.addValue("prm2", vo.getSituacaoArquivoTransferencia()); // CSIT_DEP_ARQ_TRNSF
        	params.addValue("prm3", vo.getDepartamento().getCodigoDepartamento()); // CDEPTO_DEP_IDTFD 
        	params.addValue("prm4", vo.getMotivo().getCodigoMotivoDeposito()); // CMOTVO_DEP_IDTFD
        	params.addValue("prm5", vo.getBanco().getCdBancoExterno()); // CBCO
        	params.addValue("prm6", vo.getAgencia()); // CAG_BCRIA
        	params.addValue("prm7", BaseUtil.setNullQuandoOpcional(vo.getRamo(), param.getCodigoRamo())); // CRAMO_SEGUR
        	params.addValue("prm8", vo.getContaCorrente()); // CCTA_CORR
        	params.addValue("prm9", BaseUtil.setNullQuandoOpcional(vo.getSucursal(), param.getCodigoSucursal())); // CSUCUR_EMISR
        	params.addValue("prm10", BaseUtil.setNullQuandoOpcional(vo.getItem(), param.getCodigoItem())); // NITEM_APOLC
        	params.addValue("prm11", BaseUtil.setNullQuandoOpcional(vo.getProtocolo(), param.getCodigoProtocolo())); // NPROT
        	params.addValue("prm12", BaseUtil.setNullQuandoOpcional(vo.getTipoDocumento(), param.getCodigoTipo())); // CTPO_DOCTO
        	params.addValue("prm13", BaseUtil.setNullQuandoOpcional(vo.getApolice(), param.getCodigoApolice())); // NAPOLC
        	params.addValue("prm14", vo.getPessoaDepositante()); // CPSSOA_DEPST
        	params.addValue("prm15", BaseUtil.setNullQuandoOpcional(vo.getDossie(), param.getCodigoDossie())); // CPROCS_JURID
        	params.addValue("prm16", BaseUtil.setNullQuandoOpcional(vo.getBloqueto(), param.getCodigoBloqueto())); // NBLETO_COBR
        	params.addValue("prm17", BaseUtil.setNullQuandoOpcional(vo.getEndosso(), param.getCodigoEndosso())); // NENDSS
        	params.addValue("prm18", BaseUtil.setNullQuandoOpcional(vo.getParcela(), param.getCodigoParcela())); // NPCELA_PRMIO
        	params.addValue("prm19", vo.getTipoGrupoRecebimento()); // CTPO_GRP_RECEB
        	params.addValue("prm20", vo.getObservacaoDeposito()); // ROBS_DEP_IDTFD
        	params.addValue("prm21", vo.getDtVencimentoDeposito()); // DVCTO_DEP_IDTFD
            params.addValue("prm22", vo.getVlrDepositoRegistrado()); // VDEP_IDTFD_ORIGN
            params.addValue("prm23", vo.getCodigoResponsavelUltimaAtualizacao()); // CUSUAR_RESP_ATULZ
            params.addValue("prm23", vo.getCodigoDepositoIdentificado()); // CDEP_IDTFD

            Integer count = getJdbcTemplate().update(query.toString(), params);

            if (count == 0) {
                throw new BusinessException(MSG_NENHUM_REGISTRO_AFETADO);
            }

        } catch (SQLException e) {
        	LOGGER.error("atualizar(DepositoVO vo, ParametroDepositoVO param)", e); 
		} finally {
        	LOGGER.info("atualizar(DepositoVO vo, ParametroDepositoVO param)"); 
        }
    }

    /**
     * Prorrogar Dep�sito
     * @param vo - DepositoVO.
     */
    @Override
    public void prorrogar(DepositoVO vo) {

    	StringBuilder query = new StringBuilder(QuerysDepi.DEPOSITO_PRORROGAR);

    	
        try {
        	
        	MapSqlParameterSource params = new MapSqlParameterSource();

        	params.addValue("prm1", vo.getSituacaoArquivoTransferencia());
        	params.addValue("prm2", vo.getDataProrrogacao());
        	params.addValue("prm3", vo.getCodigoResponsavelUltimaAtualizacao());
        	params.addValue("prm4", vo.getCodigoDepositoIdentificado());  
            
            Integer count = getJdbcTemplate().update(query.toString(), params);

            if (count == 0) {
                throw new BusinessException(MSG_NENHUM_REGISTRO_AFETADO);
            }

        } finally {
        	LOGGER.info("atualizar(DepositoVO vo, ParametroDepositoVO param)");
        } 
    }

    /**
     * Prorrogar Dep�sito
     * @param deposito - DepositoVO.
     */
    @Override
    public void cancelar(DepositoVO deposito)  {

    	StringBuilder query = new StringBuilder(QuerysDepi.DEPOSITO_CANCELAR);

    	
        try {
        	
        	MapSqlParameterSource params = new MapSqlParameterSource();

        	params.addValue("prm1", deposito.getSituacaoArquivoTransferencia());
        	params.addValue("whr1", deposito.getCodigoResponsavelUltimaAtualizacao());
        	params.addValue("whr2", deposito.getCodigoDepositoIdentificado());
            
            Integer count = getJdbcTemplate().update(query.toString(), params);

            if (count == 0) {
                throw new BusinessException(MSG_NENHUM_REGISTRO_AFETADO);
            }

        } finally {
        	LOGGER.info("cancelar(DepositoVO deposito)");
        } 

    }

    /**
     * Exclui de forma l�gica um Dep�sito em situa��o de tr�mite 1, 4, 5 (1- � enviar, 4- Reenvio e 5-Rejeitado).
     * @param vo - DepositoVO.
     */

    @Override
    public void excluir(DepositoVO vo)  {

    	StringBuilder query = new StringBuilder(QuerysDepi.DEPOSITO_INATIVAR);

        try {

        	
        	MapSqlParameterSource params = new MapSqlParameterSource();

        	params.addValue("whr1", vo.getCodigoResponsavelUltimaAtualizacao());
        	params.addValue("whr2", vo.getCodigoDepositoIdentificado());
            
            Integer count = getJdbcTemplate().update(query.toString(), params);

            if (count == 0) {
            	throw  new BusinessException(ConstantesDEPI.MSG_CUSTOMIZADA + " - A exclus�o n�o afetou nenhum registro.");
            }

        } finally {
        	LOGGER.info("excluir(DepositoVO vo)");
        }
    }

    /**
     * Registrar Log
     * @param oldObj - DepositoVO
     * @param newObj - DepositoVO
     * @param actionName - String
     * @return Generated Key - long
     */
    @Override
    public List<LogDepositoVO> registrarLogs(DepositoVO oldObj, DepositoVO newObj, String actionName) {

    	try {
            List<LogDepositoVO> logs = gerarLogs(oldObj, newObj, actionName);
/* FIXME Código comentado porque estava dando erro de compilação ao acessar métodos da LogDepositoVO
            for (LogDepositoVO log : logs) {
                log.setIdLog(registrarLog(log));
            }
*/            
        	LOGGER.info("registrarLogs(DepositoVO oldObj, DepositoVO newObj, String actionName)"); 
            return logs;
        } finally {
         	LOGGER.info("registrarLogs(DepositoVO oldObj, DepositoVO newObj, String actionName)"); 
        }

    }

    /**
     * Registrar Log
     * @param log - LogDepositoVO
     * @return Generated Key - long
     */
    private long registrarLog(LogDepositoVO log) {
        
    	StringBuilder query = new StringBuilder(QuerysDepi.DEPOSITO_LOGS_INSERT);

    	try {
    		
	        	MapSqlParameterSource params = new MapSqlParameterSource();

/* FIXME Código comentado porque estava dando erro de compilação ao acessar métodos da LogDepositoVO
	
	        	params.addValue("prm1", log.getCodigo() );
	        	params.addValue("prm2", log.getFieldName());
	        	params.addValue("prm3", log.getValorAntigo());
	        	params.addValue("prm4", log.getValorNovo());
	        	params.addValue("prm5", log.getUsuarioAntigo());
	        	params.addValue("prm6", log.getUsuarioNovo());
*/	            		
	            GeneratedKeyHolder key = new GeneratedKeyHolder();
	            
	            Integer count = getJdbcTemplate().update(query.toString(), params, key);
	
	            return key.getKey().longValue();

        } finally {
        	LOGGER.info("registrarLog(LogDepositoVO log)");
        }
    }    

    /**
	 * Update Log
	 * 
	 * @param dep -DepositoVO
	 * @return Generated Key - long
	 */
	public long updateLog(DepositoVO dep) {

    	StringBuilder query = new StringBuilder(QuerysDepi.DEPOSITO_UPDATELOGS);

    	try {
    		
	        	MapSqlParameterSource params = new MapSqlParameterSource();
	
	        	params.addValue("prm1", dep.getCodigoResponsavelUltimaAtualizacao());
	        	params.addValue("prm2", dep.getCodigoDepositoIdentificado());
	        	
	            GeneratedKeyHolder key = new GeneratedKeyHolder();
	            
	            Integer count = getJdbcTemplate().update(query.toString(), params, key);
	
	            return key.getKey().longValue();
			
		} finally {
        	LOGGER.info("updateLog(DepositoVO dep)");
		}
	}

    
    /**
	 * @author F�bio Henrique Registrar Log
	 * @param oldObj -
	 *            DepositoVO
	 * @param newObj -
	 *            DepositoVO
	 * @param actionName -
	 *            String
	 * @return Generated Key - long
	 */
    private List<LogDepositoVO> gerarLogs(DepositoVO oldObj, DepositoVO newObj, String actionName) {
/*    	beginMethod(LOGGER, "gerarLogs(DepositoVO oldObj, DepositoVO newObj, String actionName)");
        List<LogDepositoVO> logs = new ArrayList<LogDepositoVO>();
        List<LogDepositoVO> logsDeposito = new ArrayList<LogDepositoVO>();
        extractModifiedValues(oldObj, newObj, actionName, logs);
        try{
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
        }catch (Exception e) {
        	LOGGER.error(e);
        	tratarExcecao(e);
		}  finally {
    	   endMethod(LOGGER, "gerarLogs(DepositoVO oldObj, DepositoVO newObj, String actionName)");
		}
        return logsDeposito; */
    	 return null;
    }

    /**
     * Método de obter dep�sito por motivo
     * @param vo - MotivoDepositoVO vo
     * @throws IntegrationException - trata erro de neg�cio
     * @return DepositoVO
     */
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

    /**
     * Método de obter dep�sito por departamento
     * @param vo - DepartamentoVO vo
     * @return DepositoVO
     */
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

    /**
     * @param oldObj - Object
     * @param newObj - Object
     * @param actionName - Stromg
     * @param logs - List<LogBase>
     */
    private void extractModifiedValues(final Object oldObj, final Object newObj, final String actionName, final List<LogDepositoVO> logs)  {
/*    	beginMethod(LOGGER, "extractModifiedValues(final Object oldObj, final Object newObj, final String actionName, final List<LogDepositoVO> logs)");
    	try {
	        List<FieldMetadata> fmds = BaseUtil.extractModifiedFields(oldObj, newObj);
	        for (FieldMetadata fmd : fmds) {
	            Field field = ReflectionUtil.getFieldByName(newObj.getClass(), fmd.getAttributeName());
	            if (field.isAnnotationPresent(ConsiderLog.class)) {
	                for (String action : field.getAnnotation(ConsiderLog.class).action()) {
	                    if (actionName.equals(action)) {
	                    	LogDepositoVO log = new LogDepositoVO();
	                        log.setFieldName(fmd.getFieldName());
	
	                        Object oldValue = ReflectionUtil.getValue(oldObj, fmd.getAttributeName());
	                        Object newValue = ReflectionUtil.getValue(newObj, fmd.getAttributeName());
	
	                        if (!ReflectionUtil.isAssignableFromPrimitiveOrWrapperType(field)) {
	                            extractModifiedValues(oldValue, newValue, actionName, logs);
	                        } else {
	                            if (oldValue == null) {
	                                log.setValorAntigo("");
	                            } else {
	                                log.setValorAntigo(oldValue.toString());
	                            }
	                            if (newValue == null) {
	                                log.setValorNovo("");
	                            } else {
	                                log.setValorNovo(newValue.toString());
	                            }
	                            logs.add(log);
	                        }
	                    }
	                }
	            }
	        }
    	} finally {
        	endMethod(LOGGER, "extractModifiedValues(final Object oldObj, final Object newObj, final String actionName, final List<LogDepositoVO> logs)");
    	} */
    }

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

    public DepositoVO obterDepositoPorChave(DepositoVO deposito)  {
		
     	/* beginMethod(LOGGER, "obterDepositoPorChave(DepositoVO deposito)");
        DataSource ds;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = this.getSQL("deposito.obterPorChave");
        DepositoVO depositoVO = null;
        try {
            ds = getDAO().getDataSource();
            conn = ds.getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.setLong(1, deposito.getCodigoDepositoIdentificado());

            rs = pstm.executeQuery();
        
            if(rs.next()){
                depositoVO = new DepositoVO();
                depositoVO.setCodigoDepositoIdentificado(Long.valueOf(rs.getString("CDEP_IDTFD")));
                depositoVO.setCodigoDigitodeposito(Integer.valueOf(rs.getString("CDIG_DEP_IDTFD")));
                depositoVO.setCia(new CompanhiaSeguradoraVO(Integer.valueOf(rs.getString("CINTRN_CIA_SEGDR"))));
                depositoVO.setDepartamento(new DepartamentoVO(Integer.valueOf(rs.getString("CDEPTO_DEP_IDTFD")), rs.getString("IDEPTO_DEP_IDTFD"), rs.getString("CSGL_DEPTO_DEP")));
                depositoVO.setMotivo(new MotivoDepositoVO(Integer.valueOf(rs.getString("CMOTVO_DEP_IDTFD")),rs.getString("RMOTVO_DEP_IDTFD"), rs.getString("RDETLH_MOTVO_DEP")));
                depositoVO.setBanco(new BancoVO(Integer.valueOf(rs.getString("CBCO"))));
                depositoVO.setAgencia(Integer.valueOf(rs.getString("CAG_BCRIA")));
                depositoVO.setContaCorrente(Long.valueOf(rs.getString("CCTA_CORR")));
              
                depositoVO.setPessoaDepositante(Long.valueOf(rs.getString("CPSSOA_DEPST")));
              
                depositoVO.setDtVencimentoDeposito(rs.getTimestamp("DVCTO_DEP_IDTFD"));
                depositoVO.setIndicadorDepositoCancelado(rs.getString("CIND_DEP_CANCD"));
                depositoVO.setIndicadorDepositoProrrogado(rs.getString("CIND_DEP_PRROG"));
                if(rs.getTimestamp("DPRROG_DEP_IDTFD") != null){
                    depositoVO.setDataProrrogacao(rs.getTimestamp("DPRROG_DEP_IDTFD"));
                }
                depositoVO.setVlrDepositoRegistrado(BigDecimal.valueOf(rs.getDouble("VDEP_IDTFD_ORIGN")));
                if(rs.getTimestamp("DCANCT_DEP_IDTFD") != null){
                    depositoVO.setDtCancelamentoDepositoIdentificado(rs.getTimestamp("DCANCT_DEP_IDTFD"));
                }
                depositoVO.setDataInclusao(rs.getTimestamp("DHORA_INCL_REG"));
                depositoVO.setSituacaoArquivoTransferencia(Integer.valueOf(rs.getString("CSIT_DEP_ARQ_TRNSF")));
                depositoVO.setDataHoraAtualizacao(rs.getTimestamp("DHORA_ULT_ATULZ")); 
                depositoVO.setCodigoResponsavelUltimaAtualizacao(new BigDecimal(rs.getString("CUSUAR_RESP_ATULZ")));
                depositoVO.setCodigoIndicativoAtivo(rs.getString("CIND_REG_ATIVO"));
                depositoVO.setObservacaoDeposito(rs.getString("ROBS_DEP_IDTFD"));
                
                depositoVO.setSucursal(rs.getInt("CSUCUR_EMISR"));
                depositoVO.setBloqueto(rs.getLong("NBLETO_COBR"));
                depositoVO.setTipoDocumento(rs.getInt("CTPO_DOCTO"));
                depositoVO.setApolice(rs.getInt("NAPOLC"));
                depositoVO.setProtocolo(rs.getInt("NPROT"));
                depositoVO.setRamo(rs.getString("CRAMO_SEGUR"));
                depositoVO.setEndosso(rs.getLong("NENDSS"));
                depositoVO.setDossie(rs.getString("CPROCS_JURID"));
                depositoVO.setParcela(rs.getLong("NPCELA_PRMIO"));
                depositoVO.setItem(rs.getInt("NITEM_APOLC"));
                
                depositoVO.setCodigoSituacaoDeposito(rs.getInt("CSIT_DEP_IDTFD"));
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
        	endMethod(LOGGER, "obterDepositoPorChave(DepositoVO deposito)");
        }
        return depositoVO; */
    	return null;
    }

	private MapSqlParameterSource ajustarParametrosQuery(FiltroUtil filtro,
			StringBuilder query) {
		/**
		 * Parametros.
		 */
		if (!filtro.getCriterios().isEmpty()) {
			// Solicitação do IC - Bradesco
			final String string = "{0}";
			query.replace(query.indexOf(string), query.indexOf(string) + 3, filtro.getClausulasParciais());
		}
		return filtro.getMapParamFiltro();
	}

}
