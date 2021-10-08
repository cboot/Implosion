package com.implosion.user.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.implosion.user.entities.User;
import com.implosion.user.repositories.UserRepository;
import com.implosion.user.services.utils.LogUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UpdateUserUseCase {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GetUserUserUseCase getUserUseCase;
	
	public User updateUser(String userId, String username, String password, String email) {
		log.debug("Updating user: {}", LogUtils.toJsonString("userId", userId, "username", username, "password", password, "email", email));
		User user = getUserUseCase.getUser(userId);
		user.setName(username);
		user.setPassword(password);
		user.setEmail(email);
		return userRepository.save(user);
	}
}
