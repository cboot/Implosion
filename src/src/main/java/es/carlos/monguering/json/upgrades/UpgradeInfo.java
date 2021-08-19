package es.carlos.monguering.json.upgrades;

import java.util.ArrayList;
import java.util.List;

public class UpgradeInfo {
	
	private String description;
	
	private List<UpgradeLevel> levels = new ArrayList<>();

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UpgradeLevel> getLevels() {
		return levels;
	}

	public void setLevels(List<UpgradeLevel> levels) {
		this.levels = levels;
	}
	
	
}
