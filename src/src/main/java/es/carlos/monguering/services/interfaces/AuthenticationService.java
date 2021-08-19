package es.carlos.monguering.services.interfaces;

import es.carlos.monguering.entities.Player;
import es.carlos.monguering.rest.dto.response.LoginResponse;

public interface AuthenticationService {

	public LoginResponse login(String login, String password);
	
	public Player authorize(String token);
}
