package com.userservice.usermanagement.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userservice.usermanagement.dao.UserDao;

import com.userservice.usermanagement.models.UserModel;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	/**
	 * Author-Yash
	 * Used for getting user details
	 */

	

	@Autowired(required=false)
	private UserDao<?> repository;

	@Value("${Postgres.value}")
	private boolean dbType;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username)  {		

			UserModel user = (UserModel) repository.findByUsername(username);
			return UserDetailsImpl.build(user);
		

	}

}
