package com.doc.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.doc")
@ComponentScan(basePackages = "com.doc.model.dao")
@EntityScan(basePackages = "com.doc.dto.entity")
@EnableJpaRepositories(basePackages = "com.doc.model.dao")
public class MedicalRecordApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalRecordApplication.class, args);
	}

}
