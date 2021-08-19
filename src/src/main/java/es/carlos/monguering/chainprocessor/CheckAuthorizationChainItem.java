package es.carlos.monguering.chainprocessor;

import es.carlos.monguering.entities.Player;
import es.carlos.monguering.services.interfaces.AuthenticationService;

public class CheckAuthorizationChainItem extends BaseChainProcessor {

	
	private String token;
	
	private AuthenticationService authenticationService;
	
	public CheckAuthorizationChainItem(String token, AuthenticationService authenticationService) {
		this.token = token;
		this.authenticationService = authenticationService;
	}
	
	@Override
	public void process() {
		Player player = authenticationService.authorize(token);
		getGameSession().setPlayer(player);
	}
	
	
}
