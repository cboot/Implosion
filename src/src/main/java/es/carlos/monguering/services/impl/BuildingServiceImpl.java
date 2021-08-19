package es.carlos.monguering.services.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.carlos.monguering.dto.BuildingSet;
import es.carlos.monguering.dto.ResourceSet;
import es.carlos.monguering.entities.Planet;
import es.carlos.monguering.enumerations.BuildingType;
import es.carlos.monguering.enumerations.ResourceType;
import es.carlos.monguering.json.upgrades.UpgradeInfo;
import es.carlos.monguering.json.upgrades.UpgradeLevel;
import es.carlos.monguering.repositories.interfaces.PlayerRepository;
import es.carlos.monguering.services.interfaces.BuildingService;
import es.carlos.monguering.util.UpgradesUtils;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private PlayerRepository playerRepository;

	public BuildingServiceImpl() {

	}

	@Override
	public BuildingSet<UpgradeLevel> getAvailableBuildings(Planet planet) {
		BuildingSet<UpgradeLevel> output = new BuildingSet<UpgradeLevel>();
 		for (BuildingType aBuildingType: BuildingType.values()) {
			output.set(aBuildingType, UpgradesUtils.getNextBuildingLevelInfo(planet, aBuildingType));
		}
		return output;
	}
	
	@Override
	public BuildingSet<UpgradeLevel> getCurrentBuildings(Planet planet) {
		BuildingSet<UpgradeLevel> output = new BuildingSet<UpgradeLevel>();
 		for (BuildingType aBuildingType: BuildingType.values()) {
 			if (planet.getBuildings().get(aBuildingType) != null) { 
 				output.set(aBuildingType, UpgradesUtils.getNextBuildingLevelInfo(planet, aBuildingType));
 			}
		}
		return output;
	}

	@Override
	public void upgradeBuilding(Planet planet, BuildingType buildingType) {
		UpgradeInfo buildingInfo = UpgradesUtils.getBuildingInfo(buildingType);

		if (hasEnoughResourcesFor(planet, buildingType)) {
			planet.setCurrentlyBuilding(buildingType);
			LocalDateTime buildingCompleted = LocalDateTime.now();
			int currentLevel = UpgradesUtils.getCurrentLevel(planet, buildingType);
			buildingCompleted.plusSeconds(buildingInfo.getLevels().get(currentLevel).getSecondsToBuild());
			planet.setBuildingCompleted(buildingCompleted);
			playerRepository.save(planet.getOwner());
		} else {
			System.out.println("not enough resources for the upgrade");
		}

	}

	private boolean hasEnoughResourcesFor(Planet planet, BuildingType buildingType) {
		UpgradeInfo buildingInfo = UpgradesUtils.getBuildingInfo(buildingType);
		int currentLevel = UpgradesUtils.getCurrentLevel(planet, buildingType);
		ResourceSet buildingCost = buildingInfo.getLevels().get(currentLevel).getCost();

		for (ResourceType aResourceType : ResourceType.values()) {
			if (buildingCost.get(aResourceType) > planet.getCurrentAmount().get(aResourceType)) {
				return false;
			}
		}
		return true;
	}



}
