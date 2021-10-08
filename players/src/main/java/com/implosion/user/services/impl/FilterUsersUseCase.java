package com.implosion.user.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.implosion.user.entities.User;
import com.implosion.user.repositories.UserRepository;
import com.implosion.user.services.utils.LogUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FilterUsersUseCase {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> filterUsers(String username, int page, int maxResultsPerPage) {
		log.debug("Filtering users: {}", LogUtils.toJsonString("username", username, "page", page, "maxResultsPerPage", maxResultsPerPage));
		if (page < 0) {
			log.error("Page is less than 0");
			throw new IllegalArgumentException("Page must be 0 or a positive integer");
		}
		
		if (maxResultsPerPage < 1) {
			log.error("maxResultsPerPage is less than 1");
			throw new IllegalArgumentException("MaxResultsPerPage must be 1 or greater");
		}
		
		return userRepository.filterUsers(username, page, maxResultsPerPage);
	}

}
