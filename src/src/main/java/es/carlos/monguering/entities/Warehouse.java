package es.carlos.monguering.entities;

import es.carlos.monguering.dto.ResourceSet;
import es.carlos.monguering.enumerations.ResourceType;

public class Warehouse {

	private ResourceSet currentAmount;

	private ResourceProduction currentProduction;

	public void addResource(ResourceType resourceType, Long quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException("Unable to add negative quantity, use the substractResource instead");
		}

		this.getCurrentAmount().set(resourceType, this.getCurrentAmount().get(resourceType) + quantity);
	}

	public void substractResource(ResourceType resourceType, Long quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException(
					"Unable to add negative quantity, use this method with a possitive value instead");
		}

		this.getCurrentAmount().set(resourceType, this.getCurrentAmount().get(resourceType) - quantity);
	}

	public ResourceSet getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(ResourceSet currentAmount) {
		this.currentAmount = currentAmount;
	}

	public ResourceProduction getCurrentProduction() {
		return currentProduction;
	}

	public void setCurrentProduction(ResourceProduction currentProduction) {
		this.currentProduction = currentProduction;
	}

}
