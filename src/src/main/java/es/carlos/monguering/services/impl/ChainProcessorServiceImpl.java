package es.carlos.monguering.services.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.carlos.monguering.chainprocessor.BaseChainProcessor;
import es.carlos.monguering.chainprocessor.BuildChainItem;
import es.carlos.monguering.chainprocessor.CheckAuthorizationChainItem;
import es.carlos.monguering.chainprocessor.CheckForUpgradesChainItem;
import es.carlos.monguering.chainprocessor.ExtractPlanetChainItem;
import es.carlos.monguering.chainprocessor.GetAvailableBuildingsChainItem;
import es.carlos.monguering.chainprocessor.UpdateResourcesChainItem;
import es.carlos.monguering.entities.Player;
import es.carlos.monguering.rest.dto.request.BuildRequest;
import es.carlos.monguering.rest.dto.request.UpdateResourcesRequest;
import es.carlos.monguering.services.interfaces.AuthenticationService;
import es.carlos.monguering.services.interfaces.BuildingService;
import es.carlos.monguering.services.interfaces.ChainProcessorService;
import es.carlos.monguering.services.interfaces.PlayerService;

@Service
public class ChainProcessorServiceImpl implements ChainProcessorService {

	@Autowired
	private PlayerService playerService;
		
	@Autowired
	private BuildingService buildingService;
	
	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public Player updateResources(String token, UpdateResourcesRequest request) {
		LocalDateTime now = LocalDateTime.now();
		BaseChainProcessor checkAuthorization = new CheckAuthorizationChainItem(token, authenticationService);
		BaseChainProcessor extractPlanet = checkAuthorization.setNext(new ExtractPlanetChainItem(request));
		BaseChainProcessor upgrade = extractPlanet.setNext(new CheckForUpgradesChainItem(playerService));
		BaseChainProcessor update = upgrade.setNext(new UpdateResourcesChainItem(playerService, now));
		update.setNext(new GetAvailableBuildingsChainItem(buildingService));
		return checkAuthorization.run();
		
	}
	
	@Override
	public Player build(String token, BuildRequest request) {
		LocalDateTime now = LocalDateTime.now();
		BaseChainProcessor checkAuthorization = new CheckAuthorizationChainItem(token, authenticationService);
		BaseChainProcessor extractPlanet = checkAuthorization.setNext(new ExtractPlanetChainItem(request));
		BaseChainProcessor upgrade = extractPlanet.setNext(new CheckForUpgradesChainItem(playerService));
		BaseChainProcessor update = upgrade.setNext(new UpdateResourcesChainItem(playerService, now));
		update.setNext(new BuildChainItem(buildingService, request.getBuildingType()));
		return checkAuthorization.run();
	}



}
