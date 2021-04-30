package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoVO;

/**
 * RelatorioEnvioRetornoDAO
 */

public interface RelatorioEnvioRetornoDAO {
    /**
     * ObterDados de envio
     * @param filtro do relatório
     * @return List<DadosEnvioRetornoAnaliticoVO>
     */
    List<RelatorioEnvioRetornoVO> obterDados(FiltroUtil filtro);

}