package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.depi.depositoidentificado.cadastro.form.DepartamentoCompanhiaEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.DepartamentoCompanhiaCrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.DepartamentoCompanhiaFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.EditarFormAction;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.DepartamentoCompanhiaCampo;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("session")
public class DepartamentoCompanhiaEditarAction extends EditarFormAction<DepartamentoCompanhiaCampo, DepartamentoCompanhiaVO, DepartamentoCompanhiaEditarFormModel> {
	
	private static final long serialVersionUID = -7675543657126275320L;
	
	private transient DepartamentoCompanhiaCrudHelper crudHelper;

	@Override
	protected CrudHelper<DepartamentoCompanhiaCampo, DepartamentoCompanhiaVO, DepartamentoCompanhiaEditarFormModel> getCrudHelper() {
		if (crudHelper == null) {
			crudHelper = new DepartamentoCompanhiaCrudHelper();
		}
		return crudHelper;
	}
	
	@Autowired
	public void setFacade(DepartamentoCompanhiaFacade facade) {
		this.crudHelper.setFacade(facade);
	}

	@Override
	protected List<DepartamentoCompanhiaVO> mapearListaVO(String[] codigosCompostos) {
		
		LoginVo usuarioLogado = getUsuarioLogado();
		Integer codUsuario = new Integer(usuarioLogado.getId());
		
        List<DepartamentoCompanhiaVO> lista = new ArrayList<>();
        
        for (String item: codigosCompostos) {
        	String[] codigos = item.split(";");
        	
        	DepartamentoCompanhiaVO vo = new DepartamentoCompanhiaVO();
        	vo.setCodigoResponsavelUltimaAtualizacao(codUsuario);
            vo.setCompanhia(new CompanhiaSeguradoraVO(Integer.parseInt(codigos[0])));
            
            if (codigos.length == 2) {
            	vo.setDepartamento(new DepartamentoVO(Integer.parseInt(codigos[1])));
            }
            
            lista.add(vo);
        }
        
        return lista;
	}
	
	public String selecionar() {
		crudHelper.preencherDepartamentos(getModel());
		return INPUT;
	}
	
	public String refrescar() {
		DepartamentoCompanhiaEditarFormModel model = getModel();
		
		List<String> siglaDepartamentos = model.getSiglaDepartamentos();
		List<DepartamentoVO> deptos = model.getDeptos();
		
		for (Iterator<DepartamentoVO> iter = deptos.iterator(); iter.hasNext(); ) {
			DepartamentoVO vo = iter.next();
			if (! siglaDepartamentos.contains(vo.getSiglaDepartamento())) {
				iter.remove();
			}
		}

		return "json";
	}
	
	@Override
	public String incluir() {
		String retorno = super.incluir();
		preencherListaCompanhia();
		return retorno;
	}

	private void preencherListaCompanhia() {
		DepartamentoCompanhiaEditarFormModel model = getModel();
		List<CompanhiaSeguradoraVO> cias = crudHelper.obterCompanhias();
		model.setCias(cias);		
	}

}
