package com.userservice.usermanagement.postgresRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.userservice.usermanagement.models.RoleModel;


@Repository
public interface PostgresRoleRepository extends JpaRepository<RoleModel, String> {
	/**Author-Yash
	 * Role repository with custom query function to find role
	 */
	Optional<RoleModel> findByName(String string);
}
