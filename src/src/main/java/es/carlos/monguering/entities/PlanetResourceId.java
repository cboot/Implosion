package es.carlos.monguering.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import es.carlos.monguering.enumerations.ResourceType;

public class PlanetResourceId implements Serializable {

	private static final long serialVersionUID = -7809344820076246950L;

	private Planet owner;

	private ResourceType resourceType;

	@ManyToOne
	@JoinColumn(name = "PLANET")
	public Planet getOwner() {
		return owner;
	}

	public void setOwner(Planet owner) {
		this.owner = owner;
	}

	@Column(name = "RESOURCE_TYPE")
	@Enumerated(EnumType.ORDINAL)
	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((resourceType == null) ? 0 : resourceType.hashCode());
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
		PlanetResourceId other = (PlanetResourceId) obj;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (resourceType != other.resourceType)
			return false;
		return true;
	}

}
