package com.userservice.usermanagement.dbconfig;



import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.userservice.usermanagement.mongoRepository")
@EnableMongoAuditing
@ComponentScan({"com.userservice.usermanagement.dao.RoleDao"})
public class MongoConfig {
}
