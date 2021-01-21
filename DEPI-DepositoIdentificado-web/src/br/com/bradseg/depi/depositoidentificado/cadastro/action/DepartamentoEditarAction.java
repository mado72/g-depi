package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.form.DepartamentoEditarForm;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.DepartamentoCrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.DepartamentoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudAction;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("request")
public class DepartamentoEditarAction extends CrudAction<DepartamentoVO, DepartamentoEditarForm> {
	
    protected static final Logger LOGGER = LoggerFactory.getLogger(DepartamentoEditarAction.class);

	private static final long serialVersionUID = -7675543657126275320L;
	
	@Autowired
	private transient DepartamentoFacade facade;
	
	private transient DepartamentoCrudHelper crudHelper;

	@Override
	protected CrudHelper<DepartamentoVO, DepartamentoEditarForm> getCrudHelper() {
		if (crudHelper == null) {
			crudHelper = new DepartamentoCrudHelper(facade);
		}
		return crudHelper;
	}

	@Override
	protected List<DepartamentoVO> mapearListaVO(String[] codigos) {
        
        List<DepartamentoVO> lista = new ArrayList<>();
        
        for (String codigo: codigos) {
            DepartamentoVO vo = new DepartamentoVO();
            vo.setCodigoDepartamento(new Integer(codigo));
            
            vo = facade.obterPorChave(vo);
            lista.add(vo);
        }
        
        return lista;
	}

}
