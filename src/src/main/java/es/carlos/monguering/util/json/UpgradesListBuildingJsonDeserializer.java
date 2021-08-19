package es.carlos.monguering.util.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import es.carlos.monguering.enumerations.BuildingType;
import es.carlos.monguering.json.upgrades.UpgradeInfo;
import es.carlos.monguering.json.upgrades.UpgradesListBuilding;

public class UpgradesListBuildingJsonDeserializer extends JsonDeserializer<UpgradesListBuilding>{

	@Override
	public UpgradesListBuilding deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		UpgradesListBuilding output = new UpgradesListBuilding();
		ObjectCodec codec = p.getCodec();
		JsonNode root = codec.readTree(p);
		
		for (BuildingType aBuildingType: BuildingType.values()) {
			JsonNode node = root.get(aBuildingType.ordinal()+"");
			if (node != null) { 
				JsonParser parser = node.traverse();
				parser.setCodec(p.getCodec());
				output.set(aBuildingType, parser.readValueAs(UpgradeInfo.class));
			}
		}
		return output;
	}

	
}
