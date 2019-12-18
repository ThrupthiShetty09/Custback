package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@Configuration
@ComponentScan({"com.slk.*"})

public class AbcBank1Application {

	public static void main(String[] args) {
		SpringApplication.run(AbcBank1Application.class, args);
	}

}
