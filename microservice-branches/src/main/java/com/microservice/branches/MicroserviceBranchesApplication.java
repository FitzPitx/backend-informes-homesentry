package com.microservice.branches;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceBranchesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceBranchesApplication.class, args);
	}

}
