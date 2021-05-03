package br.com.bradseg.depi.depositoidentificado.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoSinteticoVO;

/**
 * RelatorioExtratoDAO
 * @author fabio.pimentel
 */
public interface RelatorioExtratoDAO {

    /**
     * Método obterDadosAnalitico
     * @param filtro do relatório
     * @throws SQLException erro na consulta
     * @return List<RelatorioExtratoAnaliticoVO>
     */
    List<RelatorioExtratoAnaliticoVO> obterDadosAnalitico(FiltroUtil filtro) throws SQLException;

    /**
     * Método obterDadosSintetico
     * @param filtro do relatório
     * @throws SQLException erro na consulta
     * @return List<RelatorioExtratoSinteticoVO>
     */
    List<RelatorioExtratoSinteticoVO> obterDadosSintetico(FiltroUtil filtro) throws SQLException;

}