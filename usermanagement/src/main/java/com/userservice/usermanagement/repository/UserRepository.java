package com.userservice.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.userservice.usermanagement.models.User;

public interface UserRepository extends MongoRepository<User, String> {
	/**
	 *  User repository with needful custom query 
	 *  to perform basic operations
	 */
	  Optional<User> findByUsername(String username); // find user name
	  Boolean existsByUsername(String username); // check if user name exists
	  Boolean existsByEmail(String email);     // check if email exists
	  Optional<User>deleteByUsername(String username); //delete user
}

