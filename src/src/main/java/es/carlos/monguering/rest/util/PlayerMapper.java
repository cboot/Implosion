package es.carlos.monguering.rest.util;

import es.carlos.monguering.dto.BuildingSet;
import es.carlos.monguering.entities.Planet;
import es.carlos.monguering.entities.Player;
import es.carlos.monguering.entities.ResourceProduction;
import es.carlos.monguering.enumerations.OptionalInfoType;
import es.carlos.monguering.json.upgrades.UpgradeLevel;
import es.carlos.monguering.rest.dto.response.PlanetRest;
import es.carlos.monguering.rest.dto.response.PlayerRest;
import es.carlos.monguering.rest.dto.response.ResourceProductionRest;

public class PlayerMapper {

	private PlayerMapper() {
	}

	public static PlayerRest toRest(Player player) {
		PlayerRest output = new PlayerRest();
		output.setId(player.getId());
		output.setName(player.getName());
		for (Planet aPlanet : player.getPlanets()) {
			output.getPlanets().put(aPlanet.getId(), toRest(aPlanet));
		}
		output.setPlayerOptionalInfo(player.getOptionals());
		return output;
	}

	@SuppressWarnings("unchecked")
	public static PlanetRest toRest(Planet planet) {
		PlanetRest output = new PlanetRest();
		output.setId(planet.getId());
		output.setResourceAmount(planet.getCurrentAmount());
		output.setResourceProduction(planet.getCurrentProduction());
		output.setLastUpdated(planet.getLastUpdated());
		output.setBuildingsNextLevel((BuildingSet<UpgradeLevel>)planet.getOptionals().get(OptionalInfoType.AVAILABLE_BUILDINGS));
		output.setBuildingsCurrentLevel((BuildingSet<UpgradeLevel>)planet.getOptionals().get(OptionalInfoType.CURRENT_BUILDINGS));
		return output;
	}

	public static ResourceProductionRest toRest(ResourceProduction resourceProduction) {
		ResourceProductionRest output = new ResourceProductionRest();
		output.setResources(resourceProduction.getResources());
		return output;
	}

}
