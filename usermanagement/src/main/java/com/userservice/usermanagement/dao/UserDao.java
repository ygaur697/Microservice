	package com.userservice.usermanagement.dao;

import java.util.Optional;


import com.userservice.usermanagement.models.UserModel;

public interface UserDao<T> {

	UserModel findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	Optional<UserModel> deleteByUsername(String username);

	void save(UserModel user);

	

}
