package br.com.bradseg.depi.depositoidentificado.form.cadastro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.form.AdmfinBPFiltroForm;
import br.com.bradseg.depi.depositoidentificado.model.cadastro.MotivoDepositoTipoOperacao;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.MotivoDepositoCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoOperacao;
import br.com.bradseg.depi.depositoidentificado.util.json.DepiObjectMapper;

/**
 * Representa o modelo do formulário da consulta para Motivo de Depósito 
 * @author Marcelo Damasceno
 */
public class MotivoDepositoConsultarForm extends AdmfinBPFiltroForm {

	private static final long serialVersionUID = -1473697395894253132L;

	private DepiObjectMapper mapper = new DepiObjectMapper();
	
	public static final String NOME_FORM = MotivoDepositoFiltroForm.class.getSimpleName();
	
	private List<MotivoDepositoTipoOperacao> motivoOperacaoList;
	
	public MotivoDepositoConsultarForm() {
		motivoOperacaoList = new ArrayList<>();
		for (MotivoDepositoCampo motivoDeposito : MotivoDepositoCampo.valuesForCriteria()) {
			List<TipoOperacao> operacoes = TipoOperacao.obterPorTipoCampo(motivoDeposito.getTipoCampo());
			
			MotivoDepositoTipoOperacao item = new MotivoDepositoTipoOperacao();
			item.setMotivoDeposito(motivoDeposito);
			item.setOperacoes(operacoes);
			
			motivoOperacaoList.add(item);
		}
	}

	@Override
	public String getContextoFiltro() {
		return NOME_FORM;
	}
	
	/**
	 * @return JSON com a estrutura de dados utilizados para o funcionamento do filtro.
	 */
	public String getMotivoOperacaoJson() {
		try {
			return new String(mapper.writeValueAsBytes(motivoOperacaoList), "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException("Falha ao converter em json", e);
		}
	}

}
