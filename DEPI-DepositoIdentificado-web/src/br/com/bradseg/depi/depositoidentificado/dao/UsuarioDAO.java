package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.vo.GrupoAcessoVO;
import br.com.bradseg.depi.depositoidentificado.vo.UsuarioVO;


/** 
 * A(O) UsuarioDAO.
 * @param UsuarioDAO
 */
public interface UsuarioDAO {
	
    /**
     * M�todo de obter por filtro um Usu�rio
     * @param vo - GrupoAcessoVO.
     * @return List<UsuarioVO>
     */
    public List<UsuarioVO> obterPorGrupoAcesso(GrupoAcessoVO vo);
    	

}
