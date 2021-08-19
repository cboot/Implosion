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

import es.carlos.monguering.enumerations.ResourceType;

@Entity
@Table(name = "PLANET_RESOURCES")
@IdClass(PlanetResourceId.class)
public class PlanetResource {

	private Planet owner;

	private ResourceType resourceType;

	private long amount;

	private long production;
	
	private long multiplier;

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
	@Column(name = "RESOURCE_TYPE")
	@Enumerated(EnumType.ORDINAL)
	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	@Column(name = "AMOUNT")
	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	@Column(name = "PRODUCTION")
	public long getProduction() {
		return production;
	}

	public void setProduction(long production) {
		this.production = production;
	}

	@Column(name = "MULTIPLIER")
	public long getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(long multiplier) {
		this.multiplier = multiplier;
	}

	
}
