package es.carlos.monguering.rest.dto.response;

import java.util.HashMap;
import java.util.Map;

import es.carlos.monguering.dto.OptionalInfo;

public class PlayerRest {

	private int id;

	private String name;

	private Map<Integer, PlanetRest> planets = new HashMap<>();

	private OptionalInfo playerOptionalInfo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<Integer, PlanetRest> getPlanets() {
		return planets;
	}

	public void setPlanets(Map<Integer, PlanetRest> planets) {
		this.planets = planets;
	}

	public OptionalInfo getPlayerOptionalInfo() {
		return playerOptionalInfo;
	}

	public void setPlayerOptionalInfo(OptionalInfo playerOptionalInfo) {
		this.playerOptionalInfo = playerOptionalInfo;
	}

}
