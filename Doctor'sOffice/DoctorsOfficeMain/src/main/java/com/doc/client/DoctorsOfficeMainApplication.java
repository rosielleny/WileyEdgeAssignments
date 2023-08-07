package com.doc.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.doc")
@EnableJpaRepositories(basePackages = "com.doc.model.dao")
@EntityScan("com.doc.dto.entity")
public class DoctorsOfficeMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorsOfficeMainApplication.class, args);
	}
	
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
