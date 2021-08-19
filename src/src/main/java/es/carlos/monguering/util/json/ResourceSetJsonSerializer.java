package es.carlos.monguering.util.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import es.carlos.monguering.dto.ResourceSet;
import es.carlos.monguering.enumerations.ResourceType;

public class ResourceSetJsonSerializer extends JsonSerializer<ResourceSet>{

	@Override
	public void serialize(ResourceSet value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		for (int i = 0; i < ResourceType.values().length; i++) {
			gen.writeStringField(""+i, value.get(ResourceType.values()[i]).toString());
		}
		gen.writeEndObject();
	}

	
}
