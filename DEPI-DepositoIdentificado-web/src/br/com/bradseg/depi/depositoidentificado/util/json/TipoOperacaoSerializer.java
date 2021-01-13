package br.com.bradseg.depi.depositoidentificado.util.json;

import java.io.IOException;

import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoOperacao;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class TipoOperacaoSerializer extends
		JsonSerializer<TipoOperacao> {

	@Override
	public void serialize(TipoOperacao tipoOperacao, JsonGenerator generator,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		TipoCampo tipoCampo = tipoOperacao.getTipoCampo();

		generator.writeStartObject();
		generator.writeObjectField("descricao", tipoOperacao.getDescricao());
		generator.writeObjectField("tipoCampo", tipoCampo);
		generator.writeEndObject();
	}

}
