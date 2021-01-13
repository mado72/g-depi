package br.com.bradseg.depi.depositoidentificado.util.json;

import java.io.IOException;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoOperacao;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ListTipoOperacaoSerializer extends
		JsonSerializer<List<TipoOperacao>> {

	@Override
	public void serialize(List<TipoOperacao> lista, JsonGenerator generator,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		
		generator.writeStartArray();
		
		for (TipoOperacao tipoOperacao: lista) {
			TipoCampo tipoCampo = tipoOperacao.getTipoCampo();
			
			generator.writeStartObject();
			generator.writeObjectField("descricao", tipoOperacao.getDescricao());
			generator.writeObjectField("tipoCampo", tipoCampo);
			generator.writeEndObject();
		}
		
		generator.writeEndArray();
	}

}
