package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoVO;

/**
 * Interface de ParametroDepositoDAODB2
 * @author igor.almeida
 */

public interface ParametroDepositoDAO  {

    /**
     * Método de obter por filtro
     * @param filtro parâmetro depósito com o código do objeto requisitado
     * @param codigoUsuario - Integer.
     * @return List<ParametroDepositoVO>
     */
    List<ParametroDepositoVO> obterPorFiltroComRestricaoDeGrupoAcesso(FiltroUtil filtro, Integer codigoUsuario);

    /**
     * isReferenciado. 
     * Se a tabela possui dependência.
     * @return Boolean.
     * @param vo - ParametroDepositoVO.
     */
    Boolean isReferenciadoDeposito(ParametroDepositoVO vo);

    /**
     * Alterar Parâmetro
     * @param parametro - ParametroDepositoVO.
     * @param referenciado - boolean.
     */
    public void alterar(ParametroDepositoVO parametro , boolean referenciado);
    
    /**
     * Inserir registro de ParametroDepositoVO
     * @param parametro de ParametroDepositoVO
     */
    public void inserir(ParametroDepositoVO parametro) ;

	Boolean isReferenciado(ParametroDepositoVO vo);

	void excluir(ParametroDepositoVO vo);

	ParametroDepositoVO obterPorChave(ParametroDepositoVO parametro);

	/**
	 * Verificar se parâmetro é referenciado
	 * @param companhia Companhia
	 * @param departamentoVO Departamento
	 * @return true quando referenciado
	 */
	boolean associacaoReferenciada(CompanhiaSeguradoraVO companhia,
			DepartamentoVO departamentoVO);

}
