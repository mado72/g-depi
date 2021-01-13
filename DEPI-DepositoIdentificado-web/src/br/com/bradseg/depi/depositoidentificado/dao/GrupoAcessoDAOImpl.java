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
     * Inserir registro de GrupoAcessoVO
     * @param vo de GrupoAcessoVO
     */
    @Override
    public void inserir(GrupoAcessoVO vo) {
    	
    	StringBuilder query = new StringBuilder(QuerysDepi.GRUPOACESSO_EXISTSATIVO);

    	try {

			MapSqlParameterSource params = new MapSqlParameterSource();

			params.addValue("whr1", vo.getDepto().getCodigoDepartamento());
			params.addValue("whr2", vo.getCia().getCodigoCompanhia());

			List<GrupoAcessoVO> grupoAcessoVO = (List<GrupoAcessoVO>) getJdbcTemplate() .query(query.toString(), params, new GrupoAcessoDataMapper());

            if (!grupoAcessoVO.isEmpty()) {

                GrupoAcessoVO grupoCadastrado = obterGrupoPorChave(new GrupoAcessoVO(grupoAcessoVO.get(0).getCodigoGrupoAcesso()));

                throw new IntegrationException( 
                		new StringBuilder(ConstantesDEPI.ERRO_CUSTOMIZADA)
                				.append(" Grupo de Acesso: ")
                				.append(grupoCadastrado.getCodigoGrupoAcesso())
                				.append(" - ").append(grupoCadastrado.getNomeGrupoAcesso())
                				.append(" já cadastrado para a Cia: ")
                				.append(grupoCadastrado.getCia().getCodigoCompanhia())
                				.append(" e Departamento: ")
                				.append(grupoCadastrado.getDepto().getSiglaDepartamento()).toString());
            }
            
            StringBuilder queryInsert = new StringBuilder(QuerysDepi.GRUPOACESSO_INSERT);

			MapSqlParameterSource paramsInsert = new MapSqlParameterSource();

            
			paramsInsert.addValue("prm1", vo.getDepto().getCodigoDepartamento());
			paramsInsert.addValue("prm2", vo.getCia().getCodigoCompanhia());
			paramsInsert.addValue("prm3", ""); // ----> vo.getCodigoResponsavelUltimaAtualizacao);
			
			int count = getJdbcTemplate() .update(queryInsert.toString(), paramsInsert);

            if (count > 0) {
                vo.setCodigoGrupoAcesso(grupoAcessoVO.get(0).getCodigoGrupoAcesso());
            } else {
                throw new IntegrationException(ConstantesDEPI.ERRO_CUSTOMIZADA + " - " + "Não foi possível incluir o Grupo de Acesso.");
            }

            StringBuilder queryAlocar = new StringBuilder(QuerysDepi.GRUPOACESSO_ALOCARFUNCIONARIO);
            MapSqlParameterSource paramsAlocar = new MapSqlParameterSource();
		
            for (UsuarioVO usuario : vo.getUsuarios()) {

            	paramsAlocar.addValue("prm1", vo.getCodigoGrupoAcesso());
            	paramsAlocar.addValue("prm2", usuario.getCodigoUsuario());
            	paramsAlocar.addValue("prm3", ""); // ----> vo.getCodigoResponsavelUltimaAtualizacao);

    			getJdbcTemplate() .update(queryAlocar.toString(), paramsAlocar);
            	
            } 
        } finally { 
        	LOGGER.info("inserir(GrupoAcessoVO vo)");
        }
    }

    /**
     * Atualizar grupo de acesso. Obtém os usuários. Para cada usuário obtido, verificar se o mesmo existe na nova lista. Para cada
     * usuário obtivo, inclui ou exclui de acordo com a existência na nova lista de usuários. | *
     * @param vo - GrupoAcessoVO.
     */
    @Override
    public void alterar(GrupoAcessoVO vo) {
    	/*beginMethod(LOGGER, "alterar(GrupoAcessoVO vo)");
        DataSource ds;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {

            List<UsuarioVO> usuariosAtuais = BaseDAOFactoryDB2.getBaseDAOFactory().createUsuarioDAO().obterPorGrupoAcesso(vo);

            //**
            // * Proteção da rotina.
            // *
            if (vo.getCodigoGrupoAcesso() <= 0) {
                throw new DEPIIntegrationException("Código Grupo de Acesso inválido na atualização.");
            }

            if (vo.getUsuarios() == null) {
                throw new DEPIIntegrationException("Lista de usuários inválida na atualização.");
            }

            if (vo.getCodigoResponsavelUltimaAtualizacao().doubleValue() <= 0) {
                throw new DEPIIntegrationException("Código do responsável pela última atualização inválido na atualização.");
            }

            ds = getDAO().getDataSource();
            conn = ds.getConnection();

            //**
            //* desaloca usuários.
            //*
            boolean encontrado = false;
            for (UsuarioVO usuarioAtual : usuariosAtuais) {
                //**
                // * Entre os novos usuários, existem os que já estavam no grupo ?
                //*
                for (UsuarioVO novoUsuario : vo.getUsuarios()) {
                    if (usuarioAtual.equals(novoUsuario)) {
                        encontrado = true;
                        break;
                    }
                }

                if (!encontrado) {
                    pstm = conn.prepareStatement(this.getSQL("grupoAcesso.desalocarFuncionario"));
                    pstm.setObject(1, vo.getCodigoResponsavelUltimaAtualizacao());
                    pstm.setInt(2, vo.getCodigoGrupoAcesso());
                    pstm.setInt(3, usuarioAtual.getCodigoUsuario());
                    pstm.executeUpdate();
                }
                encontrado = false;
            }

            //**
            //* Alocação dos usuários novos.
            //*
            for (UsuarioVO usr : vo.getUsuarios()) {

                pstm = conn.prepareStatement(this.getSQL("alocacao.existsUsuario"));
                pstm.setInt(1, vo.getCodigoGrupoAcesso());
                pstm.setInt(2, usr.getCodigoUsuario());

                rs = pstm.executeQuery();
                if (rs.next()) {
                    if (ConstantesDEPI.INDICADOR_INATIVO.toString().equals(rs.getString(1).toString())) {
                        pstm = conn.prepareStatement(this.getSQL("grupoAcesso.realocarFuncionario"));
                        pstm.setObject(1, vo.getCodigoResponsavelUltimaAtualizacao());
                        pstm.setInt(2, vo.getCodigoGrupoAcesso());
                        pstm.setInt(3, usr.getCodigoUsuario());
                        pstm.executeUpdate();
                    }
                } else {
                    pstm = conn.prepareStatement(this.getSQL("grupoAcesso.alocarFuncionario"));
                    pstm.setInt(1, vo.getCodigoGrupoAcesso());
                    pstm.setInt(2, usr.getCodigoUsuario());
                    pstm.setObject(3, vo.getCodigoResponsavelUltimaAtualizacao());
                    pstm.executeUpdate();
                }
            }

            pstm = conn.prepareStatement(this.getSQL("grupoAcesso.update"));
            pstm.setObject(1, vo.getCodigoResponsavelUltimaAtualizacao());
            pstm.setInt(2, vo.getCodigoGrupoAcesso());
            pstm.executeUpdate();

        } catch (DAOException pe) {
        	LOGGER.error(pe);
            tratarExcecao(pe);
        } catch (SQLException pe) {
        	LOGGER.error(pe);
            tratarExcecao(pe);
        } finally {
        	closeResultSet(rs); 
        	closeStatement(pstm);
        	closeConnection(conn);
        	endMethod(LOGGER, "alterar(GrupoAcessoVO vo)"); 
        }*/
    }

    /**
     * Método de obter por filtro um Grupo de Usuário
     * @param filtro - filtro CriterioFiltroUtil
     * @return List - Lista de GrupoAcessoVO
     */
    public List<GrupoAcessoVO> obterPorFiltro(FiltroUtil filtro)  {
		return null;
/*        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        StringBuilder sql = new StringBuilder(this.getSQL("grupoAcesso.obterPorFiltroNew"));
        
        GrupoAcessoVO grupoAcessoVO = null;
        List<GrupoAcessoVO> listaGrupoAcessoVO = new ArrayList<GrupoAcessoVO>();
        try {

            if (filtro != null) {
                sql = new StringBuilder(sql.toString().replace("{0}", filtro.getCriterioWithOperatorAnd()));
            } else {
                sql = new StringBuilder(sql.toString().replace("{0}", ""));
            }
            
            conn = getDAO().getDataSource().getConnection();
            pstm = conn.prepareStatement(sql.toString());

            rs = pstm.executeQuery();
            
            while (rs.next()) {
                grupoAcessoVO = new GrupoAcessoVO();
                
                grupoAcessoVO.setCodigoGrupoAcesso(Integer.valueOf(rs.getString("CGRP_DEPTO_DEP")));
                grupoAcessoVO.setCia(new CompanhiaSeguradoraVO(Integer.valueOf(rs.getString("CINTRN_CIA_SEGDR"))));
                grupoAcessoVO.setDepto(new DepartamentoVO(Integer.valueOf(rs.getString("CDEPTO_DEP_IDTFD")), rs.getString("IDEPTO_DEP_IDTFD"), rs.getString("CSGL_DEPTO_DEP")));
                grupoAcessoVO.setCodigoIndicativoAtivo(rs.getString("CIND_REG_ATIVO"));
                grupoAcessoVO.setCodigoResponsavelUltimaAtualizacao(new BigDecimal(rs.getString("CUSUAR_RESP_ATULZ")));
                grupoAcessoVO.setDataInclusao(rs.getTimestamp("DHORA_INCL_REG"));
                grupoAcessoVO.setDataHoraAtualizacao(rs.getTimestamp("DHORA_ULT_ATULZ"));
                
                listaGrupoAcessoVO.add(grupoAcessoVO);
           
            }

        } catch (SQLException e) {
        	LOGGER.error(e);
            tratarExcecao(e);
        } catch (DAOException e) {
        	LOGGER.error(e);
            tratarExcecao(e);
        } finally {
        	closeResultSet(rs);
        	closeStatement(pstm);
        	closeConnection(conn);
        	endMethod(LOGGER, "obterPorFiltro(CriterioFiltroUtil filtro)"); 
        }
        return listaGrupoAcessoVO;*/
    }

    /**
     * {@inheritDoc}
     */
    public void desalocarUsuarios(GrupoAcessoVO grupo) {
  /*      grupo.setUsuarios(new ArrayList<UsuarioVO>());
    	beginMethod(LOGGER, "desalocarUsuarios(GrupoAcessoVO grupo)"); 
        try {
	        **
	         * Alterar Desaloca usuários na alteração, pois a lista de usuários não é enviada.
	         *
	        alterar(grupo); 
        } finally {
    	  endMethod(LOGGER, "desalocarUsuarios(GrupoAcessoVO grupo)");
        } */
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void excluir(GrupoAcessoVO grupo) {
    	/*beginMethod(LOGGER, "excluir(GrupoAcessoVO grupo)"); 
        DataSource ds;
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            ds = getDAO().getDataSource();
            conn = ds.getConnection();

            **
             * Desalocar Usuários.
             *
            desalocarUsuarios(grupo);

            pstm = conn.prepareStatement(this.getSQL("grupoAcesso.desativar"));
            pstm.setObject(1, grupo.getCodigoResponsavelUltimaAtualizacao());
            pstm.setInt(2, grupo.getCodigoGrupoAcesso());
            pstm.executeUpdate();

        } catch (SQLException e) {
        	LOGGER.error(e);
            tratarExcecao(e);
        } catch (DAOException e) {
        	LOGGER.error(e);
            tratarExcecao(e);
        } finally {
        	closeStatement(pstm);
        	closeConnection(conn);
        	endMethod(LOGGER, "excluir(GrupoAcessoVO grupo)"); 
        }*/
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized Boolean isReferenciado(GrupoAcessoVO grupo) {
		return null;
    	/*beginMethod(LOGGER, "isReferenciado(GrupoAcessoVO grupo)"); 
        DataSource ds;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            ds = getDAO().getDataSource();
            conn = ds.getConnection();

            pstm = conn.prepareStatement(this.getSQL("grupoAcesso.referenciado.parametroDeposito"));
            pstm.setInt(1, grupo.getCia().getCodigoCompanhia());
            pstm.setInt(2, grupo.getDepto().getCodigoDepartamento());
            rs = pstm.executeQuery();
            return (rs.next());

        } catch (SQLException e) {
        	LOGGER.error(e);
            throw new DEPIIntegrationException(e);
        } catch (DAOException e) {
        	LOGGER.error(e);
            throw new DEPIIntegrationException(e);
        } finally {
        	//Verificar MHG
        	closeResultSet(rs); 
        	closeStatement(pstm);
        	closeConnection(conn);
        	endMethod(LOGGER, "isReferenciado(GrupoAcessoVO grupo)"); 
        } */
    }

    public GrupoAcessoVO obterGrupoPorChave(GrupoAcessoVO grupo)  {
		return null;
/*        beginMethod(LOGGER, "obterGrupoPorChave(GrupoAcessoVO grupo)"); 
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            conn = getDAO().getDataSource().getConnection();
            pstm = conn.prepareStatement(this.getSQL("grupoAcesso.obterGrupoPorChave"));
            
            pstm.setInt(1, grupo.getCodigoGrupoAcesso());
            
            rs = pstm.executeQuery();
           
            while (rs.next()) {
                grupo.setDepto(new DepartamentoVO(Integer.valueOf(rs.getString("CDEPTO_DEP_IDTFD")), rs.getString("IDEPTO_DEP_IDTFD"), rs.getString("CSGL_DEPTO_DEP")));
                grupo.setCia(new CompanhiaSeguradoraVO(Integer.valueOf(rs.getString("CINTRN_CIA_SEGDR"))));
                grupo.setCodigoIndicativoAtivo(rs.getString("CIND_REG_ATIVO"));
                
                grupo.setCodigoResponsavelUltimaAtualizacao(new BigDecimal(rs.getString("CUSUAR_RESP_ATULZ")));
                grupo.setDataInclusao(rs.getTimestamp("DHORA_INCL_REG"));
                grupo.setDataHoraAtualizacao(rs.getTimestamp("DHORA_ULT_ATULZ"));
            } 
            
        } catch (SQLException e) {
        	LOGGER.error(e);
            throw new DEPIIntegrationException(e);
        } catch (DAOException e) {
        	LOGGER.error(e);
            throw new DEPIIntegrationException(e);
        } finally {
        	closeResultSet(rs); 
        	closeStatement(pstm);
        	closeConnection(conn);
        	endMethod(LOGGER, "obterGrupoPorChave(GrupoAcessoVO grupo)"); 
        }
        
        return grupo; */
    }

}