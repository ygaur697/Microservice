package com.userservice.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userservice.usermanagement.models.PostgresRoleModel;

@Repository
public interface PostgresRoleRepository extends JpaRepository<PostgresRoleModel, String> {
	/**
	 * Role repository with custom query function to find role
	 */
	Optional<PostgresRoleModel> findByName(String string);
}
