package com.implosion.user.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ErrorDTO {

	@Getter
	@Setter
	
	private int status;
	
	@Getter
	@Setter
	private String message;
	
	@Getter
	@Setter
	private String path;
	
	@Getter
	@Setter
	private Date timestamp;
	
	
}
