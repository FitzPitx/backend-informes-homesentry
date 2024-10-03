package com.microservice.inventories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceInventoriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceInventoriesApplication.class, args);
	}

}
