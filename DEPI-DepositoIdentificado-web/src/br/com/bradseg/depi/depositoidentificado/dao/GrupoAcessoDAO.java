/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.GrupoAcessoVO;

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
     * Obtem o grupo de acesso por chave.
     * @param grupo - GrupoAcessoVO.
     */
	GrupoAcessoVO obterGrupoPorChave(GrupoAcessoVO grupo);

    /**
     * inserir o grupo de acesso por chave.
     * {@inheritDoc}
     */
	void inserir(GrupoAcessoVO vo);
    /**
     * alterar o grupo de acesso por chave.
     * {@inheritDoc}
     */
	void alterar(GrupoAcessoVO vo);
    /**
     * isReferenciado 
     * @param grupo - GrupoAcessoVO.
     */
	Boolean isReferenciado(GrupoAcessoVO grupo);
}
