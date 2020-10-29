package com.userservice.usermanagement.dao;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;

import com.userservice.usermanagement.models.RoleModel;

@Configuration
public interface RoleDao<T> {

	Optional<RoleModel> findByName(String name);
}
