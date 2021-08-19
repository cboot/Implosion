class BuildingUtils {

	
	constructor(planetInfo) {
		this.buildingsNextLevel =  planetInfo.buildingsNextLevel;
		this.buildingsCurrentLevel =  planetInfo.buildingsCurrentLevel;
	
	}
	
	getNextLevel(buildingType) {
		return this.buildingsNextLevel[buildingType].level;
	}
	
	getCurrentLevel(buildingType) {
		return this.getNextLevel(buildingType) - 1;
	}
	
	getNextLevelCost(buildingType) {
		return this.buildingsNextLevel[buildingType].cost;
	}
	
	getNextLevelProduction(buildingType) {
		return this.buildingsNextLevel[buildingType].productionIncrease;
	}
	
	getNextLevelMultiplier(buildingType) {
		return this.buildingsNextLevel[buildingType].multiplierIncrease;
	}
	
	isBuilt(buildingType) {
		return this.getCurrentLevel(buildingType) > 0;
	}
	
	getCurrentLevelCost(buildingType){
		return this.buildingsCurrentLevel[buildingType].cost;
	}
	
	getCurrentLevelProduction(buildingType) {
		return this.buildingsCurrentLevel[buildingType].productionIncrease;
	}
	
	getCurrentLevelMultiplier(buildingType) {
		return this.buildingsCurrentLevel[buildingType].multiplierIncrease;
	}
	
}