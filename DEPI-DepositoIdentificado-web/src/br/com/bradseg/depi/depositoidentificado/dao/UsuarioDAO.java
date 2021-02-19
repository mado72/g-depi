package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.GrupoAcessoVO;
import br.com.bradseg.depi.depositoidentificado.vo.UsuarioVO;


/** 
 * A(O) UsuarioDAO.
 */
public interface UsuarioDAO {
	
    /**
     * Método de obter por filtro um Usuário
     * @param vo - GrupoAcessoVO.
     * @return List<UsuarioVO>
     */
    public List<UsuarioVO> obterPorGrupoAcesso(GrupoAcessoVO vo);

	/**
	 * Busca os funcionários que atendam as critérios de pesquisa
	 * @param filtro Contém os critérios de pesquisa
	 * @return Lista dos registros encontrados
	 */
	List<UsuarioVO> obterPorFiltro(FiltroUtil filtro);

	/**
	 * Busca Funcionário pelo seu código
	 * @param codigoUsuario Código
	 * @return Usuário
	 */
	UsuarioVO obterPorCodigo(Integer codigoUsuario);

	/**
	 * Verifica se existe grupo acesso associado ao usuário
	 * @param codigoUsuario Código do usuário
	 * @return true quando existe.
	 */
	boolean existeGrupoAcessoUsuario(Integer codigoUsuario);
    	
}
