package com.andydelgado.vinapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class VinapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VinapiApplication.class, args);
	}

}
