package com.userservice.usermanagement.dataservice;

import com.userservice.usermanagement.models.User;
import com.userservice.usermanagement.payload.request.SignupRequest;

public interface UserService {
	public abstract boolean existsByUsername(SignupRequest signUpRequest);

	public abstract boolean existsByEmail(SignupRequest signUpRequest);

	public abstract void createPostgresUser(SignupRequest signUpRequest);

	public abstract void createMongoUser(SignupRequest signUpRequest);
	
	public abstract void updatePostgresUserPassword(User<?> user, String encodedPassword);
	
	public abstract void updateMongoUserPassword(User<?> user, String encodedPassword);
	
	public abstract void updatePostgresUser(User<?> user);
	
	public abstract void updateMongoUser(User<?> user);

}
