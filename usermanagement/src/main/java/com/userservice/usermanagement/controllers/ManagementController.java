package com.userservice.usermanagement.controllers;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.BasicDBObject;
import com.userservice.usermanagement.models.Role;
import com.userservice.usermanagement.models.URole;
import com.userservice.usermanagement.models.User;
import com.userservice.usermanagement.payload.request.SignupRequest;
import com.userservice.usermanagement.payload.response.MessageResponse;
import com.userservice.usermanagement.repository.RoleRepository;
import com.userservice.usermanagement.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/auth")
public class ManagementController {
/**
 * Management controller is for all the crud operations needful for user management
 *  
 */
	AuthenticationManager authenticationManager;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@PostMapping("/adduser")
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		/*
		 * This controller Creates new user based on all the entities for the user
		 * 
		 */
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 signUpRequest.getCustomername(),
							 signUpRequest.getCustomerid(),
							 signUpRequest.getDescription(),
							 encoder.encode(signUpRequest.getPassword()));
		

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();
      
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(URole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(URole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				
				default:
					Role userRole = roleRepository.findByName(URole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User Added successfully!"));
	}
	
	@PostMapping("/deleteuser/{username}")
	@PreAuthorize("hasRole('ADMIN')")	
	public String deleteuser(@PathVariable String username) {
			
		userRepository.deleteByUsername(username);   
		return "User Deleted succesfully";
	}
	
	@PutMapping("/updatepassword")
	@PreAuthorize("hasRole('ADMIN')")	
	public String updatepassword(@RequestBody User users) {
			
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(users.getUsername()));
		User user = mongoTemplate.findOne(query, User.class);
		if (user != null) {
			if(users.getPassword()!=null)
			{
				String encodedPassword = bCryptPasswordEncoder.encode(users.getPassword());
				user.setPassword(encodedPassword);   //update password for the given user
			}			
			mongoTemplate.save(user);
			return "Password updated.";
		} else {
			return "User not found.";
		}
		
	}
	
	@PutMapping("/updateuser")
	@PreAuthorize("hasRole('ADMIN')")	
	public String updateuser(@RequestBody User users) {
			
		Query query = new Query();                  //adding custom query using user name
		query.addCriteria(Criteria.where("username").is(users.getUsername()));
		User user = mongoTemplate.findOne(query, User.class);
		if (user != null) {
			
			//update based on input by the user
			
			if(users.getEmail() != null)
			{
				user.setEmail(users.getEmail());
			}
			if(users.getCustomername() != null)
			{
				user.setCustomername(users.getCustomername());
			}
			if(users.getCustomerid() != null)
			{
				user.setCustomerid(users.getCustomerid());
			}
			if(users.getDescription() != null)
			{
				user.setDescription(users.getDescription());
			}
		
			mongoTemplate.save(user);
			return "User updated.";
		} else {
			return "User not found.";
		}
		
	}
	
	
}
