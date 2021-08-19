package es.carlos.monguering.rest.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import es.carlos.monguering.security.exceptions.InvalidCredentialsException;
import es.carlos.monguering.security.exceptions.InvalidTokenException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { InvalidCredentialsException.class })
	protected ResponseEntity<Object> invalidCredentials(RuntimeException ex, WebRequest request) {
		String message = ex.getMessage();
		HttpStatus code = HttpStatus.UNAUTHORIZED;
		RestResponseException output = new RestResponseException();
		output.setCode(code.value());
		output.setMessage(message);
		output.setException(ex.getClass().getSimpleName());
		return handleExceptionInternal(ex, output, new HttpHeaders(), code, request);
	}
	
	@ExceptionHandler(value = { InvalidTokenException.class })
	protected ResponseEntity<Object> invalidToken(RuntimeException ex, WebRequest request) {
		String message = ex.getMessage();
		HttpStatus code = HttpStatus.FORBIDDEN;
		RestResponseException output = new RestResponseException();
		output.setCode(code.value());
		output.setMessage(message);
		output.setException(ex.getClass().getSimpleName());
		return handleExceptionInternal(ex, output, new HttpHeaders(), code, request);
	}
}