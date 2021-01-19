package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;
import br.com.bradseg.bucb.servicos.model.pessoa.vo.ListarPessoaPorFiltroEntradaVO;
import br.com.bradseg.bucb.servicos.model.pessoa.vo.ListarPessoaPorFiltroSaidaVO;
import br.com.bradseg.depi.depositoidentificado.dao.delagate.BUCBBusinessDelegate;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.ManutencoesAnaliticoDataMapper;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.QuerysDepi;
import br.com.bradseg.depi.depositoidentificado.vo.ManutencoesAnaliticoVO;

	/**
	 * A(O) Class RelatorioManutencoesDAOImpl.
	 */
	@Repository
	public class RelatorioManutencoesDAOImpl extends JdbcDao implements RelatorioManutencoesDAO {

		/** A Constante LOGGER. */
		private static final Logger LOGGER = LoggerFactory.getLogger(RelatorioManutencoesDAOImpl.class);
		
		
		/** A(O) data source. */
		@Autowired
		private DataSource dataSource;
		
//		/** A(O) map sql parameter source. */
//		private MapSqlParameterSource mapSqlParameterSource;
		

		/* (non-Javadoc)
		 * @see br.com.bradseg.bsad.framework.core.jdbc.JdbcDao#getDataSource()
		 */
		@Override
		public DataSource getDataSource() {		
			return dataSource;
		}
	
	
    /* Método obterDadosAnalitico
     * @param filtro do relatório
     * @return List<ManutencoesAnaliticoVO>
     */
    public List<ManutencoesAnaliticoVO> obterDadosAnalitico(FiltroUtil filtro) {
    	
    	BUCBBusinessDelegate bucbDelegate = new BUCBBusinessDelegate ();
    	List<ManutencoesAnaliticoVO> manutencoes = null;
    	
        StringBuilder query = new StringBuilder(QuerysDepi.RELATORIOENVIORETORNO_OBTERDADOSANALITICO);
        
        StringBuilder sb = new StringBuilder();

        try {
 
			MapSqlParameterSource params = new MapSqlParameterSource();
			
        	params.addValue("dataInicio", filtro.getDataInicio());
        	params.addValue("dataFim", filtro.getDataFinal());
            
            if (filtro.getCodigoCia() > 0 ) {
                sb.append(" AND DBPROD.DEP_IDTFD.CINTRN_CIA_SEGDR = :codcia ");
    			params.addValue("codcia", filtro.getCodigoCia());
            }
            if (filtro.getCodigoDepartamento() > 0) {
                sb.append(" AND DBPROD.DEP_IDTFD.CDEPTO_DEP_IDTFD = :depto ");
    			params.addValue("depto", filtro.getCodigoDepartamento());
            }
            if (filtro.getCodigoMotivo() > 0 ) {
                sb.append(" AND DBPROD.DEP_IDTFD.CMOTVO_DEP_IDTFD = :motvo ");
    			params.addValue("motvo", filtro.getCodigoMotivo());                
            }

            if (filtro.getApolice() > 0) {
                sb.append(" AND DBPROD.DEP_IDTFD.NAPOLC = :apolice ");
    			params.addValue("apolice", filtro.getApolice());
            }

            if (filtro.getSucursal() > 0) {
                sb.append(" AND DBPROD.DEP_IDTFD.CSUCUR_EMISR = :sucursal ");
    			params.addValue("sucursal", filtro.getSucursal());
            }

            if (filtro.getEndosso() > 0) {
                sb.append(" AND DBPROD.DEP_IDTFD.NENDSS = :endosso ");
    			params.addValue("endosso", filtro.getEndosso());
            }

            if (filtro.getTipoDeposito() > 0) {
                sb.append(" AND DBPROD.DEP_IDTFD.CTPO_DOCTO = :tipoDeposito ");
    			params.addValue("tipoDeposito", filtro.getTipoDeposito());
            }
            if (filtro.getCodigoAutorizador() > 0) {
                sb.append(" AND DBPROD.DEP_IDTFD.CDEP_IDTFD = :codigoAutorizador ");
    			params.addValue("depto", filtro.getCodigoAutorizador());
            }

            if (filtro.getSituacaoManutencao() > 0) {
                sb.append(" AND DBPROD.MOVTO_FINCR_DEP.CTPO_ACAO_MOVTO = :sitManut ");
    			params.addValue("sitManut", filtro.getSituacaoManutencao());
            }

            if (filtro.getValorInicial() > 0 && filtro.getValorFinal() > 0) {
                sb.append(" AND (DBPROD.DEP_IDTFD.VDEP_IDTFD_ORIGN BETWEEN :valInicial AND :valFinal) ");
    			params.addValue("valInicial", filtro.getValorInicial());
    			params.addValue("valFinal", filtro.getValorFinal());
            }

            if (!filtro.getCpfCnpj().isEmpty()) {
                ListarPessoaPorFiltroEntradaVO f = new ListarPessoaPorFiltroEntradaVO();
                f.setCpfCgc(Long.parseLong(filtro.getCpfCnpj()));
                f.setCodigoTipoPesquisa(1);
                f.setDataNascimento(0);
                if (String.valueOf(filtro.getCpfCnpj()).length() > 11) { // � cnpj
                    f.setCodigoTipoPessoa(4);
                } else {
                    f.setCodigoTipoPessoa(3);
                }

               List<?> lista = bucbDelegate.listarPessoaPorFiltro(filtro.getIp(), String.valueOf(filtro.getUsuario()), f);
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

            query.replace(query.indexOf("#"), query.indexOf("#")+1 , sb.toString());
            manutencoes = getJdbcTemplate().query(query.toString(), params, new ManutencoesAnaliticoDataMapper());
			
		} catch(DataAccessException e) {
			LOGGER.error("RelatorioManutencoesDAOImpl - obterDadosAnalitico", e);
		} catch(Exception e) {
			LOGGER.error("RelatorioManutencoesDAOImpl - obterDadosAnalitico", e);
		}
        return manutencoes;
    }
}
