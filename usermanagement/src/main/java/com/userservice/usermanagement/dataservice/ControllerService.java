package com.userservice.usermanagement.dataservice;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.userservice.usermanagement.dao.UserDao;
import com.userservice.usermanagement.models.MongoRoleModel;
import com.userservice.usermanagement.models.PostgresRoleModel;
import com.userservice.usermanagement.models.URole;
import com.userservice.usermanagement.models.MongoUserModel;
import com.userservice.usermanagement.models.PostgresUserModel;
import com.userservice.usermanagement.payload.request.SignupRequest;
import com.userservice.usermanagement.repository.MongoRoleRepository;

@Service
public class ControllerService implements UserService {
	@Autowired
	private UserDao repository;
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	MongoRoleRepository mongo_role_repository;

	@Override
	public boolean existsByUsername(SignupRequest signUpRequest) {
		if (repository.existsByUsername(signUpRequest.getUsername())) {
			return true;
		}
		return false;
	}

	@Override
	public boolean existsByEmail(SignupRequest signUpRequest) {
		if (repository.existsByEmail(signUpRequest.getEmail())) {
			return true;
		}
		return false;
	}

	@Override
	public void createPostgresUser(SignupRequest signUpRequest) {
		PostgresUserModel user = new PostgresUserModel(signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getCustomername(),
				signUpRequest.getCustomerid(), signUpRequest.getDescription(),
				encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRoles();
		Set<PostgresRoleModel> roles = new HashSet<>();
		PostgresRoleModel e = new PostgresRoleModel();
		if (strRoles == null) {

			throw new RuntimeException("Error: Role is not found.");
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					e.setName("ROLE_ADMIN");
					roles.add(e);
					break;
				case "user":
					e.setName("ROLE_USER");
					roles.add(e);
					break;

				default:
					throw new RuntimeException("Error: Invalid Role");
				}
			});
		}

		user.setRoles(roles);
		repository.save(user);

	}

	@Override
	public void createMongoUser(SignupRequest signUpRequest) {
		MongoUserModel user = new MongoUserModel(signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getCustomername(),
				signUpRequest.getCustomerid(), signUpRequest.getDescription(),
				encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRoles();
		Set<MongoRoleModel> roles = new HashSet<>();

		if (strRoles == null) {
			MongoRoleModel userRole = mongo_role_repository.findByName(URole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					MongoRoleModel adminRole = mongo_role_repository.findByName(URole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;

				default:
					MongoRoleModel userRole = mongo_role_repository.findByName(URole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		repository.save(user);
	}

}
