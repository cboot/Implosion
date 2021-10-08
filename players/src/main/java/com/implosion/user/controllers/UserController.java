package com.implosion.user.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.implosion.user.dto.CreateUserRequestDTO;
import com.implosion.user.dto.CreateUserResponseDTO;
import com.implosion.user.dto.UserDTO;
import com.implosion.user.entities.User;
import com.implosion.user.services.impl.CreateUserUseCase;
import com.implosion.user.services.impl.DeleteUserUseCase;
import com.implosion.user.services.impl.FilterUsersUseCase;
import com.implosion.user.services.impl.GetUserUserUseCase;
import com.implosion.user.services.utils.Mapper;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private FilterUsersUseCase filterUseUseCase;

	@Autowired
	private CreateUserUseCase createUserUseCase;

	@Autowired
	private DeleteUserUseCase deleteUserUseCase;

	@Autowired
	private GetUserUserUseCase getUserUseCase;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@GetMapping
	public ResponseEntity<List<UserDTO>> filterUsers(
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "results", required = false, defaultValue = "100") Integer maxResultsPerPage) {

		List<UserDTO> output = filterUseUseCase.filterUsers(username, page, maxResultsPerPage).stream()
				.map(User -> Mapper.toDTO(User)).collect(Collectors.toList());
		return new ResponseEntity<>(output, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CreateUserResponseDTO> createUser(@RequestBody @Valid CreateUserRequestDTO request) {

		User user = createUserUseCase.createUser(request.getUsername(), request.getPassword(), request.getEmail());

		CreateUserResponseDTO output = new CreateUserResponseDTO();
		output.setUrl(buildUserUrl(user.getId()));
		output.setUserId(user.getId());
		return new ResponseEntity<>(output, HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId) {
		deleteUserUseCase.deleteUser(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("userId") String userId) {

		UserDTO payload = Mapper.toDTO(getUserUseCase.getUser(userId));
		return new ResponseEntity<>(payload, HttpStatus.OK);
	}

	private String buildUserUrl(String userId) {
		return httpServletRequest.getRequestURL() + "/" + userId;
	}

}
