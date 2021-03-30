package br.com.bradseg.depi.depositoidentificado.cadastro.form;

import java.util.ArrayList;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.vo.AgenciaVO;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ParcelaCobrancaVO;

/**
 * Representa o modelo do formulário da consulta para Conta Corrente
 * 
 * @author Marcelo Damasceno
 */
public class DepositoEditarFormModel extends CrudForm {

	private static final long serialVersionUID = 957768938376772158L;
	
	private Long codigoDepositoIdentificado;
	
	private Long dv;
	
	private List<CompanhiaSeguradoraVO> cias = new ArrayList<>();
	
	private List<DepartamentoVO> deptos = new ArrayList<>();
	
	private List<MotivoDepositoVO> motivos = new ArrayList<>();
	
	private List<BancoVO> bancos = new ArrayList<>();
	
	private List<AgenciaVO> agencias = new ArrayList<>();
	
	private List<ContaCorrenteAutorizadaVO> contas = new ArrayList<>();
	
	private String codigoCompanhia;
	
	private String codigoDepartamento;
	
	private String codigoMotivoDeposito;
	
	private String descricaoDetalhadaMotivo;
	
	private String codBanco;
	
	private String descricaoBanco;
	
	private String codigoAgencia;
	
	private String descricaoAgencia;

	private String contaCorrente;

	private String contaInterna;
	
	private String cpfCnpj;
	
	private List<?> pessoasCorporativas;
	
	private Long pessoaDepositante;
	
	private String nomePessoa;
	
	private int sucursal;
	
	private int item;
	
	private String dossie;
	
	private long bloqueto;
	
	private int apolice;
	
	private int protocolo;
	
	private int tipoDocumento;
	
	private String ramo;
	
	private long endosso;
	
	private long parcela;
	
	private int tipoGrupoRecebimento;
	
	private String observacaoDeposito;
	
	private String indicadorDepositoProrrogado;
	
	private String dtVencimentoDeposito;
	
	private String indicadorDepositoCancelado;
	
	private String dataProrrogacao;
	
	private String vlrDepositoRegistrado;
	
	private String dtCancelamentoDepositoIdentificado;
	
	private int situacaoArquivoTransferencia;
	
	private long trps;
	
	private Integer codigoSituacaoDeposito;
	
	private List<ParcelaCobrancaVO> listaParcelas;
	
	private ParametroDepositoVO parametro;
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm#limparDados()
	 */
	@Override
	public void preencherDadosIniciais() {
		setCias(new ArrayList<CompanhiaSeguradoraVO>());
		setDeptos(new ArrayList<DepartamentoVO>());
		setMotivos(new ArrayList<MotivoDepositoVO>());
		setBancos(new ArrayList<BancoVO>());
		setAgencias(new ArrayList<AgenciaVO>());
		
		setCodigoAgencia(null);
		setCodigo(null);
		setCodBanco(null);
		setCodigoCompanhia(null);
		setContaCorrente(null);
		setContaInterna(null);
		setCodigoDepartamento(null);
		setCodigoMotivoDeposito(null);
		setDescricaoAgencia(null);
		setDescricaoBanco(null);
	}
	
	/**
	 * Preenche os valores de acordo com os dados do
	 * {@link DepositoVO} e os outros parâmetros advindos do CICS.
	 * 
	 * @param vo
	 *            DepositoVO
	 * @param descricaoAgencia
	 *            Descrição da agência (CICS)
	 * @param descricaoBanco
	 *            Descrição do banco (CICS)
	 */
	public void preencherCampos(DepositoVO vo,
			String descricaoAgencia, String descricaoBanco) {
		
		setCodigo(null);
		setCodigoAgencia(BaseUtil.getValueMaskFormat("99999", BaseUtil.blankIfNull(vo.getCodigoAgencia()), true));
		setCodBanco(BaseUtil.getValueMaskFormat("9999", BaseUtil.blankIfNull(vo.getBanco().getCdBancoExterno()), true));
		setCodigoCompanhia(String.valueOf(vo.getCia().getCodigoCompanhia()));
		setContaCorrente(BaseUtil.getValueMaskFormat("0000000000000", BaseUtil.blankIfNull(vo.getContaCorrente()), true));
		setContaInterna(BaseUtil.getValueMaskFormat("999999", BaseUtil.blankIfNull(vo.getBanco().getCdBancoInterno()), true));
		setCodigoDepartamento(String.valueOf(vo.getDepartamento().getCodigoDepartamento()));
		setCodigoMotivoDeposito(String.valueOf(vo.getMotivoDeposito().getCodigoMotivoDeposito()));
		
		// Campos que necessitam de consulta ao CICS
		setDescricaoAgencia(descricaoAgencia);
		setDescricaoBanco(descricaoBanco);
	}
	
	/**
	 * Preenche os dados em uma instância de {@link DepositoVO} a partir dos
	 * valores do formulário
	 * @param vo Instância de {@link DepositoVO}
	 */
	public void obterValores(DepositoVO vo) {
		
		vo.setCodigoAgencia(Integer.parseInt(getCodigoAgencia()));
		vo.setBanco(new BancoVO(Integer.parseInt(getCodBanco())));
		vo.setCia(new CompanhiaSeguradoraVO(Integer.parseInt(getCodigoCompanhia())));
		vo.setContaCorrente(Long.parseLong(getContaCorrente()));
		vo.setDepartamento(new DepartamentoVO(Integer.parseInt(getCodigoDepartamento())));
		vo.setMotivoDeposito(new MotivoDepositoVO(Integer.parseInt(getCodigoMotivoDeposito())));
		
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
	 * Retorna deptos
	 * @return o deptos
	 */
	public List<DepartamentoVO> getDeptos() {
		return deptos;
	}

	/**
	 * Define deptos
	 * @param deptos valor deptos a ser definido
	 */
	public void setDeptos(List<DepartamentoVO> deptos) {
		this.deptos = deptos;
	}

	/**
	 * Retorna motivos
	 * @return o motivos
	 */
	public List<MotivoDepositoVO> getMotivos() {
		return motivos;
	}

	/**
	 * Define motivos
	 * @param motivos valor motivos a ser definido
	 */
	public void setMotivos(List<MotivoDepositoVO> motivos) {
		this.motivos = motivos;
	}

	/**
	 * Retorna bancos
	 * @return o bancos
	 */
	public List<BancoVO> getBancos() {
		return bancos;
	}

	/**
	 * Define bancos
	 * @param bancos valor bancos a ser definido
	 */
	public void setBancos(List<BancoVO> bancos) {
		this.bancos = bancos;
	}

	/**
	 * Retorna agencias
	 * @return o agencias
	 */
	public List<AgenciaVO> getAgencias() {
		return agencias;
	}

	/**
	 * Define agencias
	 * @param agencias valor agencias a ser definido
	 */
	public void setAgencias(List<AgenciaVO> agencias) {
		this.agencias = agencias;
	}
	
	/**
	 * Retorna contas
	 * @return o contas
	 */
	public List<ContaCorrenteAutorizadaVO> getContas() {
		return contas;
	}
	
	/**
	 * Define contas
	 * @param contas valor contas a ser definido
	 */
	public void setContas(List<ContaCorrenteAutorizadaVO> contas) {
		this.contas = contas;
	}

	/**
	 * Retorna codigoCompanhia
	 * @return o codigoCompanhia
	 */
	public String getCodigoCompanhia() {
		return codigoCompanhia;
	}

	/**
	 * Define codigoCompanhia
	 * @param codigoCompanhia valor codigoCompanhia a ser definido
	 */
	public void setCodigoCompanhia(String codigoCompanhia) {
		this.codigoCompanhia = codigoCompanhia;
	}

	/**
	 * Retorna codigoDepartamento
	 * @return o codigoDepartamento
	 */
	public String getCodigoDepartamento() {
		return codigoDepartamento;
	}

	/**
	 * Define codigoDepartamento
	 * @param codigoDepartamento valor codigoDepartamento a ser definido
	 */
	public void setCodigoDepartamento(String codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

	/**
	 * Retorna codigoMotivo
	 * @return o codigoMotivo
	 */
	public String getCodigoMotivoDeposito() {
		return codigoMotivoDeposito;
	}

	/**
	 * Define codigoMotivo
	 * @param codigoMotivo valor codigoMotivo a ser definido
	 */
	public void setCodigoMotivoDeposito(String codigoMotivo) {
		this.codigoMotivoDeposito = codigoMotivo;
	}
	
	/**
	 * Retorna descricaoDetalhadaMotivo
	 * @return o descricaoDetalhadaMotivo
	 */
	public String getDescricaoDetalhadaMotivo() {
		return descricaoDetalhadaMotivo;
	}
	
	/**
	 * Define descricaoDetalhadaMotivo
	 * @param descricaoDetalhadaMotivo valor descricaoDetalhadaMotivo a ser definido
	 */
	public void setDescricaoDetalhadaMotivo(String descricaoDetalhadaMotivo) {
		this.descricaoDetalhadaMotivo = descricaoDetalhadaMotivo;
	}

	/**
	 * Retorna codigoBanco
	 * @return o codigoBanco
	 */
	public String getCodBanco() {
		return codBanco;
	}

	/**
	 * Define codigoBanco
	 * @param codigoBanco valor codigoBanco a ser definido
	 */
	public void setCodBanco(String codigoBanco) {
		this.codBanco = codigoBanco;
	}

	/**
	 * Retorna descricaoBanco
	 * @return o descricaoBanco
	 */
	public String getDescricaoBanco() {
		return descricaoBanco;
	}

	/**
	 * Define descricaoBanco
	 * @param descricaoBanco valor descricaoBanco a ser definido
	 */
	public void setDescricaoBanco(String descricaoBanco) {
		this.descricaoBanco = descricaoBanco;
	}

	/**
	 * Retorna agencia
	 * @return o agencia
	 */
	public String getCodigoAgencia() {
		return codigoAgencia;
	}

	/**
	 * Define agencia
	 * @param agencia valor agencia a ser definido
	 */
	public void setCodigoAgencia(String agencia) {
		this.codigoAgencia = agencia;
	}

	/**
	 * Retorna descricaoAgencia
	 * @return o descricaoAgencia
	 */
	public String getDescricaoAgencia() {
		return descricaoAgencia;
	}

	/**
	 * Define descricaoAgencia
	 * @param descricaoAgencia valor descricaoAgencia a ser definido
	 */
	public void setDescricaoAgencia(String descricaoAgencia) {
		this.descricaoAgencia = descricaoAgencia;
	}

	/**
	 * Retorna contaCorrente
	 * @return o contaCorrente
	 */
	public String getContaCorrente() {
		return contaCorrente;
	}

	/**
	 * Define contaCorrente
	 * @param contaCorrente valor contaCorrente a ser definido
	 */
	public void setContaCorrente(String contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	/**
	 * Retorna contaInterna
	 * @return o contaInterna
	 */
	public String getContaInterna() {
		return contaInterna;
	}

	/**
	 * Define contaInterna
	 * @param contaInterna valor contaInterna a ser definido
	 */
	public void setContaInterna(String contaInterna) {
		this.contaInterna = contaInterna;
	}
	
	/**
	 * Retorna cpfCnpj
	 * @return o cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	
	/**
	 * Define cpfCnpj
	 * @param cpfCnpj valor cpfCnpj a ser definido
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	
	/**
	 * Retorna pessoasCorporativas
	 * @return o pessoasCorporativas
	 */
	public List<?> getPessoasCorporativas() {
		return pessoasCorporativas;
	}
	
	/**
	 * Define pessoasCorporativas
	 * @param pessoasCorporativas valor pessoasCorporativas a ser definido
	 */
	public void setPessoasCorporativas(List<?> pessoasCorporativas) {
		this.pessoasCorporativas = pessoasCorporativas;
	}

	/**
	 * Retorna pessoaDepositante
	 * @return o pessoaDepositante
	 */
	public Long getPessoaDepositante() {
		return pessoaDepositante;
	}

	/**
	 * Define pessoaDepositante
	 * @param pessoaDepositante valor pessoaDepositante a ser definido
	 */
	public void setPessoaDepositante(Long pessoaDepositante) {
		this.pessoaDepositante = pessoaDepositante;
	}

	/**
	 * Retorna nomePessoa
	 * @return o nomePessoa
	 */
	public String getNomePessoa() {
		return nomePessoa;
	}

	/**
	 * Define nomePessoa
	 * @param nomePessoa valor nomePessoa a ser definido
	 */
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	/**
	 * Retorna sucursal
	 * @return o sucursal
	 */
	public int getSucursal() {
		return sucursal;
	}

	/**
	 * Define sucursal
	 * @param sucursal valor sucursal a ser definido
	 */
	public void setSucursal(int sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Retorna item
	 * @return o item
	 */
	public int getItem() {
		return item;
	}

	/**
	 * Define item
	 * @param item valor item a ser definido
	 */
	public void setItem(int item) {
		this.item = item;
	}

	/**
	 * Retorna dossie
	 * @return o dossie
	 */
	public String getDossie() {
		return dossie;
	}

	/**
	 * Define dossie
	 * @param dossie valor dossie a ser definido
	 */
	public void setDossie(String dossie) {
		this.dossie = dossie;
	}

	/**
	 * Retorna bloqueto
	 * @return o bloqueto
	 */
	public long getBloqueto() {
		return bloqueto;
	}

	/**
	 * Define bloqueto
	 * @param bloqueto valor bloqueto a ser definido
	 */
	public void setBloqueto(long bloqueto) {
		this.bloqueto = bloqueto;
	}

	/**
	 * Retorna apolice
	 * @return o apolice
	 */
	public int getApolice() {
		return apolice;
	}

	/**
	 * Define apolice
	 * @param apolice valor apolice a ser definido
	 */
	public void setApolice(int apolice) {
		this.apolice = apolice;
	}

	/**
	 * Retorna protocolo
	 * @return o protocolo
	 */
	public int getProtocolo() {
		return protocolo;
	}

	/**
	 * Define protocolo
	 * @param protocolo valor protocolo a ser definido
	 */
	public void setProtocolo(int protocolo) {
		this.protocolo = protocolo;
	}

	/**
	 * Retorna tipoDocumento
	 * @return o tipoDocumento
	 */
	public int getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Define tipoDocumento
	 * @param tipoDocumento valor tipoDocumento a ser definido
	 */
	public void setTipoDocumento(int tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Retorna ramo
	 * @return o ramo
	 */
	public String getRamo() {
		return ramo;
	}

	/**
	 * Define ramo
	 * @param ramo valor ramo a ser definido
	 */
	public void setRamo(String ramo) {
		this.ramo = ramo;
	}

	/**
	 * Retorna endosso
	 * @return o endosso
	 */
	public long getEndosso() {
		return endosso;
	}

	/**
	 * Define endosso
	 * @param endosso valor endosso a ser definido
	 */
	public void setEndosso(long endosso) {
		this.endosso = endosso;
	}

	/**
	 * Retorna parcela
	 * @return o parcela
	 */
	public long getParcela() {
		return parcela;
	}

	/**
	 * Define parcela
	 * @param parcela valor parcela a ser definido
	 */
	public void setParcela(long parcela) {
		this.parcela = parcela;
	}

	/**
	 * Retorna tipoGrupoRecebimento
	 * @return o tipoGrupoRecebimento
	 */
	public int getTipoGrupoRecebimento() {
		return tipoGrupoRecebimento;
	}

	/**
	 * Define tipoGrupoRecebimento
	 * @param tipoGrupoRecebimento valor tipoGrupoRecebimento a ser definido
	 */
	public void setTipoGrupoRecebimento(int tipoGrupoRecebimento) {
		this.tipoGrupoRecebimento = tipoGrupoRecebimento;
	}

	/**
	 * Retorna observacaoDeposito
	 * @return o observacaoDeposito
	 */
	public String getObservacaoDeposito() {
		return observacaoDeposito;
	}

	/**
	 * Define observacaoDeposito
	 * @param observacaoDeposito valor observacaoDeposito a ser definido
	 */
	public void setObservacaoDeposito(String observacaoDeposito) {
		this.observacaoDeposito = observacaoDeposito;
	}

	/**
	 * Retorna indicadorDepositoProrrogado
	 * @return o indicadorDepositoProrrogado
	 */
	public String getIndicadorDepositoProrrogado() {
		return indicadorDepositoProrrogado;
	}

	/**
	 * Define indicadorDepositoProrrogado
	 * @param indicadorDepositoProrrogado valor indicadorDepositoProrrogado a ser definido
	 */
	public void setIndicadorDepositoProrrogado(String indicadorDepositoProrrogado) {
		this.indicadorDepositoProrrogado = indicadorDepositoProrrogado;
	}

	/**
	 * Retorna dtVencimentoDeposito
	 * @return o dtVencimentoDeposito
	 */
	public String getDtVencimentoDeposito() {
		return dtVencimentoDeposito;
	}

	/**
	 * Define dtVencimentoDeposito
	 * @param dtVencimentoDeposito valor dtVencimentoDeposito a ser definido
	 */
	public void setDtVencimentoDeposito(String dtVencimentoDeposito) {
		this.dtVencimentoDeposito = dtVencimentoDeposito;
	}

	/**
	 * Retorna indicadorDepositoCancelado
	 * @return o indicadorDepositoCancelado
	 */
	public String getIndicadorDepositoCancelado() {
		return indicadorDepositoCancelado;
	}

	/**
	 * Define indicadorDepositoCancelado
	 * @param indicadorDepositoCancelado valor indicadorDepositoCancelado a ser definido
	 */
	public void setIndicadorDepositoCancelado(String indicadorDepositoCancelado) {
		this.indicadorDepositoCancelado = indicadorDepositoCancelado;
	}

	/**
	 * Retorna dataProrrogacao
	 * @return o dataProrrogacao
	 */
	public String getDataProrrogacao() {
		return dataProrrogacao;
	}

	/**
	 * Define dataProrrogacao
	 * @param dataProrrogacao valor dataProrrogacao a ser definido
	 */
	public void setDataProrrogacao(String dataProrrogacao) {
		this.dataProrrogacao = dataProrrogacao;
	}

	/**
	 * Retorna vlrDepositoRegistrado
	 * @return o vlrDepositoRegistrado
	 */
	public String getVlrDepositoRegistrado() {
		return vlrDepositoRegistrado;
	}

	/**
	 * Define vlrDepositoRegistrado
	 * @param vlrDepositoRegistrado valor vlrDepositoRegistrado a ser definido
	 */
	public void setVlrDepositoRegistrado(String vlrDepositoRegistrado) {
		this.vlrDepositoRegistrado = vlrDepositoRegistrado;
	}

	/**
	 * Retorna dtCancelamentoDepositoIdentificado
	 * @return o dtCancelamentoDepositoIdentificado
	 */
	public String getDtCancelamentoDepositoIdentificado() {
		return dtCancelamentoDepositoIdentificado;
	}

	/**
	 * Define dtCancelamentoDepositoIdentificado
	 * @param dtCancelamentoDepositoIdentificado valor dtCancelamentoDepositoIdentificado a ser definido
	 */
	public void setDtCancelamentoDepositoIdentificado(
			String dtCancelamentoDepositoIdentificado) {
		this.dtCancelamentoDepositoIdentificado = dtCancelamentoDepositoIdentificado;
	}

	/**
	 * Retorna situacaoArquivoTransferencia
	 * @return o situacaoArquivoTransferencia
	 */
	public int getSituacaoArquivoTransferencia() {
		return situacaoArquivoTransferencia;
	}

	/**
	 * Define situacaoArquivoTransferencia
	 * @param situacaoArquivoTransferencia valor situacaoArquivoTransferencia a ser definido
	 */
	public void setSituacaoArquivoTransferencia(int situacaoArquivoTransferencia) {
		this.situacaoArquivoTransferencia = situacaoArquivoTransferencia;
	}

	/**
	 * Retorna trps
	 * @return o trps
	 */
	public long getTrps() {
		return trps;
	}

	/**
	 * Define trps
	 * @param trps valor trps a ser definido
	 */
	public void setTrps(long trps) {
		this.trps = trps;
	}

	/**
	 * Retorna codigoSituacaoDeposito
	 * @return o codigoSituacaoDeposito
	 */
	public Integer getCodigoSituacaoDeposito() {
		return codigoSituacaoDeposito;
	}

	/**
	 * Define codigoSituacaoDeposito
	 * @param codigoSituacaoDeposito valor codigoSituacaoDeposito a ser definido
	 */
	public void setCodigoSituacaoDeposito(Integer codigoSituacaoDeposito) {
		this.codigoSituacaoDeposito = codigoSituacaoDeposito;
	}

	/**
	 * Retorna listaParcelas
	 * @return o listaParcelas
	 */
	public List<ParcelaCobrancaVO> getListaParcelas() {
		return listaParcelas;
	}

	/**
	 * Define listaParcelas
	 * @param listaParcelas valor listaParcelas a ser definido
	 */
	public void setListaParcelas(List<ParcelaCobrancaVO> listaParcelas) {
		this.listaParcelas = listaParcelas;
	}

	/**
	 * Retorna codigoDepositoIdentificado
	 * @return o codigoDepositoIdentificado
	 */
	public Long getCodigoDepositoIdentificado() {
		return codigoDepositoIdentificado;
	}

	/**
	 * Define codigoDepositoIdentificado
	 * @param codigoDepositoIdentificado valor codigoDepositoIdentificado a ser definido
	 */
	public void setCodigoDepositoIdentificado(Long codigoDepositoIdentificado) {
		this.codigoDepositoIdentificado = codigoDepositoIdentificado;
	}

	/**
	 * Retorna dv
	 * @return o dv
	 */
	public Long getDv() {
		return dv;
	}

	/**
	 * Define dv
	 * @param dv valor dv a ser definido
	 */
	public void setDv(Long dv) {
		this.dv = dv;
	}
	
	public ParametroDepositoVO getParametro() {
		return parametro;
	}
	
	public void setParametro(ParametroDepositoVO parametro) {
		this.parametro = parametro;
	}

}
