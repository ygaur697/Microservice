package com.userservice.usermanagement.mongoRepository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.userservice.usermanagement.models.RoleModel;


public interface MongoRoleRepository extends MongoRepository<RoleModel, String> {
	/**
	 * Author-Yash
	 * Role repository with custom query function to find role
	 */
	Optional<RoleModel> findByName(String name);
}
