package br.com.bradseg.depi.depositoidentificado.funcao.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.bradseg.depi.depositoidentificado.form.cadastro.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.MotivoDepositoCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoOperacao;
import br.com.bradseg.depi.depositoidentificado.util.CriterioConsultaVO;

import com.opensymphony.xwork2.Action;

@Component
public abstract class FiltroAction<T extends FiltroConsultarForm<?>> extends BaseModelAction<T> {

	private static final long serialVersionUID = 935947361413242271L;

	private static final Logger LOGGER = LoggerFactory.getLogger(FiltroAction.class);
	
	protected void prepararFiltro() {
		LOGGER.info("Preparando contexto de filtro da consulta");
		
		if (getModel() != null && getModel().getColecaoDados() != null) {
			getModel().setColecaoDados(null);
		}
		
		LOGGER.debug("Removendo formulário do contexto de sessão e criando nova instância");
		String actionName = this.getClass().getSimpleName();
		sessionData.remove(actionName);
		this.getModel().setColecaoDados(null);
		
		this.novaInstanciaModel();
		
		this.getModel().setColecaoDados(null);
	}
	
	protected void persistirContextoFiltro() {
		T model = getModel();
		
		String actionName = this.getClass().getSimpleName();
		sessionData.put(actionName, model);
	}

	protected CriterioConsultaVO montarCriterioConsulta(
			MotivoDepositoCampo campo, TipoOperacao operacao, String valor,
			String param) {
		String clausula = operacao.formatarClausula(campo.getNome(), param);
		String valorFormatado = operacao.formatarValor(valor);
		CriterioConsultaVO criterioConsultaVO = new CriterioConsultaVO(clausula, param, valorFormatado);
		return criterioConsultaVO;
	}
	
	/**
	 * Primeiro método chamado da Action. Cria uma nova instância de formulário
	 * na sessão e armazena-a na sessão do usuário.
	 * 
	 * @return {@link Action#SUCCESS}
	 */
	public String iniciarFormulario() {
		
		this.prepararFiltro();
		
		this.persistirContextoFiltro();
		
		return SUCCESS;
	}

}
