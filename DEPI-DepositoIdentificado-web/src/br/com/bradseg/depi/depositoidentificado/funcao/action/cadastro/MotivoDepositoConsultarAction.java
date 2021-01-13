package br.com.bradseg.depi.depositoidentificado.funcao.action.cadastro;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.facade.MotivoDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.form.cadastro.MotivoDepositoConsultarForm;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroAction;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesView;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

import com.opensymphony.xwork2.Action;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("request")
public class MotivoDepositoConsultarAction extends FiltroAction<MotivoDepositoConsultarForm> {
	
    protected static final Logger LOGGER = LoggerFactory.getLogger(MotivoDepositoConsultarAction.class);

	private static final long serialVersionUID = -7675543657126275320L;
	
	private MotivoDepositoConsultarForm _model;
	
	@Autowired
	private MotivoDepositoFacade facade;
	
	@Override
	public MotivoDepositoConsultarForm getModel() {
		if (sessionData.containsKey(MotivoDepositoConsultarForm.NOME_FORM)) {
			_model = (MotivoDepositoConsultarForm) sessionData.get(MotivoDepositoConsultarForm.NOME_FORM);
		}
		else {
			this.novaInstanciaModel();
		}
		return _model;
	}
	
	@Override
	protected void novaInstanciaModel() {
		_model = new MotivoDepositoConsultarForm();
		sessionData.put(MotivoDepositoConsultarForm.NOME_FORM, _model);
	}
	
	/**
	 * Apenas redireciona a saída para SUCCESS. Usado para apresentar o
	 * formulário armazenado na sessão.
	 * 
	 * @return {@link Action#SUCCESS}
	 */
	public String execute() {
		return SUCCESS;
	}
	
	/**
	 * Processa os dados do filtro.
	 * 
	 * @return {@link Action#SUCCESS}, quando consegue realizar a consulta. Senão {@link Action#ERROR}
	 */
	public String consultar() {
		try {
			processarFiltro();
			
			return SUCCESS;
		} catch (DEPIIntegrationException e) {
			LOGGER.error("Falha na consulta", e);
			addActionError(e.getMessage());
			
			return ERROR;
		}
	}
	
	public String incluir() {
		return "incluirAcao";
	}
	
	private void processarFiltro() {
/*
		FIXME Tirar este comentário e implementar método para preparar o filtro da consulta. Substituir
		facade.obterTodosMotivoDepositvo() por outra consulta com filtro.

        CriterioFiltroUtil filtro = new CriterioFiltroUtil();
        List<CriterioFiltroUtil> listCriterios = new ArrayList<CriterioFiltroUtil>();

        for (int i = 0; i < model.getCampo().size(); i++) {
            CriterioFiltroUtil criterio = new CriterioFiltroUtil();
            criterio.setCampo(MotivoDepositoCampo.obterPorNome(model.getCampo().get(i)));
            criterio.setOperacao(TipoOperacao.obterPorCodigo(model.getOperacao().get(i)));
            criterio.setValor(model.getValor().get(i));
            listCriterios.add(criterio);
        }
        listCriterios.add(CriterioFiltroUtil.getDefaultCriterioAtivo());
        filtro.setCriterios(listCriterios);
*/
		
		List<MotivoDepositoVO> retorno = facade.obterTodosMotivoDepositvo();
		
		getModel().setColecaoDados(retorno);

		if (retorno == null || retorno.isEmpty()) {
			String message = super.getText(ConstantesView.MSG_CONSULTA_RETORNO_VAZIO);
			addActionMessage(message);
		}
	}

}
