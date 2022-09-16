package com.lun;

import com.lun.common.middleware.ribbonrule.MyRoundRobinRule;
import com.lun.common.middleware.ribbonrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

// 该注解启动springboot服务
@SpringBootApplication
// 该注解开启Eureka注册中心的功能
@EnableEurekaClient
// 该注解开启服务发现功能，开启非Eureka注册中心的功能
@EnableDiscoveryClient
// 该注解为ribbon负载均衡的开启
// @RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MySelfRule.class)
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MyRoundRobinRule.class)
public class OrderMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }

}
