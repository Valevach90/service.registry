package com.andersen.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import reactivefeign.spring.config.EnableReactiveFeignClients;


@EnableEurekaClient
@SpringBootApplication
@EnableReactiveFeignClients
public class InformationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InformationServiceApplication.class, args);
    }
}
