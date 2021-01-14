/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.bradseg.depi.depositoidentificado.dao.mapper.MotivoDepositoDataMapper;
import br.com.bradseg.depi.depositoidentificado.enums.Tabelas;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.QuerysDepi;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

import br.com.bradseg.bsad.framework.core.exception.BusinessException;
import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;

/**
 * Dao que representa a entidade MOTVO_DEP_IDTFD
 * @author Globality
 */
@Repository
public class MotivoDepositoDAOImpl extends JdbcDao implements MotivoDepositoDAO {

	/** A Constante LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MovimentoDepositoDAOImpl.class);
	
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
     * Método responsável por inserir um Motivo Depósito.
     * @param vo - Motivo Depósito a ser inserido.
     */
    @Override
    public void inserir(MotivoDepositoVO vo) {

    	StringBuilder query = new StringBuilder(QuerysDepi.MOTIVODEPOSITO_EXISTS);
    	
    	try {
    		
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("whr1", vo.getDescricaoBasica().trim());


			List<MotivoDepositoVO> motivoDepto = (List<MotivoDepositoVO>) getJdbcTemplate().query(query.toString(), params, new MotivoDepositoDataMapper());

			if (motivoDepto.size() >= 1 ) {
                if (motivoDepto.get(1).getIndicadorAtivo().equals("S")) {
                    throw new BusinessException(ConstantesDEPI.ERRO_REGISTRO_JA_CADASTRADO + " : "  + new StringBuilder(
                        " Descrição Básica: ").append(vo.getDescricaoBasica()).append(".").toString());
                } else {

                    params.addValue("prm1", vo.getDescricaoDetalhada().trim());
                    params.addValue("prm2", vo.getCodigoEventoContabil());
                    params.addValue("prm3", vo.getCodigoItemContabil());
                    params.addValue("prm4", vo.getCodigoResponsavelUltimaAtualizacao());
                    params.addValue("whr1", motivoDepto.get(1).getCodigoMotivoDeposito());

        			getJdbcTemplate().update(QuerysDepi.MOTIVODEPOSITO_ATIVAR, params);
                }
        					
            } else {

            	params.addValue("prm1", vo.getDescricaoBasica().trim());
                params.addValue("prm2", vo.getDescricaoDetalhada());
                params.addValue("prm3", vo.getCodigoEventoContabil());
                params.addValue("prm4", vo.getCodigoItemContabil());
                params.addValue("prm5", vo.getCodigoResponsavelUltimaAtualizacao());

    			getJdbcTemplate().update(QuerysDepi.MOTIVODEPOSITO_INSERT, params);
                
            }

        }  finally {
        	LOGGER.info("inserir(MotivoDepositoVO vo)"); 
        }
    }

    /**
     * Método responsável por alterar um Motivo Depósito.
     * @param vo - Motivo Depósito a ser alterado.
     */
    @Override
    public void alterar(MotivoDepositoVO vo){
 
    	StringBuilder query = null;  
    	query = new StringBuilder(QuerysDepi.MOTIVODEPOSITO_ALL);
        	
       	try {
        		
			MapSqlParameterSource paramsBasica = new MapSqlParameterSource();
			//params.addValue("descBasica", vo.getDescricaoBasica().trim());

			List<MotivoDepositoVO> motivoDepto = (List<MotivoDepositoVO>) getJdbcTemplate().query(query.toString(), paramsBasica, new MotivoDepositoDataMapper());
			
			MotivoDepositoVO motivoExistente = findPorDescricaoBasica (motivoDepto , vo );

            if (motivoExistente != null) {
                StringBuilder sb = new StringBuilder("Motivo Depósito com Descrição Básica: ").append(vo.getDescricaoBasica())
                    .append(" já está cadastrado");
                if (motivoExistente.getIndicadorAtivo().equals("N")) {
                    sb.append(" com status inativo");
                }
                sb.append(".");
                throw new BusinessException(ConstantesDEPI.ERRO_CUSTOMIZADA + " : " + sb.toString());
            }
            
			MapSqlParameterSource paramsUpd = new MapSqlParameterSource();

			paramsUpd.addValue("prm1", vo.getDescricaoBasica().trim());
			paramsUpd.addValue("prm2", vo.getDescricaoDetalhada());
			paramsUpd.addValue("prm3", vo.getCodigoEventoContabil());
			paramsUpd.addValue("prm4", vo.getCodigoItemContabil());
			paramsUpd.addValue("prm5", vo.getCodigoResponsavelUltimaAtualizacao());
			paramsUpd.addValue("whr1", vo.getCodigoMotivoDeposito());

			Integer count = getJdbcTemplate().update(QuerysDepi.MOTIVODEPOSITO_UPDATE, paramsUpd);

            if (count == 0) {
                throw new BusinessException(ConstantesDEPI.ERRO_REGISTRO_INEXISTENTE);
            }

        }  finally {
        	LOGGER.info("alterar(MotivoDepositoVO vo)"); 
        }
    }

    /**
     * Método responsável por excluir um Motivo Depósito.
     * @param vo - Motivo Depósito a ser excluído.
     */
    @Override
    public void excluir(MotivoDepositoVO vo) {

        try {
        	
			MapSqlParameterSource params = new MapSqlParameterSource();

			params.addValue("prm1", vo.getCodigoResponsavelUltimaAtualizacao());
			params.addValue("whr1", vo.getCodigoMotivoDeposito());

			Integer count = getJdbcTemplate().update(QuerysDepi.MOTIVODEPOSITO_INSERT, params);
			
            if (count == 0) {
                throw new BusinessException(ConstantesDEPI.ERRO_REGISTRO_INEXISTENTE);
            }

        } finally {
        	LOGGER.info("excluir(MotivoDepositoVO vo)"); 
        }
    }
    
    /**
     * Método responsável por excluir lista um Motivo Depósito.
     * @param vo - Motivo Depósito a ser excluído.
     */
    @Override
    public void excluirLista(List<MotivoDepositoVO> listvo) {

        StringBuilder codsMotivos = new StringBuilder();
        for (MotivoDepositoVO vo : listvo) {
            if (isReferenciado(vo)) {
                if (codsMotivos.length() > 0) {
                    codsMotivos.append("; ");
                }
                MotivoDepositoVO m = obterPorChave(vo);
                codsMotivos.append("Motivo Depósito: ").append(m.getDescricaoBasica());
            } else {
            	excluir(vo);
            }
        }
        if (codsMotivos.length() > 0) {
            throw new IntegrationException(ConstantesDEPI.ERRO_DEPENDENCIA + " - " + codsMotivos.toString() + "Parâmetros de Depósito");
        }
        
    }
    
    
    /**
     * isReferenciado retorna se o registro é utilizado nas tabelas como FK. {@inheritDoc}
     */
    @Override
    public synchronized Boolean isReferenciado(MotivoDepositoVO vo){

    	StringBuilder query = new StringBuilder(QuerysDepi.MOTIVODEPOSITO_REFERENCIADO_PARAMETRODEPOSITO);
    	
    	try {
    		
			MapSqlParameterSource params = new MapSqlParameterSource();
            /**
             * Where
             */
			params.addValue("whr1", vo.getCodigoMotivoDeposito());
			
			Integer count = getJdbcTemplate().queryForInt(query.toString(), params);


            if (count >= 1) { 
            	return true;
            }

        } finally {
        	LOGGER.info("isReferenciado(MotivoDepositoVO vo)"); 
        }
        return false;
    }

    /**
     * Obtém apenas os Motivos que estão associados a Parametros de Depósito.
     * @param codigoCia - int.
     * @param codigoDep - int.
     * @param codigoUsuario - BigDecimal.
     * @param e - Tabelas.
     * @return List<MotivoDepositoVO>.
     */
    public List<MotivoDepositoVO> obterComRestricaoDeGrupoAcesso(final int codigoCia, final int codigoDep,final Double codigoUsuario, final Tabelas e){

		StringBuilder query = new StringBuilder();
    	
    	try {

			MapSqlParameterSource params = new MapSqlParameterSource();   		
            String msg = "";

            if (e.equals(Tabelas.PARAMETRO_DEPOSITO)) {
            	query.append(QuerysDepi.MOTIVODEPOSITO_OBTERCOMRESTRICAODEPARAMETRODEPOSITO);
                msg = " um Parâmetro de Depósito ou Grupo de Acesso vinculado ao usuário.";
            } else if (e.equals(Tabelas.CONTA_CORRENTE_MOTIVO_DEPOSITO)) {
            	query.append(QuerysDepi.MOTIVODEPOSITO_OBTERCOMRESTRICAODECONTACORRENTEMOTIVODEPOSITO);
                msg = " uma Associação de Motivo ou Grupo de Acesso vinculado ao usuário";
            } else if (e.equals(Tabelas.DEPOSITO)) {
            	query.append(QuerysDepi.MOTIVODEPOSITO_OBTERCOMRESTRICAODEDEPOSITO);
                msg = " um Depósito ou Grupo de Acesso vinculado ao usuário";
            } else {
                throw new IntegrationException("Enum inválido.");
            }

            /**
             * Parametros.
             */
        	params.addValue("prm1", codigoCia);
            params.addValue("prm2", codigoDep);
            params.addValue("prm3", codigoUsuario);

			List<MotivoDepositoVO> motivoDepto = (List<MotivoDepositoVO>) getJdbcTemplate().query(query.toString(), params, new MotivoDepositoDataMapper());
			
            if (motivoDepto == null || motivoDepto.isEmpty()) {
                throw new BusinessException(ConstantesDEPI.ERRO_RELACIONAMENTOS_NAO_CADASTRADOS + " : " + msg);
            }
                
            return motivoDepto;
            
        } finally {
        	LOGGER.info("obterComRestricaoDeGrupoAcesso(final int codigoCia, final int codigoDep, final BigDecimal codigoUsuario, final Tabelas e)"); 
        }
    }
    
    /**
     * Obtém apenas os Motivos por Chave.
     * @param MotivoDepositoVO - MotivoDepositoVO
     * @return List<MotivoDepositoVO>.
     */
    @Override
    public MotivoDepositoVO obterPorChave(MotivoDepositoVO vo){

		StringBuilder query = new StringBuilder();
    	query.append(QuerysDepi.MOTIVODEPOSITO_OBTERPORCHAVE);
   	
		try {

			MapSqlParameterSource params = new MapSqlParameterSource();   		

            /**
             * Parametros.
             */
        	// params.addValue("whr1", BigDecimal.valueOf(vo.getCodigoMotivoDeposito()));
			params.addValue("whr1", vo.getCodigoMotivoDeposito());
			
			List<MotivoDepositoVO> motivoDepto = (List<MotivoDepositoVO>) getJdbcTemplate().query(query.toString(), params, new MotivoDepositoDataMapper());
			
            if (motivoDepto == null) {
                throw new BusinessException(ConstantesDEPI.ERRO_RELACIONAMENTOS_NAO_CADASTRADOS);
            }
                
            return motivoDepto.get(0);
            
        } finally {
        	LOGGER.info("obterPorChave(MotivoDepositoVO vo)"); 
        }
    }

    /**
     * Obtém apenas os Motivos por Filtro.
     * @param MotivoDepositoVO - MotivoDepositoVO
     * @return List<MotivoDepositoVO>.
     */
	@Override
	public List<MotivoDepositoVO> obterPorFiltro(FiltroUtil filtro) {
		StringBuilder query = new StringBuilder();
    	query.append(QuerysDepi.MOTIVODEPOSITO_ALL);
    	
		try {

			MapSqlParameterSource params = new MapSqlParameterSource();   		

	        /**
	         * Parametros.
	         */
			if (!filtro.getDescricaoBasica().isEmpty()) {
				query.append("WHERE RMOTVO_DEP_IDTFD = :whr1");
		    	params.addValue("whr1", filtro.getDescricaoBasica());			
			} else if (!filtro.getDescricaoDetalhada().isEmpty()) {
				query.append("WHERE RDETLH_MOTVO_DEP = :whr1");
		    	params.addValue("whr1", filtro.getDescricaoDetalhada());			
			}
			
        	List<MotivoDepositoVO> motivoDepto = (List<MotivoDepositoVO>) getJdbcTemplate().query(query.toString(), params, new MotivoDepositoDataMapper());
			
            if (motivoDepto == null || motivoDepto.isEmpty() ) {
                throw new BusinessException(ConstantesDEPI.ERRO_RELACIONAMENTOS_NAO_CADASTRADOS);
            }
                
            return motivoDepto;
            
        } finally {
        	LOGGER.info("obterPorFiltro(FiltroUtil filtro)"); 
        }
	}

	
    /**
     * Obtém todos os Motivos.
     * @return List<MotivoDepositoVO>.
     */
	@Override
	public List<MotivoDepositoVO> obterTodos() {
		StringBuilder query = new StringBuilder();
    	query.append(QuerysDepi.MOTIVODEPOSITO_ALL);
   	
		try {

			MapSqlParameterSource params = new MapSqlParameterSource();   		

        	List<MotivoDepositoVO> motivoDepto = (List<MotivoDepositoVO>) getJdbcTemplate().query(query.toString(), params, new MotivoDepositoDataMapper());
			
            if (motivoDepto == null || motivoDepto.isEmpty() ) {
                throw new BusinessException(ConstantesDEPI.ERRO_RELACIONAMENTOS_NAO_CADASTRADOS);
            }
                
            return motivoDepto;
            
        } finally {
        	LOGGER.info("obterPorFiltro(FiltroUtil filtro)"); 
        }
	}
	

	private MotivoDepositoVO findPorDescricaoBasica (List<MotivoDepositoVO> motivoDepto , MotivoDepositoVO vo ) {
	
	    for(MotivoDepositoVO motivo : motivoDepto) {
	        if(motivo.getDescricaoBasica().equals(vo.getDescricaoBasica().trim())) {
	            return motivo;
	        }
	    }
	    return null;
	}
	
}
