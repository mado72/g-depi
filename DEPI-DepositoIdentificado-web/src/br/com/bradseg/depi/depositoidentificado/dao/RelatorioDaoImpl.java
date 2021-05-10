package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;
import br.com.bradseg.bucb.servicos.model.pessoa.vo.ListarPessoaPorFiltroSaidaVO;
import br.com.bradseg.depi.depositoidentificado.dao.delagate.BUCBBusinessDelegate;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.ManutencoesAnaliticoDataMapper;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.RelatorioDadosComplementaresDataMapper;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.RelatorioEnvioRetornoDataMapper;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.RelatorioExtratoAnaliticoDataMapper;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.RelatorioExtratoSinteticoDataMapper;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.QueriesDepi;
import br.com.bradseg.depi.depositoidentificado.vo.ManutencoesAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioDadosComplementaresVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoSinteticoVO;

/**
 * Classe com métodos de consultas para geração de relatórios
 */
@Repository
public class RelatorioDaoImpl extends JdbcDao implements RelatorioDao {
	/** A Constante LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RelatorioDaoImpl.class);

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
	public List<RelatorioEnvioRetornoVO> obterDadosEnvioRetorno(
			FiltroUtil filtro) {

		StringBuilder sb = new StringBuilder();

		MapSqlParameterSource params = new MapSqlParameterSource();

		filtrosComuns(filtro, sb, params);
		filtrosApoliceEndosso(filtro, sb, params);
		filtroPessoa(filtro, sb); 

		return executarQuery(
				QueriesDepi.RELATORIOENVIORETORNO,
				sb, params, new RelatorioEnvioRetornoDataMapper());
	}

	@Override
	public List<RelatorioExtratoAnaliticoVO> obterDadosExtratoAnalitico(
			FiltroUtil filtro) {

		StringBuilder sb = new StringBuilder();

		MapSqlParameterSource params = new MapSqlParameterSource();

		filtrosComuns(filtro, sb, params);
		filtrosApoliceEndosso(filtro, sb, params);
		filtroPessoa(filtro, sb);

		if (!BaseUtil.isNZB(filtro.getSituacaoArquivo())) {
			sb.append(" AND DBPROD.DEP_IDTFD.CSIT_DEP_ARQ_TRNSF = :situacaoArquivo ");
			params.addValue("situacaoArquivo", filtro.getSituacaoArquivo());
		}

		return executarQuery(
				QueriesDepi.RELATORIO_EXTRATO_ANALITICO,
				sb, params, new RelatorioExtratoAnaliticoDataMapper());
	}

	@Override
	public List<RelatorioExtratoSinteticoVO> obterDadosExtratoSintetico(
			FiltroUtil filtro) {

		StringBuilder sb = new StringBuilder();

		MapSqlParameterSource params = new MapSqlParameterSource();

		filtrosComuns(filtro, sb, params);
		filtrosApoliceEndosso(filtro, sb, params);

		return executarQuery(
				QueriesDepi.RELATORIO_EXTRATO_SINTETICO,
				sb, params, new RelatorioExtratoSinteticoDataMapper());
	}

	@Override
	public List<ManutencoesAnaliticoVO> obterDadosManutencoesAnalitico(
			FiltroUtil filtro) {

		StringBuilder sb = new StringBuilder();

		MapSqlParameterSource params = new MapSqlParameterSource();

		filtrosComuns(filtro, sb, params);
		filtrosApoliceEndosso(filtro, sb, params);
		filtroPessoa(filtro, sb);

		if (!BaseUtil.isNZB(filtro.getSituacaoManutencao())) {
			sb.append(" AND DBPROD.MOVTO_FINCR_DEP.CTPO_ACAO_MOVTO = :tipoAcao ");
			params.addValue("tipoAcao", filtro.getSituacaoManutencao());
		}

		return executarQuery(
				QueriesDepi.RELATORIOENVIORETORNO_OBTERDADOSANALITICO,
				sb, params, new ManutencoesAnaliticoDataMapper());
	}

	@Override
	public List<RelatorioDadosComplementaresVO> obterDadosComplementaresAnalitico(
			FiltroUtil filtro) {

		StringBuilder sb = new StringBuilder();

		MapSqlParameterSource params = new MapSqlParameterSource();

		filtrosComuns(filtro, sb, params);

		return executarQuery(
				QueriesDepi.RELATORIODADOSCOMPLEMENTARES_OBTERDADOSCOMPLEMENTARESANALITICO,
				sb, params, new RelatorioDadosComplementaresDataMapper());
	}

	private <T> List<T> executarQuery(String query, StringBuilder filtros,
			MapSqlParameterSource params, RowMapper<T> mapper) {

		try {

			query = query.replace("#", filtros.toString());

			LOGGER.debug("Executando query {}", query);
			return getJdbcTemplate().query(query, params, mapper);

		} catch (Exception e) {
			LOGGER.error("Erro ao executar consulta", e);
			throw new DEPIIntegrationException(e);
		}
	}

	private void filtrosComuns(FiltroUtil filtro, StringBuilder sb,
			MapSqlParameterSource params) {
		params.addValue("dtInicio", filtro.getDataInicio());
		params.addValue("dtFim", filtro.getDataFinal());

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
		if (filtro.getTipoDeposito() > 0) {
			sb.append(" AND DBPROD.DEP_IDTFD.CTPO_DOCTO = :tipoDep ");
			params.addValue("tipoDep", filtro.getTipoDeposito());
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

	private void filtrosApoliceEndosso(FiltroUtil filtro, StringBuilder sb,
			MapSqlParameterSource params) {
		if (filtro.getApolice() > 0) {
			sb.append(" AND DBPROD.DEP_IDTFD.NAPOLC = :apolice ");
			params.addValue("apolice", filtro.getApolice());
		}
		if (filtro.getEndosso() > 0) {
			sb.append(" AND DBPROD.DEP_IDTFD.NENDSS = :endosso ");
			params.addValue("endosso", filtro.getEndosso());
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
