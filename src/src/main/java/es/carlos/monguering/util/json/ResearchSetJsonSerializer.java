package es.carlos.monguering.util.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import es.carlos.monguering.dto.ResearchSet;
import es.carlos.monguering.enumerations.ResearchType;

public class ResearchSetJsonSerializer extends JsonSerializer<ResearchSet>{

	@Override
	public void serialize(ResearchSet value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		for (int i = 0; i < ResearchType.values().length; i++) {
			gen.writeStringField("research"+i, value.get(ResearchType.values()[i]).toString());
		}
		gen.writeEndObject();
	}

	
}
