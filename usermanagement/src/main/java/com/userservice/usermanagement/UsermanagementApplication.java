package com.userservice.usermanagement;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import brave.sampler.Sampler;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
@EnableHystrix
@EnableEncryptableProperties
@EnableAutoConfiguration

public class UsermanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsermanagementApplication.class, args);
	}

	@Bean
    public Sampler defaultSampler() {
    	return Sampler.ALWAYS_SAMPLE;
    }
}
