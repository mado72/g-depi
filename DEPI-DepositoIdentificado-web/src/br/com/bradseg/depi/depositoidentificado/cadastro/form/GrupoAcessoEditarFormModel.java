package br.com.bradseg.depi.depositoidentificado.cadastro.form;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.util.CollectionUtils;

import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.UsuarioVO;

/**
 * Representa o modelo do formulário para Grupo de Acesso 
 * @author Marcelo Damasceno
 */
public class GrupoAcessoEditarFormModel extends CrudForm {

	private static final long serialVersionUID = 957768938376772158L;

	private String codigoGrupoAcesso;
	
	private String siglaDepartamento;
	
	private String codCompanhia;
	
	private String nomeGrupoAcesso;
	
	private List<String> codFuncionarios = new ArrayList<>();
	
	private List<UsuarioVO> funcionarios = new ArrayList<>();
	
	private List<CompanhiaSeguradoraVO> cias = new ArrayList<>();
	
	private List<DepartamentoVO> deptos = new ArrayList<>();
	
	private Object json;
	
	@Override
	public boolean isDetalhar() {
		return getEstado() == EstadoCrud.EXIBIR;
	}

	@Override
	public void limparDados() {
		setCodigo("");
		codigoGrupoAcesso = "";
		siglaDepartamento = "";
		codCompanhia = null;
		json = null;

		setCodFuncionarios(new ArrayList<String>());
		setDeptos(new ArrayList<DepartamentoVO>());
		setFuncionarios(new ArrayList<UsuarioVO>());
	}

	/**
	 * Retorna cias
	 * @return o cias
	 */
	public List<CompanhiaSeguradoraVO> getCias() {
		return cias;
	}

	/**
	 * Define cias
	 * @param cias valor cias a ser definido
	 */
	public void setCias(List<CompanhiaSeguradoraVO> cias) {
		this.cias = cias;
	}

	/**
	 * Retorna dptos
	 * @return o dptos
	 */
	public List<DepartamentoVO> getDeptos() {
		return deptos;
	}

	/**
	 * Define dptos
	 * @param dptos valor dptos a ser definido
	 */
	public void setDeptos(List<DepartamentoVO> dptos) {
		this.deptos = dptos;
	}

	/**
	 * Retorna codigoGrupoAcesso
	 * @return o codigoGrupoAcesso
	 */
	public String getCodigoGrupoAcesso() {
		return codigoGrupoAcesso;
	}

	/**
	 * Define codigoGrupoAcesso
	 * @param codigoGrupoAcesso valor codigoGrupoAcesso a ser definido
	 */
	public void setCodigoGrupoAcesso(String codigoGrupoAcesso) {
		this.codigoGrupoAcesso = codigoGrupoAcesso;
	}

	/**
	 * Retorna siglaDepartamento
	 * @return o siglaDepartamento
	 */
	public String getSiglaDepartamento() {
		return siglaDepartamento;
	}

	/**
	 * Define siglaDepartamento
	 * @param siglaDepartamento valor siglaDepartamento a ser definido
	 */
	public void setSiglaDepartamento(String siglaDepartamento) {
		this.siglaDepartamento = siglaDepartamento;
	}

	/**
	 * Retorna codCompanhia
	 * @return o codCompanhia
	 */
	public String getCodCompanhia() {
		return codCompanhia;
	}

	/**
	 * Define codCompanhia
	 * @param codCompanhia valor codCompanhia a ser definido
	 */
	public void setCodCompanhia(String codCompanhia) {
		this.codCompanhia = codCompanhia;
	}
	
	/**
	 * Retorna funcionarios
	 * @return o funcionarios
	 */
	public List<UsuarioVO> getFuncionarios() {
		return funcionarios;
	}
	
	/**
	 * Define funcionarios
	 * @param funcionarios valor funcionarios a ser definido
	 */
	public void setFuncionarios(List<UsuarioVO> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	/**
	 * Retorna nomeGrupoAcesso
	 * @return o nomeGrupoAcesso
	 */
	public String getNomeGrupoAcesso() {
		return nomeGrupoAcesso;
	}
	
	/**
	 * Define nomeGrupoAcesso
	 * @param nomeGrupoAcesso valor nomeGrupoAcesso a ser definido
	 */
	public void setNomeGrupoAcesso(String nomeGrupoAcesso) {
		this.nomeGrupoAcesso = nomeGrupoAcesso;
	}

	/**
	 * Retorna codFuncionarios
	 * @return o codFuncionarios
	 */
	public List<String> getCodFuncionarios() {
		return codFuncionarios;
	}
	
	/**
	 * Método utilitário para retornar os códigos dos funcionários como inteiros
	 * @return Lista de códigos
	 */
	public List<Integer> getCodFuncionariosInt() {
		if (CollectionUtils.isEmpty(getCodFuncionarios())) {
			return Collections.emptyList();
		}
		
		ArrayList<Integer> list = new ArrayList<>(getCodFuncionarios().size());
		for (String cod : codFuncionarios) {
			list.add(new Integer(cod));
		}
		return list;
	}

	/**
	 * Define codFuncionarios
	 * @param codFuncionarios valor codFuncionarios a ser definido
	 */
	public void setCodFuncionarios(List<String> codFuncionarios) {
		this.codFuncionarios = codFuncionarios;
	}
	
	/**
	 * Retorna json com o resultado da operação Ajax
	 * @return o json
	 */
	public Object getJson() {
		return json;
	}
	
	/**
	 * Define json com o resultado Ajax
	 * @param json valor json a ser definido
	 */
	public void setJson(Object json) {
		this.json = json;
	}
	
}
