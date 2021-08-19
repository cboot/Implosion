package es.carlos.monguering.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import es.carlos.monguering.dto.OptionalInfo;
import es.carlos.monguering.dto.ResourceSet;
import es.carlos.monguering.enumerations.BuildingType;

@Entity
@Table(name = "PLANET")
public class Planet {

	private int id;

	private Player owner;

	private LocalDateTime lastUpdated;

	private List<PlanetResource> planetResources = new ArrayList<>();

	private List<PlanetBuilding> planetBuildings = new ArrayList<>();

	private ResourceSet currentAmount = new ResourceSet();

	private ResourceSet currentProduction = new ResourceSet();

	private ResourceSet currentMultiplier = new ResourceSet();

	private BuildingType currentlyBuilding;

	private LocalDateTime buildingCompleted;

	private OptionalInfo optionals = new OptionalInfo();

	private Map<BuildingType, PlanetBuilding> buildings = new HashMap<>();

	@PostLoad
	public void postLoad() {
		for (PlanetResource aPlanetResource : planetResources) {
			currentAmount.set(aPlanetResource.getResourceType(), aPlanetResource.getAmount());
			currentProduction.set(aPlanetResource.getResourceType(), aPlanetResource.getProduction());
			currentMultiplier.set(aPlanetResource.getResourceType(), aPlanetResource.getMultiplier());
		}

		for (PlanetBuilding aPlanetBuilding : planetBuildings) {
			buildings.put(aPlanetBuilding.getBuildingType(), aPlanetBuilding);
		}
	}

	@PreUpdate
	public void preUpdate() {
		for (PlanetResource aPlanetResource : planetResources) {
			aPlanetResource.setAmount(currentAmount.get(aPlanetResource.getResourceType()));
			aPlanetResource.setProduction(currentProduction.get(aPlanetResource.getResourceType()));
			aPlanetResource.setMultiplier(currentMultiplier.get(aPlanetResource.getResourceType()));
		}
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "OWNER")
	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	@Column(name = "LAST_UPDATED")
	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	public List<PlanetResource> getPlanetResources() {
		return planetResources;
	}

	public void setPlanetResources(List<PlanetResource> planetResources) {
		this.planetResources = planetResources;
	}

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	public List<PlanetBuilding> getPlanetBuildings() {
		return planetBuildings;
	}

	public void setPlanetBuildings(List<PlanetBuilding> planetBuildings) {
		this.planetBuildings = planetBuildings;
	}

	@Column(name = "CURRENTLY_BUILDING")
	@Enumerated(EnumType.ORDINAL)
	public BuildingType getCurrentlyBuilding() {
		return currentlyBuilding;
	}

	public void setCurrentlyBuilding(BuildingType currentlyBuilding) {
		this.currentlyBuilding = currentlyBuilding;
	}

	@Column(name = "BUILDING_COMPLETED")
	public LocalDateTime getBuildingCompleted() {
		return buildingCompleted;
	}

	public void setBuildingCompleted(LocalDateTime buildingCompleted) {
		this.buildingCompleted = buildingCompleted;
	}

	@Transient
	public ResourceSet getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(ResourceSet currentAmount) {
		this.currentAmount = currentAmount;
	}

	@Transient
	public ResourceSet getCurrentProduction() {
		return currentProduction;
	}

	public void setCurrentProduction(ResourceSet currentProduction) {
		this.currentProduction = currentProduction;
	}

	@Transient
	public Map<BuildingType, PlanetBuilding> getBuildings() {
		return buildings;
	}

	public void setBuildings(Map<BuildingType, PlanetBuilding> buildings) {
		this.buildings = buildings;
	}

	@Transient
	public ResourceSet getCurrentMultiplier() {
		return currentMultiplier;
	}

	public void setCurrentMultiplier(ResourceSet currentMultiplier) {
		this.currentMultiplier = currentMultiplier;
	}

	@Transient
	public OptionalInfo getOptionals() {
		return optionals;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentAmount == null) ? 0 : currentAmount.hashCode());
		result = prime * result + ((currentProduction == null) ? 0 : currentProduction.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastUpdated == null) ? 0 : lastUpdated.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((planetResources == null) ? 0 : planetResources.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planet other = (Planet) obj;
		if (currentAmount == null) {
			if (other.currentAmount != null)
				return false;
		} else if (!currentAmount.equals(other.currentAmount))
			return false;
		if (currentProduction == null) {
			if (other.currentProduction != null)
				return false;
		} else if (!currentProduction.equals(other.currentProduction))
			return false;
		if (id != other.id)
			return false;
		if (lastUpdated == null) {
			if (other.lastUpdated != null)
				return false;
		} else if (!lastUpdated.equals(other.lastUpdated))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (planetResources == null) {
			if (other.planetResources != null)
				return false;
		} else if (!planetResources.equals(other.planetResources))
			return false;
		return true;
	}

}
