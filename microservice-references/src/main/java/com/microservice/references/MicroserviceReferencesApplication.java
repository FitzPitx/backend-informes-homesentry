package com.microservice.references;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@EntityScan(basePackages = "com.microservice.references.entities")
@EnableJpaRepositories(basePackages = "com.microservice.references.repository")
public class MicroserviceReferencesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceReferencesApplication.class, args);
	}

}
