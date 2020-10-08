package com.userservice.usermanagement.dbconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.userservice.usermanagement.dao.UserDao;
import com.userservice.usermanagement.dao.UserMongoDao;
import com.userservice.usermanagement.dao.UserPostgresDao;

@Configuration
public class Config {

	@Bean
	@Profile("postgres")
	public UserDao userPostgresDao() {
		return new UserPostgresDao();
	}

	@Bean
	@Profile("mongo")
	public UserDao userMongoDao() {
		return new UserMongoDao();
	}

}
