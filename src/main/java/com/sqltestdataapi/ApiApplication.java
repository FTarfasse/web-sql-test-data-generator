package com.sqltestdataapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiApplication {

	@Value("${server.port}")
	private String port;

	private static final Logger log = LoggerFactory.getLogger(ApiApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	CommandLineRunner init(){
		return args ->  {
			this.log.info(System.lineSeparator());
			this.log.info("QUICKPERF API IS LIVE ON http://localhost:" + port);
			this.log.info(System.lineSeparator());
		};
	}
}
