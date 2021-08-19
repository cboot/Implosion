package es.carlos.monguering.security.exceptions;

public class InvalidCredentialsException extends RuntimeException {

	private static final long serialVersionUID = 8713980575706140739L;

	public InvalidCredentialsException(String message) {
		super(message);
	}
}
