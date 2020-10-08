package com.userservice.usermanagement.models;

import java.util.HashSet;
import java.util.Set;

public class User<E> {
	/**
	 * User model
	 */

	private String id;

	private String username;

	private String email;

	private String password;

	private String customername;

	private String customerid;

	private String description;

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	protected Set<E> roles = new HashSet<>();

	public User() {
	}

	public User(String username, String email, String customername, String customerid, String description,
			String password) {
		this.username = username;
		this.email = email;
		this.customername = customername;
		this.customerid = customerid;
		this.description = description;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<E> getRoles() {
		return roles;
	}

	protected void setRoles(Set<E> roles) {
		this.roles = roles;
	}
}
