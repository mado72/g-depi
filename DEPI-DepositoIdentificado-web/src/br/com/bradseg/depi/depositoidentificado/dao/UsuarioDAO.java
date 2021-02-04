package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.GrupoAcessoVO;
import br.com.bradseg.depi.depositoidentificado.vo.UsuarioVO;


/** 
 * A(O) UsuarioDAO.
 * @param UsuarioDAO
 */
public interface UsuarioDAO {
	
    /**
     * Método de obter por filtro um Usu�rio
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
    	

}
