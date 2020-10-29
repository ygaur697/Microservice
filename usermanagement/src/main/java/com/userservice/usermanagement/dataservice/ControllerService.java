package com.userservice.usermanagement.dataservice;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.userservice.usermanagement.dao.RoleDao;
import com.userservice.usermanagement.dao.UserDao;
import com.userservice.usermanagement.models.UserModel;
import com.userservice.usermanagement.models.RoleModel;
import com.userservice.usermanagement.payload.request.SignupRequest;


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
	private RoleDao<?> RoleRepository;

	@Autowired
	@Value("${Postgres.value}")
	private boolean dbValue;
	
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
	public void createUser(SignupRequest signUpRequest) {
		UserModel user = new UserModel(signUpRequest.getUsername(), signUpRequest.getEmail(),
				signUpRequest.getCustomername(), signUpRequest.getCustomerid(), signUpRequest.getDescription(),
				encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRoles();
		Set<RoleModel> roles = new HashSet<>();
		
	if(dbValue)
	{
		RoleModel e = new RoleModel();
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
	}
	else
	{
		if (strRoles == null) {
					
					RoleModel userRole = RoleRepository.findByName("ROLE_USER")
							.orElseThrow(() -> new RuntimeException(roleError));
				
					roles.add(userRole);
				} else {
					
					strRoles.forEach(role -> {
						switch (role) {
						case "admin":
							System.out.println("here i lie" +RoleRepository);
							RoleModel adminRole = RoleRepository.findByName("ROLE_ADMIN")
									.orElseThrow(() -> new RuntimeException(roleError));
							
							roles.add(adminRole);
		
							break;
		
						default:
							RoleModel userRole = RoleRepository.findByName("ROLE_USER")
									.orElseThrow(() -> new RuntimeException(roleError));
							roles.add(userRole);
						}
					});
				}
		}
		
		user.setRoles(roles);
		repository.save(user);
	}

	
	@Override
	public void updateUserPassword(UserModel user, String encodedPassword) {
		String name = user.getUsername();
		UserModel users = (UserModel) repository.findByUsername(name);
		users.setPassword(encodedPassword);
		repository.save(users);
		
	}
	
	@Override
	public void updateUser(UserModel user) {
		String name = user.getUsername();
		UserModel users = (UserModel) repository.findByUsername(name);
		users.setUsername(user.getUsername());
		users.setCustomerid(user.getCustomerid());
		users.setCustomername(user.getCustomername());
		users.setDescription(user.getDescription());
		users.setEmail(user.getEmail());
		repository.save(users);
		
	}
	



}
