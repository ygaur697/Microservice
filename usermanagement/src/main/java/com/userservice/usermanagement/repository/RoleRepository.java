package com.userservice.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.userservice.usermanagement.models.Role;
import com.userservice.usermanagement.models.URole;

public interface RoleRepository extends MongoRepository<Role, String> {
	  Optional<Role> findByName(URole name);
	}
