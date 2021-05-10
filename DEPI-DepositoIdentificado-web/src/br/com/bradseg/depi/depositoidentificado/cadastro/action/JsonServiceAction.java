/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.bucb.servicos.model.pessoa.vo.ListarPessoaPorFiltroSaidaVO;
import br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.facade.ContaCorrenteFacade;
import br.com.bradseg.depi.depositoidentificado.facade.DepositoFacade;
import br.com.bradseg.depi.depositoidentificado.facade.GrupoAcessoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.BaseModelAction;
import br.com.bradseg.depi.depositoidentificado.relatorio.facade.ConsultarRelatorioFacade;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.vo.AgenciaVO;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.EventoContabilVO;
import br.com.bradseg.depi.depositoidentificado.vo.JsonRequestVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoVO;

/**
 * Ação para retornar um JSON com a lista de departamentos de acordo com o
 * código da companhia selecionada e o usuário logado.
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("request")
public class JsonServiceAction extends BaseModelAction<JsonRequestVO> {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(JsonServiceAction.class);

	@Autowired
	private GrupoAcessoFacade grupoAcessoFacade;
	
	@Autowired
	private ContaCorrenteFacade contaCorrenteFacade;
	
	@Autowired
	private AssociarMotivoDepositoFacade associarMotivoFacade;
	
	@Autowired
	private DepositoFacade depositoFacade;
	
	@Autowired
	private ConsultarRelatorioFacade relatorioFacade;
	
	private static final long serialVersionUID = 8999882840693772747L;
	
	private final JsonRequestVO model = new JsonRequestVO();
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public JsonRequestVO getModel() {
		return model;
	}
	
	private void handleException(Exception e) {
		HashMap<String, Object> error = new HashMap<>();
		error.put("erro", e.getMessage());
		
		model.setErro(error);
	}

	public void validateCiaListar() {
		
	}
	
	public String ciaListar() {
		try {
			List<CompanhiaSeguradoraVO> cias = grupoAcessoFacade
					.obterCompanhias();
			
			model.setResponse(cias);
		} catch (Exception e) {
			handleException(e);
		}
		
		return SUCCESS;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	public void validateCiaDepartamentos() {
		String codigoCia = model.getCodigoCia();
		if (codigoCia == null || codigoCia.isEmpty()) {
			addFieldError("codigoCia", ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO);
		}
	}
	
	public String ciaDepartamentos() {
		try {
			int codigoCia = Integer.parseInt(model.getCodigoCia());
			CompanhiaSeguradoraVO ciaVO = new CompanhiaSeguradoraVO(codigoCia);
		
			List<DepartamentoVO> deptos = grupoAcessoFacade.obterDepartamentos(ciaVO);
			
			for (DepartamentoVO vo : deptos) {
				vo.setDeposito(null);
			}
			
			model.setResponse(deptos);
		} catch (Exception e) {
			handleException(e);
		}
		
		return SUCCESS;
	}
	
	public String ciaDeptosComRestricao() {
		try {
			int codigoCia = Integer.parseInt(model.getCodigoCia());
			int codUsuarioLogado = getCodUsuarioLogado();
			
			List<DepartamentoVO> deptos = grupoAcessoFacade.obterDepartamentos(
					codUsuarioLogado, new CompanhiaSeguradoraVO(codigoCia));
			
			for (DepartamentoVO vo : deptos) {
				vo.setDeposito(null);
			}
			
			model.setResponse(deptos);
		} catch (Exception e) {
			handleException(e);
		}
		
		return SUCCESS;
	}
	
	public String agencia() {
		
		try {
			int codBanco = Integer.parseInt(model.getCodigoBanco());
			int codAgencia = Integer.parseInt(model.getCodigoAgencia());
			
			ContaCorrenteAutorizadaVO ccVO = new ContaCorrenteAutorizadaVO(new BancoVO(codBanco), codAgencia, 0);
			String descricaoAgencia = contaCorrenteFacade.obterAgencia(ccVO);
			model.setResponse(Collections.singletonMap("agencia", descricaoAgencia));
		}
		catch (Exception e) {
			handleException(e);
		}
		
		return SUCCESS;
	}
	
	public String contaInterna() {
		
		try {
			int codCia = Integer.parseInt(model.getCodigoCia());
			int codBanco = Integer.parseInt(model.getCodigoBanco());
			int codAgencia = Integer.parseInt(model.getCodigoAgencia());
			int codCC = Integer.parseInt(model.getContaCorrente());
			
			ContaCorrenteAutorizadaVO ccVO = new ContaCorrenteAutorizadaVO(new BancoVO(codBanco), codAgencia, codCC);
			ccVO.setCia(new CompanhiaSeguradoraVO(codCia));
			
			Integer contaInterna = contaCorrenteFacade.obterContaInterna(ccVO);
			model.setResponse(Collections.singletonMap("contaInterna", contaInterna));
		}
		catch (Exception e) {
			handleException(e);
		}
		
		return SUCCESS;
		
	}
	
	public String ciaBancos() {

		try {
			int codCia = Integer.parseInt(model.getCodigo().get("cia"));
			List<BancoVO> bancos = associarMotivoFacade.obterBancos(new CompanhiaSeguradoraVO(codCia));
			
			model.setResponse(bancos);		

		} catch (Exception e) {
			handleException(e);
		}
		
		return SUCCESS;
	}
	
	public String ciaBancoAgencias() {
		
		try {
			int codCia = Integer.parseInt(model.getCodigo().get("cia"));
			int codBanco = Integer.parseInt(model.getCodigo().get("banco"));
			
			List<AgenciaVO> agencias = associarMotivoFacade.obterAgencias(
					new CompanhiaSeguradoraVO(codCia), new BancoVO(codBanco));
			model.setResponse(agencias);
			
		} catch (Exception e) {
			handleException(e);
		}
		
		return SUCCESS;
	}
	
	public String ciaBancoAgenciaConta() {
		
		try {
			int codUsuario = getCodUsuarioLogado();
			int codCia = Integer.parseInt(model.getCodigo().get("cia"));
			int codBanco = Integer.parseInt(model.getCodigo().get("banco"));
			int codAg = Integer.parseInt(model.getCodigo().get("agencia"));
			
			List<ContaCorrenteAutorizadaVO> contas = associarMotivoFacade
					.obterContas(codUsuario, new CompanhiaSeguradoraVO(codCia),
							new BancoVO(codBanco), new AgenciaVO(codAg));
			model.setResponse(contas);
			
		} catch (Exception e) {
			handleException(e);
		}
		
		return SUCCESS;
	}
	
	public String descricaoEventoContabil() {
		int codigoEvento = Integer.parseInt(model.getCodigo().get("evento"));
		
		EventoContabilVO evento = associarMotivoFacade.obterEventoContabil(codigoEvento);
		model.setResponse(evento);
		return SUCCESS;
	}
	
	public String pessoasCorporativas() {
		String cpfCnpj = model.getCodigo().get("cpfCnpj");
		List<ListarPessoaPorFiltroSaidaVO> pessoas = depositoFacade.listarPessoas(cpfCnpj, getIp(), getCodUsuarioLogado());
		model.setResponse(pessoas);
		return SUCCESS;
	}
	
	public String parametro() {
		Map<String, String> codigo = model.getCodigo();
		int cia = Integer.parseInt(codigo.get("cia"));
		int depto = Integer.parseInt(codigo.get("depto"));
		int motivo = Integer.parseInt(codigo.get("motivo"));
		
		DepositoVO vo = new DepositoVO();
		vo.setCia(new CompanhiaSeguradoraVO(cia));
		vo.setDepartamento(new DepartamentoVO(depto));
		vo.setMotivoDeposito(new MotivoDepositoVO(motivo));
		
		ParametroDepositoVO parametro = depositoFacade.obterParametro(vo);
		model.setResponse(parametro);
		
		return SUCCESS;
	}
	
	public String relatorioComboMotivo() {
		int cia = Integer.parseInt(model.getCodigo().get("cia"));
		int depto = Integer.parseInt(model.getCodigo().get("depto"));
		
		LoginVo loginVO = getUsuarioLogado();
		
		try {
			List<MotivoDepositoVO> motivos = relatorioFacade
					.obterMotivoComRestricaoDeDeposito(cia, depto, loginVO);
			model.setResponse(motivos);
		} catch (Exception e) {
			LOGGER.error("Falha ao carregar motivos", e);
			addActionError(e.getMessage());
			model.setErro(e.getMessage());
			model.setResponse(null);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
}
