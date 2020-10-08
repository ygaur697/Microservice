package com.userservice.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userservice.usermanagement.models.PostgresUserModel;

@Repository
public interface PostgresUserRepository extends JpaRepository<PostgresUserModel, String> {
	/**
	 * User repository with needful custom query to perform basic operations
	 */
	Optional<PostgresUserModel> findByUsername(String username); // find user name

	Boolean existsByUsername(String username); // check if user name exists

	Boolean existsByEmail(String email); // check if email exists

	Optional<PostgresUserModel> deleteByUsername(String username); // delete user
}
