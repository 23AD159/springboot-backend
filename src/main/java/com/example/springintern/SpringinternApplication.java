package com.example.springintern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringinternApplication {
	@Value("${app.jwt-secret}")
	private static String jwtsecret;

	public static void main(String[] args) {
		SpringApplication.run(SpringinternApplication.class, args);
		System.out.println("JWT Key"+jwtsecret);
	}
}
