package com.tortones.APItortones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiTortonesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTortonesApplication.class, args);
	}

}
