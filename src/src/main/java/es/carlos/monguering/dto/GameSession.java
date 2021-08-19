package es.carlos.monguering.dto;

import es.carlos.monguering.entities.Planet;
import es.carlos.monguering.entities.Player;

public class GameSession {

	private Player player;

	private Planet planet;

	public GameSession(Player player, Planet planet) {
		this.player = player;
		this.planet = planet;
	}

	public GameSession() {
		super();
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Planet getPlanet() {
		return planet;
	}

	public void setPlanet(Planet planet) {
		this.planet = planet;
	}

}
