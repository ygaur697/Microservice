package com.userservice.usermanagement.dataservice;

import com.userservice.usermanagement.payload.request.SignupRequest;

public interface UserService {
	public abstract boolean existsByUsername(SignupRequest signUpRequest);

	public abstract boolean existsByEmail(SignupRequest signUpRequest);

	public abstract void createPostgresUser(SignupRequest signUpRequest);

	public abstract void createMongoUser(SignupRequest signUpRequest);

}
