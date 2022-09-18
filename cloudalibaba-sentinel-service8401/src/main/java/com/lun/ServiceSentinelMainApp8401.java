package com.lun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceSentinelMainApp8401 {

    public static void main(String[] args) {
        SpringApplication.run(ServiceSentinelMainApp8401.class, args);
    }

}
