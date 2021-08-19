package es.carlos.monguering.interfaces;

import es.carlos.monguering.dto.ResourceSet;

public interface Upgradable {

	int getCurrentLevel();
	
	ResourceSet getUpgradeCost();
}
