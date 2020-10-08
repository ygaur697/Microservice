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
import com.userservice.usermanagement.models.User;
import com.userservice.usermanagement.models.MongoUserModel;
import com.userservice.usermanagement.models.PostgresUserModel;
import com.userservice.usermanagement.payload.request.SignupRequest;
import com.userservice.usermanagement.repository.MongoRoleRepository;

@Service
public class ControllerService implements UserService {
	/**
	 * Author-Yash
	 * This is controller service to detail the controller logic, with interface defined as UserService.
	 */
	@Autowired
	private UserDao<?> repository;
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	MongoRoleRepository mongoRoleRepository;

	String roleError = "Error: Role is not found.";

	@Override
	public boolean existsByUsername(SignupRequest signUpRequest) {
		boolean existsByUsername = repository.existsByUsername(signUpRequest.getUsername());
		if (Boolean.TRUE.equals(existsByUsername)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean existsByEmail(SignupRequest signUpRequest) {
		boolean existsByEmail = repository.existsByEmail(signUpRequest.getEmail());
		if (Boolean.TRUE.equals(existsByEmail)) {
			return true;
		}
		return false;
	}

	@Override
	public void createPostgresUser(SignupRequest signUpRequest) {
		PostgresUserModel user = new PostgresUserModel(signUpRequest.getUsername(), signUpRequest.getEmail(),
				signUpRequest.getCustomername(), signUpRequest.getCustomerid(), signUpRequest.getDescription(),
				encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRoles();
		Set<PostgresRoleModel> roles = new HashSet<>();
		PostgresRoleModel e = new PostgresRoleModel();
		if (strRoles == null) {

			throw new RuntimeException(roleError);
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
					throw new RuntimeException(roleError);
				}
			});
		}

		user.setRoles(roles);
		repository.save(user);

	}

	@Override
	public void createMongoUser(SignupRequest signUpRequest) {
		MongoUserModel user = new MongoUserModel(signUpRequest.getUsername(), signUpRequest.getEmail(),
				signUpRequest.getCustomername(), signUpRequest.getCustomerid(), signUpRequest.getDescription(),
				encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRoles();
		Set<MongoRoleModel> roles = new HashSet<>();

		if (strRoles == null) {
			MongoRoleModel userRole = mongoRoleRepository.findByName(URole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException(roleError));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					MongoRoleModel adminRole = mongoRoleRepository.findByName(URole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException(roleError));
					roles.add(adminRole);

					break;

				default:
					MongoRoleModel userRole = mongoRoleRepository.findByName(URole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException(roleError));
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		repository.save(user);
	}

	@Override
	public void updatePostgresUserPassword(User<?> user, String encodedPassword) {
		String name = user.getUsername();
		PostgresUserModel users = (PostgresUserModel) repository.findByUsername(name);
		users.setPassword(encodedPassword);
		repository.save(users);

	}

	@Override
	public void updateMongoUserPassword(User<?> user, String encodedPassword) {
		String name = user.getUsername();
		MongoUserModel users = (MongoUserModel) repository.findByUsername(name);
		users.setPassword(encodedPassword);
		repository.save(users);
	}

	@Override
	public void updatePostgresUser(User<?> user) {
		String name = user.getUsername();
		PostgresUserModel users = (PostgresUserModel) repository.findByUsername(name);
		users.setUsername(user.getUsername());
		users.setCustomerid(user.getCustomerid());
		users.setCustomername(user.getCustomername());
		users.setDescription(user.getDescription());
		users.setEmail(user.getEmail());
		repository.save(users);

	}

	@Override
	public void updateMongoUser(User<?> user) {
		String name = user.getUsername();
		MongoUserModel users = (MongoUserModel) repository.findByUsername(name);
		users.setUsername(user.getUsername());
		users.setCustomerid(user.getCustomerid());
		users.setCustomername(user.getCustomername());
		users.setDescription(user.getDescription());
		users.setEmail(user.getEmail());
		repository.save(users);

	}

}
