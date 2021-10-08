package com.implosion.user.controllers.utils;

import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.implosion.user.dto.ErrorDTO;
import com.implosion.user.services.utils.Messages;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {
	
	@Autowired
	private Messages messages;

    @ExceptionHandler ( IllegalArgumentException.class )
    public ResponseEntity<ErrorDTO> mapToBadRequest(IllegalArgumentException ex, WebRequest request ) {
    	ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ((ServletWebRequest)request).getRequest().getRequestURL().toString(), new Date());
        return new ResponseEntity<>(errorDTO,HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler( { MethodArgumentNotValidException.class } )
    public ResponseEntity<ErrorDTO> mapToBadRequest(MethodArgumentNotValidException ex, WebRequest request ) {
    	ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), ex.getFieldError().getDefaultMessage(), ((ServletWebRequest)request).getRequest().getRequestURL().toString(), new Date());
        return new ResponseEntity<>(errorDTO,HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler( { EntityNotFoundException.class } )
    public ResponseEntity<ErrorDTO> mapToNotFound(EntityNotFoundException ex, WebRequest request ) {	
    	ErrorDTO errorDTO = new ErrorDTO(HttpStatus.NOT_FOUND.value(), messages.get("exception.user.notfound"), ((ServletWebRequest)request).getRequest().getRequestURL().toString(), new Date());
        return new ResponseEntity<>(errorDTO,HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler( { HttpRequestMethodNotSupportedException.class} )
    public ResponseEntity<ErrorDTO> mapToNotAllowed(HttpRequestMethodNotSupportedException ex, WebRequest request ) {	
    	ErrorDTO errorDTO = new ErrorDTO(HttpStatus.METHOD_NOT_ALLOWED.value(), messages.get("exception.methodnotallowed"), ((ServletWebRequest)request).getRequest().getRequestURL().toString(), new Date());
        return new ResponseEntity<>(errorDTO,HttpStatus.METHOD_NOT_ALLOWED);
    }
    
    @ExceptionHandler( { RuntimeException.class, Exception.class } )
    public ResponseEntity<ErrorDTO> mapToInternalServerError(Exception ex, WebRequest request ) {
    	log.error("Exception ocurred, mapping to internal server error: ", ex);
    	ErrorDTO errorDTO = new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), ((ServletWebRequest)request).getRequest().getRequestURL().toString(), new Date());
        return new ResponseEntity<>(errorDTO,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
