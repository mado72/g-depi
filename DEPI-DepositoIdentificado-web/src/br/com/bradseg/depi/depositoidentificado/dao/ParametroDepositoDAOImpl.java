package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.core.exception.BusinessException;
import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.DepartamentoDataMapper;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.MotivoDepositoDataMapper;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.ParametroDepositosDataMapper;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.QuerysDepi;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoVO;

	/**
	 * A(O) Class ParametroDepositoDAOImpl.
	 */
	@Repository
	public class ParametroDepositoDAOImpl extends JdbcDao implements ParametroDepositoDAO {

		/** A Constante LOGGER. */
		private static final Logger LOGGER = LoggerFactory.getLogger(ParametroDepositoDAOImpl.class);
		
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
     * Método de obter por filtro
     * @param filtro parâmetro depósito com o código do objeto requisitado
     * @param codigoUsuario - Double.
     * @return List<ParametroDepositoVO>
     */
    public List<ParametroDepositoVO> obterPorFiltroComRestricaoDeGrupoAcesso(FiltroUtil filtro, Double codigoUsuario) {
    	
    	List<ParametroDepositoVO> parametros = null;
    	
        StringBuilder query = new StringBuilder(QuerysDepi.PARAMETRODEPOSITO_OBTERPORFILTROCOMRESTRICAODEGRUPOACESSO);
        
        StringBuilder sb = new StringBuilder();
        
        try {
			
			MapSqlParameterSource params = new MapSqlParameterSource();
			
            sb.append(" AND DBPROD.ALOC_USUAR_GRP_DEP.CUSUAR_DEP_IDTFD = :codusuario ");
            params.addValue("codusuario", codigoUsuario);

            query.replace(query.indexOf("#"), query.indexOf("#")+1 , sb.toString());
            parametros = (List<ParametroDepositoVO>) getJdbcTemplate().query(query.toString(), params, new ParametroDepositosDataMapper());
            return parametros;
            
        } finally {
            LOGGER.info("obterPorFiltroComRestricaoDeGrupoAcesso(FiltroUtil filtro, Double codigoUsuario)"); 
        }
    }

    /**
     * Método de obter por filtro
     * @param filtro parâmetro depósito com o código do objeto requisitado
     * @return List<ParametroDepositoVO>
     */
    public List<ParametroDepositoVO> obterPorFiltro(FiltroUtil filtro) {

    	
    	List<ParametroDepositoVO> parametros = null;
    	
        StringBuilder query = new StringBuilder(QuerysDepi.PARAMETRODEPOSITO_OBTERPORFILTRO);
        
        try {
			
			MapSqlParameterSource params = new MapSqlParameterSource();
			
	        // COLOCAR OS FILTROS DE ACORDO COM A TELA 
			
			if ( filtro != null ) {
				
				StringBuilder sb = new StringBuilder();
				
				// MHG Incluir os filtros
				
	            query.replace(query.indexOf("#"), query.indexOf("#")+1 , sb.toString() );		
			} else {
	            query.replace(query.indexOf("#"), query.indexOf("#")+1 , "" );			
			}

            parametros = (List<ParametroDepositoVO>) getJdbcTemplate().query(query.toString(), params, new ParametroDepositosDataMapper());
            return parametros;
            
        } finally {
            LOGGER.info("obterPorFiltroComRestricaoDeGrupoAcesso(CriterioFiltroUtil filtro, BigDecimal codigoUsuario)"); 
        }
    }

    /**
     * Atualiza um Parametro Depósito.
     * @param parametro - ParametroDepositoVO.
     * @param referenciado - boolean
     * {@inheritDoc}
     */
    public void alterar(ParametroDepositoVO parametro , boolean referenciado) {

        try {

			MapSqlParameterSource params = new MapSqlParameterSource();
        	
            /**
             * Verificação de preenchimento de Campo obrigatório.
             */
            if (parametro.getCompanhia().getCodigoCompanhia() <= 0) {
                throw new BusinessException("Código Cia não informado para o DAO");
            }

            /**
             * Verificação de preenchimento de Campo obrigatório.
             */
            if (parametro.getMotivoDeposito().getCodigoMotivoDeposito() <= 0) {
                throw new BusinessException("Código do Motivo não informado para o DAO");
            }

            /**
             * Verificação de preenchimento de Campo obrigatório.
             */
            if (parametro.getDepartamento().getCodigoDepartamento() <= 0) {
                throw new BusinessException("Código Departamento não informado para o DAO");
            }

            if (referenciado) {
                params.addValue("prm1", parametro.getOutrosDocumentosNecessarios());
                params.addValue("prm2", parametro.getMotivoDeposito().getCodigoMotivoDeposito());                
                params.addValue("whr1", parametro.getCompanhia().getCodigoCompanhia());        	
                params.addValue("whr2", parametro.getDepartamento().getCodigoDepartamento());  
                params.addValue("whr3", parametro.getMotivoDeposito().getCodigoMotivoDeposito());  
                
            } else {
               /**
                 * Entre o indicativo (se tem valor), e (ter o valor de fato) eu prefiro (usar o valor para saber se tem
                 * indicativo). FHV
                 */
                if (parametro.getNumeroDiasAposVencimento() <= 0) {
                    params.addValue("prm1", ConstantesDEPI.NAO);
                    params.addValue("prm2", null);
                } else {
                    params.addValue("prm1", ConstantesDEPI.SIM);
                    params.addValue("prm2", parametro.getNumeroDiasAposVencimento());
                }
                params.addValue("prm3", parametro.getCodigoSucursal());
                params.addValue("prm4", parametro.getCodigoApolice());
                params.addValue("prm5", parametro.getCodigoEndosso());
                params.addValue("prm6", parametro.getCodigoItem());
                params.addValue("prm7", parametro.getCodigoBloqueto());
                params.addValue("prm8", ConstantesDEPI.SIM);
                params.addValue("prm9", parametro.getCodigoDossie());
                params.addValue("prm10", parametro.getCodigoProtocolo());
                params.addValue("prm11", parametro.getCodigoTipo());
                params.addValue("prm12", parametro.getCodigoRamo());
                params.addValue("prm13", parametro.getCodigoParcela());
                params.addValue("prm14", parametro.getOutrosDocumentosNecessarios());
                params.addValue("prm15", parametro.getMotivoDeposito().getCodigoMotivoDeposito());

                /**
                 * Where
                 */
                params.addValue("whr1", parametro.getCompanhia().getCodigoCompanhia());
                params.addValue("whr2", parametro.getDepartamento().getCodigoDepartamento());
                params.addValue("whr3", parametro.getMotivoDeposito().getCodigoMotivoDeposito());

            }
            
            if (referenciado) {
    			getJdbcTemplate().update(QuerysDepi.PARAMETRODEPOSITO_UPDATEREFERENCIADO, params);	
            } else {
    			getJdbcTemplate().update(QuerysDepi.PARAMETRODEPOSITO_UPDATENAOREFERENCIADO, params);	
            } 

        } catch(DataAccessException e){
			LOGGER.error(e.getMessage());
		}  finally {
            LOGGER.info("alterar(ParametroDepositoVO parametro, String usuarioAtualizacao , boolean referenciado) "); 
        }
    }

    /**
     * Excluir
     * @param vo - ParametroDepositoVO.
     * {@inheritDoc}
     */
    public void excluir(ParametroDepositoVO vo, Integer usuarioAtualizacao) {

    	try {

			MapSqlParameterSource params = new MapSqlParameterSource();

            params.addValue("prm1", usuarioAtualizacao);
            
            /**
             * Where
             */
            params.addValue("whr1", vo.getCompanhia().getCodigoCompanhia());
            params.addValue("whr2", vo.getDepartamento().getCodigoDepartamento());
            params.addValue("whr3", vo.getMotivoDeposito().getCodigoMotivoDeposito());

            getJdbcTemplate().update(QuerysDepi.PARAMETRODEPOSITO_INATIVAR, params);	
            
        } catch(DataAccessException e){
			throw new BusinessException("\nA exclusão falhou!\n");
		} finally {
        	LOGGER.info("excluir(ParametroDepositoVO vo)"); 
        }
    }

    /**
     * isReferenciado. Se a tabela possui dependência.
     * @return Boolean.
     * @param vo - ParametroDepositoVO.
     */
    public synchronized Boolean isReferenciado(ParametroDepositoVO vo)  {

        StringBuilder query = new StringBuilder(QuerysDepi.PARAMETRODEPOSITO_ASSOCIACAOMOTIVODEPOSITO_REFERENCIADO);
    	
        try {

        	MapSqlParameterSource params = new MapSqlParameterSource();
            
            params.addValue("prm1", vo.getCompanhia().getCodigoCompanhia());
            params.addValue("prm2", vo.getDepartamento().getCodigoDepartamento());
            params.addValue("prm3", vo.getMotivoDeposito().getCodigoMotivoDeposito());
            
            int retorno =  getJdbcTemplate().queryForInt(query.toString(), params);
            
            if (retorno >= 1) {return true;}

        } finally {

        	LOGGER.info("isReferenciado(ParametroDepositoVO vo)"); 
        }
        return false;
    }

    /**
     * isReferenciado. Se a tabela possui dependência.
     * @return Boolean.
     * @param vo - ParametroDepositoVO.
     */
    @Override
    public synchronized Boolean isReferenciadoDeposito(ParametroDepositoVO vo) {


        StringBuilder query = new StringBuilder(QuerysDepi.PARAMETRODEPOSITO_DEPOSITO_REFERENCIADO);
    	
    	
    	try {

        	MapSqlParameterSource params = new MapSqlParameterSource();
            
            params.addValue("prm1", vo.getCompanhia().getCodigoCompanhia());
            params.addValue("prm2", vo.getDepartamento().getCodigoDepartamento());
            params.addValue("prm3", vo.getMotivoDeposito().getCodigoMotivoDeposito());
            
            int retorno =  getJdbcTemplate().queryForInt(query.toString(), params);
            
            if (retorno >= 1) {return true;}


        } finally {
        	LOGGER.info("isReferenciadoDeposito(ParametroDepositoVO vo)"); 
        }
        return false;
    }

    /**
     * Inserir registro de ParametroDepositoVO
     * @param parametro de ParametroDepositoVO
     */
    @Override
    public void inserir(ParametroDepositoVO parametro) {


    	try {

        	MapSqlParameterSource params = new MapSqlParameterSource();

            /**
             * Verificação de preenchimento de Campo obrigatório.
             */
            if (parametro.getCompanhia().getCodigoCompanhia() <= 0) {
                throw new BusinessException("Código Cia não informado para o DAO");
            }

            /**
             * Verificação de preenchimento de Campo obrigatório.
             */
            if (parametro.getMotivoDeposito().getCodigoMotivoDeposito() <= 0) {
                throw new BusinessException("Código do Motivo não informado para o DAO");
            }

            /**
             * Verificação de preenchimento de Campo obrigatório.
             */
            if (parametro.getDepartamento().getCodigoDepartamento() <= 0) {
                throw new BusinessException("Código Departamento não informado para o DAO");
            }

            //* Verifica se já existe um parametro cadastrado e ativo com os dados informados
            StringBuilder query = new StringBuilder(QuerysDepi.PARAMETRODEPOSITO_EXISTS);
            
            params.addValue("prm1", parametro.getCompanhia().getCodigoCompanhia());
            params.addValue("prm2", parametro.getDepartamento().getCodigoDepartamento());
            params.addValue("prm3", parametro.getMotivoDeposito().getCodigoMotivoDeposito());

            List<String> retorno =  getJdbcTemplate().queryForList(query.toString(), params, String.class);

            if (retorno.isEmpty()) {
                if (retorno.get(1).equals(ConstantesDEPI.INDICADOR_ATIVO)) { // campo not null.
                	
                    StringBuilder queryDepto = new StringBuilder(QuerysDepi.DEPARTAMENTO_OBTERPORCHAVE);
                	MapSqlParameterSource paramsDepto = new MapSqlParameterSource();
                	paramsDepto.addValue("prm1", parametro.getDepartamento().getCodigoDepartamento());
                	
                	DepartamentoVO dep = (DepartamentoVO) getJdbcTemplate().query(queryDepto.toString(), params, new DepartamentoDataMapper());
                	
                    StringBuilder queryMotDepto = new StringBuilder(QuerysDepi.MOTIVODEPOSITO_OBTERPORCHAVE);
                	MapSqlParameterSource paramsMotDepto = new MapSqlParameterSource();
                	paramsMotDepto.addValue("whr1", parametro.getMotivoDeposito().getCodigoMotivoDeposito());
                	
                	MotivoDepositoVO mot = (MotivoDepositoVO) getJdbcTemplate().query(queryMotDepto.toString(), params, new MotivoDepositoDataMapper());

                	String msg = new StringBuilder("Parâmetros de Depósito: ").append(" já cadastrado para a Cia: ").append(
                                    parametro.getCompanhia().getCodigoCompanhia()).append(", para o Departamento: ").append(
                                    dep.getSiglaDepartamento()).append(" e para o Motivo: ").append(mot.getDescricaoBasica()).toString();
                	
                    throw new BusinessException(msg) ;
                } else {
                	
                    MapSqlParameterSource paramsReativar = new MapSqlParameterSource();

                    /**
                     * Novos valores.
                     */
                    paramsReativar.addValue("prm1", parametro.getCodigoBancoVencimento());
                    
                    if (parametro.getNumeroDiasAposVencimento() <= 0) {
                    	paramsReativar.addValue("prm2", null);
                    } else {
                    	paramsReativar.addValue("prm2", parametro.getNumeroDiasAposVencimento());
                    }
                    paramsReativar.addValue("prm3", parametro.getCodigoSucursal());
                    paramsReativar.addValue("prm4", parametro.getCodigoApolice());
                    paramsReativar.addValue("prm5", parametro.getCodigoEndosso());
                    paramsReativar.addValue("prm6", parametro.getCodigoItem());
                    paramsReativar.addValue("prm7", parametro.getCodigoBloqueto());
                    paramsReativar.addValue("prm8", "S");
                    paramsReativar.addValue("prm9", parametro.getCodigoDossie());
                    paramsReativar.addValue("prm10", parametro.getCodigoProtocolo());
                    paramsReativar.addValue("prm11", parametro.getCodigoTipo());
                    paramsReativar.addValue("prm12", parametro.getCodigoRamo());
                    paramsReativar.addValue("prm13", parametro.getCodigoParcela());
                    paramsReativar.addValue("prm14", parametro.getOutrosDocumentosNecessarios());
                    paramsReativar.addValue("prm15", parametro.getCodigoResponsavelUltimaAtualizacao());
                    
                    /**
                     * Where
                     */
                    paramsReativar.addValue("whr1", parametro.getCompanhia().getCodigoCompanhia());
                    paramsReativar.addValue("whr2", parametro.getDepartamento().getCodigoDepartamento());
                    paramsReativar.addValue("whr3", parametro.getMotivoDeposito().getCodigoMotivoDeposito());
                    
                    if (getJdbcTemplate().update(QuerysDepi.PARAMETRODEPOSITO_REATIVAR, paramsReativar) == 0) {
                        throw new BusinessException("A reativação falhou!");
                    }
                }
            } else {
            	
                MapSqlParameterSource paramsInsert = new MapSqlParameterSource();

                /**
                 * Novos valores.
                 */
                paramsInsert.addValue("prm1", parametro.getCompanhia().getCodigoCompanhia());                	paramsInsert.addValue("prm2", parametro.getNumeroDiasAposVencimento());
                paramsInsert.addValue("prm2", parametro.getDepartamento().getCodigoDepartamento());
                paramsInsert.addValue("prm3", parametro.getMotivoDeposito().getCodigoMotivoDeposito());
                paramsInsert.addValue("prm4", parametro.getCodigoBancoVencimento());
                
                if (parametro.getNumeroDiasAposVencimento() <= 0) {
                	paramsInsert.addValue("prm5", null);
                } else {
                	paramsInsert.addValue("prm5", parametro.getNumeroDiasAposVencimento());
                }
                
                paramsInsert.addValue("prm6", parametro.getCodigoSucursal());
                paramsInsert.addValue("prm7", parametro.getCodigoApolice());
                paramsInsert.addValue("prm8", parametro.getCodigoEndosso());
                paramsInsert.addValue("prm9", parametro.getCodigoItem());
                paramsInsert.addValue("prm10", parametro.getCodigoBloqueto());
                paramsInsert.addValue("prm11", parametro.getCodigoCpfCnpj());
                paramsInsert.addValue("prm12", parametro.getCodigoDossie());
                paramsInsert.addValue("prm13", parametro.getCodigoProtocolo());
                paramsInsert.addValue("prm14", parametro.getCodigoTipo());
                paramsInsert.addValue("prm15", parametro.getCodigoRamo());
                paramsInsert.addValue("prm16", parametro.getCodigoParcela());
                paramsInsert.addValue("prm17", parametro.getOutrosDocumentosNecessarios());
                paramsInsert.addValue("prm18", parametro.getMotivoDeposito().getCodigoMotivoDeposito());

                getJdbcTemplate().update(QuerysDepi.PARAMETRODEPOSITO_INSERT, paramsInsert) ;
                }

        } finally {
        	LOGGER.info("inserir(ParametroDepositoVO parametro)"); 
        }
    }

}
