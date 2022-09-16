package com.lun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderOpenFeignMain81 {

    public static void main(String[] args) {
        SpringApplication.run(OrderOpenFeignMain81.class, args);
    }

}
