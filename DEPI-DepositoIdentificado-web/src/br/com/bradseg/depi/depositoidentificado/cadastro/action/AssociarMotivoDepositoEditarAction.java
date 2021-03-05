package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import java.util.ArrayList;
import java.util.Collections;
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
import br.com.bradseg.depi.depositoidentificado.vo.AgenciaVO;
import br.com.bradseg.depi.depositoidentificado.vo.AssociarMotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.EventoContabilVO;
import br.com.bradseg.depi.depositoidentificado.vo.ItemContabilVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

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
				CompanhiaSeguradoraVO ciaVO = cias.get(0);
				definirCompanhia(codUsuario, ciaVO);
			}
			
			BancoVO banco = crudHelper.obterBanco(new BancoVO(CODIGO_BANCO_BRADESCO));
			getModel().setCodigoBanco(String.valueOf(CODIGO_BANCO_BRADESCO));
			getModel().setDescricaoBanco(banco.getDescricaoBanco());
			
			return retorno;
		} catch (Exception e) {
			addActionError(e.getMessage());
			return INPUT;
		}
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.funcao.action.EditarFormAction#alterar()
	 */
	@Override
	public String alterar() {
		try {
			// Valida se o usuário está logado
			getCodUsuarioLogado();
			
			String retorno = super.incluir();
			
			CompanhiaSeguradoraVO ciaVO = crudHelper.obterCompanhia(new CompanhiaSeguradoraVO(Integer.parseInt(getModel().getCodigoCompanhia())));
			getModel().setCias(Collections.singletonList(ciaVO));
			
			DepartamentoVO depto = crudHelper
					.obterDepartamento(new DepartamentoVO(Integer
							.parseInt(getModel().getCodigoDepartamento())));
			getModel().setDeptos(Collections.singletonList(depto));
			
			MotivoDepositoVO motivo = crudHelper
					.obterMotivoDeposito(new MotivoDepositoVO(Integer
							.parseInt(getModel().getCodigoMotivo())));
			definirMotivo(motivo);
			
			BancoVO banco = crudHelper.obterBanco(new BancoVO(CODIGO_BANCO_BRADESCO));
			getModel().setCodigoBanco(String.valueOf(CODIGO_BANCO_BRADESCO));
			getModel().setDescricaoBanco(banco.getDescricaoBanco());
			
			return retorno;
		} catch (Exception e) {
			addActionError(e.getMessage());
			return INPUT;
		}
	}

	private void definirCompanhia(int codUsuario, CompanhiaSeguradoraVO ciaVO) {
		int codigoCompanhia = ciaVO.getCodigoCompanhia();
		
		AssociarMotivoDepositoEditarFormModel model = getModel();
		model.setCodigoCompanhia(String.valueOf(codigoCompanhia));
		
		List<DepartamentoVO> deptos = crudHelper.obterDepartamentos(codUsuario, ciaVO);
		model.setDeptos(deptos);
		
		if (! deptos.isEmpty()) {
			DepartamentoVO deptoVO = deptos.get(0);
			definirDepartamento(codUsuario, ciaVO, deptoVO);
		}
		
		List<BancoVO> bancos = crudHelper.obterBancos(ciaVO);
		if (! bancos.isEmpty()) {
			BancoVO bancoVO = bancos.get(0);
			definirBanco(ciaVO, bancoVO);
		}
	}

	private void definirBanco(CompanhiaSeguradoraVO ciaVO, BancoVO bancoVO) {
		AssociarMotivoDepositoEditarFormModel model = getModel();
		model.setCodigoBanco(String.valueOf(bancoVO.getCdBancoExterno()));

		List<AgenciaVO> agencias = crudHelper.obterAgencias(ciaVO, bancoVO);
		
		if (! agencias.isEmpty()) {
			AgenciaVO agenciaVO = agencias.get(0);
			definirAgencia(ciaVO, bancoVO, agenciaVO);
		}
	}

	private void definirAgencia(CompanhiaSeguradoraVO ciaVO, BancoVO bancoVO,
			AgenciaVO agenciaVO) {
		AssociarMotivoDepositoEditarFormModel model = getModel();
		model.setCodigoAgencia(String.valueOf(agenciaVO.getCdAgenciaExterno()));
		
		List<ContaCorrenteAutorizadaVO> contas = crudHelper.obterContasCorrente(ciaVO, bancoVO, agenciaVO);
		
		if (!contas.isEmpty()) {
			ContaCorrenteAutorizadaVO contaCorrente = contas.get(0);
			definirConta(contaCorrente);
		}
	}

	private void definirConta(ContaCorrenteAutorizadaVO contaCorrente) {
		AssociarMotivoDepositoEditarFormModel model = getModel();
		model.setContaCorrente(contaCorrente.getChaveComposta());
		model.setContaInterna(String.valueOf(contaCorrente.getCodigoInternoCC()));
	}

	private void definirDepartamento(int codUsuario,
			CompanhiaSeguradoraVO ciaVO, DepartamentoVO deptoVO) {
		DepartamentoCompanhiaVO deptoCia = new DepartamentoCompanhiaVO(ciaVO, deptoVO);
		
		String codDepto = String.valueOf(deptoVO.getCodigoDepartamento());
		getModel().setCodigoDepartamento(codDepto);
		
		List<MotivoDepositoVO> motivos = crudHelper.obterMotivosDeposito(codUsuario, deptoCia);
		getModel().setMotivos(motivos);
		
		MotivoDepositoVO motivo = motivos.get(0);
		definirMotivo(motivo);
	}

	private void definirMotivo(MotivoDepositoVO motivo) {
		getModel().setCodigoMotivo(String.valueOf(motivo.getCodigoMotivoDeposito()));
		
		EventoContabilVO eventoContabilVO = crudHelper.obterEventoContabil(motivo);
		getModel().setCodigoEventoContabil(String.valueOf(eventoContabilVO.getCodigoTipo()));
		getModel().setDescricaoEventoContabil(eventoContabilVO.getDescricaoTipo());
		
		ItemContabilVO itemContabilVO = crudHelper.obterItemContabil(motivo);
		getModel().setCodigoItemContabil(String.valueOf(itemContabilVO.getCodigoTipo()));
		getModel().setDescricaoItemContabil(itemContabilVO.getDescricaoTipo());
	}
	
}
