package es.carlos.monguering.dto;

import java.util.HashMap;
import java.util.Map;

import es.carlos.monguering.enumerations.BuildingType;

public class BuildingSet<T> {

	private Map<BuildingType, T> buildings = new HashMap<>();

	public T get(BuildingType buildingType, T defaultValue) {
		return buildings.getOrDefault(buildingType, defaultValue);
	}
	
	public void set(BuildingType buildingType, T value) {
		buildings.put(buildingType, value);
	}
}
