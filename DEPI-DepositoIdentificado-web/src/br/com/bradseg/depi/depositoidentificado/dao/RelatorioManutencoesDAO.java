package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.ManutencoesAnaliticoVO;


/** 
 * A(O) RelatorioManutencoesDAO.
 * @param RelatorioManutencoesDAO
 */
public interface RelatorioManutencoesDAO {

    /* Método obterDadosAnalitico
     * @param filtro do relatório
     * @return List<ManutencoesAnaliticoVO>
     */
    public List<ManutencoesAnaliticoVO> obterDadosAnalitico(FiltroUtil filtro);
	
}
