package es.carlos.monguering.util.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import es.carlos.monguering.dto.ResourceSet;
import es.carlos.monguering.enumerations.ResourceType;

public class ResourceSetJsonDeserializer extends JsonDeserializer<ResourceSet>{

	@Override
	public ResourceSet deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ResourceSet output = new ResourceSet();
		ObjectCodec codec = p.getCodec();
		JsonNode root = codec.readTree(p);
		
		for (int i = 0; i < ResourceType.values().length; i++) {
			JsonNode node = root.get("" + i);
			if (node != null) { 
				output.set(ResourceType.values()[i], node.asLong());
			}
		}
		return output;
	        
	}

	
}
