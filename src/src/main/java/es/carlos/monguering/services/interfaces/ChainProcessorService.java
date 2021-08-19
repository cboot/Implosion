package es.carlos.monguering.services.interfaces;

import es.carlos.monguering.entities.Player;
import es.carlos.monguering.rest.dto.request.BuildRequest;
import es.carlos.monguering.rest.dto.request.UpdateResourcesRequest;

public interface ChainProcessorService {
	
	public Player updateResources(String token, UpdateResourcesRequest request);
	
	public Player build(String token, BuildRequest request);
	
}
