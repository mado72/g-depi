package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.List;

import br.com.bradseg.bucb.servicos.model.pessoa.vo.ListarPessoaPorFiltroSaidaVO;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.AgenciaVO;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.EventoContabilVO;
import br.com.bradseg.depi.depositoidentificado.vo.ItemContabilVO;
import br.com.bradseg.depi.depositoidentificado.vo.LancamentoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MovimentoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoVO;

/**
 * Interface de serviço para atender ao cadastro para associar motivo depósito.
 */
public interface DepositoFacade {

	/**
	 * Retorna lista de associações de motivo depósito que atendem aos critérios de consulta
	 * @param codUsuario Código do usuário
	 * @param filtro Critérios de consulta
	 * @return Lista
	 */
	List<DepositoVO> obterPorFiltro(int codUsuario,
			FiltroUtil filtro);

	/**
	 * Obtém uma instância com base na chave primária
	 * @param vo Contém a chave primária
	 * @param codUsuario Código do usuário que realiza a consulta.
	 * @param ipCliente Endereço IP do cliente. 
	 * @return Entidade encontrada ou null
	 */
	DepositoVO obterPorChave(DepositoVO vo, int codUsuario, String ipCliente);

	/**
	 * Insere uma nova instância
	 * @param vo Dados para persistir
	 */
	void inserir(DepositoVO vo);

	/**
	 * Exclui uma lista de instâncias de {@link DepositoVO}
	 * @param voList Lista
	 */
	void excluirLista(List<DepositoVO> voList);

	/**
	 * Lista as companhias a que um usuário tem acesso
	 * @param codUsuario Código do usuário
	 * @return Lista de companhias
	 */
	List<CompanhiaSeguradoraVO> obterCompanhias(int codUsuario);

	/**
	 * Lista os departamentos de uma companhia a que o usuário tem acesso
	 * @param codUsuario Código do usuário
	 * @param ciaVO Companhia
	 * @return Lista de departamentos
	 */
	List<DepartamentoVO> obterDepartamentosComRestricaoDeposito(int codUsuario,
			CompanhiaSeguradoraVO ciaVO);

	/**
	 * Lista os motivos de depósito associados a uma relação departamento x Cia a que o usuário tem acesso
	 * @param codUsuario Código do usuário
	 * @param deptoCia Relação Departamento x Cia
	 * @return Lista de motivos de depósito
	 */
	List<MotivoDepositoVO> obterMotivosDeposito(int codUsuario, DepartamentoCompanhiaVO deptoCia);

	/**
	 * Lista os bancos relacionados a uma companhia
	 * @param cia Companhia
	 * @param depto depto
	 * @param motivoVO motivoVO
	 * @return Lista de bancos
	 */
	List<BancoVO> obterBancos(CompanhiaSeguradoraVO cia, DepartamentoVO depto, MotivoDepositoVO motivoVO);

	/**
	 * Obtém os dados de um banco
	 * @param vo Contém a código do banco
	 * @return Banco
	 */
	BancoVO obterBanco(BancoVO vo);

	/**
	 * Consulta o nome de uma agência
	 * @param banco Banco
	 * @param codigoAgencia Código da agência
	 * @return Descrição da agência
	 */
	AgenciaVO obterAgencia(BancoVO banco, int codigoAgencia);

	/**
	 * Consulta um motivo depósito pela chave
	 * @param motivoDeposito Contém a chave de motivo depósito
	 * @return Item encontrado ou nulo
	 */
	MotivoDepositoVO obterMotivoDeposito(MotivoDepositoVO motivoDeposito);

	/**
	 * Consulta um evento contábil pelo código de evento de negócio no CICS. Não
	 * há uma consulta para retornar apenas um único registro, mas uma lista de
	 * eventos de um tipo de evento.
	 * 
	 * @param codigoEvento
	 *            Tipo do evento
	 * @return Evento contábil.
	 */
	EventoContabilVO obterEventoContabil(int codigoEvento);

	/**
	 * Consulta um item contábil a partir do tipo de evento no CICS.
	 * @param codigoTipoEventoNegocio Código do tipo de evento
	 * @param codigoItemContabil Código do item contábil
	 * @return Item contábil.
	 */
	ItemContabilVO obterItemContabil(int codigoTipoEventoNegocio,
			int codigoItemContabil);

	/**
	 * Consulta o banco de dados e o CICS para obter a companhia seguradora
	 * @param companhiaSeguradoraVO Fornece o código da Cia
	 * @return Companhia Seguradora
	 */
	CompanhiaSeguradoraVO obterCompanhiaSeguradora(
			CompanhiaSeguradoraVO companhiaSeguradoraVO);

	/**
	 * Consulta o banco para obter um departamento
	 * @param departamentoVO Fornece o código do departamento
	 * @return Departamento
	 */
	DepartamentoVO obterDepartamento(DepartamentoVO departamentoVO);

	/**
	 * Obtém lista de contas
	 * @param ciaVO Companhia
	 * @param depto Depto
	 * @param motivoVO Motivo
	 * @param bancoVO Banco
	 * @param agenciaVO Agência
	 * @return Lista de contas
	 */
	List<ContaCorrenteAutorizadaVO> obterContasComRestricaoAssociacaoMotivos(
			CompanhiaSeguradoraVO ciaVO, DepartamentoVO depto,
			MotivoDepositoVO motivoVO, BancoVO bancoVO, AgenciaVO agenciaVO);

	/**
	 * Obtém os dados de uma conta
	 * @param banco Banco
	 * @param codigoAgencia Agência
	 * @param contaCorrente Número da Conta
	 * @return Conta
	 */
	ContaCorrenteAutorizadaVO obterConta(
			BancoVO banco, int codigoAgencia, Long contaCorrente);

	/**
	 * Consulta pessoas para um mesmo número Cpf/Cnpj
	 * @param numeroCpfCnpj Número a ser pesquisado
	 * @param ipCliente IP do usuário
	 * @param codUsuario Código do usuário
	 * @return Lista de pessoas encontradas
	 */
	List<ListarPessoaPorFiltroSaidaVO> listarPessoas(String numeroCpfCnpj,
			String ipCliente, int codUsuario);

	/**
	 * Retorna parâmetro do Depósito
	 * @param vo Depósito
	 * @return Parâmetro
	 */
	ParametroDepositoVO obterParametro(DepositoVO vo);
	
	void prorrogar(DepositoVO vo, String ipCliente);
	
	void cancelar(DepositoVO vo, String ipCliente);

	MovimentoDepositoVO obterMovimentoDeposito(DepositoVO vo);
	
	void inserirMovimento(MovimentoDepositoVO vo, String ipCliente);
	
	void alterarMovimento(MovimentoDepositoVO vo);

	LancamentoDepositoVO obterLancamentoDeposito(DepositoVO vo);

	void alterar(DepositoVO vo);


	/**
	 * Obtém lista de agências de um banco associadas a um banco
	 * @param ciaVO Companhia
	 * @param depto TODO
	 * @param bancoVO Banco
	 * @param motivoVO motivo
	 * @return Agências
	 */
	List<AgenciaVO> obterAgenciasMotivo(CompanhiaSeguradoraVO ciaVO, DepartamentoVO depto,
			BancoVO bancoVO, MotivoDepositoVO motivoVO);

}
