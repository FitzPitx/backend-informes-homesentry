package com.microservice.references;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceReferencesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceReferencesApplication.class, args);
	}

}
