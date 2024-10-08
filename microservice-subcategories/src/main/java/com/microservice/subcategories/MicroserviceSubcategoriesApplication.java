package com.microservice.subcategories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceSubcategoriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSubcategoriesApplication.class, args);
	}

}