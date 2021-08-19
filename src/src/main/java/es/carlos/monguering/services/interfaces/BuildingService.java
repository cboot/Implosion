package es.carlos.monguering.services.interfaces;

import es.carlos.monguering.dto.BuildingSet;
import es.carlos.monguering.entities.Planet;
import es.carlos.monguering.enumerations.BuildingType;
import es.carlos.monguering.json.upgrades.UpgradeLevel;

public interface BuildingService {
	
	public BuildingSet<UpgradeLevel> getAvailableBuildings(Planet planet);
	
	public BuildingSet<UpgradeLevel> getCurrentBuildings(Planet planet);
	
	public void upgradeBuilding(Planet planet, BuildingType building);

}
