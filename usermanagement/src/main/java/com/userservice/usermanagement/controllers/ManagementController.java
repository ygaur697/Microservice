package com.userservice.usermanagement.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.usermanagement.dao.UserDao;
import com.userservice.usermanagement.dataservice.ControllerService;
import com.userservice.usermanagement.models.User;
import com.userservice.usermanagement.models.MongoUserModel;
import com.userservice.usermanagement.models.PostgresUserModel;
import com.userservice.usermanagement.payload.request.SignupRequest;
import com.userservice.usermanagement.payload.response.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/auth")
public class ManagementController {
	/**
	 * Management controller is for all the crud operations needful for user
	 * management
	 * 
	 */
	AuthenticationManager authenticationManager;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private UserDao repository;

	@Autowired
	@Value("${Postgres.value}")
	private boolean dbValue;

	@Autowired
	ControllerService service;

	@PostMapping("/adduser")
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		/*
		 * This controller Creates new user based on all the entities for the user
		 * 
		 */
		if (dbValue) {

			if (service.existsByUsername(signUpRequest)) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
			}

			if (service.existsByEmail(signUpRequest)) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
			}
			service.createPostgresUser(signUpRequest);
		}

		else {
			if (service.existsByUsername(signUpRequest)) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
			}

			if (service.existsByEmail(signUpRequest)) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
			}
			service.createMongoUser(signUpRequest);
		}
		return ResponseEntity.ok(new MessageResponse("User Added successfully!"));
	}

	@Transactional
	@PostMapping("/deleteuser/{username}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteuser(@PathVariable String username) {

		repository.deleteByUsername(username);
		return "User Deleted succesfully";
	}

	@PutMapping("/updatepassword")
	@PreAuthorize("hasRole('ADMIN')")
	public String updatepassword(@RequestBody User user) {
		String encodedPassword = null;
		if (user != null) {
			if (user.getPassword() != null) {
				encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
			}

			if (dbValue) {
				String name = user.getUsername();
				PostgresUserModel users = (PostgresUserModel) repository.findByUsername(name);
				users.setPassword(encodedPassword);
				repository.save(users);
			} else {
				String name = user.getUsername();
				MongoUserModel users = (MongoUserModel) repository.findByUsername(name);
				users.setPassword(encodedPassword);
				repository.save(users);
			}
		}
		return "User updated";
	}

	@PutMapping("/updateuser")
	@PreAuthorize("hasRole('ADMIN')")
	public String updateuser(@RequestBody User user) {

		if (user != null) {
			if (dbValue) {
				String name = user.getUsername();
				PostgresUserModel users = (PostgresUserModel) repository.findByUsername(name);
				users.setUsername(user.getUsername());
				users.setCustomerid(user.getCustomerid());
				users.setCustomername(user.getCustomername());
				users.setDescription(user.getDescription());
				users.setEmail(user.getEmail());
				repository.save(users);
			} else {
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
		return "User details updated";

	}

}
