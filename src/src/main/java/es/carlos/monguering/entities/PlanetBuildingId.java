package es.carlos.monguering.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import es.carlos.monguering.enumerations.BuildingType;

public class PlanetBuildingId implements Serializable {

	private static final long serialVersionUID = -7809344820076246950L;

	private Planet owner;

	private BuildingType buildingType;

	@ManyToOne
	@JoinColumn(name = "PLANET")
	public Planet getOwner() {
		return owner;
	}

	public void setOwner(Planet owner) {
		this.owner = owner;
	}

	@Column(name = "BUILDING_TYPE")
	@Enumerated(EnumType.ORDINAL)
	public BuildingType getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(BuildingType buildingType) {
		this.buildingType = buildingType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buildingType == null) ? 0 : buildingType.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
		PlanetBuildingId other = (PlanetBuildingId) obj;
		if (buildingType != other.buildingType)
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}

}
