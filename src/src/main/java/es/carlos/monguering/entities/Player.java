package es.carlos.monguering.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import es.carlos.monguering.dto.OptionalInfo;
import es.carlos.monguering.enumerations.ResearchType;

@Entity
@Table(name = "PLAYER")
public class Player {

	private int id;

	private String name;

	private String password;

	private String token;

	private LocalDateTime tokenValidUntil;

	private List<Planet> planets = new ArrayList<>();

	private List<PlayerResearch> playerResearchs = new ArrayList<>();

	private Map<ResearchType, PlayerResearch> researchs = new HashMap<>();

	private OptionalInfo optionals = new OptionalInfo();

	@PostLoad
	public void postLoad() {
		for (PlayerResearch aPlayerResearch : playerResearchs) {
			researchs.put(aPlayerResearch.getResearchType(), aPlayerResearch);
		}
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "TOKEN")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(name = "TOKEN_VALID_UNTIL")
	public LocalDateTime getTokenValidUntil() {
		return tokenValidUntil;
	}

	public void setTokenValidUntil(LocalDateTime tokenValidUntil) {
		this.tokenValidUntil = tokenValidUntil;
	}


	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "owner")
	public List<Planet> getPlanets() {
		return planets;
	}

	public void setPlanets(List<Planet> planets) {
		this.planets = planets;
	}

	@OneToMany(mappedBy = "owner")
	public List<PlayerResearch> getPlayerResearchs() {
		return playerResearchs;
	}

	public void setPlayerResearchs(List<PlayerResearch> playerResearchs) {
		this.playerResearchs = playerResearchs;
	}

	@Transient
	public Map<ResearchType, PlayerResearch> getResearchs() {
		return researchs;
	}

	@Transient
	public OptionalInfo getOptionals() {
		return optionals;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((planets == null) ? 0 : planets.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (planets == null) {
			if (other.planets != null)
				return false;
		} else if (!planets.equals(other.planets))
			return false;
		return true;
	}

}
