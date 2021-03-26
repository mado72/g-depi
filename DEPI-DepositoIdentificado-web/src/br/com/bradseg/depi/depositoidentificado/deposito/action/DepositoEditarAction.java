package br.com.bradseg.depi.depositoidentificado.deposito.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.form.DepositoEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.DepositoCrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.DepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.EditarFormAction;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.DepositoCampo;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.vo.AgenciaVO;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("session")
public class DepositoEditarAction
		extends EditarFormAction<DepositoCampo, DepositoVO, DepositoEditarFormModel> {

	private static final long serialVersionUID = -7675543657126275320L;
	
	private transient DepositoCrudHelper crudHelper;
	
	private final static int CODIGO_BANCO_BRADESCO = 237;
	
	@Override
	protected CrudHelper<DepositoCampo, DepositoVO, DepositoEditarFormModel> getCrudHelper() {
		if (crudHelper == null) {
			crudHelper = new DepositoCrudHelper();
		}
		return crudHelper;
	}
	
	@Autowired
	public void setFacade(DepositoFacade facade) {
		crudHelper.setFacade(facade);
	}
		
	@Override
	protected List<DepositoVO> mapearListaVO(String[] codigos) {
		List<DepositoVO> lista = new ArrayList<>();
		
		for (String codigo : codigos) {
			DepositoVO vo = new DepositoVO(Long.parseLong(codigo));
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
							.parseInt(getModel().getCodigoMotivoDeposito())));
			definirMotivo(motivo);
			
			BancoVO banco = crudHelper.obterBanco(new BancoVO(CODIGO_BANCO_BRADESCO));
			getModel().setCodBanco(String.valueOf(CODIGO_BANCO_BRADESCO));
			getModel().setDescricaoBanco(banco.getDescricaoBanco());
			
			return retorno;
		} catch (Exception e) {
			addActionError(e.getMessage());
			return INPUT;
		}
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.funcao.action.EditarFormAction#excluir()
	 */
	@Override
	public String excluir() {
		String[] codigos = request.getParameterValues("codigo");
		List<DepositoVO> listaVO = mapearListaVO(codigos);
		
		try {
			int usuarioLogado = getCodUsuarioLogado();
			for (DepositoVO vo : listaVO) {
				vo.setCodigoResponsavelUltimaAtualizacao(usuarioLogado);
			}
			getCrudHelper().excluirRegistros(listaVO);
			addActionMessage(getText(ConstantesDEPI.MSG_EXCLUIR_EXITO));
			
			return SUCCESS;
		} catch (Exception e) {
			addActionError(e.getMessage());
			return voltar();
		}
	}

	private void definirCompanhia(int codUsuario, CompanhiaSeguradoraVO ciaVO) {
		int codigoCompanhia = ciaVO.getCodigoCompanhia();
		
		DepositoEditarFormModel model = getModel();
		model.setCodigoCompanhia(String.valueOf(codigoCompanhia));
		
		List<DepartamentoVO> deptos = crudHelper.obterDepartamentos(codUsuario, ciaVO);
		model.setDeptos(deptos);
		
		if (! deptos.isEmpty()) {
			DepartamentoVO deptoVO = deptos.get(0);
			definirDepartamento(codUsuario, ciaVO, deptoVO);
		}
		
		List<BancoVO> bancos = crudHelper.obterBancos(ciaVO);
		model.setBancos(bancos);
		
		if (! bancos.isEmpty()) {
			BancoVO bancoVO = bancos.get(0);
			definirBanco(ciaVO, bancoVO);
		}
	}

	private void definirBanco(CompanhiaSeguradoraVO ciaVO, BancoVO bancoVO) {
		DepositoEditarFormModel model = getModel();
		model.setCodBanco(String.valueOf(bancoVO.getCdBancoExterno()));

		List<AgenciaVO> agencias = crudHelper.obterAgencias(ciaVO, bancoVO);
		model.setAgencias(agencias);
		
		if (! agencias.isEmpty()) {
			AgenciaVO agenciaVO = agencias.get(0);
			definirAgencia(ciaVO, bancoVO, agenciaVO);
		}
	}

	private void definirAgencia(CompanhiaSeguradoraVO ciaVO, BancoVO bancoVO,
			AgenciaVO agenciaVO) {
		DepositoEditarFormModel model = getModel();
		model.setCodigoAgencia(String.valueOf(agenciaVO.getCdAgenciaExterno()));
		
		int codUsuario = getCodUsuarioLogado();
		
		List<ContaCorrenteAutorizadaVO> contas = crudHelper
				.obterContasCorrente(codUsuario, ciaVO, bancoVO, agenciaVO);
		model.setContas(contas);
		
		if (!contas.isEmpty()) {
			ContaCorrenteAutorizadaVO contaCorrente = contas.get(0);
			definirConta(contaCorrente);
		}
	}

	private void definirConta(ContaCorrenteAutorizadaVO contaCorrente) {
		DepositoEditarFormModel model = getModel();
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
		getModel().setCodigoMotivoDeposito(String.valueOf(motivo.getCodigoMotivoDeposito()));
	}
	
}