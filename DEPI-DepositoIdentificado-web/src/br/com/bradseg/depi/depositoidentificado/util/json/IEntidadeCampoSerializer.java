package br.com.bradseg.depi.depositoidentificado.util.json;

import java.io.IOException;

import br.com.bradseg.depi.depositoidentificado.model.enumerated.IEntidadeCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoCampo;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;

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
		generator.writeObjectField("campo", campo.toString());
		
		String descricao = getDescricao(campo);
		
		generator.writeObjectField("descricao", descricao);
		generator.writeObjectField("tipo", tipoCampo);
		generator.writeEndObject();
	}
	
	private String getDescricao(IEntidadeCampo campo) {
		String classe = campo.getClass().getSimpleName();
		if (campo instanceof Enum) {
			Enum<?> enumCampo = (Enum<?>) campo;
			String chave = String.format("enum.%s.%s", classe, enumCampo.name());
			return BaseUtil.getTexto(chave);
		}
		return BaseUtil.getTexto(String.format("entidade.%s", classe));
	}

}
