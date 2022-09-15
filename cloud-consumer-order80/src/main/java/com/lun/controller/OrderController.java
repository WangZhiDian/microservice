package com.lun.controller;

import com.lun.common.bean.CommonResult;
import com.lun.common.entity.Payment;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://localhost:8001";

    @Autowired
    public RestTemplate restTemplate;


    @Resource
    public DiscoveryClient discoveryClient;

/*    @Autowired
    public EurekaDiscoveryClient eurekaDiscoveryClient;*/

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {

        String url = PAYMENT_URL + "/payment/get/" + id;
        CommonResult ret = restTemplate.getForObject(url, CommonResult.class);
        return ret;

    }

    @PostMapping("/consumer/payment/create")
    public CommonResult create(@RequestBody Payment payment) {

        String url = PAYMENT_URL + "/payment/create";
        CommonResult ret = restTemplate.postForObject(url, payment, CommonResult.class);
        return ret;
    }

    @GetMapping("/consumer/discovery")
    public CommonResult getDiscoveryInfo() {

        List<ServiceInstance> services = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance element : services) {
            System.out.println("*****element: "+element);
        }


        return new CommonResult(200, "services", services);

    }


}
