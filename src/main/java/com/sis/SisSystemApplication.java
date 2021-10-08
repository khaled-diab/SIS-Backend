package com.sis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SisSystemApplication {
	private static final Logger log = LogManager.getLogger(SisSystemApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SisSystemApplication.class, args);
		log.info("SIS backend started successfully");		
	}

}
