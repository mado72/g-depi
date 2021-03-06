package br.com.bradseg.depi.depositoidentificado.util.json;

import java.io.IOException;

import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoOperacao;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;

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
		generator.writeStartObject();
		String name = tipoOperacao.name();
		String chave = String.format("enum.TipoOperacao.%s", name);
		generator.writeObjectField("n", name);
		String descricao = BaseUtil.getTexto(chave);
		generator.writeObjectField("d", descricao);
		generator.writeEndObject();
	}

}
