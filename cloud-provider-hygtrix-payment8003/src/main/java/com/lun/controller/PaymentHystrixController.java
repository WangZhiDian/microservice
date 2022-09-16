package com.lun.controller;

import com.lun.common.bean.CommonResult;
import com.lun.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentHystrixController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    CommonResult paymentInfo_OK(@PathVariable("id") Integer id)
    {
        String result = paymentService.paymentInfo_OK(id);
        System.out.println("*****result: "+result);
        return new CommonResult(200, "success", result);
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    CommonResult paymentInfo_TimeOut(@PathVariable("id") Integer id)
    {
        String result = paymentService.paymentInfo_TimeOut(id);
        System.out.println("***** timeout result: "+result);
        return new CommonResult(200, "timeout success", result);
    }

}
