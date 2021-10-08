package com.implosion.user.services.impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.implosion.user.dto.AuthorizationResponseDTO;
import com.implosion.user.services.exceptions.AuthorizationServiceException;
import com.implosion.user.services.exceptions.NotAuthorizedException;
import com.implosion.user.services.interfaces.AuthorizationService;
import com.implosion.user.services.utils.LogUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class AuthorizationServiceRest implements AuthorizationService {

	@Value("${rest.authorization.endpoint}")
	private String authorizationEndpoint;

	@Override
	public String authorize(String authorizationHeader) throws AuthorizationServiceException, NotAuthorizedException {
		if (!StringUtils.hasText(authorizationHeader)) {
			String message = "Authorization header not set or empty";
			log.debug(message);
			throw new NotAuthorizedException(message);
		}

		URI endpoint;
		try {
			endpoint = new URI(authorizationEndpoint);
		} catch (URISyntaxException e) {
			String message = String.format("Authorization service URI failed to build: %s",
					LogUtils.toJsonString("authorizationEndpoint", authorizationEndpoint));
			log.error(message);
			throw new AuthorizationServiceException(message, e);
		}

		Mono<ResponseEntity<AuthorizationResponseDTO>> response;
		try {
			response = WebClient.create().post().uri(endpoint).header(HttpHeaders.AUTHORIZATION, authorizationHeader)
					.accept(MediaType.APPLICATION_JSON).retrieve().toEntity(AuthorizationResponseDTO.class);
			return response.block().getBody().getToken();
		} catch (WebClientResponseException e) {
			String message = String.format("Authorization service does not authorize: %s", LogUtils
					.toJsonString("statusCode", e.getStatusCode(), "responseBody", e.getResponseBodyAsString()));
			log.error(message);
			throw new NotAuthorizedException(message, e);
		}
	}

}
