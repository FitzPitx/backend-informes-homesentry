package com.microservice.acumulado.microservice_acumulado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceAcumuladoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceAcumuladoApplication.class, args);
	}

}
