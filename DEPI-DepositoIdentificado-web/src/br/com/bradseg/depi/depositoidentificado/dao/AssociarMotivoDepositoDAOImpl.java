package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.AssociarMotivoDepositoDataMapper;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIBusinessException;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.QueriesDepi;
import br.com.bradseg.depi.depositoidentificado.vo.AssociarMotivoDepositoVO;

/**
 * Dao que representa a entidade CTA_CORR_MOTVO
 */
@Repository
public class AssociarMotivoDepositoDAOImpl extends JdbcDao implements AssociarMotivoDepositoDAO {

	private static final String PARAM_PRM1 = "prm1";

	private static final String PARAM_PRM2 = "prm2";

	private static final String PARAM_PRM3 = "prm3";

	private static final String PARAM_PRM4 = "prm4";

	private static final String PARAM_PRM5 = "prm5";

	private static final String PARAM_PRM6 = "prm6";

	private static final String PARAM_PRM7 = "prm7";

	private static final String PARAM_WHR1 = "whr1";

	private static final String PARAM_WHR2 = "whr2";

	private static final String PARAM_WHR3 = "whr3";

	private static final String PARAM_WHR4 = "whr4";

	private static final String PARAM_WHR5 = "whr5";

	private static final String PARAM_WHR6 = "whr6";

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
     * validarID
     * @param vo - AssociarMotivoDepositoVO.
     */
    private void validarID(AssociarMotivoDepositoVO vo) {

    	 /**
         * Proteção da rotina.
         */
    	try { 
	        if (vo.getCia() == null || vo.getCia().getCodigoCompanhia() <= 0) {
	            throw new DEPIIntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Cia.");
	        }
	
	        if (vo.getDepartamento() == null || vo.getDepartamento().getCodigoDepartamento() <= 0) {
	            throw new DEPIIntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Departamento.");
	        }
	
	        if (vo.getMotivoDeposito() == null || vo.getMotivoDeposito().getCodigoMotivoDeposito() <= 0) {
	            throw new DEPIIntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Motivo Dep\u00f3sito.");
	        }
	
	        if (vo.getContaCorrente() <= 0) {
	            throw new DEPIIntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Conta Corrente.");
	        }
	
	        if (vo.getCodigoResponsavelUltimaAtualizacao().doubleValue() <= 0) {
	            throw new DEPIIntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "C\u00f3digo Usu\u00e1rio Respons\u00e1vel");
	        }
    	} finally {
    	    LOGGER.info("validarID(AssociarMotivoDepositoVO vo))");
    	}
    }

    /**
     * método para inserir uma associação de motivo
     * @param vo - objeto que será inserido
     */
    @Override
    public void inserir(AssociarMotivoDepositoVO vo) {

    	validarID(vo);

    	try {
    		
            String ativo = queryIsAtivo(vo);
            
            if (ativo != null) {
                if (ConstantesDEPI.INDICADOR_ATIVO.toString().equals(ativo)) {
                	
                    StringBuilder sb = new StringBuilder();
                    sb.append(" Associa\u00E7\u00E3o de Motivo: [Cia: ").append(vo.getCia().getCodigoCompanhia());
                    sb.append("; Departamento: ").append(vo.getDepartamento().getCodigoDepartamento());
                    sb.append("; Motivo: ").append(vo.getMotivoDeposito().getCodigoMotivoDeposito());
                    sb.append(" Banco: ").append(vo.getBanco().getCdBancoExterno());
                    sb.append(", Ag\u00eancia: ").append(vo.getCodigoAgencia());
                    sb.append(", Conta Corrente: ").append(vo.getContaCorrente()).append("]");
                    throw new DEPIBusinessException(ConstantesDEPI.ERRO_REGISTRO_JA_CADASTRADO, sb.toString());
                    
                } else {
                    queryReativar(vo);
                }
            } else {
            	
            	queryInsert(vo);
            }

        } finally {
        	LOGGER.info("inserir(AssociarMotivoDepositoVO vo)");   
        }
    }

	private void queryReativar(AssociarMotivoDepositoVO vo) {
		MapSqlParameterSource paramsReativar = new MapSqlParameterSource();
		paramsReativar.addValue(PARAM_PRM1,vo.getCodigoResponsavelUltimaAtualizacao());
		paramsReativar.addValue(PARAM_WHR1,vo.getCia().getCodigoCompanhia());
		paramsReativar.addValue(PARAM_WHR2,vo.getDepartamento().getCodigoDepartamento());
		paramsReativar.addValue(PARAM_WHR3,vo.getMotivoDeposito().getCodigoMotivoDeposito());
		paramsReativar.addValue(PARAM_WHR4,vo.getBanco().getCdBancoExterno());
		paramsReativar.addValue(PARAM_WHR5,vo.getCodigoAgencia());
		paramsReativar.addValue(PARAM_WHR6,vo.getContaCorrente());
		
		Integer count = getJdbcTemplate().update(QueriesDepi.ASSOCIARMOTIVODEPOSITO_REATIVAR, paramsReativar);

		if (count == 0) {
		    throw new DEPIIntegrationException(ConstantesDEPI.Geral.ERRO_INCLUSAO);
		}
	}

	private void queryInsert(AssociarMotivoDepositoVO vo) {
		MapSqlParameterSource paramsInserir = new MapSqlParameterSource();
		paramsInserir.addValue(PARAM_PRM1,vo.getCia().getCodigoCompanhia());
		paramsInserir.addValue(PARAM_PRM2,vo.getDepartamento().getCodigoDepartamento());
		paramsInserir.addValue(PARAM_PRM3,vo.getMotivoDeposito().getCodigoMotivoDeposito());
		paramsInserir.addValue(PARAM_PRM4,vo.getBanco().getCdBancoExterno());
		paramsInserir.addValue(PARAM_PRM5,vo.getCodigoAgencia());
		paramsInserir.addValue(PARAM_PRM6,vo.getContaCorrente());
		paramsInserir.addValue(PARAM_PRM7,vo.getCodigoResponsavelUltimaAtualizacao());

		Integer count = getJdbcTemplate().update(QueriesDepi.ASSOCIARMOTIVODEPOSITO_INSERT, paramsInserir);

		if (count == 0) {
		    throw new DEPIIntegrationException(ConstantesDEPI.Geral.ERRO_INCLUSAO);
		}
	}

	private String queryIsAtivo(
			AssociarMotivoDepositoVO vo) {
		
		try {
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(PARAM_WHR1,vo.getCia().getCodigoCompanhia());
			params.addValue(PARAM_WHR2,vo.getDepartamento().getCodigoDepartamento());
			params.addValue(PARAM_WHR3,vo.getMotivoDeposito().getCodigoMotivoDeposito());
			params.addValue(PARAM_WHR4,vo.getBanco().getCdBancoExterno());
			params.addValue(PARAM_WHR5,vo.getCodigoAgencia());
			params.addValue(PARAM_WHR6,vo.getContaCorrente());
			

			return getJdbcTemplate().queryForObject(
					QueriesDepi.ASSOCIARMOTIVODEPOSITO_EXISTS, params,
					String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

    /**
     * Método responsável por excluir uma associação de Motivo.
     * @param vo - opbjeto a ser excluído.
     */
    @Override
    public void excluir(AssociarMotivoDepositoVO vo) {

        try {
        	MapSqlParameterSource paramsInativar = new MapSqlParameterSource();

            /**
             * Novos valores
             */
        	paramsInativar.addValue(PARAM_PRM1,vo.getCodigoResponsavelUltimaAtualizacao());

            /**
             * Where
             */
        	paramsInativar.addValue(PARAM_WHR1,vo.getCia().getCodigoCompanhia());
        	paramsInativar.addValue(PARAM_WHR2,vo.getDepartamento().getCodigoDepartamento());
        	paramsInativar.addValue(PARAM_WHR3,vo.getMotivoDeposito().getCodigoMotivoDeposito());
        	paramsInativar.addValue(PARAM_WHR4,vo.getBanco().getCdBancoExterno());
        	paramsInativar.addValue(PARAM_WHR5,vo.getCodigoAgencia());
        	paramsInativar.addValue(PARAM_WHR6,vo.getContaCorrente());

            int count = getJdbcTemplate().update(QueriesDepi.ASSOCIARMOTIVODEPOSITO_INATIVAR, paramsInativar);

            if (count == 0) {
                throw new DEPIIntegrationException(ConstantesDEPI.Geral.ERRO_EXCLUSAO);
            }

        } finally {
        	LOGGER.info("excluir(AssociarMotivoDepositoVO vo)");   
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isReferenciado(AssociarMotivoDepositoVO vo) {

    	StringBuilder query = new StringBuilder(QueriesDepi.ASSOCIARMOTIVODEPOSITO_REFERENCIADO_DEPOSITO);
    	
    	try {
            
    		MapSqlParameterSource params = new MapSqlParameterSource();
    		
    		params.addValue(PARAM_WHR1,vo.getCia().getCodigoCompanhia());
    		params.addValue(PARAM_WHR2,vo.getDepartamento().getCodigoDepartamento());
    		params.addValue(PARAM_WHR3,vo.getMotivoDeposito().getCodigoMotivoDeposito());
    		params.addValue(PARAM_WHR4,vo.getBanco().getCdBancoExterno());
    		params.addValue(PARAM_WHR5,vo.getCodigoAgencia());
    		params.addValue(PARAM_WHR6,vo.getContaCorrente());
    		
        	try {
				Boolean exists = getJdbcTemplate().queryForObject(query.toString(), params, Boolean.class);

				return (exists);
			} catch (EmptyResultDataAccessException e) {
				return false;
			}
            
        } finally {
        	LOGGER.info("isReferenciado(AssociarMotivoDepositoVO vo)");            
        } 	
    }

    /**
     * Método de obter por filtro
     * @param filtro parâmetro depósito com o código do objeto requisitado
     * @param codigoUsuario - BigDecimal.
     * @return List<AssociarMotivoDepositoVO>
     */
	@Override
    public List<AssociarMotivoDepositoVO> obterPorFiltroComRestricaoDeGrupoAcesso(FiltroUtil filtro, int codigoUsuario) {
		
    	StringBuilder query = new StringBuilder(QueriesDepi.ASSOCIARMOTIVODEPOSITO_OBTERPORFILTROCOMRESTRICAODEGRUPOACESSO);
    	
    	List<AssociarMotivoDepositoVO> associarMotivoDeposito = null;

        try {

        	MapSqlParameterSource params = filtro.getMapParamFiltro(); 
        	params.addValue(PARAM_WHR1,codigoUsuario);
        	
			if (!filtro.getCriterios().isEmpty()) {
				String complemento = filtro.getClausulasParciais(" AND ", true);
				query.append(complemento);
				params.addValues(filtro.getMapParamFiltro().getValues());
			}
			
			query.append(QueriesDepi.ASSOCIARMOTIVODEPOSITO_ORDERBY);

    		associarMotivoDeposito = getJdbcTemplate().query(query.toString(), params, new AssociarMotivoDepositoDataMapper());
    		
        } finally {
        	LOGGER.info("obterPorFiltroComRestricaoDeGrupoAcesso(CriterioFiltroUtil filtro, BigDecimal codigoUsuario)");  
        }
        return associarMotivoDeposito;
    }

	/**
     * Método de obter por chave
     * @param vo AssociarMotivoDepositoVO
     * @return List<AssociarMotivoDepositoVO>
     */
	@Override
    public AssociarMotivoDepositoVO obterPorChave(AssociarMotivoDepositoVO vo) {
		
    	StringBuilder query = new StringBuilder(QueriesDepi.ASSOCIARMOTIVODEPOSITO_OBTERPORCHAVE);
    	
    	AssociarMotivoDepositoVO associarMotivoDeposito = null;

        try {

    		MapSqlParameterSource params = new MapSqlParameterSource();

    		params.addValue(PARAM_WHR1, vo.getCodigoAgencia());
    		params.addValue(PARAM_WHR2, vo.getBanco().getCdBancoExterno());
    		params.addValue(PARAM_WHR3,vo.getContaCorrente());
    		params.addValue(PARAM_WHR4,vo.getDepartamento().getCodigoDepartamento());
    		params.addValue(PARAM_WHR5,vo.getCia().getCodigoCompanhia());
    		params.addValue(PARAM_WHR6,vo.getMotivoDeposito().getCodigoMotivoDeposito());
			
    		try {
				associarMotivoDeposito = getJdbcTemplate().queryForObject(query.toString(), params, new AssociarMotivoDepositoDataMapper());
			} catch (EmptyResultDataAccessException e) {
				return null;
			}
    		
        } finally {
        	LOGGER.info("obterPorFiltroComRestricaoDeGrupoAcesso(CriterioFiltroUtil filtro, BigDecimal codigoUsuario)");  
        }
        return associarMotivoDeposito;
    }

}
