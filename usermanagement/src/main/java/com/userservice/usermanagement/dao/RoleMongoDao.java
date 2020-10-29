package com.userservice.usermanagement.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import com.userservice.usermanagement.models.RoleModel;
import com.userservice.usermanagement.mongoRepository.MongoRoleRepository;




public class RoleMongoDao implements RoleDao<RoleModel> {

	@Autowired
	private MongoRoleRepository repo;
	
	@Override
	public Optional<RoleModel> findByName(String name) {
		
		return repo.findByName(name);
		
	}

}
