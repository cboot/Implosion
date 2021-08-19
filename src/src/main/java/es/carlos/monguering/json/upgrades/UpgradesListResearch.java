package es.carlos.monguering.json.upgrades;

import java.util.HashMap;
import java.util.Map;

import es.carlos.monguering.enumerations.ResearchType;

public class UpgradesListResearch {

	private Map<ResearchType, UpgradeInfo> map = new HashMap<>();

	public UpgradeInfo get(ResearchType type) {
		return map.get(type);
	}

	public void set(ResearchType type, UpgradeInfo upgradeInfo) {
		map.put(type, upgradeInfo);
	}

}
