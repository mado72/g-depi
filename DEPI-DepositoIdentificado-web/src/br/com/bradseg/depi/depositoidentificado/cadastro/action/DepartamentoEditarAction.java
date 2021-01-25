package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.form.DepartamentoEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.DepartamentoCrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.DepartamentoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.EditarFormAction;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("session")
public class DepartamentoEditarAction extends EditarFormAction<DepartamentoVO, DepartamentoEditarFormModel> {
	
    protected static final Logger LOGGER = LoggerFactory.getLogger(DepartamentoEditarAction.class);

	private static final long serialVersionUID = -7675543657126275320L;
	
	private transient DepartamentoCrudHelper crudHelper;

	@Override
	protected CrudHelper<DepartamentoVO, DepartamentoEditarFormModel> getCrudHelper() {
		if (crudHelper == null) {
			crudHelper = new DepartamentoCrudHelper();
		}
		return crudHelper;
	}
	
	@Autowired
	public void setFacade(DepartamentoFacade facade) {
		this.crudHelper.setFacade(facade);
	}

	@Override
	protected List<DepartamentoVO> mapearListaVO(String[] codigos) {
        
        List<DepartamentoVO> lista = new ArrayList<>();
        
        for (String codigo: codigos) {
            DepartamentoVO vo = new DepartamentoVO();
            vo.setCodigoDepartamento(new Integer(codigo));
            
            vo = getCrudHelper().obterPorChave(vo);
            lista.add(vo);
        }
        
        return lista;
	}

}
