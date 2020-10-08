	package com.userservice.usermanagement.dao;

import java.util.Optional;

import com.userservice.usermanagement.models.MongoUserModel;
import com.userservice.usermanagement.models.PostgresUserModel;

public interface UserDao<T> {

	T findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	Optional<T> deleteByUsername(String username);

	void save(PostgresUserModel users);

	void save(MongoUserModel users);

}
