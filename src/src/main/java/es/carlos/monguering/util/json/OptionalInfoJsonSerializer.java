package es.carlos.monguering.util.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import es.carlos.monguering.dto.OptionalInfo;
import es.carlos.monguering.enumerations.OptionalInfoType;


public class OptionalInfoJsonSerializer extends JsonSerializer<OptionalInfo>{

	@Override
	public void serialize(OptionalInfo value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		for (OptionalInfoType anOptionalInfoType: OptionalInfoType.values()) {
			gen.writeObjectField(anOptionalInfoType.name(),value.get(anOptionalInfoType));
		}
		gen.writeEndObject();
	}

	
}
