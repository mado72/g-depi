 /**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao;

import java.text.MessageFormat;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.core.exception.BusinessException;
import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.GrupoAcessoDataMapper;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.QuerysDepi;
import br.com.bradseg.depi.depositoidentificado.vo.GrupoAcessoVO;
import br.com.bradseg.depi.depositoidentificado.vo.UsuarioVO;

/**
 * Dao que representa a entidade GrupoAcessoDAO
 * @author Globality
 */
@Repository
public class GrupoAcessoDAOImpl extends JdbcDao implements GrupoAcessoDAO {
	
	private static final String WHR1 = "whr1";

	private static final String WHR2 = "whr2";


	@Autowired
	private UsuarioDAO usuarioDAO;

	/** A Constante LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(GrupoAcessoDAOImpl.class);
	
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
     * Método de obter por filtro um Grupo de Usuário
     * @param filtro - filtro CriterioFiltroUtil
     * @return List - Lista de GrupoAcessoVO
     */
    @Override
	public List<GrupoAcessoVO> obterPorFiltro(FiltroUtil filtro)  {

    	
    	String query = QuerysDepi.GRUPOACESSO_OBTERPORFILTRONEW;
    	final String complementoQuery;

        try {

        	MapSqlParameterSource params = null;
        	
			if (!filtro.getCriterios().isEmpty()) {
				complementoQuery = filtro.getClausaAndFiltro();
				
				params = filtro.getMapParamFiltro();
			}
			else {
				complementoQuery = "";
			}
        	
			query = MessageFormat.format(query, complementoQuery);
			
			List<GrupoAcessoVO> listGrupoAcessoVO = getJdbcTemplate().query(
					query.toString(), params, new GrupoAcessoDataMapper());
			
			return listGrupoAcessoVO;
        } finally {
        	LOGGER.info("obterPorFiltro(CriterioFiltroUtil filtro)"); 
        }
        
    }

	/**
     * Inserir registro de GrupoAcessoVO
     * @param vo de GrupoAcessoVO
     */
    @Override
    public void inserir(GrupoAcessoVO vo) {
    	
    	StringBuilder query = new StringBuilder(QuerysDepi.GRUPOACESSO_EXISTSATIVO);

    	try {

			MapSqlParameterSource params = new MapSqlParameterSource();

			params.addValue(WHR1, vo.getDepto().getCodigoDepartamento());
			params.addValue(WHR2, vo.getCia().getCodigoCompanhia());

			List<GrupoAcessoVO> grupoAcessoVO = getJdbcTemplate() .query(query.toString(), params, new GrupoAcessoDataMapper());

            if (!grupoAcessoVO.isEmpty()) {

                GrupoAcessoVO grupoCadastrado = obterGrupoPorChave(new GrupoAcessoVO(grupoAcessoVO.get(0).getCodigoGrupoAcesso()));

                throw new IntegrationException( 
                		new StringBuilder(ConstantesDEPI.ERRO_CUSTOMIZADA)
                				.append(" Grupo de Acesso: ")
                				.append(grupoCadastrado.getCodigoGrupoAcesso())
                				.append(" - ").append(grupoCadastrado.getNomeGrupoAcesso())
                				.append(" j� cadastrado para a Cia: ")
                				.append(grupoCadastrado.getCia().getCodigoCompanhia())
                				.append(" e Departamento: ")
                				.append(grupoCadastrado.getDepto().getSiglaDepartamento()).toString());
            }
            
            StringBuilder queryInsert = new StringBuilder(QuerysDepi.GRUPOACESSO_INSERT);

			MapSqlParameterSource paramsInsert = new MapSqlParameterSource();

            
			paramsInsert.addValue("prm1", vo.getDepto().getCodigoDepartamento());
			paramsInsert.addValue("prm2", vo.getCia().getCodigoCompanhia());
			paramsInsert.addValue("prm3", vo.getCodigoResponsavelUltimaAtualizacao()); 
			
			int count = getJdbcTemplate() .update(queryInsert.toString(), paramsInsert);

            if (count > 0) {
                vo.setCodigoGrupoAcesso(grupoAcessoVO.get(0).getCodigoGrupoAcesso());
            } else {
                throw new IntegrationException(ConstantesDEPI.ERRO_CUSTOMIZADA + " - " + "N�o foi poss�vel incluir o Grupo de Acesso.");
            }

            StringBuilder queryAlocar = new StringBuilder(QuerysDepi.GRUPOACESSO_ALOCARFUNCIONARIO);
            MapSqlParameterSource paramsAlocar = new MapSqlParameterSource();
		
            for (UsuarioVO usuario : vo.getUsuarios()) {

            	paramsAlocar.addValue("prm1", vo.getCodigoGrupoAcesso());
            	paramsAlocar.addValue("prm2", usuario.getCodigoUsuario());
            	paramsAlocar.addValue("prm3", vo.getCodigoResponsavelUltimaAtualizacao()); 
    			getJdbcTemplate().update(queryAlocar.toString(), paramsAlocar);
            	
            } 
        } finally { 
        	LOGGER.info("inserir(GrupoAcessoVO vo)");
        }
    }

    /**
     * Atualizar grupo de acesso. Obt�m os usu�rios. Para cada usu�rio obtido, verificar se o mesmo existe na nova lista. Para cada
     * usu�rio obtivo, inclui ou exclui de acordo com a exist�ncia na nova lista de usu�rios. | *
     * @param vo - GrupoAcessoVO.
     */
    @Override
    public void alterar(GrupoAcessoVO vo) {

    	try {

            List<UsuarioVO> usuariosAtuais = usuarioDAO.obterPorGrupoAcesso(vo);

            //**
            // * Proteção da rotina.
            // *
            if (vo.getCodigoGrupoAcesso() <= 0) {
                throw new BusinessException("Código Grupo de Acesso inv�lido na atualiza��o.");
            }

            if (vo.getUsuarios() == null) {
                throw new BusinessException("Lista de usu�rios inválida na atualiza��o.");
            }

            if (vo.getCodigoResponsavelUltimaAtualizacao().doubleValue() <= 0) {
                throw new IntegrationException("Código do responsável pela �ltima atualiza��o inv�lido na atualiza��o.");
            }

            //**
            //* desaloca usu�rios.
            //*
            boolean encontrado = false;
            for (UsuarioVO usuarioAtual : usuariosAtuais) {
                //**
                // * Entre os novos usu�rios, existem os que j� estavam no grupo ?
                //*
                for (UsuarioVO novoUsuario : vo.getUsuarios()) {
                    if (usuarioAtual.equals(novoUsuario)) {
                        encontrado = true;
                        break;
                    }
                }

                if (!encontrado) {
                	
                	StringBuilder query = new StringBuilder(QuerysDepi.GRUPOACESSO_DESALOCARFUNCIONARIO);

          			MapSqlParameterSource params = new MapSqlParameterSource();

          			params.addValue("prm1", vo.getCodigoResponsavelUltimaAtualizacao());
           			params.addValue(WHR1, vo.getCodigoGrupoAcesso());
           			params.addValue(WHR2, usuarioAtual.getCodigoUsuario());
           			
        			getJdbcTemplate().update(query.toString(), params);
           			
                }
                encontrado = false;
            }

            //**
            //* Aloca��o dos usu�rios novos.
            //*
            for (UsuarioVO usr : vo.getUsuarios()) {
            	
            	StringBuilder queryAlocar = new StringBuilder(QuerysDepi.ALOCACAO_EXISTSUSUARIO);

      			MapSqlParameterSource paramsAlocar = new MapSqlParameterSource();

      			paramsAlocar.addValue(WHR1, vo.getCodigoGrupoAcesso());
      			paramsAlocar.addValue(WHR2, usr.getCodigoUsuario());
       			
    			List<String> indAtivo = getJdbcTemplate().queryForList(queryAlocar.toString(), paramsAlocar, String.class) ; 
    
                if (!indAtivo.isEmpty()) {
                    if (ConstantesDEPI.INDICADOR_INATIVO.toString().equals(indAtivo.get(0))) {
                    	
                    	StringBuilder queryRealocar = new StringBuilder(QuerysDepi.GRUPOACESSO_REALOCARFUNCIONARIO);

              			MapSqlParameterSource paramsRealocar = new MapSqlParameterSource();

              			paramsRealocar.addValue("prm1", vo.getCodigoResponsavelUltimaAtualizacao());
              			paramsRealocar.addValue(WHR1, vo.getCodigoGrupoAcesso());
              			paramsRealocar.addValue(WHR2, usr.getCodigoUsuario());
               			
            			getJdbcTemplate().update(queryRealocar.toString(), paramsRealocar);
                        
                    }
                } else {
                	
                	StringBuilder queryAloc = new StringBuilder(QuerysDepi.GRUPOACESSO_ALOCARFUNCIONARIO);

          			MapSqlParameterSource paramsAloc = new MapSqlParameterSource();

          			paramsAloc.addValue("prm1", vo.getCodigoResponsavelUltimaAtualizacao());
          			paramsAloc.addValue(WHR1, vo.getCodigoGrupoAcesso());
          			paramsAloc.addValue(WHR2, usr.getCodigoUsuario());
           			
        			getJdbcTemplate().update(queryAloc.toString(), paramsAloc);
                	
                }
            }

            
        	StringBuilder queryAloc = new StringBuilder(QuerysDepi.GRUPOACESSO_UPDATE);

  			MapSqlParameterSource paramsAloc = new MapSqlParameterSource();

  			paramsAloc.addValue("prm1", vo.getCodigoResponsavelUltimaAtualizacao());
  			paramsAloc.addValue(WHR1, vo.getCodigoGrupoAcesso());
   			
			getJdbcTemplate().update(queryAloc.toString(), paramsAloc);

        } finally {
        	LOGGER.info("alterar(GrupoAcessoVO vo)"); 
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
	public void desalocarUsuarios(GrupoAcessoVO grupo) {
    
        try {
	        /**
	         * Alterar Desaloca usu�rios na altera��o, pois a lista de usu�rios n�o � enviada.
	         */
	        alterar(grupo); 
        } finally {
    	  LOGGER.info("desalocarUsuarios(GrupoAcessoVO grupo)");
        } 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void excluir(GrupoAcessoVO grupo) {
    
    	try {
        
            /**
             * Desalocar Usu�rios.
             */
            desalocarUsuarios(grupo);
            
            
        	StringBuilder query = new StringBuilder(QuerysDepi.GRUPOACESSO_DESATIVAR);

  			MapSqlParameterSource params = new MapSqlParameterSource();

  			params.addValue("prm1", grupo.getCodigoResponsavelUltimaAtualizacao());
  			params.addValue(WHR1, grupo.getCodigoGrupoAcesso());
   			
			getJdbcTemplate().update(query.toString(), params);

        } finally {
        	LOGGER.info("excluir(GrupoAcessoVO grupo)"); 
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized Boolean isReferenciado(GrupoAcessoVO grupo) {

		
    	StringBuilder query = new StringBuilder(QuerysDepi.GRUPOACESSO_REFERENCIADO_PARAMETRODEPOSITO);

    	try {

			MapSqlParameterSource params = new MapSqlParameterSource();

			params.addValue(WHR1, grupo.getCia().getCodigoCompanhia());
			params.addValue(WHR2, grupo.getDepto().getCodigoDepartamento());

			List<GrupoAcessoVO> grupoAcessoVO = getJdbcTemplate() .query(query.toString(), params, new GrupoAcessoDataMapper());

            return (!grupoAcessoVO.isEmpty());

        } finally {
        	LOGGER.info("isReferenciado(GrupoAcessoVO grupo)"); 
        }
    }

    @Override
	public GrupoAcessoVO obterGrupoPorChave(GrupoAcessoVO grupo)  {

    	StringBuilder query = new StringBuilder(QuerysDepi.GRUPOACESSO_OBTERGRUPOPORCHAVE);

    	try {

			MapSqlParameterSource params = new MapSqlParameterSource();

			params.addValue(WHR1, grupo.getCodigoGrupoAcesso());

			List<GrupoAcessoVO> grupoAcessoVO = getJdbcTemplate().query(query.toString(), params, new GrupoAcessoDataMapper());

	        return grupoAcessoVO.get(0); 

        } finally {
        	LOGGER.info("obterGrupoPorChave(GrupoAcessoVO grupo)"); 
        }
        
    }

}