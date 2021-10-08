package com.implosion.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.util.UriComponentsBuilder;

import com.implosion.user.controllers.UserController;
import com.implosion.user.dto.CreateUserRequestDTO;
import com.implosion.user.dto.CreateUserResponseDTO;
import com.implosion.user.dto.ErrorDTO;
import com.implosion.user.dto.UserDTO;
import com.implosion.user.services.utils.Messages;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.yml")
class ImplosionUserMicroServiceApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private UserController controller;

	@Autowired
	private Messages messages;

	private String baseUri;
	
	@BeforeEach
	public void init() {
		baseUri = "http://localhost:" + port + "/users";
	}

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	// ### GET /users ###
	// Happy paths
	@Test
	public void usersCollectionWorksWithWithoutParameters() {
		ResponseEntity<UserDTO[]> response = restTemplate.getForEntity(buildUriWithParams(baseUri, null),
				UserDTO[].class);
		assertThat(response.getStatusCode() == HttpStatus.OK).isTrue();
		assertThat(response.getBody() != null).isTrue();
	}

	@Test
	public void usersCollectionWorksWithAllParameters() {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("username", "c");
		queryParams.put("page", 1);
		queryParams.put("results", 10);
		ResponseEntity<UserDTO[]> response = restTemplate.getForEntity(buildUriWithParams(baseUri, queryParams),
				UserDTO[].class);
		assertThat(response.getStatusCode() == HttpStatus.OK).isTrue();
		assertThat(response.getBody() != null).isTrue();
	}

	@Test
	public void usersCollectionReturnsEmptyCollection() {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("page", 100000);
		queryParams.put("results", Integer.MAX_VALUE);
		ResponseEntity<UserDTO[]> response = restTemplate.getForEntity(buildUriWithParams(baseUri, queryParams),
				UserDTO[].class, queryParams);
		assertThat(response.getStatusCode() == HttpStatus.OK).isTrue();
		assertThat(response.getBody() != null).isTrue();
		assertThat(response.getBody().length == 0).isTrue();
	}
	// End of Happy paths

	// Boundary paths
	@Test
	public void usersCollectionWithEmptyUsername() {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("username", "");
		queryParams.put("page", Integer.MAX_VALUE);
		queryParams.put("results", Integer.MAX_VALUE);
		ResponseEntity<UserDTO[]> response = restTemplate.getForEntity(buildUriWithParams(baseUri, queryParams),
				UserDTO[].class, queryParams);
		assertThat(response.getStatusCode() == HttpStatus.OK).isTrue();
		assertThat(response.getBody() != null).isTrue();
	}

	@Test
	public void usersCollectionWithoutPageParameter() {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("username", "");
		queryParams.put("results", Integer.MAX_VALUE);
		ResponseEntity<UserDTO[]> response = restTemplate.getForEntity(buildUriWithParams(baseUri, queryParams),
				UserDTO[].class, queryParams);
		assertThat(response.getStatusCode() == HttpStatus.OK).isTrue();
		assertThat(response.getBody() != null).isTrue();
	}

	@Test
	public void usersCollectionWithoutUsernameParameter() {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("page", Integer.MAX_VALUE);
		queryParams.put("results", Integer.MAX_VALUE);
		ResponseEntity<UserDTO[]> response = restTemplate.getForEntity(buildUriWithParams(baseUri, queryParams),
				UserDTO[].class, queryParams);
		assertThat(response.getStatusCode() == HttpStatus.OK).isTrue();
		assertThat(response.getBody() != null).isTrue();
	}

	@Test
	public void usersCollectionWithoutMaxResultsPerPageParameter() {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("username", "");
		queryParams.put("page", Integer.MAX_VALUE);
		ResponseEntity<UserDTO[]> response = restTemplate.getForEntity(buildUriWithParams(baseUri, queryParams),
				UserDTO[].class, queryParams);
		assertThat(response.getStatusCode() == HttpStatus.OK).isTrue();
		assertThat(response.getBody() != null).isTrue();
	}

	@Test
	public void usersCollectionWithBoundaryPageParameter() {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("page", 0);
		ResponseEntity<UserDTO[]> response = restTemplate.getForEntity(buildUriWithParams(baseUri, queryParams),
				UserDTO[].class, queryParams);
		assertThat(response.getStatusCode() == HttpStatus.OK).isTrue();
		assertThat(response.getBody() != null).isTrue();
	}

	@Test
	public void usersCollectionWithBoundaryMaxResultsPerPageParameter() {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("results", 1);
		ResponseEntity<UserDTO[]> response = restTemplate.getForEntity(buildUriWithParams(baseUri, queryParams),
				UserDTO[].class, queryParams);
		assertThat(response.getStatusCode() == HttpStatus.OK).isTrue();
		assertThat(response.getBody() != null).isTrue();
	}

	// End of Boundary paths

	// Error paths
	@Test
	public void usersCollectionWithWrongPageParameter() {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("page", -1);
		ResponseEntity<String> response = restTemplate.getForEntity(buildUriWithParams(baseUri, queryParams),
				String.class, queryParams);
		assertThat(response.getStatusCode() == HttpStatus.BAD_REQUEST).isTrue();
	}

	@Test
	public void usersCollectionWithWrongMaxResultsPerPageParameter() {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("results", 0);
		ResponseEntity<String> response = restTemplate.getForEntity(buildUriWithParams(baseUri, queryParams),
				String.class, queryParams);
		assertThat(response.getStatusCode() == HttpStatus.BAD_REQUEST).isTrue();
	}

	// End of Error paths
	// #### End of GET /users

	// #### POST /users
	// Happy path
	@Test
	public void postNewUserOk() {
		CreateUserRequestDTO req = new CreateUserRequestDTO();
		req.setEmail(randomEmail());
		req.setPassword(randomString());
		req.setUsername(randomString());

		ResponseEntity<CreateUserResponseDTO> response = restTemplate.postForEntity(buildUriWithParams(baseUri, null),
				req, CreateUserResponseDTO.class);
		assertThat(response.getStatusCode() == HttpStatus.OK).isTrue();
		assertThat(!ObjectUtils.isEmpty(response.getBody().getUserId())).isTrue();
		assertThat(!ObjectUtils.isEmpty(response.getBody().getUrl())).isTrue();
		
		CreateUserResponseDTO created = response.getBody();
		
		restTemplate.delete(created.getUrl());
	}

	// Errors path

	@Test
	public void postNewUserWithoutUsername() {
		CreateUserRequestDTO req = new CreateUserRequestDTO();
		req.setEmail(randomEmail());
		req.setPassword(randomString());
		ResponseEntity<ErrorDTO> response = restTemplate.postForEntity(buildUriWithParams(baseUri, null), req,
				ErrorDTO.class);
		assertThat(response.getStatusCode() == HttpStatus.BAD_REQUEST).isTrue();
		ErrorDTO error = response.getBody();
		assertThat(error.getMessage().equals(messages.get("validation.username.notempty"))).isTrue();
	}

	@Test
	public void postNewUserWithoutEmail() {
		CreateUserRequestDTO req = new CreateUserRequestDTO();
		req.setUsername(randomString());
		req.setPassword(randomString());
		ResponseEntity<ErrorDTO> response = restTemplate.postForEntity(buildUriWithParams(baseUri, null), req,
				ErrorDTO.class);
		assertThat(response.getStatusCode() == HttpStatus.BAD_REQUEST).isTrue();
		ErrorDTO error = response.getBody();
		assertThat(error.getMessage().equals(messages.get("validation.email.notempty"))).isTrue();
	}

	@Test
	public void postNewUserWithoutPassword() {
		CreateUserRequestDTO req = new CreateUserRequestDTO();
		req.setUsername(randomString());
		req.setEmail(randomEmail());
		ResponseEntity<ErrorDTO> response = restTemplate.postForEntity(buildUriWithParams(baseUri, null), req,
				ErrorDTO.class);
		assertThat(response.getStatusCode() == HttpStatus.BAD_REQUEST).isTrue();
		ErrorDTO error = response.getBody();
		assertThat(error.getMessage().equals(messages.get("validation.password.notempty"))).isTrue();
	}

	@Test
	public void postNewUserWithShortUsername() {
		CreateUserRequestDTO req = new CreateUserRequestDTO();
		req.setUsername(randomString(3));
		req.setEmail(randomEmail());
		req.setPassword(randomString());
		ResponseEntity<ErrorDTO> response = restTemplate.postForEntity(buildUriWithParams(baseUri, null), req,
				ErrorDTO.class);
		assertThat(response.getStatusCode() == HttpStatus.BAD_REQUEST).isTrue();
		ErrorDTO error = response.getBody();
		assertThat(error.getMessage().equals(messages.get("validation.username.size"))).isTrue();
	}

	@Test
	public void postNewUserWithLongUsername() {
		CreateUserRequestDTO req = new CreateUserRequestDTO();
		req.setUsername(randomString(21));
		req.setEmail(randomEmail());
		req.setPassword(randomString());
		ResponseEntity<ErrorDTO> response = restTemplate.postForEntity(buildUriWithParams(baseUri, null), req,
				ErrorDTO.class);
		assertThat(response.getStatusCode() == HttpStatus.BAD_REQUEST).isTrue();
		ErrorDTO error = response.getBody();
		assertThat(error.getMessage().equals(messages.get("validation.username.size"))).isTrue();
	}

	@Test
	public void postNewUserWithShortPassword() {
		CreateUserRequestDTO req = new CreateUserRequestDTO();
		req.setUsername(randomString());
		req.setEmail(randomEmail());
		req.setPassword(randomString(3));
		ResponseEntity<ErrorDTO> response = restTemplate.postForEntity(buildUriWithParams(baseUri, null), req,
				ErrorDTO.class);
		assertThat(response.getStatusCode() == HttpStatus.BAD_REQUEST).isTrue();
		ErrorDTO error = response.getBody();
		assertThat(error.getMessage().equals(messages.get("validation.password.size"))).isTrue();
	}

	@Test
	public void postNewUserWithLongPassword() {
		CreateUserRequestDTO req = new CreateUserRequestDTO();
		req.setUsername(randomString());
		req.setEmail(randomEmail());
		req.setPassword(randomString(41));
		ResponseEntity<ErrorDTO> response = restTemplate.postForEntity(buildUriWithParams(baseUri, null), req,
				ErrorDTO.class);
		assertThat(response.getStatusCode() == HttpStatus.BAD_REQUEST).isTrue();
		ErrorDTO error = response.getBody();
		assertThat(error.getMessage().equals(messages.get("validation.password.size"))).isTrue();
	}

	@Test
	public void postNewUserWithInvalidEmail() {
		CreateUserRequestDTO req = new CreateUserRequestDTO();
		req.setUsername(randomString());
		req.setEmail(randomString());
		req.setPassword(randomString());
		ResponseEntity<ErrorDTO> response = restTemplate.postForEntity(buildUriWithParams(baseUri, null), req,
				ErrorDTO.class);
		assertThat(response.getStatusCode() == HttpStatus.BAD_REQUEST).isTrue();
		ErrorDTO error = response.getBody();
		assertThat(error.getMessage().equals(messages.get("validation.email.valid"))).isTrue();
	}

	// End Errors path
	// #### End POST /users

	
	// #### DELETE /users/{userId}
	@Test
	public void deleteUserUnknownUser() {
		ResponseEntity<ErrorDTO> response = restTemplate.exchange(
				buildUriWithParams(baseUri + "/" + randomString(24), null), HttpMethod.DELETE, null, ErrorDTO.class);
		assertThat(response.getStatusCode() == HttpStatus.NOT_FOUND).isTrue();
		assertThat(response.getBody().getMessage().equals(messages.get("exception.user.notfound"))).isTrue();
	}
	
	@Test
	public void deleteUserNoUser() {
		ResponseEntity<ErrorDTO> response = restTemplate.exchange(
				buildUriWithParams(baseUri + "/", null), HttpMethod.DELETE, null, ErrorDTO.class);
		assertThat(response.getStatusCode() == HttpStatus.METHOD_NOT_ALLOWED).isTrue();
		assertThat(response.getBody().getMessage().equals(messages.get("exception.methodnotallowed"))).isTrue();
	}
	
	@Test
	public void deleteExistingUser() {
		CreateUserRequestDTO req = new CreateUserRequestDTO();
		req.setEmail(randomEmail());
		req.setPassword(randomString());
		req.setUsername(randomString());

		ResponseEntity<CreateUserResponseDTO> response = restTemplate.postForEntity(buildUriWithParams(baseUri, null),
				req, CreateUserResponseDTO.class);
		assertThat(response.getStatusCode() == HttpStatus.OK).isTrue();
		assertThat(!ObjectUtils.isEmpty(response.getBody().getUserId())).isTrue();
		assertThat(!ObjectUtils.isEmpty(response.getBody().getUrl())).isTrue();
		
		CreateUserResponseDTO created = response.getBody();
		ResponseEntity<Object> responseDeleted = restTemplate.exchange(created.getUrl(), HttpMethod.DELETE, null, Object.class);
		assertThat(responseDeleted.getStatusCode() == HttpStatus.OK).isTrue();

	}

	// #### End DELETE /users/{userId}
	
	// Utils

	private String buildUriWithParams(String uri, Map<String, Object> queryParams) {
		if (CollectionUtils.isEmpty(queryParams)) {
			return uri;
		}

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(uri);
		for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
			builder.queryParam(entry.getKey(), entry.getValue());
		}
		return builder.build().toUriString();

	}
	
	

	private String randomEmail() {
		return randomString(5) + "@" + randomString(5) + "." + randomString(3);
	}

	private String randomString() {
		return randomString(10);
	}

	private String randomString(int targetStringLength) {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedString;
	}
}
