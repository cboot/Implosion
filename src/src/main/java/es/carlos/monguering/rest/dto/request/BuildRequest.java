package es.carlos.monguering.rest.dto.request;

import es.carlos.monguering.enumerations.BuildingType;

public class BuildRequest extends BaseRequest {

	private BuildingType buildingType;

	public BuildingType getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(BuildingType buildingType) {
		this.buildingType = buildingType;
	}


}
