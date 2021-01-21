package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.form.MotivoDepositoEditarForm;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.MotivoDepositoCrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.MotivoDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudAction;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("session")
public class MotivoDepositoEditarAction extends CrudAction<MotivoDepositoVO, MotivoDepositoEditarForm> {

	private static final long serialVersionUID = -7675543657126275320L;
	
	private transient MotivoDepositoCrudHelper crudHelper;
	
	@Override
	protected CrudHelper<?, MotivoDepositoVO, MotivoDepositoEditarForm> getCrudHelper() {
		if (crudHelper == null) {
			crudHelper = new MotivoDepositoCrudHelper();
		}
		return crudHelper;
	}
	
	@Autowired
	public void setFacade(MotivoDepositoFacade facade) {
		crudHelper.setFacade(facade);
	}
	
	@Override
	protected List<MotivoDepositoVO> mapearListaVO(String[] codigos) {
		List<MotivoDepositoVO> lista = new ArrayList<>();
		
		for (String codigo : codigos) {
			MotivoDepositoVO vo = new MotivoDepositoVO();
			vo.setCodigoMotivoDeposito(new Integer(codigo));
			
			vo = crudHelper.obterPorChave(vo);
			lista.add(vo);
		}
		
		return lista;
	}
	
}
