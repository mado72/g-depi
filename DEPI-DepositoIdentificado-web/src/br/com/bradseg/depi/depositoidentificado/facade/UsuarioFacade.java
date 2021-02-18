/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.UsuarioVO;

/**
 * Facade para entidade Usuario
 * @author Marcelo Damasceno
 */
public interface UsuarioFacade {

	/**
	 * Busca funcionários atendendo aos critérios de filtro
	 * @param filtro Filtro com os critérios
	 * @return Registros encontrados
	 */
	List<UsuarioVO> obterPorFiltro(FiltroUtil filtro);

}
