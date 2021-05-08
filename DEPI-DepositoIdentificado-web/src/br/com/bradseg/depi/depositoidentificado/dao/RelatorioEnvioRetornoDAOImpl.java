package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;
import br.com.bradseg.bucb.servicos.model.pessoa.vo.ListarPessoaPorFiltroSaidaVO;
import br.com.bradseg.depi.depositoidentificado.dao.delagate.BUCBBusinessDelegate;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.RelatorioEnvioRetornoDataMapper;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.QueriesDepi;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoVO;

/**
 * A(O) Class RelatorioEnvioRetornoDAOImpl.
 */
@Repository
public class RelatorioEnvioRetornoDAOImpl extends JdbcDao implements RelatorioEnvioRetornoDAO {

	/** A Constante LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RelatorioEnvioRetornoDAOImpl.class);
	
	/** A(O) data source. */
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BUCBBusinessDelegate bucbDelegate;
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.bsad.framework.core.jdbc.JdbcDao#getDataSource()
	 */
	@Override
	public DataSource getDataSource() {		
		return dataSource;
	}

	@Override
	public List<RelatorioEnvioRetornoVO> obterDados(FiltroUtil filtro) {

		StringBuilder query = new StringBuilder(QueriesDepi.RELATORIOENVIORETORNO);

		StringBuilder sb = new StringBuilder();

		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("dtInicio", filtro.getDataInicio());
		params.addValue("dtFim", filtro.getDataFinal());

		filtrosOpcionais(filtro, params, sb);

		filtroPessoa(filtro, sb); 

		query.replace(query.indexOf("#"), query.indexOf("#")+1 , sb.toString());
		LOGGER.debug("obterDados: {}", query.toString());

		try {
			return getJdbcTemplate().query(query.toString(), params, new RelatorioEnvioRetornoDataMapper()); 
		} catch (Exception e) {
			throw new DEPIIntegrationException(e);
		}
	}

	private void filtrosOpcionais(FiltroUtil filtro,
			MapSqlParameterSource params, StringBuilder sb) {
		
		if (filtro.getCodigoCia() > 0) {
		    sb.append(" AND DBPROD.DEP_IDTFD.CINTRN_CIA_SEGDR = :codCia ");
		    params.addValue("codCia", filtro.getCodigoCia());
		}
		
		if (filtro.getCodigoDepartamento() > 0) {
		    sb.append(" AND DBPROD.DEP_IDTFD.CDEPTO_DEP_IDTFD = :codDepto ");
		    params.addValue("codDepto", filtro.getCodigoDepartamento());                
		}
	
		if (filtro.getSucursal() > 0) {
		    sb.append(" AND DBPROD.DEP_IDTFD.CSUCUR_EMISR = :sucursal ");
		    params.addValue("sucursal", filtro.getSucursal()); 
		}
		
		if (filtro.getCodigoMotivo() > 0 ) {
		    sb.append(" AND DBPROD.DEP_IDTFD.CMOTVO_DEP_IDTFD = :motivo ");
		    params.addValue("motivo", filtro.getCodigoMotivo()); 
		}
	
		if (filtro.getApolice() > 0) {
		    sb.append(" AND DBPROD.DEP_IDTFD.NAPOLC = :apolice ");
		    params.addValue("apolice", filtro.getApolice());
		}
	
		if (filtro.getEndosso() > 0) {
		    sb.append(" AND DBPROD.DEP_IDTFD.NENDSS = :endosso ");
		    params.addValue("endosso", filtro.getEndosso());
		}
		
		if (filtro.getTipoDeposito() > 0) {
		    sb.append(" AND DBPROD.DEP_IDTFD.CTPO_GRP_RECEB = :tpDeposito ");
		    params.addValue("tpDeposito", filtro.getTipoDeposito());
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
	}

	private void filtroPessoa(FiltroUtil filtro, StringBuilder sb) {
		if (!filtro.getCpfCnpj().isEmpty()) {

			List<?> lista = bucbDelegate.listarPessoaPorFiltro(filtro.getIp(),
					filtro.getUsuario(), filtro.getCpfCnpj());
			
			if (lista.isEmpty()) {
				StringBuilder in = new StringBuilder("(");
				String token = "";
				for (Object obj : lista) {
					if (obj instanceof ListarPessoaPorFiltroSaidaVO) {
						ListarPessoaPorFiltroSaidaVO pessoa = (ListarPessoaPorFiltroSaidaVO) obj;
						in.append(token);
						in.append(pessoa.getCodigoPessoa());
						token = ",";
					}
				}
				in.append(")");
				sb.append(" AND DBPROD.DEP_IDTFD.CPSSOA_DEPST IN ").append(in.toString());
			}
		}
	}

}
