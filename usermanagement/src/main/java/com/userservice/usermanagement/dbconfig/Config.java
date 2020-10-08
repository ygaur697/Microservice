package com.userservice.usermanagement.dbconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.userservice.usermanagement.dao.UserDao;
import com.userservice.usermanagement.dao.UserMongoDao;
import com.userservice.usermanagement.dao.UserPostgresDao;
import com.userservice.usermanagement.models.MongoUserModel;
import com.userservice.usermanagement.models.PostgresUserModel;

@Configuration
public class Config {
	/**
	 * Author-Yash
	 * This is Config class, for profile configuration
	 */
	@Bean
	@Profile("postgres")
	public UserDao<PostgresUserModel> userPostgresDao() {
		return new UserPostgresDao();
	}

	@Bean
	@Profile("mongo")
	public UserDao<MongoUserModel> userMongoDao() {
		return new UserMongoDao();
	}

}
