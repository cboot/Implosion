package es.carlos.monguering.util.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import es.carlos.monguering.enumerations.ResearchType;
import es.carlos.monguering.json.upgrades.UpgradeInfo;
import es.carlos.monguering.json.upgrades.UpgradesListResearch;

public class UpgradesListResearchJsonDeserializer extends JsonDeserializer<UpgradesListResearch>{

	@Override
	public UpgradesListResearch deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		UpgradesListResearch output = new UpgradesListResearch();
		ObjectCodec codec = p.getCodec();
		JsonNode root = codec.readTree(p);
		
		for (ResearchType aResearchType: ResearchType.values()) {
			JsonNode node = root.get(aResearchType.ordinal());
			if (node != null) { 
				JsonParser parser = node.traverse();
				parser.setCodec(p.getCodec());
				output.set(aResearchType, parser.readValueAs(UpgradeInfo.class));
			}
		}
		return output;
	}

	
}
