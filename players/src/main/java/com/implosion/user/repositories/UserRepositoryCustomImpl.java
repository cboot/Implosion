package com.implosion.user.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.implosion.user.entities.User;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> filterUsers(String username, int page, int maxResultsPerPage) {
		username = username == null ? "" : username;
		return entityManager.createNamedQuery("user.filterUsers").setParameter("username", "%"+username+"%").setFirstResult(Math.abs(page * maxResultsPerPage)).setMaxResults(maxResultsPerPage).getResultList();
	}

	    
}
