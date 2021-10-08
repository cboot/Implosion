package com.implosion.user.services.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.implosion.user.entities.User;
import com.implosion.user.repositories.UserRepository;
import com.implosion.user.services.utils.LogUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CreateUserUseCase {

	@Autowired
	private UserRepository userRepository;
	
	public User createUser(String username, String password, String email) {
		log.debug("Creating user: {}", LogUtils.toJsonString("username", username, "password", password.replaceAll("(.)", "#"), "email", email));
		UUID uuid = UUID.randomUUID();
		User user = new User();
		user.setId(uuid.toString());
		user.setEmail(email);
		user.setName(username);
		user.setPassword(password);
		user.setRegisterDate(new Date());
		userRepository.save(user);
		log.debug("User created with: {}", LogUtils.toJsonString("UUID", uuid, "username", username));
		return user;
	}
}
