package com.userservice.usermanagement.payload.request;

public class LoginRequest {
	/**
	 * Login request definition
	 */
	private String username;

	
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
