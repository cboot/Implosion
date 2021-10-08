package com.implosion.user.services.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.implosion.user.entities.User;
import com.implosion.user.repositories.UserRepository;
import com.implosion.user.services.utils.LogUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GetUserUserUseCase {

	@Autowired
	private UserRepository userRepository;
	
	public User getUser(String userId) {
		log.debug("Fetching user: {}", LogUtils.toJsonString("userId", userId));
		Optional<User> fetched = userRepository.findById(userId);
		if (fetched.isPresent()) {
			return fetched.get();
		} else {
			String message = String.format("No user found with %s", LogUtils.toJsonString("userId", userId)); 
			log.error(message);
			throw new EntityNotFoundException(message);
		}
	}
}