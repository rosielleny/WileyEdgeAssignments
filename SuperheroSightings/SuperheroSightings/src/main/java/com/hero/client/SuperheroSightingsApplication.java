package com.hero.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.hero")
public class SuperheroSightingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperheroSightingsApplication.class, args);
	}
}
