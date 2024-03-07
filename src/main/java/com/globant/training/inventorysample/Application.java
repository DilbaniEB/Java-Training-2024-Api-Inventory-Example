package com.globant.training.inventorysample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.globant.training.inventorysample"})
@EntityScan(basePackages = {"com.globant.training.inventorysample"})
@SpringBootApplication

public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
