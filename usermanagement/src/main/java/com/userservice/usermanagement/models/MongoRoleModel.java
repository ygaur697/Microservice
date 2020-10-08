package com.userservice.usermanagement.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class MongoRoleModel {
	/**
	 * Model for role with all the attributes
	 */
	@Id
	private String id;

	private URole name;

	public MongoRoleModel() {

	}

	public MongoRoleModel(URole name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public URole getName() {
		return name;
	}

	public void setName(URole name) {
		this.name = name;
	}
}
