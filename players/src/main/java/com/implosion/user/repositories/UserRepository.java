package com.implosion.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.implosion.user.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>, UserRepositoryCustom {
	
}