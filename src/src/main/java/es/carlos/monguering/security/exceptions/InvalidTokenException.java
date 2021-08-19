package es.carlos.monguering.security.exceptions;

public class InvalidTokenException extends RuntimeException {

	private static final long serialVersionUID = 8713980575706140739L;

	public InvalidTokenException(String message) {
		super(message);
	}
}
