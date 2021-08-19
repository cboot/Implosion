package es.carlos.monguering.chainprocessor;

import es.carlos.monguering.enumerations.OptionalInfoType;
import es.carlos.monguering.services.interfaces.BuildingService;

public class GetAvailableBuildingsChainItem extends BaseChainProcessor {

	private BuildingService buildingService;

	public GetAvailableBuildingsChainItem(BuildingService buildingService) {
		this.buildingService = buildingService;

	}

	@Override
	public void process() {
		getPlanet().getOptionals().set(OptionalInfoType.AVAILABLE_BUILDINGS, buildingService.getAvailableBuildings(getPlanet()));
		getPlanet().getOptionals().set(OptionalInfoType.CURRENT_BUILDINGS, buildingService.getCurrentBuildings(getPlanet()));

	}
}
