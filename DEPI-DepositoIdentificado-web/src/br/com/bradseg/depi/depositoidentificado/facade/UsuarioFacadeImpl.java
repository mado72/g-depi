/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bradseg.depi.depositoidentificado.dao.UsuarioDAO;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.UsuarioVO;

/**
 * Implementa {@link UsuarioFacade} 
 * @author Marcelo Damasceno
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class UsuarioFacadeImpl implements UsuarioFacade {
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.UsuarioFacade#obterPorFiltro(br.com.bradseg.depi.depositoidentificado.util.FiltroUtil)
	 */
	@Override
	public List<UsuarioVO> obterPorFiltro(FiltroUtil filtro) {
		return usuarioDAO.obterPorFiltro(filtro);
	}

}
