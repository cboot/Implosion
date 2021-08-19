package es.carlos.monguering.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import es.carlos.monguering.enumerations.ResearchType;

public class PlayerResearchId implements Serializable {

	private static final long serialVersionUID = -3691986851953129866L;

	private Player owner;

	private ResearchType researchType;

	@ManyToOne
	@JoinColumn(name = "PLAYER")
	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	@Column(name = "RESEARCH_TYPE")
	@Enumerated(EnumType.ORDINAL)
	public ResearchType getResearchType() {
		return researchType;
	}

	public void setResearchType(ResearchType researchType) {
		this.researchType = researchType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((researchType == null) ? 0 : researchType.hashCode());
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
		PlayerResearchId other = (PlayerResearchId) obj;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (researchType != other.researchType)
			return false;
		return true;
	}

}
