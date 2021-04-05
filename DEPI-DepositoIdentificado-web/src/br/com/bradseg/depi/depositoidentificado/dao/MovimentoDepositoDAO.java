package br.com.bradseg.depi.depositoidentificado.dao;

import br.com.bradseg.depi.depositoidentificado.vo.MovimentoDepositoVO;

/**
 * Interface do Objeto MovimentoDepositoDAO.
 * @author Globality
 *
 */
public interface MovimentoDepositoDAO {

	void inserir(MovimentoDepositoVO vo);

	void alterar(MovimentoDepositoVO vo);

	MovimentoDepositoVO obterPorChave(MovimentoDepositoVO chave);

}
