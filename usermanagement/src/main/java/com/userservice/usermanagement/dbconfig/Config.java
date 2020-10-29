package com.userservice.usermanagement.dbconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.userservice.usermanagement.dao.RoleDao;
import com.userservice.usermanagement.dao.RoleMongoDao;
import com.userservice.usermanagement.dao.RolePostgresDao;
import com.userservice.usermanagement.dao.UserDao;
import com.userservice.usermanagement.dao.UserMongoDao;
import com.userservice.usermanagement.dao.UserPostgresDao;

import com.userservice.usermanagement.models.UserModel;

@Configuration
public class Config {
	/**
	 * Author-Yash
	 * This is Config class, for profile configuration
	 */
	@Bean
	@Profile("postgres")
	public UserPostgresDao userPostgresDao() {
		return new UserPostgresDao();
	}

	@Bean
	@Profile("mongo")
	public UserMongoDao userMongoDao() {
		return new UserMongoDao();
	}
	
	@Bean
	@Profile("mongo")
	public RoleMongoDao roleMongoDao() {
		return new RoleMongoDao();
	}

	@Bean
	@Profile("postgres")
	public RolePostgresDao rolePostgresDao() {
		return new RolePostgresDao();
	}

}
