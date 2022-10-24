package com.deapika.ResponseWriters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ResponseWritersApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ResponseWritersApplication.class, args);
	}

}
