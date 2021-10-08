package com.implosion.user.services.exceptions;

public class MissingAuthorizationException extends Exception {

	private static final long serialVersionUID = 9160964272956378664L;

	public MissingAuthorizationException(String message) {
		super(message);
	}
	
	public MissingAuthorizationException(Throwable throwable) {
		super(throwable);
	}
	
	public MissingAuthorizationException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
