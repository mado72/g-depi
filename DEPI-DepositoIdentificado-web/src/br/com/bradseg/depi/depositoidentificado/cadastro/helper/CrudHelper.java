package br.com.bradseg.depi.depositoidentificado.cadastro.helper;

import java.util.List;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.funcao.action.BaseForm;
import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroAction;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.cadastro.EntidadeCampoOperacoesFiltro;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.IEntidadeCampo;
import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;

/**
 * Interface que determina o contrato de apoio para o fluxo de cadastro de uma entidade
 * 
 * @author Marcelo Damasceno
 * 
 * @param <T>
 *            Classe da {@link EntidadeCampoOperacoesFiltro} que é consultada.
 * @param <VO>
 *            Classe de dados que são listadas na consulta.
 */
public interface CrudHelper<T extends IEntidadeCampo, VO> {
	
	/**
	 * Estados de um registro em edição 
	 */
	public static enum EstadoRegistro {
		NOVO, PERSISTIDO;
	}
	
	/**
	 * Retorna a chave para imprimir o título quando o formulário está no modo
	 * de consultar
	 * 
	 * @return Chave
	 */
	String getChaveTituloConsultar();
	
	/**
	 * Retorna a chave para imprimir o título quando o formulário está no modo
	 * de listar
	 * @return Chave
	 */
	String getChaveTituloListar();
	
	/**
	 * Retorna a chave para imprimir o título quando está no modo
	 * de detalhar um registro
	 * @return Chave
	 */
	String getChaveTituloDetalhar();
	
	/**
	 * Retorna a chave para imprimir o título quando está no modo
	 * de editar um registro
	 * @return Chave
	 */
	String getChaveTituloAlterar();
	
	/**
	 * Retorna a chave para imprimir o título quando está no modo
	 * de incluir um registro
	 * @return Chave
	 */
	String getChaveTituloIncluir();
	
	/**
	 * Instancia um novo model para o formulário de filtro
	 * 
	 * @return Nova instância
	 */
	FiltroConsultarForm<T> criarFiltroModel();
	
	/**
	 * Instancia um novo model para o formulário de edição
	 * 
	 * @return Nova instância de {@link BaseForm}
	 */
	CrudForm criarCrudModel();
	
	/**
	 * Processa os critérios de pesquisa e preenche o
	 * {@link FiltroAction#getModel()}
	 * 
	 * @param criterioCol
	 *            Coleção de critérios para ser utilizado na pesquisa.
	 */
	List<VO> processarCriterios(List<CriterioConsultaVO> criterioCol);
	
	/**
	 * Preenche formulário para edição de um registro.
	 * 
	 * @param model Instância do formulário de edição.
	 * 
	 * @throws DEPIIntegrationException Quando não é possível preencher o formulário.
	 */
	void preencherFormularioEdicao(CrudForm model) throws DEPIIntegrationException;
	
	/**
	 * Persiste os dados informados no formulário
	 * 
	 * @param model
	 *            Instância do formulário de edição
	 * @param usuarioLogado
	 *            Dados do usuário logado
	 * 
	 * @return Estado do registro editado
	 * @throws DEPIIntegrationException
	 *             Quando há uma falha durante o processo de persistir os dados
	 *             do formulário.
	 */
	EstadoRegistro persistirDados(CrudForm model, LoginVo usuarioLogado) throws DEPIIntegrationException;
	
	/**
	 * Exclui os registros que informados na coleção de VO
	 * 
	 * @param voList
	 *            Coleção de VO
	 * 
	 * @throws DEPIIntegrationException
	 *             Quando há uma falha na exclusão de um ou mais registros.
	 */
	void excluirRegistros(List<VO> voList) throws DEPIIntegrationException;
	
	/**
	 * Obtém uma instância de VO
	 * @param vo Vo que contém a chave persistida
	 * @return VO recuperado da fonte de dados
	 */
	VO obterPorChave(VO vo);

}
