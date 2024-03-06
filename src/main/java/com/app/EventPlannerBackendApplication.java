package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.app.model")
public class
EventPlannerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventPlannerBackendApplication.class, args);
	}

}
