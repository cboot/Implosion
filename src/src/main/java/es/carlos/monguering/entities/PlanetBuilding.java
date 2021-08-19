package es.carlos.monguering.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.carlos.monguering.enumerations.BuildingType;

@Entity
@Table(name = "PLANET_BUILDINGS")
@IdClass(PlanetBuildingId.class)
public class PlanetBuilding {

	private Planet owner;

	private BuildingType buildingType;

	private int level;

	@Id
	@ManyToOne
	@JoinColumn(name = "PLANET")
	public Planet getOwner() {
		return owner;
	}

	public void setOwner(Planet owner) {
		this.owner = owner;
	}

	@Id
	@Column(name = "BUILDING_TYPE")
	@Enumerated(EnumType.ORDINAL)
	public BuildingType getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(BuildingType resourceType) {
		this.buildingType = resourceType;
	}

	@Column(name = "CURRENT_LEVEL")
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	
}
