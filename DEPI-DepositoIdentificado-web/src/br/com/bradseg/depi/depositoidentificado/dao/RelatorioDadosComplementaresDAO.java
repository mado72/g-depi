package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioDadosComplementaresVO;

/**
 * RelatorioDadosComplementaresDAO
 * @author vitor.santiago
 */
public interface RelatorioDadosComplementaresDAO {
	/**
     * M�todo obterDadosComplementaresAnalitico
     * @param filtro do relat�rio
     * @throws IntegrationException - trata erro de neg�cio
     * @return List<DadosEnvioRetornoAnaliticoVO>
     */
    List<RelatorioDadosComplementaresVO> obterDadosComplementaresAnalitico(FiltroUtil filtro);
}
