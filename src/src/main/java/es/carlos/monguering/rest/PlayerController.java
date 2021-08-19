package es.carlos.monguering.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.carlos.monguering.rest.dto.request.BuildRequest;
import es.carlos.monguering.rest.dto.request.UpdateResourcesRequest;
import es.carlos.monguering.rest.dto.response.PlayerRest;
import es.carlos.monguering.rest.util.PlayerMapper;
import es.carlos.monguering.services.interfaces.ChainProcessorService;

@RestController
@RequestMapping(value = "/players")
public class PlayerController {

	@Autowired
	private ChainProcessorService chainProcessorService;

	@RequestMapping(method = RequestMethod.POST, path = "UPDATE_RESOURCES")
	public PlayerRest updateResources(@RequestHeader(name = "Authorization") String authorization,
			@RequestBody UpdateResourcesRequest operationRequest) {
		return PlayerMapper
				.toRest(chainProcessorService.updateResources(extractToken(authorization), operationRequest));
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "BUILD")
	public PlayerRest build(@RequestHeader(name = "Authorization") String authorization,
			@RequestBody BuildRequest operationRequest) {
		return PlayerMapper
				.toRest(chainProcessorService.build(extractToken(authorization), operationRequest));
	}

	private String extractToken(String authorization) {
		if (!StringUtils.isEmpty(authorization) && authorization.startsWith("Bearer ")) {
			return authorization.substring("Bearer ".length());
		}
		return null;
	}
}
