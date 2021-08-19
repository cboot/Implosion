package es.carlos.monguering.rest.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import es.carlos.monguering.dto.ResourceSet;
import es.carlos.monguering.util.json.ResourceSetJsonDeserializer;
import es.carlos.monguering.util.json.ResourceSetJsonSerializer;

public class ResourceProductionRest {

	private ResourceSet resources;

	@JsonSerialize(using = ResourceSetJsonSerializer.class)
	@JsonDeserialize(using = ResourceSetJsonDeserializer.class)
	public ResourceSet getResources() {
		return resources;
	}

	public void setResources(ResourceSet resources) {
		this.resources = resources;
	}

}
