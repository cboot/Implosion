package es.carlos.monguering.dto;

import java.util.HashMap;
import java.util.Map;

import es.carlos.monguering.enumerations.ResourceType;

public class ResourceSet {

	private Map<ResourceType, Long> resources = new HashMap<>();

	public Long get(ResourceType resourceType) {
		return resources.getOrDefault(resourceType, 0L);
	}

	public void set(ResourceType resourceType, Long quantity) {
		this.resources.put(resourceType, quantity);
	}

	public void addResource(ResourceType resourceType, Long quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException("Unable to add negative quantity, use the substractResource instead");
		}

		this.resources.put(resourceType, resources.get(resourceType) + quantity);
	}

	public void substractResource(ResourceType resourceType, Long quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException(
					"Unable to add negative quantity, use this method with a possitive value instead");
		}

		this.resources.put(resourceType, resources.get(resourceType) - quantity);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((resources == null) ? 0 : resources.hashCode());
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
		ResourceSet other = (ResourceSet) obj;
		if (resources == null) {
			if (other.resources != null)
				return false;
		} else if (!resources.equals(other.resources))
			return false;
		return true;
	}

}
