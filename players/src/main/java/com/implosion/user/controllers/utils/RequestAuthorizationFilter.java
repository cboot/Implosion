package com.implosion.user.controllers.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.implosion.user.services.exceptions.AuthorizationServiceException;
import com.implosion.user.services.exceptions.NotAuthorizedException;
import com.implosion.user.services.interfaces.AuthorizationService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RequestAuthorizationFilter implements Filter {

	@Autowired
	private AuthorizationService authorizationService;
	
	@Value("${config.authorization.request.filter.enabled}")
	private boolean filterEnabled;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		if (filterEnabled) {
	        HttpServletRequest req = (HttpServletRequest) request;
	        if ( req.getMethod().equals("POST") && req.getRequestURI().endsWith("users") ) {
	        	chain.doFilter(request, response);
	        } else {
	       		try {
					authorizationService.authorize(req.getHeader(HttpHeaders.AUTHORIZATION));
				} catch (AuthorizationServiceException e) {
					log.error("Error invoking the authorization service", e);
					log.debug("Involved token: {}", req.getHeader(HttpHeaders.AUTHORIZATION));
			        HttpServletResponse res = (HttpServletResponse) response;
			        res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			        res.getWriter().write(e.getMessage());
				} catch (NotAuthorizedException e) {
					log.error("User is not authorized", e);
					log.debug("Unathorized token: {}", req.getHeader(HttpHeaders.AUTHORIZATION));
			        HttpServletResponse res = (HttpServletResponse) response;
			        res.setStatus(HttpStatus.BAD_REQUEST.value());
			        res.getWriter().write(e.getMessage());
				}
	        }
		} else {
			chain.doFilter(request, response);
		}
	}

}
