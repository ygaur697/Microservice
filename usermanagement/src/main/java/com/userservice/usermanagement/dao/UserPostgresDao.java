package com.userservice.usermanagement.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import com.userservice.usermanagement.models.UserModel;
import com.userservice.usermanagement.postgresRepository.PostgresUserRepository;


public class UserPostgresDao implements UserDao<UserModel> {
	/**
	 * Author-Yash
	 * This is postgres dao that sits between postgres db and and User Dao for data abstraction
	 */
	@Autowired
	private PostgresUserRepository repo;

	@Override
	public UserModel findByUsername(String username) {

		return repo.findByUsername(username).get();
	}

	@Override
	public Boolean existsByUsername(String username) {

		return repo.existsByUsername(username);
	}

	@Override
	public Boolean existsByEmail(String email) {

		return repo.existsByEmail(email);
	}

	@Override
	public Optional<UserModel> deleteByUsername(String username) {

		return repo.deleteByUsername(username);
	}

	
	public void save(UserModel users) {

		repo.save(users);

	}

	

}
