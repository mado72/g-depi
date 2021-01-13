package br.com.bradseg.depi.depositoidentificado.util.json;

import java.io.IOException;

import br.com.bradseg.depi.depositoidentificado.model.enumerated.MotivoDepositoCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoCampo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class MotivoDepositoCampoSerializer extends
		JsonSerializer<MotivoDepositoCampo> {

	@Override
	public void serialize(MotivoDepositoCampo campo, JsonGenerator generator,
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
