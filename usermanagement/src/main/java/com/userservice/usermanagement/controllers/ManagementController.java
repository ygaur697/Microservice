package com.userservice.usermanagement.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.userservice.usermanagement.models.UserModel;
import com.userservice.usermanagement.payload.request.SignupRequest;
import com.userservice.usermanagement.payload.response.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/auth")
public class ManagementController {
	/**
	 * Management controller is for all the crud operations needful for user
	 * management Author-Yash
	 */
	AuthenticationManager authenticationManager;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private UserDao<?> repository;

	@Autowired
	@Value("${Postgres.value}")
	private boolean dbValue;

	@Autowired
	ControllerService service;

	String userNameException = "Error: Username is already taken!";
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping("/adduser")
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		/*
		 * This controller Creates new user based on all the entities for the user
		 * 
		 */

		if (service.existsByUsername(signUpRequest)) {
			return ResponseEntity.badRequest().body(new MessageResponse(userNameException));
		}

		if (service.existsByEmail(signUpRequest)) {
			return ResponseEntity.badRequest().body(new MessageResponse(userNameException));
		}
		service.createUser(signUpRequest);

		logger.info("{}", "User Added successfully!");
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
	public String updatepassword(@RequestBody UserModel user) {
		String encodedPassword = null;
		if (user != null) {
			if (user.getPassword() != null) {
				encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
			}

			service.updateUserPassword(user, encodedPassword);

		}
		return "User updated";
	}

	@PutMapping("/updateuser")
	@PreAuthorize("hasRole('ADMIN')")
	public String updateuser(@RequestBody UserModel user) {

		if (user != null) {
			service.updateUser(user);
		}
		return "User details updated";

	}

}
