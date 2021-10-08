package com.implosion.user.repositories;

import java.util.List;

import com.implosion.user.entities.User;

public interface UserRepositoryCustom {

	List<User> filterUsers(String username, int page, int maxResultsPerPage);

}
