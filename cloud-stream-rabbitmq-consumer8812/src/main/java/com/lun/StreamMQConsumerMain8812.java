package com.lun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StreamMQConsumerMain8812 {

    public static void main(String[] args) {
        SpringApplication.run(StreamMQConsumerMain8812.class, args);
    }

}
