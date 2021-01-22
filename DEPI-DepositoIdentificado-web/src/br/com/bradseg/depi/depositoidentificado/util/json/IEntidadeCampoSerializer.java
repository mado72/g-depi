package br.com.bradseg.depi.depositoidentificado.util.json;

import java.io.IOException;

import br.com.bradseg.depi.depositoidentificado.model.enumerated.IEntidadeCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoCampo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Conversor JSON para {@link IEntidadeCampo}
 *  
 * @author Marcelo Damasceno
 */
public class IEntidadeCampoSerializer extends
		JsonSerializer<IEntidadeCampo> {

	@Override
	public void serialize(IEntidadeCampo campo, JsonGenerator generator,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		TipoCampo tipoCampo = campo.getTipoCampo();

		generator.writeStartObject();
		generator.writeObjectField("campo", campo.getNome());
		generator.writeObjectField("descricao", campo.getDescricao());
		generator.writeObjectField("tipo", tipoCampo);
		generator.writeEndObject();
	}

}
