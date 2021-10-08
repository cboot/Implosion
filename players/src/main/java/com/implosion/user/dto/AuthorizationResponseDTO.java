package com.implosion.user.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

public class AuthorizationResponseDTO {

	@NotNull
	@Getter
	@Setter
	private String token;
}
