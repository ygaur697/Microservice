package com.userservice.usermanagement.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Role {
	  @Id
	  private String id;

	  private URole name;

	  public Role() {

	  }

	  public Role(URole name) {
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
