package com.implosion.user.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class UserDTO {

	@Getter
	@Setter
	private String id;
	
	@Getter
	@Setter
	private String username;
	
	@Getter
	@Setter
	private String email;
	
	@Getter
	@Setter
	private Date registerDate;
	
}
