package es.carlos.monguering.services.interfaces;

import java.time.LocalDateTime;

import es.carlos.monguering.entities.Planet;
import es.carlos.monguering.entities.Player;

public interface PlayerService {

	public void updateResourcesAllPlanets(Player player, LocalDateTime upToDate);
	
	public void updateResourcesOnPlanet(Planet planet, LocalDateTime upToDate);
	
	public void checkForUpgradesAllPlanets(Player player);

	public void checkForUpgradesOnPlanet(Planet planet);
	
}
