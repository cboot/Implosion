package com.implosion.user.services.interfaces;

import com.implosion.user.services.exceptions.AuthorizationServiceException;
import com.implosion.user.services.exceptions.NotAuthorizedException;

public interface AuthorizationService {

	public String authorize(String authorizationHeader) throws AuthorizationServiceException, NotAuthorizedException;
}
