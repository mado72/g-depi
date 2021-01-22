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
     * Método obterDadosAnalitico
     * @param filtro do relatório
     * @throws IntegrationException - trata erro de neg�cio
     * @return List<DadosEnvioRetornoAnaliticoVO>
     */
    List<RelatorioEnvioRetornoAnaliticoVO> obterDadosAnalitico(FiltroUtil filtro);

    /**
     * Método obterDadosSintetico
     * @param filtro do relatório
     * @throws IntegrationException - trata erro de neg�cio
     * @return List<DadosEnvioRetornoSinteticoVO>
     */
    List<RelatorioEnvioRetornoSinteticoVO> obterDadosSintetico(FiltroUtil filtro);

}