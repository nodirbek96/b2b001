package com.example.b2b001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class B2b001Application {

	public static void main(String[] args) {
		SpringApplication.run(B2b001Application.class, args);
	}
	@Bean
	public RestTemplate makeRestTemplate(){
		return new RestTemplate();
	}
}
