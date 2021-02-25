package br.com.bradseg.depi.depositoidentificado.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioDadosComplementaresVO;

/**
 * RelatorioDadosComplementaresDAO
 * @author vitor.santiago
 */
public interface RelatorioDadosComplementaresDAO{
	/**
     * Método obterDadosComplementaresAnalitico
     * @param filtro do relatório
     * @throws IntegrationException - trata erro de neg�cio
     * @return List<DadosEnvioRetornoAnaliticoVO>
     */
    List<RelatorioDadosComplementaresVO> obterDadosComplementaresAnalitico(FiltroUtil filtro) throws SQLException ;
}
