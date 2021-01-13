package br.com.bradseg.depi.depositoidentificado.funcao.action.cadastro;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.facade.MotivoDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.form.cadastro.MotivoDepositoConsultarForm;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroAction;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesView;
import br.com.bradseg.depi.depositoidentificado.util.json.DepiObjectMapper;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

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
	
	private MotivoDepositoConsultarForm model = new MotivoDepositoConsultarForm();
	
	@Autowired
	private MotivoDepositoFacade facade;

	@Resource
	private transient String www3;
	
	@Override
	public MotivoDepositoConsultarForm getModel() {
		return model;
	}

	public String getWww3() {
		return www3;
	}
	
	public String execute() {
		super.prepararFiltro();
		
		super.persistirContextoFiltro();
		
		return SUCCESS;
	}
	
	public String consultar() {
		processarFiltro(); 
		
		return SUCCESS;
	}

	private void processarFiltro() {

/*
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
		try {
//            List<MotivoDepositoVO> retorno = facade.obterPorFiltroMotivoDepositvo(filtro);
			List<MotivoDepositoVO> retorno = facade.obterTodosMotivoDepositvo();

			model.setColecaoDados(retorno);
			
			DepiObjectMapper mapper = new DepiObjectMapper();
			try {
				String json = mapper.writeValueAsString(retorno);
				request.setAttribute("json", json);
			} catch (JsonProcessingException e) {
			}

            if (retorno == null || retorno.isEmpty()) {
            	String message = super.getText(ConstantesView.MSG_CONSULTA_RETORNO_VAZIO);
                addActionMessage(message);
            }
        } catch (DEPIIntegrationException e) {
        	LOGGER.error("Falha na consulta", e);
            addActionError(e.getMessage());
        }
	}

}
