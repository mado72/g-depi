/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;

/**
 * Implementa os métodos de {@link ContaCorrenteDAO}
 * @author Marcelo Damasceno
 */
@Repository
public class ContaCorrenteDAOImpl implements ContaCorrenteDAO {

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.ContaCorrenteDAO#incluir(br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO)
	 */
	@Override
	public void incluir(ContaCorrenteAutorizadaVO vo) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.ContaCorrenteDAO#alterar(br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO)
	 */
	@Override
	public void alterar(ContaCorrenteAutorizadaVO vo) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.ContaCorrenteDAO#excluir(br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO)
	 */
	@Override
	public void excluir(ContaCorrenteAutorizadaVO vo) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.ContaCorrenteDAO#isReferenciado(br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO)
	 */
	@Override
	public boolean isReferenciado(ContaCorrenteAutorizadaVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.ContaCorrenteDAO#obterPorFiltroComRestricao(int, br.com.bradseg.depi.depositoidentificado.util.FiltroUtil)
	 */
	@Override
	public List<ContaCorrenteAutorizadaVO> obterPorFiltroComRestricao(
			int codUsuario, FiltroUtil filtro) {
		// TODO Auto-generated method stub
		return null;
	}

}
