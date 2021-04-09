package br.com.bradseg.depi.depositoidentificado.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.core.exception.BusinessException;
import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.MovimentoDepositoDataMapper;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.QueriesDepi;
import br.com.bradseg.depi.depositoidentificado.vo.MovimentoDepositoVO;

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
	
//	/** A(O) map sql parameter source. */
//	private MapSqlParameterSource mapSqlParameterSource;
	

	/* (non-Javadoc)
	 * @see br.com.bradseg.bsad.framework.core.jdbc.JdbcDao#getDataSource()
	 */
	@Override
	public DataSource getDataSource() {		
		return dataSource;
	}
	
	/**
	 * Obtém um movimento pela sua chave
	 * @param chave Chave do movimento
	 * @return Movimento encontrado ou null
	 */
	@Override
	public MovimentoDepositoVO obterPorChave(MovimentoDepositoVO chave) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		BaseUtil.prepararQuery(params, "whr", chave.getCodigoMovimento());
		
		try {
			MovimentoDepositoVO vo = getJdbcTemplate().queryForObject(
					QueriesDepi.DEPOSITO_MOVIMENTO_OBTERPORCHAVE, params,
					new MovimentoDepositoDataMapper());
			
			return vo;
			
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
     * {@inheritDoc}
     */
    @Override
    public void inserir(MovimentoDepositoVO vo) {

        try {
        	
			MapSqlParameterSource params = new MapSqlParameterSource();

			/*
			 * Atentar que são regras para incluir na tabela e não regras de negocio.
			 */
			String indicacaoAcao = vo.getIndicacaoAcao();
			boolean b = "RDT".contains(indicacaoAcao); // Receita, Devolução e Transferência
			boolean c = b && "D".equals(indicacaoAcao); // Devolução 

			BaseUtil.prepararQuery(params, "prm", 
					vo.getCodigoMovimento(),
					vo.getIndicacaoAcao(), 
					BaseUtil.setNullQuandoOpcional(vo.getNroCheque(), c),
					BaseUtil.setNullQuandoOpcional(vo.getBancoMovimento(), b), 
					BaseUtil.setNullQuandoOpcional(vo.getAgenciaMovimento(), b), 
					vo.getObservacao(), 
					BaseUtil.setNullQuandoOpcional(vo.getContaMovimento(), b), 
					vo.getCodigoResponsavelUltimaAtualizacao());
            
            Integer num = getJdbcTemplate().update(QueriesDepi.DEPOSITO_MOVIMENTO_INSERT, params);
            
            if (num == 0) {
                throw new BusinessException("A atualiza��o não afetou nenhum registro.");
            }

        } catch (SQLException e) {
        	LOGGER.error("inserir(MovimentoDepositoVO vo)", e); 
		}  finally {
        	LOGGER.info("inserir(MovimentoDepositoVO vo)"); 
        }
    }

    /**
     * Inserir Movimento Existe regras de inclus�o. {@inheritDoc}
     */
    @Override
    public void alterar(MovimentoDepositoVO vo)  {

    	
        try {
        	
			MapSqlParameterSource params = new MapSqlParameterSource();
			
			/*
			 * Atentar que são regras para incluir na tabela e não regras de negocio.
			 */
			String indicacaoAcao = vo.getIndicacaoAcao();
			boolean b = !"RDT".contains(indicacaoAcao); // Receita, Devolução e Transferência
			boolean c = !"D".equals(indicacaoAcao); // Devolução 
			
			BaseUtil.prepararQuery(params, "prm", 
					indicacaoAcao, 
					BaseUtil.setNullQuandoOpcional(vo.getNroCheque(), c),
					BaseUtil.setNullQuandoOpcional(vo.getBancoMovimento(), b), 
					BaseUtil.setNullQuandoOpcional(vo.getAgenciaMovimento(), b), 
					vo.getObservacao(), 
					BaseUtil.setNullQuandoOpcional(vo.getContaMovimento(), b), 
					vo.getCodigoResponsavelUltimaAtualizacao());

            params.addValue("whr1", vo.getCodigoMovimento());

            Integer num = getJdbcTemplate().update(QueriesDepi.DEPOSITO_MOVIMENTO_UPDATE, params);
            
            if (num == 0) {
                throw new BusinessException("A atualiza��o não afetou nenhum registro.");
            }

        } catch (SQLException e) {
        	LOGGER.error("alterar(MovimentoDepositoVO vo)", e); 
		} finally {
        	LOGGER.info("alterar(MovimentoDepositoVO vo)"); 
        }
    }
}
