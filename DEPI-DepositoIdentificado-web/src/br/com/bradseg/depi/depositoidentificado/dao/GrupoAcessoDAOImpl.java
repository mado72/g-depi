 /**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.GrupoAcessoDataMapper;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIBusinessException;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.Funcao;
import br.com.bradseg.depi.depositoidentificado.util.QuerysDepi;
import br.com.bradseg.depi.depositoidentificado.vo.CodigoIndicativoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.GrupoAcessoVO;
import br.com.bradseg.depi.depositoidentificado.vo.UsuarioVO;

/**
 * Dao que representa a entidade GrupoAcessoDAO
 * @author Globality
 */
@Repository
public class GrupoAcessoDAOImpl extends JdbcDao implements GrupoAcessoDAO {
	
	private static final String PRM1 = "prm1";

	private static final String PRM2 = "prm2";

	private static final String PRM3 = "prm3";

	private static final String WHR1 = "whr1";

	private static final String WHR2 = "whr2";
	
	/** A Constante LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(GrupoAcessoDAOImpl.class);

	@Autowired
	private UsuarioDAO usuarioDAO;
	
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
    	String complementoQuery;

        try {

        	MapSqlParameterSource params = null;
        	
			if (!filtro.getCriterios().isEmpty()) {
				complementoQuery = new StringBuilder(" AND ").append(filtro.getClausulasParciais()).toString();
				
				params = filtro.getMapParamFiltro();
			}
			else {
				complementoQuery = "";
			}
        	
			query = query.replaceAll("%s", complementoQuery);
			
			return getJdbcTemplate().query(query, params,
					new GrupoAcessoDataMapper());
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
    	
    	try {

			MapSqlParameterSource params = new MapSqlParameterSource();

			params.addValue(WHR1, vo.getDepto().getCodigoDepartamento());
			params.addValue(WHR2, vo.getCia().getCodigoCompanhia());

			List<GrupoAcessoVO> grupoAcessoVO = getJdbcTemplate().query(
					QuerysDepi.GRUPOACESSO_EXISTSATIVO, params,
					new GrupoAcessoDataMapper());

            if (!grupoAcessoVO.isEmpty()) {

                GrupoAcessoVO grupoCadastrado = obterGrupoPorChave(new GrupoAcessoVO(grupoAcessoVO.get(0).getCodigoGrupoAcesso()));

                throw new DEPIIntegrationException(ConstantesDEPI.ERRO_REGISTRO_DUPLICADO,
                		new StringBuilder(" Grupo de Acesso: ").append(grupoCadastrado.getCodigoGrupoAcesso()).append(" - ").append(grupoCadastrado.getNomeGrupoAcesso())
                				.append(" - Cia: ").append(grupoCadastrado.getCia().getCodigoCompanhia())
                				.append(" - Departamento: ").append(grupoCadastrado.getDepto().getSiglaDepartamento()).toString());
            }
            
            StringBuilder queryInsert = new StringBuilder(QuerysDepi.GRUPOACESSO_INSERT);

			MapSqlParameterSource paramsInsert = new MapSqlParameterSource();
            
			paramsInsert.addValue(PRM1, vo.getDepto().getCodigoDepartamento());
			paramsInsert.addValue(PRM2, vo.getCia().getCodigoCompanhia());
			paramsInsert.addValue(PRM3, vo.getCodigoResponsavelUltimaAtualizacao()); 
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			int count = getJdbcTemplate().update(queryInsert.toString(), paramsInsert, keyHolder);

            if (count > 0) {
            	int codGrupoAcesso = keyHolder.getKey().intValue();
                vo.setCodigoGrupoAcesso(codGrupoAcesso);
            } else {
                throw new DEPIIntegrationException(ConstantesDEPI.ERRO_CUSTOMIZADA, "Não foi possível incluir o Grupo de Acesso.");
            }

            StringBuilder queryAlocar = new StringBuilder(QuerysDepi.GRUPOACESSO_ALOCARFUNCIONARIO);
            MapSqlParameterSource paramsAlocar = new MapSqlParameterSource();
		
            for (UsuarioVO usuario : vo.getFuncionarios()) {

            	paramsAlocar.addValue(PRM1, vo.getCodigoGrupoAcesso());
            	paramsAlocar.addValue(PRM2, usuario.getCodigoUsuario());
            	paramsAlocar.addValue(PRM3, vo.getCodigoResponsavelUltimaAtualizacao()); 
    			getJdbcTemplate().update(queryAlocar.toString(), paramsAlocar);
            	
            } 
        } finally { 
        	LOGGER.info("inserir(GrupoAcessoVO vo)");
        }
    }

    /**
     * Atualizar grupo de acesso. Obt�m os usuários. Para cada usuário obtido, verificar se o mesmo existe na nova lista. Para cada
     * usuário obtivo, inclui ou exclui de acordo com a exist�ncia na nova lista de usuários. | *
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
                throw new DEPIBusinessException("msg.erro.grupoAcesso.codigoinvalido");
            }

            List<UsuarioVO> funcionarios = vo.getFuncionarios();
            if (funcionarios == null) {
                throw new DEPIBusinessException("msg.erro.grupoAcesso.listausuarioinvalida");
            }

            if (vo.getCodigoResponsavelUltimaAtualizacao().doubleValue() <= 0) {
                throw new DEPIBusinessException("msg.erro.grupoAcesso.codResponsavelInvalido");
            }
            
            final Funcao<UsuarioVO, ?> extrairCodigoUsuario = new Funcao<UsuarioVO, Object>() {
            	@Override
            	public Object apply(UsuarioVO source) {
            		return source.getCodigoUsuario();
            	}
			};
			
			List<UsuarioVO> desalocar = BaseUtil.obterItensSemIntersecao(usuariosAtuais, funcionarios, extrairCodigoUsuario);
			List<UsuarioVO> alocar = BaseUtil.obterItensSemIntersecao(funcionarios, usuariosAtuais, extrairCodigoUsuario);
			
			for (UsuarioVO usuarioVO : desalocar) {
				queryDesalocar(vo, usuarioVO);
			}
			
			for (UsuarioVO usuarioVO : alocar) {
				queryAlocarOuRealocar(vo, usuarioVO);
			}
            
        	StringBuilder queryAloc = new StringBuilder(QuerysDepi.GRUPOACESSO_UPDATE);

  			MapSqlParameterSource paramsAloc = new MapSqlParameterSource();

  			paramsAloc.addValue(PRM1, vo.getCodigoResponsavelUltimaAtualizacao());
  			paramsAloc.addValue(WHR1, vo.getCodigoGrupoAcesso());
   			
			getJdbcTemplate().update(queryAloc.toString(), paramsAloc);

        } finally {
        	LOGGER.info("alterar(GrupoAcessoVO vo)"); 
        }
    }

	private void queryAlocarOuRealocar(GrupoAcessoVO vo, UsuarioVO usr) {
		MapSqlParameterSource paramsAlocar = new MapSqlParameterSource();

		paramsAlocar.addValue(WHR1, vo.getCodigoGrupoAcesso());
		paramsAlocar.addValue(WHR2, usr.getCodigoUsuario());
		
		List<String> indAtivo = getJdbcTemplate().queryForList(QuerysDepi.ALOCACAO_EXISTSUSUARIO, paramsAlocar, String.class) ; 
   
		if (!indAtivo.isEmpty()) {
		    if (ConstantesDEPI.INDICADOR_INATIVO.equals(indAtivo.get(0))) {
				queryRealocar(vo, usr);
		    }
		} else {
			queryAlocar(vo, usr);
		}
	}

	private void queryAlocar(GrupoAcessoVO vo, UsuarioVO usr) {
		MapSqlParameterSource paramsAloc = new MapSqlParameterSource();

		paramsAloc.addValue(PRM1, vo.getCodigoGrupoAcesso());
		paramsAloc.addValue(PRM2, usr.getCodigoUsuario());
		paramsAloc.addValue(PRM3, vo.getCodigoResponsavelUltimaAtualizacao());
		
		getJdbcTemplate().update(QuerysDepi.GRUPOACESSO_ALOCARFUNCIONARIO, paramsAloc);
	}

	private void queryRealocar(GrupoAcessoVO vo, UsuarioVO usr) {
		MapSqlParameterSource paramsRealocar = new MapSqlParameterSource();

		paramsRealocar.addValue(PRM1, vo.getCodigoResponsavelUltimaAtualizacao());
		paramsRealocar.addValue(WHR1, vo.getCodigoGrupoAcesso());
		paramsRealocar.addValue(WHR2, usr.getCodigoUsuario());
		
		getJdbcTemplate().update(QuerysDepi.GRUPOACESSO_REALOCARFUNCIONARIO, paramsRealocar);
	}

	private void queryDesalocar(GrupoAcessoVO grupo, UsuarioVO usuario) {
		StringBuilder query = new StringBuilder(QuerysDepi.GRUPOACESSO_DESALOCARFUNCIONARIO);

		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue(PRM1, grupo.getCodigoResponsavelUltimaAtualizacao());
		params.addValue(WHR1, grupo.getCodigoGrupoAcesso());
		params.addValue(WHR2, usuario.getCodigoUsuario());
		
		getJdbcTemplate().update(query.toString(), params);
	}

    /**
     * {@inheritDoc}
     */
    @Override
	public void desalocarUsuarios(GrupoAcessoVO grupo) {
    
        try {
	        /**
	         * Alterar Desaloca usuários na alteração, pois a lista de usuários não � enviada.
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
             * Desalocar Usuários.
             */
            desalocarUsuarios(grupo);
            
            
        	StringBuilder query = new StringBuilder(QuerysDepi.GRUPOACESSO_DESATIVAR);

  			MapSqlParameterSource params = new MapSqlParameterSource();

  			params.addValue(PRM1, grupo.getCodigoResponsavelUltimaAtualizacao());
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

    	try {

			MapSqlParameterSource params = new MapSqlParameterSource();

			params.addValue(WHR1, grupo.getCia().getCodigoCompanhia());
			params.addValue(WHR2, grupo.getDepto().getCodigoDepartamento());

			List<Map<String, Object>> lista = getJdbcTemplate().queryForList(
					QuerysDepi.GRUPOACESSO_REFERENCIADO_PARAMETRODEPOSITO,
					params);
			//			List<GrupoAcessoVO> grupoAcessoVO = getJdbcTemplate().query(
//					QuerysDepi.GRUPOACESSO_REFERENCIADO_PARAMETRODEPOSITO, params, new GrupoAcessoDataMapper());

            return (!lista.isEmpty());

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

			GrupoAcessoVO grupoAcessoVO = getJdbcTemplate().queryForObject(query.toString(), params, new GrupoAcessoDataMapper());
			
	        return grupoAcessoVO; 

        } finally {
        	LOGGER.info("obterGrupoPorChave(GrupoAcessoVO grupo)"); 
        }
    }
    
    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.dao.GrupoAcessoDAO#associacaoReferenciada(br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO, br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO)
     */
    @Override
    public boolean associacaoReferenciada(CompanhiaSeguradoraVO companhia,
    		DepartamentoVO departamentoVO) {
    	
    	MapSqlParameterSource params = new MapSqlParameterSource();
    	params.addValue(WHR1, departamentoVO.getCodigoDepartamento());
    	params.addValue(WHR2, companhia.getCodigoCompanhia());
    	
		try {
			CodigoIndicativoVO vo = getJdbcTemplate().queryForObject(
					QuerysDepi.GRUPOACESSO_EXISTS, params,
					new GrupoAcessoDataMapper.CodigoIndicativo());
			
			return vo.getIndicativo().equals(ConstantesDEPI.INDICADOR_ATIVO);
			
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
    }

}