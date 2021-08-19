package es.carlos.monguering.util;

import java.io.IOException;

import es.carlos.monguering.entities.Planet;
import es.carlos.monguering.entities.Player;
import es.carlos.monguering.enumerations.BuildingType;
import es.carlos.monguering.enumerations.ResearchType;
import es.carlos.monguering.json.upgrades.UpgradeInfo;
import es.carlos.monguering.json.upgrades.UpgradeLevel;
import es.carlos.monguering.json.upgrades.UpgradesListBuilding;
import es.carlos.monguering.json.upgrades.UpgradesListResearch;
import es.carlos.monguering.rest.util.CustomObjectMapper;

public class UpgradesUtils {

	private static UpgradesListBuilding buildingsList;

	private static UpgradesListResearch researchsList;

	static { 
		try {
			buildingsList = new CustomObjectMapper().readValue(
					UpgradesUtils.class.getResourceAsStream("/config/buildings.json").readAllBytes(),
					UpgradesListBuilding.class);
			researchsList = new CustomObjectMapper().readValue(
					UpgradesUtils.class.getResourceAsStream("/config/researchs.json").readAllBytes(),
					UpgradesListResearch.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private UpgradesUtils() {

	}

	public static UpgradesListBuilding getBuildingsList() {
		return buildingsList;
	}

	public static UpgradesListResearch getResearchsList() {
		return researchsList;
	}

	public static int getCurrentLevel(Planet planet, BuildingType buildingType) {
		return planet.getBuildings().get(buildingType) != null ? planet.getBuildings().get(buildingType).getLevel() : 0;
	}

	public static int getCurrentLevel(Player player, ResearchType researchType) {
		return player.getResearchs().get(researchType) != null ? player.getResearchs().get(researchType).getLevel() : 0;
	}

	public static UpgradeInfo getBuildingInfo(BuildingType buildingType) {
		return (UpgradeInfo) buildingsList.get(buildingType);
	}

	public static UpgradeInfo getResearchInfo(ResearchType researchType) {
		return (UpgradeInfo) researchsList.get(researchType);
	}

	public static UpgradeLevel getBuildingLevelInfo(BuildingType buildingType, int level) {
		return getBuildingInfo(buildingType).getLevels().get(level - 1);
	}

	public static UpgradeLevel getNextBuildingLevelInfo(Planet planet, BuildingType buildingType) {
		return getBuildingLevelInfo(buildingType, UpgradesUtils.getCurrentLevel(planet, buildingType) + 1);
	}
}
