package es.carlos.monguering.rest.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import es.carlos.monguering.dto.BuildingSet;
import es.carlos.monguering.dto.OptionalInfo;
import es.carlos.monguering.dto.ResourceSet;
import es.carlos.monguering.json.upgrades.UpgradesListBuilding;
import es.carlos.monguering.json.upgrades.UpgradesListResearch;
import es.carlos.monguering.util.json.BuildingSetJsonSerializer;
import es.carlos.monguering.util.json.OptionalInfoJsonSerializer;
import es.carlos.monguering.util.json.ResourceSetJsonDeserializer;
import es.carlos.monguering.util.json.ResourceSetJsonSerializer;
import es.carlos.monguering.util.json.UpgradesListBuildingJsonDeserializer;
import es.carlos.monguering.util.json.UpgradesListResearchJsonDeserializer;

public class CustomObjectMapper extends ObjectMapper {
	
	private static final long serialVersionUID = -8603971718614782456L;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CustomObjectMapper() {
		super();
	   
		SimpleModule buildingSet = new SimpleModule();
	    buildingSet.addSerializer(BuildingSet.class, new BuildingSetJsonSerializer());
	    registerModule(buildingSet);
	    
	    SimpleModule optionalInfo = new SimpleModule();
	    optionalInfo.addSerializer(OptionalInfo.class, new OptionalInfoJsonSerializer());
	    registerModule(optionalInfo);
	    
	    SimpleModule resourceSetSerializer = new SimpleModule();
	    resourceSetSerializer.addSerializer(ResourceSet.class, new ResourceSetJsonSerializer());
	    registerModule(resourceSetSerializer);
	    
	    SimpleModule resourceSetDeserializer = new SimpleModule();
	    resourceSetSerializer.addDeserializer(ResourceSet.class, new ResourceSetJsonDeserializer());
	    registerModule(resourceSetDeserializer);
	    
	    SimpleModule upgradeListBuildingJsonDeserializer = new SimpleModule();
	    upgradeListBuildingJsonDeserializer.addDeserializer(UpgradesListBuilding.class, new UpgradesListBuildingJsonDeserializer());
	    registerModule(upgradeListBuildingJsonDeserializer);
	    
	    SimpleModule upgradesListResearchJsonDeserializer = new SimpleModule();
	    upgradesListResearchJsonDeserializer.addDeserializer(UpgradesListResearch.class, new UpgradesListResearchJsonDeserializer());
	    registerModule(upgradesListResearchJsonDeserializer);
	    
	    enable(SerializationFeature.WRITE_ENUMS_USING_INDEX);
	}
}
