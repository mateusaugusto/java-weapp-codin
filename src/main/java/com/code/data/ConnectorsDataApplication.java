package com.code.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableJpaRepositories
public class ConnectorsDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectorsDataApplication.class, args);
	}
}
