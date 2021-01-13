package br.com.bradseg.depi.depositoidentificado.dao;

import java.sql.SQLException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.QuerysDepi;
import br.com.bradseg.depi.depositoidentificado.vo.MovimentoDepositoVO;


import br.com.bradseg.bsad.framework.core.exception.BusinessException;
import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;

/**
 * A(O) Class ParametroDepositoDAOImpl.
 */
@Repository
public class MovimentoDepositoDAOImpl extends JdbcDao implements MovimentoDepositoDAO {

	/** A Constante LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MovimentoDepositoDAOImpl.class);
	
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
     * {@inheritDoc}
     */
    @Override
    public void inserir(MovimentoDepositoVO vo) {

        try {
        	
			MapSqlParameterSource params = new MapSqlParameterSource();

			/**
             * Atentar que são regras para incluir na tabela e não regras de negocio.
             */
			
            params.addValue("prm1", vo.getCodigoMovimento());
            params.addValue("prm2", vo.getIndicacaoAcao());


            boolean b = !"D".equals(vo.getIndicacaoAcao());
            params.addValue("prm3", BaseUtil.setNullQuandoOpcional(vo.getNroCheque(), b));

            if (b) {
                b = !"T".equals(vo.getIndicacaoAcao());
            }

            params.addValue("prm4", BaseUtil.setNullQuandoOpcional(vo.getBancoMovimento(), b));
            params.addValue("prm5", BaseUtil.setNullQuandoOpcional(vo.getAgenciaMovimento(), b));
            params.addValue("prm6", vo.getObservacao());
            params.addValue("prm7", BaseUtil.setNullQuandoOpcional(vo.getContaMovimento(), b));
            params.addValue("prm8", vo.getCodigoResponsavelUltimaAtualizacao());
            
            
            Integer num = getJdbcTemplate().update(QuerysDepi.DEPOSITO_MOVIMENTO_INSERT, params);
            
            if (num == 0) {
                throw new BusinessException("A atualização não afetou nenhum registro.");
            }

        } catch (SQLException e) {
        	LOGGER.error("inserir(MovimentoDepositoVO vo)", e); 
		}  finally {
        	LOGGER.info("inserir(MovimentoDepositoVO vo)"); 
        }
    }

    /**
     * Inserir Movimento Existe regras de inclusão. {@inheritDoc}
     */
    @Override
    public void alterar(MovimentoDepositoVO vo)  {

    	
        try {
        	
			MapSqlParameterSource params = new MapSqlParameterSource();
			
            /**
             * Atentar que são regras para incluir na tabela e não regras de negocio.
             */
            params.addValue("prm1", vo.getIndicacaoAcao());

            boolean b = !"D".equals(vo.getIndicacaoAcao());
            params.addValue("prm2", BaseUtil.setNullQuandoOpcional(vo.getNroCheque(), b));

            if (b) {
                b = !"T".equals(vo.getIndicacaoAcao());
            }
            
            params.addValue("prm3", BaseUtil.setNullQuandoOpcional(vo.getBancoMovimento(), b));
            params.addValue("prm4", BaseUtil.setNullQuandoOpcional(vo.getAgenciaMovimento(), b));
            params.addValue("prm5", vo.getObservacao());
            params.addValue("prm6", BaseUtil.setNullQuandoOpcional(vo.getContaMovimento(), b));
            params.addValue("prm7", vo.getCodigoResponsavelUltimaAtualizacao());
            params.addValue("whr1", vo.getCodigoMovimento());

            Integer num = getJdbcTemplate().update(QuerysDepi.DEPOSITO_MOVIMENTO_UPDATE, params);
            
            if (num == 0) {
                throw new BusinessException("A atualização não afetou nenhum registro.");
            }

        } catch (SQLException e) {
        	LOGGER.error("alterar(MovimentoDepositoVO vo)", e); 
		} finally {
        	LOGGER.info("alterar(MovimentoDepositoVO vo)"); 
        }
    }
}
