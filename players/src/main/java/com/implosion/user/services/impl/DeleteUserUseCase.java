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
public class DeleteUserUseCase {

	@Autowired
	private UserRepository userRepository;

	public void deleteUser(String userId) {
		log.debug("Deleting user: {}", LogUtils.toJsonString("userId", userId));
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			throw new EntityNotFoundException();
		}
		userRepository.delete(user.get());
	}
	
}
