package com.example.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.limitsservice.bean.LimitConfiguration;

@RestController
public class LimitConfigurationController {
	
	@Autowired
	private Configuration configuration;
	@GetMapping("/limits")
	public LimitConfiguration retrieveLimitsConfiguration() {
		return new LimitConfiguration(configuration.getMaximum(),configuration.getMinimum());
	}

}
