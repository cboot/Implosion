package es.carlos.monguering.json.upgrades;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import es.carlos.monguering.dto.ResourceSet;
import es.carlos.monguering.enumerations.BuildingType;
import es.carlos.monguering.enumerations.ResearchType;
import es.carlos.monguering.util.json.ResourceSetJsonDeserializer;

public class UpgradeLevel {

	private int level;

	private List<UpgradeRequirement<BuildingType>> buildingsRequired = new ArrayList<>();

	private List<UpgradeRequirement<ResearchType>> researchsRequired = new ArrayList<>();

	private ResourceSet cost;

	private int secondsToBuild;

	private ResourceSet productionIncrease;

	private ResourceSet multiplierIncrease;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<UpgradeRequirement<BuildingType>> getBuildingsRequired() {
		return buildingsRequired;
	}

	public void setBuildingsRequired(List<UpgradeRequirement<BuildingType>> buildingsRequired) {
		this.buildingsRequired = buildingsRequired;
	}

	public List<UpgradeRequirement<ResearchType>> getResearchsRequired() {
		return researchsRequired;
	}

	public void setResearchsRequired(List<UpgradeRequirement<ResearchType>> researchsRequired) {
		this.researchsRequired = researchsRequired;
	}

	@JsonDeserialize(using = ResourceSetJsonDeserializer.class)
	public ResourceSet getCost() {
		return cost;
	}

	public void setCost(ResourceSet cost) {
		this.cost = cost;
	}

	public int getSecondsToBuild() {
		return secondsToBuild;
	}

	public void setSecondsToBuild(int secondsToBuild) {
		this.secondsToBuild = secondsToBuild;
	}

	@JsonDeserialize(using = ResourceSetJsonDeserializer.class)
	public ResourceSet getProductionIncrease() {
		return productionIncrease;
	}

	public void setProductionIncrease(ResourceSet productionIncrease) {
		this.productionIncrease = productionIncrease;
	}

	@JsonDeserialize(using = ResourceSetJsonDeserializer.class)
	public ResourceSet getMultiplierIncrease() {
		return multiplierIncrease;
	}

	public void setMultiplierIncrease(ResourceSet multiplierIncrease) {
		this.multiplierIncrease = multiplierIncrease;
	}

}
