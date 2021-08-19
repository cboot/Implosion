package es.carlos.monguering.dto;

import java.util.HashMap;
import java.util.Map;

import es.carlos.monguering.entities.PlayerResearch;
import es.carlos.monguering.enumerations.ResearchType;

public class ResearchSet {

	private Map<ResearchType, Integer> researchs = new HashMap<>();

	public Integer get(ResearchType researchType) {
		return researchs.getOrDefault(researchType, 0);
	}

	public void set(ResearchType researchType, Integer level) {
		this.researchs.put(researchType, level);
	}
	
	public static ResearchSet fromMap(Map<ResearchType, PlayerResearch> researchs) {
		ResearchSet output = new ResearchSet();
		for (ResearchType aResearchType : ResearchType.values()) {
			if (researchs.get(aResearchType) != null) {
				output.set(aResearchType, researchs.get(aResearchType).getLevel());
			}
		}
		return output;
	}
}
