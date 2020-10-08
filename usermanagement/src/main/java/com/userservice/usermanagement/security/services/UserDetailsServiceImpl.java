package com.userservice.usermanagement.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userservice.usermanagement.dao.UserDao;
import com.userservice.usermanagement.models.MongoUserModel;
import com.userservice.usermanagement.models.PostgresUserModel;
import com.userservice.usermanagement.repository.PostgresUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	/**
	 * Author-Yash
	 * Used for getting user details
	 */

	@Autowired
	PostgresUserRepository userRepository;

	@Autowired
	private UserDao<?> repository;

	@Value("${Postgres.value}")
	private boolean dbType;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username)  {

		if (dbType) {

			PostgresUserModel user = (PostgresUserModel) repository.findByUsername(username);
			return UserDetailsImpl.build(user);
		} else {

			MongoUserModel user = (MongoUserModel) repository.findByUsername(username);
			return UserDetailsImpl.build(user);
		}

	}

}
