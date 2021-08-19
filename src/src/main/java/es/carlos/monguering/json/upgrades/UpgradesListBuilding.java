package es.carlos.monguering.json.upgrades;

import java.util.HashMap;
import java.util.Map;

import es.carlos.monguering.enumerations.BuildingType;

public class UpgradesListBuilding {

	private Map<BuildingType, UpgradeInfo> map = new HashMap<>();

	public UpgradeInfo get(BuildingType type) {
		return map.get(type);
	}

	public void set(BuildingType type, UpgradeInfo upgradeInfo) {
		map.put(type, upgradeInfo);
	}

}
