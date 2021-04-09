package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.ParcelaCobrancaDataMapper;
import br.com.bradseg.depi.depositoidentificado.util.QueriesDepi;
import br.com.bradseg.depi.depositoidentificado.vo.ParcelaCobrancaVO;

/**
 * A(O) Class ParametroDepositoDAOImpl.
 */
@Repository
public class ParcelasPendentesDAOImpl extends JdbcDao implements ParcelasPendentesDAO {

	/** A Constante LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ParcelasPendentesDAOImpl.class);
	
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
	 * Método que insere um registro relativo � um deposito.
	 * @param listaParcelas - objeto com os dados populados.
	 */	
	public void inserirDepositoCobranca(List<ParcelaCobrancaVO> listaParcelas) {

		try {
			
			MapSqlParameterSource params = new MapSqlParameterSource();
			
            for(ParcelaCobrancaVO parcelaCobranca : listaParcelas) {
            	
            	params.addValue("prm1",  parcelaCobranca.getDeposito().getCodigoDepositoIdentificado());
            	params.addValue("prm2",  parcelaCobranca.getCodigoParcela());
            	params.addValue("prm3",  parcelaCobranca.getCodigoIdentificadorOrigem());
            	params.addValue("prm4",  parcelaCobranca.getValorParcelaCobrado());
            	params.addValue("prm5",  parcelaCobranca.getValorIofCobrado());
            	params.addValue("prm6",  Double.valueOf(0));
            	params.addValue("prm7",  Double.valueOf(0));
            	params.addValue("prm8",  new Date(parcelaCobranca.getDataVencimento().getTime()));
            	params.addValue("prm9",  parcelaCobranca.getCodigoUsuarioAtualizador());
                
                getJdbcTemplate().update(QueriesDepi.PARCELASPENDENTES_INSERIRDEPOSITOCOBRANCA, params);	
            }
            
		} finally {
			LOGGER.info("inserirDepositoCobranca(List<ParcelaCobrancaVO> listaParcelas)");
		}
	}

	/**
	 * Método que lista os códigos das parcelas associadas.
	 * @param parcelaCobranca - objeto com os par�metros.
	 * @return List<ParcelaCobrancaVO> - lista com os dados de retorno.
	 */	
	public List<ParcelaCobrancaVO> listarParcelasAssociadas(ParcelaCobrancaVO parcelaCobranca){

		// List<ParcelaCobrancaVO> listaParcelas = new ArrayList<ParcelaCobrancaVO>();
		List<ParcelaCobrancaVO> listaParcelas = null;
		
		
        StringBuilder query = new StringBuilder(QueriesDepi.PARCELASPENDENTES_LISTARPARCELASASSOCIADAS);

		try {

			MapSqlParameterSource params = new MapSqlParameterSource();
                
			params.addValue("whr1", parcelaCobranca.getDeposito().getCodigoDepositoIdentificado());
			listaParcelas = getJdbcTemplate().query(query.toString(), params, new ParcelaCobrancaDataMapper());
        
            
		} finally {
			LOGGER.info("listarParcelasAssociadas(ParcelaCobrancaVO parcelaCobranca)");
		}
		
        return listaParcelas; 
	}

	/**
	 * Método que lista os detalhes das parcelas associadas.
	 * @param parcelaCobranca - objeto com os par�metros de consulta.
	 * @return List<ParcelaCobrancaVO> - lista com as parcelas populadas.
	 */	
	public List<ParcelaCobrancaVO> detalharParcelasAssociadas(ParcelaCobrancaVO parcelaCobranca) {

		// List<ParcelaCobrancaVO> listaParcelas = new ArrayList<ParcelaCobrancaVO>();
		List<ParcelaCobrancaVO> listaParcelas = null; 
				
        StringBuilder query = new StringBuilder(QueriesDepi.PARCELASPENDENTES_DETALHARPARCELASASSOCIADAS);

		try {

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("whr1", parcelaCobranca.getDeposito().getCodigoDepositoIdentificado());
			
			listaParcelas = getJdbcTemplate().query(query.toString(), params, new ParcelaCobrancaDataMapper());
                
		} finally {
			LOGGER.info("detalharParcelasAssociadas(ParcelaCobrancaVO parcelaCobranca)");
		}
		
		return listaParcelas;		
	}

	/**
	 * Método que exclui os registros da tabela.
	 * @param parcela - a ser exclu�da.
	 */
	public void excluirRegistro(Long parcela){

		try {
			
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("whr1", parcela);    
            
            getJdbcTemplate().update(QueriesDepi.PARCELASPENDENTES_EXCLUIRPARCELA, params);	
                
            
		} finally {
			LOGGER.info("excluirRegistros(ParcelaCobrancaVO parcela)");
		}
	}
	

}
