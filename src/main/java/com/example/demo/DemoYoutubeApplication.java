package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableAutoConfiguration
//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
//@Entity
public class DemoYoutubeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoYoutubeApplication.class, args);
	}

}
