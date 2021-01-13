package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoVO;

/**
 * Interface de ParametroDepositoDAODB2
 * @author igor.almeida
 */

public interface ParametroDepositoDAO  {

    /**
     * Método de obter por filtro
     * @param filtro parâmetro depósito com o código do objeto requisitado
     * @return List<ParametroDepositoVO>
     */
    List<ParametroDepositoVO> obterPorFiltro(FiltroUtil filtro);

    /**
     * Método de obter por filtro
     * @param filtro parâmetro depósito com o código do objeto requisitado
     * @param codigoUsuario - BigDecimal.
     * @return List<ParametroDepositoVO>
     */
    List<ParametroDepositoVO> obterPorFiltroComRestricaoDeGrupoAcesso(FiltroUtil filtro, Double codigoUsuario);

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
}
