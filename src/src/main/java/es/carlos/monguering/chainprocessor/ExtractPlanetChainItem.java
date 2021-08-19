package es.carlos.monguering.chainprocessor;

import es.carlos.monguering.entities.Planet;
import es.carlos.monguering.rest.dto.request.BaseRequest;

public class ExtractPlanetChainItem extends BaseChainProcessor {

	private BaseRequest request;

	public ExtractPlanetChainItem(BaseRequest request) {
		this.request = request;
	}

	@Override
	public void process() {
		if (request.getCurrentPlanetId() == 0) {
			getGameSession().setPlanet(getGameSession().getPlayer().getPlanets().get(0));
		} else {
			for (Planet aPlanet : getGameSession().getPlayer().getPlanets()) {
				if (aPlanet.getId() == request.getCurrentPlanetId()) {
					getGameSession().setPlanet(aPlanet);
					break;
				}
			}
		}
	}

}
