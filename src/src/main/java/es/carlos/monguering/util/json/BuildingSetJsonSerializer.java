package es.carlos.monguering.util.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import es.carlos.monguering.dto.BuildingSet;
import es.carlos.monguering.enumerations.BuildingType;

public class BuildingSetJsonSerializer<T> extends JsonSerializer<BuildingSet<T>>{

	@Override
	public void serialize(BuildingSet<T> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		for (int i = 0; i < BuildingType.values().length; i++) {
			gen.writeObjectField(""+i, value.get(BuildingType.values()[i], null));
		}
		gen.writeEndObject();
	}

	
} 
