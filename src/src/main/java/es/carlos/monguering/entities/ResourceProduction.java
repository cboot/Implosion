package es.carlos.monguering.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import es.carlos.monguering.dto.ResourceSet;
import es.carlos.monguering.enumerations.ResourceType;

@Embeddable
public class ResourceProduction {

	private ResourceSet resources = new ResourceSet();
	
	private Long resource0;
	
	private Long resource1;

	@Transient
	public Long get(ResourceType resourceType) {
		return resources.get(resourceType);
	}

	public void set(ResourceType resourceType, Long quantity) {
		this.resources.set(resourceType, quantity);
	}

	@Transient
	public ResourceSet getResources() {
		return resources;
	}

	public void setResources(ResourceSet resources) {
		this.resources = resources;
	}

	@Column(name = "RESOURCE_0_PRODUCTION", nullable = false)
	public long getResource0() {
		return resource0;
	}

	public void setResource0(Long resource0) {
		this.resource0 = resource0;
	}

	@Column(name = "RESOURCE_1_PRODUCTION", nullable = false)
	public long getResource1() {
		return resource1;
	}

	public void setResource1(Long resource1) {
		this.resource1 = resource1;
	}
}
