package com.userservice.usermanagement.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.userservice.usermanagement.models.MongoUserModel;
import com.userservice.usermanagement.models.PostgresUserModel;
import com.userservice.usermanagement.repository.MongoUserRepository;

public class UserMongoDao implements UserDao<MongoUserModel> {

	@Autowired
	private MongoUserRepository repo;

	@Override
	public MongoUserModel findByUsername(String username) {

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
	public Optional<MongoUserModel> deleteByUsername(String username) {

		return repo.deleteByUsername(username);
	}

	@Override
	public void save(PostgresUserModel users) {

	}

	@Override
	public void save(MongoUserModel users) {

		repo.save(users);
	}

}
