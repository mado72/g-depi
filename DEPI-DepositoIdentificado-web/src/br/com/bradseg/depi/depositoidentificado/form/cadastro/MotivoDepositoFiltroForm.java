package br.com.bradseg.depi.depositoidentificado.form.cadastro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.bradseg.depi.depositoidentificado.form.AdmfinBPFiltroForm;
import br.com.bradseg.depi.depositoidentificado.model.cadastro.MotivoDepositoTipoOperacao;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.MotivoDepositoCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoOperacao;
import br.com.bradseg.depi.depositoidentificado.util.json.DepiObjectMapper;

@Component
public class MotivoDepositoFiltroForm extends AdmfinBPFiltroForm {
	
	private DepiObjectMapper mapper = new DepiObjectMapper();

	private static final long serialVersionUID = -8222823833308525511L;
	
	public static final String NOME_FORM = MotivoDepositoFiltroForm.class.getSimpleName();
	
	private List<MotivoDepositoTipoOperacao> motivoOperacaoList;
	
	public MotivoDepositoFiltroForm() {
		motivoOperacaoList = new ArrayList<>();
		for (MotivoDepositoCampo motivoDeposito : getMotivoDepositoList()) {
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

	public MotivoDepositoCampo[] getMotivoDepositoList() {
		return MotivoDepositoCampo.valuesForCriteria();
	}
	
	public List<MotivoDepositoTipoOperacao> getMotivoOperacaoList() {
		return motivoOperacaoList;
	}
	
	public String getMotivoOperacaoJson() {
		try {
			return mapper.writeValueAsString(motivoOperacaoList);
		} catch (IOException e) {
			throw new RuntimeException("Falha ao converter em json", e);
		}
	}

}
