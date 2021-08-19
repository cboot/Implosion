package es.carlos.monguering.services.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.carlos.monguering.entities.Planet;
import es.carlos.monguering.entities.PlanetBuilding;
import es.carlos.monguering.entities.PlanetBuildingId;
import es.carlos.monguering.entities.Player;
import es.carlos.monguering.enumerations.BuildingType;
import es.carlos.monguering.enumerations.ResourceType;
import es.carlos.monguering.json.upgrades.UpgradeLevel;
import es.carlos.monguering.repositories.interfaces.PlayerRepository;
import es.carlos.monguering.services.interfaces.PlayerService;
import es.carlos.monguering.util.UpgradesUtils;

@Service
public class PlayerServiceImpl implements PlayerService {

	private Logger log = Logger.getLogger(PlayerService.class);

	@Autowired
	private PlayerRepository playerRepository;

	@Override
	public void updateResourcesAllPlanets(Player player, LocalDateTime upToDate) {
		for (Planet aPlanet : player.getPlanets()) {
			updateAllResourcesInner(aPlanet, upToDate);
			aPlanet.setLastUpdated(upToDate);
		}
		playerRepository.saveAndFlush(player);
	}

	@Override
	public void updateResourcesOnPlanet(Planet planet, LocalDateTime upToDate) {
		for (ResourceType aResourceType : ResourceType.values()) {
			updateResourcesInner(planet, upToDate, aResourceType);
		}
		planet.setLastUpdated(upToDate);
		playerRepository.saveAndFlush(planet.getOwner());
	}

	@Override
	public void checkForUpgradesOnPlanet(Planet planet) {
		checkForUpgradesInner(planet);
	}

	@Override
	public void checkForUpgradesAllPlanets(Player player) {
		for (Planet aPlanet : player.getPlanets()) {
			checkForUpgradesInner(aPlanet);
		}

	}

	private void updateResourcesInner(Planet planet, LocalDateTime upToDate, ResourceType resourceType) {
		log.debug("updateResources " + planet + " " + upToDate + " " + resourceType);
		long secondsPassed = planet.getLastUpdated().until(upToDate, ChronoUnit.SECONDS);

		if (log.isTraceEnabled()) {
			log.trace("There were " + planet.getCurrentAmount().get(resourceType) + " units of " + resourceType);
		}

		long increase = planet.getCurrentProduction().get(resourceType) * secondsPassed;
		increase = increase + increase * planet.getCurrentMultiplier().get(resourceType) / 100;
		planet.getCurrentAmount().addResource(resourceType, increase);

		if (log.isTraceEnabled()) {
			log.trace("There are " + planet.getCurrentAmount().get(resourceType) + " units of " + resourceType);
		}
	}

	private void updateAllResourcesInner(Planet planet, LocalDateTime upToDate) {
		for (ResourceType aResourceType : ResourceType.values()) {
			updateResourcesInner(planet, upToDate, aResourceType);
		}
	}

	private void checkForUpgradesInner(Planet planet) {
		if (planet.getCurrentlyBuilding() == null && (planet.getBuildingCompleted() == null
				|| LocalDateTime.now().isBefore(planet.getBuildingCompleted()))) {
			return;
		}
		LocalDateTime completionDate = planet.getBuildingCompleted();
		BuildingType buildingType = planet.getCurrentlyBuilding();

		int currentLevel = UpgradesUtils.getCurrentLevel(planet, buildingType);
		if (currentLevel == 0) {
			PlanetBuildingId id = new PlanetBuildingId();
			id.setOwner(planet);
			id.setBuildingType(buildingType);
			PlanetBuilding planetBuilding = new PlanetBuilding();
			planetBuilding.setBuildingType(buildingType);
			planetBuilding.setOwner(planet);
			planetBuilding.setLevel(1);
			planet.getPlanetBuildings().add(planetBuilding);
			planet.getBuildings().put(buildingType, planetBuilding);
		} else {
			planet.getBuildings().get(buildingType).setLevel(currentLevel + 1);
		}
		planet.setCurrentlyBuilding(null);
		planet.setBuildingCompleted(null);
		updateAllResourcesInner(planet, completionDate);

		UpgradeLevel buildingLevelInfo = UpgradesUtils.getBuildingLevelInfo(buildingType,
				planet.getBuildings().get(buildingType).getLevel());
		for (ResourceType aResourceType : ResourceType.values()) {
			planet.getCurrentProduction().addResource(aResourceType,
					buildingLevelInfo.getProductionIncrease().get(aResourceType));
			planet.getCurrentMultiplier().addResource(aResourceType,
					buildingLevelInfo.getMultiplierIncrease().get(aResourceType));
		}
	}
}
