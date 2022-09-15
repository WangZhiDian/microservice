package com.lun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Payment002 {

    public static void main(String[] args) {
        SpringApplication.run(Payment002.class, args);
    }

}
