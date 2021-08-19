package es.carlos.monguering.enumerations;

public enum ResearchType {
	HYDROPONIC("getHydroponic");

	private ResearchType(String camelName) {
		this.camelName = camelName;
	}

	private String camelName;

	public String getCamelName() {
		return camelName;
	}

}