package com.lun.controller;

import com.lun.common.bean.CommonResult;
import com.lun.common.entity.Payment;
import com.lun.common.middleware.customerlbrule.MyLoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderCustomLbController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    public RestTemplate restTemplate;


    @Resource
    public DiscoveryClient discoveryClient;

    @Autowired
    public MyLoadBalancer myLoadBalancer;


    @GetMapping("/consumer/payment/lb/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {


        // 该处由程序通过服务发现，获取访问调用时，应该访问哪一个服务uri节点
        // 下方的restTemplate在使用时，定义的Bean上，不用添加ribbon的@LoadBalance注解
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        if(instances == null || instances.size() <= 0){
            return null;
        }

        ServiceInstance serviceInstance = myLoadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri+"/payment/lb", CommonResult.class);

    }

    @GetMapping("/consumer/Lb/discovery")
    public CommonResult getDiscoveryInfo() {

        List<ServiceInstance> services = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance element : services) {
            System.out.println("*****element: "+element);
        }


        return new CommonResult(200, "services", services);

    }


}
