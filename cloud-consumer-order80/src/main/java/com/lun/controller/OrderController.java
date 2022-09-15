package com.lun.controller;

import com.lun.common.bean.CommonResult;
import com.lun.common.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://localhost:8001";

    @Autowired
    public RestTemplate restTemplate;



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


}
