package com.userservice.usermanagement.dataservice;


import com.userservice.usermanagement.models.UserModel;
import com.userservice.usermanagement.payload.request.SignupRequest;

public interface UserService {
	public abstract boolean existsByUsername(SignupRequest signUpRequest);

	public abstract boolean existsByEmail(SignupRequest signUpRequest);

	public abstract void createUser(SignupRequest signUpRequest);	
	
	public abstract void updateUserPassword(UserModel user, String encodedPassword);	
	
	public abstract void updateUser(UserModel user);
	
	

}
