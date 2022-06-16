package com.embedded.apiapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiappApplication {

	public static void main(String[] args) {
		System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow","{}");
		System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow",":");
		System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow","\\");
		SpringApplication.run(ApiappApplication.class, args);
	}

}
