package br.com.bradseg.depi.depositoidentificado.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
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

	/** A Constante LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DepositoDAOImpl.class);

    private static final String MSG_NENHUM_REGISTRO_AFETADO = "A atualização não afetou nenhum registro.";
	
	/** A(O) data source. */
	@Autowired
	private DataSource dataSource;
	
	/** A(O) map sql parameter source. */
	private MapSqlParameterSource mapSqlParameterSource;
	

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
    public List<DepositoVO> obterPorFiltroComRestricaoDeGrupoAcesso(FiltroUtil filtro, BigDecimal codigoUsuario) {
		return null;
/*    	beginMethod(LOGGER, "obterPorFiltroComRestricaoDeGrupoAcesso(CriterioFiltroUtil filtro, BigDecimal codigoUsuario)"); 
        DataSource ds;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = this.getSQL("deposito.obterPorFiltroComRestricaoDeGrupoAcesso");
        DepositoVO depositoVO = null;
        List<DepositoVO> listaDepositoVO = new ArrayList<DepositoVO>();
        try {
            ds = getDAO().getDataSource();
            conn = ds.getConnection();
            if (filtro == null) {
                filtro = new CriterioFiltroUtil();

            }
            if (filtro.getCriterios() == null) {
                filtro.setCriterios(new ArrayList<CriterioFiltroUtil>());
            }

            sql = sql.replace("{0}", filtro.getCriterioWithOperatorAnd());

            pstm = conn.prepareStatement(sql);

            pstm.setBigDecimal(1, codigoUsuario);

            rs = pstm.executeQuery();

            int i = 0;
            
            while (rs.next()) {
            	i++;
                depositoVO = new DepositoVO();
                
                depositoVO.setCodigoDepositoIdentificado(Long.valueOf(rs.getString("CDEP_IDTFD")));
                depositoVO.setCodigoDigitodeposito(Integer.valueOf(rs.getString("CDIG_DEP_IDTFD")));
                depositoVO.setCia(new CompanhiaSeguradoraVO(Integer.valueOf(rs.getString("CINTRN_CIA_SEGDR"))));
                depositoVO.setDepartamento(new DepartamentoVO(Integer.valueOf(rs.getString("CDEPTO_DEP_IDTFD")), null, rs.getString("CSGL_DEPTO_DEP")));
                depositoVO.setMotivo(new MotivoDepositoVO(Integer.valueOf(rs.getString("CMOTVO_DEP_IDTFD")), rs.getString("RMOTVO_DEP_IDTFD"), null));
                depositoVO.setBanco(new BancoVO(Integer.valueOf(rs.getString("CBCO"))));
                depositoVO.setAgencia(Integer.valueOf(rs.getString("CAG_BCRIA")));
                depositoVO.setContaCorrente(Long.valueOf(rs.getString("CCTA_CORR")));
                depositoVO.setPessoaDepositante(Long.valueOf(rs.getString("CPSSOA_DEPST")));
                depositoVO.setDtVencimentoDeposito(rs.getTimestamp("DVCTO_DEP_IDTFD"));
                depositoVO.setDataInclusao(rs.getTimestamp("DHORA_INCL_REG"));
                depositoVO.setSituacaoArquivoTransferencia(Integer.valueOf(rs.getString("CSIT_DEP_ARQ_TRNSF")));
                depositoVO.setDataHoraAtualizacao(rs.getTimestamp("DHORA_ULT_ATULZ")); 
                depositoVO.setCodigoResponsavelUltimaAtualizacao(new BigDecimal(rs.getString("CUSUAR_RESP_ATULZ")));
                depositoVO.setCodigoIndicativoAtivo(rs.getString("CIND_REG_ATIVO"));
                depositoVO.setCodigoSituacaoDeposito(rs.getInt("CSIT_DEP_IDTFD"));
                
                listaDepositoVO.add(depositoVO);
                
                if(i > 499) {
                	return listaDepositoVO;
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
        	endMethod(LOGGER, "obterPorFiltroComRestricaoDeGrupoAcesso(CriterioFiltroUtil filtro, BigDecimal codigoUsuario)"); 
        }
        return listaDepositoVO; */
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void inserir(DepositoVO vo) {
        throw new IntegrationException("O método inserir não foi implementado.");
    }

    /**
     * Método reponsável pela inclusão de um registro de Depósito
     * @param vo - DepositoVO.
     * @param param - ParametroDepositoVO.
     */
    public void inserir(DepositoVO vo, ParametroDepositoVO param) {
/*    	beginMethod(LOGGER, "inserir(DepositoVO vo, ParametroDepositoVO param"); 
        Connection cnn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            cnn = getDAO().getDataSource().getConnection();
            stmt = cnn.prepareStatement(this.getSQL("deposito.insert"), Statement.RETURN_GENERATED_KEYS);
            StatementUtil su = StatementUtil.getNewInstance(stmt);
            su.setStatementValue(00);
            su.setStatementValue(vo.getCia().getCodigoCompanhia()); // CINTRN_CIA_SEGDR
            su.setStatementValue(vo.getDepartamento().getCodigoDepartamento()); // CDEPTO_DEP_IDTFD
            su.setStatementValue(vo.getMotivo().getCodigoMotivoDeposito()); // CMOTVO_DEP_IDTFD
            su.setStatementValue(vo.getBanco().getCdBancoExterno()); // CBCO
            su.setStatementValue(vo.getAgencia()); // CAG_BCRIA
            su.setStatementValue(BaseUtil.setNullQuandoOpcional(vo.getRamo(), param.getCodigoRamo())); // CRAMO_SEGUR
            su.setStatementValue(vo.getContaCorrente()); // CCTA_CORR
            su.setStatementValue(BaseUtil.setNullQuandoOpcional(vo.getSucursal(), param.getCodigoSucursal())); // CSUCUR_EMISR
            su.setStatementValue(BaseUtil.setNullQuandoOpcional(vo.getItem(), param.getCodigoItem())); // NITEM_APOLC
            su.setStatementValue(BaseUtil.setNullQuandoOpcional(vo.getProtocolo(), param.getCodigoProtocolo())); // NPROT
            su.setStatementValue(BaseUtil.setNullQuandoOpcional(vo.getTipoDocumento(), param.getCodigoTipo())); // CTPO_DOCTO
            su.setStatementValue(BaseUtil.setNullQuandoOpcional(vo.getApolice(), param.getCodigoApolice())); // NAPOLC
            su.setStatementValue(vo.getPessoaDepositante()); // CPSSOA_DEPST
            su.setStatementValue(BaseUtil.setNullQuandoOpcional(vo.getDossie(), param.getCodigoDossie())); // CPROCS_JURID
            su.setStatementValue(BaseUtil.setNullQuandoOpcional(vo.getBloqueto(), param.getCodigoBloqueto())); // NBLETO_COBR
            su.setStatementValue(BaseUtil.setNullQuandoOpcional(vo.getEndosso(), param.getCodigoEndosso())); // NENDSS
            su.setStatementValue(BaseUtil.setNullQuandoOpcional(vo.getParcela(), param.getCodigoParcela())); // NPCELA_PRMIO

            su.setStatementValue(vo.getTipoGrupoRecebimento()); // CTPO_GRP_RECEB
            // su.setStatementValue(vo.getObservacaoDeposito()); // ROBS_DEP_IDTFD

            su.setStatementValue(vo.getDtVencimentoDeposito()); // DVCTO_DEP_IDTFD

            su.setStatementValue(vo.getVlrDepositoRegistrado()); // VDEP_IDTFD_ORIGN
            su.setStatementValue(vo.getCodigoResponsavelUltimaAtualizacao()); // CUSUAR_RESP_ATULZ
            
            su.setStatementValue(vo.getObservacaoDeposito()); // ROBS_DEP_IDTFD
            su.setStatementValue(vo.getCodigoSituacaoDeposito()); // CSIT_DEP_IDTFD

            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();

            if (rs != null && rs.next()) {
                vo.setCodigoDepositoIdentificado(rs.getBigDecimal(1).longValue());
                vo.setCodigoDigitodeposito((int) vo.getDv());
                inserirDV(vo);
            }

        } catch (DAOException e) {
        	LOGGER.error(e);
            tratarExcecao(e);
        } catch (SQLException e) {
        	LOGGER.error(e);
            tratarExcecao(e);
        } finally {
        	closeResultSet(rs);
        	closeStatement(stmt);
        	closeConnection(cnn);
        	endMethod(LOGGER, "inserir(DepositoVO vo, ParametroDepositoVO param"); 
        } */
    }

    /**
     * Método reponsável pela inserção de um DV para um depósito inserido
     * @param vo - DepositoVO.
     */
    public void inserirDV(DepositoVO vo)  {
    /*	beginMethod(LOGGER, "inserirDV(DepositoVO vo)"); 
        Connection cnn = null;
        PreparedStatement stmt = null;

        try {
            cnn = getDAO().getDataSource().getConnection();
            stmt = cnn.prepareStatement(this.getSQL("deposito.inserirDv"));
            StatementUtil su = StatementUtil.getNewInstance(stmt);
            su.setStatementValue(vo.getCodigoDigitodeposito()); // CDIG_DEP_IDTFD
            su.setStatementValue(vo.getCodigoDepositoIdentificado()); // CDEP_IDTFD

            stmt.executeUpdate();

            if (stmt.executeUpdate() == 0) {
                throw new DEPIIntegrationException(MSG_NENHUM_REGISTRO_AFETADO);
            }

        } catch (DAOException e) {
        	LOGGER.error(e);
            tratarExcecao(e);

        } catch (SQLException e) {
        	LOGGER.error(e);
            tratarExcecao(e);
        } finally {
        	closeStatement(stmt);
        	closeConnection(cnn);
        	endMethod(LOGGER, "inserirDV(DepositoVO vo)"); 
        } */
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void alterar(DepositoVO vo)  {
        throw new IntegrationException("O método inserir não foi implementado.");
    }

    /**
     * Método reponsável pela atualização normal de um registro de Depósito
     * @param vo - DepositoVO.
     * @param param - ParametroDepositoVO.
     */
    public void atualizar(DepositoVO vo, ParametroDepositoVO param) {
      	/*beginMethod(LOGGER, "atualizar(DepositoVO vo, ParametroDepositoVO param))");
        Connection cnn = null;
        PreparedStatement stmt = null;

        try {
            cnn = getDAO().getDataSource().getConnection();
            stmt = cnn.prepareStatement(this.getSQL("deposito.update"));
            StatementUtil su = StatementUtil.getNewInstance(stmt);
            su.setStatementValue(vo.getCia().getCodigoCompanhia()); // CCIA_SEGDR
            su.setStatementValue(vo.getSituacaoArquivoTransferencia()); // CSIT_DEP_ARQ_TRNSF 
            su.setStatementValue(vo.getDepartamento().getCodigoDepartamento()); // CDEPTO_DEP_IDTFD
            su.setStatementValue(vo.getMotivo().getCodigoMotivoDeposito()); // CMOTVO_DEP_IDTFD
            su.setStatementValue(vo.getBanco().getCdBancoExterno()); // CBCO
            su.setStatementValue(vo.getAgencia()); // CAG_BCRIA
            su.setStatementValue(BaseUtil.setNullQuandoOpcional(vo.getRamo(), param.getCodigoRamo())); // CRAMO_SEGUR
            su.setStatementValue(vo.getContaCorrente()); // CCTA_CORR
            su.setStatementValue(BaseUtil.setNullQuandoOpcional(vo.getSucursal(), param.getCodigoSucursal())); // CSUCUR_EMISR
            su.setStatementValue(BaseUtil.setNullQuandoOpcional(vo.getItem(), param.getCodigoItem())); // NITEM_APOLC
            su.setStatementValue(BaseUtil.setNullQuandoOpcional(vo.getProtocolo(), param.getCodigoProtocolo())); // NPROT
            su.setStatementValue(BaseUtil.setNullQuandoOpcional(vo.getTipoDocumento(), param.getCodigoTipo())); // CTPO_DOCTO
            su.setStatementValue(BaseUtil.setNullQuandoOpcional(vo.getApolice(), param.getCodigoApolice())); // NAPOLC
            su.setStatementValue(vo.getPessoaDepositante()); // CPSSOA_DEPST
            su.setStatementValue(BaseUtil.setNullQuandoOpcional(vo.getDossie(), param.getCodigoDossie())); // CPROCS_JURID
            su.setStatementValue(BaseUtil.setNullQuandoOpcional(vo.getBloqueto(), param.getCodigoBloqueto())); // NBLETO_COBR
            su.setStatementValue(BaseUtil.setNullQuandoOpcional(vo.getEndosso(), param.getCodigoEndosso())); // NENDSS
            su.setStatementValue(BaseUtil.setNullQuandoOpcional(vo.getParcela(), param.getCodigoParcela())); // NPCELA_PRMIO
            su.setStatementValue(vo.getTipoGrupoRecebimento()); // CTPO_GRP_RECEB
            su.setStatementValue(vo.getObservacaoDeposito()); // ROBS_DEP_IDTFD
            su.setStatementValue(vo.getDtVencimentoDeposito()); // DVCTO_DEP_IDTFD
            su.setStatementValue(vo.getVlrDepositoRegistrado()); // VDEP_IDTFD_ORIGN
            su.setStatementValue(vo.getCodigoResponsavelUltimaAtualizacao()); // CUSUAR_RESP_ATULZ
            su.setStatementValue(vo.getCodigoDepositoIdentificado()); // CDEP_IDTFD

            if (stmt.executeUpdate() == 0) {
                throw new DEPIIntegrationException(MSG_NENHUM_REGISTRO_AFETADO);
            }

        } catch (DAOException e) {
        	LOGGER.error(e);
            tratarExcecao(e);
        } catch (SQLException e) {
        	LOGGER.error(e);
            tratarExcecao(e);
        } finally {
        	closeStatement(stmt);
        	closeConnection(cnn);
        	endMethod(LOGGER, "atualizar(DepositoVO vo, ParametroDepositoVO param)"); 
        } */
    }

    /**
     * Prorrogar Depósito
     * @param vo - DepositoVO.
     */
    public void prorrogar(DepositoVO vo) {
    	/*beginMethod(LOGGER, "prorrogar(DepositoVO vo)");
    	Connection cnn = null;
        PreparedStatement stmt = null;

        try {
            cnn = getDAO().getDataSource().getConnection();
            stmt = cnn.prepareStatement(this.getSQL("deposito.prorrogar"));
            StatementUtil su = StatementUtil.getNewInstance(stmt);
            su.setStatementValue(vo.getSituacaoArquivoTransferencia());
            su.setStatementValue(vo.getDataProrrogacao());
            su.setStatementValue(vo.getCodigoResponsavelUltimaAtualizacao());
            su.setStatementValue(vo.getCodigoDepositoIdentificado());            

            if (stmt.executeUpdate() == 0) {
                throw new DEPIIntegrationException(MSG_NENHUM_REGISTRO_AFETADO);
            }

        } catch (DAOException e) {
        	LOGGER.error(e);
            tratarExcecao(e);
        } catch (SQLException e) {
        	LOGGER.error(e);
            tratarExcecao(e);
        } finally {
        	closeStatement(stmt);
        	closeConnection(cnn);
        	endMethod(LOGGER, "atualizar(DepositoVO vo, ParametroDepositoVO param)");
        } */
    }

    /**
     * Prorrogar Depósito
     * @param deposito - DepositoVO.
     */
    public void cancelar(DepositoVO deposito)  {
    	/*beginMethod(LOGGER, "cancelar(DepositoVO deposito)");
        Connection cnn = null;
        PreparedStatement stmt = null;

        try {
            cnn = getDAO().getDataSource().getConnection();
            stmt = cnn.prepareStatement(this.getSQL("deposito.cancelar"));
          
            StatementUtil.getNewInstance(stmt).setStatementValue(
                deposito.getSituacaoArquivoTransferencia(), 
                deposito.getCodigoResponsavelUltimaAtualizacao(),
                deposito.getCodigoDepositoIdentificado());

            if (stmt.executeUpdate() == 0) {
                throw new DEPIIntegrationException(MSG_NENHUM_REGISTRO_AFETADO);
            }

        } catch (DAOException e) {
        	LOGGER.error(e);
            tratarExcecao(e);
        } catch (SQLException e) {
        	LOGGER.error(e);
            tratarExcecao(e);
        } finally {
        	closeStatement(stmt);
        	closeConnection(cnn);
        	endMethod(LOGGER, "cancelar(DepositoVO deposito)");
        } */
    }

    /**
     * Exclui de forma lógica um Depósito em situação de trâmite 1, 4, 5 (1- À enviar, 4- Reenvio e 5-Rejeitado).
     * @param vo - DepositoVO.
     */

    @Override
    public void excluir(DepositoVO vo)  {
/*    	beginMethod(LOGGER, "excluir(DepositoVO vo)");
        Connection cnn = null;
        PreparedStatement stmt = null;

        try {
            cnn = getDAO().getDataSource().getConnection();
            stmt = cnn.prepareStatement(this.getSQL("deposito.inativar"));
            StatementUtil.getNewInstance(stmt).setStatementValue(vo.getCodigoResponsavelUltimaAtualizacao(),
                vo.getCodigoDepositoIdentificado());

            if (stmt.executeUpdate() == 0) {
            	throw  new DEPIIntegrationException(ConstantesModel.MSG_CUSTOMIZADA, "A exclusão não afetou nenhum registro.");                
            }

        } catch (DAOException e) {
        	LOGGER.error(e);
            tratarExcecao(e);
        } catch (SQLException e) {
        	LOGGER.error(e);
            tratarExcecao(e);
        } finally {
        	closeStatement(stmt);
        	closeConnection(cnn);
        	endMethod(LOGGER, "excluir(DepositoVO vo)");
        } */
    }

    /**
     * Registrar Log
     * @param oldObj - DepositoVO
     * @param newObj - DepositoVO
     * @param actionName - String
     * @return Generated Key - long
     */
    public List<LogDepositoVO> registrarLogs(DepositoVO oldObj, DepositoVO newObj, String actionName) {
/*    	beginMethod(LOGGER, "registrarLogs(DepositoVO oldObj, DepositoVO newObj, String actionName)");
        try {
            List<LogDepositoVO> logs = gerarLogs(oldObj, newObj, actionName);
            for (LogDepositoVO log : logs) {
                log.setIdLog(registrarLog(log));
            }
        	endMethod(LOGGER, "registrarLogs(DepositoVO oldObj, DepositoVO newObj, String actionName)"); 
            return logs;
        } catch (IllegalArgumentException e) {
        	LOGGER.error(e);
            throw new DEPIIntegrationException(e);
        } catch (ReflectionException e) {
        	LOGGER.error(e);
            throw new DEPIIntegrationException(e);
        } catch (ConverterException e) {
        	LOGGER.error(e);
            throw new DEPIIntegrationException(e);
        } catch (PersistenceException e) {
        	LOGGER.error(e);
            throw new DEPIIntegrationException(e);
        } finally {
         	endMethod(LOGGER, "registrarLogs(DepositoVO oldObj, DepositoVO newObj, String actionName)"); 
        }
*/ 
    	 return null;
    }

    /**
     * Registrar Log
     * @param log - LogDepositoVO
     * @return Generated Key - long
     */
    private long registrarLog(LogDepositoVO log) {
  /*  	beginMethod(LOGGER, "registrarLog(LogDepositoVO log)");
        Connection cnn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        long generatedKey = 0L;
        try {
            cnn = getDAO().getDataSource().getConnection();
            stmt = cnn.prepareStatement(this.getSQL("deposito.logs.insert"), Statement.RETURN_GENERATED_KEYS);
            StatementUtil.getNewInstance(stmt).setStatementValue(log.getCodigo(), log.getFieldName(),
                log.getValorAntigo(), log.getValorNovo(), log.getUsuarioAntigo(), log.getUsuarioNovo());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getLong(1);
            }
        } catch (DAOException e) {
        	LOGGER.error(e);
            tratarExcecao(e);
        } catch (SQLException e) {
        	LOGGER.error(e);
            tratarExcecao(e);
        } finally {
        	closeResultSet(rs);
        	closeStatement(stmt);
        	closeConnection(cnn);
        	endMethod(LOGGER, "registrarLog(LogDepositoVO log)");
        }
        return generatedKey; */
    	 return 0L;
    }    

    /**
	 * Update Log
	 * 
	 * @param dep -DepositoVO
	 * @return Generated Key - long
	 */
	public long updateLog(DepositoVO dep) {
/*    	beginMethod(LOGGER, "updateLog(DepositoVO dep)");
		Connection cnn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		long generatedKey = 0L;
		try {
			cnn = getDAO().getDataSource().getConnection();
			stmt = cnn.prepareStatement(this.getSQL("deposito.updateLogs"),
					Statement.RETURN_GENERATED_KEYS);
			StatementUtil.getNewInstance(stmt).setStatementValue(
					dep.getCodigoResponsavelUltimaAtualizacao(),
					dep.getCodigoDepositoIdentificado());
			stmt.executeUpdate();
			
		} catch (DAOException e) {
			LOGGER.error(e);
			tratarExcecao(e);
		} catch (SQLException e) {
			LOGGER.error(e);
			tratarExcecao(e);
		} finally {
        	closeResultSet(rs);
        	closeStatement(stmt);
        	closeConnection(cnn);
        	endMethod(LOGGER, "updateLog(DepositoVO dep)");
		}
		return generatedKey; */
		 return 0L;
	}

    
    /**
	 * @author Fábio Henrique Registrar Log
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
     * Método de obter depósito por motivo
     * @param vo - MotivoDepositoVO vo
     * @throws DEPIIntegrationException - trata erro de negócio
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
     * Método de obter depósito por departamento
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
     * Método de obter depósito por conta corrente
     * @param vo - DepartamentoVO vo
     * @throws DEPIIntegrationException - trata erro de negócio
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

}
