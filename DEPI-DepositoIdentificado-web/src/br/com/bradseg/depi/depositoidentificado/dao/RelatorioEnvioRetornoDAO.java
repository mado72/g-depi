package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoSinteticoVO;

/**
 * RelatorioEnvioRetornoDAO
 * @author igor.almeida
 */

public interface RelatorioEnvioRetornoDAO {
    /**
     * M�todo obterDadosAnalitico
     * @param filtro do relat�rio
     * @throws DEPIIntegrationException - trata erro de neg�cio
     * @return List<DadosEnvioRetornoAnaliticoVO>
     */
    List<RelatorioEnvioRetornoAnaliticoVO> obterDadosAnalitico(FiltroUtil filtro);

    /**
     * M�todo obterDadosSintetico
     * @param filtro do relat�rio
     * @throws DEPIIntegrationException - trata erro de neg�cio
     * @return List<DadosEnvioRetornoSinteticoVO>
     */
    List<RelatorioEnvioRetornoSinteticoVO> obterDadosSintetico(FiltroUtil filtro);

}