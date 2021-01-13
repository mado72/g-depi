package br.com.bradseg.depi.depositoidentificado.dao;

import java.math.BigDecimal;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.LogDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoVO;

/**
 * Interface do Objeto DepositoDAO.
 * @author lazaro
 */
public interface DepositoDAO{

    /**
     * M�todo repons�vel pela atualizar um registro de Dep�sito sempre que uma insercao/atualizacao for efetuada em manter movimento
     * ou controle deposito
     * @param deposito - DepositoVO.
     * @param param - ParametroDepositoVO.
     */
    void atualizar(DepositoVO deposito, ParametroDepositoVO param);

    /**
     * M�todo repons�vel pela inclus�o de um registro de Dep�sito
     * @param deposito - DepositoVO.
     * @param param - ParametroDepositoVO.
     */
    void inserir(DepositoVO deposito, ParametroDepositoVO param);

    /**
     * M�todo de obter por filtro
     * @param filtro par�metro dep�sito com o c�digo do objeto requisitado
     * @param codigoUsuario - BigDecimal.
     * @return List<DepositoVO>
     */
    List<DepositoVO> obterPorFiltroComRestricaoDeGrupoAcesso(FiltroUtil filtro, BigDecimal codigoUsuario);

    /**
     * M�todo repons�vel pela atualiza��o normal de um registro de Dep�sito
     * @param deposito - DepositoVO.
     */
    void prorrogar(DepositoVO deposito);

    /**
     * Cancelar Dep�sito
     * @param deposito - DepositoVO.
     */
    void cancelar(DepositoVO deposito);

    /**
     * M�todo de obter dep�sito por motivo
     * @param vo - MotivoDepositoVO vo
     * @return List<DepositoVO>
     */
    DepositoVO obterPorMotivo(MotivoDepositoVO vo);
    
    /**
     * M�todo de obter dep�sito por departamento
     * @param vo - DepartamentoVO vo 
     * @return DepositoVO
     */
    DepositoVO obterPorContaCorrente(ContaCorrenteAutorizadaVO vo);
    
    /**
     * M�todo de obter dep�sito por departamento
     * @param vo - DepartamentoVO vo 
     * @return DepositoVO
     */
    DepositoVO obterPorDepartamento(DepartamentoVO vo);
    
    /**
     * Registrar Log
     * @param oldObj - DepositoVO
     * @param newObj - DepositoVO
     * @param actionName - String
     * @return Generated Key
     */
    List<LogDepositoVO> registrarLogs(DepositoVO oldObj, DepositoVO newObj, String actionName);
    
    /**
     * Update Logs
     * @param deposito - DepositoVO 
     * @return Generated Key
     * @throws DEPIIntegrationException - DEPIIntegrationException.
     */
    long updateLog(DepositoVO deposito);

    /**
     * M�todo de verificar lan�amento do dep�sito
     * @param deposito - DepositoVO vo 
     * @throws DEPIIntegrationException - trata erro de neg�cio
     * @return Boolean
     */
    long obterSituacaoDeposito(DepositoVO deposito);
    /**
     * M�todo de verificar lan�amento do dep�sito
     * @param deposito - DepositoVO vo 
     * @return Boolean
     */
    boolean verificarLancamentoDeposito(DepositoVO deposito);
 
    /**
     * M�todo de verificar lan�amento do dep�sito
     * @param deposito - DepositoVO vo 
     * @return boolean
     */
    boolean verificarEnvioArquivoTransferencia(DepositoVO deposito);
    
    /**
     * M�todo de obter deposito por chave
     * @param deposito - DepositoVO deposito 
     * @return boolean
     */
    DepositoVO obterDepositoPorChave (DepositoVO deposito);

    /**
     * M�todo inserir deposito 
     * {@inheritDoc}
     */
	void inserir(DepositoVO vo);
    /**
     * M�todo Alterar deposito 
     * {@inheritDoc} 
     */
	void alterar(DepositoVO vo) ;
    /**
     * M�todo Excluir deposito 
     * {@inheritDoc}
     */
	void excluir(DepositoVO vo);

}

   
