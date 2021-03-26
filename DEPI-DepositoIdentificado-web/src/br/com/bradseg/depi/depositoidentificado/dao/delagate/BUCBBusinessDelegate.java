package br.com.bradseg.depi.depositoidentificado.dao.delagate;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.bradseg.bsad.framework.core.exception.BusinessException;
import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.bucb.servicos.model.ejb.PessoaSessionFacade;
import br.com.bradseg.bucb.servicos.model.pessoa.vo.InserirPessoaEntradaVO;
import br.com.bradseg.bucb.servicos.model.pessoa.vo.InserirPessoaSaidaVO;
import br.com.bradseg.bucb.servicos.model.pessoa.vo.ListarPessoaIDVO;
import br.com.bradseg.bucb.servicos.model.pessoa.vo.ListarPessoaPorFiltroEntradaVO;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;

/**
 * Classe BUCBBusinessDelegate
 * @author Globality
 */
public class BUCBBusinessDelegate   {

	@Autowired
	private PessoaSessionFacade pessoaSessionFacade;

		public void setPessoaSessionFacade(PessoaSessionFacade pessoaSessionFacade) {
			this.pessoaSessionFacade = pessoaSessionFacade;
		}

	    /**
	     * Listar Pessoa por filtro.
	     * @param ipCliente - String.
	     * @param userID - String.
	     * @param pessoa - ListarPessoaPorFiltroEntradaVO.
	     * @return List.
	     * @throws RemoteException 
	     * @throws br.com.bradseg.bsad.exception.IntegrationException 
	     * @throws br.com.bradseg.bsad.exception.BusinessException 
	     */
		@SuppressWarnings("deprecation")
		public List<?> listarPessoaPorFiltro(String ipCliente, String userID, ListarPessoaPorFiltroEntradaVO pessoa) {
	    	try {
				return pessoaSessionFacade.listarPessoaPorFiltro(ipCliente, userID, pessoa);
			} catch (br.com.bradseg.bsad.exception.IntegrationException e) {
				throw new DEPIIntegrationException(e);
			} catch (br.com.bradseg.bsad.exception.BusinessException e) {
				throw new DEPIIntegrationException(e);
			} catch (RemoteException e) {
				throw new DEPIIntegrationException(e);
			}
	    }

	    /**
	     * Inserir Pessoa - BUCB.
	     * @param ipCliente - String.
	     * @param userID - String.
	     * @param pessoa - InserirPessoaSaidaVO.
	     * @return InserirPessoaSaidaVO.
	     * @throws br.com.bradseg.bsad.exception.BusinessException 
	     * @throws br.com.bradseg.bsad.exception.IntegrationException
         * @throws BucValidacaoCamposException - BucValidacaoCamposException.
	     */
		@SuppressWarnings("deprecation")
		public InserirPessoaSaidaVO inserirPessoa(String ipCliente, String userID, InserirPessoaEntradaVO pessoa)  {
	        try {
	            return pessoaSessionFacade.inserirPessoa(ipCliente, userID, pessoa);
			} catch (RemoteException e) {
				throw new IntegrationException(e);
			} catch (br.com.bradseg.bsad.exception.IntegrationException e) {
				// TODO Auto-generated catch block
				throw new IntegrationException(e);
			} catch (br.com.bradseg.bsad.exception.BusinessException e) {
				// TODO Auto-generated catch block
				throw new BusinessException(e);
			}
	    }

	    /**
	     * Listar Pessoa por filtro.
	     * @param ipCliente - String
	     * @param userID - String
	     * @param codigoPessoa - long
	     * @return ListarPessoaIDVO
	     * @throws br.com.bradseg.bsad.exception.IntegrationException 
	     * @throws br.com.bradseg.bsad.exception.BusinessException 
	     */
		@SuppressWarnings("deprecation")
		public ListarPessoaIDVO listarDadosPessoa(String ipCliente, String userID, long codigoPessoa)  {
	        ListarPessoaIDVO p = new ListarPessoaIDVO();
	        p.setCodigoPessoa(codigoPessoa);
	        try {
	        	return pessoaSessionFacade.listarPessoaPorID(ipCliente, userID, 1L, codigoPessoa, 0L, "");
	        } catch (RemoteException e) {
	            throw new IntegrationException(e);
	        } catch (br.com.bradseg.bsad.exception.IntegrationException e) {
				// TODO Auto-generated catch block
	            throw new IntegrationException(e);
			} catch (br.com.bradseg.bsad.exception.BusinessException e) {
				// TODO Auto-generated catch block
				throw new BusinessException(e);
			}
	    }	
	
	
}  
