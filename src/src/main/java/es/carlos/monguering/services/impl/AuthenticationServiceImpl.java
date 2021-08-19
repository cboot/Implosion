package es.carlos.monguering.services.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.carlos.monguering.entities.Player;
import es.carlos.monguering.repositories.interfaces.PlayerRepository;
import es.carlos.monguering.rest.dto.response.LoginResponse;
import es.carlos.monguering.security.exceptions.InvalidCredentialsException;
import es.carlos.monguering.security.exceptions.InvalidTokenException;
import es.carlos.monguering.services.interfaces.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private static final int TOKEN_MAXIMUM_EXPIRATION_MINUTES = 5;
	@Autowired
	private PlayerRepository playerRepository;

	@Override
	public LoginResponse login(String username, String password) throws InvalidCredentialsException {
		Player player = playerRepository.findByNameIgnoreCaseAndPassword(username, password);
		if (player == null) {
			throw new InvalidCredentialsException("Username is invalid or password does not match");
		}
		LoginResponse output = new LoginResponse();
		output.setToken(generateToken(player));
		output.setCurrentPlanetId(player.getPlanets().get(0).getId());
		return output;
	}
	
	public Player authorize(String token) throws InvalidTokenException {
		Player player = playerRepository.findByToken(token);
		if (player == null) {
			throw new InvalidTokenException("Invalid token");
		}
		checkTokenValidity(player);
		updateTokenValidity(player);
		return player;
	}

	private String generateToken(Player player) {
		String token = UUID.randomUUID().toString();
		player.setToken(token);
		updateTokenValidity(player);
		return token;
	}
	
	private void updateTokenValidity(Player player) {
		player.setTokenValidUntil(LocalDateTime.now().plus(TOKEN_MAXIMUM_EXPIRATION_MINUTES, ChronoUnit.MINUTES));
		playerRepository.save(player);
	}
	
	private void checkTokenValidity(Player player)  throws InvalidTokenException {
		if (player.getTokenValidUntil().isBefore(LocalDateTime.now())) {
			throw new InvalidTokenException("Token has expired");
		}
	}
	
}
