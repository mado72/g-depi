package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.form.AssociarMotivoDepositoEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.AssociarMotivoDepositoCrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.EditarFormAction;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.AssociarMotivoDepositoCampo;
import br.com.bradseg.depi.depositoidentificado.vo.AssociarMotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("session")
public class AssociarMotivoDepositoEditarAction
		extends EditarFormAction<AssociarMotivoDepositoCampo, AssociarMotivoDepositoVO, AssociarMotivoDepositoEditarFormModel> {

	private static final long serialVersionUID = -7675543657126275320L;
	
	private transient AssociarMotivoDepositoCrudHelper crudHelper;
	
	private final static int CODIGO_BANCO_BRADESCO = 237;
	
	@Override
	protected CrudHelper<AssociarMotivoDepositoCampo, AssociarMotivoDepositoVO, AssociarMotivoDepositoEditarFormModel> getCrudHelper() {
		if (crudHelper == null) {
			crudHelper = new AssociarMotivoDepositoCrudHelper();
		}
		return crudHelper;
	}
	
	@Autowired
	public void setFacade(AssociarMotivoDepositoFacade facade) {
		crudHelper.setFacade(facade);
	}
		
	@Override
	protected List<AssociarMotivoDepositoVO> mapearListaVO(String[] codigos) {
		List<AssociarMotivoDepositoVO> lista = new ArrayList<>();
		
		for (String codigo : codigos) {
			AssociarMotivoDepositoVO vo = crudHelper.analisarCodigo(codigo);
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
