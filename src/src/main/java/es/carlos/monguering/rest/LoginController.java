package es.carlos.monguering.rest;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.carlos.monguering.rest.dto.response.LoginResponse;
import es.carlos.monguering.security.exceptions.InvalidCredentialsException;
import es.carlos.monguering.services.interfaces.AuthenticationService;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private AuthenticationService authenticationService;

	@RequestMapping(method = RequestMethod.POST)
	public LoginResponse login(@RequestHeader(name = "Authorization") String authorization)
			throws InvalidCredentialsException {
		if (StringUtils.isEmpty(authorization)) {
			throw new InvalidCredentialsException("Login required");
		}
		return authenticationService.login(extractUser(authorization), extractPassword(authorization));
	}

	private String extractUser(String authorization) throws InvalidCredentialsException {
		String[] split = splitAuthorization(authorization);
		return split[0];
	}

	private String extractPassword(String authorization) throws InvalidCredentialsException {
		String[] split = splitAuthorization(authorization);
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
			digest.update(split[1].getBytes("utf8"));
			return String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			throw new InvalidCredentialsException("Invalid encoding");
		}
	}

	private String[] splitAuthorization(String authorization) throws InvalidCredentialsException {
		
		authorization = authorization.substring("Basic ".length());
		String decoded;
		decoded = new String(Base64.getDecoder().decode(authorization));

		String[] split = decoded.split(":");
		if (split.length != 2) {
			throw new InvalidCredentialsException("Invalid parameters");
		}
		return split;
	}
}
