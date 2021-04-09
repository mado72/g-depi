package br.com.bradseg.depi.depositoidentificado.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.RelatorioDadosComplementaresDataMapper;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.QueriesDepi;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioDadosComplementaresVO;

/**
 * A(O) Class RelatorioEnvioRetornoDAOImpl.
 */
@Repository
public class RelatorioDadosComplementaresDAOImpl extends JdbcDao implements RelatorioDadosComplementaresDAO {

	/** A Constante LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RelatorioDadosComplementaresDAOImpl.class);
	
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
	 * Método obterDadosComplementaresAnalitico
	 * @param filtro do relatório
	 * @return List<RelatorioDadosComplementaresVO>
	 */
	@Override
	public List<RelatorioDadosComplementaresVO> obterDadosComplementaresAnalitico(FiltroUtil filtro) throws SQLException {

    	List<RelatorioDadosComplementaresVO> relatorio = null;
    	
        StringBuilder query = new StringBuilder(QueriesDepi.RELATORIODADOSCOMPLEMENTARES_OBTERDADOSCOMPLEMENTARESANALITICO);
        
        StringBuilder sb = new StringBuilder();
        

		
			MapSqlParameterSource params = new MapSqlParameterSource();
        	params.addValue("dtInicioA", filtro.getDataInicio());
        	params.addValue("dtFimA", filtro.getDataFinal());
        	params.addValue("dtInicioB", filtro.getDataInicio());
        	params.addValue("dtFimB", filtro.getDataFinal());

            if (filtro.getCodigoCia() > 0) {
                sb.append(" AND DBPROD.DEP_IDTFD.CINTRN_CIA_SEGDR = :codCia ");
                params.addValue("codCia", filtro.getCodigoCia());
            }
            
            if (filtro.getCodigoDepartamento() > 0) {
                sb.append(" AND DBPROD.DEP_IDTFD.CDEPTO_DEP_IDTFD = :codDepto ");
                params.addValue("codDepto", filtro.getCodigoDepartamento());                
            }
   
            if (filtro.getCodigoMotivo() > 0 ) {
                sb.append(" AND DBPROD.DEP_IDTFD.CMOTVO_DEP_IDTFD = :motivo ");
                params.addValue("motivo", filtro.getCodigoMotivo()); 
            }

            if (filtro.getSituacaoArquivo() > 0) {
                sb.append(" AND DBPROD.DEP_IDTFD.CSIT_DEP_ARQ_TRNSF = :sitArquivo ");
                params.addValue("sitArquivo", filtro.getSituacaoArquivo());
            }
            
            if (filtro.getCodigoAutorizador() > 0) {
                sb.append(" AND DBPROD.DEP_IDTFD.CDEP_IDTFD = :autorizador ");
                params.addValue("autorizador", filtro.getCodigoAutorizador());
            }

            if (filtro.getValorInicial() > 0 && filtro.getValorFinal() > 0) {
                sb.append(" AND (DBPROD.DEP_IDTFD.VDEP_IDTFD_ORIGN BETWEEN :vlrInicio AND :vlrFinal) ");
                params.addValue("vlrInicio", filtro.getValorInicial());
                params.addValue("vlrFinal", filtro.getValorFinal());
            }			
			
            query.replace(query.indexOf("#"), query.indexOf("#")+1 , sb.toString());
            relatorio = getJdbcTemplate().query(query.toString(), params, new RelatorioDadosComplementaresDataMapper());


       
        LOGGER.info("obterDadosAnalitico(FiltroUtil filtro) - termino com sucesso"); 
    	
        return relatorio;
	}
}
