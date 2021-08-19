package es.carlos.monguering.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.carlos.monguering.enumerations.ResearchType;

@Entity
@Table(name = "PLAYER_RESEARCH")
@IdClass(PlayerResearchId.class)
public class PlayerResearch {

	private Player owner;

	private ResearchType researchType;

	private int level;

	@Id
	@ManyToOne
	@JoinColumn(name = "PLAYER")
	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	@Id
	@Column(name = "RESEARCH_TYPE")
	@Enumerated(EnumType.ORDINAL)
	public ResearchType getResearchType() {
		return researchType;
	}

	public void setResearchType(ResearchType researchType) {
		this.researchType = researchType;
	}

	@Column(name = "CURRENT_LEVEL")
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
