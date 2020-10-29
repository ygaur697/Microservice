package com.userservice.usermanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document(collection = "roles")
@Table(name = "roles")
public class RoleModel {
	/**
	 * Model for role with all the attributes
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "roleId", nullable = false, length = 1024)
	private String id;

	@Column(name = "name", nullable = false, length = 1024)
	private String name;

	public String getid() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}


