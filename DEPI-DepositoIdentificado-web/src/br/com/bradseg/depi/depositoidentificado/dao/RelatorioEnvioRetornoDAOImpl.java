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
import br.com.bradseg.bucb.servicos.model.pessoa.vo.ListarPessoaPorFiltroEntradaVO;
import br.com.bradseg.bucb.servicos.model.pessoa.vo.ListarPessoaPorFiltroSaidaVO;
import br.com.bradseg.depi.depositoidentificado.dao.delagate.BUCBBusinessDelegate;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.RelatorioEnvioRetornoAnaliticoDataMapper;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.RelatorioEnvioRetornoSinteticoDataMapper;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.QuerysDepi;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoSinteticoVO;

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
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.bsad.framework.core.jdbc.JdbcDao#getDataSource()
	 */
	@Override
	public DataSource getDataSource() {		
		return dataSource;
	}

    /**
     * Método obterDadosAnalitico
     * @param filtro do relatï¿½rio
     * @return List<ManutencoesAnaliticoVO>
     */
    @Override
	public List<RelatorioEnvioRetornoAnaliticoVO> obterDadosAnalitico(FiltroUtil filtro) throws SQLException{

    	List<RelatorioEnvioRetornoAnaliticoVO> relatorio = null;
    	
    	BUCBBusinessDelegate bucbDelegate = new BUCBBusinessDelegate (); 
    	
        StringBuilder query = new StringBuilder(QuerysDepi.RELATORIOENVIORETORNO_OBTERDADOSANALITICO);
        
        StringBuilder sb = new StringBuilder();
        
        MapSqlParameterSource params = new MapSqlParameterSource();
		
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
		
	    params.addValue("dataInicio",filtro.getDataInicio());
	    params.addValue("dataFim", filtro.getDataFinal());
	    

		if (!filtro.getCpfCnpj().isEmpty()) {
		    ListarPessoaPorFiltroEntradaVO f = new ListarPessoaPorFiltroEntradaVO();
		    f.setCpfCgc(Long.parseLong(filtro.getCpfCnpj()));
		    f.setCodigoTipoPesquisa(1);
		    f.setDataNascimento(0);
		    if (String.valueOf(filtro.getCpfCnpj()).length() > 11) { // ï¿½ cnpj
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
        
		LOGGER.error("obterDadosAnalitico1 - "+query.toString());
		LOGGER.error("obterDadosAnalitico2 - "+sb.toString());		
		query.replace(query.indexOf("#"), query.indexOf("#")+1 , sb.toString());
		LOGGER.error("obterDadosAnalitico3 - "+query.toString());
		
		
		relatorio = getJdbcTemplate().query(query.toString(), params, new RelatorioEnvioRetornoAnaliticoDataMapper()); 
		
        
   
    	return relatorio;

    }

    /**
     * Método obterDadosSintetico
     * @param filtro do relatï¿½rio
     * @return List<ManutencoesAnaliticoVO>
     */
    @Override
	public List<RelatorioEnvioRetornoSinteticoVO> obterDadosSintetico(FiltroUtil filtro)throws SQLException {

    	BUCBBusinessDelegate bucbDelegate = new BUCBBusinessDelegate (); 
    	List<RelatorioEnvioRetornoSinteticoVO> relatorio = null;
    	
        StringBuilder query = new StringBuilder(QuerysDepi.RELATORIOENVIORETORNO_OBTERDADOSSINTETICO);
        
        StringBuilder sb = new StringBuilder();
    	
 			MapSqlParameterSource params = new MapSqlParameterSource();
			
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

            if (!filtro.getCpfCnpj().isEmpty()) {
                ListarPessoaPorFiltroEntradaVO f = new ListarPessoaPorFiltroEntradaVO();
                f.setCpfCgc(Long.parseLong(filtro.getCpfCnpj()));
                f.setCodigoTipoPesquisa(1);
                f.setDataNascimento(0);
                if (String.valueOf(filtro.getCpfCnpj()).length() > 11) { // ï¿½ cnpj
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

            params.addValue("dataInicio",filtro.getDataInicio());
    	    params.addValue("dataFim", filtro.getDataFinal());

            query.replace(query.indexOf("#"), query.indexOf("#")+1 , sb.toString());
            
            relatorio = getJdbcTemplate().query(query.toString(), params, new RelatorioEnvioRetornoSinteticoDataMapper());


    	
    	return relatorio;
    }

}
