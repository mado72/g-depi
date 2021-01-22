package br.com.bradseg.depi.depositoidentificado.util.json;

import java.io.IOException;

import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoOperacao;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Conversor JSON para {@link TipoOperacao}
 * @author Marcelo Damasceno
 */
public class TipoOperacaoSerializer extends
		JsonSerializer<TipoOperacao> {

	@Override
	public void serialize(TipoOperacao tipoOperacao, JsonGenerator generator,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		TipoCampo tipoCampo = tipoOperacao.getTipoCampo();

		generator.writeStartObject();
		generator.writeObjectField("n", tipoOperacao.name());
		generator.writeObjectField("d", tipoOperacao.getDescricao());
		generator.writeObjectField("t", tipoCampo);
		generator.writeEndObject();
	}

}
