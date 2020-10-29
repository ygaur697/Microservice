package com.userservice.usermanagement.dbconfig;



import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.userservice.usermanagement.postgresRepository")
@ComponentScan({"com.userservice.usermanagement.dao.RoleDao"})
public class PostgresConfig {
}
