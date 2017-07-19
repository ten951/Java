package com.wyt.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@SpringBootApplication
@EnableEurekaClient
public class RestaurantApp {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApp.class, args);
	}
}
