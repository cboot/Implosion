package es.carlos.monguering.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.carlos.monguering.enumerations.OptionalInfoType;

public class OptionalInfo {

	private Map<OptionalInfoType, Object> optionals = new HashMap<>();

	public Object get(OptionalInfoType optionalInfoType ) {
		return optionals.get(optionalInfoType);
	}

	public void set(OptionalInfoType optionalInfoType, Object optional) {
		optionals.put(optionalInfoType, optional);
	}
	
	public List<Object> asList() {
		return new ArrayList<>(optionals.values());
	}

}
