package com.microservice.lines;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceLinesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceLinesApplication.class, args);
	}

}
