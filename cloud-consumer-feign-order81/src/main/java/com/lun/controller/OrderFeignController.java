package com.lun.controller;

import com.lun.common.bean.CommonResult;
import com.lun.common.entity.Payment;
import com.lun.openfeign.PaymentFeignService;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class OrderFeignController {

    @Autowired
    public PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/feign/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {

        // 此处直接使用OpenFeign封装的远端接口进行调用，不显示的使用restTemplate
        CommonResult<Payment> ret = paymentFeignService.getPaymentById(id);
        return ret;

    }

    // 检验超时
    @GetMapping("/consumer/feign/timeout/payment/get/{id}")
    public CommonResult<Payment> getPaymentTimeOut(@PathVariable("id") Long id) {

        // 此处直接使用OpenFeign封装的远端接口进行调用，不显示的使用restTemplate
        CommonResult<Payment> ret = paymentFeignService.getPaymentTimeOutById(id);
        return ret;

    }

/*    @PostMapping("/consumer/payment/create")
    public CommonResult create(@RequestBody Payment payment) {

        String url = PAYMENT_URL + "/payment/create";
        CommonResult ret = restTemplate.postForObject(url, payment, CommonResult.class);
        return ret;
    }*/



}
