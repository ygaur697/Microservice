package com.userservice.usermanagement.mongoRepository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.userservice.usermanagement.models.UserModel;

public interface MongoUserRepository extends MongoRepository<UserModel, String> {
	/**
	 * Author - Yash
	 * User repository with needful custom query to perform basic operations
	 */
	Optional<UserModel> findByUsername(String username); // find user name

	Boolean existsByUsername(String username); // check if user name exists

	Boolean existsByEmail(String email); // check if email exists

	Optional<UserModel> deleteByUsername(String username); // delete user

	
}
