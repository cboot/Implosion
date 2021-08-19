package es.carlos.monguering.chainprocessor;

import es.carlos.monguering.enumerations.BuildingType;
import es.carlos.monguering.services.interfaces.BuildingService;

public class BuildChainItem extends BaseChainProcessor {

	private BuildingType buildingType;
	
	private BuildingService buildingService;
	
	public BuildChainItem(BuildingService buildingService, BuildingType buildingType) {
		this.buildingService = buildingService;
		this.buildingType = buildingType;
		
	}
	@Override
	public void process() {
		buildingService.upgradeBuilding(getPlanet(), buildingType);
	}

}
