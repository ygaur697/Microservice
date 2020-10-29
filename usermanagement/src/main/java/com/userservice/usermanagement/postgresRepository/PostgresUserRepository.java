package com.userservice.usermanagement.postgresRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.userservice.usermanagement.models.UserModel;

@Repository
public interface PostgresUserRepository extends JpaRepository<UserModel, String> {
	/**
	 * Author-Yash
	 * User repository with needful custom query to perform basic operations
	 */
	Optional<UserModel> findByUsername(String username); // find user name

	Boolean existsByUsername(String username); // check if user name exists

	Boolean existsByEmail(String email); // check if email exists

	Optional<UserModel> deleteByUsername(String username); // delete user

//	void save(UserModel users);
}
