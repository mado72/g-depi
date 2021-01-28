package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.form.ParametroDepositoEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.ParametroDepositoCrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.ParametroDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.EditarFormAction;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.ParametroDepositoCampo;
import br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoVO;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("session")
public class ParametroEditarAction extends EditarFormAction<ParametroDepositoCampo, ParametroDepositoVO, ParametroDepositoEditarFormModel> {

	private static final long serialVersionUID = -7675543657126275320L;
	
	private transient ParametroDepositoCrudHelper crudHelper;
	
	@Override
	protected CrudHelper<ParametroDepositoCampo, ParametroDepositoVO, ParametroDepositoEditarFormModel> getCrudHelper() {
		if (crudHelper == null) {
			crudHelper = new ParametroDepositoCrudHelper();
		}
		return crudHelper;
	}
	
	@Autowired
	public void setFacade(ParametroDepositoFacade facade) {
		crudHelper.setFacade(facade);
	}
	
	@Override
	protected List<ParametroDepositoVO> mapearListaVO(String[] codigos) {
		List<ParametroDepositoVO> lista = new ArrayList<>();
		
		for (String codigo : codigos) {
			ParametroDepositoVO vo = new ParametroDepositoVO();
			// FIXME preencher a chave.
			// vo.setCodigoParametroDeposito(new Integer(codigo));
			
			vo = crudHelper.obterPorChave(vo);
			lista.add(vo);
		}
		
		return lista;
	}
	
}
