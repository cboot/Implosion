package com.implosion.user.services.exceptions;

public class NotAuthorizedException extends Exception {

	private static final long serialVersionUID = 9160964272956378664L;

	public NotAuthorizedException(String message) {
		super(message);
	}
	
	public NotAuthorizedException(Throwable throwable) {
		super(throwable);
	}
	
	public NotAuthorizedException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
