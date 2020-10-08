package com.userservice.usermanagement.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "users")
public class PostgresUserModel {
	/**
	 * User model
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id", nullable = false, length = 1024)
	private String id;
	@Column(name = "username", nullable = false, length = 1024)
	private String username;
	@Column(name = "email", nullable = false, length = 1024)
	private String email;
	@Column(name = "password", nullable = false, length = 1024)
	private String password;
	@Column(name = "customername", nullable = false, length = 1024)
	private String customername;
	@Column(name = "customerid", nullable = false, length = 1024)
	private String customerid;
	@Column(name = "description", nullable = false, length = 1024)
	private String description;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private Set<PostgresRoleModel> roles = new HashSet<>();

	public PostgresUserModel() {
	}

	public PostgresUserModel(String username, String email, String customername, String customerid, String description,
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

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

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

	public Set<PostgresRoleModel> getRoles() {
		return roles;
	}

	public void setRoles(Set<PostgresRoleModel> roles) {
		this.roles = roles;
	}

}