package es.carlos.monguering.rest.dto.response;

public class LoginResponse {

	private String token;
	
	private int currentPlanetId;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getCurrentPlanetId() {
		return currentPlanetId;
	}

	public void setCurrentPlanetId(int currentPlanetId) {
		this.currentPlanetId = currentPlanetId;
	}
	
	
}
