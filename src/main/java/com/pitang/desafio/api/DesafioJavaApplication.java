package com.pitang.desafio.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DesafioJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioJavaApplication.class, args);
	}

}
