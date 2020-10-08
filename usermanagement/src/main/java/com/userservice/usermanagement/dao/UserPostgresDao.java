package com.userservice.usermanagement.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.userservice.usermanagement.models.MongoUserModel;
import com.userservice.usermanagement.models.PostgresUserModel;
import com.userservice.usermanagement.repository.PostgresUserRepository;

public class UserPostgresDao implements UserDao<PostgresUserModel> {

	@Autowired
	private PostgresUserRepository repo;

	@Override
	public PostgresUserModel findByUsername(String username) {

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
	public Optional<PostgresUserModel> deleteByUsername(String username) {

		return repo.deleteByUsername(username);
	}

	@Override
	public void save(PostgresUserModel users) {

		repo.save(users);

	}

	@Override
	public void save(MongoUserModel users) {

	}

}
