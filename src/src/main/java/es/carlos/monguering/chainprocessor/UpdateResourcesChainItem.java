package es.carlos.monguering.chainprocessor;

import java.time.LocalDateTime;

import es.carlos.monguering.services.interfaces.PlayerService;

public class UpdateResourcesChainItem extends BaseChainProcessor {

	private LocalDateTime upToDate;
	
	private PlayerService playerService;
	
	public UpdateResourcesChainItem(PlayerService playerService, LocalDateTime upToDate) {
		this.playerService = playerService;
		this.upToDate = upToDate;
	}
	
	@Override
	public void process() {
		this.playerService.updateResourcesOnPlanet(getPlanet(), upToDate);
	}

}
