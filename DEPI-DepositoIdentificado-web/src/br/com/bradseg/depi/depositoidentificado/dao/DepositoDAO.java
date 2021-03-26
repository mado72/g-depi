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
 */
public interface DepositoDAO{

    /**
	 * Método responsável por atualizar um registro de Depósito sempre que uma
	 * inserção/atualização for efetuada em manter movimento ou controle
	 * deposito
	 * 
	 * @param deposito
	 *            DepositoVO.
	 * @param param
	 *            ParametroDepositoVO.
	 */
    void atualizar(DepositoVO deposito, ParametroDepositoVO param);

    /**
     * Inclui um registro de Depósito
     * @param deposito DepositoVO.
     * @param param ParametroDepositoVO.
     */
    void inserir(DepositoVO deposito, ParametroDepositoVO param);

    /**
     * Método para consultar por filtro
     * @param filtro parâmetro depósito com o código do objeto requisitado
     * @param codigoUsuario Código do usuário
     * @return List<DepositoVO>
     */
    List<DepositoVO> obterPorFiltroComRestricaoDeGrupoAcesso(FiltroUtil filtro, Integer codigoUsuario);

    /**
	 * Atualiza um registro de Depósito definindo a situação do arquivo e a data
	 * de prorrogação
	 * 
	 * @param deposito
	 *            DepositoVO.
	 */
    void prorrogar(DepositoVO deposito);

    /**
     * Cancela um Depósito
     * @param deposito - DepositoVO.
     */
    void cancelar(DepositoVO deposito);

    /**
     * Método de obter depósito por motivo
     * @param vo - MotivoDepositoVO vo
     * @return List<DepositoVO>
     */
    DepositoVO obterPorMotivo(MotivoDepositoVO vo);
    
    /**
     * Método de obter depósito por departamento
     * @param vo - DepartamentoVO vo 
     * @return DepositoVO
     */
    DepositoVO obterPorContaCorrente(ContaCorrenteAutorizadaVO vo);
    
    /**
     * Método de obter depósito por departamento
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
     * Método de verificar lançamento do depósito
     * @param deposito - DepositoVO vo 
     * @return Boolean
     */
    long obterSituacaoDeposito(DepositoVO deposito);
    /**
     * Método de verificar lançamento do depósito
     * @param deposito - DepositoVO vo 
     * @return Boolean
     */
    boolean verificarLancamentoDeposito(DepositoVO deposito);
 
    /**
     * Método de verificar lançamento do depósito
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
	 * @param vo Depósito a inserir
	 */
	void inserir(DepositoVO vo);

    /**
     * Método Excluir deposito 
	 * @param vo Depósito a excluir
	 */
	void excluir(DepositoVO vo);

	/**
	 * Método para inserir um DV no depósito inserido
	 * @param vo Depósito inserido
	 */
	void inserirDV(DepositoVO vo);

}

   
