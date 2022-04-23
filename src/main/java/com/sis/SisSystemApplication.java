package com.sis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableAsync
@CrossOrigin(origins = ("http://localhost:4200"))
public class SisSystemApplication extends SpringBootServletInitializer {
	private static final Logger log = LogManager.getLogger(SisSystemApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SisSystemApplication.class, args);
		log.info("SIS backend started successfully");

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SisSystemApplication.class);
	}


}
