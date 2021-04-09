package com.sqltestdataapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {
	@Value("${server.port}")
	private String port;

	private static final Logger log = LoggerFactory.getLogger(ApiApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
