package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoSinteticoVO;

/**
 * RelatorioExtratoDAO
 * @author fabio.pimentel
 */
public interface RelatorioExtratoDAO {

    /**
     * M�todo obterDadosAnalitico
     * @param filtro do relat�rio
     * @throws DEPIIntegrationException trata erro de neg�cio
     * @return List<RelatorioExtratoAnaliticoVO>
     */
    List<RelatorioEnvioRetornoAnaliticoVO> obterDadosAnalitico(FiltroUtil filtro);

    /**
     * M�todo obterDadosSintetico
     * @param filtro do relat�rio
     * @throws DEPIIntegrationException trata erro de neg�cio
     * @return List<RelatorioExtratoSinteticoVO>
     */
    List<RelatorioExtratoSinteticoVO> obterDadosSintetico(FiltroUtil filtro);

}