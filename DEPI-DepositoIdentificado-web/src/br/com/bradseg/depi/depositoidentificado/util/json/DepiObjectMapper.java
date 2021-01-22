package br.com.bradseg.depi.depositoidentificado.util.json;

import br.com.bradseg.depi.depositoidentificado.model.enumerated.IEntidadeCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoOperacao;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Classe que configura o ObjectMapper com conversões já definidas.
 * 
 * @author Marcelo Damasceno
 */
public class DepiObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = -8045418945845937463L;

	public DepiObjectMapper() {
		super();
		setSerializationInclusion(Include.NON_NULL);
		configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		SimpleModule module = new SimpleModule();
		module.addSerializer(TipoOperacao.class, new TipoOperacaoSerializer());
		module.addSerializer(IEntidadeCampo.class, new IEntidadeCampoSerializer());
	}
}
