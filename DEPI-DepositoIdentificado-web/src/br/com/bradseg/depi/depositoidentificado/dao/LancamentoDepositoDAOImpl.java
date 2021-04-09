package br.com.bradseg.depi.depositoidentificado.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.LancamentoDepositoDataMapper;
import br.com.bradseg.depi.depositoidentificado.util.QueriesDepi;
import br.com.bradseg.depi.depositoidentificado.vo.DepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.LancamentoDepositoVO;

/**
 * Dao que representa a entidade MOTVO_DEP_IDTFD
 * @author Globality
 */
@Repository
public class LancamentoDepositoDAOImpl extends JdbcDao implements LancamentoDepositoDAO {

//	/** A Constante LOGGER. */
//	private static final Logger LOGGER = LoggerFactory.getLogger(LancamentoDepositoDAOImpl.class);
	
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
	
	@Override
	public LancamentoDepositoVO obterPorDeposito(DepositoVO vo) {
		
		try {
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("whr1", vo.getCodigoDepositoIdentificado());
			
			return getJdbcTemplate().queryForObject(
					QueriesDepi.DEPOSITO_LANCAMENTO_OBTERPORCHAVE, params,
					new LancamentoDepositoDataMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
