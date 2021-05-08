package br.com.bradseg.depi.depositoidentificado.dao.delagate;

import java.util.List;

import br.com.bradseg.bucb.servicos.model.pessoa.vo.ListarPessoaPorFiltroSaidaVO;
import br.com.bradseg.depi.depositoidentificado.vo.PessoaVO;

/**
 * 
 * Interface para acessar métodos legados do BUCB
 * 
 * @author Globality
 */
public interface BUCBBusinessDelegate {

	/**
	 * Listar Pessoa por filtro.
	 * 
	 * @param ipCliente
	 *            - String
	 * @param userID
	 *            - String
	 * @param codigoPessoa
	 *            - long
	 * @return ListarPessoaIDVO
	 */
	public PessoaVO obterDadosPessoa(String ipCliente, int userID,
			long codigoPessoa);

    /**
	 * Listar Pessoa por filtro.
	 * 
	 * @param ipCliente
	 *            - String.
	 * @param userID
	 *            - String.
	 * @param cpfCnpj
	 *            - CPF com 11 caracteres ou CNPJ com 14 caracteres
	 * @return Lista de pessoas encontradas
	 */
	public List<ListarPessoaPorFiltroSaidaVO> listarPessoaPorFiltro(
			String ipCliente, int userID, String cpfCnpj);

}
