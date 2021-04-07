package br.com.bradseg.depi.depositoidentificado.dao;

import br.com.bradseg.depi.depositoidentificado.vo.DepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.LancamentoDepositoVO;


/**
 * Interface do Objeto LancamentoDepositoDAO.
 * @author Globality
 *
 */
public interface LancamentoDepositoDAO {

	LancamentoDepositoVO obterPorDeposito(DepositoVO vo);

}
