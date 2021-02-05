/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.Collection;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.GrupoAcessoVO;
import br.com.bradseg.depi.depositoidentificado.vo.UsuarioVO;

/**
 * Interface de GrupoAcessoDAO
 */
public interface GrupoAcessoDAO {
	/**
     * Consulta usando filtro.
     * @param filtro - GrupoAcessoFiltroVO.
     * @return List.
     */
	List<GrupoAcessoVO> obterPorFiltro(FiltroUtil filtro);

	/**
     * Excluir Grupo Acesso.
     * @param grupo - GrupoAcessoVO.
     */
	void excluir(GrupoAcessoVO grupo);

	/**
     * Desalocar Usuários do Grupo Acesso.
     * @param grupo - GrupoAcessoVO.
     */
	void desalocarUsuarios(GrupoAcessoVO grupo);
	
    /**
	 * @param vo
	 * @param usuarios
	 */
	void desalocarUsuarios(GrupoAcessoVO vo, Collection<UsuarioVO> usuarios);

	/**
     * Obtem o grupo de acesso por chave.
     * @param grupo - GrupoAcessoVO.
     */
	GrupoAcessoVO obterGrupoPorChave(GrupoAcessoVO grupo);

	/**
	 * inserir o grupo de acesso por chave.
	 * @param vo Grupo Acesso
	 */
	void inserir(GrupoAcessoVO vo);
    /**
     * alterar o grupo de acesso por chave.
	 * @param vo Grupo Acesso
	 */
	void alterar(GrupoAcessoVO vo);
    /**
     * isReferenciado 
     * @param grupo - GrupoAcessoVO.
     */
	Boolean isReferenciado(GrupoAcessoVO grupo);
}
