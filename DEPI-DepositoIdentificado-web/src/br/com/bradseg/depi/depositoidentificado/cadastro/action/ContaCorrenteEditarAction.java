package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.form.ContaCorrenteEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.ContaCorrenteCrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.ContaCorrenteFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.EditarFormAction;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.ContaCorrenteAutorizadaCampo;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("session")
public class ContaCorrenteEditarAction extends EditarFormAction<ContaCorrenteAutorizadaCampo, ContaCorrenteAutorizadaVO, ContaCorrenteEditarFormModel> {

	private static final long serialVersionUID = -7675543657126275320L;
	
	private transient ContaCorrenteCrudHelper crudHelper;
	
	private final static int CODIGO_BANCO_BRADESCO = 237;
	
	@Override
	protected CrudHelper<ContaCorrenteAutorizadaCampo, ContaCorrenteAutorizadaVO, ContaCorrenteEditarFormModel> getCrudHelper() {
		if (crudHelper == null) {
			crudHelper = new ContaCorrenteCrudHelper();
		}
		return crudHelper;
	}
	
	@Autowired
	public void setFacade(ContaCorrenteFacade facade) {
		crudHelper.setFacade(facade);
	}
		
	@Override
	protected List<ContaCorrenteAutorizadaVO> mapearListaVO(String[] codigos) {
		List<ContaCorrenteAutorizadaVO> lista = new ArrayList<>();
		
		for (String codigo : codigos) {
			ContaCorrenteAutorizadaVO vo = crudHelper.obterPeloCodigo(codigo);
			lista.add(vo);
		}
		
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.funcao.action.EditarFormAction#incluir()
	 */
	@Override
	public String incluir() {
		try {
			int codUsuario = getCodUsuarioLogado();
			
			String retorno = super.incluir();
			
			List<CompanhiaSeguradoraVO> cias = crudHelper.obterCompanhias(codUsuario);
			getModel().setCias(cias);
			
			if (cias != null && !cias.isEmpty()) {
				int codigoCompanhia = cias.get(0).getCodigoCompanhia();
				getModel().setCodigoCompanhia(String.valueOf(codigoCompanhia));
			}
			
			BancoVO banco = crudHelper.obterBanco(new BancoVO(CODIGO_BANCO_BRADESCO));
			getModel().setCodigoBanco(String.valueOf(CODIGO_BANCO_BRADESCO));
			getModel().setDescricaoBanco(banco.getDescricaoBanco());
			
			return retorno;
		} catch (Exception e) {
			addActionError(e.getMessage());
			return voltar();
		}
	}
	
}
