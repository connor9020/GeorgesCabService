package com.georgescabservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.georgescabservice")
@EntityScan(basePackages = "com.georgescabservice.entity")
@EnableJpaRepositories(basePackages = "com.georgescabservice.repository")
@EnableDiscoveryClient
public class CabBookingApplication {
    public static void main(String[] args) {
        SpringApplication.run(CabBookingApplication.class, args);
        System.err.println("Cab booking micro service up on port number 8081");
    }
}
