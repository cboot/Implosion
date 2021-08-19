package es.carlos.monguering.json.upgrades;

public class UpgradeRequirement<T> {

	private T type;
	
	private int level;

	public T getType() {
		return type;
	}

	public void setType(T type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
