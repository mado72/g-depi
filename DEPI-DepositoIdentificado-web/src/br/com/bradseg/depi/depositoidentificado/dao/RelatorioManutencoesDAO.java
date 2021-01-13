package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.ManutencoesAnaliticoVO;


/** 
 * A(O) RelatorioManutencoesDAO.
 * @param RelatorioManutencoesDAO
 */
public interface RelatorioManutencoesDAO {

    /* M�todo obterDadosAnalitico
     * @param filtro do relat�rio
     * @return List<ManutencoesAnaliticoVO>
     */
    public List<ManutencoesAnaliticoVO> obterDadosAnalitico(FiltroUtil filtro);
	
}
