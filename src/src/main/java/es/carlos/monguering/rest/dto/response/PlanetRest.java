package es.carlos.monguering.rest.dto.response;

import java.time.LocalDateTime;

import es.carlos.monguering.dto.BuildingSet;
import es.carlos.monguering.dto.ResourceSet;
import es.carlos.monguering.json.upgrades.UpgradeLevel;

public class PlanetRest {

	private int id;

	private ResourceSet resourceAmount;

	private ResourceSet resourceProduction;

	private LocalDateTime lastUpdated;

	private BuildingSet<UpgradeLevel> buildingsNextLevel;

	private BuildingSet<UpgradeLevel> buildingsCurrentLevel;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ResourceSet getResourceAmount() {
		return resourceAmount;
	}

	public void setResourceAmount(ResourceSet resourceAmount) {
		this.resourceAmount = resourceAmount;
	}

	public ResourceSet getResourceProduction() {
		return resourceProduction;
	}

	public void setResourceProduction(ResourceSet resourceProduction) {
		this.resourceProduction = resourceProduction;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public BuildingSet<UpgradeLevel> getBuildingsNextLevel() {
		return buildingsNextLevel;
	}

	public void setBuildingsNextLevel(BuildingSet<UpgradeLevel> buildings) {
		this.buildingsNextLevel = buildings;
	}

	public BuildingSet<UpgradeLevel> getBuildingsCurrentLevel() {
		return buildingsCurrentLevel;
	}

	public void setBuildingsCurrentLevel(BuildingSet<UpgradeLevel> buildingsCurrentLevel) {
		this.buildingsCurrentLevel = buildingsCurrentLevel;
	}

}
