package br.com.bradseg.depi.depositoidentificado.dao;

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
     * Método repons�vel pela atualizar um registro de Dep�sito sempre que uma insercao/atualizacao for efetuada em manter movimento
     * ou controle deposito
     * @param deposito - DepositoVO.
     * @param param - ParametroDepositoVO.
     */
    void atualizar(DepositoVO deposito, ParametroDepositoVO param);

    /**
     * Método repons�vel pela inclus�o de um registro de Dep�sito
     * @param deposito - DepositoVO.
     * @param param - ParametroDepositoVO.
     */
    void inserir(DepositoVO deposito, ParametroDepositoVO param);

    /**
     * Método de obter por filtro
     * @param filtro par�metro dep�sito com o código do objeto requisitado
     * @param codigoUsuario - BigDecimal.
     * @return List<DepositoVO>
     */
    List<DepositoVO> obterPorFiltroComRestricaoDeGrupoAcesso(FiltroUtil filtro, Integer codigoUsuario);

    /**
     * Método repons�vel pela atualiza��o normal de um registro de Dep�sito
     * @param deposito - DepositoVO.
     */
    void prorrogar(DepositoVO deposito);

    /**
     * Cancelar Dep�sito
     * @param deposito - DepositoVO.
     */
    void cancelar(DepositoVO deposito);

    /**
     * Método de obter dep�sito por motivo
     * @param vo - MotivoDepositoVO vo
     * @return List<DepositoVO>
     */
    DepositoVO obterPorMotivo(MotivoDepositoVO vo);
    
    /**
     * Método de obter dep�sito por departamento
     * @param vo - DepartamentoVO vo 
     * @return DepositoVO
     */
    DepositoVO obterPorContaCorrente(ContaCorrenteAutorizadaVO vo);
    
    /**
     * Método de obter dep�sito por departamento
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
     */
    long updateLog(DepositoVO deposito);

    /**
     * Método de verificar lan�amento do dep�sito
     * @param deposito - DepositoVO vo 
     * @return Boolean
     */
    long obterSituacaoDeposito(DepositoVO deposito);
    /**
     * Método de verificar lan�amento do dep�sito
     * @param deposito - DepositoVO vo 
     * @return Boolean
     */
    boolean verificarLancamentoDeposito(DepositoVO deposito);
 
    /**
     * Método de verificar lan�amento do dep�sito
     * @param deposito - DepositoVO vo 
     * @return boolean
     */
    boolean verificarEnvioArquivoTransferencia(DepositoVO deposito);
    
    /**
     * Método de obter deposito por chave
     * @param deposito - DepositoVO deposito 
     * @return boolean
     */
    DepositoVO obterDepositoPorChave (DepositoVO deposito);

    /**
     * Método inserir deposito 
     * {@inheritDoc}
     */
	void inserir(DepositoVO vo);
    /**
     * Método Alterar deposito 
     * {@inheritDoc} 
     */
	void alterar(DepositoVO vo) ;
    /**
     * Método Excluir deposito 
     * {@inheritDoc}
     */
	void excluir(DepositoVO vo);

	void inserirDV(DepositoVO vo);

}

   
