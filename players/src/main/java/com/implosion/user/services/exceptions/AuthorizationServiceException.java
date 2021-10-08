package com.implosion.user.services.exceptions;

public class AuthorizationServiceException extends Exception {

	private static final long serialVersionUID = 9160964272956378664L;

	public AuthorizationServiceException(String message) {
		super(message);
	}
	
	public AuthorizationServiceException(Throwable throwable) {
		super(throwable);
	}
	
	public AuthorizationServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
