package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.ManutencoesAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioDadosComplementaresVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoSinteticoVO;

public interface RelatorioDao {

	/**
	 * Obter dados de envio retorno
	 * @param filtro filtro
	 * @return resultado da consulta
	 */
	List<RelatorioEnvioRetornoVO> obterDadosEnvioRetorno(FiltroUtil filtro);

	/**
	 * Obter dados de extrato analítico
	 * @param filtro filtro
	 * @return resultado da consulta
	 */
	List<RelatorioExtratoAnaliticoVO> obterDadosExtratoAnalitico(FiltroUtil filtro);

	/**
	 * Obter dados de extrato sintético
	 * @param filtro filtro
	 * @return resultado da consulta
	 */
	List<RelatorioExtratoSinteticoVO> obterDadosExtratoSintetico(
			FiltroUtil filtro);

	/**
	 * Obter dados de manutenções analítico
	 * @param filtro filtro
	 * @return resultado da consulta
	 */
	List<ManutencoesAnaliticoVO> obterDadosManutencoesAnalitico(
			FiltroUtil filtro);
	
	/**
	 * Obter dados completares
	 * @param filtro filtro
	 * @return resultado da consulta
	 */
	List<RelatorioDadosComplementaresVO> obterDadosComplementaresAnalitico(
			FiltroUtil filtro);

}
