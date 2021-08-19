package es.carlos.monguering.chainprocessor;

import es.carlos.monguering.services.interfaces.PlayerService;

public class CheckForUpgradesChainItem extends BaseChainProcessor {

	private PlayerService playerService;
	
	public CheckForUpgradesChainItem(PlayerService playerService) {
		this.playerService = playerService;
	}

	@Override
	public void process() {
		playerService.checkForUpgradesOnPlanet(getPlanet());
	}

}
