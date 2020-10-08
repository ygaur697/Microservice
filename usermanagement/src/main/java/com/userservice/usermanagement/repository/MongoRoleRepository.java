package com.userservice.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.userservice.usermanagement.models.MongoRoleModel;
import com.userservice.usermanagement.models.URole;

public interface MongoRoleRepository extends MongoRepository<MongoRoleModel, String> {
	/**
	 * Author-Yash
	 * Role repository with custom query function to find role
	 */
	Optional<MongoRoleModel> findByName(URole name);
}
