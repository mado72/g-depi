package br.com.bradseg.depi.depositoidentificado.dao.delagate;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bradseg.bucb.servicos.model.ejb.PessoaSessionFacade;
import br.com.bradseg.bucb.servicos.model.exception.BucBusinessException;
import br.com.bradseg.bucb.servicos.model.pessoa.vo.ListarPessoaIDVO;
import br.com.bradseg.bucb.servicos.model.pessoa.vo.ListarPessoaPorFiltroEntradaVO;
import br.com.bradseg.bucb.servicos.model.pessoa.vo.ListarPessoaPorFiltroSaidaVO;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.vo.PessoaVO;

/**
 * Classe BUCBBusinessDelegate
 * 
 * @author Globality
 */
@Service
public class BUCBBusinessDelegateImpl implements BUCBBusinessDelegate {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(BUCBBusinessDelegateImpl.class);

	/*
	 * FIXME Verificar CÓDIGO para simular acesso EJB
	 */
	private static final boolean FAKE_COMP = String.valueOf(3).equals("3");

	@Autowired
	private PessoaSessionFacade pessoaSessionFacade;

	@Override
	public PessoaVO obterDadosPessoa(String ipCliente, int userID,
			long codigoPessoa) {
		
		if (FAKE_COMP) {
			return criarFake();
		}
		
		try {
			ListarPessoaIDVO dados = pessoaSessionFacade
					.listarPessoaPorID(ipCliente, String.valueOf(userID), 1L,
							codigoPessoa, 0L, "");
			
			long cpfCnpj;
			if (dados.getTipoPessoa() == ConstantesDEPI.TIPO_PESSOA_FISICA) {
				cpfCnpj = dados.getNumeroCpf();
			}
			else if (dados.getTipoPessoa() == ConstantesDEPI.TIPO_PESSOA_JURIDICA) {
				cpfCnpj = dados.getNumeroCnpj();
			}
			else {
				cpfCnpj = 0L;
			}
			
			return new PessoaVO(dados.getCodigoPessoa(), dados.getNomePessoa(),
					cpfCnpj, dados.getTipoPessoa());
		} catch (Exception e) {
			throw new DEPIIntegrationException(e);
		}
	}
	
	@Override
	public List<ListarPessoaPorFiltroSaidaVO> listarPessoaPorFiltro(
			String ipCliente, int userID, String cpfCnpj) {
		if (FAKE_COMP) {
        	List<ListarPessoaPorFiltroSaidaVO> lista;
        	long numeroCpfCnpj = Long.parseLong(cpfCnpj);
        	if (numeroCpfCnpj > 99999999999L) { // CNPJ
        		lista = Arrays.asList(
        				new ListarPessoaPorFiltroSaidaVO(123L, "Pessoa 123", numeroCpfCnpj, 0L, 0L),
        				new ListarPessoaPorFiltroSaidaVO(1002804669, "Pessoa 234", numeroCpfCnpj, 0L, 0L));
        		return lista;
        	}
    		lista = Arrays.asList(
    				new ListarPessoaPorFiltroSaidaVO(1002804669, "Pessoa 345", numeroCpfCnpj, 0L, 0L));
    		return lista;
		}
    	try {
    		final long numeroCpfCnpj = Long.parseLong(BaseUtil.retiraMascaraCNPJ(cpfCnpj));
    		
            ListarPessoaPorFiltroEntradaVO filtro = new ListarPessoaPorFiltroEntradaVO();
    		filtro.setCpfCgc(numeroCpfCnpj);
            filtro.setCodigoTipoPesquisa(1);
            filtro.setDataNascimento(0);
            
            if (String.valueOf(cpfCnpj).length() > 11) { // É cnpj
                filtro.setCodigoTipoPessoa(ConstantesDEPI.TIPO_PESSOA_JURIDICA);
            } else {
                filtro.setCodigoTipoPessoa(ConstantesDEPI.TIPO_PESSOA_FISICA);
            }
            
			@SuppressWarnings("unchecked")
			List<ListarPessoaPorFiltroSaidaVO> retorno = pessoaSessionFacade
					.listarPessoaPorFiltro(ipCliente, String.valueOf(userID), filtro);
			
			return retorno;
    	} catch (BucBusinessException e) {
    		if ("10".toString().equals(e.getCodigoErroNegocio().toString())) {
    			throw new DEPIIntegrationException("erro.bucb.cpfCnpj.inexistente", cpfCnpj);
    		} else {
    			LOGGER.error("Erro ao listarPessoaPorFiltro", e);
    			throw new DEPIIntegrationException(e);
    		}
		} catch (Exception e) {
			throw new DEPIIntegrationException(e);
		}
    }
	
	private PessoaVO criarFake() {
		return new PessoaVO(1L, "Nome Fake", 12345678901L, 3);
	}

}
